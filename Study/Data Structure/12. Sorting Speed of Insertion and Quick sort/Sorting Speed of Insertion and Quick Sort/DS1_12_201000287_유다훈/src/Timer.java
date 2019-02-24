
public final class Timer { //시간측정 클래스
	
	private long _startTime;
	private long _stopTime;
	
	public Timer(){
		
	}
	public void start(){
		this._startTime = System.nanoTime();
	}
	public void stop(){
		this._stopTime = System.nanoTime();
	}
	public long duration(){
		return (this._stopTime - this._startTime);
	}

}
