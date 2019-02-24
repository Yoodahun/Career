package binaryTreeTraversals;

public class BinaryNode<T> {
	private T _element;
	private BinaryNode<T> _left;
	private BinaryNode<T> _right;
	
	public  BinaryNode() {
		this(null, null, null);
		
	}
	public  BinaryNode (T givenElement, BinaryNode<T> givenLeft, BinaryNode<T> givenRight){
		this._element = givenElement;
		this._left = givenLeft;
		this._right = givenRight;
	}
	public T element() {
		return this._element;
	}
	public void setElement(T newElement) {
		this._element = newElement;
	}
	public BinaryNode<T> left() {
		return this._left;
	}
	public void setLeft(BinaryNode<T> newLeft) {
		this._left = newLeft;
	}
	
	public BinaryNode<T> right() {
		return this._right;
	}
	public void setRight(BinaryNode<T> newRight) {
		this._right = newRight;
	}

}
