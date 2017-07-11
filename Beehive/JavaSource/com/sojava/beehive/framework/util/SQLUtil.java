package com.sojava.beehive.framework.util;

public class SQLUtil {

	public static String concatWhere(String where1, String where2) {
		return concatWhere(where1, where2, "and");
	}

	public static String concatWhere(String where1, String where2, String operator) {
		where1 = where1.trim();
		where1 = where1.indexOf("and ") == 0 ? where1.substring(4) : where1;
		where2 = where2.trim();
		where2 = where2.indexOf("and ") == 0 ? where2.substring(4) : where2;
		return "(" + where1 + ") " + operator + " (" + where2 + ")";
	}
}
