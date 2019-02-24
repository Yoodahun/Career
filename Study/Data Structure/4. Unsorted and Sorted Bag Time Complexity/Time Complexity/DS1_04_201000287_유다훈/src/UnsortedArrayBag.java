
public class UnsortedArrayBag {
	
	private static final int DEFAULT_MAX_SIZE = 100;
	private int _maxSize;
	private int _size;
	private Coin _elements[];
	
	
	public UnsortedArrayBag() { //가방의 기본 생성자.
		this._maxSize = DEFAULT_MAX_SIZE;
		this._elements = new Coin[DEFAULT_MAX_SIZE];
		this._size = 0;
		
	}
	
	public UnsortedArrayBag(int givenMaxSize) { //가방의 생성자
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
	
	public Coin maxElement() { //코인들 중의 최대값 반영
		Coin maxValue = this._elements[0];
		for (int i = 0; i < this.size() ; i++) { //가방안에 들어있는 코인의 갯수만큼 확인
			if(maxValue.value() < _elements[i].value()) //최대값보다 i번째 코인의 값이 크다면
			maxValue = this._elements[i]; //i번째 코인의 값을 최대값으로 변경
		}
		return maxValue;
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
	
	
	
	

}
