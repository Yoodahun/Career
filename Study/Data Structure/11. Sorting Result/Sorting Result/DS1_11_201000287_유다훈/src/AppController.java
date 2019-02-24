import java.util.Arrays;

public class AppController {
	private static final int DEFAULT_TEST_SIZE = 10000;
	private static final int DEFAULT_FIRST_PART_SIZE = 5;
	private static final InsertionSort<Integer> INSERTION_SORT = new InsertionSort<Integer>();
	private static final QuickSort<Integer> QUICK_SORT = new QuickSort<Integer>();
	
	private AppView  _appView;
	private Integer[] _list;
	private ListOrder _listOrder;
	
	public AppController() {
		this._appView = new AppView();
		
	}
	public void run() {
		this._appView.outputLine("<< 정렬 알고리즘의 정렬 결과를 검증하는 프로그램을 시작합니다. >>");
		this._appView.outputLine("");
		
		this._appView.outputLine("> 정렬 결과의 검증");
		this._appView.outputLine("");
		
		this.validateWithAscendingOrderList();
		this.validateWithDescendingOrderList();
		this.validateWithRandomOrderList();
		
		this._appView.outputLine
		("<< 정렬 알고리즘의 정렬 결과를 검증하는 프로그램을 종료합니다. >>");
		
	}
	private void validateWithAscendingOrderList() {//오름차순 데이터생성 및 출력
		this._listOrder = ListOrder.Ascending;
		this._list = DataGenerator.ascendingOrderList(DEFAULT_TEST_SIZE);
		this.showFirstPartOfDataList();
		this.validateSortsAndShowResult();
		
	}
	private void validateWithDescendingOrderList() {//내림차순 데이터생성 및 출력
		this._listOrder = ListOrder.Descending;
		this._list = DataGenerator.descendingOrderList(DEFAULT_TEST_SIZE);
		this.showFirstPartOfDataList(); //앞부분출
		this.validateSortsAndShowResult(); //결과출력
		
	}
	private void validateWithRandomOrderList() {//랜덤순서 데이터생성 및 출력
		this._listOrder = ListOrder.Random;
		this._list = DataGenerator.randomOrderList(DEFAULT_TEST_SIZE);
		this.showFirstPartOfDataList();
		this.validateSortsAndShowResult();
		
	}
	private void showFirstPartOfDataList() {
		this._appView.output
		("[" + this._listOrder.orderName() + "리스트] 의 앞 부분: ");
		for (int i = 0; i < this.DEFAULT_FIRST_PART_SIZE; i++) {
			this._appView.output(String.valueOf(this._list[i])+ " ");
		}
		this._appView.outputLine("");
	}
	private void validateSortsAndShowResult(){
		this.validateSort(AppController.INSERTION_SORT);
		this.validateSort(AppController.QUICK_SORT);
		this._appView.outputLine("");
		
	}
	private void validateSort(Sort<Integer> aSort){
		Integer[] list = this.copyList(this._list);
		aSort.sort(list, list.length);
		this.showValidationMessage(aSort,list);
		
	}
	private Integer[] copyList(Integer[] aList) {
		Integer[] copiedList = new Integer[aList.length];
		copiedList = Arrays.copyOf(aList, aList.length);
		
		return copiedList;
	}
	private boolean sortedListIsValid(Integer[] aList) {
		for(int i = 0; i < (aList.length-1) ; i++) {
			if(aList[i].compareTo(aList[i+1]) > 0) {
				return false; //내림차순
			}
		}
		return true;//오름차순
	}
	private void showValidationMessage(Sort<Integer> aSort, Integer[] aList){
		this._appView.output
		("[" + this._listOrder.orderName()+ "리스트] 를 [" +
		aSort.getClass().getSimpleName() + "] 한 결과는 ");
		
		if(this.sortedListIsValid(aList)) {
			this._appView.output("올바릅니다.");
			this._appView.outputLine("");
		} else {
			this._appView.output("올바르지 않습니다..");
			this._appView.outputLine("");
		}
	}
	
}
