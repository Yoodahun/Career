
public class Node<T> {
	private T _element;
	private Node<T> _next;
	
	public Node() {
		this(null, null);
	}
	public Node(T givenElement) {
		this._element = givenElement;
		this._next = null;
	}
	public Node(T givenElement, Node<T> givenNode) {
		this._element = givenElement;
		this._next = givenNode;
	}
	public T element() {
		return this._element;
	}
	public void setElement(T newElement) {
		this._element = newElement;
	}
	public Node<T> next() {
		return this._next;
		
	}
	public void setNext(Node<T> newNext) {
		this._next = newNext;
	}

}
