package com.prasanth.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {

	public WebDriver driver=null;
	public WebDriver initializeDriver(String browserType, String driverPath) {
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		DesiredCapabilities cap=DesiredCapabilities.chrome();
	//	cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	//	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	//	cap.setCapability("ignoreZoomSetting", true);
		Utils.browserName=cap.getBrowserName();
		Utils.browserVersion=cap.getVersion();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
		
		
	}
	
}
