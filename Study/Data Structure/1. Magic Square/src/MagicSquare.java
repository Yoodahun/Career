
public class MagicSquare {
	private static int DEFAULT_MAX_ORDER = 99; //마방진을 구현할 수 있는 최대 차수값.
	
	private int _maxOrder; 
	private int _order;
	private Board _board;
	
	public int maxOrder() {
		return this._maxOrder;
		
	}
	public int order() {
		return this._order;
	}
	
	public MagicSquare() { //기본 생성자.
		this._maxOrder = MagicSquare.DEFAULT_MAX_ORDER;
		this._order = 3;
		this._board = null;
	}
	
	public MagicSquare(int givenMaxOrder) { //차수가 주어졌을 때 생성자.
		this._maxOrder = givenMaxOrder;
		this._order = 3;
		this._board = null;
				
	}

	public OrderValidity checkOrderValidity(int anOrder) { //차수가 유효한지 검사하고 해당되는 유/무효값을 리턴.
		// TODO Auto-generated method stub
		if (anOrder < 3) { //3보다 작으면,
			return OrderValidity.TooSmall; //매우 작다 값.
		} else if (anOrder > 99) { // 99보다 크면,
			return OrderValidity.TooLarge; //매우 크다 값.
		} else if (anOrder % 2 == 0) { // 차수를 나눠서 0으로 떨어지면 짝수이므로,
			return OrderValidity.NotOddNumber; // 홀수가 아니다 값.
		} else
		return OrderValidity.Valid; // 모든 조건을 검사하고 통과하면 유효값 리턴.
		
	}

	public Board solve(int anOrder) { //문제를 푸는 메소드.
		// TODO Auto-generated method stub
		this._order = anOrder; //차수를 저장. 원래 기본값은 3이었으나 바꿈.
		if(this.checkOrderValidity(anOrder) != OrderValidity.Valid) { //만일 차수가 유효하지 않으면,
			return null; //실행하지 않음.
		} else { //차수가 유효값이라면,
			this._board = new Board(this._order); // 차수x차수만큼의 판 객체 생성. 
			
			CellLocation currentLoc = new CellLocation(0, this._order/2); //현재위치 객체 생성. 위치는 가로 0/ 세로 = 차수를 2로나눈 가운데 값.
			CellLocation nextLoc = new CellLocation(); //아무 값도 없는 다음위치 객체 생성.
			
			this._board.setCell(currentLoc, 1);  // 보드의 첫번째 값을 설정. 현재위치에 1 입력.
			int lastValue = this._order * this._order; //보드 내부에 채워지는 값 중 최대값 설정. 차수x차수
			
			for(int cellValue = 2; cellValue <= lastValue; cellValue++){ //다음 값 2부터 최대값까지 실행.
				//다음 위치는 현재 위치로부터 오른쪽 위.
				nextLoc.setRow(currentLoc.row()-1); //다음 가로위치는 현재 가로위치로 부터 한 칸 위. 
				if(nextLoc.row() < 0) { //만약 0번째줄보다 값이 낮아진다면, 마방진이 원통형으로 있다는 가정하에 바로아래값으로 가야하므로,
					nextLoc.setRow(this.order()-1); //최대값보다 1낮은 마방진의 제일 아래쪽으로 이동.
				}
				nextLoc.setCol(currentLoc.col()+1); // 다음 세로 위치는 현재 세로위치로부터 한 칸 옆. 오른쪽으로 증가.
				if(nextLoc.col() >= this.order()) { // 만약 최대값보다 크게된다면, 마방진이 원통형으로 있다는 가정하에 바로 왼쪽값(제일 낮은값)으로 .
					nextLoc.setCol(0); //제일 낮은 값 0으로 설정.
				}
				
				if (! this._board.cellsEmpty(nextLoc)) { //만약 다음위치에 이미 값이 들어있으면,
					nextLoc.setRow(currentLoc.row()+1); //현재위치로부터 바로 아래칸으로 내려간다.
					nextLoc.setCol(currentLoc.col()); // 세로위치는 그대로.
				}

				//다음위치에 설정해놓은 가로세로값을 현재위치로 바꾸어 오른쪽 위로 진행한다.
				currentLoc.setRow(nextLoc.row()); 
				currentLoc.setCol(nextLoc.col());
				// 현재위치에 들어있는 값을 그린다.
				this._board.setCell(currentLoc, cellValue);
 				
			}
			return this._board; //이렇게 그려진 값을 리턴한다.
		}
		
		
	}

}
