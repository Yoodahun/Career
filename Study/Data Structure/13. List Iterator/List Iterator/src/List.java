

public interface List<E> {
	public boolean add(E anElement);
	public boolean contains(E anElement);
	public boolean isFull();
	
	public void clear() ;
	
	public E removeMin();
	public boolean isEmpty();
	
	public E removeMax();
	
	public int size();
	public E removeFrom(int aPosition);
	
	public E elementAt(int anOrder);
	public Iterator<E> listIterator();
	


}
