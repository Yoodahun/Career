package binaryTreeTraversals;
import java.util.Scanner;

public class AppView {
	private Scanner _scanner;
	
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	public void output(String aString) {
		System.out.print(aString);
	}
	public void outputLine(String aString) {
		System.out.println(aString);
	}

}
