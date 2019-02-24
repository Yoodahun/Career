	import java.util.Scanner;

public class AppView {
	private Scanner _scanner;
	
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	public String inputString() {
		return this._scanner.next();
	}
	public int inputInt() {
		System.out.print("> 숫자를 입력하시오 : ");
		return this._scanner.nextInt();
	}
	public char inputCharacter() {
		System.out.print("> 문자를 입력하시오 : ");
		char element =this._scanner.nextLine().charAt(0);
		return  element;
	}
	public int inputNumber() {
		System.out.print("> 숫자를 입력하시오 : ");
		return Integer.parseInt(this._scanner.nextLine());
	}
	public void outputSize(int size) {
		System.out.println("[Length] 리스트에는 현재 " + size + "개가 있습니다.");
		
	}
	public void outputAdd(int anElement){
		System.out.println("[Insert] 삽입된 원소는 " + anElement + "입니다.");
	}
	public void outputRemove(int anElement){
		
		System.out.println("[Delete] 삭제된 원소는 " + anElement + "입니다.");
	}
	public void outputElement(int anElement) {
		System.out.print(anElement + " ");
		
	}
	public void outputMessage(String aMessage) {
		System.out.print(aMessage);
	}

}
