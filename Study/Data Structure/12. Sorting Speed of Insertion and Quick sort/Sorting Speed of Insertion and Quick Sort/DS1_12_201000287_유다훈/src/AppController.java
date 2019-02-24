
public class AppController {
	private AppView _appView;
	private ExperimentManager _manager;
	
	public AppController() {
		this._appView = new AppView();
		this._manager = new ExperimentManager();
	}
	private void showTableTitle(ListOrder anOrder){
		this._appView.outputLine(">" + anOrder.orderName() + "데이터를 사용하여 실행한 측정");
		
	}
	private void showTableHead() {
		this._appView.outputLine(
				String.format("%8s", "") +
				String.format("%16s", "<Insertion Sort>") +
				String.format("%16s", "<Quick Sort>")
				);
	}
	private void showMeasuredResult() {
		int startingSize = this._manager.parameterSet().startingSize();
		int incrementSize = this._manager.parameterSet().incrementSize();
		int numberOfSteps = this._manager.parameterSet().numberOfSizeIncreasingSteps();
		
		for(int step = 0; step < numberOfSteps; step++) {
			int sortingSize = startingSize + (incrementSize * step) ;
			this._appView.outputLine(
					"[" + String.format("%5d", sortingSize) + "]" +
					String.format("%16d", this._manager.measuredResultForInsertionSortAt(step)) +
					String.format("%16d", this._manager.measuredResultForQuickSortAt(step))
					
					);
		}
	}
	
	private void measureAndShowResultFor(ListOrder anOrder) {
		//주어진 anOrder에 대하여 성능 측정을 하고 결과를 보여준다.
		this.showTableTitle(anOrder);
		this.showTableHead();
		this.showMeasuredResult();
		this._appView.outputLine("");
	}
	public void run() {
		this._appView.outputLine("<< 정렬 성능 비교 프로그램을 시작합니다. >>");
		this._appView.outputLine("");
		
		this._appView.outputLine(">> 2가지 정렬의 성능 비교: 삽입, 퀵 <<");
		
		this._manager.prepareExperiment(null); //기본설정값 부여
		this._manager.performExperiment(ListOrder.Ascending);
		this.measureAndShowResultFor(ListOrder.Ascending);
		this._manager.performExperiment(ListOrder.Descending);
		this.measureAndShowResultFor(ListOrder.Descending);
		this._manager.performExperiment(ListOrder.Random);
		this.measureAndShowResultFor(ListOrder.Random);
		
		
		this._appView.outputLine("<< 정렬 성능 비교 프로그램을 종료합니다 >>");
	}
	
}
