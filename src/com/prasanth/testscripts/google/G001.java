package com.prasanth.testscripts.google;

import org.testng.annotations.Test;

import com.prasanth.engine.BaseTest;
import com.prasanth.pages.GooglePage;
import com.prasanth.report.html.Status;
import com.prasanth.utilities.ReadExcel;
import com.prasanth.utilities.Utils;

public class G001 extends BaseTest{
	
	GooglePage googlePage;
	ReadExcel readExcel;
	
	@Test
	public void testGoogle() {
		intializeAllProperty();
	}
	
	

	@Override
	public void executeTestCase() throws Exception {
        Utils.testCaseId=getPageName();
        
        readExcel=new ReadExcel();
        String searchText=readExcel.getTestData("TestData2", "Sheet1", 1, "SearchData");
        
        googlePage=new GooglePage(driver);
        googlePage.clickEnglish();
        googlePage.waitTime(2000);
        googlePage.searchText(searchText);
        googlePage.clickGoogleSearchButton();
        
        
		
	}

	@Override
	public void tearDown() throws Exception {
		if(report.generateIndividualHtmlReport(Utils.testCaseId)) {
			report.addDetailsInReport(Utils.testCaseId, Utils.testCaseId, Utils.totalTimeOfIndividualTest, Status.Fail);
		}else {
			report.addDetailsInReport(Utils.testCaseId, Utils.testCaseId, Utils.totalTimeOfIndividualTest, Status.Pass);
			
		}
		
	}
	

}
