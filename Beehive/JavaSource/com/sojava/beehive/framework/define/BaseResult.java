package com.sojava.beehive.framework.define;

public class BaseResult {
	private boolean success;
	private String message;

	protected BaseResult() {}

	public BaseResult(boolean success, String message) {
		setSuccess(success);
		setMessage(message);
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
