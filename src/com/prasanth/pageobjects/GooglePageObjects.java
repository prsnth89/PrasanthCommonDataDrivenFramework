package com.prasanth.pageobjects;

public enum GooglePageObjects {
	
	Search("//input[@name='q']","XPATH"),
	SearchInvalid("//input[@nae='q']","XPATH"),
	SearchButton("//div[contains(@class,'FPdoLc')]//input[@name='btnK']","XPATH"),
	English("//a[text()='English']","XPATH");
	String locator="";
	
	String locatorType="";
	
	GooglePageObjects(String locator, String locatorType) {
		this.locator=locator;
		this.locatorType=locatorType;
		
	}
	
	public String getLocator() {
		return locator;
	}
	
	
	public String getLocatorType() {
		return locatorType;
	}

}
