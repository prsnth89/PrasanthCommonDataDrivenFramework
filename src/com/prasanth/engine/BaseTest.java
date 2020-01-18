package com.prasanth.engine;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.prasanth.report.html.HTMLReport;
import com.prasanth.utilities.Utils;
import com.prasanth.utilities.WebDriverFactory;

import atu.testrecorder.ATUTestRecorder;

public abstract class BaseTest extends WebDriverFactory {
	
	public static Properties properties=null;
	public FileInputStream fileInput=null;
	public WebDriverFactory webdriverFactory=null;
	public WebDriver driver=null;
	public static String className=null;
	public static long startTime;
	public static long endTime;
	public static HTMLReport report=null;
	public static boolean isFailIndividualExecution=false;
	public static boolean isFailSuiteExecution=false;
	
	public BaseTest(WebDriver driver) {
		this.driver=driver;
		Utils.pageName=getPageName();
	}
	
	public BaseTest() {
		
	}
	
	public void intializeAllProperty()  {
		
		String browserType;
		try {
			
			fileInput=new FileInputStream(System.getProperty("user.dir")+File.separator+"GlobalSettings.properties");
			properties=new Properties();
			properties.load(fileInput);
			
		}catch(Exception e) {
			System.out.println("No file found");
		}
		
		browserType=properties.getProperty("browsertype").trim();
		webdriverFactory=new WebDriverFactory();
		if(browserType.equals("chrome")) {
			String internetexplorerdriver=System.getProperty("user.dir")+File.separator+"driver"+File.separator+"chromedriver.exe";
			driver=webdriverFactory.initializeDriver(browserType, internetexplorerdriver);
		}
		
		Utils.startTimeMilliSec=System.currentTimeMillis();
		Utils.startTime=Utils.getCurrentTimeWithCustomizedFormat();
		report=new HTMLReport(driver);
		String url=properties.getProperty("url").trim();
		Utils.currentUrl=url;
		driver.get(url);
		try {
			executeTestCase();
			Thread.sleep(3000);
		}catch(Exception e) {
			try {
				tearDown();
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		
		finally {
			
			driver.quit();
		}
	}
	
	public void initializeRegressionProperty() throws Exception {
		ATUTestRecorder recorder=null;
		try {
			fileInput=new FileInputStream(System.getProperty("user.dir")+File.separator+"GlobalSettings.properties");
			properties=new Properties();
			properties.load(fileInput);
		
			report =new HTMLReport(driver);
			recorder=new ATUTestRecorder(System.getProperty("user.dir")+File.separator+"Reports","scriptrecording",false);
			recorder.start();
			executeTestCase();
		
		}catch(Exception e) {
			isFailIndividualExecution=true;
					
		}
		
		finally {
			tearDown();
			recorder.stop();
			recorder=null;
		//	driver.quit();
		}
		
	}
	
	public String getPageName() {
		return this.getClass().getSimpleName();
	}
	
	public abstract void executeTestCase() throws Exception;
	
	public abstract void tearDown() throws Exception;

}
