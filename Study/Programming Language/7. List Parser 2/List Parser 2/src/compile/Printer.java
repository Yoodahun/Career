package compile;

import java.io.PrintStream;

import ast.ListNode;
import ast.Node;
import ast.QuoteNode;

public class Printer {
	PrintStream ps;

	public Printer(PrintStream ps) {
		this.ps = ps;
	}
	
	/*
	 * print를 어떻게 해야할지 고민해보고 구현하기.
	 * 아래에 있는 모든 method를 사용하지 않아도 됨.
	 * 자유롭게 구현하세요.
	 */
	public void printNode(Node node) {
		if(node != null){
		if(node instanceof ListNode) {
			if (((ListNode) node).value instanceof QuoteNode)
			{
				printQuote(((ListNode) node).value);
			} else {
				ListNode listNode = (ListNode)node;
				ps.print("( ");
				
				printNode(listNode.value);
				
				ps.print(") ");
			}
			
		} else if (node instanceof QuoteNode) {
			printQuote(node);
			
		} else {
			ps.print("[" + node.toString() + "] ");
		}
		
		printNode(node.getNext());
		
	}
		
	}

	
	private void printQuote(Node node){
		QuoteNode quoteNode = (QuoteNode)node;
		ps.print("'");
		printNode(quoteNode.value);
	}
}
