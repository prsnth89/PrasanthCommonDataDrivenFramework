package com.prasanth.report.html;

public class Steps {

    public synchronized String getTcID() {
        return tcID;
}
public synchronized void setTcID(String tcID) {
        this.tcID = tcID;
}

public synchronized String getStepName() {
        return stepName;
}
public synchronized void setStepName(String stepName) {
        this.stepName = stepName;
}
public synchronized String getStepDescription() {
        return stepDescription;
}
public synchronized void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
}
public synchronized String getExpectedResult() {
        return expectedResult;
}
public synchronized void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
}
public synchronized String getActualResult() {
        return actualResult;
}
public synchronized void setActualResult(String actualResult) {
        this.actualResult = actualResult;
}

/*public synchronized String getResult() {
        return result;
}
public synchronized void setResult(String result) {
        this.result = result;
}*/


public synchronized void setTestData(String testData) {
        this.testData=testData;
}

public synchronized String getTestData() {
        return testData;
}

public String getPageName() {
        return pageName;
}
public synchronized void setPageName(String pageName) {
        this.pageName = pageName;
}
public synchronized Status getResult() {
        return result;
}
public synchronized void setResult(Status result) {
        this.result = result;
}


private String tcID;
private String stepName;
private String stepDescription;
private String expectedResult;
private String actualResult;
//private String result;
private String testData;

private Status result;

private String pageName;

private String screenshotName;

private String testCaseDescription;

private String executionTime;


public String getExecutionTime() {
        return executionTime;
}
public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
}
public String getTestCaseDescription() {
        return testCaseDescription;
}
public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
}
public synchronized String getScreenshotName() {
        return screenshotName;
}
public synchronized void setScreenshotName(String screenshotName) {
        this.screenshotName = screenshotName;
}



}
