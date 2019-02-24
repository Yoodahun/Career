
public class InsertionSort<E extends Comparable<E>> extends Sort<E> {
	public InsertionSort() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	@Override
	public boolean sort(E[] aList, int aSize) {
		// TODO Auto-generated method stub
		if ((aSize < 1) || (aSize > aList.length)) {
			return false;
		} //사이즈가 유효한값이 아니라면 false;
		
		int minLoc = 0; //c최소값
		for(int i = 1 ; i <aSize; i++) {
			if(aList[i].compareTo(aList[minLoc]) < 0) {
				//1부터 aSize까지 i번째 배열값과 최소값째 배열과 비교하여 i번째 배열값이 작다면
				minLoc = i; //그 값을 최소값으로 다시 저장.
				
			}
		}
		this.swap(aList, 0, minLoc); //0과 최소값의 위치를 교환
		
		for(int i = 2; i <aSize; i++) { 
			E insertedElement = aList[i]; //삽입할 i번째 데이터
			int insertionLoc = i-1; //삽입위치는 i-1
			
			while (aList[insertionLoc].compareTo(insertedElement) > 0) {
				//삽입위치의 데이터가 삽입할 데이터보다 큰 경우 계속반복
				aList[insertionLoc+1] = aList[insertionLoc];
				//삽입위치+1번째 데이터에 삽입위치번째 데이터 삽입.
				insertionLoc--; //삽입위치 감소
			}
			aList[insertionLoc+1] = insertedElement;
			//삽입위치 +1값을 삽입할 데이터로.
		}
		return true;

	}

}
