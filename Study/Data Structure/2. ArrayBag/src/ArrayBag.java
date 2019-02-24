
public class ArrayBag {
	
	private static final int DEFAULT_MAX_SIZE = 100;
	private int _maxSize;
	private int _size;
	private Coin _elements[];
	
	
	public ArrayBag() { //가방의 기본 생성자.
		this._maxSize = DEFAULT_MAX_SIZE;
		this._elements = new Coin[DEFAULT_MAX_SIZE];
		this._size = 0;
		
	}
	
	public ArrayBag(int givenMaxSize) { //가방의 생성자
		this._maxSize = givenMaxSize; //주어진 값으로 최대크기를 정함
		this._elements = new Coin[givenMaxSize]; //주어진 값만큼 코인이 들어갈 가방크기 생성
		this._size = 0;
		
	}
	public int size() {
		return this._size; //코인이 들어가는 사이즈 리턴
	}
	public boolean isEmpty() {
		return (this._size == 0); // 가방이 비었는지 안비었는지
	}
	public boolean isFull() {
		return (this._size == this._maxSize); //가방이 꽉 찼는지 안찼는지
	}
	public boolean doesContain(Coin anElement) { //가방에 특정 코인이 들어있는지 안들어있는지
		boolean found = false;
		
		for (int i = 0; i < this._size && ! found; i++) { //가방의 코인갯수크기만큼이나 특정 코인 찾을때까지
			if(this._elements[i].equals(anElement)) { // i번째의 코인이 특정코인과 같으면
				found = true; //빙고
			}
		}
		return found;
	}
	public int frequencyOf(Coin anElement) { //특정 코인이 몇개나 들어있는지
		int frequencyCount = 0;
		for (int i = 0; i < this._size; i++) {
			if(this._elements[i].equals(anElement)) { //i번째 코인이 특정 코인과 같으면
				frequencyCount++; //갯수 증가
			}
			
		}
		return frequencyCount;
	}
	public int maxElementValue() { //코인들 중의 최대값 반영
		int maxValue = 0;
		for (int i = 0; i < this.size() ; i++) { //가방안에 들어있는 코인의 갯수만큼 확인
			if(maxValue < _elements[i].value()) //최대값보다 i번째 코인의 값이 크다면
			maxValue = _elements[i].value(); //i번째 코인의 값을 최대값으로 변경
		}
		return maxValue;
	}
	public int sumElemnetValues() { //코인들의 합
		int sumValue = 0;
		for (int i = 0; i < this.size(); i++) { // 가방안에 들어있는 코인들의 갯수만큼
			sumValue += _elements[i].value(); //합에 코인의 값을 게속 더함.
			
		}
		return sumValue;
	}
	
	public boolean add(Coin anElement) { // 가방에 코인넣기
		if(this.isFull()) { //만약 꽉 찼으면
			return false; //넣지 말기
		} else { //꽉안찼으면
			this._elements[this._size] = anElement; //size번째 공간에 코인을 넣기 
			this._size++; //사이즈 증가
			return true;
		}
	}
	
	public boolean remove(Coin anElement) { //가방의 코인 삭제하기
		int foundIndex = 0;
		boolean found = false;
		
		for (int i = 0; i < this._size && !found; i++) { //가방에 들어있는 코인의 갯수나 해당 코인을 찾았을때까지
			if(this._elements[i].equals(anElement)) { //i번째 코인이 특정코인과 같다면
				foundIndex = i; //몇번째 코인인지 저장하고
				found = true; //빙고
			}
		}
		//삭제된 원소 이후의 모든 원소를 앞쪽으로 한 칸씩 이동시킨다.
		if (!found) { //찾지않았으면
			return false; //통과	
		} else { //찾았으면
			for (int i = foundIndex; i < this._size-1; i++) { //찾은 코인의 순번부터 코인갯수의 -1만큼 진행
				this._elements[i] = this._elements[i+1]; //찾은 코인의 순번에 그 다음 순번의 코인을 집어넣음
				/*한칸씩 땡김*/
			}
			this._elements[this._size-1] = null; //한칸씩땡기면 맨 뒷칸은 비어있게되므로 null처리
			this._size--; //코인이 들어있는 총 갯수 줄임
			return true;
		}
	}
	public void clear() { //가방 비우기
		
		for(int i = 0; i < this._size; i++) {
			this._elements[i] = null; //가방의 모든 내용물을 비우는 작업
		}
		this._size = 0; //들어있는 갯수를 0개로
	}
	
	
	

}
