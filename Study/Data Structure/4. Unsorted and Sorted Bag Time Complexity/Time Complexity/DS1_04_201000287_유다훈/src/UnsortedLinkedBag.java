
public class UnsortedLinkedBag {
	
	private int _size; //여기서 사이즈는 총 노드의 갯수
	private Node _head;
	
	public UnsortedLinkedBag() { 
		this._head = null; //초기화실행
		this._size = 0;
		
	}
	public int size() {
		return this._size; //노드갯수 리턴.
	}
	public boolean isEmpty() {
		return (this._size == 0); //비었는지 안비었는지 체크
	}

	
	public Coin maxElement() { //최대값 찾기
		Node searchNode = this._head; //현재 노드는 맨 앞부터
		Coin maxValue = this._head.coin(); //최대값을 저장할 변수
		
		while(searchNode != null) { //검색할 노드가 없을때까지. 노드가 존재하는한 계속검색
		if (maxValue.value() < searchNode.coin().value()) { //최대값과 현재 찾고있는 노드의 코인값과 비교하여 노드값이 크면
			maxValue = searchNode.coin(); //맥스값을 현재 코인으로 교체
		}
		searchNode = searchNode.next(); //다음 노드로 이동
		}
		return maxValue;
	}
	
	public boolean add(Coin anCoin) { //코인 저장하기
		
		Node newNode = new Node(anCoin);
		//주어진 코인값의 새로운 노드를 생성
		newNode.setNext(this._head); // 현재 노드의 다음값을 현재 헤드로 지정. 지금 헤드노드는 뒤로 밀려남.
		this._head = newNode; //현재헤드를 지금 헤드로 새로 지정. 노드를 추가할때는 맨 앞에서 추가하므로 원래 있던 헤드는 뒤로 밀려나가는 형식
		this._size++;
		return true;
	}
	
}
