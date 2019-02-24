import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {
	public enum TokenType{
		ID(3), INT(2);
		
		private final int finalState;
		
		private TokenType(int finalState) {
			this.finalState = finalState;
		}
	}
	
	private int transM[][];
	private String source;
	private StringTokenizer st;

	
	public Scanner(String source) {
		this.transM = new int[4][128];
		this.source = source == null ? "" : source;
		//문자열을 토큰화 해주는 클래스, " "을 delimiter로 함
		this.st = new StringTokenizer(this.source, " ");
		init_TM();
	}
	
	private void init_TM() {
		for(int i=0;i<4;i++)
			for(int j=0;j<128;j++)
				transM[i][j] = -1;
		
		transM[0]['-'] = 1; // '-'
		for(int i='0'; i<='9'; i++){
			transM[0][i] = 2;
			transM[1][i] = 2;
			transM[2][i] = 2;
			transM[3][i] = 3;
		}
		for(int i='A'; i<= 'Z'; i++){
			transM[0][i] = 3;
			transM[3][i] = 3;
		}
		for(int i='a'; i<= 'z'; i++){
			transM[0][i] = 3;
			transM[3][i] = 3;
		}
		
		//입력 받은 각각의 값을 확인하여 State 지정.
		}
	
	
	private Token nextToken(){		
		int StateOld = 0, StateNew;
		//토큰이 더 있는지 검사
		while(st.hasMoreTokens()) {
			
		//그 다음 토큰을 받음
		String temp = st.nextToken();
		Token result = null;
		
		//문자열의 문자를 하나씩 가져와 상태 판별
		for(int i =0; i< temp.length(); i++) 
		{
			StateNew = transM[StateOld][temp.charAt(i)];
		//입력된 문자의 상태가 reject이면 에러메세지 출력후 return함
			if(StateNew == -1)
			{
				System.out.println(String.format("acceptState error %s\n", temp));
				return null;
			}
			StateOld = StateNew;
		}
		
		//TokenType의 값을 확인하여서 state가 일치하는 경우 새로운 토큰을 생성하여 해당 값을 반환
		
			TokenType[] ts = TokenType.values();
			for( TokenType t : ts) {
				if(StateOld == t.finalState)
					result = new Token(t, temp);
			}
			
		return result;
		}
		return null;
	}
	

	
	public List<Token> tokenize() {
		List<Token> tokens = new ArrayList<Token>();
		Token t = null;
		
		while(true){
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
	
	public static void main(String[] args){
		String source = "ban 267 h  cat  -3789  7  y2010  ";
		System.out.println(source);
		Scanner s = new Scanner(source);
		System.out.println(s.tokenize());
	}
	
}
