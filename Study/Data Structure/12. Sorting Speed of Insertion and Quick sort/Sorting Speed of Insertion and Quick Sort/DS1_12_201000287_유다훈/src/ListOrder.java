
public enum ListOrder {
	
	Ascending,
	Descending,
	Random;
	//객체들임.
	
	public static String[] ORDER_NAMES = {"오름차순", "내림차순", "무작위"};
	
	
	
	public String orderName() {
		return ListOrder.ORDER_NAMES[this.ordinal()];
	} //Enum안에서의 순서를 정수로 얻을 수 있다.
	
	

}
