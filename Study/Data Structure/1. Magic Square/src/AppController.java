
public class AppController {
	private AppView _appView;
	private Board _board;
	private MagicSquare _magicSquare;
	
	public AppController() { //생성자.
		this._appView = new AppView();
		this._board = null;
		this._magicSquare = new MagicSquare();
	}
	
	public void run() {
		this.showMessage(MessageID.Notice_BeginMagicSquare); //마방진풀이를 시작합니다 라는 메세지 출력.
		OrderValidity currentOrderValidity; //차수가 유효한 홀수인지 검사하기 위한 enum.
		
		int order = this._appView.inputOrder();
		
		while(order > 0) { //만약 차수가 0 이상이라면.
			currentOrderValidity = this._magicSquare.checkOrderValidity(order); //입력받은 차수가 유효한지 검사한 값을 저장.
			
			if(currentOrderValidity == OrderValidity.Valid) { //만일 입력받은 차수가 유효한 값이라면,
				this._appView.outputTitleWithOrder(order); // 차수를 출력.
				this._board = this._magicSquare.solve(order); // 문제를 풀고,
				this.showBoard(this._board); //푼 문제를 출력한다.
			}
			else { //만약 차수가 유효한 값이 아니라면,
			this.showOrderValidityErrorMessage(currentOrderValidity); //에러메세지 출력.
		}
	
		order = this._appView.inputOrder(); //다음 문제를 풀기 위해 안내문을 출력하고 값을 다시 받기위한 준비를 한다.
	}
		this.showMessage(MessageID.Notice_EndMagicSquare); //차수가 0이하라면,
		System.exit(0); // 프로그램을 종료한다.
	}



	private void showOrderValidityErrorMessage(OrderValidity anOrderValidity) { //차수가 유효한 값이 아닐때 출력하는 에러메소드.
		// TODO Auto-generated method stub
		switch (anOrderValidity) {
		case TooSmall : //차수가 너무 작을때,
			this.showMessage(MessageID.Error_OrderIsTooSmall); //차수가 너무 작다는 에러메세지 출력.
			break;
			
		case TooLarge : //차수가 너무 클 때,
			this.showMessage(MessageID.Error_OrderIsTooLarge); //차수가 너무 크다는 에러메세지 출력.
			break;
			
		case NotOddNumber: //차수가 홀수가 아닐 때,
			this.showMessage(MessageID.Error_OrderIsNotOddNumber); //차수가 홀수가 아니라는 에러메세지 출력.
			break;
			default:
				break;
		}
		
	}

	private void showBoard(Board aBoard) { //문제를 풀고 답을 출력하는 메소드
		// TODO Auto-generated method stub
		CellLocation currentLoc = new CellLocation();
		this.showTitleForColumnIndexes(aBoard.order()); //헤드컬럼을 출력하는 메소드.
		
		for (int row= 0; row < aBoard.order(); row++) { //가로
			this._appView.outputRowNumber(row); 
			for(int col = 0; col<aBoard.order(); col++) { //세로.
				currentLoc.setRow(row); //현재 배열에 가로세로 값을 지정해줌.
				currentLoc.setCol(col);
				this._appView.outputCell(aBoard.cell(currentLoc)); //좌표가 저장된 현재위의 값을 출력.
			}
			this._appView.outputLine("");
		}
		
		
		
		
		
	}

	private void showTitleForColumnIndexes(int anOrder) { //헤드컬럼 출력.
		// TODO Auto-generated method stub
		this._appView.output("     ");//d
		
		for (int col = 0; col < anOrder; col++) {
			this._appView.output(String.format("[%3d]",col));//헤드컬럼 출력
		}
		this._appView.outputLine("");
		
	}

	private void showMessage(MessageID aMessageID) { //메세지 출력메소드
		// TODO Auto-generated method stub
		switch (aMessageID) {
		case Notice_BeginMagicSquare: //첫 프로그램이 시작되었을때.
			this._appView.outputLine("<<마방진 풀이를 시작합니다.>>");
			break;
			
		case Notice_EndMagicSquare: //프로그램이 끝났을 때.
			this._appView.outputLine("");
			this._appView.outputLine("<<< 마방진 풀이를 종료합니다 >>>");
			break;
			
		case Error_OrderIsTooSmall: // 차수가 너무 작을때.
			this._appView.outputLine("오류 : 차수가 너무 작습니다. 3 보다 크거나 같아야 합니다.");
			break;
		case Error_OrderIsTooLarge://차수가 너무 클 때.
			this._appView.outputLine("오류 : 차수가 너무 큽니다. 99 보다 작거나 같아야 합니다.");
			break;
		case Error_OrderIsNotOddNumber: //차수가 홀수가 아닐 때.ru
			this._appView.outputLine("오류 : 차수가 짝수입니다 홀수이어야 합니다..");
			break;
		}
		
	}

}
