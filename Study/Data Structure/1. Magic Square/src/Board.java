
public class Board {
	private static int EMPTY_CELL = -1; //빈칸을 표현하기 위한 -1.
	private int _order; //차수.
	private int[][] _cell; //2차원배열로 표현된셀.
	
	public Board(int givenOrder) { //생성자.
		this._order = givenOrder; //차수 설정.
		this._cell = new int [givenOrder][givenOrder]; // 차수x차수 크기만큼의 배열생성.
		
		for (int row = 0; row < givenOrder; row++) {
			for (int col = 0; col < givenOrder; col++) {
				this._cell[row][col] = Board.EMPTY_CELL; //배열에 초기값(빈칸) 설정.
			}
		}
	}
	

	public int order() {
		// TODO Auto-generated method stub
		return _order; //차수 반환.
	}
	public void setCell (CellLocation aLocation, int aCellValue) { //해당되는 위치에 값을 설정.
		this._cell[aLocation.row()][aLocation.col()] = aCellValue;
	}
	public int cell (CellLocation aLocation) { //해당되는 위치의 값을 반환.
		return  this._cell[aLocation.row()][aLocation.col()];
		
	
	}
	public boolean cellsEmpty (CellLocation aLocation) { //위치에 값이 있는지 없는지 확인.
		if (this._cell[aLocation.row()][aLocation.col()] != -1)  //만일 값이 빈칸이 아니라면,
			return false; //false반환.
		else 
			return true; //빈칸이라면 true 반환.
		
	}




}
