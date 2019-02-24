import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class CuteScanner {
	public enum TokenType {
		//1) ��ȣ�ȿ� TokenType�� �´� state number �Է�
		INT(2), ID(3), MINUS(1), PLUS(4), L_PAREN(5), R_PAREN(6), TRUE(8), FALSE(9)
		, TIMES(10), DIV(11), LT(12), GT(13), EQ(14), APOSTROPHE(15), QUESTION(16),

		// not present in automata
		DEFINE(-1), LAMBDA(-1), COND(-1), QUOTE(-1), NOT(-1), CAR(-1), CDR(-1), CONS(-1),
		ATOM_Q(-1), NULL_Q(-1), EQ_Q(-1);

		private final int finalState;

		TokenType(int finalState) {
			this.finalState = finalState;
		}

		public static TokenType keyWordCheck(String str) {
			return KeyWord.m.get(str); // if not keyword return null
		}

		private enum KeyWord {
			//2) 나머지 키워드 작성
			DEFINE("define", TokenType.DEFINE), LAMBDA("lambda",TokenType.LAMBDA), 
			COND("cond", TokenType.COND),
			QUOTE("quote", TokenType.QUOTE),
			NOT("not", TokenType.NOT),
			CAR("car", TokenType.CAR),
			CDR("cdr", TokenType.CDR),
			CONS("cons", TokenType.CONS),
			ATOM_Q("atom?", TokenType.ATOM_Q),
			NULL_Q("null?", TokenType.NULL_Q),
			EQ_Q("eq?", TokenType.EQ_Q);

			final String word;
			final TokenType type;

			KeyWord(String word, TokenType type) {
				this.word = word;
				this.type = type;
			}

			private static final Map<String, TokenType> m = new HashMap<String, TokenType>();
			static {
				for (KeyWord k : KeyWord.values()) {
					m.put(k.word, k.type);
				}
			}
		}
	}

	private int transM[][];
	private boolean accept[];
	private String source;
	private StringTokenizer st;

	public CuteScanner(String source) {
		//3) state 갯수만큼 배열초기화
		this.transM = new int[16][128];
		//state에 따라 참거짓 
		this.accept = new boolean[transM.length];
		for (TokenType t : TokenType.values()) {
			if (t.finalState > 0)
				accept[t.finalState-1] = true;
		}
		this.source = source == null ? "" : source;
		st = new StringTokenizer(source, " ");
		init_TM();
	}

	private void init_TM() {
		for (int i = 0; i < transM.length; i++)
			for (int j = 0; j < transM[i].length; j++)
				transM[i][j] = -1;

		//4) DFA를 만족하는 Transition Matrix작성하시오.
		transM[0]['-'] = 1; // '-'
		transM[0]['('] = 5; 
		transM[0][')'] = 6;
		transM[0][')'] = 6;
		transM[0]['#'] = 7;
		transM[7]['T'] = 8;
		transM[7]['F'] = 9;
		transM[3]['?'] = 16;
		transM[0]['+'] = 4;
		transM[0]['<'] = 12;
		transM[0]['='] = 14;
		transM[0]['>'] = 13;
		transM[0]['*'] = 10;
		transM[0]['/'] = 11;
		transM[0]['\''] = 15;
		
		for(int i = 'a'; i <= 'z'; i++) {
			this.transM[0][i] = 3;
			this.transM[3][i] = 3;
 		}
		for(int i = 'A'; i <= 'Z'; i++) {
			this.transM[0][i] = 3;
			this.transM[3][i] = 3;
		}
		for(int i = '0'; i <= '9'; i++) {
			this.transM[0][i] = 2;
			this.transM[1][i] = 2;
			this.transM[2][i] = 2;
			this.transM[3][i] = 3;
		}
	}

	private Token nextToken() {
		int StateOld = 0, StateNew;
		if (!st.hasMoreTokens())
			return null;
		// 그 다음 토큰을 받음
		String temp = st.nextToken();
		Token result = null;

		for (int i = 0; i < temp.length(); i++) {
			StateNew = transM[StateOld][temp.charAt(i)]; // 입력문자로 새로운 상태판별

			if (StateNew == -1) {
				//5) 입력된 문자의 상태가 리젝트이므로 에러 메세지 출력 후 리턴함
				System.out.println(String.format("acceptState error %s\n", temp));
				return null;
			}

			StateOld = StateNew;
		}

		for (TokenType t : TokenType.values()) {
			if (t.finalState == StateOld) {
				TokenType keyWord = TokenType.keyWordCheck(temp);
				if (keyWord != null) {
					result = new Token(keyWord, temp);
				} else {
					result = new Token(t, temp);
				}
				break;
			}
		}

		return result;
	}

	public List<Token> tokenize() {
		// Token 리스트 반환
		List<Token> tokens = new ArrayList<Token>();
		Token t = null;

		while (true) {
			t = nextToken();

			if (t == null)
				break;

			tokens.add(t);
		}

		return tokens;
	}

	public static class Token {
		public final TokenType type;
		public final String lexeme;

		Token(TokenType type, String lexeme) {
			this.type = type;
			this.lexeme = lexeme;
		}

		@Override
		public String toString() {
			return String.format("\n[%s: %s]", type.toString(), lexeme);
		}
	}

	public static void main(String[] argc) throws Exception {
		String source = "( define length ( lambda ( x ) ( cond ( ( null? X ) 0 ) ( #T ( + 1 ( length ( cdr x ) ) ) ) ) ) )";

		CuteScanner s = new CuteScanner(source);
		List<Token> result = s.tokenize();

		System.out.println(result);
	}
}
