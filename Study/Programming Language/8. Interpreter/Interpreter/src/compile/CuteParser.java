package compile;

import java.util.List;
import java.util.ListIterator;

import ast.*;
import ast.BinarayOpNode.BinType;
import ast.FunctionNode.FunctionType;
import compile.CuteScanner.Token;

public class CuteParser {
	private ListIterator<Token> iter;

	public CuteParser(List<Token> tokenList) {
		
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
		case DIV:
			BinarayOpNode div = new BinarayOpNode();
			div.value = BinType.DIV;
			return div;
		case EQ:
			BinarayOpNode eq = new BinarayOpNode();
			eq.value = BinType.EQ;
			return eq;
		case MINUS:
			BinarayOpNode minus = new BinarayOpNode();
			minus.value = BinType.MINUS;
			return minus;
		case GT:
			BinarayOpNode gt = new BinarayOpNode();
			gt.value = BinType.GT;
			return gt;
		case PLUS:
			BinarayOpNode plus = new BinarayOpNode();
			plus.value = BinType.PLUS;
			return plus;
		case TIMES:
			BinarayOpNode times = new BinarayOpNode();
			times.value = BinType.TIMES;
			return times;
		case LT:
			BinarayOpNode lt = new BinarayOpNode();
			lt.value = BinType.LT;
			return lt;
		case ATOM_Q:
			FunctionNode atom = new FunctionNode();
			atom.value = FunctionType.ATOM_Q;
			return atom;
		case CAR:
			FunctionNode car = new FunctionNode();
			car.value = FunctionType.CAR;
			return car;
		case CDR:
			FunctionNode cdr = new FunctionNode();
			cdr.value = FunctionType.CDR;
			return cdr;
		case COND:
			FunctionNode cond = new FunctionNode();
			cond.value = FunctionType.COND;
			return cond;
		case CONS:
			FunctionNode cons = new FunctionNode();
			cons.value = FunctionType.CONS;
			return cons;
		case DEFINE:
			FunctionNode define = new FunctionNode();
			define.value = FunctionType.DEFINE;
			return define;
		case EQ_Q:
			FunctionNode eqq = new FunctionNode();
			eqq.value = FunctionType.EQ_Q;
			return eqq;
		case LAMBDA:
			FunctionNode lambda = new FunctionNode();
			lambda.value = FunctionType.LAMBDA;
			return lambda;
		case NOT:
			FunctionNode not = new FunctionNode();
			not.value = FunctionType.NOT;
			return not;
		case NULL_Q:
			FunctionNode nullq = new FunctionNode();
			nullq.value = FunctionType.NULL_Q;
			return nullq;
		case FALSE:
			BooleanNode falseNode = new BooleanNode();
			falseNode.value = false;
			return falseNode;
		case TRUE:
			BooleanNode trueNode = new BooleanNode();
			trueNode.value = true;
			return trueNode;
		case L_PAREN:
			ListNode listNode = new ListNode();
			listNode.value = parseExprList();
			return listNode;
		case R_PAREN:
			return null;
		case APOSTROPHE:
			ListNode apListNode = new ListNode();
			QuoteNode apQuoteNode = new QuoteNode();
			apQuoteNode.value = parseExpr();
			apListNode.value = apQuoteNode;
			return apListNode;
		case QUOTE:
			QuoteNode quoteNode = new QuoteNode();
			quoteNode.value = parseExpr();
			return quoteNode;
		default:
			errorLog("Parsing Error!");
			return null;
		}
	}

	private Node parseExprList() {
		Node head = parseExpr();

		if (head == null)
			return null;
		head.setNext(parseExprList());
		return head;
	}
}
