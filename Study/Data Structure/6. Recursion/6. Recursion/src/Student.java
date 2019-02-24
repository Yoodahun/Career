
public class Student {
	private int _score;
	
	public Student(int givenScore) {
		this._score = givenScore;
	}
	
	public int score() {
		return this._score;
	}
	public void setScore(int newScore) {
		this._score = newScore;
	}
}
