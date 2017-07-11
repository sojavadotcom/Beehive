package com.sojava.beehive.framework.common;

import java.util.Calendar;
import java.util.Properties;

public class Fiscal {

	public static int year;
	public static int month;
	public static int quarter;
	public static Properties monthVsQuarter = null;

	public static void calculate() {
		init();
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		quarter = Integer.parseInt(monthVsQuarter.getProperty(month+""));
		if (quarter == 4) year --;
	}

	private static void init() {
		if (monthVsQuarter == null) {
			monthVsQuarter = new Properties();
			monthVsQuarter.setProperty("1", "4");
			monthVsQuarter.setProperty("2", "4");
			monthVsQuarter.setProperty("3", "4");
			monthVsQuarter.setProperty("4", "1");
			monthVsQuarter.setProperty("5", "1");
			monthVsQuarter.setProperty("6", "1");
			monthVsQuarter.setProperty("7", "2");
			monthVsQuarter.setProperty("8", "2");
			monthVsQuarter.setProperty("9", "2");
			monthVsQuarter.setProperty("10", "3");
			monthVsQuarter.setProperty("11", "3");
			monthVsQuarter.setProperty("12", "3");
		}
	}

}
