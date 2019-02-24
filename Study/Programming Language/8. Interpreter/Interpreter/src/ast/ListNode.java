package ast;


public class ListNode extends Node{
	public Node value;

	@Override
	public void copyValue(Node node) {
		// TODO Auto-generated method stub
		this.value = ((ListNode) node).value;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj) return true;
		
		if(!(obj instanceof ListNode)) return false;
		ListNode temp = (ListNode) obj;
		
		return value.equals(temp.value);
	}
}
