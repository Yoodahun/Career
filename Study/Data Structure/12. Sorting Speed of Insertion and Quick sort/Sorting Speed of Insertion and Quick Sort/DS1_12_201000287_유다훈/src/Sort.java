
public abstract class Sort<E> {
	protected Sort(){ //추상클래스의 상속자와 변수, 멤버함수는 다 프로텍티드로 선언해야.
		
	}
	
	protected void swap(E[] aList, int i, int j) {
		E tempElement = aList[i];
		aList[i] = aList[j];
		aList[j] = tempElement;
	}
	
	public abstract boolean sort(E[] aList, int aSize) ;
	//상속받는 클래스에서 반드시 구현해야할 메소
	
}
