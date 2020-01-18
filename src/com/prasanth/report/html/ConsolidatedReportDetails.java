package com.prasanth.report.html;

public class ConsolidatedReportDetails {

	  private String individualExecutionTime;
      private String testCaseId;
      private String testCaseDescription;
      private Status result;
      private String fileName;

      
      public String getFileName() {
              return fileName;
      }

      public void setFileName(String fileName) {
              this.fileName = fileName;
      }

      public String getIndividualExecutionTime() {
              return individualExecutionTime;
      }

      public void setIndividualExecutionTime(String individualExecutionTime) {
              this.individualExecutionTime = individualExecutionTime;
      }

      public String getTestCaseId() {
              return testCaseId;
      }

      public void setTestCaseId(String testCaseId) {
              this.testCaseId = testCaseId;
      }

      public String getTestCaseDescription() {
              return testCaseDescription;
      }

      public void setTestCaseDescription(String testCaseDescription) {
              this.testCaseDescription = testCaseDescription;
      }

      public Status getResult() {
              return result;
      }

      public void setResult(Status result) {
              this.result = result;
      }


}

	
	

