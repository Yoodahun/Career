
public class AppController {
	private AppView _appView ;
	private Ban _ban;
	
	public AppController() {
		this._appView = new AppView();
	}
	public void run() {
		this.showMessage(MessageID.Notice_StartProgram);
		
		this.inputAndStoreStudents();
		
		if(this._ban.isEmpty()) {
			this.showMessage(MessageID.Error_NoInputScores);
		} else {
			this.showStatics();
			
			this._ban.sortStudentsByScore();
			this.showStudentsSortedByScore();
		}
		this.showMessage(MessageID.Notice_EndProgram);
	}
	
	
	private boolean inputAndStoreStudents() { //Ban객체에 저장
		this.showMessage(MessageID.Notice_StartMenu);
		
		int score;
		boolean storingAStudentWasSuccessful = true;
		this._ban = new Ban();
		
		while(storingAStudentWasSuccessful && this._appView.inputDoesContinueToInputNextStudent()) {
			score = this._appView.inputScore();
			
			if(score < 0 || score > 100) {
				this.showMessage(MessageID.Error_InvalidScore);
			} else {
				Student aStudent = new Student(score);
				this._ban.add(aStudent);
			}	
		}
		this.showMessage(MessageID.Notice_EndMenu);
		return storingAStudentWasSuccessful;
		
	}
	private void showStatics() { //Ban이 성적을 처리한 후 그 결과를 얻어서 출력
		this._appView.outputAverageScore(this._ban.averageScore());
		this._appView.outputNumberOfStudentsAboveAverage(this._ban.numberOfStudentAboveAverage());
		this._appView.outputMaxScore(this._ban.maxScore());
		this._appView.outputMinScore(this._ban.minScore());
		
		GradeCounter gradeCounter = this._ban.countGrades();
		//학점 별 학생수는Ban객체로 부터 GradeCounter 객체형태로 얻는다
		this._appView.outputGradeCountFor('A', gradeCounter.numberOfA());
		this._appView.outputGradeCountFor('B', gradeCounter.numberOfB());
		this._appView.outputGradeCountFor('C', gradeCounter.numberOfC());
		this._appView.outputGradeCountFor('D', gradeCounter.numberOfD());
		this._appView.outputGradeCountFor('F', gradeCounter.numberOfF());
		
	}
	
	private void showStudentsSortedByScore() { //성적순으로 정렬된 학생 정보를 출력한다.
		this.showMessage(MessageID.Show_SortedStudentList);
		
		for(int position = 0; position < this._ban.size(); position++) {
			this._appView.outputStudentInfo(this._ban.elementAt(position).score());
		}
		
	}
	
	
	
	private void showMessage(MessageID aMessage) {
		switch(aMessage) {
		case Notice_StartProgram:
			this._appView.outputMessage("<<성적 처리를 시작합니다.>>\n");
			break;
		case Notice_StartMenu:
			
			break;
		case Notice_EndMenu:
			this._appView.outputMessage("[성적 입력을 종료합니다.]\n\n");
			break;
		case Notice_EndProgram:
			this._appView.outputMessage("\n<< 성적 처리를 종료합니다 >>");
			break;
		case Error_InvalidScore:
			this._appView.outputMessage("ERROR : 0보다 작거나 100보다 커서, 정상적인 정수가 아닙니다.\n");
			break;
		case Error_NoInputScores:
			this._appView.outputMessage("성적을 입력해주세요.");
			break;
		case Error_WrongMenu:
			this._appView.outputMessage("잘못된 메뉴입니다.");
			break;
		case Show_SortedStudentList:
			this._appView.outputMessage("\n학생들의 성적순 목록입니다.\n");
			break;
			default:
				break;
		}
	}
}
