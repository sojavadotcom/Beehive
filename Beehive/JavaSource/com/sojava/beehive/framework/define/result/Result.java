package com.sojava.beehive.framework.define.result;

import java.util.List;

import com.sojava.beehive.framework.util.ExceptionUtil;

public class Result {
	private boolean success;
	private String code;
	private String message;
	private List<Object> data;

	protected Result() {}

	public Result(boolean success) {
		this.success = success;
		this.code = this.success ? "0001" : "1000";
	}
	public Result(boolean success, String message) {
		this.success = success;
		this.code = this.success ? "0001" : "1000";
		this.message = message;
	}
	public Result(boolean success, String code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}
	public Result(Throwable t) {
		this.success = false;
		this.code = "1000";
		this.message = ExceptionUtil.getMessage(t);
	}
	public Result(Throwable t, String code) {
		this.success = false;
		this.code = code;
		this.message = t.getMessage() == null ? t.toString() : t.getMessage();
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
}
