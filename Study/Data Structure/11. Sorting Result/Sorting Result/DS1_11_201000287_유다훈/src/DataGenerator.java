import java.util.Random;

public final class DataGenerator { //객체를 생성하지 않는 클래스
	private DataGenerator() {
	
	}
	//모든 상수, 변수, 함수는 static으로 선언
	
	public static Integer[] ascendingOrderList(int aSize){
		Integer[] list = null;
		
		if(aSize > 0) {
			list = new Integer[aSize];
			
			for (int i = 0; i < aSize; i++) {
				list[i] = i;
			}
		}
		return list;
	}
	public static Integer[] descendingOrderList(int aSize) {
		Integer[] list = null;
		int maxValue = aSize-1;
		if(aSize > 0) {
			list = new Integer[aSize];
			
			for (int i = 0; i < aSize; i++) {
				list[i] = maxValue;
				maxValue -= 1;
			}
		}
		return list;
	}
	
	public static Integer[] randomOrderList (int aSize) {
		Integer[] list = null;
		if( aSize>0) {
			list = new Integer[aSize];
			for(int i =0; i< aSize; i++) {
				list[i] = i;
			}
			
			//각 원소list[i]에 대해 무작위 위치 j를 생성하여 리스트 i와 j를 맞바꾼다.
			Random random = new Random();
			for(int i=0; i <aSize; i++) {
				int j = random.nextInt(aSize);
				Integer temp = list[i];
				list[i] = list[j];
				list[j] = temp;
			}
		}
		return list;
	}
	
	
}
