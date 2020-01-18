package com.prasanth.regressionsuite;

import org.testng.annotations.Test;

import com.prasanth.engine.BaseTest;
import com.prasanth.testscripts.google.G001;
import com.prasanth.testscripts.google.G002;
import com.prasanth.utilities.Utils;

public class Suite1 extends BaseTest {
	
	G001 g001;
	G002 g002;

	
	@Test
	public void testRegression() throws Exception {
		initializeRegressionProperty();
	}
	
	@Override
	public void executeTestCase() throws Exception {
		Utils.suiteStartTimeMilliSec=System.currentTimeMillis();
		Utils.strSuiteStartTimeMilliSec=Utils.getCurrentTimeWithCustomizedFormat();
		
		if(!isFailIndividualExecution) {
			g001=new G001();
			g001.testGoogle1();
			
			
		}
		
		if(!isFailIndividualExecution) {
			g002=new G002();
			g002.testGoogle2();
			
			
		}
		
	}

	@Override
	public void tearDown() throws Exception {
		report.createOverallSummaryReportDirectory();
		
	}

}
