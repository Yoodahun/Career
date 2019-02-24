public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;
	/**
	 * 새롭게 생성된 노드를 리스트의 처음으로 연결
	 */
	private void linkFirst(char element) {
		head = new Node(element, head);
	}
	/**
	 * 과제 (1) 주어진 Node x의 마지막으로 연결된 Node의 다음으로 새롭게 생성된 노드를 연결
	 * 
	 * @param element
	 *            데이터
	 * @param x
	 *            노드
	 */
	private void linkLast(char element, Node x) {
		if (x.next == null) 	//다음 원소로 연결
			this.linkNext(element, x);
		else 	//다음 노드 방문 recursion
			this.linkLast(element, x.next);
	}
	/**
	 * 이전 Node의 다음 Node로 새롭게 생성된 노드를 연결
	 * 
	 * @param element
	 *            원소
	 * @param pred
	 *            이전노드
	 */
	private void linkNext(char element, Node pred) {
		Node next = pred.next;
		pred.next = new Node(element, next);
	}
	/**
	 * 리스트의 첫번째 원소 해제(삭제)
	 * 
	 * @return 첫번째 원소의 데이터
	 */
	private char unlinkFirst() {
		Node x = head;
		char element = x.item;
		head = head.next;
		x.item = UNDEF;
		x.next = null;
		return element;
	}
	/**
	 * 이전Node의 다음 Node연결 해제(삭제)
	 * 
	 * @param pred
	 *            이전노드
	 * @return 다음노드의 데이터
	 */
	private char unlinkNext(Node pred) {
		Node x = pred.next;
		Node next = x.next;
		char element = x.item;
		x.item = UNDEF;
		x.next = null;
		pred.next = next;
		return element;
	}
	/**
	 * 과제 (2) x노드에서 index만큼 떨어진 Node 반환
	 */
	private Node node(int index, Node x) {
		// 채워서 사용, index를 줄여가면서 다음 노드 방문
		
		Node currentNode =x;
		
		if(index != 0) {
			return node(index-1, x.next);
		} 
		return currentNode;

	}
	/**
	 * 과제 (3) 노드로부터  끝까지의 리스트의 노드 갯수 반환
	 */
	private int length(Node x) {
		// 채워서 사용, recusion 사용
		int count;
		
		if (x.next == null) {
			return 1;
		} else {
			count = 1 + length(x.next);
			return count;
		}
		
	}
	/**
	 * 과제 (4) 노드로부터 시작하는 리스트의 내용 반환
	 */
	private String toString(Node x) {
		// 채워서 사용, recusion 사용
		
		String total;
		if(x.next == null) {
			total = Character.toString(x.item);
			return total;
		} else {
			return total= " " + x.item + toString(x.next) ;
		}
		
		 
		
	}

	/**
	 * 원소를 리스트의 마지막에 추가
	 */
	public boolean add(char element) {
		if (head == null) {
			linkFirst(element);
		} else {
			linkLast(element, head);
		}

		return true;
	}
	/**
	 * 원소를 주어진 index 위치에 추가
	 * 
	 * @param index
	 *            리스트에서 추가될 위치
	 * @param element
	 *            추가될 데이터
	 */
	public void add(int index, char element) {
		if (!(index >= 0 && index <= size()))
			throw new IndexOutOfBoundsException("" + index);

		if (index == 0)
			linkFirst(element);
		else
			linkNext(element, node(index - 1, head));
	}
	/**
	 * 리스트에서 index 위치의 원소 반환
	 */
	public char get(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);

		return node(index, head).item;
	}
	/**
	 * 리스트에서 index 위치의 원소 삭제
	 */
	public char remove(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);

		if (index == 0) {
			return unlinkFirst();
		}
		return unlinkNext(node(index - 1, head));
	}
	/**
	 * 리스트의 원소 갯수 반환
	 */
	public int size() {
		return length(head);
	}
	@Override
	public String toString() {
		if (head == null)
			return "[]";

		return "[ " + toString(head) + " "+ " ]";
	}
	/**
	 * 리스트에 사용될 자료구조
	 */
	private static class Node {
		char item;
		Node next;

		Node(char element, Node next) {
			this.item = element;
			this.next = next;
		}
	}
}

