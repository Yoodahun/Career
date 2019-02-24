package dictionaryPerformanceTest;

public enum ListOrder {
	
	Ascending,
	Descending,
	Random;
	//객체들임.
	
	public static String[] STRINGS_IN_KOREAN = new String[] {"오름차순", "내림차순", "무작위"};
	
	
	
	public String toStringInKorean() {
		return ListOrder.STRINGS_IN_KOREAN[this.ordinal()];
	}

}
