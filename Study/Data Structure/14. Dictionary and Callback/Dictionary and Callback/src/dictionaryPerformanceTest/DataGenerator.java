package dictionaryPerformanceTest;
import java.util.Random;

public final class DataGenerator {
	private DataGenerator(){};
	
	public static int[] ascendingOrderList(int aSize){
		int[] list = null;
		
		if(aSize > 0) {
			list = new int[aSize];
			
			for (int i = 0; i < aSize; i++) {
				list[i] = i;
			}
		}
		return list;
	}
	public static int[] descendingOrderList(int aSize) {
		int[] list = null;
		int maxValue = aSize-1;
		if(aSize > 0) {
			list = new int[aSize];
			
			for (int i = 0; i < aSize; i++) {
				list[i] = maxValue;
				maxValue -= 1;
			}
		}
		return list;
	}
	
	public static int[] randomOrderList (int aSize) {
		int[] list = null;
		if( aSize>0) {
			list = new int[aSize];
			for(int i =0; i< aSize; i++) {
				list[i] = i;
			}
			
			//각 원소list[i]에 대해 무작위 위치 j를 생성하여 리스트 i와 j를 맞바꾼다.
			Random random = new Random();
			for(int i=0; i <aSize; i++) {
				int j = random.nextInt(aSize);
				int temp = list[i];
				list[i] = list[j];
				list[j] = temp;
			}
		}
		return list;
	}

}
