
public class AppController {
	private AppView _appView;
	//private CircularArrayQueue<Character> _queue;
	private CircularlyLinkedQueue<Character> _queue;
	private int _inputChars;
	private int _ignoredChars;
	private int _addedChars;
	
	public AppController() {
		this._appView = new AppView();
		this.initCharCounts(); //입력받고 추가 혹은 무시된 숫자들을 초기화
	}
	private void initCharCounts() {
		this._inputChars = 0;
		this._addedChars = 0;
		this._ignoredChars = 0;
	}
	private void countAdded() { //추가글자 상승
		this._addedChars++;
	}
	private void countIgnored(){ //무시글자 상승
		this._ignoredChars++;
	}
	private void countInputChar() { //입력횟수 상승
		this._inputChars++;
	}
	private void showFrontElement() { //맨 앞 원소 출력
		this._appView.outputFrontElement((char)this._queue.frontElement());
	}
	private void showQueueSize() { //큐의 사이즈 반환
		this._appView.outputQueueSize(this._queue.size());
	}
	private void showAll() { //큐의 내용물을 반환
		int queueSize = this._queue.size();
		Character elementForOutput = null;
		
		this._appView.outputMessage("[Queue] <Front> ");
		for (int position = 0; position < queueSize; position++) {
			elementForOutput = this._queue.elementAt(position);
			this._appView.outputElement(elementForOutput);
		}
		this._appView.outputMessage("<Rear>\n");
	}
	private void add(Character anElement) { //큐에 더하기
		if(!this._queue.enQueue(anElement)) {
			this.showMessage(MessageID.Error_InputFull);
		} else {
			this._appView.outputAdd(anElement);
			this.countAdded();
		}
	}
	private void removeOne() { //하나 삭제하기
		if(!this._queue.isEmpty()) {
		this._appView.outputRemove((char)this._queue.deQueue());
		} else {
			this.showMessage(MessageID.Error_Empty);
		}
			
	}
	private void removeN(int numberOfCharsToBeRemoved) { //입력된 숫자만큼 삭제하기
		this._appView.outputRemoveN(numberOfCharsToBeRemoved);
		for(int i = 0; i < numberOfCharsToBeRemoved; i++) {
			removeOne();
		}
	}
	private void conclusion() { //결과 출력
		for(int i =0; i<= this._queue.size(); i++) {
			removeOne();
		}
		this._appView.outputResult(_inputChars, _ignoredChars, _addedChars);
	}
	
	public void run() {
		//this._queue = new CircularArrayQueue<Character>(); //큐의 생성
		this._queue = new CircularlyLinkedQueue<Character>();
		char inputChar;
		
		this.showMessage(MessageID.Notice_StartProgram);
		this.showMessage(MessageID.Notice_StartMenu);
		inputChar = this._appView.inputCharacter();
		
		while(inputChar != '!') { //입력된 문자가 느낌표가 아닌동안 계속 반복
			this.countInputChar();
			
			if((inputChar >= 'A' && inputChar <= 'Z') || 
					(inputChar >= 'a' && inputChar <= 'z')){
				this.add(inputChar);
				
			} else if(inputChar >='0' && inputChar <= '9') {
				this.removeN(Integer.parseInt(String.valueOf(inputChar)));
				//입력된 숫자를 문자열로 바꾸고 다시 정수형으로 바꾸어서 전달
				
			} else if(inputChar == '-' ) {
				this.removeOne(); //하나 삭제
			} else if (inputChar == '#') {
				this.showQueueSize(); //큐의 사이즈
			} else if (inputChar == '/') {
				this.showAll(); //큐 내용물 전부 출력
			} else if (inputChar == '^') {
				this.showFrontElement(); //큐의 맨 앞 원소 출력
			} else {
				this.showMessage(MessageID.Error_WrongMenu);
				this.countIgnored();
			}
			inputChar = this._appView.inputCharacter();
		}
		this.showMessage(MessageID.Notice_EndMenu);
		this.conclusion();
		this.showMessage(MessageID.Notice_EndProgram);
	}
	
	
	private void showMessage(MessageID aMessage) {
		switch(aMessage) {
		case Notice_StartProgram :
			this._appView.outputMessage("> 프로그램을 시작합니다.\n");
			break;
		case Notice_StartMenu :
			this._appView.outputMessage("[큐 입력을 시작합니다.]\n");
			break;
		case Notice_EndMenu :
			this._appView.outputMessage("[큐 입력을 종료합니다.]\n");
			break;
		case Notice_EndProgram :
			this._appView.outputMessage("> 프로그램을 종료합니다.\n");
			break;
			
		case Error_WrongMenu:
			this._appView.outputMessage("ERROR : 의미 없는 문자가 입력되었습니다.\n");
			break;
		case Error_InputFull:
			this._appView.outputMessage("ERROR : 큐가 꽉 차서 삽입이 불가능합니다.\n");
			break;
		case Error_Empty:
			this._appView.outputMessage("[Empty] 큐에 원소가 없습니다.\n");
			break;
		default :
			break;
		}
	}
}
