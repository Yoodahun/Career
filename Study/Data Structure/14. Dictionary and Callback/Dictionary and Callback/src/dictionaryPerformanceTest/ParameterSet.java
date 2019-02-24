package dictionaryPerformanceTest;

public class ParameterSet {
	private int _numberOfExperiments;
	private int _firstDataSize;
	private int _sizeIncrement;
	
	public ParameterSet() {
		
	}
	public ParameterSet(int givenNumberOfExperiments, int givenFirstDataSize, int givenSizeIncrement) {
		this._numberOfExperiments = givenNumberOfExperiments;
		this._firstDataSize = givenFirstDataSize;
		this._sizeIncrement = givenSizeIncrement;
	}
	
	public int numberOfExperiments(){
		return this._numberOfExperiments;
	}
	public void setNumberOfExperiments(int newNumberOfExperiments){
		this._numberOfExperiments = newNumberOfExperiments;
	}
	public int firstDataSize() {
		return this._firstDataSize;
	}
	public void setFirstDataSize(int newFirstDataSize) {
		this._firstDataSize = newFirstDataSize;
	}
	public int sizeIncrement(){
		return this._sizeIncrement;
	}
	public void setSizeIncrement(int newSizeIncrement) {
		this._sizeIncrement = newSizeIncrement;
	}
	
	public int maxDataSize() {
		return (this._firstDataSize + (this._sizeIncrement * this._numberOfExperiments-1));
	}

}
