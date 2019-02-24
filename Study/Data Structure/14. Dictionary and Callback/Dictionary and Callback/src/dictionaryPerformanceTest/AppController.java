package dictionaryPerformanceTest;

public class AppController {
	private static final int APP_NUMBER_OF_EXPERIMENTS = 5;
	private static final int APP_FIRST_DATA_SIZE = 10000;
	private static final int APP_SIZE_INCREMENT = 10000;
	
	private AppView _appView;
	private PerformanceMeasurement _measurement;
	
	public AppController() {
		this._appView = new AppView();
	}
	public void run() {
	this._appView.outputLine("<<\"Dictionary\" 의 성능 측정을 시작합니다.>> \n");
	this._measurement = new PerformanceMeasurement(
			APP_NUMBER_OF_EXPERIMENTS, APP_FIRST_DATA_SIZE, APP_SIZE_INCREMENT);
	
	this.showExperimentByListOrderType(ListOrder.Ascending);
	this.showExperimentByListOrderType(ListOrder.Descending);
	this.showExperimentByListOrderType(ListOrder.Random);
	
	this._appView.outputLine("<<\"Dictionary\" 의 성능 측정을 종료합니다. >>\n");
	
	

	}
	private void showExperimentByListOrderType(ListOrder anOrder) {
		this._appView.outputLine("<" + anOrder.toStringInKorean() + " 데이터를 사용한 측정 (단위: micro second) >");
		
		this.showExperimentByDictionaryAndListOrderType
			(new DictionaryBySortedArray<Integer, String>(this._measurement.parameterSet().maxDataSize()
				),anOrder);
		this.showExperimentByDictionaryAndListOrderType
			(new DictionaryBySortedLinkedList<Integer, String>(),anOrder);
		
		this.showExperimentByDictionaryAndListOrderType
			(new DictionaryByBinarySearchTree<Integer, String>(), anOrder);
		
	}
	
	private void showUnitExperimentResult(UnitExperimentResult aResult) {
		this._appView.output(String.format("[%5d]", aResult.experimentSize()));
		this._appView.output(String.format("%12d", (int)aResult.timeForAdd()/1000));
		this._appView.output(String.format("%12d", (int)aResult.timeForSearch()/1000));
		this._appView.output(String.format("%12d", (int)aResult.timeForRemove()/1000));
		this._appView.outputLine("");
	}
	private void showExperimentByDictionaryAndListOrderType
									(Dictionary<Integer, String> aDictionary, ListOrder anOrder){
		this._appView.output("\"" + aDictionary.getClass().getName() + "\"로 구현된 사전의 성능 \n");
		this._appView.output(String.format("%6s", "크기 "));
		this._appView.output(String.format("%11s", "삽입"));
		this._appView.output(String.format("%11s", "검색"));
		this._appView.outputLine(String.format("%11s", "삭제"));
		
		UnitExperimentResult[] results = this._measurement.experimentByDictionaryAndListOrderType(aDictionary, anOrder);
		
		for(int iteration = 0;
				iteration < this._measurement.parameterSet().numberOfExperiments();
				iteration++) {
			this.showUnitExperimentResult(results[iteration]);
		}
		this._appView.outputLine("");
		
	}
	
	
}
