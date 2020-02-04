package com.sojava.beehive.framework.util;

public class ValueUtil {

	public static double get(Object val, double defaultVal) {
		if (val == null) return defaultVal;
		else return Double.parseDouble(val.toString());
	}

	public static int get(Object val, int defaultVal) {
		if (val == null) return defaultVal;
		else return Integer.parseInt(val.toString());
	}

	public static String get(Object val, String defaultVal) {
		if (val == null) return defaultVal;
		else return val.toString();
	}
}
