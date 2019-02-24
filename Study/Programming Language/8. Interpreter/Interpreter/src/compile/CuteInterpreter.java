package compile;

import ast.*;

public class CuteInterpreter {
	private final static BooleanNode TRUE_NODE = new BooleanNode();
	private final static BooleanNode FALSE_NODE = new BooleanNode();
	static {
		TRUE_NODE.value = true;
		FALSE_NODE.value = false;
	}

	private void errorLog(String err) {
		System.out.println(err);
	}

	enum CopyMode {
		NO_NEXT, NEXT
	}

	private Node runFunction(FunctionNode func) {
		Node rhs1 = func.getNext();
		Node rhs2 = (rhs1 != null) ? rhs1.getNext() : null;

		switch (func.value) {
		case CAR:
			if(checkQuote(rhs1)) {
				ListNode listNode = (ListNode)runQuote((ListNode)rhs1);
				return copyNode(listNode.value, CopyMode.NO_NEXT);
			} else {
				return null;
			}
		case CDR:
			if(checkQuote(rhs1)) {
				ListNode listNode = (ListNode)runQuote((ListNode)rhs1);
				ListNode cdrListNode = new ListNode();
				cdrListNode.value = copyNode(listNode.value.getNext(), CopyMode.NEXT);
				 
				return cdrListNode;
			} else {
				return null;
			}
		case CONS:
			ListNode newListNode = new ListNode();
		
			if(checkQuote(rhs1)) {
				ListNode headNode = (ListNode)runQuote((ListNode)rhs1); //쿼트만 벗겨내면 리스트일 수 있음
				
				if(checkQuote(rhs2)) {
					ListNode nextListNode = (ListNode)runQuote((ListNode)rhs2);
					 headNode.value.setNext(nextListNode.value);
					newListNode.value = headNode.value;
				} else {
					headNode.setNext(rhs2);
					newListNode.value = headNode;
				}
			} else if (checkQuote(rhs2)) {
				ListNode nextListNode = (ListNode)runQuote((ListNode)rhs2);
				rhs1.setNext(nextListNode.value);
				newListNode.value = rhs1;
			}
			return newListNode;
			
		case ATOM_Q:
			if(checkQuote(rhs1)) {
				if (rhs2 instanceof ListNode) {
					if(rhs2.getNext() == null) {
						return FALSE_NODE;
					} 
				} else {
					return TRUE_NODE;
				}
			} else {
				if (rhs1 instanceof ListNode) {
					if(rhs2 == null) {
						return FALSE_NODE;
					} 
				} else {
					return TRUE_NODE;
				}
			}
			
		case EQ_Q:
			Node a, b;
			if(checkQuote(rhs1)) {
				a = runQuote((ListNode)rhs1);
			} else {
				a = copyNode(rhs1, CopyMode.NO_NEXT);
			}
			if(checkQuote(rhs2)) {
				b = runQuote((ListNode)rhs2);
			} else {
				b = copyNode(rhs2, CopyMode.NO_NEXT);
			}

			if (a.equals(b)) {
				return TRUE_NODE;
			} else {
				return FALSE_NODE;
			}
			
		case NULL_Q:
			if( rhs1 instanceof ListNode) {
				if(rhs2 == null) {
					return TRUE_NODE;
				} else {
					return FALSE_NODE;
				}
				
			}
		case NOT:
			if(rhs1.equals(FALSE_NODE)) {
				return TRUE_NODE;
			} else {
				return FALSE_NODE;
			}
		case COND:
				return runCond(rhs1);
		default:
			return null;
		}
	}
	private Node runCond(Node param){
		Node condValue;
		Node runNode = runExpr(((ListNode)param).value);
		if(((BooleanNode)runNode).value == TRUE_NODE.value){
			condValue = ((ListNode)param).value.getNext();
		} else {
			condValue = runCond(param.getNext());
		}
		return condValue;
	}
	private Node copyNode(Node node, CopyMode mode) {
		if (node == null)
			return null;

		Node result = null;

		if (node instanceof BinarayOpNode)
			result = new BinarayOpNode();
		else if (node instanceof BooleanNode)
			result = new BooleanNode();
		else if (node instanceof FunctionNode)
			result = new FunctionNode();
		else if (node instanceof IdNode)
			result = new IdNode();
		else if (node instanceof IntNode)
			result = new IntNode();
		else if (node instanceof ListNode)
			result = new ListNode();

		result.copyValue(node);
		if (mode == CopyMode.NEXT && result != null)
			result.setNext(copyNode(node.getNext(), mode));

		return result;
	}
	
	private Node runList(ListNode list) {
		if (list.value instanceof QuoteNode)
			return runQuote(list);
		Node opCode = list.value;

		if (opCode == null)
			return list;
		if (opCode instanceof FunctionNode)
			return runFunction((FunctionNode) opCode);
		if(opCode instanceof BinarayOpNode)
			return runBinary((BinarayOpNode)opCode);
		return list;
	}

	private Node runQuote(ListNode node) {
		QuoteNode qItem = (QuoteNode) node.value;
		Node item = qItem.value;

		return item;
	}

	public Node runExpr(Node rootExpr) {
		if (rootExpr == null)
			return null;

		if (rootExpr instanceof IdNode)
			return rootExpr;
		else if (rootExpr instanceof IntNode)
			return rootExpr;
		else if (rootExpr instanceof BooleanNode)
			return rootExpr;
		else if (rootExpr instanceof ListNode)
			return runList((ListNode) rootExpr);
		else {
			errorLog("run Expr error");
			return null;
		}

	}
	
	private Node runBinary(BinarayOpNode node){
		Node result; //결과
		Node first_param = node.getNext(); //첫번째 파라미터
		Node second_param = first_param.getNext(); //두번째 파라미터
		
		first_param = runExpr(first_param); //인자가 더 있는지?
		second_param = runExpr(second_param);  //두번째도 마찬가지
		
		if(first_param==null || second_param==null) {
			errorLog("runBinary runExpr null");
			return null;
		}
		
		if(!(first_param instanceof IntNode) || !(second_param instanceof IntNode)){
			errorLog("Type Error!");
			return null;
		}
		
		switch(node.value){
		case MINUS:
			IntNode minusNode1 = (IntNode) first_param;
			IntNode minusNode2 = (IntNode) second_param;
			IntNode resultMinusNode = new IntNode();
			resultMinusNode.value = minusNode1.value - minusNode2.value;
			result = resultMinusNode;
			return result;
		case PLUS:
			IntNode plusNode1 = (IntNode) first_param;
			IntNode plusNode2 = (IntNode) second_param;
			IntNode resultPlusNode = new IntNode();
			resultPlusNode.value = plusNode1.value + plusNode2.value;
			result = resultPlusNode;
			return result;
		case DIV:
			IntNode	divNode1 = (IntNode) first_param;
			IntNode divNode2 = (IntNode) second_param;
			IntNode resultDivNode = new IntNode();
			resultDivNode.value = divNode1.value / divNode2.value;
			result = resultDivNode;
			return result;
		case TIMES:
			IntNode timesNode1 = (IntNode) first_param;
			IntNode timesNode2 = (IntNode) second_param;
			IntNode resultTimesNode = new IntNode();
			resultTimesNode.value = timesNode1.value * timesNode2.value;
			result = resultTimesNode;
			return result;
		case GT: //>
			IntNode gtNode1 = (IntNode) first_param;
			IntNode gtNode2 = (IntNode) second_param;
			
			if (gtNode1.value > gtNode2.value){
				result = TRUE_NODE;
			} else {
				result = FALSE_NODE;
			}
			return result;
		case LT: //<
			IntNode ltNode1 = (IntNode) first_param;
			IntNode ltNode2 = (IntNode) second_param;
			
			if (ltNode1.value < ltNode2.value){
				result = TRUE_NODE;
			} else {
				result = FALSE_NODE;
			}
			return result;
		default:
			return null;
		}
	}
	
	private boolean checkQuote(Node node) {
		if (!(node instanceof ListNode))
			return false;
		ListNode tmp = (ListNode) node;
		if (!(tmp.value instanceof QuoteNode))
			return false;
		return true;
	}
}
