

public class AppController {
	private AppView _appView;
	private ArrayBag _coinCollector; //코인 컨트롤러
	
	public AppController() {
		this._appView = new AppView();
	}
	
	
	
	
	public void run() {
		// TODO Auto-generated method stub
		
		int totalCoin = 0; //들어갈 수 있는총 코인의 갯수
		int input = 0; //넣으려는 코인
		int order = 0; //메뉴 번호
		
		this.showMessage(MessageID.Notice_StartProgram); //프로그램 시작
		this.showMessage(MessageID.Notice_InputTotalCoin);//총 코인의 개수 입력 출력
		
		totalCoin = this._appView.inputInt(); //총 코인의 갯수
		this._coinCollector = new ArrayBag(totalCoin); //총 코인의 갯수만큼 가방공간 생성
		
		while (order != 9) { //메뉴 번호가 9가 아니라면 계속 실행
			this.showMessage(MessageID.Notice_Menu); //메뉴 출력
			order = this._appView.inputInt(); //수행하려고 하는 메뉴 입력받기
			if(order == 1) { //1번메뉴
				this.showMessage(MessageID.Notice_InputCoin); //넣으려는 코인의 액수
				input = this._appView.inputInt(); //액수 입력
				Coin anCoin = new Coin(input); //코인값을 저장하는 코인객체 생성
				this._coinCollector.add(anCoin); //코인값을 가방에 저장
			}
			else if (order == 2) { //2번메뉴
				this.showMessage(MessageID.Notice_InputCoin); //코인액수 입력
				input = this._appView.inputInt();
				Coin givenCoin = new Coin(input); //입력받은 코인액수를 이용하여 코인 객체 생성
				this._coinCollector.remove(givenCoin); //입력받은 코인액수를 삭제
			} else if (order == 3) { //3번메뉴
				this._appView.outputResult(this._coinCollector.size(), this._coinCollector.maxElementValue(), this._coinCollector.sumElemnetValues());
				//가방에 들어있는 코인의 갯수 , 가장 큰 코인값, 모든 코인의 합
			} else if (order == 4) { //4번 메뉴
				this.showMessage(MessageID.Notice_InputCoin); //코인값 입력
				input = this._appView.inputInt();
				Coin givenCoin = new Coin(input);
				this._appView.outputSearch(input, this._coinCollector.frequencyOf(givenCoin));
				//입력받은 코인값이 몇개가 존재하는지 검색
			} else if (order == 9) { //9번메뉴
				this.showMessage(MessageID.Notice_EndMenu); //메뉴를 종료
				this._appView.outputResult(this._coinCollector.size(), this._coinCollector.maxElementValue(), this._coinCollector.sumElemnetValues());
				//결과출력. 총 코인갯수, 가장 큰 코인, 코인들의 합.
				this.showMessage(MessageID.Notice_EndProgram); //프로그램 종료 메세지 출력
				System.exit(0); //프로그램 종료
			}
			
			
		}
		
	}
	private void showMessage(MessageID aMessageID) { //메세지 출력메소드
		// TODO Auto-generated method stub
		switch(aMessageID) {
		case Notice_StartProgram : //프로그램 시작시
			this._appView.outputMessage("<<동전 가방 프로그램을 시작합니다>>" + "\n");
			break;
		case Notice_InputTotalCoin: //가방에 들어갈 총 코인 갯수
			this._appView.outputMessage("가방에 들어갈 총 코인 개수를 입력하시오: ");
			break;
		case Notice_Menu : // 메뉴알림 및 선택
			this._appView.outputMessage("수행하려고하는 메뉴를 선택하세요" + "\n");
			this._appView.outputMessage("(add: 1, remove: 2, print: 3, search: 4, exit: 9): ");
			break;
		case Notice_InputCoin: // 코인의 액수
			this._appView.outputMessage("코인의 액수를 입력하세요: ");
			break;
		case Notice_EndMenu: // 종료합니다
			this._appView.outputMessage("9가 입력되어 종료합니다." + "\n");
			break;
		case Notice_EndProgram: // 프로그램 종료
			this._appView.outputMessage("<<동전 가방 프로그램을 종료합니다>>");
			break;
		default:
			break;
		
		}
		 
		
		
		
	}

}
