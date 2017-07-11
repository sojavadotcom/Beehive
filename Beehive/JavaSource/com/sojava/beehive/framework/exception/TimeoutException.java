package com.sojava.beehive.framework.exception;

public class TimeoutException extends CommonException {

	private static final long serialVersionUID = -6892697115657906799L;

	public TimeoutException(Throwable t) {
		super(t);
	}

	public TimeoutException(String msg) {
		super(msg);
	}

}
