package dictionaryPerformanceTest;

public class PerformanceMeasurement {
	private static final int DEFAULT_NUMBER_OF_EXPERIMENTS = 5;
	private static final int  DEFAULT_FIRST_DATA_SIZE = 10000;
	private static final int  DEFAULT_SIZE_INCREMENT = 10000;
	
	private ParameterSet _parameterSet;
	
	private int[] _ascendingList;
	private int[] _descendingList;
	private int[] _randomList;
	
	public PerformanceMeasurement() {
		this.setParameterSet(new ParameterSet(PerformanceMeasurement.DEFAULT_NUMBER_OF_EXPERIMENTS
			, PerformanceMeasurement.DEFAULT_FIRST_DATA_SIZE, PerformanceMeasurement.DEFAULT_SIZE_INCREMENT));
		this.generateData();
	}
	public PerformanceMeasurement(int givenNumberOfExperiments, int givenFirstDataSize, int givenSizeIncrement) {
		this.setParameterSet(new ParameterSet(givenNumberOfExperiments, givenFirstDataSize, givenSizeIncrement));
		this.generateData();
	}
	
	
	public ParameterSet parameterSet() {
		return this._parameterSet;
	}
	public void setParameterSet(ParameterSet newParameterSet) {
		this._parameterSet = newParameterSet;
	}
	public int[] ascendingList() {
		return this._ascendingList;
	}
	public int[] descendingList() {
		return this._descendingList;
	}
	public int[] randomList() {
		return this._randomList;
	}
	
	public void generateData() { //최대치 생성
		this._ascendingList = DataGenerator.ascendingOrderList(this._parameterSet.maxDataSize());
		this._descendingList = DataGenerator.descendingOrderList(this._parameterSet.maxDataSize());
		this._randomList = DataGenerator.randomOrderList(this._parameterSet.maxDataSize());
	}
	public int[] experimentList(ListOrder anOrder) {
		if(anOrder == ListOrder.Ascending) {
			return this._ascendingList;
		} else if (anOrder == ListOrder.Descending) {
			return this._descendingList;
		} else if(anOrder == ListOrder.Random) {
			return this._randomList;
		} else {
			return null;
		}
	}
	private UnitExperimentResult unitExperiment(Dictionary<Integer, String> aDictionary, 
												ListOrder anOrder, int dataSize) {
		long startTime;
		long stopTime;
		int[] experimentList = this.experimentList(anOrder);
		
		long timeForAdd = 0;
		for(int i=0; i< dataSize ; i++) {
			startTime = System.nanoTime();
			aDictionary.addKeyAndObject(experimentList[i], null);
			stopTime = System.nanoTime();
			timeForAdd += (stopTime - startTime);
		}
		
		long timeForSearch = 0;
		for(int i =0; i<dataSize; i++) {
			startTime = System.nanoTime();
			aDictionary.objectForKey(experimentList[i]);
			stopTime = System.nanoTime();
			timeForSearch += (stopTime - startTime) ;
		}
		
		long timeForRemove = 0;
		for(int i =0; i<dataSize; i++) {
			startTime = System.nanoTime();
			aDictionary.removeObjectForKey(experimentList[i]);
			stopTime = System.nanoTime();
			timeForRemove += (stopTime - startTime) ;
		}
		
		return (new UnitExperimentResult(dataSize, timeForAdd, timeForSearch, timeForRemove));
		
	}
	
	public UnitExperimentResult[] experimentByDictionaryAndListOrderType
					(Dictionary<Integer, String> aDictionary, ListOrder anOrder) {
		UnitExperimentResult[] experimentResults =
				new UnitExperimentResult[this._parameterSet.numberOfExperiments()];
		//측정결과를 안정시키기 위한 워밍업
		this.unitExperiment(aDictionary, anOrder, this._parameterSet.maxDataSize());
		
		System.gc(); //쓰레기처리를 미리 실행.
		
		int dataSize = this._parameterSet.firstDataSize();
		for (int iteration = 0; iteration < this._parameterSet.numberOfExperiments(); iteration++) {
			aDictionary.clear();
			experimentResults[iteration] = this.unitExperiment(aDictionary, anOrder, dataSize);
			dataSize += this._parameterSet.sizeIncrement();
		}
		return experimentResults;
	}
	
	
	
}
