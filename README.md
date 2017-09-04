# RetryHandler
is an object what can be used for easy handling that case 
<br> when there need to handle several attempts to re-run a potentially problematic code part

### Current version: `1.0.0`

### Maven dependecy:
```pom
	<dependency>
		<groupId>hu.barbar</groupId>
		<artifactId>RetryHandler</artifactId>
		<version>1.0.0</version>
	</dependency>
```

### Example usage
```java
int MAX_RETRY_COUNT = 3;
int DELAY_IN_MS_BETWEEN_RETRIES = 1000;

RetryHandler.ResultAfterMultipleRetries ram = new RetryHandler(MAX_RETRY_COUNT, DELAY_IN_MS_BETWEEN_RETRIES) {
			
	@Override
	public Object doProblematicJob() throws Exception {
		
		// Do something that sometimes can throws exceptions..
		objectWhatDoesPotentiallyProblematicCodeParts.doYourJob();
		return objectWhatDoesPotentiallyProblematicCodeParts.getYourResult();
		
	}
	
}.run();

if(ram.isDoneSuccessfully()){
	System.out.println("Result: " + ram.getResult());
	
	System.out.println("\nBut in this case you can get the exception list what was thrown in the prevous attemps:\n");
	System.out.println("Cought exceptions:\n" + ram.getStackTraces());
}else{
	System.out.println("Cought exceptions:\n" + ram.getStackTraces());
}
```
