
public class AppController {
	private AppView _appView;
	private ArrayList<Character> _arrayStack;
	private int _inputChars; //입력된 문자의 개수
	private int _ignoredChars; //무시된 문자의 개수
	private int _addedChars; //삽입된 문자의 개수

	public AppController() {
		this._appView = new AppView();
		this._inputChars = 0;
		this._ignoredChars = 0;
		this._addedChars = 0;
	}
	public void run() {
		this._arrayStack = new ArrayList<Character>();
		char input;
		
		this.showMessage(MessageID.Notice_StartProgram);
		this.showMessage(MessageID.Notice_StartMenu);
		input = this._appView.inputCharacter();
		while(input != '!') {
			this.countInputChar();
			if ((input >= 'A' && input <= 'Z') || (input >= 'a' && input <= 'z')) {
				this.addToStack(input);
			} else if (input>= '0' && input <= '9') {
				this.removeN(input - '0');
			} else if (input == '-') {
				this.removeOne();
			} else if (input == '#' ) {
				this.showStackSize();
			} else if (input == '/') {
				this.showAllFromBottom();
			} else if(input =='\\') {
				this.showAllFromTop();
			} else if (input =='^') {
				this.showTopElement();
			} else {
				this.showMessage(MessageID.Error_WrongMenu);
				this.countIgonored();
			}
			input = this._appView.inputCharacter();
		}
		this.showMessage(MessageID.Notice_EndMenu);
		this.conclusion();
		this.showMessage(MessageID.Notice_EndProgram);
	}
	
	
	private void showAllFromBottom() {
		this.showMessage(MessageID.Notice_ShowStack);
		this.showMessage(MessageID.Show_StartBottom);
		
		for(int index = 0; index < this._arrayStack.size(); index++) {
			this._appView.outputStackElement((char)this._arrayStack.elementAt(index));
		}
		this.showMessage(MessageID.Show_EndBottom);
		
	}
	private void showAllFromTop() {
		this.showMessage(MessageID.Notice_ShowStack);
		this.showMessage(MessageID.Show_StartTop);
		
		for(int index = (this._arrayStack.size() - 1) ; index > -1; index--) {
			this._appView.outputStackElement((char) this._arrayStack.elementAt(index));
			
		}
		this.showMessage(MessageID.Show_EndTop);
		
	}
	private void showTopElement() {
		this.showMessage(MessageID.Notice_ShowStack);
		this._appView.outputTopElement(this._arrayStack.peek());
		
	}
	private void showStackSize() {
		this._appView.outputStackSize(this._arrayStack.size());
		
	}
	private void countAdded() {
		this._addedChars++;
		
	}
	private void countIgonored() {
		this._ignoredChars++;
		
	}
	private void countInputChar() {
		this._inputChars++;
		
	}
	private void addToStack(char inputChar) {
		if (this._arrayStack.push(new Character(inputChar))) {
			this._appView.outputAddedElement(inputChar);
			this.countAdded();
		} else {
			this.showMessage(MessageID.Error_InputFull);
		}
		
	}
	private void removeOne() {
		if(this._arrayStack.isEmpty()) {
			this.showMessage(MessageID.Error_RemoveEmpty);
		} else {
			this._appView.outputRemove((char)this._arrayStack.pop());
		}
		
	}
	private void removeN(int numberOfCharsToBeRemoved) {
		for(int i = 0; i < numberOfCharsToBeRemoved; i++){
			if(this._arrayStack.isEmpty()) {
				this.showMessage(MessageID.Error_RemoveEmpty);
				break;
			} else {
				this._appView.outputRemove((char)this._arrayStack.pop());
			}
		}
	}
	private void conclusion() {
		for(int i = this._arrayStack.size() ; i > -1; i--) {
			if(this._arrayStack.isEmpty()) {
				break;
			} else {
			removeOne();
			}
		}
		this._appView.outputResult(_inputChars, _ignoredChars, _addedChars);
		
		
	}
	private void showMessage(MessageID aMessage) {
		switch(aMessage) {
		case Notice_StartProgram :
			this._appView.outputMessage("> 프로그램을 시작합니다.\n");
			break;
		case Notice_StartMenu :
			this._appView.outputMessage("[스택 사용을 시작합니다.]\n");
			break;
		case Notice_EndMenu :
			this._appView.outputMessage("[스택 사용을 종료합니다.]\n");
			break;
		case Notice_EndProgram :
			this._appView.outputMessage("> 프로그램을 종료합니다.\n");
			break;
		case Notice_ShowStack :
			this._appView.outputMessage("[Stack] ");
			break;
		case Show_StartBottom :
			this._appView.outputMessage("<Bottom>");
			break;
		case Show_EndBottom:
			this._appView.outputMessage("<Top>\n");
			break;
		case Show_StartTop :
			this._appView.outputMessage("<Top>");
			break;
		case Show_EndTop:
			this._appView.outputMessage("<Bottom>\n");
			break;
		case Error_WrongMenu:
			this._appView.outputMessage("[Error] 의미 없는 문자가 입력되었습니다.\n");
			break;
		case Error_InputFull:
			this._appView.outputMessage("[Full] 스택이 꽉 차서 삽입이 불가능합니다.\n");
			break;
		case Error_RemoveEmpty:
			this._appView.outputMessage("[Empty] 스택에 삭제할 원소가 없습니다.\n");
			break;
		default :
			break;
		}
	}
}
