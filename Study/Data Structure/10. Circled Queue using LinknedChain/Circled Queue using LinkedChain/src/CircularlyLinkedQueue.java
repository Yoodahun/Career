
public class CircularlyLinkedQueue<T> {
	private int _size;
	private Node<T> _rear;
	
	public CircularlyLinkedQueue() { //노드의 초기화
		this._size = 0;
		this._rear = null;
	}
	
	public int size() { //사이즈반환
		return this._size;
	}
	public boolean isEmpty() { //비어있는지 검사하기
		return (this._rear == null); 
		//연결체인에서는 맨 뒤노드를 _rear를 통해서 접근. _rear의 노드의 next노드는 맨 앞 노드
	}
	public boolean isFull() {
		return false; //꽉 찰일은 없다.
	}
	public int capacity() {
		return 0; //용량이 꽉 찰일은 없다.
	}
	public T frontElement() { //맨 앞 원소 리턴하기
		
		if(isEmpty()) {
			return null; //만약 비어있으면 null
			
		} else {
			return this._rear.next().element();
			//비어있지 않으면 맨 뒤의 값의 다음값의 원소 리턴
		}
	}
	public boolean enQueue(T anElement) { //큐에 원소 삽입하기
		Node<T> newNode = new Node<T>(anElement, null); 
		//원소는 담겨있지만 다음노드값을 지정안하는 새로운 노드 생성
		if(isEmpty()) { //만약 큐가 비어있다면
			newNode.setNext(newNode); //삽입될 노드의 다음 노드값을 자기 자신을 지정
			//self-loop
			
		} else { //비어있지 않다면
			newNode.setNext(this._rear.next()); //삽입될 노드의 다음 노드값을 맨 뒤 노드의 다음값으로 지정
			//this._rear.setNext(newNode);
			
		}
		this._rear = newNode;//_rear값을 삽입될 노드로 바꿈. 결국 새로운 노드는 맨 뒷 노드가 됨.
		this._size++; //사이즈증가
		return true;
	}
	public T deQueue() { //원소 삭제하기
		Node<T> removedElement = null; //삭제할 원소
		
		if(isEmpty()) { //만약 비어있으면
			return null; // null 리턴
		} else if(this._rear == this._rear.next()) { //원소가 1개만 존재할 경우. self-loop일 경우
			removedElement = this._rear;//_rear밖에 없는 상태이므로rear를 삭제
			this._rear = null; //rear값을 null로
			
		} else { //원소가 여러개 있는 경우
			removedElement = this._rear.next(); //맨 뒤의 다음값이 제일 앞의 값이므로 그것을 삭제
			this._rear.setNext(this._rear.next().next());
			//rear의 다음값을 리어의 다음다음값으로 함. 맨 앞 노드의 그 다음값.
			//지정할 수단이 없어졌으므로 가비지컬렉션에 의해 메모리수거
		}
		return removedElement.element(); //삭제할 원소 리턴
	}
	public void clear() { //큐 초기화하기
		if(!isEmpty()) { //비어있지 않다면
			this._rear = null; //rear값을 초기화. 모든 노드는 rear값으로부터 시작하여 연결되므로 rear를 초기화하면 없어짐
			this._size = 0;
		}
	}
	public T elementAt(int aPosition) { //참조하고 싶은 위치의 원소 참조하기
		Node<T> frontNode = this._rear.next(); //제일 앞노드
		Node<T> searchNode = null; //찾고 싶은 노드
		if(aPosition == 0) { //맨앞 노드값은 0
			
			return frontNode.element();
		} else {
			searchNode = frontNode.next();
			for(int i = 0 ; i <= aPosition; i++) { //해당 위치까지 반복
				searchNode = searchNode.next(); //맨 앞부터 찾고자하는 위치의 노드까지 반복
				}
		}
		return searchNode.element();
		
	}
}
