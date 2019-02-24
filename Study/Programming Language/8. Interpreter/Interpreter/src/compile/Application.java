package compile;

public class Application {
	public static void main(String[] args) {
//		String source = new String("( cond ( ( > 1 2 ) 0 ) ( #T 1 ) )");
//		String source = new String("( car '(( 2 3 ) (4 5) 6))");
//		String source = new String("( cdr '((2 3) (4 5) 6))");
//		String source = new String("( not #F )");
		String source = new String("( cons 1 '( 2 3 4 ) )");
//		String source = new String("( null? ())");
//		String source = new String("( atom? 'a ) ");
		
//		 String source = new String("( eq? 'a 'a )");
//		String source = new String("( cond ((not #T) 1) (( > 1 2 ) 2) ((atom? 'a) 5 ))");
		CuteScanner scanner = new CuteScanner(source);
		CuteParser parser = new CuteParser(scanner.tokenize());
		CuteInterpreter interpreter = new CuteInterpreter();
		CutePrinter pt = new CutePrinter(System.out);
		pt.printNode(interpreter.runExpr(parser.parseExpr()));
	}
}
