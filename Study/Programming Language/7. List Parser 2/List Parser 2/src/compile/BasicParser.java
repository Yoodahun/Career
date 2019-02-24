package compile;

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
import ast.QuoteNode;
import compile.CuteScanner.Token;

public class BasicParser {
	private ListIterator<Token> iter;

	public BasicParser(List<Token> tokenList) {
		
		iter = tokenList.listIterator();
	}

	@SuppressWarnings("unused")
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
			trueNode.value = false;
			return trueNode;
		case L_PAREN:
			ListNode listNode = new ListNode();
			listNode.value = parseExprList();
			return listNode;
		case R_PAREN:
			return null;
		case APOSTROPHE:
			ListNode aplistNode = new ListNode();
			QuoteNode apQuoteNode = new QuoteNode();
			aplistNode.value = apQuoteNode;
			apQuoteNode.value = parseExpr();
			return aplistNode;
			/*
			 * APOSTROPHE일 때, 
			 * 어떻게 작동하는지 설명 듣고 구현하기
			 */
		case QUOTE:
			QuoteNode quoteNode = new QuoteNode();
			quoteNode.value = parseExpr();
			return quoteNode;
			/*
			 * QUOTE일 때,
			 * 어떻게 작동하는지 설명 듣고 구현하기
			 */
		default:
			System.out.println("Parsing Error!");
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
	
	public static void main(String[] args) {
//		CuteScanner cs = new CuteScanner("(+ (lenght '(2 3 16)) -378)");
		CuteScanner cs = new CuteScanner(" (quote ( + 2 3 ))");
//		CuteScanner cs = new CuteScanner(" '( + 2 3 )");
		BasicParser bp = new BasicParser(cs.tokenize());
		Printer pt = new Printer(System.out);
		pt.printNode(bp.parseExpr());
	}
}
