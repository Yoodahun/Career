
public class ArraySet<E> {
	
	private static final int DEFAULT_MAX_SIZE = 100;
	private int _maxSize;
	private int _size;
	private E[] _elements;
	
	
	@SuppressWarnings("unchecked")
	public ArraySet() { //집합의 기본 생성자.
		this._maxSize = DEFAULT_MAX_SIZE;
		this._elements = (E[]) new Object[DEFAULT_MAX_SIZE];
		this._size = 0;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArraySet(int givenMaxSize) { //집합의 생성자
		this._maxSize = givenMaxSize; //주어진 값으로 최대크기를 정함
		this._elements = (E[]) new Object[givenMaxSize];
		this._size = 0;
		
	}
	public int size() {
		return this._size; //집합 내부의 원소 사이즈 리턴
	}
	public boolean isEmpty() {
		return (this._size == 0); // 집합이 비었는지 안비었는지
	}
	public boolean isFull() {
		return (this._size == this._maxSize); //집합이 꽉 찼는지 안찼는지
	}
	public boolean doesContain(E anElement) { //집합에 중복되는 원소가 있는지 없는지
		boolean found = false;
		
		for (int i = 0; i < this._size && ! found; i++) { //집합의 원소 갯수만큼이나 특정 원소를 찾을때까지
			if(this._elements[i].equals(anElement)) { // i번째의 원소가 특정 원소와 같다면
				found = true;
			}
		}
		
		return found;
	}


	public boolean add(E anElement) { // 집합에 원소넣기
		if(this.isFull()) { //만약 꽉 찼으면
			return false; //넣지 말기
		} else if (this.doesContain(anElement)){
			return false;
		} else{//꽉안찼으면
			this._elements[this._size] = anElement; //size번째 공간에 원소넣기 
			this._size++; //사이즈 증가
			return true;
		}
	}
	
	public E remove(E anElement) { //집합의 원소 삭제하기
		int foundIndex = 0;
		boolean found = false;
		
		for (int i = 0; i < this._size && !found; i++) { //집합에 들어있는 원소의 갯수나 특정 원소를 찾을 때까지
			if(this._elements[i].equals(anElement)) { //i번째 원소가 특정 원소랑 같다면
				foundIndex = i; //원소의 번호저장
				found = true; //빙고
				
			}
		}
		E removedStar = this._elements[foundIndex];
		//삭제된 원소 이후의 모든 원소를 앞쪽으로 한 칸씩 이동시킨다.
		if (!found) { //찾지않았으면
			return null; //통과	
		} else { //찾았으면
			for (int i = foundIndex; i < this._size-1; i++) { //찾은 원소의 순번부터 원소갯수의 -1만큼 진행
				this._elements[i] = this._elements[i+1]; //찾은 원소의 순번에 그 다음 순번의 원소를 집어넣음
				/*한칸씩 땡김*/
			}
			this._elements[this._size-1] = null; //한칸씩땡기면 맨 뒷칸은 비어있게되므로 null처리
			this._size--; //원소가 들어있는 총 갯수 줄임
			return removedStar;
		}
	}
	
	public E removeAny() {
		E removedElements = this._elements[this._size-1];
		this._elements[this._size-1] = null;
		this._size--;
		return removedElements; //아무거나 지우는 것은 배열의 맨 뒤에 있는 녀석을 지움.
		 //맨 앞에 있는 것을 지우면 한칸씩 앞으로 당겨야하는 번거로움 발생
		
		
	}
	public void clear() { //원소 비우기
		
		for(int i = 0; i < this._size; i++) {
			this._elements[i] = null; //집합의 모든 내용물을 비우는 작업
		}
		this._size = 0; //들어있는 갯수를 0개로
	}
	
	
	

}
