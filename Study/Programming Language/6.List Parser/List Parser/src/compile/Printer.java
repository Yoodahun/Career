package compile;

import java.io.PrintStream;

import ast.BinarayOpNode;
import ast.BooleanNode;
import ast.FunctionNode;
import ast.IdNode;
import ast.IntNode;
import ast.ListNode;
import ast.Node;

public class Printer {
	PrintStream ps;

	public Printer(PrintStream ps) {
		this.ps = ps;
	}
	
	//recursive call or iteration...
	//void printNode
	public void printNode(Node node) {
		if(node != null){
		if(node instanceof ListNode) {
			ListNode listNode = (ListNode)node;
			ps.print("(");
			printNode(listNode.value);
			ps.print(")");
			
		} else {
			ps.print("[" + node.toString() + "] ");
		}
		printNode(node.getNext());
	} 
	}
}
	//void printList
	

