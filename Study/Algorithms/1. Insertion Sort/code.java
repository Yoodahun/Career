import java.io.*;

public class code {
	public static void main(String[] args) throws IOException {					
		// 파일 읽기
		String buffer = "";
//		BufferedReader br = new BufferedReader(new FileReader(new File("test_10.txt")));
////		BufferedReader br = new BufferedReader(new FileReader(new File("test_100.txt")));
////		BufferedReader br = new BufferedReader(new FileReader(new File("test_1000.txt")));
//		BufferedReader br = new BufferedReader(new FileReader(new File("test_10000.txt")));
		 
//		int value;
//
//		
//		// 한줄씩 읽는 메소드가 readLine() 입니다.
//		while((buffer = br.readLine()) != null){
//			value = Integer.parseInt(buffer); //버퍼의 내용을 정수값으로 바꾸어서 value에 입력
//			System.out.printf("%d\n", value); //출력
//			
//		}
//		br.close(); //리딩 종료
		
		
		
		
		
		/*
		 * Linked list만들고 Sorting하세요
		 */
		
		BufferedReader br2 = new BufferedReader(new FileReader(new File("test_10.txt")));
//		BufferedReader br2 = new BufferedReader(new FileReader(new File("test_100.txt")));
//		BufferedReader br2 = new BufferedReader(new FileReader(new File("test_1000.txt")));
//		BufferedReader br2 = new BufferedReader(new FileReader(new File("test_10000.txt")));
		
		Node headNode = null; //헤드노드
		
		/* 링크드 리스트 생성*/
		
		while((buffer = br2.readLine()) != null) {
			Node newNode = new Node(Integer.parseInt(buffer));
			if(headNode == null) { //헤드노드가 없을 때 첫 헤드노드 설정
				
				newNode.setNext(headNode);
				headNode = newNode;
			} else { //헤드가 있을 때 추가되는 데이터의 생성
				headNode.setBack(newNode);
				newNode.setNext(headNode);
				headNode = newNode;
			}
		}
		
		br2.close(); //읽기 종료


		
		/* 헤드노드의 앞부분을 처리해주기 위한 더미노드 생성 */
		Node dummyNode = new Node();
		headNode.setBack(dummyNode);
		dummyNode.setNext(headNode);
		headNode = dummyNode;
		
		
		
		
		/*Insertion sort*/
		
		Node nextNode = headNode.next().next(); //while문 제어를 위한 노드 생성
		long startTime = System.currentTimeMillis(); //정렬 시작시간
		while(nextNode != null) {
		
			
			int keyValue = nextNode.element();
			Node previousNode = nextNode.back();
			while( (previousNode != null) && (previousNode.element() > keyValue)) {
				if(previousNode.back() != null) {
					previousNode.next().setElement(previousNode.element());
					previousNode = previousNode.back();	
				}
			}
			previousNode.next().setElement(keyValue);
			nextNode = nextNode.next();
		}
		long endTime = System.currentTimeMillis(); //정렬 종료 시간
		
		long time = (endTime - startTime);
		System.out.println(time); //정렬시간 출력
		
		/* Insertion sort END */
	
		
		// 파일 쓰기
		BufferedWriter out = new BufferedWriter(new FileWriter("out_10.txt"));
//		BufferedWriter out = new BufferedWriter(new FileWriter("out_100.txt"));
//		BufferedWriter out = new BufferedWriter(new FileWriter("out_1000.txt"));
//		BufferedWriter out = new BufferedWriter(new FileWriter("out_10000.txt"));
		
		/*각 노드를 순회하며 노드가 가진 원소를 하나씩 출력*/
		Node writeNode = headNode.next();
		while(writeNode != null) {
			out.write(Integer.toString(writeNode.element()));
			out.newLine();
			writeNode = writeNode.next();
		}
		out.close();
		/*출력 및 쓰기 */
		
		
		// 파일에 쓰고, 한줄 내리기
//		out.write("형식에 맞게 출력하세요\n");
//		out.write(Integer.toString(v));out.newLine();
//		out.write(Integer.toString(v));out.newLine();
//		out.write(Integer.toString(v));out.newLine();
//		out.close();

	}
}

class Node {
	private int element;
	private Node next;
	private Node back;
	
	public Node() {
		this.next = null;
	}
	public Node(int element) {
		this.element = element;
		this.next = null;
	}
	public Node(int element, Node givenNode) {
		this.element = element;
		this.next = givenNode;
	}
	public int element() {
		return this.element;
	}
	public void setElement(int newElement) {
		this.element = newElement;
	}
	public Node next() {
		return this.next;
		
	}
	public void setNext(Node newNext) {
		this.next = newNext;
	}
	public Node back() {
		return this.back;
	}
	public void setBack(Node newback) {
		this.back = newback;
	}

}
