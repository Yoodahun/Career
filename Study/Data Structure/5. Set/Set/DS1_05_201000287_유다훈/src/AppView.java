import java.util.Scanner;

public class AppView {
	private Scanner _scanner;
	
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	
	public int inputInt() {
		return Integer.parseInt(this._scanner.next());
	}
	public String inputString() {
		return this._scanner.next();
	}
	public void outputMessage(String aMessage) {
		System.out.print(aMessage);
	}
	public void outputStar(String aStarName, int aX, int aY) {
		System.out.println("X : " + aX +"\n" + "Y : " + aY + "\n"+ "이름 : " + aStarName);
	}
	public void outputStarExistence(String aStarName, int aX, int aY){
		if(aX == 0 && aY == 0) {
		
		System.out.println(aStarName + " 별이 존재합니다.");
		} else if (aStarName == null) {
			System.out.println("("+ aX+", "+aY+")" + "위치에 별이 존재합니다.");
		}
	}
	public void outputNumberOfStars(int aStarCollectorSize) {
		System.out.println(aStarCollectorSize + " 개의 별이 존재합니다.");
		
	}
	
	
	
}
