package hu.barbar.retryHandler.Example;

import java.io.IOException;
import java.net.SocketTimeoutException;

import hu.barbar.retryHandler.RetryHandler;

/**
 * Example app for demonstrate example usage of RetryHander.
 * 
 * @author BbkBarbar
 */
public class ExampleApp {
	
	public static int iForSimulateMultipleTypesOfExceptionsInProblematicCodePart = 0;
	
	public static void main(String[] args) {
		
		
		RetryHandler.ResultAfterMultipleRetries ram = new RetryHandler(3, 1000) {
			
			@Override
			public Object doProblematicJob() throws Exception {
				// Do something that sometimes can throws exceptions..
				
				ExampleApp.iForSimulateMultipleTypesOfExceptionsInProblematicCodePart++;
				
				if(ExampleApp.iForSimulateMultipleTypesOfExceptionsInProblematicCodePart==1){
					throw new IOException("Message of the IOException. :)");
				}else
				if(ExampleApp.iForSimulateMultipleTypesOfExceptionsInProblematicCodePart==2){
					throw new SocketTimeoutException("Message of the SocketTimeoutException. :)");
				}else{
					return new String("This is the result of the problematic code part after a successfully attemp when there were no excption..");
				}
			}
			
		}.run();
		
		if(ram.isDoneSuccessfully()){
			System.out.println("Result: " + ram.getResultString());
			
			System.out.println("\nBut in this case you can get the exception list what was thrown in the prevous attemps:\n");
			System.out.println("Cought exceptions:\n" + ram.getStackTraces());
		}else{
			System.out.println("Cought exceptions:\n" + ram.getStackTraces());
		}
	}
	
}
