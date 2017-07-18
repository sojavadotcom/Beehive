package com.sojava.beehive.framework.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class FormatUtil implements Serializable {
	private static final long serialVersionUID = -4138275189236384236L;

	public final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public final static DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	public final static DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public final static Date parseDate(String date) throws ParseException, Exception {
		return DATE_FORMAT.parse(date.replaceAll(Pattern.quote("\\"), "-").replaceAll(Pattern.quote("/"), "-"));
	}

	public final static Date parseTime(String time) throws ParseException, Exception {
		return TIME_FORMAT.parse(time);
	}

	public final static Date parseDateTime(String dateTime) throws ParseException, Exception {
		return DATETIME_FORMAT.parse(dateTime.replaceAll(Pattern.quote("\\"), "-").replaceAll(Pattern.quote("/"), "-"));
	}

	public final static int currentYear() {
		return Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
	}

	public final static int currentMonth() {
		return Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
	}

	public final static int currentQuarter() {
		switch (currentMonth()) {
		case 1: return 1;
		case 2: return 1;
		case 3: return 1;
		case 4: return 2;
		case 5: return 2;
		case 6: return 2;
		case 7: return 3;
		case 8: return 3;
		case 9: return 3;
		case 10: return 4;
		case 11: return 4;
		case 12: return 4;
		default: return 0;
		}
	}

	public final static int currentFiscalQuarter() {
		switch (currentMonth()) {
		case 1: return 4;
		case 2: return 4;
		case 3: return 4;
		case 4: return 1;
		case 5: return 1;
		case 6: return 1;
		case 7: return 2;
		case 8: return 2;
		case 9: return 2;
		case 10: return 3;
		case 11: return 3;
		case 12: return 3;
		default: return 0;
		}
	}

	public final static double formatDecimal(double value, int scale) {
		return formatDecimal(value, scale, RoundingMode.HALF_EVEN);
	}

	public final static double formatDecimal(double value, int scale, RoundingMode mode) {
		return formatDecimal(Double.toString(value), scale, mode);
	}

	public final static double formatDecimal(String value, int scale, RoundingMode mode) {
		return new BigDecimal(value).setScale(scale, mode).doubleValue();
	}
}
