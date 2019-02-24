package compile;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;

import ast.BinarayOpNode;
import ast.BinarayOpNode.BinType;
import ast.BooleanNode;
import ast.FunctionNode;
import ast.FunctionNode.FunctionType;
import ast.IdNode;
import ast.IntNode;
import ast.ListNode;
import ast.Node;
import compile.CuteScanner.Token;
import compile.CuteScanner.TokenType;

public class BasicParser {
	private ListIterator<Token> iter;

	public BasicParser(List<Token> tokenList) {
		
		iter = tokenList.listIterator();
	}

	private void errorLog(String err) {
		System.out.println(err);
	}

	private Token getNextToken() {
		if (!iter.hasNext())
			return null;
		return iter.next();
	}

	public Node parseExpr() {
		Token t = getNextToken();
		BinarayOpNode binaryOpNode;
		if (t == null) {
			System.out.println("No more token");
			return null;
		}
		switch (t.type) {
		case ID:
			IdNode idNode = new IdNode();
			idNode.value = t.lexeme;
			return idNode;
		case INT:
			IntNode intNode = new IntNode();
			if (t.lexeme == null)
				System.out.println("???");
			intNode.value = new Integer(t.lexeme);
			return intNode;
		// BinaryOpNode�� ���Ͽ� �ۼ�
		// +, -, /, *����� �ش��
		case MINUS:
			binaryOpNode = new BinarayOpNode();
			binaryOpNode.value = BinType.MINUS;
			return binaryOpNode;
			
		case PLUS :
			binaryOpNode = new BinarayOpNode();
			binaryOpNode.value = BinType.PLUS;
			return binaryOpNode;
			
		case TIMES:
			binaryOpNode = new BinarayOpNode();
			binaryOpNode.value = BinType.TIMES;
			return binaryOpNode;
		case DIV: 
			binaryOpNode = new BinarayOpNode();
			binaryOpNode.value = BinType.DIV;
			return binaryOpNode;
		case LT :
			binaryOpNode = new BinarayOpNode();
			binaryOpNode.value = BinType.LT;
			return binaryOpNode;
		case GT :
			binaryOpNode = new BinarayOpNode();
			binaryOpNode.value = BinType.GT;
			return binaryOpNode;
		case EQ :
			binaryOpNode = new BinarayOpNode();
			binaryOpNode.value = BinType.EQ;
			return binaryOpNode;
		case ATOM_Q:
			FunctionNode atom = new FunctionNode();
			atom.value = FunctionType.ATOM_Q;
			return atom;
		// FunctionNode�� ���Ͽ� �ۼ�
		// Ű���尡 FunctionNode�� �ش�
		case DEFINE:
			FunctionNode define = new FunctionNode();
			define.value = FunctionType.DEFINE;
			return define;
		case LAMBDA:
			FunctionNode lambda = new FunctionNode();
			lambda.value = FunctionType.LAMBDA;
			return lambda;
		case COND:
			FunctionNode cond = new FunctionNode();
			cond.value = FunctionType.COND;
			return cond;
		case NOT:
			FunctionNode not = new FunctionNode();
			not.value = FunctionType.NOT;
			return not;
		case CDR:
			FunctionNode cdr = new FunctionNode();
			cdr.value = FunctionType.CDR;
			return cdr;
		case CAR:
			FunctionNode car = new FunctionNode();
			car.value = FunctionType.CAR;
			return car;
		case CONS:
			FunctionNode cons = new FunctionNode();
			cons.value = FunctionType.CONS;
			return cons;
		case EQ_Q:
			FunctionNode eq_q = new FunctionNode();
			eq_q.value = FunctionType.EQ_Q;
			return eq_q;
		case NULL_Q:
			FunctionNode null_q = new FunctionNode();
			null_q.value = FunctionType.NULL_Q;
			return null_q;
		
		// BooleanNode�� ���Ͽ� �ۼ�
		case FALSE:
			BooleanNode falseNode = new BooleanNode();
			falseNode.value = false;
			return falseNode;
		case TRUE:
			BooleanNode trueNode = new BooleanNode();
			trueNode.value = true;
			return trueNode;
			// case L_PAREN�� ���� case R_PAREN�� ��쿡 ���ؼ� �ۼ�
			// L_PAREN�� ��� parseExprList()�� ȣ���Ͽ� ó��
		case L_PAREN:
			ListNode listNode = new ListNode();
			listNode.value = parseExprList();
			return listNode;
		case R_PAREN:
			return null;
		}
		// head�� next�� ����� head�� ��ȯ�ϵ��� �ۼ�
		System.out.println("Parsing Error!");
		return null;
	}

	// List�� value�� �����ϴ� �޼ҵ�
	private Node parseExprList() {
				 
		Node head = parseExpr();
		
		if (head == null) {
			return null;
		}
		head.setNext(parseExprList());
		
		
		return head;
	}

	public static void main(String[] args) {
		CuteScanner cs = new CuteScanner("( + ( - 3 String 2 ) ( ) -378 ) ");
		BasicParser bp = new BasicParser(cs.tokenize());
		Printer pt = new Printer(System.out);
		pt.printNode(bp.parseExpr());
	}
}
