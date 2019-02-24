import compile.TreeFactory;

import ast.IntNode;
import ast.ListNode;
import ast.Node;


public class DoubleRecursion {
	public static int max(Node node) {
		//최대값을 리턴하도록 작성, recursion으로 짜시오.
		//value와 next 값 중 큰 값을 리턴
		int maxValue=Integer.MIN_VALUE;
	
		
		if(node instanceof ListNode) {
			ListNode listNode = (ListNode)node;
			if(listNode.value != null)
				maxValue = Math.max(maxValue, max(listNode.value));
		}
		if(node instanceof IntNode) { //type check;
			IntNode intNode = (IntNode)node;
			
			maxValue = Math.max(maxValue, intNode.value);
				
			}
		if(node.getNext() != null) {
			maxValue = Math.max(maxValue, max(node.getNext()));
			
		} 
		
		return maxValue;
		
	}

	public static int sum(Node node) {
		//노드 value의 총합을 반환
		//value와 next의 총 합을 리턴하면됨
		//sum_result 값 초기화
		int sum_result = 0;
		
		// node의 타입 확인
		if (node instanceof ListNode) {
			// listnode 일 경우
			ListNode listNode = (ListNode) node;
			// listnode안의 값이 있을 경우
			if(listNode.value != null) {
				sum_result += sum(listNode.value);
				// sum_result 값 갱신
			}
		
		} 
		if(node instanceof IntNode) {
			// node 타입 확인
			// intnode일 경우
			// sum_result 은 해당 노드의 value.
			IntNode intNode = (IntNode)node;
			sum_result = intNode.value;
		}
			// 다음 노드의 값이 있을 경우
		if(node.getNext() != null) {
			sum_result += sum(node.getNext());
			// recursion으로 계속 실행
		}
			return sum_result;
			// sum_result 값 return

		}
		
	

	public static void main(String[] args) {
		Node node = TreeFactory.createtTree("( ( 3 ( ( 10 ) ) 6 ) 4 1 ( ) -2 ( ) )");
		System.out.println(DoubleRecursion.max(node));
		System.out.println(DoubleRecursion.sum(node));
	}
}
