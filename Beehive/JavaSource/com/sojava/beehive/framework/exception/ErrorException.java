package com.sojava.beehive.framework.exception;

import javax.servlet.http.HttpServletRequest;

import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.framework.log.Logger;

public class ErrorException extends CommonException {
	private static final long serialVersionUID = -7660611658717730181L;

	public ErrorException(Throwable t) {
		super(t);
	}

	public ErrorException(String message) {
		super(message);
	}

	public ErrorException(Class<?> instance, Throwable t) {
		super(instance, t);
		Logger.error(instance, null, t);
	}

	public ErrorException(Class<?> instance, String message) {
		super(instance, message);
		Logger.error(instance, message);
	}

	public ErrorException(HttpServletRequest httpRequest, UserInfo userInfo, Class<?> instance, Throwable t) {
		super(instance, t);
		Logger.error(instance, (httpRequest != null ? httpRequest.getRemoteAddr() : "") + "\r\n" + (userInfo != null ? userInfo.getUserId() + "," + userInfo.getUserName() + "," + userInfo.getName() : "") + "\r\n" + (httpRequest != null ? (httpRequest.getRequestURI() != null ? httpRequest.getRequestURI() : "") + ";" + (httpRequest.getQueryString() != null ? httpRequest.getQueryString() : "") : "") + "\r\n", t);
	}

	public ErrorException(HttpServletRequest httpRequest, UserInfo userInfo, Class<?> instance, String msg) {
		super(instance, msg);
		Logger.error(instance, (httpRequest != null ? httpRequest.getRemoteAddr() : "") + "\r\n" + (userInfo != null ? userInfo.getUserId() + "," + userInfo.getUserName() + "," + userInfo.getName() : "") + "\r\n" + (httpRequest != null ? (httpRequest.getRequestURI() != null ? httpRequest.getRequestURI() : "") + ";" + (httpRequest.getQueryString() != null ? httpRequest.getQueryString() : "") : "") + "\r\n" + msg);
	}
}
