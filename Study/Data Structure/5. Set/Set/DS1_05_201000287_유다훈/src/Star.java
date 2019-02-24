
public class Star { 
	private int _xCoordinate; //x좌표
	private int _yCoordinate;
	private String _starName;
	
	public Star(int givenX, int givenY) { //좌표값만 있는 별
		this._xCoordinate = givenX;
		this._yCoordinate = givenY;
		this._starName = null;
		
	}
	public Star(String givenStarName) { //이름만 있는 별
		this._starName = givenStarName;
		this._xCoordinate= 0;
		this._yCoordinate=0;
	}
	public Star(int givenX, int givenY, String givenStarName) { //이름과 좌표가 둘 다 있는 별
		this._xCoordinate = givenX;
		this._yCoordinate = givenY;
		this._starName = givenStarName;
		
		
	}
	public int xCoordinate() { //X좌표
		return this._xCoordinate;
	}
	public int yCoordinate() { //Y좌표
		return this._yCoordinate;
				
	}
	public String starName() { //별 이름 반환
		return this._starName;
	}
	
	public void setXCoordinate(int newX) { //x좌표 설정
		this._xCoordinate = newX;
	}
	public void setYCoordinate(int newY) { //Y좌표 설정
		
		this._yCoordinate = newY;
	}
	public void setStarName(String newStarName) {
		this._starName = newStarName; //별 이름 설정
	}
	
	public boolean equals (Object object) { //입력받은 별이 현재별과 같은지 중복여부 확인
		
		Star aStar = (Star) object;
		if (this._xCoordinate== aStar.xCoordinate() && this._yCoordinate == aStar.yCoordinate()) {
			return true;
		}  
		if(aStar.starName() != null && this._starName.equals(aStar.starName())) {
			return true;
			
		} else {
			return false;
		}
	}

}
