package com.sojava.beehive.framework.log;

import org.apache.log4j.Level;

public class Logger {

	public static void debug(Class<?> instance, String msg) {
		record(Level.DEBUG_INT, instance, msg);
	}

	public static void debug(Class<?> instance, String msg, Throwable t) {
		record(Level.DEBUG_INT, instance, msg, t);
	}

	public static void info(Class<?> instance, String msg) {
		record(Level.INFO_INT, instance, msg);
	}

	public static void info(Class<?> instance, String msg, Throwable t) {
		record(Level.INFO_INT, instance, msg, t);
	}

	public static void warn(Class<?> instance, String msg) {
		record(Level.WARN_INT, instance, msg);
	}

	public static void warn(Class<?> instance, String msg, Throwable t) {
		record(Level.WARN_INT, instance, msg, t);
	}

	public static void error(Class<?> instance, String msg) {
		record(Level.ERROR_INT, instance, msg);
	}

	public static void error(Class<?> instance, Throwable t) {
		record(Level.ERROR_INT, instance, null, t);
	}

	public static void error(Class<?> instance, String msg, Throwable t) {
		record(Level.ERROR_INT, instance, msg, t);
	}

	public static void record(int level, Class<?> instance, String msg) {
		record(level, instance, msg, null);
	}

	public static void record(int level, Class<?> instance, String msg, Throwable t) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(instance);
		if (msg == null && t == null) msg = "";
		else if (msg == null && t != null) msg = t.getMessage();
		else if (msg != null && t != null) msg += "\r\n" + t.getMessage();

		switch (level) {
			case Level.DEBUG_INT:
				logger.debug(msg, t);
				break;
			case Level.INFO_INT:
				logger.info(msg, t);
				break;
			case Level.WARN_INT:
				logger.warn(msg, t);
				break;
			case Level.ERROR_INT:
				logger.error(msg, t);
				break;
			case Level.FATAL_INT:
				logger.fatal(msg, t);
				break;
			default:
				record(Level.TRACE_INT, instance, msg, t);
		}
	}
}
