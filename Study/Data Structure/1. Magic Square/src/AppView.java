import java.util.Scanner;

public class AppView { //표시를 담당하는 클래스.
	private Scanner _scanner; //차수를 입력받는 스캐너.
	
	public AppView() { //생성자.
		this._scanner = new Scanner(System.in); //스캐너 선언.
	}
	
	public int inputOrder() { //차수를 입력받아 리턴하는 메소드.
		// TODO Auto-generated method stub
		this.outputLine("");
		this.output("마방진 차수를 입력하시오 (음수를 입력하면 종료합니다): ");
		
		int order = _scanner.nextInt(); //차수를 입력받아 저장하고 리턴.
		
		return  order;
	}


	public void outputLine(String aString) { //메세지 출력 및 줄바꿈 메소드.
		// TODO Auto-generated method stub
		System.out.println(aString);
		
		
	}

	public void output(String aString) { //메세지 출력 메소드.
		// TODO Auto-generated method stub
		System.out.print(aString);
		
	}
	public void outputTitleWithOrder(int anOrder) { //차수와 함께 문제풀이를 시작하는 것을 알리는 메소드.
		System.out.println("Magic Square Board : Order " + anOrder);
	}
	
	public void outputRowNumber(int aRowNumber) { //가로수 출력.
		System.out.printf("[%3d]", aRowNumber);
	}
	
	public void outputCell (int aCellValue) { //마방진의 내용을 출력하는 메소드.
		System.out.printf("  %3d", aCellValue);
	}

}
