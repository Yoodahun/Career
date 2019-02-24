package dictionaryPerformanceTest;

public class DictionaryByBinarySearchTree<K extends Comparable<K>, O> extends Dictionary<K, O> {
	private BinaryNode<DictionaryElement<K, O>> _root;
	
	private BinaryNode<DictionaryElement<K, O>> root() {
		return this._root;
	}
	private void setRoot(BinaryNode<DictionaryElement<K, O>> newRoot) {
		this._root = newRoot;
	}
	 public DictionaryByBinarySearchTree() {
		// TODO Auto-generated constructor stub
		 this.clear();
	}
	private DictionaryElement elementForKey(K aKey) {
		BinaryNode<DictionaryElement<K, O>> currentNode = this._root;
		
		while(currentNode != null) {
			if(currentNode.element().key().compareTo(aKey) ==0) {
				return currentNode.element();
			} else if(currentNode.element().key().compareTo(aKey) > 0) {
				currentNode = currentNode.left();
			} else {
				currentNode = currentNode.right();
			}
		}
		return null;
	}
	
	
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDoesExist(K aKey) {
		// TODO Auto-generated method stub
		return (this.elementForKey(aKey) != null);
	}

	@Override
	public O objectForKey(K aKey) {
		// TODO Auto-generated method stub
		DictionaryElement<K, O> element = this.elementForKey(aKey);
		if( element != null) {
			return element.object();
		} else {
			return null;
		}
		
	}

	@Override
	public boolean addKeyAndObject(K aKey, O anObject) {
		// TODO Auto-generated method stub
		DictionaryElement<K, O> elementForAdd = new DictionaryElement<K, O>(aKey, anObject);
		BinaryNode<DictionaryElement<K, O>> nodeForAdd =
				new BinaryNode<DictionaryElement<K,O>>(elementForAdd, null, null);
		
		if(this._root == null) {
			this.setRoot(nodeForAdd);
			this.setSize(1); 
			return true;
		}
		
		BinaryNode<DictionaryElement<K, O>> currentNode = this._root;
		
		while(aKey.compareTo(currentNode.element().key()) != 0) { //같은 키가 없는동안
			if(aKey.compareTo(currentNode.element().key()) < 0) { //노드 키 보다 작다면.
				if(currentNode.left() == null) { //왼쪽이 비어있으면 왼쪽에다가 삽입
					currentNode.setLeft(nodeForAdd);
					this.setSize(this.size()+1);
					return true;
				} else { //비어있지 않다면 현재값을 현재값의 왼쪽노드로 변환
					currentNode = currentNode.left();
				}
			} else { //노드키보다 크다면
				if(currentNode.right() == null) { //오른쪽이 비어있다면
					currentNode.setRight(nodeForAdd);
					this.setSize(this.size()+1);
					return true;
				} else {
					currentNode = currentNode.right();
				}
			}
		}
		return false;
	}
	private DictionaryElement<K, O> removeRightMostElementOfLeftSubTree (BinaryNode<DictionaryElement<K, O>> root) {
		BinaryNode<DictionaryElement<K, O>> leftOfRoot = root.left(); //왼쪽트리
		
		if(leftOfRoot.right() == null) { //만일 왼쪽트리의 오른쪽노드가 없다면?
			root.setLeft(leftOfRoot.left()); //왼쪽트리의 왼쪽을 루트의 왼쪽으로 올려버리기
			return leftOfRoot.element();
		} else {
			BinaryNode<DictionaryElement<K, O>> parentOfRightMost = leftOfRoot;
			BinaryNode<DictionaryElement<K, O>> rightMost = parentOfRightMost.right();
			//오른쪽 노드의 부모를 루트의 왼쪽트리로.
			//제일 오른쪽을부모트리의 오른쪽트리로.
			
			while(rightMost.right() != null) { //오른쪽 노드가 없을때까지 오른쪽으로 계속해서 타고 내려가기
				parentOfRightMost = rightMost;
				rightMost = rightMost.right();
			}
			parentOfRightMost.setRight(rightMost.left());
			return rightMost.element();
			
			
		}
		
		
	}

	@Override
	public O removeObjectForKey(K aKey) {
		// TODO Auto-generated method stub
		if (this._root == null) {
			return null;
		}
		if(aKey.compareTo(this.root().element().key()) == 0) { //지금키와 루트의 키가 같다면?
			O objectForRemove = this.root().element().object();
			
			if((this._root.left() == null) && this._root.right() == null) {
				this.setRoot(null); 
				//현재 키가 루트키이면서 자식이 없는경우
			} else if(this._root.left()==null) { //왼쪽자식이 없는경우
				this.setRoot(this._root.right()); //현재키를 오른쪽노드로
			} else if(this._root.right() == null) {
				this.setRoot(this._root.left()); //현재키를 왼쪽노드로
			} else { //둘다 있는 경우
				this._root.setElement(this.removeRightMostElementOfLeftSubTree(this._root));
				//현재루트의 원소를 왼쪽서브트리의 제일 오른쪽것으로 변경
			}
			this.setSize(this.size()-1);
			return objectForRemove;
		} 
		BinaryNode<DictionaryElement<K, O>> currentNode = this._root;
		BinaryNode<DictionaryElement<K, O>> childNode = null;
		
		do {
			if(aKey.compareTo(currentNode.element().key()) < 0) { //들어오는 키값이 노드보다 작다면
				childNode = currentNode.left(); //차일드노드를 왼쪽값으로 지정
				if(childNode == null) { //지정한 값이 널값이라면
					return null; //널값 리턴
				}
				if(aKey.compareTo(childNode.element().key()) == 0) { //들어오는 키값이 왼쪽자식값과 같다면
					O objectForRemove = childNode.element().object();
					if(childNode.left() == null && childNode.right() == null) { 
						//왼쪽자식의 왼쪽과 오른쪽에 아무것도없다면,
						currentNode.setLeft(null); //현재값의 왼쪽을 널값으로
					} else if (childNode.left() == null) { //왼쪽자식의 왼쪽이 없다면
						currentNode.setLeft(childNode.right()); //왼쪽자식의 오른쪽을 현재의 왼쪽으로
					} else if (childNode.right() == null) { //왼쪽자식의 오른쪽이 없다면
						currentNode.setRight(childNode.left()); //왼쪽자식의 오른쪽을 현재의 오른쪽으로
					} else {
						childNode.setElement(this.removeRightMostElementOfLeftSubTree(childNode));
					}
					this.setSize(this.size()-1);
					return objectForRemove;
					
				}
			} else { //들어오는 키값이 노드보다 크다면
				childNode = currentNode.right(); //자식을 현재의 오른쪽값으로 지정
				if(childNode == null) { //자식이 어무것도 없으면
					return null; //널리턴
				}
				if(aKey.compareTo(childNode.element().key()) == 0) {//현재값이 자식의 값과 같으면
					O objectForRemove = childNode.element().object(); //자식의 값을 지울 값으로
					
					if(childNode.left() == null && childNode.right() == null) { //자식노드가 아무런 노드도 없으면
						currentNode.setRight(null); //현재노드의 오른쪽을 널값으로. 자식노드를 없애는것.	
					} else if (childNode.left() == null) { //자식노드의 왼쪽이 없다면
						currentNode.setRight(childNode.right()); //자식의 오른쪽을 현재노드의 오른쪽으로
					} else if (childNode.right() == null) { //자식의 오른쪽이 없다면
						currentNode.setLeft(childNode.left()) ; //자식의 왼쪽을 현재 노드의 왼쪽으로
					} else { //자식이 양쪽다 가지고 있다면
						childNode.setElement(this.removeRightMostElementOfLeftSubTree(childNode));
					}
					this.setSize(this.size()-1);
					return objectForRemove;
				}
			}
			currentNode = childNode;
		} while(true);
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.setSize(0);
		this.setRoot(null);
		
	}

}
