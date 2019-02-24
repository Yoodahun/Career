package dictionaryPerformanceTest;

public class DictionaryBySortedLinkedList<K extends Comparable<K>, O> extends Dictionary<K, O> {

	private LinkedNode<DictionaryElement<K, O>> _head;
	
	private LinkedNode<DictionaryElement<K, O>> head() {
		return this._head;
	}
	private void setHead(LinkedNode<DictionaryElement<K, O>> newHead) {
		this._head = newHead;
	}
	 public DictionaryBySortedLinkedList() {
		 this.clear();
	}
	
	
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDoesExist(K aKey) {
		// TODO Auto-generated method stub
		LinkedNode<DictionaryElement<K, O>> currentNode = this._head;
		while(currentNode != null) {
			switch(currentNode.element().key().compareTo(aKey)) {
			case -1:
				currentNode = currentNode.next();
				break;
			case 0:
				return true; //키가 있음
			case +1:
				return false; //키가 없음
			}
		}
		return false;
	}

	@Override
	public O objectForKey(K aKey) {
		// TODO Auto-generated method stub
		LinkedNode<DictionaryElement<K, O>> currentNode = this._head;
		
		while((currentNode != null) && (currentNode.element().key().compareTo(aKey)<0)) {
			currentNode = currentNode.next();
		}
		if((currentNode != null) && (currentNode.element().key().compareTo(aKey)==0)) {
			return currentNode.element().object(); //찾았음
		}else {
			return null;//못찾음
		}
	}

	@Override
	public boolean addKeyAndObject(K aKey, O anObject) {
		// TODO Auto-generated method stub
		LinkedNode<DictionaryElement<K, O>> currentNode = this._head;
		LinkedNode<DictionaryElement<K, O>> previousNode = null;
		while((currentNode != null) && (currentNode.element().key().compareTo(aKey) < 0)) {
			previousNode = currentNode;
			currentNode = currentNode.next();
		}
		if ((currentNode != null) && (currentNode.element().key().compareTo(aKey) == 0)) {
			return false; //키가 이미 존재
			
		}
		DictionaryElement<K, O> addedElement = new DictionaryElement<K, O>(aKey, anObject);
		LinkedNode<DictionaryElement<K, O>> addedNode = new LinkedNode<DictionaryElement<K, O>>(addedElement, currentNode);
		
		if(previousNode == null) {
			this.setHead(addedNode);
		} else {
			previousNode.setNext(addedNode);
		}
		this.setSize(this.size()+1);
		return true;
	}

	@Override
	public O removeObjectForKey(K aKey) {
		// TODO Auto-generated method stub
		LinkedNode<DictionaryElement<K, O>> currentNode = this._head;
		LinkedNode<DictionaryElement<K, O>> previousNode = null;
		while((currentNode != null) && (currentNode.element().key().compareTo(aKey) < 0)) {
			previousNode = currentNode;
			currentNode = currentNode.next();
		}
		if ((currentNode != null) && (currentNode.element().key().compareTo(aKey) == 0)) {
			if(currentNode == this._head) {
				this.setHead(currentNode.next());
			} else {
				previousNode.setNext(currentNode.next()); //현재 노드 삭제
			}
			return currentNode.element().object();
		} else {
			return null; //Not Found
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.setSize(0);
		this._head = null;
		
	}

}
