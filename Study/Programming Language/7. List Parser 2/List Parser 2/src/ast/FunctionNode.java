package ast;

public class FunctionNode extends Node{
	public enum FunctionType { DEFINE, LAMBDA, COND, NOT, CDR, CAR, CONS, EQ_Q, NULL_Q, ATOM_Q }
	public FunctionType value;
	
	@Override
	public String toString(){
		return value.name();
	}
}
