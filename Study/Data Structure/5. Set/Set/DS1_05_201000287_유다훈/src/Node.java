
public class Node<E> {
	
	private E _element; //노드에 담기는 코인
	private Node<E> _next;
	
	
	
	public Node(){ //노드 초기화
		this._element = null;
		this._next = null;
	}
	
	public Node(E givenElement) { //노드의 원소값을 설정
		this._element = givenElement;
		this._next = null;
	}
	
	public Node(E givenElement,  Node<E> givenNext) { //노드의 원소와 다음노드값을 설정
		this._element = givenElement;
		this._next = givenNext;
	}
	
	public E element() {
		
		
		return this._element; //원소값 리턴
	}
	
	public Node<E> next() {
		return this._next; //다음노드값 리턴
		
	}
	public void setStar(E anElement) {
		this._element = anElement; //원소값 새로 설정
		
	}
	public void setNext (Node<E> newNext) {
		this._next = newNext; //다음 노드값 새로 설정
	}
	

}
