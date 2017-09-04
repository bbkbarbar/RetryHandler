# RetryHandler
is an object what can be used for easy handling that case 
<br> when there need to handle several attempts to re-run a potentially problematic code part

## Example usage
```java
int MAX_RETRY_COUNT = 3;
int DELAY_IN_MS_BETWEEN_RETRIES = 1000;

RetryHandler.ResultAfterMultipleRetries ram = new RetryHandler(MAX_RETRY_COUNT, DELAY_IN_MS_BETWEEN_RETRIES) {
			
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
```
