package com.sojava.beehive.framework.exception;

import com.sojava.beehive.framework.log.Logger;

@SuppressWarnings("serial")
public class WarnException extends CommonException {
	public WarnException(Class<?> instance, Throwable t) {
		super(instance, t);
		Logger.warn(instance, null, t);
	}

	public WarnException(Class<?> instance, String msg) {
		super(instance, msg);
		Logger.warn(instance, msg);
	}

	public WarnException(String msg) {
		super(msg);
	}
}
