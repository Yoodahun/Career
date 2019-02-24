
public class CircularArrayQueue<T> {
	private static final int DEFAULT_CAPACITY = 5; //큐의 기본 용량
	
	private int _capacity; //용량
	private int _front;  //앞
	private int _rear; //뒤
	private T[] _element; //원소를 저장할 제네릭 타입 배열
	
	public CircularArrayQueue() {
		this(DEFAULT_CAPACITY); //기본용량으로 초기화
	}
	
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int givenCapacity) { //전달받는 용량으로 초기화
		this._capacity = givenCapacity;
		this._front = 0;
		this._rear = 0;
		this._element = (T[]) new Object[givenCapacity];
				
	}
	public boolean isEmpty() {
		return (this._front == this._rear); //만약 프론트와 리어가 같다면 비어있다.
		
	}
	public boolean isFull() {
		int nextRear = (this._rear+1) % this._capacity; 
		//리어값의 +1한 값을 용량으로 나누어 남는 나머지의 값
		return (nextRear == this._front); 
		//이 프론트와 같다면 꽉차있는 상태.
		//+1를 하지 않았을때, 리어가 5이고 용량이 5라면 나머지는 0이됨.
		//이렇게되면 큐가 비어있다고 알리는 것과 같게됨. 그렇기 때문에 0번째 자리는 포기.
		
	}
	public int size() {
		if(this._front <= this._rear) { //만약 프론트보다 리어가 크거나 같다면
			return (this._rear - this._front); //리어에서 프론트를 뺀 값이 사이즈
		} else { //아니라면
			return ((this._rear + this._capacity) - this._front);
			//리어에 용량을 더하고, 거기에서 프론트값을 뺀 것이 사이즈
		}
	}
	public T frontElement() { //제일 앞 원소를 리턴하는 것.
		
		if(isEmpty()) {
			return null;
		}else{
			return this._element[this._front+1];
			//this._front는 원소의 직전 위치를 가르키기 때문에, 직전위치는 null값이 들어있다.
			//이 경우에는 nullException이 발생하므로 +1를 해주어야함.
			
		}
			
	}
	public boolean enQueue(T anElement) { //큐에 추가하기
		if(isFull()) { //꽉 차있으면 추가하지않기
			return false;
		} else {
			this._rear = (this._rear +1) % this._capacity; 
			//리어값을 현재 리어값에 +1를 한 다음
			this._element[this._rear] = anElement; //+1한 위치에 원소를 추가하기
			
			return true;
		}
	}
	public T deQueue() { //큐에서 삭제하기
		if(isEmpty()) { //비어있으면 추가하지 않기
			return null;
		} else { 
			this._front = (this._front + 1) % this._capacity;
			//현재 프론트값에서 1을 증가시키기.
			//프론트값은 항상 원소의 직전값을 가르키므로 +1를 하여 삭제하고 싶은 원소의 위치설정
			T removedElement = this._element[this._front]; //이 위치의 원소를 따로 저장
			this._element[this._front] = null; //해당 위치를 비우기
			
			return removedElement; //따로 저장한 값을 반환
		}
	}
	public void clear() {
		this._front = 0;
		this._rear = 0;
		for(int i=1; i < this._capacity ; i++){
			this._element[i] = null; //큐비우기
		}
	}
	public T elementAt(int aPosition) {
		int elementPosition = ((this._front + 1 + aPosition) % this._capacity);
		//프론트+1값이 0일때, 포지션만큼 이동을 해야 내가 원하는 원소를 조회할 수 있음.
		return this._element[elementPosition]; //해당 위치를 리턴
	}
}
