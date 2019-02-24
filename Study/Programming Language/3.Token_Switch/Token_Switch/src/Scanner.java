import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {
	public enum TokenType {
		ID(3), INT(2);

		private final int finalState;

		private TokenType(int finalState) {
			this.finalState = finalState;
		}
	}

	private String source;
	private StringTokenizer st;

	public Scanner(String source) {
		this.source = source == null ? "" : source;
		// 문자열을 토큰화해주는 클래스, " "를 delimeter로 함
		this.st = new StringTokenizer(this.source, " ");

	}

	private Token nextToken() {
		int state = 0;
		boolean errorState = false;
		// 토큰이 더 있는지 검사
		if (!st.hasMoreTokens())
			return null;
		// 그 다음 토큰을 받음
		String temp = st.nextToken();
		Token result = null;

		for (int i = 0; i < temp.length() && !errorState; i++) {
			switch (state) {
			case 0:
				if (Character.isDigit(temp.charAt(i)))
					state = 2;
				else if (temp.charAt(i) == '-')
					state = 1;
				else if (Character.isLetter(temp.charAt(i)))
					state = 3;
				else
					errorState = true;
				break;
			case 1:
				//상태에 따라state를 변경
				if (Character.isDigit(temp.charAt(i)))
					state = 2;
				break;
				
			case 2:
				//상태에 따라state를 변경
				if (Character.isDigit(temp.charAt(i)))
					state = 2;
				break;
			case 3:
				//상태에 따라state를 변경
				if (Character.isDigit(temp.charAt(i)))
					state = 3;
				else if (Character.isLetter(temp.charAt(i)))
					state = 3;
				break;
				
			default:
				System.out.println("Case error: " + temp);
				return result;
			}

		}
		if (errorState) {
			System.out.println("acceptState error: " + temp);
			return result;
		}

		switch (state) {
		case 2: //int
			result = new Token(TokenType.INT, temp);
			break;
		case 3: //id			
			result = new Token(TokenType.ID, temp);
			break;
		}
		return result;
	}

	public List<Token> tokenize() {
		List<Token> tokens = new ArrayList<Token>();
		Token t = null;
		
		//��ū List�� ��ȯ�ϵ��� �ۼ� 
		while (true) {
			t = nextToken();
			
			if(t == null)
				break;
			
			tokens.add(t);
		}
		

		return tokens;
	}

	public static class Token {
		public final TokenType type;
		public final String lexme;

		Token(TokenType type, String lexme) {
			this.type = type;
			this.lexme = lexme;
		}

		@Override
		public String toString() {
			return String.format("[%s: %s]", type.toString(), lexme);
		}
	}

	public static void main(String[] args) {
		String source = "ban 267   h  cat  -3789  7   y2010  ";
		Scanner s = new Scanner(source);
		System.out.println(source);
		System.out.println(s.tokenize());
	}

}
