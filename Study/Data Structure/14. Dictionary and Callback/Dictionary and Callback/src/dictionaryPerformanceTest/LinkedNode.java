package dictionaryPerformanceTest;

public class LinkedNode<T> {
	private T _element;
	private LinkedNode<T> _next;
	
	
	
	public LinkedNode(T givenElement) {
		this(givenElement, null);
	}
	
	
	public LinkedNode(T givenElement, LinkedNode<T> givenNext) {
		this._element = givenElement;
		this._next = givenNext;
	}
	
	public T element() {
		return this._element;
	}
	public void setElement(T newElement) {
		this._element = newElement;
	}
	public LinkedNode<T> next() {
		return this._next;
	}
	public void setNext(LinkedNode<T> newNext) {
		this._next = newNext;
	}

}
