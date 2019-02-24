package ast;

public class QuoteNode extends Node {
	public Node value;

	@Override
	public void copyValue(Node node) {
		// TODO Auto-generated method stub
		this.value = ((QuoteNode) node).value;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(!(arg0 instanceof QuoteNode)) return false;
		QuoteNode temp = (QuoteNode) arg0;
		return value.equals(temp.value);
	}
	
	
}
