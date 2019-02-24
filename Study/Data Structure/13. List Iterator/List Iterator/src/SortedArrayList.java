

public class SortedArrayList<E extends Comparable<E>> implements List<E>{
	private static final int DEFAULT_MAX_SIZE = 20;
	private int _maxSize;
	private int _size;
	private E[] _element;
	
	
	 public SortedArrayList() {
		// TODO Auto-generated constructor stub
		 this (DEFAULT_MAX_SIZE);
	}
	 public SortedArrayList(int givenMaxSize) {
		 this._maxSize = givenMaxSize;
		 this._size = 0;
		 this._element = (E[]) new Comparable[this._maxSize];
	 }

	@Override
	public boolean add(E anElement) {
		// TODO Auto-generated method stub
		
		if (this.isFull()) {
			return false;
		} else if (this.contains(anElement)){
			return false;			
		} else  {
			int foundIndex;
			for(foundIndex = 0 ; foundIndex <this._size ; foundIndex++) {
				if(this._element[foundIndex].compareTo(anElement) > 0) {	
					break;
					}
				}
			for (int i = this._size; i > foundIndex; i--) {
				this._element[i] = this._element[i-1];
			
			}
			
			this._element[foundIndex] = anElement;
			this._size++;
			return true;
		}	
	}

	@Override
	public boolean contains(E anElement) {
		// TODO Auto-generated method stub
		boolean found = false;
		
		for(int i = 0; i < this._size; i++ ) {
			if(this._element[i].compareTo(anElement) == 0) { //두개의 값이 같으면 0
				found = true;
			}
		}
		
		return found;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		
		return (this._size == this._maxSize);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this._size = 0;
		for(int i = 0; i < this._maxSize; i++) {
			this._element[i] = null;
		}
		
	}

	@Override
	public E removeMin() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			return null;
		} else {
			E removedElement = this._element[0];
			
			for ( int i = 0; i<this._size-1; i++) {
				this._element[i] = this._element[i+1];
				}
			this._element[this._size-1] = null;
			this._size--;
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
			E removedElement = this._element[this._size-1];
			this._element[this._size-1] = null;
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
		if(isEmpty()) {
			return null;
		} else {
			if(aPosition >= this._size || aPosition < 0) {
				return null;
			} else {
				E removeElement = this._element[aPosition];
				for ( int i = aPosition; i<this._size-1; i++) {
					this._element[i] = this._element[i+1];
					}
					this._element[this._size-1] = null;
					this._size--;
					return removeElement;
			}
			
		}
	}
	@Override
	public E elementAt(int anOrder) {
		if(anOrder < 0 || anOrder >= this._size) {
			return null;
		} else {
			return this._element[anOrder];
		}
		
	}



	@SuppressWarnings({ "unchecked", "rawtypes"})
	public ListIterator<E> listIterator() {
		return new ListIterator();
	}
	
	@SuppressWarnings({"rawtypes", "hiding"})
	public class ListIterator<E> implements Iterator<E>{
		private int _nextPostion;
		
		private ListIterator() {
			this._nextPostion = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (this._nextPostion < SortedArrayList.this.size());
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(this._nextPostion == SortedArrayList.this.size()){
				return null;	
			} else {
				@SuppressWarnings("unchecked")
				E element = (E) SortedArrayList.this._element[this._nextPostion];
				this._nextPostion++;
				return element;
			}
			
		}
	}

	
	


}
