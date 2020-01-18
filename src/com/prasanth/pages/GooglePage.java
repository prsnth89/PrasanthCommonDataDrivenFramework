package com.prasanth.pages;

import org.openqa.selenium.WebDriver;

import com.prasanth.pageobjects.GooglePageObjects;
import com.prasanth.utilities.CommonFunctions;
import com.prasanth.utilities.Utils;

public class GooglePage extends CommonFunctions {
	
	public GooglePage(WebDriver driver) {
		super(driver);
		Utils.pageName=getPageName();
		
	}
	
	public void clickEnglish() {
		clickElement(GooglePageObjects.English.getLocator(), GooglePageObjects.English.getLocatorType());
	}
	
	public void searchText(String text) {
		enterTestDataAndUseEnter(GooglePageObjects.Search.getLocator(), GooglePageObjects.Search.getLocatorType(), text);
	}
	public void searchTextInvalid(String text) {
		enterTestDataAndUseEnter(GooglePageObjects.SearchInvalid.getLocator(), GooglePageObjects.SearchInvalid.getLocatorType(), text);
	}
	
	public void clickGoogleSearchButton() {
		clickElement(GooglePageObjects.SearchButton.getLocator(), GooglePageObjects.SearchButton.getLocatorType());
	}
	
	

}
