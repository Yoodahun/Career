package dictionaryPerformanceTest;

public class UnitExperimentResult { //최소단위검사? 결과 저
	
	private int _experimentSize;
	private double _timeForAdd;
	private double _timeForSearch;
	private double _timeForRemove;
	
	public UnitExperimentResult(int givenExperimentSize, double givenTimeForAdd,
								double givenTimeForSearch, double givenTimeForRemove) {
		this._experimentSize = givenExperimentSize;
		this._timeForAdd = givenTimeForAdd;
		this._timeForSearch = givenTimeForSearch;
		this._timeForRemove = givenTimeForRemove;
	}
	
	public int experimentSize() {
		return this._experimentSize;
	}
	public void setExperimentSize(int newExperimentSize) {
		this._experimentSize = newExperimentSize;
	}
	
	public double timeForAdd() {
		return this._timeForAdd;
	}
	public void setTimeForAdd(double newTimeForAdd) {
		this._timeForAdd = newTimeForAdd;
	}
	public double timeForSearch(){
		return this._timeForSearch;
	}
	public void setTimeForSearch(double newTimeForSearch) {
		this._timeForSearch = newTimeForSearch;
	}
	public double timeForRemove() {
		return this._timeForRemove;
	}
	public void setTimeForRemove(double newTimeForRemove) {
		this._timeForRemove = newTimeForRemove;
	}
	

}
