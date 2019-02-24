package ast;

public class BinarayOpNode extends Node{
	public enum BinType { MINUS, PLUS, TIMES, DIV, LT, GT, EQ }
	public BinType value;
	
	
	@Override
	public String toString(){
		return value.name();
	}
}
