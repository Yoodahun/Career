import java.util.Scanner;

public class AppView {
	private Scanner _scanner;
	
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	public int inputInt() {
		return Integer.parseInt(this._scanner.nextLine());
	}
	public String inputString() {
		return this._scanner.nextLine();
	}
	
	public char inputCharacter() {
		char element;
		System.out.print("- 문자를 입력하시오 : ");
		element = this.inputString().charAt(0);
		return element;
	}
	public void outputAddedElement(char anElement) {
		System.out.println("[Push] 삽입된 원소는 '" + anElement + "'입니다.");
		
	}
	public void outputStackElement(char anElement) {
		System.out.print(" " + anElement + " ");
		
	}
	public void outputTopElement(char anElement) {
		System.out.println("[Top] Top 원소는 '" + anElement + "'입니다.");
		
	}
	public void outputStackSize(int aStackSize) {
		System.out.println("[Size] 스택에는 현재 " + aStackSize+ "개의 원소가 있습니다.");
		
	}
	public void outputRemove(char anElement) {
		System.out.println("[Pop] 삭제된 원소는 '" + anElement + "'입니다.");
		
	}
	public void outputRemoveN(int numberOfCharsToBeRemoved) {
		
	}
	public void outputResult(int numberOfInputChars, int numberOfIgnoredChars,
							int numberOfAddedChars) {
		System.out.println("---입력된 문자는 모두 " + numberOfInputChars + "개 입니다.");
		System.out.println("---정상 처리된 문자는 모두 " + (numberOfInputChars - numberOfIgnoredChars) + "개 입니다.");
		System.out.println("---무시된 문자는 모두 " + numberOfIgnoredChars + "개 입니다.");
		System.out.println("---삽입된 문자는 모두 " + numberOfAddedChars + "개 입니다.");
		
		
	}
	public void outputMessage (String aMessageString) {
		System.out.print(aMessageString);
	}
 	
	
	
}
