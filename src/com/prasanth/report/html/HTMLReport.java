package com.prasanth.report.html;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.prasanth.engine.BaseTest;
import com.prasanth.utilities.Utils;

public class HTMLReport extends BaseTest {
	
	  private static StringBuffer sBf;
      private static StringBuffer sBfOverallReport;
      private static ArrayList<Steps> stepsDescription;
      private static String executionReport;
      private Steps steps;
      public static int passcount=0;
      public static int failcount=0;
      public static int total=0;
      private static File dir,currentReportDir,htmlReport,screenshotdir,overallReportDir=null;
//      private static Map<String, ArrayList<Map<String, ArrayList<Steps>>>> consolidatedReport=new HashMap<String, ArrayList<Map<String, ArrayList<Steps>>>>();
      //private static ArrayList<File> consolidatedReportDirectory=new ArrayList<File>();
      public static Map<String, ArrayList<ConsolidatedReportDetails>> consolidatedReportDirectories=new LinkedHashMap<String, ArrayList<ConsolidatedReportDetails>>();
      public static ArrayList<ConsolidatedReportDetails> arrConsolidatedReportDetails=new ArrayList<ConsolidatedReportDetails>();        ;

      private ConsolidatedReportDetails consolidatedReportDetails;
      
      private static Map<String, File> setConsolidatedDirectory=new LinkedHashMap<String,File>();
      
      private File directory;
      public File getDirectory() {
              return directory;
      }
      public void setDirectory(File directory) {
              this.directory = directory;
      }
      
      
      
      public HTMLReport(WebDriver driver){
              super(driver);
              stepsDescription =new ArrayList<Steps>();
      //        consolidatedReportDirectory.add(createDirectory());
              setDirectory(createDirectory());
      }
      
      public HTMLReport() {
              stepsDescription =new ArrayList<Steps>();
              setDirectory(createDirectory());
      }
      
      public static void main(String[] args) {
      

              
                      
              
              
      /*        HTMLReport htmlReport=new HTMLReport(driver);
              htmlReport.addStepsInReport("TC001","homepage",  "url", "load url", "url loaded","www.google.com", Status.Pass);
              htmlReport.addStepsInReport("TC001","homepage",  "homepage", "load homepage", "homepage loaded","N/A", Status.Done);
              htmlReport.addStepsInReport("TC001", "loginpage",  "homepage", "click login", "login link clicked", "N/A",Status.Pass);
      */        
      
                      
              //        sBf=htmlReport.rowData(sBf, 1, "Navigate to url", "Should navigate to google.com ", "Naviagated to google", "Pass");
              //        sBf=htmlReport.rowData(sBf, 2, "Verify google page", "Verify google search elememt", "Verified google search elememt", "Fail");
      //        htmlReport.generateFinalHtmlReport("TC001")        ;
              
                              

      }

      public File createDirectory() {
              
      dir=new File(System.getProperty("user.dir")+File.separator+"Reports");
             
      if(!dir.isDirectory()) {
              
              dir.mkdir();
              
      }
      
      executionReport="Prasanth_ExecutionReport_"+Utils.generateDate()+"_"+Utils.getCurrentTime();
      currentReportDir=new File(dir+File.separator+executionReport);
      currentReportDir.mkdir();
      htmlReport=new File(currentReportDir+File.separator+"HTMLReport");
      htmlReport.mkdir();
      screenshotdir=new File(currentReportDir+File.separator+"Screenshot");
      screenshotdir.mkdir();
      
      
      return currentReportDir;
      }
      
      
  public synchronized void createOverallSummaryReportDirectory() {
              sBfOverallReport=new StringBuffer();
              String date=Utils.generateDate();
              String corporateId=Utils.getCorporateId();
              String platform=Utils.platform;
              String environment=Utils.getEnvironment();
              String browserName=Utils.browserName;
              String url=Utils.currentUrl;
              Utils.suiteEndTimeMilliSec=System.currentTimeMillis()+1000l;
              Utils.strSuiteEndTimeMilliSec=Utils.getCurrentTimeWithCustomizedFormat();
              String totalOverallSuiteTime=Utils.getTotalTimeInMinutes(Utils.suiteStartTimeMilliSec, Utils.suiteEndTimeMilliSec);
              
              
          dir=new File(System.getProperty("user.dir")+File.separator+"Reports");
             
      if(!dir.isDirectory()) {
                         dir.mkdir();
              
      }
      
      overallReportDir=new File(dir+File.separator+"Prasanth_OverallExecutionReport_"+Utils.generateDate()+"_"+Utils.getCurrentTime());
      overallReportDir.mkdir();
      
 //     System.out.println("ConsilidatedReportDirectory--"+consolidatedReportDirectory);
//      System.out.println("ConsolidatedReportDirectoriesss----"+consolidatedReportDirectories);
          
//      for(int i=0;i<consolidatedReportDirectory.size();i++) {
//               copyDirectory(consolidatedReportDirectory.get(i), overallReportDir);
//      }
      
      Set<String> testCases=setConsolidatedDirectory.keySet();
      Iterator<String> testCaseIdss=testCases.iterator();
      while(testCaseIdss.hasNext()) {
              String testCaseName=testCaseIdss.next();
              copyDirectory(setConsolidatedDirectory.get(testCaseName).getAbsoluteFile(), overallReportDir);
      }
     
      
      Set<String> testCaseIds=consolidatedReportDirectories.keySet();
      System.out.println(testCaseIds);
      Iterator<String> testCaseId=testCaseIds.iterator();
      int totalTestCase=0;
      int failCount=0;
      int passCount=0;
   
      int k=0;
      while(testCaseId.hasNext()) {
    //          String currentTestCase=testCaseId.next();
              
              String testCaseId1=testCaseId.next();
      //           System.out.println("TestCase--"+testCaseId1);
         //        String testCasefileName = null;
                 try {
                         //testCasefileName=overallReportDir.getAbsolutePath()+File.separator+consolidatedReportDirectory.get(j).getName()+File.separator+"HTMLReport"+File.separator+testCaseId1+".html";
                 //        testCasefileName=consolidatedReportDirectory.get(k).getName()+File.separator+"HTMLReport"+File.separator+testCaseId1+".html";
         //                System.out.println("Consolidated Report Path---"+testCasefileName);
              
                 }
                 catch(Exception e){
                         
                 }
        //  sBfOverallReport=rowDataOfConsolidatedReport(sBfOverallReport,consolidatedReportDirectories.get(testCaseId1).get(totalTestCase).getTestCaseId(),testCasefileName,consolidatedReportDirectories.get(testCaseId1).get(totalTestCase).getTestCaseDescription(),consolidatedReportDirectories.get(testCaseId1).get(totalTestCase).getResult(),consolidatedReportDirectories.get(testCaseId1).get(totalTestCase).getIndividualExecutionTime());
      //        System.out.println("Result Index--"+totalTestCase+"---"+consolidatedReportDirectories.get(testCaseId1).get(k).getResult());
          if(consolidatedReportDirectories.get(testCaseId1).get(k).getResult()==Status.Fail) {
                  failCount=failCount+1;
                    }else {
                             passCount=passCount+1;
                     }
             //   System.out.println("TCID--"+consolidatedReportDirectories.get(testCaseId1).get(totalTestCase).getTestCaseId()+"--Result--"+consolidatedReportDirectories.get(testCaseId1).get(totalTestCase).getResult()+"--Desc--"+consolidatedReportDirectories.get(testCaseId1).get(totalTestCase).getTestCaseDescription()+"--Time--"+consolidatedReportDirectories.get(testCaseId1).get(totalTestCase).getIndividualExecutionTime());
                      totalTestCase=totalTestCase+1;        
                      k=k+1;
      
      }
      
           
      
      sBfOverallReport=createHeaderForOverallSummaryReport(sBfOverallReport,corporateId,platform,environment,browserName,url);
      sBfOverallReport= dashBoardHeaderOfConsolidatedReport(sBfOverallReport,date,totalTestCase,passCount, failCount,Utils.strSuiteStartTimeMilliSec,Utils.strSuiteEndTimeMilliSec,totalOverallSuiteTime);
      
      sBfOverallReport=colHeaderConsolidatedReport(sBfOverallReport);
    
   /*   testCaseIds=consolidatedReportDirectories.keySet();
      System.out.println(testCaseIds);
      testCaseId=testCaseIds.iterator(); */
  /*    int i=0;
      while(testCaseId.hasNext()) {
        
              String testCaseId1=testCaseId.next();
              System.out.println("TestCase--"+testCaseId1);
              sBfOverallReport=rowDataOfConsolidatedReport(sBfOverallReport,consolidatedReportDirectories.get(testCaseId1).get(i).getTestCaseId(),"filename",consolidatedReportDirectories.get(testCaseId1).get(i).getTestCaseDescription(),consolidatedReportDirectories.get(testCaseId1).get(i).getResult(),consolidatedReportDirectories.get(testCaseId1).get(i).getIndividualExecutionTime());
              System.out.println("TCID--"+consolidatedReportDirectories.get(testCaseId1).get(i).getTestCaseId()+"--Result--"+consolidatedReportDirectories.get(testCaseId1).get(i).getResult()+"--Desc--"+consolidatedReportDirectories.get(testCaseId1).get(i).getTestCaseDescription()+"--Time--"+consolidatedReportDirectories.get(testCaseId1).get(i).getIndividualExecutionTime());
              i=i+1;        
      }
        */ 
      
      
      Set<String> testCaseIds001=consolidatedReportDirectories.keySet();
      System.out.println(testCaseIds001);
      Iterator<String> testCaseId001=testCaseIds001.iterator();
      int j=0;
      int totalTestCase1=0;
      int failCount1=0;
      int passCount1=0;
   
    //  int k1=0;
      while(testCaseId001.hasNext()) {
    //          String currentTestCase=testCaseId.next();
              
              String testCaseId1=testCaseId001.next();
                 System.out.println("TestCase--"+testCaseId1);
                 String testCasefileName = null;
                 String arrTestCaseIdSequential=null;
                 try {
                         arrTestCaseIdSequential=consolidatedReportDirectories.get(testCaseId1).get(totalTestCase1).getTestCaseId();
                         if(setConsolidatedDirectory.containsKey(arrTestCaseIdSequential)){
                                 testCasefileName=setConsolidatedDirectory.get(arrTestCaseIdSequential).getName()+File.separator+"HTMLReport"+File.separator+arrTestCaseIdSequential+".html";
                             System.out.println("Testcase file name---"+testCasefileName);
                             System.out.println("Consolidated report---"+consolidatedReportDirectories.get(arrTestCaseIdSequential).get(totalTestCase1).getTestCaseId());
                         sBfOverallReport=rowDataOfConsolidatedReport(sBfOverallReport,consolidatedReportDirectories.get(arrTestCaseIdSequential).get(totalTestCase1).getTestCaseId(),testCasefileName,consolidatedReportDirectories.get(arrTestCaseIdSequential).get(totalTestCase1).getTestCaseDescription(),consolidatedReportDirectories.get(arrTestCaseIdSequential).get(totalTestCase1).getResult(),consolidatedReportDirectories.get(arrTestCaseIdSequential).get(totalTestCase1).getIndividualExecutionTime());
                       System.out.println("Result Index--"+totalTestCase1+"---"+consolidatedReportDirectories.get(arrTestCaseIdSequential).get(totalTestCase1).getResult());
             
                         }else {
                                 System.out.println("Value not found");
                         }
                         //testCasefileName=overallReportDir.getAbsolutePath()+File.separator+consolidatedReportDirectory.get(j).getName()+File.separator+"HTMLReport"+File.separator+testCaseId1+".html";
                         
              
                 }
                 catch(Exception e){
                         
                 }
           if(consolidatedReportDirectories.get(testCaseId1).get(j).getResult()==Status.Fail) {
                  failCount1=failCount1+1;
                    }else {
                             passCount1=passCount1+1;
                     }
                System.out.println("TCID--"+consolidatedReportDirectories.get(testCaseId1).get(totalTestCase1).getTestCaseId()+"--Result--"+consolidatedReportDirectories.get(testCaseId1).get(totalTestCase1).getResult()+"--Desc--"+consolidatedReportDirectories.get(testCaseId1).get(totalTestCase1).getTestCaseDescription()+"--Time--"+consolidatedReportDirectories.get(testCaseId1).get(totalTestCase1).getIndividualExecutionTime());
                      totalTestCase1=totalTestCase1+1;        
                      j=j+1;
      
      }
      sBfOverallReport=closeTable(sBfOverallReport);
      createNewFileForOverallReport(sBfOverallReport);
          sBfOverallReport=null;
              
     
            
      }
      
      
      
      public synchronized StringBuffer createHeader(StringBuffer stringBuffer,String testCaseName,String date,String corporateId, String platform,String environment,String browserName,String url) {
      
              Utils.endTime=Utils.getCurrentTimeWithCustomizedFormat();
              Utils.endTimeMilliSec=System.currentTimeMillis();
              stringBuffer=new StringBuffer();
              stringBuffer.append("<!doctype html>\r\n" + 
                              "<html lang=\"en\">\r\n" + 
                              "   <head>\r\n" + 
                              "    <!-- Required meta tags -->\r\n" + 
                              "    <meta charset=\"utf-8\">\r\n" + 
                              "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
                              "    <!-- Bootstrap CSS -->\r\n" + 
                              "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\r\n" + 
                              "        <link rel=\"stylesheet\" type=\"text/css\" href=\"https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css\">\r\n" + 
                              "        <link rel=\"shortcut icon\" href=\"https://upload.wikimedia.org/wikipedia/commons/f/f1/ING_logo.jpg\" type=\"image/png\" style=\" max-height:10px; max-width:10px;\">\r\n" + 
                              "        <title > Automation Report</title>\r\n" + 
                              "        </head>\r\n" + 
                              "        \r\n" + 
                              "        \r\n" + 
                              "  <body>\r\n" + 
                              "    <div class=\"container\">\r\n" + 
                              "        <div class=\"text-center\">\r\n" + 
                            //  "        <img src=\"https://upload.wikimedia.org/wikipedia/commons/f/f1/ING_logo.jpg\" class=\"rounded\" alt=\"400*400\" class=\"img-thumbnail\" style=\" max-height: 400px; max-width: 400px;\"> \r\n" + 
                              " <img src=\"https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png\" class=\"img-thumbnail\" style=\" max-height: 400px; max-width: 400px;\"> \r\n" + 
                              "        </div>\r\n" + 
                              " \r\n" + 
                              "        <div class=\"row\" >\r\n" + 
                              "        \r\n" + 
                              "      <tr> </tr>\r\n" + 
                              "      <div class=\"col-md-12 border\" style=\"color: black; font-size:14px\">\r\n" + 
                              "      <table class=\"table table-striped table-bordered table-secondary\">\r\n" + 
                              "          <h2><p class=\"text-center\" style=\"background-color: rgb(255,98,0);color: white; font-size:24px\">Prasanth_Automation Execution Report_"+testCaseName+"</p>\r\n" + 
                              "\r\n" + 
                              "           <tr style=\"background-color: rgb(255,98,0);color: white; font-size:14px\">\r\n" + 
                              "            <th scope=\"col\">Date</th>\r\n" + 
                              "        <th scope=\"col\">User</th>\r\n" + 
                              "            <th scope=\"col\">Platform</th>\r\n" + 
                              "        <th scope=\"col\">Environment</th>\r\n" + 
                              "            <th scope=\"col\">Browser</th>\r\n" + 
                              "            <th scope=\"col\">url</th>\r\n" + 
                              "       </tr>\r\n" + 
                              "        \r\n" + 
                              "      <tr>\r\n" + 
                              "       <td>"+date+"</td>\r\n" + 
                              "       <td>"+corporateId +"</td>\r\n" + 
                              "           <td>"+platform+"</td>\r\n" + 
                              "           <td>"+environment+"</td>\r\n" + 
                              "           <td>"+browserName+"</td>\r\n" + 
                              "           <td>"+url+"</td>\r\n" + 
                              "          </tr>");

              return stringBuffer;
                                              
              
              
      }
      
      
      public synchronized StringBuffer createHeaderForOverallSummaryReport(StringBuffer stringBuffer,String corporateId, String platform,String environment,String browserName,String url) {
              
              Utils.endTime=Utils.getCurrentTimeWithCustomizedFormat();
              Utils.endTimeMilliSec=System.currentTimeMillis();
              stringBuffer=new StringBuffer();
              stringBuffer.append("<!doctype html>\r\n" + 
                              "<html lang=\"en\">\r\n" + 
                              "   <head>\r\n" + 
                              "    <!-- Required meta tags -->\r\n" + 
                              "    <meta charset=\"utf-8\">\r\n" + 
                              "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
                              "    <!-- Bootstrap CSS -->\r\n" + 
                              "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\r\n" + 
                              "        <link rel=\"stylesheet\" type=\"text/css\" href=\"https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css\">\r\n" + 
                              "        <link rel=\"shortcut icon\" href=\"https://upload.wikimedia.org/wikipedia/commons/f/f1/ING_logo.jpg\" type=\"image/png\" style=\" max-height:10px; max-width:10px;\">\r\n" + 
                              "        <title > Automation Summary Report</title>\r\n" + 
                              "        </head>\r\n" + 
                              "        \r\n" + 
                              "        \r\n" + 
                              "  <body>\r\n" + 
                              "    <div class=\"container\">\r\n" + 
                              "        <div class=\"text-center\">\r\n" + 
                              "        <img src=\"https://upload.wikimedia.org/wikipedia/commons/f/f1/ING_logo.jpg\" class=\"rounded\" alt=\"400*400\" class=\"img-thumbnail\" style=\" max-height: 400px; max-width: 400px;\"> \r\n" + 
                              "        </div>\r\n" + 
                              "        \r\n" + 
                              "        \r\n" + 
                              " \r\n" + 
                              "        <div class=\"row\" >\r\n" + 
                              "        \r\n" + 
                              "      <tr> </tr>\r\n" + 
                              "      <div class=\"col-md-12 border\" style=\"color: black; font-size:14px\">\r\n" + 
                              "      <table class=\"table table-striped table-bordered table-secondary\">\r\n" + 
                              "          <h2><p class=\"text-center\" style=\"background-color: rgb(255,98,0);color: white; font-size:24px\">Billing_TBMS_Automation Execution Summary Report</p>\r\n" + 
                              "\r\n" + 
                              "           <tr style=\"background-color: rgb(255,98,0);color: white; font-size:14px\">\r\n" + 
                              "            \r\n" + 
                              "        <th scope=\"col\">User</th>\r\n" + 
                              "            <th scope=\"col\">Platforms</th>\r\n" + 
                              "        <th scope=\"col\">Environment</th>\r\n" + 
                              "            <th scope=\"col\">Browser</th>\r\n" + 
                              "            <th scope=\"col\">url</th>\r\n" + 
                              "       </tr>\r\n" + 
                              "        \r\n" + 
                              "      <tr>\r\n" + 
                              "       \r\n" + 
                              "       <td>"+corporateId+"</td>\r\n" + 
                              "           <td>"+platform+"</td>\r\n" + 
                              "           <td>"+environment+"</td>\r\n" + 
                              "           <td>"+browserName+"</td>\r\n" + 
                              "           <td>"+url+"</td>\r\n" + 
                              "          </tr>");

              return stringBuffer;
                                              
              
              
      }
      
      
      private synchronized StringBuffer dashBoardHeaderOfConsolidatedReport(StringBuffer stringBuffer,String date, int totalTestCase, int passCount, int failCount, String startTime, String endTime, String totalTime) {
              return stringBuffer.append("<table class=\"table table-striped table-bordered table-secondary\" >          \r\n" + 
                              "         <tr style=\"background-color: rgb(255,98,0);color: white; font-size:14px\">\r\n" + 
                              "         <th scope=\"col\">Date</th>\r\n" + 
                              "          <th scope=\"col\">Test Test Case</th>\r\n" + 
                              "          <th scope=\"col\">Pass</th>\r\n" + 
                              "          <th scope=\"col\">Fail</th>\r\n" + 
                              "          <th scope=\"col\">Start Time</th>\r\n" + 
                              "          <th scope=\"col\">End Time</th>\r\n" + 
                              "          <th scope=\"col\">Total Time</th>\r\n" + 
                              "        </tr>\r\n" + 
                              "          \r\n" + 
                              "         <tr>\r\n" + 
                              "          <td>"+date+"</td>\r\n" + 
                              "      <td>"+totalTestCase+"</td>\r\n" + 
                              "      <td>"+passCount+"</td>\r\n" + 
                              "          <td>"+failCount+"</td>\r\n" + 
                              "          <td>"+startTime+"</td>\r\n" + 
                              "          <td>"+endTime+"</td>\r\n" + 
                              "          <td>"+totalTime+"</td>\r\n" + 
                              "          </tr>\r\n" + 
                              "   </table>");
      }
      
      
      
      
      
      private synchronized StringBuffer dashBoardOfSingleTestScript(StringBuffer stringBuffer, int totalTestSteps, int passCount, int failCount, String startTime, String endTime, String totalTime) {
              return stringBuffer.append("<table class=\"table table-striped table-bordered table-secondary\" >          \r\n" + 
                              "         <tr style=\"background-color: rgb(255,98,0);color: white; font-size:14px\">\r\n" + 
                              "          <th scope=\"col\">Test Steps</th>\r\n" + 
                              "          <th scope=\"col\">Pass</th>\r\n" + 
                              "          <th scope=\"col\">Fail</th>\r\n" + 
                              "          <th scope=\"col\">Start Time</th>\r\n" + 
                              "          <th scope=\"col\">End Time</th>\r\n" + 
                              "          <th scope=\"col\">Total Time(Mins)</th>\r\n" + 
                              "        </tr>\r\n" + 
                              "          \r\n" + 
                              "         <tr>\r\n" + 
                              "      <td>"+totalTestSteps+"</td>\r\n" + 
                              "      <td>"+passCount+"</td>\r\n" + 
                              "          <td>"+failCount+"</td>\r\n" + 
                              "          <td>"+startTime+"</td>\r\n" + 
                              "          <td>"+endTime+"</td>\r\n" + 
                              "          <td>"+totalTime+"</td>\r\n" + 
                              "          </tr>\r\n" + 
                              "   </table>");
      }
      
      
      private synchronized StringBuffer colHeader(StringBuffer stringBuffer) {
              
              
              return stringBuffer.append(" <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js\"></script>\r\n" + 
                              "         <!-- Optional JavaScript -->\r\n" + 
                              "    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\r\n" + 
                              "    <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
                              "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\r\n" + 
                              "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>\r\n" + 
                              "        <script type=\"text/javascript\" charset=\"utf8\" src=\"https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js\"></script>\r\n" + 
                              "        <script>        \r\n" + 
                              "                $(document).ready(function() {\r\n" + 
                              "                $('#dataTable').DataTable();\r\n" + 
                              "                } );\r\n" + 
                              "        </script>\r\n" + 
                              "   \r\n" + 
                              "   \r\n" + 
                              "    \r\n" + 
                              "        \r\n" + 
                              "         <table class=\"table table-striped table-bordered\" id=\"dataTable\">\r\n" + 
                              "          \r\n" + 
                              "         <thead class=\"\">\r\n" + 
                              "          <tr style=\"background-color: rgb(255,98,0);color: white;\">\r\n" + 
                              "          <th scope=\"col\">Steps</th>\r\n" + 
                              "          <th scope=\"col\">PageName</th>\r\n" + 
                              "      <th scope=\"col\">Step Description</th>\r\n" + 
                              "      <th scope=\"col\">Expected Result</th>\r\n" + 
                              "      <th scope=\"col\">Actual Result</th>\r\n" + 
                              "          <th scope=\"col\">Test Data</th>\r\n" + 
                              "          <th scope=\"col\">Result</th>\r\n" + 
                              "    </tr>\r\n" + 
                              " </thead>");
              
              
      }
      
      private synchronized StringBuffer colHeaderConsolidatedReport(StringBuffer stringBuffer) {
              
              
              return stringBuffer.append(" <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js\"></script>\r\n" + 
                              "         <!-- Optional JavaScript -->\r\n" + 
                              "    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\r\n" + 
                              "    <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
                              "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\r\n" + 
                              "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>\r\n" + 
                              "        <script type=\"text/javascript\" charset=\"utf8\" src=\"https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js\"></script>\r\n" + 
                              "        <script>        \r\n" + 
                              "                $(document).ready(function() {\r\n" + 
                              "                $('#dataTable').DataTable();\r\n" + 
                              "                } );\r\n" + 
                              "        </script>\r\n" + 
                              "   \r\n" + 
                              "   \r\n" + 
                              "    \r\n" + 
                              "        \r\n" + 
                              "         <table class=\"table table-striped table-bordered\" id=\"dataTable\">\r\n" + 
                              "          \r\n" + 
                              "         <thead class=\"\">\r\n" + 
                              "          <tr style=\"background-color: rgb(255,98,0);color: white;\">\r\n" + 
                              "          <th scope=\"col\">Test Case Id</th>\r\n" + 
                              "          <th scope=\"col\">Test Case Description</th>\r\n" + 
                              "      <th scope=\"col\">Result</th>\r\n" + 
                              "      <th scope=\"col\">Execution Time</th>\r\n" + 
                              "         </tr>\r\n" + 
                              " </thead>");
              
              
      }
      
      
      
      
private synchronized StringBuffer rowData(StringBuffer stringBuffer,int stepNo,String pageName, String stepDesc,String expectedResult,String actualResult,String testData, Status result) {
              
              
              return stringBuffer.append("<tr>\r\n" + 
                              "      <td>"+stepNo+"</td>\r\n" + 
                              "          <td>"+pageName+"</td>\r\n" + 
                              "      <td>"+stepDesc+"</td>\r\n" + 
                              "      <td>"+expectedResult+"</td>\r\n" + 
                              "      <td>"+actualResult+"</td>\r\n" + 
                              "          <td>"+testData+"</td>\r\n" + 
                              "          <td>"+result+" </td>\r\n" + 
                              "        </tr>");
              
              
      }

private synchronized StringBuffer rowDataOfConsolidatedReport(StringBuffer stringBuffer,String testCaseName, String testCaseFileName, String testCaseDescription, Status result, String executionTime) {
      
      
       stringBuffer.append("<tr>\r\n" + 
                      "      \r\n" + 
                      "          <td> <a href="+testCaseFileName+">"+ testCaseName+"</td> </a>\r\n" + 
                      "      <td>"+testCaseDescription+"</td>\r\n" + 
                      "      <td>"+result+"</td>\r\n" + 
                      "          <td>"+executionTime+"</td>");
      
      // System.out.println(stringBuffer.toString());
       return stringBuffer;
}



private static synchronized StringBuffer rowData(StringBuffer stringBuffer,int stepNo,String pageName, String stepDesc,String expectedResult,String actualResult,String testData, Status result, String screenshotName) {
      
      
      return stringBuffer.append("<tr>\r\n" + 
                      "      <td>"+stepNo+"</td>\r\n" + 
                      "      <td>"+pageName+"</td>\r\n" + 
                      "      <td>"+stepDesc+"</td>\r\n" + 
                      "      <td>"+expectedResult+"</td>\r\n" + 
                      "      <td>"+actualResult+"</td>\r\n" + 
                      "          <td>"+testData+"</td>\r\n" + 
                      "          <td><a href="+screenshotName+">"+result+"</a> </td>\r\n" + 
                      "         \r\n" + 
                      "    </tr>");
      
      
}
      

private synchronized StringBuffer closeTable(StringBuffer stringBuffer) {
      
      return stringBuffer.append("</table>\r\n" + 
                      " </table>\r\n" + 
                      " </div>\r\n" + 
                      "    \r\n" + 
                      "  </div>\r\n" + 
                      "</div>\r\n" + 
                      "\r\n" + 
                      " \r\n" + 
                      "  </body>\r\n" + 
                      "</html>");
}
      
      
      private synchronized File createNewFile(StringBuffer stringBuffer) {
               File html = null;
              try {
                         
                 String fileName=Utils.testCaseId+".html";
                 String fileNameNew=htmlReport+File.separator+fileName;
                 html=new File(fileNameNew);
                 html.createNewFile();
                     FileOutputStream fout=new FileOutputStream(html);
                    byte strbytarr[]=stringBuffer.toString().getBytes();
                    fout.write(strbytarr);
                    fout.flush();
                    fout.close(); 
                             
              
                              
              }catch(Exception e) {
                      
                      System.out.println("Exception occured while creating new file---"+e.getMessage());
              }
                      
          return html;         
              
      }
      
      
      private synchronized File createNewFileForOverallReport(StringBuffer stringBuffer) {
               File html = null;
              try {
                         
                 String fileName="OverallReport"+".html";
                 String fileNameNew=overallReportDir+File.separator+fileName;
                 html=new File(fileNameNew);
                 html.createNewFile();
                     FileOutputStream fout=new FileOutputStream(html);
                   byte strbytarr[]=stringBuffer.toString().getBytes();
                   fout.write(strbytarr);
                   fout.flush();
                   fout.close(); 
                            
              
                              
              }catch(Exception e) {
                      
                      System.out.println("Exception occured while creating new file---"+e.getMessage());
              }
                      
          return html;         
              
      }
      
      
      
      
      
      public synchronized Steps addStepsInReport(String tcid, String pageName, String stepDesc, String expectedResult, String actualResult,String testData, Status result) {
              steps=new Steps();
              steps.setTcID(tcid);
      //        steps.setStepNo(stepNo);
              steps.setPageName(pageName);
              steps.setStepDescription(stepDesc);
              steps.setExpectedResult(expectedResult);
              steps.setActualResult(actualResult);
              steps.setTestData(testData);
              if(result.equals(Status.Fail)) {
                      steps.setScreenshotName(captureScreenshot());
              }
              steps.setResult(result);
              stepsDescription.add(steps);
              return steps;
              
      }
      
      
      public synchronized ConsolidatedReportDetails addDetailsInReport(String tcid, String testCaseDescription, String totalTime, Status result) {
              consolidatedReportDetails=new ConsolidatedReportDetails();
              consolidatedReportDetails.setTestCaseId(tcid);
              consolidatedReportDetails.setTestCaseDescription(testCaseDescription);
              consolidatedReportDetails.setIndividualExecutionTime(totalTime);
              consolidatedReportDetails.setResult(result);
      //        consolidatedReportDetails.setFileName(fileName);
              arrConsolidatedReportDetails.add(consolidatedReportDetails);
              return consolidatedReportDetails;
              
      }
      
      
      
      private synchronized boolean extractTestSteps(Map<String, ArrayList<Steps>> singleReportMap) {
              
         boolean fail=false;
              System.out.println(singleReportMap);
              try {
                      Set<String> totalTestCases=singleReportMap.keySet();
                      System.out.println(totalTestCases);
                      Iterator<String> tcid=totalTestCases.iterator();
                  total=totalTestCases.size();
                  for(int j=0;j<totalTestCases.size();j++){
                                      String tmpTcid=tcid.next();
                                      System.out.println(tmpTcid);
                                              total=singleReportMap.get(tmpTcid).size();
                                                for(int k=0;k<singleReportMap.get(tmpTcid).size();k++) {
                                         
                                                        if(singleReportMap.get(tmpTcid).get(k).getResult().equals(Status.Done)||singleReportMap.get(tmpTcid).get(k).getResult().equals(Status.Pass)) {
                                                            passcount=passcount+1;
                                                                  
                                                  }else {
                                                       failcount=failcount+1;
                                                       fail=true;
                                                       
                                                                
                                                  }
                                                  
                                                }
                                                
                                   }
                  
                                      
                  //header
              
                   System.out.println("loop1 has been completed");
                  Utils.totalTimeOfIndividualTest=Utils.getTotalTimeInMinutes(Utils.startTimeMilliSec, Utils.endTimeMilliSec);
                      sBf = dashBoardOfSingleTestScript(sBf, total, passcount, failcount, Utils.startTime, Utils.endTime,
                                      Utils.getTotalTimeInMinutes(Utils.startTimeMilliSec, Utils.endTimeMilliSec));
                      sBf=colHeader(sBf);
                      Set<String> totalTestCases1=singleReportMap.keySet();
                      System.out.println(totalTestCases);
                      Iterator<String> tcid1=totalTestCases1.iterator();
                  total=totalTestCases1.size();
                                  
              for(int i=0;i<totalTestCases1.size();i++){
                      
                      String tmpTcid=tcid1.next();
                      System.out.println(tmpTcid);
                      
                        for(int j=0;j<singleReportMap.get(tmpTcid).size();j++) {
                                  if(singleReportMap.get(tmpTcid).get(j).getResult().equals(Status.Done)||singleReportMap.get(tmpTcid).get(j).getResult().equals(Status.Pass)) {
                                             sBf=rowData(sBf, j+1,singleReportMap.get(tmpTcid).get(j).getPageName(), singleReportMap.get(tmpTcid).get(j).getStepDescription(),singleReportMap.get(tmpTcid).get(j).getExpectedResult(),singleReportMap.get(tmpTcid).get(j).getActualResult(),singleReportMap.get(tmpTcid).get(j).getTestData(),singleReportMap.get(tmpTcid).get(j).getResult());
                                                      
                                  }else {
                                       sBf=rowData(sBf, j+1,singleReportMap.get(tmpTcid).get(j).getPageName(), singleReportMap.get(tmpTcid).get(j).getStepDescription(),singleReportMap.get(tmpTcid).get(j).getExpectedResult(),singleReportMap.get(tmpTcid).get(j).getActualResult(),singleReportMap.get(tmpTcid).get(j).getTestData(),singleReportMap.get(tmpTcid).get(j).getResult(),singleReportMap.get(tmpTcid).get(j).getScreenshotName());
                                              
                                  }
                                  
                                  System.out.println("Iteration--"+j+"---completed"); 
                         }
        }
              
                passcount=0;
                failcount=0;
                System.out.println("exitting");
              
              System.out.println("Total----"+total+"----Pass---"+passcount+"----Fail---"+failcount);
              }catch(Exception e) {
                      System.out.println("Exception----"+e.getMessage());
              }
              
              return fail;
      }
      
      
      public synchronized boolean generateIndividualHtmlReport(String className) {
              boolean fail=false;
              Map<String, ArrayList<Steps>> singeReportMap;
              String date=Utils.generateDate();
              String corporateId=Utils.getCorporateId();
              String platform=Utils.platform;
              String environment=Utils.getEnvironment();
              String browserName=Utils.browserName;
              if(browserName==null) {
                      browserName="Internet Explorer";
              }
              String url=Utils.currentUrl;
              if(url==null) {
                      url=Utils.unixServerName;
              }
              
              sBf=createHeader(sBf,className,date,corporateId,platform,environment,browserName,url);
              singeReportMap= new HashMap<String, ArrayList<Steps>>();        
              singeReportMap.put(className, stepsDescription);
              
              consolidatedReportDirectories.put(className, arrConsolidatedReportDetails );
          System.out.println("consolidatedReportDirectories--"+consolidatedReportDirectories);
          setConsolidatedDirectory.put(className,getDirectory());
          System.out.println("set consolidated directory---"+setConsolidatedDirectory);
              
        //   System.out.println("arrConsolidatedReportDetails----"+arrConsolidatedReportDetails.get(0));
              boolean failStatus=extractTestSteps(singeReportMap);
              if(failStatus) {
                      System.out.println("---Fail---");
                      fail=true;
              }else {
                      System.out.println("---Pass---");
              }
              
              
              sBf=closeTable(sBf);
              createNewFile(sBf);
              sBf=null;
              passcount=0;
              failcount=0;
              total=0;
              
              
                      
              System.out.println("Completed -------"+className+"------File");
              return fail;
      }
      
      
      
      
      
      public synchronized void generateOverallSummaryReport() {
              
      //        createOverallSummaryReportDirectory();
      //        overallReportDir=createDirectory();
      }
      
      
      public void getPassFailCount() {
              
              
      }
      
      public String captureScreenshot()
       {
              String screenshotName = null;
              try {
             File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
             screenshotName="screenshot"+System.currentTimeMillis()+".png";
             System.out.println("scrnshotname--"+screenshotName);
             System.out.println("screesnhot dir--"+HTMLReport.screenshotdir+File.separator+screenshotName);
              // Now you can do whatever you need to do with it, for example copy somewhere
             FileUtils.copyFile(scrFile, new File(HTMLReport.screenshotdir+File.separator+screenshotName));
             System.out.println("Module Page Screen is taken succsfully.");
             
              }catch(Exception e) {
                      System.out.println("Not able to take screenshot, check with developer");
              }
            // return HTMLReport.screenshotdir+File.separator+screenshotName;
              System.out.println("capture screenshot-----"+HTMLReport.executionReport+File.separator+screenshotName);
              return "../Screenshot/"+screenshotName;
       }
      
      public static void copyDirectory(File src, File destination) {
              
      try {
              
      //        File fileSource=new File(src);
      //        fileSource.mkdir();
      
      //        File dest=new File(destination);
      //        dest.mkdir();
              
              FileUtils.moveDirectoryToDirectory(src, destination, true);
      //        FileUtils.moveDirectory(fileSource, dest);
              System.out.println("Copied files");
              
      }catch(Exception e) {
              
              System.out.println("exception occurd while copying files");
      }
      
      }

      @Override
      public void executeTestCase() {
              // TODO Auto-generated method stub
              
      }

      @Override
      public void tearDown() {
              // TODO Auto-generated method stub
              
      }

      
      
}





