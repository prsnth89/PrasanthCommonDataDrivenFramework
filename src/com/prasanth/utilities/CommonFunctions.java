package com.prasanth.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.prasanth.engine.BaseTest;
import com.prasanth.report.html.Status;

public class CommonFunctions extends BaseTest {

	 public static String pageName=null;
     Session session;
     JSch jsch;
     
     public CommonFunctions(WebDriver driver) {
             super(driver);

     }
     
     public CommonFunctions() {
     
     }
     
     
     
     public WebElement getElement(String locatorName, String locatorType) {
             
             WebElement element = null;
             
             try {
                      if(locatorType.equalsIgnoreCase("id")){
                             element= driver.findElement(By.id(locatorName));
                     }else if(locatorType.equalsIgnoreCase("name")){
                             element= driver.findElement(By.name(locatorName));
                     }if(locatorType.equalsIgnoreCase("xpath")){
                             element= driver.findElement(By.xpath(locatorName));
                     }if(locatorType.equalsIgnoreCase("css")){
                             element= driver.findElement(By.cssSelector(locatorName));
                     }if(locatorType.equalsIgnoreCase("linkText")){
                             element= driver.findElement(By.linkText(locatorName));
                     }if(locatorType.equalsIgnoreCase("partialLinkText")){
                             element= driver.findElement(By.partialLinkText(locatorName));
                     }if(locatorType.equalsIgnoreCase("tagName")){
                             element= driver.findElement(By.tagName(locatorName));
                     }if(locatorType.equalsIgnoreCase("class")){
                             element= driver.findElement(By.className(locatorName));
                     }
                     
                     
             }catch(Exception e) {
                     
                     System.out.println("Element not found in getElement catch method----"+locatorName);
             //        report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Get the element", "System should find the given webelement", "System failed to find the element", "N/A", Status.Fail);
                     
                     
             }
             
             return element;
     }

     
     /*  Description -Clicks any given element using this method
      
      * @parameter1 -Locator Name-String
      * 
      * @parameter2 -Locator Type-String
      * 
      * @return type -null */
      

     public void clickElement(String locatorName, String locatorType) {

             try {
             
                     // Thread.sleep(3000);
                      getElement(locatorName,locatorType).click();
                      report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Click the element "+locatorName, "System should click the given webelement", "System clicked the given webelement","N/A", Status.Done);
                             
                      
             } catch (NoSuchElementException e) {
                     System.out.println("No Element Found in clickElement--" + e.getMessage());
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Click the given element--"+locatorName, "System should click the given webelement", "System failed to click the given webelement","N/A", Status.Fail);
                     throw e;
             }
     }

     /*
      * Description -Allows to enter the test data
      * 
      * @parameter1 -Locator Name-String
      * 
      * @parameter2 -Locator Type-String
      * 
      * @return type -null
      */
     public void enterTestData(String locatorName, String locatorType, String testData) {

             try {
                     waitTime(3000);
                     getElement(locatorName, locatorType).clear();
                     getElement(locatorName, locatorType).sendKeys(testData);
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Enter the test data in "+locatorName+" field", "System should enter the test data", "System entered the test data successfully", testData, Status.Done);
                     
             } catch (Exception e) {
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Enter test data", "System should enter the test data in "+locatorName+ "field", "System failed to enter the test data--Exception Reason--WebElement not found/invalid locator type"+e.getMessage(), testData, Status.Fail);
                     System.out.println("No Element Found in enterTestData--" + locatorType+"----"+locatorName);
                     throw e; 
             }

     }
     
     
     /*
      * Description -Allows to enter the test data
      * 
      * @parameter1 -Locator Name-String
      * 
      * @parameter2 -Locator Type-String
      * 
      * @return type -null
      */
     public void enterTestDataWithoutLoggingInReport(String locatorName, String locatorType, String testData) {

             try {
                     waitTime(3000);
                     getElement(locatorName, locatorType).clear();
                     getElement(locatorName, locatorType).sendKeys(testData);
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Enter the test data in "+locatorName+" field", "System should enter the test data", "System entered the test data successfully", "N/A", Status.Done);
                     
             } catch (Exception e) {
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Enter test data", "System should enter the test data in "+locatorName+ "field", "System failed to enter the test data--Exception Reason--WebElement not found/invalid locator type"+e.getMessage(), "N/A", Status.Fail);
                     System.out.println("No Element Found in enterTestData--" + locatorType+"----"+locatorName);
                     throw e; 
             }

     }
     
     
     /*
      * Description -Allows to enter the test data
      * 
      * @parameter1 -Locator Name-String
      * 
      * @parameter2 -Locator Type-String
      * 
      * @return type -null
      */
     public void enterTestDataAndUseTab(String locatorName, String locatorType, String testData) {

             try {
                     waitTime(3000);
                     getElement(locatorName, locatorType).clear();
                     getElement(locatorName, locatorType).sendKeys(testData,Keys.TAB);
                     
             } catch (NoSuchElementException e) {
                     System.out.println("No Element Found in enterTestData--" + locatorType+"----"+locatorName);
                     throw e;
             }

     }
     
     /*
      * Description -Allows to enter the test data
      * 
      * @parameter1 -Locator Name-String
      * 
      * @parameter2 -Locator Type-String
      * 
      * @return type -null
      */
     public void enterTestDataAndUseEnter(String locatorName, String locatorType, String testData) {

             try {
                     waitTime(3000);
                     getElement(locatorName, locatorType).clear();
                     getElement(locatorName, locatorType).sendKeys(testData,Keys.ENTER);
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName, "enterTestDataAndUseEnter "+locatorName, "System should enter the test data", "System entered the test data",testData, Status.Done);
                     
             } catch (NoSuchElementException e) {
            	    report.addStepsInReport(Utils.testCaseId, Utils.pageName, "enterTestDataAndUseEnter "+locatorName, "System should enter the test data", "System entered the test data",testData, Status.Fail);
                    System.out.println("No Element Found in enterTestData--" + locatorType+"----"+locatorName);
                    throw e;
             }

     }

     /*
      * Description -Clicks any given element using this method
      * 
      * @parameter1 -Locator Name-String
      * 
      * @parameter2 -Locator Type-String
      * 
      * @return type -String
      */

/*        public String extractText(String locatorName, String locatorType, String textToVerify) {

             String extractedText=null;
             try {
                     if (locatorType.trim().equalsIgnoreCase("id")) {
                             extractedText = driver.findElement(By.id(locatorName)).getText().trim();
                     } 
                     else if (locatorType.trim().equalsIgnoreCase("name")) {
                             extractedText=driver.findElement(By.name(locatorName)).getText().trim();
                     } 
                     else if (locatorType.trim().equalsIgnoreCase("xpath")) {
                             extractedText=driver.findElement(By.xpath(locatorName)).getText().trim();
                     } 
                     else if (locatorType.trim().equalsIgnoreCase("css")) {
                             extractedText=driver.findElement(By.cssSelector(locatorName)).getText().trim();
                     }                         
                     else if (locatorType.trim().equalsIgnoreCase("linkText")) {
                             extractedText=driver.findElement(By.linkText(locatorName)).getText().trim();
                     } 
                     else if (locatorType.trim().equalsIgnoreCase("partialLinkText")) {
                             extractedText=driver.findElement(By.partialLinkText(locatorName)).getText().trim();
                     } 
                     else if (locatorType.trim().equalsIgnoreCase("tagName")) {
                             extractedText=driver.findElement(By.tagName(locatorName)).getText();
                     } 
                     else if (locatorType.trim().equalsIgnoreCase("class")) {
                             extractedText=driver.findElement(By.className(locatorName)).getText();
                     } else {
                             System.out.println("Enter valid locator Type in verifyExtractedText");
                     }
             } catch (NoSuchElementException e) {
                     System.out.println("No Element Found in verifyExtractedText--" + e.getMessage());
             }
             
             return extractedText;

     } */
     
     
     /*
      * Description -Extract text based on the element
      * 
      * @parameter1 -Locator Name-String
      * 
      * @parameter2 -Locator Type-String
      * 
      * @return type -String
      */
     
     public String extractText(String locatorName, String locatorType) {

                     String extractedText=null;
                     try {
                     extractedText=getElement(locatorName, locatorType).getText();
                     } catch (NoSuchElementException e) {
                                     
                     System.out.println("Not able to verify the Extracted Text--" + e.getMessage());
                     throw e;
             }
             
             return extractedText;

     } 
     
     

     /*
      * Description -verification of text
      * 
      * @parameter1 -String - text to verify
      * 
      * @parameter2 -String - extracted text
      * 
      * @return type -boolean
      */

     public boolean verifyText(String textToVerify, String extractedText) throws Exception {
             boolean result=false;
             
             if (extractedText.contains(textToVerify.trim())) {
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName,"Verifies the given text and extracted text are same", "Given text and extracted text should be same", "Given Text and extracted text are same", textToVerify,Status.Pass);
                     
     //                System.out.println("Extracted Text------" + extractedText + "------ and Given text---" + textToVerify
     //                                + "------are same and hence the verification result is pass");
                     result = true;
             } else {
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName,"Verifies the given text and extracted text are same", "Given text and extracted text should be same", "Given Text and extracted text are not same", textToVerify,Status.Fail);
                     result=false;
                     throw new Exception();                
             //        System.out.println("Extracted Text------" + extractedText + "------ and Given text---" + textToVerify
             //                        + "------are not same and hence the verification result is fail");

             }
             
             return result;

     }
     
     
     
     /*
      * Description -selectOptionFromDropDownListByValue
      * 
      * @parameter1 -String - locator name
      * 
      * @parameter2 -String - locator type
      * 
      * @parameter3 -String - value
      * 
      * @return type -null
      */
     
     public void selectOptionFromDropDownListByValue(String locatorName, String locatorType, String value) {
          WebElement selectElement;
             try {
                     selectElement=getElement(locatorName, locatorType);
                     selectByValue(selectElement, value);
             } catch (NoSuchElementException e) {
                     System.out.println("No Element Found in verifyExtractedText--" + e.getMessage());
                     throw e;
             }
             
     }
     
     
     /*
      * Description -selectOptionFromDropDownListByGivenText
      * 
      * @parameter1 -String - locator name
      * 
      * @parameter2 -String - locator type
      * 
      * @parameter3 -String - text
      * 
      * @return type -null
      */
     
     public void selectOptionFromDropDownListByGivenText(String locatorName, String locatorType , String text) {
          
             try {
                     WebElement element=getElement(locatorName, locatorType);
                     selectByGivenText(element, text);
                     
             } catch (NoSuchElementException e) {
                     System.out.println("selectByGivenText--No Element Found in selectByGivenText--" + e.getMessage());
             }
             
     }
     
     
     /*
      * Description -select dropdown By Value
      * 
      * @parameter1 -WebElement
      * 
      * @parameter2 -String- value
      * 
      * @return type -null
      */
     
     public void selectByValue(WebElement element, String value) {
             
             Select select;
             try {
                     select=new Select(element);
                     select.selectByValue(value.trim());
                     System.out.println("Selected value successfully---"+value);
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName,"SelectByValue", "Select the dropdown by value", "Selected the dropdown successfully", value,Status.Pass);
                     
             }catch(Exception e) {
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName,"SelectByValue", "Select the dropdown by value", "Failed to select the dropdown--"+e.getMessage(), value,Status.Fail);
                     System.out.println("Not able to select the dropdown value in selectByValue method");
                     throw e;
             }
             
     }
     
     /*
      * Description -select given text from drop down
      * 
      * @parameter1 -Webelement
      * 
      * @parameter2 -String - Text
      * 
      * @return type -null
      */
     
     
     public void selectByGivenText(WebElement element, String value) {
             
             Select select;
             try {
                     select=new Select(element);
                     select.selectByVisibleText(value);
                     System.out.println("selectByGivenText--Selected value successfully based on given text---"+value);
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName,"SelectByText", "Select the dropdown by text", "Selected the dropdown successfully by text", value,Status.Pass);
                     
             }catch(Exception e) {
                     report.addStepsInReport(Utils.testCaseId, Utils.pageName,"SelectByValue", "Select the dropdown by text", "Failed to select the dropdown by text--"+e.getMessage(), value,Status.Fail);
                     System.out.println("selectByGivenText-Not able to select the dropdown value in selectByGivenText method");
                     throw e;
             }
             
     }
     
     
     /*
      * Description -wait for the presence of element
      * 
      * @parameter1 -locatorName
      * 
      * @parameter2 -locatorType
      * 
      * @parameter3 -int time
      * @return type -booelan-true or false
      */
     public boolean waitForElementVisibility(String locatorName,String locatorType,long time)
     {
             boolean elementVisible=false;
             
             try {
                             
                             WebElement element=getElement(locatorName, locatorType);
                             long startTime=System.currentTimeMillis();
                             WebDriverWait wait=new WebDriverWait(driver,time);
                             wait.until(ExpectedConditions.visibilityOf(element));
             //                wait.until(ExpectedConditions.visibilityOf(element));
                             System.out.println("Testing wait time---"+Utils.calculateTotalTime(startTime));
                             return elementVisible=true;        
                     
             }catch(Exception e) {
                     System.out.println("waitForElementVisibility---Not able to find element--catch block----"+locatorName+"--- +in the given time ---"+Utils.calculateTotalTime(startTime));
             }
             return elementVisible;
     
     }
     
     
     public void waitTime(long time) {
             try {
                     
                     Thread.sleep(time);
                     
             }catch(Exception e) {
                     
             }
     }
     
     
     /*
      * Description -verify the element is displayed or not
      * 
      * @parameter1 -locatorName
      * 
      * @parameter2 -locatorType
      * 
      * @return type -booelan-true or false
      */
     public boolean verifyIfElementIsDisplayed(String locatorName,String locatorType)
     {
             boolean elementVisible=false;
             
             try {
                     Thread.sleep(3000);
                     WebElement element=getElement(locatorName, locatorType);
                     if(element.isDisplayed()) {
                      
                       return        elementVisible=true;
                     }
             }catch(Exception e) {
                     
                     System.out.println("verifyIfElementIsDisplayed---Not able to find the element----"+locatorName);
             }
             
             return elementVisible;
     
     }
     
     
     /*
      * Description -verify the element is enabled or not
      * 
      * @parameter1 -locatorName
      * 
      * @parameter2 -locatorType
      * 
      * @return type -booelan-true or false
      */
     public boolean verifyIfElementIsEnabled(String locatorName,String locatorType)
     {
             boolean elementVisible=false;
             
             try {
                     Thread.sleep(3000);
                     WebElement element=getElement(locatorName, locatorType);
                     if(element.isEnabled()) {
                      
                       return        elementVisible=true;
                     }
             }catch(Exception e) {
                     
                     System.out.println("verifyIfElementIsEnabled---Not able to verify the element----"+locatorName+"---"+e.getMessage());
             }
             
             return elementVisible;
     
     }
     
     
     /*
      * Description -verify the element is displayed or not
      * 
      * @parameter1 -locatorName
      * 
      * @parameter2 -locatorType
      * 
      * @return type -booelan-true or false
      */
     public void acceptAlert() {
             
             Alert alert=null;
             try {
                     Thread.sleep(2000);
                     alert=driver.switchTo().alert();
                     alert.accept();
                     System.out.println("acceptAlert----Alert accepted successfully----Pass");
             }catch(Exception e) {
                     System.out.println("acceptAlert----Failed to accept alert----Fail");
             }
             
     }
     
     /*
      * Description -get the parent window id
      * 
      * @parameter1 -Nil
      *  
      * @return type -string
      */
     
     public String getParentWindowId() {
             String parentWindow = null;
             try {
                     Set<String> windowId=driver.getWindowHandles();
                     System.out.println("Set---"+windowId);
                     Iterator<String> iterator=windowId.iterator();
                     System.out.println("iterator---"+iterator);
                     parentWindow=iterator.next();
             }catch(Exception e) {
                     System.out.println("Exception occurs--Parent window id");
             }
             
             return parentWindow;
             
             
     }
     
     /*
      * Description -get the child window id
      * 
      * @parameter1 -Nil
      *  
      * @return type -string
      */
     public String getChildWindowId() {
             String childWindow=null;
             String parentWindow=null;
             try {
                     
                     Set<String> windowId=driver.getWindowHandles();
                     System.out.println(windowId);
                     Iterator<String> iterator=windowId.iterator();
                     parentWindow=iterator.next();
                     childWindow=iterator.next();
                     
             }catch(Exception e) {
                     System.out.println("Exception occured in the child window--"+e.getMessage());
             }
             
             return childWindow;
             
             
     }
     
     
     
     /*
      * Description -switch to window using right id
      * 
      * @parameter1 -Nil
      *  
      * @return type -string
      */
     public void switchToWindow(String windowId) {
             
        driver.switchTo().window(windowId);
                             
     }
     
     public void switchToFrame(int frameId) {
             try {
                     String child=driver.getWindowHandle();
                     System.out.println(child);
                     driver.switchTo().window(child);
                     System.out.println(driver.getTitle());
             }catch(Exception e) {
                     System.out.println("note able to switch to given frame--"+frameId);
             }
     
     }
     
     
     public void scrollToWebElementUsingJavascript(WebElement element) {
             
             ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
     }
     
     public void refreshPage() {
             driver.navigate().refresh();
             waitTime(2000);
     }
     
     
     public void navigateTo(String url) {
             driver.navigate().to(url);
             waitTime(2000);
     }
     
     public void clickElementByJavaScriptExecutor(WebElement element) {
             try {
                     JavascriptExecutor executor = (JavascriptExecutor)driver;
                     executor.executeScript("arguments[0].click();", element);
             }catch(Exception e) {
                     System.out.println("Not able to find element or click through java script executor--"+element);
             }
             
     }
     
public void amazon() {
             
     try {
             
             driver.navigate().to("https://www.amazon.in/ap/signin?_encoding=UTF8&openid.assoc_handle=inflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fcard%3Fie%3DUTF8%26ref_%3Dcust_rec_intestitial_signin");
             WebElement condition=driver.findElement(By.xpath("//a[contains(text(),'Conditions of Use')]"));
             Set<String> windowsId=driver.getWindowHandles();
             Iterator<String> iterator=windowsId.iterator();
             System.out.println(windowsId);
             String parentWindow=iterator.next();
             //String conditionOfUseWindow=iterator.next();
             System.out.println("Beforee clicking----"+driver.getTitle());
             condition.click();
             Set<String> windowsId1=driver.getWindowHandles();
             Iterator<String> iterator1=windowsId1.iterator();
             System.out.println(windowsId1);
             String parentWindow1=iterator1.next();
             System.out.println("Parent window---"+parentWindow1);
             String childWindow1=iterator1.next();
             System.out.println("child window---"+childWindow1);
             System.out.println("Beforee switching----"+driver.getTitle());
             driver.switchTo().window(childWindow1);
             System.out.println("After switching----"+driver.getTitle());
             driver.switchTo().window(parentWindow);
             System.out.println("Back to parent window----"+driver.getTitle());
             
     }catch(Exception e) {
             System.out.println("caat"+e.getMessage());
     }
     }


public XMLGregorianCalendar getXMLGregorianCalendar(String date) throws DatatypeConfigurationException, ParseException {
     XMLGregorianCalendar xmlCalender = null;
     GregorianCalendar calender = new GregorianCalendar();
     calender.setTime(stringToJavaDate(date));
     try {
             xmlCalender = DatatypeFactory.newInstance().newXMLGregorianCalendar(calender);
             xmlCalender.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
             xmlCalender.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
     } catch (DatatypeConfigurationException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             throw e;
     }
     return xmlCalender;
}

public XMLGregorianCalendar getXMLGregorianCalendarWithTime(String date) throws DatatypeConfigurationException, ParseException {
     XMLGregorianCalendar xmlCalender = null;
     GregorianCalendar calender = new GregorianCalendar();
     calender.setTime(stringToJavaDateWithTime(date));
     try {
             xmlCalender = DatatypeFactory.newInstance().newXMLGregorianCalendar(calender);
             xmlCalender.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
             xmlCalender.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
     } catch (DatatypeConfigurationException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             throw e;
     }
     return xmlCalender;
}

public  XMLGregorianCalendar getXMLGregorianCalendarWithZuluTimeZone(String dateTime) throws DatatypeConfigurationException, ParseException {
     XMLGregorianCalendar xmlCalender = null;
     GregorianCalendar calender = new GregorianCalendar();
     calender.setTime(stringToJavaDateWithTime(dateTime));
     try {
             calender.setTimeZone(TimeZone.getTimeZone("UTC"));
             xmlCalender = DatatypeFactory.newInstance().newXMLGregorianCalendar(calender);
             xmlCalender.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
             
     } catch (DatatypeConfigurationException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             throw e;
     }
     return xmlCalender;
}


public Date stringToJavaDateWithTime(String sDate) throws ParseException {
     Date formatter = null;
     try {
             formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(sDate);
     } catch (ParseException e) {
             e.printStackTrace();
             throw e;
     }

     return formatter;
}

public Date stringToJavaDate(String sDate) throws ParseException {
     Date date = null;
     try {
             date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(sDate);
     //        System.out.println("Print date---" + date);
             // HH:mm:ss
     } catch (ParseException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             throw e;
     }
     return date;
}

public BigInteger getBigInteger(String value) {

     BigInteger bInt = new BigInteger(value);
     return bInt;

}

public BigDecimal getBigDecimal(String value) {

     BigDecimal no = new BigDecimal(value);
     return no;
}

public String generateRandomMsgId() {
//     String ts = String.valueOf(System.currentTimeMillis());
     String rand = UUID.randomUUID().toString();
     // System.out.println(rand);
     return rand;
}

public String generateDateTime() {

     Date date = null;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     date = new Date();
     System.out.println(formatter.format(date));

     return formatter.format(date);
}

public String generateDateTimeWithCustomizedFormat() {

     Date date = null;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
     date = new Date();
     System.out.println(formatter.format(date));

     return formatter.format(date);
}



public String generateFiveDigitNo(int iterationId) {
     NumberFormat formatter = new DecimalFormat("#000000000");
     String fiveDigitNo = formatter.format(iterationId);
     return fiveDigitNo;
}

public String generateTwoDigitNo(int iterationId) {
     NumberFormat formatter = new DecimalFormat("#000000000");
     String fiveDigitNo = formatter.format(iterationId);
     return fiveDigitNo;
}

public String calculateTotalTime(long startTime) {
     long endTime = System.currentTimeMillis();
     NumberFormat formatter = new DecimalFormat("#0.00");
     String totalTime = formatter.format((endTime - startTime) / 1000d);
     return totalTime;

}


public String generateDate() {

     Date date = null;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
     date = new Date();
     System.out.println(formatter.format(date));

     return formatter.format(date);
}

public String generateTime() {

     Date date = null;
     SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
     date = new Date();
     System.out.println(formatter.format(date));

     return formatter.format(date);
}


     

public String getCurrentUrl() {
       
       return driver.getCurrentUrl();
}

public String getClassName() {
     
     String className = this.getClass().getSimpleName();
     return className;
}


public Session connectLinuxServer(String servername,String username, String password) {

             //String host = "lrv143zf";
             // String host="lrv152hm";
             //String user = "do35kc";
     //        String password = "Prsnth05";
   System.out.println("servername--"+servername+"---username--"+username);
             jsch = new JSch();
             try {
                     session = jsch.getSession(username, servername, 22);
                     session.setConfig("StrictHostKeyChecking", "no");
                     session.setPassword(password);
             //        System.out.println(session.getUserName());
                     session.connect();
                     System.out.println("Server connected");
                     

             } catch (JSchException e) {

                     e.printStackTrace();
             }
             return session;

     }

     public Channel executeShellCommand(Session session, String command) throws Exception {

             String output = null;
             boolean processFail=false;
             ChannelExec channel=null;

             try {
                     
                     channel = (ChannelExec) session.openChannel("exec");
             //        System.out.println(session.getUserInfo());
                     channel.setInputStream(null);
                     // ((ChannelExec) channel).setPty(true);
                     // Thread.sleep(2000);
                     
             
                     ((ChannelExec) channel).setCommand(command);
                     
                     ((ChannelExec) channel).setErrStream(System.err);

                     // Thread.sleep(2000);
                     InputStream input = channel.getInputStream();
                     channel.connect();
             /*        if(channel.getErrStream() != null) {
                              processFail=true;
                              throw new Exception();        
                     }*/
                     
                     
                      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                  String line;
                      // You can also simple print the result here
                      while ((line = reader.readLine()) != null)
                   {
                              System.out.println(line);
                              if(line.contains("The return value from the response is 1")||line.contains("Unable to Process Admin Client. The return value setting as 1")||line.contains("Exception")) {
                                      report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Execute the given shell command", "Shell commands should be executed", "Execution terminated--Access logs---"+line, command, Status.Fail);
                                      processFail=true;
                                      System.out.println("Failed during script execution");
                                     // throw new Exception("Failure in execution of shell scripts");
                                      break;
                                      
                              }
                             
                        }
                     
/*                        byte[] tmp = new byte[1024];
                     while (true) {
                             while (input.available() > 0) {
                                     
                                     
                                     int i = input.read(tmp, 0, 1024);
                                     if (i < 0)
                                             break;
                                     output = "~" + new String(tmp, 0, i);
                                     System.out.println(output);
                                     if (output.contains("The return value from the response is 1")||output.contains("Unable to Process Admin Client. The return value setting as 1")) {
                                             System.out.println("Fail");
                                             processFail=true;
                                             throw new Exception();
                                             //break;
                                     }
                                     if(processFail) {
                                     //        return ;
                                             break;
                                             
                                             
                                     }
                                             
                                     // System.out.print(new String(tmp, 0, i));

                             } */
                     
                     
                     
                             if (channel.isClosed()) {
                                     System.out.println("exit the status status: "); // channel.getExitStatus()
                             }

                             
                             

             } catch (Exception e) {
                     System.out.println("Main exception--" + e.getMessage());
                 report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Execute the given shell commands", "Shell commands should be executed", "Execution was not successful, exception occurred--"+e.getMessage(), command, Status.Fail);
                 processFail=true;
                 throw e;
             }
             
             if(processFail) {
                      report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Execute the given shell commands", "Shell commands should be executed", "Execution was not successful, please check logs", command, Status.Fail);
                             
             }else {
                      report.addStepsInReport(Utils.testCaseId, Utils.pageName, "Execute the given shell commands", "Shell commands should be executed", "Execution completed successfully", command, Status.Pass);
                             
             }
             
     
             
             return channel;
     }
     
     
     public List executeShellCommandForFetchingFiles(Session session, String command) {

             List list=new ArrayList();
             String output = null;
             boolean processFail=false;
             ChannelExec channel=null;

             try {
                     channel = (ChannelExec) session.openChannel("exec");
                     System.out.println(session.getUserInfo());
                     channel.setInputStream(null);
                     // ((ChannelExec) channel).setPty(true);
                     // Thread.sleep(2000);
                     ((ChannelExec) channel).setCommand(command);
                     ((ChannelExec) channel).setErrStream(System.err);

                     // Thread.sleep(2000);
                     
                      
                     InputStream input = channel.getInputStream();
                     channel.connect();
                     
                      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                  String line;
                      // You can also simple print the result here
                      while ((line = reader.readLine()) != null)
                   {
                              if(line.contains("GCBL")) {
                                      
                                     //System.out.println(line.substring(line.indexOf("GCBL"), line.length()));
                                     list.add(line.substring(line.indexOf("GCBL"), line.length()));
                              }
                             
                          
             
                       }
                     
                     
                             if (channel.isClosed()) {
                                     System.out.println("exit-status: "); // channel.getExitStatus()
                     
                             }

                             try {
                                     Thread.sleep(1000);
                             } catch (Exception ee) {
                                     System.out.println(ee);
                             }
             

             } catch (Exception e) {
                     System.out.println("Main exception--" + e.getMessage());
             }

             return list;
     }

     public void disconnectLinux(Session session, Channel channel) {
             int exitStatus = channel.getExitStatus();
             channel.disconnect();
             session.disconnect();
             if (exitStatus < 0) {
                     System.out.println("Done, but exit status not set!");
             } else if (exitStatus > 0) {
                     System.out.println("Done,! - Disconnected linux connection successfully");

             } else {

                     System.out.println("Done! -Disconnected linux connection successfully");

             }
     }
     
     
     public void disconnectLinux(Session session) {
     //        int exitStatus = channel.getExitStatus();
             //channel.disconnect();
             //int exitStatus=session.get
             session.disconnect();
     
     }

     public void uploadFile(Session session, String fileToCopyInWindows, String fileToPasteInUnix) throws JSchException, SftpException {

             try {
                     Channel localChannel = session.openChannel("sftp");
                     localChannel.connect();
                     ChannelSftp localChannelSftp = (ChannelSftp) localChannel;
                     // localChannelSftp.get("tpamNPASuccessLog.txt", fileToPaste);
                     localChannelSftp.put(fileToCopyInWindows, fileToPasteInUnix);
                     System.out.println("----uploaded the file successfully from---"+fileToCopyInWindows+"-----"+fileToPasteInUnix);
             } catch (JSchException e) {
                     System.out.println(e.getMessage());
                     throw e;
             } catch (SftpException e) {
                     System.out.println(e.getMessage());
                     throw e;
             }
     }
     
     
      public static void downloadFile(Session session,String fileToCopy, String fileToPaste) throws JSchException, SftpException{
               
              try {
                         Channel localChannel = session.openChannel("sftp");
                         localChannel.connect();
                         ChannelSftp localChannelSftp = (ChannelSftp)localChannel;
                       //  localChannelSftp.get("tpamNPASuccessLog.txt", fileToPaste);
                         localChannelSftp.get(fileToCopy,fileToPaste);
                         System.out.println("Downloaded files from unix server successfully in the below path----"+fileToPaste+"\\"+fileToCopy.substring(fileToCopy.indexOf("GCBL"), fileToCopy.length()));
              }catch(JSchException e) {
                     System.out.println(e.getMessage()); 
                     throw e;
              }catch(SftpException e) {
                      System.out.println(e.getMessage());
                      throw e;
              }
       }
     
     public List<File> getFilesFromPath(String path) throws IOException{
             List<File> filesInFolder = null;
             try {
                     filesInFolder = Files.walk(Paths.get(path))
                             .filter(Files::isRegularFile)
                             .map(Path::toFile)
                             .collect(Collectors.toList());
                     
                     System.out.println("Files in folder---"+filesInFolder);
             } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                     throw e;
             }
             
             return filesInFolder;
             
     }
     
     public void deleteAllFilesFromPath(String path) throws Exception {
             try {
             Files.walk(Paths.get(path))
   .filter(Files::isRegularFile)
   .map(Path::toFile)
   .forEach(File::delete);
             
             System.out.println("Deleted all files");
             }catch(Exception e){
                     System.out.println("Not able to delete files from the given path/path not available--"+path);
                     throw e;
             }
     }

	@Override
	public void executeTestCase() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tearDown() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
