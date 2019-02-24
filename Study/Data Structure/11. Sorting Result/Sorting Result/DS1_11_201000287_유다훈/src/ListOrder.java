
public enum ListOrder {
	
	Ascending,
	Descending,
	Random;
	
	public static String[] ORDER_NAMES = {"오름차순", "내림차순", "무작위"};
	
	public String orderName() {
		return ListOrder.ORDER_NAMES[this.ordinal()];
	}
	
	

}
