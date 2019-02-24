
public class Ban { //학생들의 점수를 계산할 클래스
	private static final int DEFAULT_MAX_SIZE = 100;
	
	private int _maxSize;
	private int _size;
	private Student[] _elements;
	
	public Ban() {
		this (DEFAULT_MAX_SIZE);
	}
	
	public Ban(int givenMaxSize) {
		this._maxSize = givenMaxSize;
		this._elements = new Student[givenMaxSize]; //주어진 수 만큼 배열생성
	}
	
	public int maxSize() {
		return this._maxSize;
	}
	public int size() {
		return this._size;
	}
	
	public boolean isEmpty() {
		return (this._size == 0);
	}
	public boolean isFull() {
		return (this._size == this._maxSize);
	}
	public boolean add(Student aScore) {
		if(this.isFull()) { //만약 꽉 찼으면
			return false; //넣지 말기
		
		} else{//꽉안찼으면
			this._elements[this._size] = aScore; //size번째 공간에 코인을 넣기 
			this._size++; //사이즈 증가
			return true;
		}
	}
	public Student elementAt(int aPosition) {
		return this._elements[aPosition]; //해당 번호의 배열 내부 학생을 반환
	}
	
	public void sortStudentsByScore() { //정렬하기
		int size = this._size;
		
		if( size >= 2) {
			//최소값 위치찾기
			int minLoc = 0;
			for( int i = 1; i < size; i++) {
				if(this._elements[i].score() < this._elements[minLoc].score()) {
					minLoc = i;
				}
			}
			this.swap(minLoc, size-1); //최소값을 원소구간의 맨 끝으로 보냄.
			quickSortRecursively(0, size-2);//정렬시작
		}
		
	}
	public int minScore() {
		int left = 0;
		int right = this._size-1;
		return this.minScoreRecursively(left, right);
		
	}
	public int maxScore() {
		int left = 0;
		int right = this._size-1;
		return this.maxScoreRecursively(left, right);
	}
	public float averageScore() {
		float sumOfScore = (float) sumOfScoresRecursively(0, this._size-1);
		float average = sumOfScore / (float) this._size;
		
		return average;
		
	}
	public int numberOfStudentAboveAverage() { //평균 이상인 학생수
		float average = averageScore();
		float score;
		int numberOfStudentsAboveAverage = 0;
		
		for(int i = 0; i< this._size; i++) {
			score = (float) this._elements[i].score();
			if(score >= average) {
				numberOfStudentsAboveAverage++;
			}
		}
		return numberOfStudentsAboveAverage;
		
	}
	public GradeCounter countGrades() { //학점별 학생수 계산
		char currentGrade;
		GradeCounter gradeCounter = new GradeCounter();
		
		for (int i = 0; i < this._size; i++) {
			currentGrade = this.scoreToGrade(this._elements[i].score()); //점수를 등급화한 값을 저장
			gradeCounter.count(currentGrade);
		}
		
		return gradeCounter;
		
	}
	private char scoreToGrade(int aScore) {
		if(aScore >= 90) {
			return 'A';
		} else if (aScore >= 80) {
			return 'B';
		} else if (aScore >= 70) {
			return 'C';
		} else if (aScore >= 60) {
			return 'D';
		} else {
			return 'F';
		}
		
	}
	private void swap(int positionA, int positionB) {
		Student temp = this._elements[positionA];
		this._elements[positionA] = this._elements[positionB];
		this._elements[positionB] = temp;
		
	}
	private void quickSortRecursively(int left, int right) {
		if (left< right) {
			int mid = this.partition(left, right);
			this.quickSortRecursively(left, mid-1);
			this.quickSortRecursively(mid+1, right);
		}
	
	}
	private int partition (int left, int right) {
		int pivot = left;
		int toRight = left;
		int toLeft = right+1;
		do {
			do{toRight++;} while (this._elements[pivot].score() > this._elements[toRight].score());
			do{toLeft--;} while(this._elements[pivot].score() < this._elements[pivot].score());
			if(toRight < toLeft) {
				swap(toRight, toLeft);
			}
			
		} while (toRight <toLeft);
		swap(pivot, toLeft);
		return pivot;
		
	}
	private float sumOfScoresRecursively(int left, int right) {
		if (left > right) {
			return 0;
		} else {
			return (this._elements[left].score() + this.sumOfScoresRecursively(left+1, right));
		}
		
	}
	private int maxScoreRecursively(int left, int right) {
		int maxScoreOfLeft;
		int maxScoreOfRight;
		int mid;
		if(left == right) {
			return this._elements[left].score();
		} else {
			mid = (left + right) /2;
			maxScoreOfLeft = maxScoreRecursively(left, mid);
			maxScoreOfRight = maxScoreRecursively(mid+1, right);
			
			if(maxScoreOfLeft >= maxScoreOfRight) {
				return maxScoreOfLeft;
			} else
				return maxScoreOfRight;
		}
		
	}
	private int minScoreRecursively(int left, int right) {
		int minScore ;
		if (left == right) {
			return this._elements[left].score();
		} else {
			minScore = minScoreRecursively(left+1, right);
			if(this._elements[left].score() <= minScore) {
				return this._elements[left].score();
			} else {
				return minScore;
			}
		}
			
		
	}
	
	
	}


