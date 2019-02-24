package ast;

public class IdNode extends Node{
	public String value;
	
	
	@Override
	public String toString(){
		return "ID: " + value;
	}


	@Override
	public void copyValue(Node node) {
		// TODO Auto-generated method stub
		this.value = ((IdNode) node).value;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
//		System.out.print("test");
		if(this==obj)return true;
		if(!(obj instanceof IdNode)) return false;
		if(!super.equals(obj)) return false;
		IdNode tmp = (IdNode) obj;
		if(tmp.value.equals(this.value)) return true;
		return false;
	}
	
	
}
