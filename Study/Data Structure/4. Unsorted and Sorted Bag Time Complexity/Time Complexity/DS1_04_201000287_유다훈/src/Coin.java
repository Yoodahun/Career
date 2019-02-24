
public class Coin {
	 /*코인 클래스*/
	private int _value; 
	private static final int DEFAULT_VALUE = 0;
	
	public Coin() {
		//기본생성자
		this._value = this.DEFAULT_VALUE;
	}
	public Coin(int givenValue) {
		this._value  = givenValue; //코인값을 저장
	}
	
	public int value() {
		return this._value; //코인값 리턴
	}
	

	public void setValue(int newValue) {
		this._value = newValue; //코인값을 새로저장
	}
	
	public boolean equals(Coin aCoin) {
		return (this._value == aCoin.value());
	}

}
