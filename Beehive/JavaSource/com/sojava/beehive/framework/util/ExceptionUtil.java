package com.sojava.beehive.framework.util;

public class ExceptionUtil {
	public static String getMessage(Throwable t) {
		return t.getLocalizedMessage() == null ? t.toString() : t.getLocalizedMessage();
	}
}
