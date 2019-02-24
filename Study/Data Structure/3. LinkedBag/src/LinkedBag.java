
public class LinkedBag {
	
	private int _size; //여기서 사이즈는 총 노드의 갯수
	private Node _head;
	
	public LinkedBag() { 
		this._head = null; //초기화실행
		this._size = 0;
		
	}
	public int size() {
		return this._size; //노드갯수 리턴.
	}
	public boolean isEmpty() {
		return (this._size == 0); //비었는지 안비었는지 체크
	}
	public boolean doesContain(Coin anCoin) {
		boolean found = false;
		
		Node searchNode = this._head; //제일 앞부터 검색해나가기
		while(searchNode != null && !found) { //검색할 노드가 없고, 못찾을때까지 검색
			if(searchNode.coin().equals(anCoin)){ //노드의 코인값이 입력된 코인값과 같다면
				found = true; //빙고
			} 
			searchNode = searchNode.next(); //아니라면 다음 노드로 이동
		}
		return found;
	}
	public int frequencyOf(Coin anCoin) {
		Node searchNode = this._head; //처음 노드부터 검색
		int count = 0; //카운터 증가
		while(searchNode != null) { //찾을 노드가 없을때까지 검색
		if (searchNode.coin().equals(anCoin)){ //현재 찾고있는 노드의 코인값과 입력된 코인값이 같다면
			count++; //갯수 증가
		}
		searchNode = searchNode.next(); //다음 노드로 이동
		}
		return count;
		
	}
	public int maxElementValue() { //최대값 찾기
		Node searchNode = this._head; //현재 노드는 맨 앞부터
		int maxValue = 0; //최대값을 저장할 변수
		
		while(searchNode != null) { //검색할 노드가 없을때까지. 노드가 존재하는한 계속검색
		if (maxValue < searchNode.coin().value()) { //최대값과 현재 찾고있는 노드의 코인값과 비교하여 노드값이 크면
			maxValue = searchNode.coin().value(); //맥스값을 현재 코인값으로 교체
		}
		searchNode = searchNode.next(); //다음 노드로 이동
		}
		return maxValue;
	}
	public int sumElementValues() {
		Node searchNode = this._head; //현재 노드 맨 앞부터
		int sumValue = 0; //값을 저장할 노드
		while(searchNode != null) { //노드가 존재하는한 계속
			
		sumValue += searchNode.coin().value(); //찾고있는 코인의 값을 계속 더해서 저장
	
		searchNode = searchNode.next(); //다음값으로 이동
		}
		return sumValue;
	}
	public boolean add(Coin anCoin) { //코인 저장하기
		
		Node newNode = new Node(anCoin);
		//주어진 코인값의 새로운 노드를 생성
		newNode.setNext(this._head); // 현재 노드의 다음값을 현재 헤드로 지정. 지금 헤드노드는 뒤로 밀려남.
		this._head = newNode; //현재헤드를 지금 헤드로 새로 지정. 노드를 추가할때는 맨 앞에서 추가하므로 원래 있던 헤드는 뒤로 밀려나가는 형식
		this._size++;
		return true;
	}
	public Coin remove(Coin anCoin) {
		if(this.isEmpty()){
			return null;
		} else {
			Node previousNode = null; //이전노드
			Node currentNode = this._head; //현재노드는 머리부터
			boolean found = false;
			
			/*입력된 코인값을 가진 노드를 찾기*/
			while(currentNode != null && !found) { //지우고싶은 코인값의 노드 찾기 현재노드가 존재하고있으면서 못찾을때까지 계속
				if(currentNode.coin().equals(anCoin)) { //입력한 값의 코인값과 현재 코인값이 같다면
					found = true; //빙고
				} else {
					previousNode = currentNode; //아니라면 현재값을 이전값으로 하고
					currentNode = currentNode.next(); //현재값을 현재의 다음 값으로하여 다음 노드로 진행
				}
			}
			
			/*해당 노드를 찾아 반복문을 탈출했다면 노드를 삭제하는 방법*/
			if(!found) {
				return null; //패스
			} else {
				if(currentNode == this._head) { //찾았을 때, 현재 노드가 헤드라면
					this._head = this._head.next(); //헤드를 헤드노드의 다음 노드로 설정
				} else {
					previousNode.setNext(currentNode.next()); //아니라면 이전노드의 다음 노드를 현재 노드의 다음 노드로 설정
					//이전노드와 다음노드의 사이에 있는 현재 노드를 삭제
				}
				this._size--; //여기서 없어진 노드는 garbage collection으로 처리.
				return anCoin; 
			}
		}
		
	}
	public Coin removeAny () {
		if (this.isEmpty()) {
			return null ;
		} else {
			Coin removedCoin = this._head.coin(); //삭제될 코인을 저장. 삭제되는 코인값이 무엇인지 확인해야하므로
			this._head = this._head.next();
			this._size--;
			return removedCoin; //아무거나 없애는 것이면 맨 앞에 있는 노드를 없애는 것.
		}
	}
	public void clear() {
		this._size = 0;
		this._head = null; //초기화. 헤드도 없앰.
		
	}
}
