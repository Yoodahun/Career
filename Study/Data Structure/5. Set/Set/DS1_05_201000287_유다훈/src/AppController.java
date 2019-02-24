
public class AppController {
	private AppView _appView;
	private ArraySet<Star> _starCollector;
//	private LinkedSet<Star> _starCollector;
	
	public AppController() {
		this._appView = new AppView();
	}
	
	public void run() {
		//this._starCollector = new LinkedSet<Star>();
		this._starCollector = new ArraySet<Star>();
		
		this.showMessage(MessageID.Notice_StartProgram);
		int command = 0;
		while( command != 9) {
			try{
				this.showMessage(MessageID.Notice_Menu);
				command = this._appView.inputInt();
					if (command == 1 ) {
						this.inputStar();
					} else if (command == 2) {
						this.remove();
					} else if (command == 3) {
						this.showMessage(MessageID.Notice_RemoveRandomStar);
						Star removeStar = this._starCollector.removeAny();
						if(removeStar != null) {
							this._appView.outputStar(removeStar.starName(), removeStar.xCoordinate(), removeStar.yCoordinate());
						} else {
							this.showMessage(MessageID.Error_Remove);
						}
					} else if (command == 4 ) {
						this.showMessage(MessageID.Notice_Show);
						this._appView.outputNumberOfStars(this._starCollector.size());
					} else if (command == 5) {
						this.searchByName();
					} else if (command == 6) {
						this.searchByCoordinate();
					}else if (command == 9) {
						this._appView.outputNumberOfStars(this._starCollector.size());
						this.showMessage(MessageID.Notice_EndProgram);
			
					} else {
						this.showMessage(MessageID.Error_WrongMenu);
					}
			} catch (Exception ex) {
			
				System.out.println("Error Message : " + ex.getMessage());
				continue;
			}
		}
		
		
		
		
	}
	public void inputStar() {
		this.showMessage(MessageID.Notice_InputStar);
		
		this.showMessage(MessageID.Notice_InputStarXCoordinate);
		int xCoordinate = this._appView.inputInt();
		
		this.showMessage(MessageID.Notice_InputStarYCoordinate);
		int yCoordinate = this._appView.inputInt();
		
		this.showMessage(MessageID.Notice_InputStarName);
		String starName = this._appView.inputString();
		
		if(! this._starCollector.add(new Star(xCoordinate, yCoordinate, starName))) {
			this.showMessage(MessageID.Error_Input);
		}
			
	}
	
	
	private void searchByCoordinate() {
		// TODO Auto-generated method stub
		this.showMessage(MessageID.Notice_SearchByCoordinate);
		this.showMessage(MessageID.Notice_InputStarXCoordinate);
		int xCoordinate = this._appView.inputInt();
		this.showMessage(MessageID.Notice_InputStarYCoordinate);
		int yCoordinate = this._appView.inputInt();
		
		Star searchStar = new Star(xCoordinate, yCoordinate);
		if(this._starCollector.doesContain(searchStar)){
			this._appView.outputStarExistence(null, xCoordinate, yCoordinate);
		} else
			this.showMessage(MessageID.Error_Remove);
	
		
		
	}
	private void searchByName() {
		// TODO Auto-generated method stub
		this.showMessage(MessageID.Notice_SearchByName);
		this.showMessage(MessageID.Notice_InputStarName);
		Star searchStar = new Star(this._appView.inputString());
		if(this._starCollector.doesContain(searchStar)) {
			this._appView.outputStarExistence(searchStar.starName(), 0, 0);
		} else
			this.showMessage(MessageID.Error_Remove);
			
		
	}
	private void remove() {
		// TODO Auto-generated method stub
		this.showMessage(MessageID.Notice_RemoveStar);
		this.showMessage(MessageID.Notice_InputStarName);
		Star searchStar = new Star(this._appView.inputString());
		if(this._starCollector.doesContain(searchStar)) {
			Star removedStar = this._starCollector.remove(searchStar);
			this._appView.outputStar(removedStar.starName(), removedStar.xCoordinate(), removedStar.yCoordinate());
		} else {
			this.showMessage(MessageID.Error_Remove);
		}
		
		
	}
	private void showMessage(MessageID aMessageID) {
		switch (aMessageID) {
		case Notice_StartProgram :
			this._appView.outputMessage("< 별의 집합을 시작합니다 >\n");
			break;
		case Notice_Menu :
			this._appView.outputMessage("\n\n1:입력    2:주어진 별 삭제    3:임의의 별 삭제\n" + 
		"4:출력    5:이름으로 검색     6:좌표로 검색    9:종료\n");
			this._appView.outputMessage("원하는 메뉴를 입력하세요 : ");
			break;
		case Notice_EndProgram :
			this._appView.outputMessage("<별의 집합을 종료합니다.>\n");
		
			break;
		case Notice_InputStar :
			this._appView.outputMessage("-  [입력]  -\n");
			break;
		case Notice_InputStarXCoordinate:
			this._appView.outputMessage("-  x좌표를 입력하시오 : ");
			break;
		case Notice_InputStarYCoordinate:
			this._appView.outputMessage("-  y좌표를 입력하시오 : ");
			break;
		case Notice_InputStarName:
			this._appView.outputMessage("-  별의 이름을 입력하시오 : ");
			break;
		case Notice_Show:
			this._appView.outputMessage("-  [출력]  -\n");
			break;
		case Notice_RemoveRandomStar:
			this._appView.outputMessage("-  [임의의 별 삭제]  -\n");
			break;
		case Notice_RemoveStar:
			this._appView.outputMessage("-  [주어진 별 삭제]  -\n");
			break;
		case Notice_SearchByName:
			this._appView.outputMessage("-  [이름으로 검색]  -\n");
			
			 break;
		case Notice_SearchByCoordinate:
			this._appView.outputMessage("-  [좌표로 검색]  -\n");
			break;
		
			
		
		case Error_Remove :
			this._appView.outputMessage("별이 존재하지 않습니다.");
			break;
		case Error_WrongMenu :
			this._appView.outputMessage("잘못된 메뉴의 입력입니다.");
			break;
		case Error_Input :
			this._appView.outputMessage("ERROR: 잘못된 입력입니다.");
			break;
		default:
			break;
					
		}
	}
}
