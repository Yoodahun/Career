
public class SortedLinkedBag {
	private static final int MAX_TEST_SIZE = 50000;
	private int _size; //여기서 사이즈는 총 노드의 갯수
	private Node _head;

	
	public SortedLinkedBag() { 
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
		Node searchNode = this._head;
		Coin maxCoin = null;
		
		
		while(searchNode != null) { //검색할 노드가 없을때까지. 노드가 존재하는한 계속검색
			maxCoin = searchNode.coin();
			searchNode = searchNode.next();
		}
		return maxCoin;
	}
	
	public boolean add(Coin anCoin) { //코인 저장하기
		if(anCoin.value() < this.MAX_TEST_SIZE || anCoin.value() < 0 ){
			return false;
		} else {
			Node search = this._head;
			Node previousNode = null;
			
			int i = 0;
			while (search != null) {
				if(search.coin().value() > anCoin.value()) {
					break;
				}
				previousNode = search;
				search = search.next();
			}
			
			if (search == this._head) {
				Node newNode = new Node(anCoin, this._head);
				this._head = newNode;
			} else {
				Node newNode = new Node(anCoin, search);
				previousNode.setNext(newNode);
			
			}
			this._size++;
			return true;
		}
		
	
	}

}
