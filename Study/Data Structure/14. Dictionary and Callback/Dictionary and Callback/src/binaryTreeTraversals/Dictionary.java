package binaryTreeTraversals;

public abstract class Dictionary<K, O> {
	
	private int _size;
	
	public int size() {
		return this._size;
	}
	protected void setSize(int newSize) {
		this._size = newSize;
	}
	public Dictionary() {
		this.setSize(0);
	}
	
	public boolean isEmpty() {
		return (this._size == 0);
	}
	
	public abstract boolean isFull();
	public abstract boolean keyDoesExist(K aKey) ;
	public abstract O objectForKey(K aKey) ;
	public abstract boolean addKeyAndObject(K aKey, O anObject) ;
	public abstract O removeObjectForKey(K aKey);
	public abstract void clear();

}
