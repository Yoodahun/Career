
public class AppController {
	private AppView _appView;
	private Calculate _calculate;
	
	public AppController() {
		this._appView = new AppView();
		this._calculate = new Calculate();
	}
	
	public void run() {
		this.showMessage(MessageID.Notice_StartProgram);
		this.showMessage(MessageID.Notice_StartMenu);
		
		String input = this._appView.inputExpression();
		
		while(input.charAt(0) != '!') {
			this._calculate.setInfix(input);
			this.evalExpression();
			input = this._appView.inputExpression();
		}
		this.showMessage(MessageID.Notice_EndMenu);
		this.showMessage(MessageID.Notice_EndProgram);
	}
	
	public void evalExpression() { //계산하는 함수
		double finalValue;
		this.showMessage(MessageID.Notice_InfixToPostfix);
		
		if(this._calculate.infixToPostfix()) {
			this._appView.outputPostfix(this._calculate.postfix());
			finalValue = this._calculate.evalPostfix();
			this._appView.ouputResult(finalValue);
		} else {
			this.showMessage(MessageID.Error_Input);
		}
	}
	
	private void showMessage(MessageID aMessage ){
		switch(aMessage) {
		case Notice_StartProgram :
			this._appView.outputMessage(":: 프로그램을 시작합니다. ::\n");
			break;
		case Notice_StartMenu:
			this._appView.outputMessage(" [수식 입력을 시작합니다.]\n");
			break;
		case Notice_InfixToPostfix:
			this._appView.outputMessage("[Infix를 Postfix로]\n");
			break;
		case Notice_EndMenu : 
			this._appView.outputMessage("[수식 입력을 종료합니다.]\n");
			break;
		case Notice_EndProgram:
			this._appView.outputMessage(":: 프로그램을 종료합니다. ::\n");
			break;
		case Error_Input:
			this._appView.outputMessage("잘못된 입력입니다.");
			break;
		default:
			break;
		}
		
	}

}
