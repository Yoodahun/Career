
public class ExperimentManager { //실험관리자
	private static final int DEFAULT_NUMBER_OF_SIZE_INCREASING_STEPS = 10;
	private static final int DEFAULT_INCREMENT_SIZE= 1000;
	private static final int DEFAULT_STARTING_SIZE = DEFAULT_INCREMENT_SIZE;
	
	private static final InsertionSort<Integer> INSERTION_SORT = new InsertionSort<Integer>();
	private static final QuickSort<Integer> QUICK_SORT = new QuickSort<Integer>();
	
	private Experiment _experiment;
	private ParameterSet _parameterSet;
	private Integer[] _ascendingList;
	private Integer[] _descendingList;
	private Integer[] _randomList;
	private long[] _measuredResultForInsertionSort;
	private long[] _measuredResultForQuickSort;
	
	public ParameterSet parameterSet() {
		return this._parameterSet;
	}
	
	public ExperimentManager() {
		this.setParameterSetWithDefaults();
		
	}
	private void prepareExperimentLists() {
		int maxDataSize = this._parameterSet.maxDataSize();
		this._ascendingList = DataGenerator.ascendingOrderList(maxDataSize);
		this._descendingList = DataGenerator.descendingOrderList(maxDataSize);
		this._randomList = DataGenerator.randomOrderList(maxDataSize);
	}
	private void setParameterSetWithDefaults() {
		this._parameterSet = new ParameterSet(DEFAULT_STARTING_SIZE, DEFAULT_NUMBER_OF_SIZE_INCREASING_STEPS, DEFAULT_INCREMENT_SIZE);
	}
	private Integer[] experimentListOfOrder(ListOrder anOrder) {
		switch(anOrder) {
		case Ascending :
			return this._ascendingList;
		case Descending:
			return this._descendingList;
		default:
			return this._randomList;
		}
	}
	public void prepareExperiment(ParameterSet aParameterSet) { //실험준
		
		if(aParameterSet != null) {
			this._parameterSet = aParameterSet;
		}
		this._experiment = new Experiment(this._parameterSet);
		this.prepareExperimentLists();
		
		this.performExperiment(ListOrder.Random);
		this.performExperiment(ListOrder.Random);
	
	
	}
	public long measuredResultForInsertionSortAt(int sizeStep){
		return this._measuredResultForInsertionSort[sizeStep];
	}
	public long measuredResultForQuickSortAt(int sizeStep) {
		return this._measuredResultForQuickSort[sizeStep];
	}
	public void performExperiment(ListOrder anOrder) { //측정실험을 한다.
		Integer[] experimentList = this.experimentListOfOrder(anOrder);
		
		this._measuredResultForInsertionSort =
				this._experiment.durationOfSort(INSERTION_SORT, experimentList);
		this._measuredResultForQuickSort =
				this._experiment.durationOfSort(QUICK_SORT, experimentList);
	}
	
	
}
