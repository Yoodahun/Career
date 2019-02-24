


public class SortedArrayBag {
	
	private static final int DEFAULT_MAX_SIZE = 100;
	private int _maxSize;
	private int _size;
	private Coin _elements[];
	
	public SortedArrayBag() { //가방의 기본 생성자.
		this._maxSize = DEFAULT_MAX_SIZE;
		this._elements = new Coin[DEFAULT_MAX_SIZE];
		this._size = 0;
		
	}
	public SortedArrayBag(int givenMaxSize) { //가방의 생성자
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
	
	public boolean add (Coin anCoin) {
		int index = 0 ; //코인이 들어갈 자리를 가리키는 값
		if (anCoin.value() > this.DEFAULT_MAX_SIZE || anCoin.value() < 0) {
			return false;
		}
		if (this.isFull()) {
			return false;
		} else {
			
			for (int i = 0; i < this._size; i++) { //코인의 갯수까지 반복
				if(this._elements[index].value() > anCoin.value()) //현재값이 코인의 값보다 크다면
					index = i; //지금 현재값이 가르키는 위치를 인덱스값으로 저장
					break; //반복문 탈출
				}
			
			}

			for(int i = this._size-1; i > index; i-- ) { //i를 현재 코인갯수보다 1 낮은 값으로. 인덱스값보다 낮아질때까지 하나씩 줄여감
				this._elements[i+1] = this._elements[i]; //현재 코인갯수보다 1높은 빈칸의 위치에 현재 코인삽입. 한칸씩 뒤로 밀려나기.
				
		}
			this._elements[index] = anCoin; //비어있는 현재 코인값에 전달받은 코인값 삽입.
			this._size++; //코인의 총 개수 증가
			return true;
		
	}
	
	public Coin maxElement() {
		return this._elements[this._size];
	}
	}
	
	
	

