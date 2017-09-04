package hu.barbar.retryHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;


/**
 * RetryHandler is an object what can be used for easy handling that case 
 * <br> when there need to handle several attempts to re-run a potentially problematic code part
 * 
 * @author BbkBarbar
 */
public abstract class RetryHandler {
	
	private static final int UNDEFINED = -1;
	
	private int maxRetryCount = UNDEFINED;
	
	private int retryCount = 0;
	
	private long delayInMs = 0;
	
	
	public class ResultAfterMultipleRetries {
		
		private Object resultObject = null;
		
		private boolean successfullyDone = false;
		
		private ArrayList<Exception> listOfCoughtExceptions = null;
		
		
		public ResultAfterMultipleRetries() {
			this.listOfCoughtExceptions = new ArrayList<Exception>();
			this.successfullyDone = false;
			this.resultObject = null;
		}
		
		public void addCoughtException(Exception e){
			this.listOfCoughtExceptions.add(e);
		}

		public boolean isDoneSuccessfully() {
			return this.successfullyDone;
		}
		
		public void setResultObject(Object obj){
			this.resultObject = obj;
		}
		
		public Object getResultObject(){
			return this.resultObject;
		}

		public void markAsSuccessfullyDone() {
			this.successfullyDone = true;
		}

		public String getResultString() {
			return (String) this.getResultObject();
		}
		
		public String getStackTraces(){
			String s = "";
			for(int i=0; i<this.listOfCoughtExceptions.size(); i++){
				StringWriter errors = new StringWriter();
				listOfCoughtExceptions.get(i).printStackTrace(new PrintWriter(errors));
				s += errors.toString();
				if(i < this.listOfCoughtExceptions.size()-1){
					s+="\n";
				}
			}
			return s;
		}
		
	}
	
	
	public RetryHandler(int maxRetryAttemps, long delayBetweenRetries){
		this.maxRetryCount = (maxRetryAttemps<1?1:maxRetryAttemps);
		this.delayInMs = (delayBetweenRetries<0?0:delayBetweenRetries);
	}
	
	public abstract Object doProblematicJob() throws Exception;
	
	public ResultAfterMultipleRetries run(){
		
		ResultAfterMultipleRetries res = new ResultAfterMultipleRetries();
		
		while( !res.isDoneSuccessfully() && this.retryCount < this.maxRetryCount ){
		
			try {
				Object obj = doProblematicJob();
				if(obj != null){
					res.setResultObject(obj);
					res.markAsSuccessfullyDone();
				}
			} catch (Exception e) {
				retryCount++;
				res.addCoughtException(e);
			}
			
			if( !res.successfullyDone ){
				try {
					Thread.sleep(this.delayInMs);
				} catch (InterruptedException e) {
					e.printStackTrace();
					// Do not need to handle exception if sleep function throws...
				}
			}
		
		}
		return res;
		
	}

}
