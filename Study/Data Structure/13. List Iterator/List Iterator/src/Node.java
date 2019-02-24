
public class Node<E> {
	private E _element;
	private Node<E> _next;
	
	public Node() {
		this(null, null);
	}
	public Node(E givenElement) {
		this._element = givenElement;
		this._next = null;
	}
	public Node(E givenElement, Node<E> givenNode) {
		this._element = givenElement;
		this._next = givenNode;
	}
	public E element() {
		return this._element;
	}
	public void setElement(E newElement) {
		this._element = newElement;
	}
	public Node<E> next() {
		return this._next;
		
	}
	public void setNext(Node<E> newNext) {
		this._next = newNext;
	}
	
}
