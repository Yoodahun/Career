package dictionaryPerformanceTest;

public class DictionaryBySortedArray<K extends Comparable<K>, O> extends Dictionary<K, O> {
	private static final int DEFAULT_CAPACITY = 100000;
	private int _capacity;
	private DictionaryElement<K, O>[] _elements;
	
	public DictionaryBySortedArray() {
		this(DictionaryBySortedArray.DEFAULT_CAPACITY);
	}
	public DictionaryBySortedArray(int givenCapacity){ 
		this._capacity = givenCapacity;
		this._elements = new DictionaryElement[givenCapacity];
	}
	public int capacity() {
		return this._capacity;
	}
	public void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}
	private int positionFor(K aKey) {
		int left=0;
		int right = this.size()-1;
		
		while(left <= right) {
			int mid = (left+right) /2;
			switch(aKey.compareTo(this._elements[mid].key())) {
				case -1: 
					right = mid-1; //왼쪽부분 계속검색
					break;
				case 0:
					return mid; //키를 찾았음
					
				case +1:
					left = mid+1; //오른쪽을 검색
					break;
			}
					
		}
		return -(left+1);
	}
	
	private void makeRoomAt(int aPosition) {
		for(int i= this.size(); i > aPosition ; i--) {
			this._elements[i] = this._elements[i-1];
		}
	}
	private void removeGapAt(int aPosition) {
		for(int i= aPosition; i<this.size()-1; i++) {
			this._elements[i] = this._elements[i+1];
		}
	}
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return (this.size() == this._capacity);
	}
	@Override
	public boolean keyDoesExist(K aKey) {
		// TODO Auto-generated method stub
		if (this.positionFor(aKey) > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	@Override
	public O objectForKey(K aKey) { //주어진 키를 갖는 객체 리
		// TODO Auto-generated method stub
		int positionWithKey = this.positionFor(aKey);
		if(positionWithKey < 0) { //없을떄?
			return null;
		}
		return this._elements[positionWithKey].object();
	}
	@Override
	public boolean addKeyAndObject(K aKey, O anObject) {
		// TODO Auto-generated method stub
		int positionForAdd = this.positionFor(aKey);
		
		if(positionForAdd >= 0) {
			return false; //키가 이미 존재함.
		}
		positionForAdd = -(positionForAdd+1);
		this.makeRoomAt(positionForAdd);
		this._elements[positionForAdd] = new DictionaryElement<K,O>(aKey, anObject);
		this.setSize(this.size()+1);
		return true;
	}
	
	@Override
	public O removeObjectForKey(K aKey) {
		// TODO Auto-generated method stub
		int positionForRemove = this.positionFor(aKey);
		if(positionForRemove < 0) {
			return null;
		}
		O removedObject = this._elements[positionForRemove].object();
		this.removeGapAt(positionForRemove);
		this.setSize(this.size()-1);
		return removedObject;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.setSize(0);
		for(int i = 1; i<this._capacity; i++) {
			this._elements[i] = null;
		}
		
	}
}
