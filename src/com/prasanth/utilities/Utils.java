package com.prasanth.utilities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.prasanth.engine.BaseTest;

public class Utils {

	public static String browserName;
	public static String browserVersion;
	public static String platform = "Windows";
	public static String startTime;
	public static long startTimeMilliSec;
	public static long endTimeMilliSec;
	public static String totalTimeOfIndividualTest;
	public static String endTime;
	public static String testCaseId;
	public static String pageName;
	public static String currentUrl;
	public static String unixServerName;
	public static long suiteStartTimeMilliSec;
	public static String strSuiteStartTimeMilliSec;
	public static long suiteEndTimeMilliSec;
	public static String strSuiteEndTimeMilliSec;
	public static String totalOverallSuiteTime;
	public static String xmlOneOffFilePath;
	public static String xmlRecurringFilePath;
	public static String xmlTransactionsFilePath;

	public static String calculateTotalTime(long startTime) {
		long endTime = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00");
		String totalTime = formatter.format((endTime - startTime) / 1000d);
		return totalTime;

	}

	public static String calculateTotalTimeInMinutes(long startTime, long endTime) {

		NumberFormat formatter = new DecimalFormat("#0.00");
		// String totalTime1 = formatter.format((endTime - startTime) / 1000d);
		// System.out.println("Total Time---"+totalTime1);
		String totalTime = formatter.format(((endTime - startTime) / 1000d));

		return totalTime;

	}

	public static String calculateTotalTimeInSecond(long startTime, long endTime) {

		NumberFormat formatter = new DecimalFormat("#0");
		String totalTime1 = formatter.format((endTime - startTime) / 1000d);
		System.out.println("Total Time---" + totalTime1);
		String totalTime = formatter.format(((endTime - startTime) / 1000d));
		return totalTime;

	}

	public static String generateDate() {

		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		date = new Date();
		System.out.println(formatter.format(date));

		return formatter.format(date);
	}

	public static String getCorporateId() {
		return BaseTest.properties.getProperty("corportateid");
	}

	public static String getEnvironment() {
		return BaseTest.properties.getProperty("environment");
	}

	public static String getCurrentTime() {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
		date = new Date();
		System.out.println(formatter.format(date));
		return formatter.format(date);
	}

	public static String getCurrentTimeWithCustomizedFormat() {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		date = new Date();
		System.out.println(formatter.format(date));
		return formatter.format(date);
	}

	public static String getTotalTime() {
		return calculateTotalTime(Utils.startTimeMilliSec);
	}

	public static String getTotalTimeInMinutes(long startTimeInMilliSec, long endTimeInMilliSec) {
		return calculateSecToMin(startTimeInMilliSec, endTimeInMilliSec);
	}

	public static String getTotalTimeInSeconds(long startTimeInMilliSec, long endTimeInMilliSec) {
		return calculateTotalTimeInSecond(startTimeInMilliSec, endTimeInMilliSec);
	}

	public static String secondToMin(long sec) {
		/*
		 * long minute=sec/60; //System.out.println(minute); long min=sec%60; String
		 * totalTime=String.format("%02d:%02d:%02d", minute,min);
		 * System.out.println(totalTime);
		 */

		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(sec),
				TimeUnit.MILLISECONDS.toMinutes(sec) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(sec)),
				TimeUnit.MILLISECONDS.toSeconds(sec)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sec)));
		System.out.println(hms);

		return String.valueOf(hms);
	}

	public static String calculateSecToMin(long startTime, long endTime) {

		// NumberFormat formatter = new DecimalFormat("#0.00");
		// long totTime=(long) ((endTime - startTime) / 1000d);
		long totTime = (long) ((endTime - startTime));
		System.out.println("Total Time--" + totTime);
		return secondToMin(totTime);
		// String totalTime = formatter.format(((endTime - startTime) / 1000d));

	}

	public static String getCurrentTimeWithCustomizedFormatWithoutSeperater() {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmssSSS");
		date = new Date();
		System.out.println(formatter.format(date));
		return formatter.format(date);
	}

}
