# RetryHandler
is an object what can be used for easy handling that case
<br> when there need to handle several attempts to re-run a potentially problematic code part

### Current version: `1.0.3`

### Maven dependecy:
```pom
	<dependency>
		<groupId>hu.barbar</groupId>
		<artifactId>RetryHandler</artifactId>
		<version>1.0.3</version>
	</dependency>
```

### Change log

#### 1.0.3
* toString method added for RetryParams class

#### 1.0.2
* RetryParams class added

#### 1.0.1
* onTriesFailed() method added

#### 1.0.0
* Initial working version

### Example usage
```java
int MAX_RETRY_COUNT = 3;
int DELAY_IN_MS_BETWEEN_RETRIES = 1000;

RetryParams retryParams = new RetryParams(MAX_RETRY_COUNT, 	DELAY_IN_MS_BETWEEN_RETRIES);

//RetryHandler.ResultAfterMultipleRetries ram = new RetryHandler(MAX_RETRY_COUNT, DELAY_IN_MS_BETWEEN_RETRIES) {
RetryHandler.ResultAfterMultipleRetries ram = new RetryHandler(retryParams) {

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
