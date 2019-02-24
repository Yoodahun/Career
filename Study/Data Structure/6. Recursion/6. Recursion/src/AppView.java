import java.util.Scanner;

public class AppView {
	private Scanner _scanner;
	
	public AppView(){
		this._scanner = new Scanner(System.in);
	}
	public int inputInt() { //점수를 입력받아 정수형으로 변환하여 리턴
		return Integer.parseInt(this._scanner.nextLine());
	}
	public String inputString() { //문자열 입력을 리턴
		return this._scanner.nextLine();
	}
	public boolean inputDoesContinueToInputNextStudent() {
		//다음 학생을 계속 입력받는 메소드
		
		char answer;
		System.out.print("성적을 입력하려면 'Y' 또는 'y'를, 종료하려면 다른 아무 키나 치시오: ");
		answer = this.inputString().charAt(0); //문자열중 맨 첫번째 알파벳 저장
		
		if((answer == 'Y') || (answer =='y')) {
			return true;
		} else {
			return false;
		}
	}
	
	public int inputScore() { //점수 입력받는 메소드
		int score;
		System.out.print("점수를 입력하시오: ");
		score = this.inputInt();
		return score;
	}
	
	public void outputMessage(String aMessageString){
		System.out.print(aMessageString);
		
	}
	public void outputAverageScore(float anAverageScore){ //평균 점수 출력
		System.out.println("평균 점수는 "+ anAverageScore + " 입니다.");
		
	}
	public void outputNumberOfStudentsAboveAverage(int aNumber){ //평균이상 학생수
		System.out.println("평균 이상인 학생은 모두 " + aNumber + " 명 입니다.");
		
	}
	public void outputMaxScore(int aMaxScore){ //최고점
		System.out.println("최고점은 " + aMaxScore + " 점 입니다.");
		
	}
	public void outputMinScore(int aMinScore){ //최저점
		System.out.println("최저점은 " + aMinScore + " 점 입니다.");
		
	}
	public void outputGradeCountFor(char aGrade, int aCount){
		System.out.println(aGrade + " 학점은 모두 " + aCount + " 명 입니다.");
		//학점 당 학생수
	}
	public void outputStudentInfo(int aScore) { //성적순 목록의 점수반영.
		System.out.println("점수 : " + aScore);
		
	}

}
