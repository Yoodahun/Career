package binaryTreeTraversals;

public class DictionaryElement<K extends Comparable<K>, O> {
	private K _key;
	private O _object;
	
	public DictionaryElement(K givenKey, O givenObject) {
		this._key = givenKey;
		this._object = givenObject;
	}
	public K key() {
		return this._key;
	}
	public void setKey(K newKey) {
		this._key = newKey;
	}
	public O object() {
		return this._object;
	}
	public void setObject(O newObject){
		this._object = newObject;
	}

}
