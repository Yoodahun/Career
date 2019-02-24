
public class ParameterSet { //실험에서 필요한 매개변수들을 모아놓은 클래스
	
	private int _startingSize;
	private int _numberOfSizeIncreasingSteps;
	private int _incrementSize;
	
	public int startingSize() {
		return this._startingSize;
	}
	public void setStartingSize(int startingSize){
		this._startingSize = startingSize;
	}
	public int numberOfSizeIncreasingSteps(){
		return this._numberOfSizeIncreasingSteps;
	}
	public void setNumberOfSizeIncreasingSteps(int numberOfSizeIncresingSteps){
		this._numberOfSizeIncreasingSteps = numberOfSizeIncresingSteps;
	}
	public int incrementSize() {
		return this._incrementSize;
	}
	public void setIncrementSize(int incrementSize){
		this._incrementSize = incrementSize;
	}
	public int maxDataSize() {
		return (this.startingSize() + this.incrementSize() *(this.numberOfSizeIncreasingSteps()
				-1));
	}
	public ParameterSet (int givenStartingSize, int givenNumberOfSizeIncresingSteps,
						int givenIncrementSize) {
		this._startingSize = givenStartingSize;
		this._numberOfSizeIncreasingSteps = givenNumberOfSizeIncresingSteps;
		this._incrementSize = givenIncrementSize;
	}
	
}
