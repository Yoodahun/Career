
public class Experiment { //정렬 성능 측정실험
	
	private final ParameterSet _parameterSet; //생성자 안에서만 값을 설정할 수 있다.
											//객체를 생성할 떄 정해진 값을 그대로 유지.	
	public Experiment (ParameterSet givenParameterSet){
		this._parameterSet = givenParameterSet;
	}
	
	private Integer[] copyListOfGivenSize(Integer[] aList, int copiedSize) {
		Integer[] copiedList = null;
		
		if(copiedSize <= aList.length) {
			copiedList = new Integer[copiedSize];
			for(int i = 0; i< copiedSize; i++) {
				copiedList[i] = aList[i];
			}
		}
		return copiedList;
	}
	private long durationOfSingleSort(Sort<Integer> aSort, Integer[] aList) {
		Timer timer = new Timer();
		timer.start();
			aSort.sort(aList, aList.length);
		timer.stop();
		return timer.duration();
	}
	
	public long[] durationOfSort(Sort<Integer> aSort, Integer[] experimentList) {
		int numberOfSteps = this._parameterSet.numberOfSizeIncreasingSteps();
		long[] durations = new long[numberOfSteps];
		
		int sortingSize = this._parameterSet.startingSize();
		int incrementSize = this._parameterSet.incrementSize();
		
		for(int step = 0; step < numberOfSteps; step++){
			Integer[] listForSorting = this.copyListOfGivenSize(experimentList, sortingSize);
			durations[step] = this.durationOfSingleSort(aSort, listForSorting);
			sortingSize += incrementSize;
		}
		return durations;
	}
}
