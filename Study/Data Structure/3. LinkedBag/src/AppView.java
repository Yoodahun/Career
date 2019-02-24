import java.util.Scanner;

public class AppView {
	
	private Scanner _scanner; 
	
	public AppView() {
		this._scanner = new Scanner(System.in); 
	}
	
	public int inputInt() {
	
		return _scanner.nextInt(); //코인을 입력받음.
	}
	
	public void outputResult(int aTotalCoinSize, int aMaxCoinValue, int aSumOfCoinValue) {
		System.out.println("총 코인 : " +aTotalCoinSize); // 총 코인 출력
		System.out.println("가장 큰 코인 : " +aMaxCoinValue); //가장 큰 코인
		System.out.println("코인의 합 : " + aSumOfCoinValue); //총 코인의 합
		
	}
	
	public void outputMessage(String aMessageString) {
		System.out.print(aMessageString); //메세지 출력
		
		
	}
	public void outputSearch(int aSearchValue, int aSearchedSize) {
		System.out.println(aSearchValue+"코인은 " + aSearchedSize + "개 존재합니다."); //검색하고 싶은 코인이 몇개 있는지
		
	}
	public void outputRemove(int removedCoin) {
		System.out.println("삭제된 코인 : " +removedCoin);
	}

}

