
public class QuickSort<E extends Comparable<E>> extends Sort<E>{
	public QuickSort(){
		
	}
	
	private int pivot(E[] aList, int left, int right) {
		return left;
	}
	private int partition(E[] aList, int left, int right) {
		
			int pivot = pivot(aList, left, right); //중간값. 처음값은 제일 왼쪽으로.
			int toRight = left; //오른쪽으로 이동하는 값. 왼쪽에서 시작.
			int toLeft = right+1; // 왼쪽으로 이동하는 값. 오른쪽에서 시작.
			do {
				do{toRight++;} //일단 오른쪽을 향해서 1칸 증가
				while (aList[toRight].compareTo(aList[pivot]) < aList[pivot].compareTo(aList[toRight]) );
				//중간값의 원소값이  오른쪽으로 한 칸씩 이동하는 원소값보다 클동안 무조건 반복
				do{toLeft--;} //일단 왼쪽을 향해서 1칸 감소
				while(aList[toLeft].compareTo(aList[pivot]) > aList[pivot].compareTo(aList[toLeft]));
				//중간값의 원소값이 작으면??
				
				if(toRight < toLeft) { //만약 왼쪽값보다 오른쪽 값이 크다면
					this.swap(aList, toRight, toLeft); //두 개의 위치를 교환. 값이 큰 쪽이 왼쪽으로 가게끔.
				}
				
			} while (toRight < toLeft); //오른쪽으로 향하는 왼쪽값이 왼쪽으로 향하는 오른쪽값보다 작을동안 계속 실행
			swap(aList, left, toLeft); //중간값과 왼쪽으로 향하는 오른쪽 값을 교체
			return toLeft; //중간값 반환
	}
	private void quickSortRecursively(E[] aList, int left, int right) {
		if (left< right) {
			int mid = this.partition(aList, left, right);
			this.quickSortRecursively(aList, left, mid-1);
			this.quickSortRecursively(aList, mid+1, right);
		}
	}

	@Override
	public boolean sort(E[] aList, int aSize) {
		// TODO Auto-generated method stub
		if(aSize <1 || aSize > aList.length) {
			return false;
		}
		int maxLoc = 0;
		for(int i =1; i < aSize; i++) {
			if(aList[i].compareTo(aList[maxLoc]) > 0) { //맞으면 양수, 틀리면 음수, 같으면 0 과 비교
				maxLoc = i;
			}
		}
		this.swap(aList, maxLoc, aSize-1); //맨뒤에 값으로 최대값을 보낸다.
		this.quickSortRecursively(aList, 0, aSize-2); //실제 정렬은 맨 뒤의 가장 큰 값을 제외한 값까지 정렬한다.
		return true;
	}
	
	
}
