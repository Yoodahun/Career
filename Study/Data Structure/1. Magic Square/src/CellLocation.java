
public class CellLocation { //셀의 위치를 가로세로로 표현할 수 있음.
	private int _row;
	private int _col;
	
	public CellLocation() { //객체를 생성한다.
		this._row = -1;
		this._col = -1;
	}
	public CellLocation(int givenRow, int givenCol) { //객체를 주어진 값으로 생성한다.
		this._row = givenRow;
		this._col = givenCol;
	}
	
	public void setRow(int newRow) {
		this._row = newRow;
	}
	public int row() {
		return this._row;
	}
	
	public void setCol(int newCol) {
		this._col = newCol;
		
	}
	public int col(){
		return this._col;
	}

}
