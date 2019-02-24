
public class Calculate {
	private ArrayList<Character> _oStack;
	private ArrayList<Double> _vStack;
	
	private char[] _infix;
	private char[] _postfix;
	
	public Calculate() {
		this._oStack = null;
		this._vStack = null;
		this._infix = null;
		this._postfix = null;
		
		
		
	}
	public void setInfix(String newInfix){
		this._infix = newInfix.toCharArray();
		
	}
	public String infix() {
		return String.valueOf(this._infix);
		
	}
	public String postfix(){
		return String.valueOf(this._postfix);
		
	}
	public boolean infixToPostfix(){ //infix를 postfix로 변경. 연산자의 우선순위에 유의
		int i = 0; //string pointer for infix
		int p = 0; //string pointer for postfix
		char currentToken, poppedToken, topToken;
		
		this._oStack = new ArrayList<Character>(this._infix.length);//연산자를 저장할 스택
		this._postfix = new char[this._infix.length]; //postfix시켜 저장할 배열
		
		while (i < this._infix.length){ //배열의 사이즈-1 까지
			currentToken = this._infix[i++]; //입력받은 문자열로부터 하나씩 빼와서 currentToken저장
			
			
			if (this.isDigit(currentToken)) { //숫자라면  
				this._postfix[p++] = currentToken;//집어넣기
			} else { //연산자 일때
				
				if (currentToken == ')'){ //닫는괄호 연산자 일때
					if(!this._oStack.isEmpty()) { //비어있지 않다면,
						poppedToken = (char)this._oStack.pop(); //형변환하여 뽑은토큰에 일단 넣
					} else {
						return false; //아님말고
					}
					while (poppedToken != '(') { //뽑아낸 토큰이 여는괄호가 아닐동안
						this._postfix[p++] = poppedToken; //뽑아낸 연산자를 postfix에 저장
						if(!this._oStack.isEmpty()) { //만일 연산자가 비어있지 않다면
							poppedToken = (char)this._oStack.pop(); //형변환하여 뽑은토큰에 넣기
						} else {
							return false;
						}
					} //뽑아낸 토큰이 여는괄호가 아닐때까지 반복하며 postfix에 저장
					this.showOStackAll(); //연산자스택내부의 모습을 i가 증가하면서 한 번 씩 출력
				
				} else { //일반 연산자일때
					
					int inComingP = this.inComingPrecedence(currentToken);//현재 토큰의 들어갈 우선순위 계산
					if(!this._oStack.isEmpty()) { //만약 연산자스택이 비어있지 않다면
						topToken = (char)this._oStack.peek(); //제일 위의 토큰을 출력
						
						while(this.inStackPrecedence(topToken) >= inComingP) { //안에들어있는 토큰의 순위가 들어갈 순위보다 크거나 같다면
																				//들어갈 순위가 크면 그냥 연산자스택에 삽입
							poppedToken = (char)this._oStack.pop(); //스택에서 하나를 뽑아낸다.
							this._postfix[p++] = poppedToken;//뽑아낸 연산자를 저장한다
							if(!this._oStack.isEmpty()){ //만약 비어있지 않다면
								topToken = (char)this._oStack.peek(); //제일위 토큰을 출력
							} else {
								break;
							}
						} //연산자 스택이 빌때까지 반복
					}
					this._oStack.push(currentToken); //스택이 비어있으면 연산자 스택에 토큰 집어넣기
					this.showOStackAll(); //내부출력
					
				} 
			}
			//베열의 사이즈-1까지 반복
		}
		while(!this._oStack.isEmpty()){
			this._postfix[p++] = this._oStack.pop();
		}
		return true;
	}
	public double evalPostfix() {
		int p; //string pointer for postfix
		char curToken;
		this._vStack = new ArrayList<Double>(this._infix.length);//입력받은 문자열길이만큼 연산할 숫자저장스택 생성
		
		p = 0;
		
		while (p < this._postfix.length) {
			curToken = _postfix[p++]; //postfix가 들어있는 문자열값 참조
			
			if (isDigit(curToken)) {
				this._vStack.push(Double.parseDouble(String.valueOf(curToken))); 
				//postfix는 문자값. 이것을 string으로 바꾸고 다시 Double형으로 바꾸어 저장.
			} else if(this._vStack.size() == 1){
				break; //스택에 값이 1개만 남아있으면 while문 탈출
			} else {
				
				double operand2 = this._vStack.pop(); //마지막에 들어간 것이 스택의 맨 위의 값
				double operand1 = this._vStack.pop();
				
				
				if(curToken == '+') {
					this._vStack.push((operand1 + operand2));
				} else if (curToken == '-') {
					this._vStack.push((operand1 - operand2));
				}else if(curToken =='*') {
					this._vStack.push((operand1 * operand2));
				}else if(curToken =='/'){
					this._vStack.push((operand1 / operand2));
				}else if(curToken =='%'){
					this._vStack.push((operand1 % operand2));
				}else if(curToken == '^'){
					double temp = 0;
					for(int i = 1; i <= operand2; i++) {
						temp = Math.pow(operand1, i); 
					}
					this._vStack.push(temp);
				} else if(curToken =='$'){
					break;
				} 

			}
			this.showVStackAll();
		}
		return this._vStack.pop();
		
	}
	private boolean isDigit(char aToken) {
		if(aToken >= '1' && aToken <= '9') {
			return true;
		} else {
			return false;
		}
	}
	private int inComingPrecedence(char aToken) { //들어올 떄의 우선순위. 스택안보다 높음.
		//보통의 연산자는 같은 값
		if(aToken == '+') {
			return 12;
		} else if (aToken == '-') {
			return 12;
		} else if(aToken == '(') {
			return 20;
		} else if(aToken ==')') {
			return 19;
		}else if(aToken =='*') {
			return 13;
		}else if(aToken =='/'){
			return 13;
		}else if(aToken =='%'){
			return 13;
		}else if(aToken == '^'){
			return 17;
		} else if(aToken =='$'){
			return 0;
		} else {
			return -1;
		}
		
	}
	private int inStackPrecedence(char aToken) {
		if(aToken == '+') {
			return 12;
		} else if (aToken == '-') {
			return 12;
		} else if(aToken == '(') {
			return 0;
		} else if(aToken ==')') {
			return 19;
		}else if(aToken =='*') {
			return 13;
		}else if(aToken =='/'){
			return 13;
		}else if(aToken =='%'){
			return 13;
		}else if(aToken == '^'){
			return 16;
		} else if(aToken =='$'){
			return 0;
		} else {
			return -1;
		}
	}
	private void showOStackAll() {
		System.out.print("OStack : ");
		for(int index=0; index < this._oStack.size(); index++) {
			System.out.print(this._oStack.elementAt(index) + " ");
		}
		System.out.println();
		
		
	}
	private void showVStackAll() {
		System.out.print("VStack : ");

		for(int index=0; index < this._vStack.size(); index++) {
			System.out.print(this._vStack.elementAt(index) + " ");
		}
		System.out.println();
		
	}

}
