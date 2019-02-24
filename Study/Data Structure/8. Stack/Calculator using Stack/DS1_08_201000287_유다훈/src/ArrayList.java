
public class ArrayList<T> implements Stack<T> {
	
	private static final int DEFAULT_CAPACITY = 5;
	private int _capacity;
	private int _top;
	private T[] _elements;
	

	
	public ArrayList() {
		this (DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int givenCapacity) {
		this._capacity = givenCapacity;
		this._top = -1;
		this._elements = (T[]) new Object [givenCapacity];
	}
	
	public boolean isEmpty() {
		return (this._top == -1);
	}
	
	public boolean isFull() {
		return (this._top+1 == this._capacity);
	}
	
	public int size() {
		return this._top+1;
	}
	
	
	@Override
	public boolean push(T anElement) {
		// TODO Auto-generated method stub
		if(isFull()) {
			return false;
		} else {
			
			this._top++;
			this._elements[this._top] = anElement;

		}
		return true;
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		} else {
			T topElement = this._elements[this._top];
			this._elements[this._top] = null;
			this._top--;
			return topElement;
		}
		
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;	
		} else {
			return this._elements[this._top];
		}

	}
	
	public void clear() {
		this._top = -1;
		
		for(int i = 0; i < this._capacity; i++) {
			this._elements[i] = null; //집합의 모든 내용물을 비우는 작업
		}
	}
	
	public T elementAt(int aPosition) {
		return this._elements[aPosition];
	}
	
	

}
