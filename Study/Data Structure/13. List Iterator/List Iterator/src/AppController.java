
public class AppController {
	private AppView _appView;
	private List<Integer> _sortedList;
	
	public AppController() {
		this._appView = new AppView();
	}
	public void run() {
		this._sortedList = new SortedArrayList<Integer>();
//		this._sortedList = new SortedLinkedList<Integer>();
		char command = '0';
		int input;
		
		this.showMessage(MessageID.Notice_StartProgram);
		while(command != '!') {
			
			command = this._appView.inputCharacter();
			
			if(command == '%') {
				input = this._appView.inputNumber();
				this.add(input);
			} else if(command == '~') {
				this.reset();
			} else if (command == '-') {
				this.removeMin();
			} else if (command == '+') {
				this.removeMax();
			} else if (command == '#') {
				this.showSize();
			} else if (command == '?') {
				input = this._appView.inputNumber();
				this.removeFrom(input);
				
			} else if (command == '/') {
				this.showAll();
			} else if (command == '!') {
				break;
			} else {
				this.showMessage(MessageID.Error_WrongMenu);
			}
			
			
		}
		this.showMessage(MessageID.Notice_EndProgram);
		
	}
	private void showSize() {
		this._appView.outputSize(this._sortedList.size());
	}
	private void reset() {
		this._sortedList.clear();
		this.showMessage(MessageID.Notice_Reset);
	}
	@SuppressWarnings("unchecked")
	private void showAll() {
		this.showMessage(MessageID.Notice_ShowStartList);
		
		Iterator<Integer> iterator = this._sortedList.listIterator();
		
		while(iterator.hasNext()){
			this._appView.outputElement((int)iterator.next());
			
		}
		
		
//		for(int i = 0 ; i < this._sortedList.size() ; i++) {
//			this._appView.outputElement(this._sortedList.elementAt(i));
//		}
		this.showMessage(MessageID.Notice_ShowEndList);
		
		
	}
	private void add(int inputValue) {
		Integer addValue = inputValue;
		if(this._sortedList.add(addValue)) {
			this._appView.outputAdd(inputValue);
		}
	}
	private void removeMin() {
		this._appView.outputRemove(this._sortedList.removeMin().intValue());
	}
	private void removeMax() {
		this._appView.outputRemove(this._sortedList.removeMax().intValue());
	}
	private void removeFrom(int aPosition) {
		this._appView.outputRemove(this._sortedList.removeFrom(aPosition).intValue());
		
	}
	
	private void showMessage(MessageID aMessage) {
		switch(aMessage) {
		case Notice_StartProgram :
			this._appView.outputMessage("< 리스트를 시작합니다 >\n");
			break;
		case Notice_EndProgram:
			this._appView.outputMessage("< 리스트가 끝났습니다 >\n");
			break;
		case Notice_Reset:
			this._appView.outputMessage("- 리스트를 비웠습니다.\n");
			break;
		case Notice_ShowStartList:
			this._appView.outputMessage("[LIST] ");
			break;
		case Notice_ShowEndList:
			this._appView.outputMessage("\n");
			break;
		case Error_WrongMenu:
			this._appView.outputMessage("잘못된 입력입니다");
			break;
			default :
				break;
		}
	}

}
