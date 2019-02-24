import java.util.Random;

//위치찾는 문제가 생김. 시간이 걸림.
//나중에 최솟값, 최대값을 찾을때는 이득을 보는 것임.
//내 상황에 맞게 해야함
//전체갯수에 비례하는 시간이 걸릴수박에없음. n에 비례하는 시간.O(n)
//최대값은 n과 무관하게 알 수있음. O(1)
//사이즈가 작으면 오차가 심함.


public class PerformanceMeasurement {
	private static final int MAX_TEST_SIZE = 50000;
	private static final int NUMBER_OF_TESTS = 5;
	private static final int FIRST_TEST_SIZE = 10000;
	private static final int SIZE_INCREMENT = 10000;
	
	private int _maxTestSize;
	private int _numberOfTests;
	private int _firstTestSize;
	private int _sizeIncrement;
	
	private int [] _data;
	private TestResult [] _testResults;
	
	public PerformanceMeasurement() {
		this._maxTestSize = this.MAX_TEST_SIZE;
		this._numberOfTests = this.NUMBER_OF_TESTS;
		this._firstTestSize = this.FIRST_TEST_SIZE;
		this._sizeIncrement = this.SIZE_INCREMENT;
		
		this._data = new int[this.MAX_TEST_SIZE];
		this._testResults = new TestResult[this.NUMBER_OF_TESTS];
	}
	
	public PerformanceMeasurement(int givenMaxTestSize, int givenNumberOfTest, int givenFirstTestSize, int givenSizeIncrement) {
		this._maxTestSize = givenMaxTestSize;
		this._numberOfTests = givenNumberOfTest;
		this._firstTestSize = givenFirstTestSize;
		this._sizeIncrement = givenSizeIncrement;
		
		this._data = new int[givenMaxTestSize];
		this._testResults = new TestResult[givenNumberOfTest];
		
	}
	
	public int numberOfTest() {
		return this._numberOfTests;
	}
	public TestResult[] testResults() {
		return this._testResults;
	}
	
	public void generateData() {
		int i = 0;
		Random random = new Random();
		
		while (i < this._maxTestSize) {
			this._data[i] = random.nextInt(this._maxTestSize);
			i++;
		}
	}
	
	public void testSortedArrayBag() {
		SortedArrayBag bag;
		Coin maxCoin;
		
		int testSize;
		long timeForAdd, timeForMax;
		long start, stop;
		int testDataCount;
		int testCount = 0;
		
		testSize = this._firstTestSize; // 10000
		while(testCount < this._numberOfTests) {
			
			bag = new SortedArrayBag(testSize);
			testDataCount = 0;
			timeForAdd = 0;
			timeForMax = 0;
			
			while(testDataCount < testSize) {
				Coin coin = new Coin(this._data[testDataCount]);
				start = System.nanoTime() ;
				bag.add(coin);
				stop = System.nanoTime() ;
				timeForAdd += (stop - start);
				
				start = System.nanoTime() ;
				maxCoin = bag.maxElement();
				stop = System.nanoTime() ;
				timeForMax += (stop - start);
				testDataCount ++;
				
			}
			this._testResults[testCount] = new TestResult(testSize, timeForAdd, timeForMax);
			testSize += this._sizeIncrement;
			testCount++;
			
		}
		
	}
	public void testUnsortedArrayBag() {
		UnsortedArrayBag bag;
		Coin maxCoin;
		
		int testSize;
		long timeForAdd, timeForMax;
		long start, stop;
		int testDataCount;
		int testCount = 0;
		
		testSize = this._firstTestSize; // 10000
		while(testCount < this._numberOfTests) {
			
			bag = new UnsortedArrayBag(testSize);
			testDataCount = 0;
			timeForAdd = 0;
			timeForMax = 0;
			
			while(testDataCount < testSize) {
				Coin coin = new Coin(this._data[testDataCount]);
				start = System.nanoTime();
				bag.add(coin);
				stop = System.nanoTime();
				timeForAdd += (stop - start);
				
				start = System.nanoTime() ;
				maxCoin = bag.maxElement();
				stop = System.nanoTime();
				timeForMax += (stop - start);
				testDataCount ++;
				
			}
			this._testResults[testCount] = new TestResult(testSize, timeForAdd, timeForMax);
			testSize += this._sizeIncrement;
			testCount++;
			
		}
		
		
	}
	public void testSortedLinkedBag() {
		SortedLinkedBag bag;
		Coin maxCoin;
		
		int testSize;
		long timeForAdd, timeForMax;
		long start, stop;
		int testDataCount;
		int testCount = 0;
		
		testSize = this._firstTestSize; // 10000
		while(testCount < this._numberOfTests) {
			
			bag = new SortedLinkedBag();
			testDataCount = 0;
			timeForAdd = 0;
			timeForMax = 0;
			
			while(testDataCount < testSize) {
				Coin coin = new Coin(this._data[testDataCount]);
				start = System.nanoTime() ;
				bag.add(coin);
				stop = System.nanoTime();
				timeForAdd += (stop - start); 
				
				start = System.nanoTime();
				maxCoin = bag.maxElement();
				stop = System.nanoTime() ;
				timeForMax += (stop - start);
				testDataCount ++;
				
			}
			this._testResults[testCount] = new TestResult(testSize, timeForAdd, timeForMax);
			testSize += this._sizeIncrement;
			testCount++;
			
		}
		
		
	}
	public void testUnsortedLinkedBag() {
		UnsortedLinkedBag bag;
		Coin maxCoin;
		
		int testSize;
		long timeForAdd, timeForMax;
		long start, stop;
		int testDataCount;
		int testCount = 0;
		
		testSize = this._firstTestSize; // 10000
		while(testCount < this._numberOfTests) {
			
			bag = new UnsortedLinkedBag();
			testDataCount = 0;
			timeForAdd = 0;
			timeForMax = 0;
			
			while(testDataCount < testSize) {
				Coin coin = new Coin(this._data[testDataCount]);
				start = System.nanoTime() ;
				bag.add(coin);
				stop = System.nanoTime() ;
				timeForAdd += (stop - start);
				
				start = System.nanoTime() ;
				maxCoin = bag.maxElement();
				stop = System.nanoTime() ;
				timeForMax += (stop - start);
				testDataCount ++;
				
			}
			this._testResults[testCount] = new TestResult(testSize, timeForAdd, timeForMax);
			testSize += this._sizeIncrement;
			testCount++;
			
		}
		
	}
	

}






