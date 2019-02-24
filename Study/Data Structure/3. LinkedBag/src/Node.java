
public class Node {
	
	private Coin _coin; //노드에 담기는 코인
	private Node _next; //다음 노드
	
	
	public Node(){ //노드 초기화
		this._coin = null;
		this._next = null;
	}
	
	public Node(Coin givenCoin) { //노드의 코인값을 설정
		this._coin = givenCoin;
		this._next = null;
	}
	
	public Node(Coin givenCoin, Node givenNext) { //노드의 코인과 다음노드값을 설정
		this._coin = givenCoin;
		this._next = givenNext;
	}
	
	public Coin coin() {
		
		
		return this._coin; //코인값 리턴
	}
	
	public Node next() {
		return this._next; //다음노드값 리턴
		
	}
	public void setCoin (Coin newCoin) {
		this._coin = newCoin; //코인값 새로 설정
		
	}
	public void setNext (Node newNext) {
		this._next = newNext; //다음 노드값 새로 설정
	}
	

}
