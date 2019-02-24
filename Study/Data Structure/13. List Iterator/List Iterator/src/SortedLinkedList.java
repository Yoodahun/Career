

public class SortedLinkedList<E extends Comparable<E>> implements List<E> {
	private int _size;
	private Node<E> _head;
	
	 public SortedLinkedList() {
		// TODO Auto-generated constructor stub
		 this._size = 0;
		 this._head = null;
	}
	
	
	@Override
	public boolean add(E anElement) {
		// TODO Auto-generated method stub
	
			Node<E> search = this._head;
			Node<E> previousNode = null;
			
			int i = 0;
			
			while (search != null) {
				if(search.element().compareTo(anElement) > 0) {
					break; //지금 노드가 들어갈 노드보다 값이 크다면
				}
				previousNode = search;
				search = search.next();
			}
			
			if (search == this._head) {
				Node<E> newNode = new Node(anElement, this._head);
				this._head = newNode;
			} else {
				Node<E> newNode = new Node(anElement, search); //지금 노드를 다음노드로 밀어내도 넣기
				previousNode.setNext(newNode);
			
			}
			this._size++;
			return true;
		}
		
	
	
	

	@Override
	public boolean contains(E anElement) {
		// TODO Auto-generated method stub
		boolean found = false;
		
		Node<E> searchNode = this._head; //제일 앞부터 검색해나가기
		while(searchNode != null && !found) { //검색할 노드가 없고, 못찾을때까지 검색
			if(searchNode.element().compareTo(anElement) == 0){ //노드의 원소값이 입력된 원소값과 같다면
				found = true; //빙고
			} 
			searchNode = searchNode.next(); //아니라면 다음 노드로 이동
		}
		return found;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this._size = 0;
		this._head = null;
		
	}

	@Override
	public E removeMin() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		} else {
			E removedElement = this._head.element();
			this._head = this._head.next();
			return removedElement;
		
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this._size == 0);
	}

	@Override
	public E removeMax() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		} else {
			E removedElement;
			Node<E> searchNode = this._head;
			while(searchNode.next() != null) {
				searchNode = searchNode.next();
			}
			removedElement = searchNode.element();
			searchNode = null;
			this._size--;
			
			return removedElement;
		
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}

	@Override
	public E removeFrom(int aPosition) {
		// TODO Auto-generated method stub
		E removedElement;
		
			if(aPosition < 0 || aPosition >= this._size) {
				return null;
			} else {
				if(aPosition == 0) {
					removedElement = this._head.element();
					this._head = this._head.next();
				} else {
					
					Node<E> previousNode = null;
					Node<E> searchNode = this._head;
					for(int i = 0; i < aPosition; i++) {
						previousNode = searchNode;
						searchNode = searchNode.next();
					}
					removedElement = searchNode.element();
					previousNode.setNext(searchNode.next());
				}
				this._size--;
				return removedElement;
		}
	}
	@Override
	public E elementAt(int anOrder) {
		// TODO Auto-generated method stub
		if(isEmpty()){
			return null;
		} else {
			E returnElement;
			Node<E> searchNode = this._head;
			for(int i=0; i<anOrder; i++) {
				searchNode= searchNode.next();
			}
			returnElement = searchNode.element();
			return returnElement;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public ListIterator<E> listIterator() {
		return new ListIterator();
	}
	
	@SuppressWarnings({"rawtypes", "hiding"})
	public class ListIterator<E> implements Iterator<E> {
		private Node<E> _next;
		
		private ListIterator() {
			this._next = (Node<E>) SortedLinkedList.this._head;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (this._next != null);
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(this._next == null) {
				return null;
			} else {
				@SuppressWarnings("unchecked")
				E element = (E)this._next.element();
				this._next = this._next.next();
				return element;
			}
		}
	}

	
	




}
