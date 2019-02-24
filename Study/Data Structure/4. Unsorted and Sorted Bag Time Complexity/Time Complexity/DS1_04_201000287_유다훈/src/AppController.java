
public class AppController {
	private AppView  _appView;
	private PerformanceMeasurement _pm;
	
	public AppController() {
		this._appView = new AppView();
	}

	public void run() {
		//Start
		this._pm = new PerformanceMeasurement();
		this.showMessage(MessageID.Notice_StartProgram);
		this._pm.generateData();
		
		//SortedArray
		this.showMessage(MessageID.Notice_SortedArrayStart);
		this._pm.testSortedArrayBag();
		this.showTestResult();
		
		//UnsortedArray
		this.showMessage(MessageID.Notice_UnsortedArrayStart);
		this._pm.testUnsortedArrayBag();
		this.showTestResult();

		//SortedLinked
		this.showMessage(MessageID.Notice_SortedLinkedStart);
		this._pm.testSortedLinkedBag();
		this.showTestResult();

		
		//UnsortedLinked
		this.showMessage(MessageID.Notice_UnsortedLinkedStart);
		this._pm.testUnsortedLinkedBag();
		this.showTestResult();
		
		
		
		//end program
		this.showMessage(MessageID.Notice_EndProgram);
	}
	
	private void showTestResult() {
		TestResult testResults[] = this._pm.testResults();
		for(int index = 0; index < this._pm.numberOfTest(); index++) {
			this._appView.outputResult(testResults[index].testSize(),
					testResults[index].testInsertTime(), 
					testResults[index].testFindMaxTime());
		}
	}
	
	private void showMessage(MessageID aMessageID) {
		switch (aMessageID) {
		case Notice_StartProgram :
			this._appView.outputMessage("<<List의 구현에 따른 실행 성능 차이 알아보기>>\n");
			break;
		case Notice_EndProgram :
			this._appView.outputMessage("<<성능 측정을 종료합니다>>\n");
			System.exit(0);
			break;
		case Notice_UnsortedArrayStart :
			this._appView.outputMessage("[Unsorted Array List]\n");
			break;
		case Notice_SortedArrayStart :
			this._appView.outputMessage("[Sorted Array List]\n");
			break;
		case Notice_UnsortedLinkedStart:
			this._appView.outputMessage("[Unsorted Linked List]\n");
			break;
		case Notice_SortedLinkedStart:
			this._appView.outputMessage("[Sorted Linked List]\n");
			break;
		case Error_WrongMenu:
			this._appView.outputMessage("<<ERROR: 잘못된 메뉴입니다.>>\n");
			break;
		default:
			break;
					
		}
	}
	
}
