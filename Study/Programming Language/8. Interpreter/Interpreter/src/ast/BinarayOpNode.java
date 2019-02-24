package ast;

public class BinarayOpNode extends Node{
	public enum BinType { MINUS, PLUS, TIMES, DIV, LT, GT, EQ }
	public BinType value;
	
	
	@Override
	public String toString(){
		return value.name();
	}


	@Override
	public void copyValue(Node node) {
		// TODO Auto-generated method stub
		this.value = ((BinarayOpNode) node).value;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj) return true;
		if(!(obj instanceof BinarayOpNode)) return false;
		if(!super.equals(obj)) return false;
		BinarayOpNode tmp = (BinarayOpNode) obj;
		if(tmp.value==this.value) return true;
		return false;
	}
	
	
}
