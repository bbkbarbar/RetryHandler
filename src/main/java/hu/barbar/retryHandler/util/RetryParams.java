package hu.barbar.retryHandler.util;

public class RetryParams {
	
	private int maxRetryCount = 0;
	
	private long delayInMs = 0;
	
	public RetryParams(int maxRetryCount, long delayInMs) {
		this.maxRetryCount = (maxRetryCount<0?1:maxRetryCount);
		this.delayInMs = (delayInMs<0?0:delayInMs);
	}
	
	public RetryParams(long maxRetryCount, long delayInMs) {
		this.maxRetryCount = (int) (maxRetryCount<0?1:maxRetryCount);
		this.delayInMs = (delayInMs<0?0:delayInMs);
	}
	
	public int getMaxRetryCount(){
		return this.maxRetryCount;
	}
	
	public long getDelayInMs(){
		return this.delayInMs;
	}
	
	public String toString(){
		return "Max count: " + this.maxRetryCount + " Delay: " + this.delayInMs + "ms (Timeout: " + (this.delayInMs*this.maxRetryCount)/1000 + "s)";
	}
	
}
