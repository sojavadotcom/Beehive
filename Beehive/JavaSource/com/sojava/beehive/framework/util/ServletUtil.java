package com.sojava.beehive.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

	public static String[] getUriParameter(HttpServletRequest request) {
		return getUriParameter(request, ".");
	}

	public static String[] getUriParameter(HttpServletRequest request, String separator) {
		String[] params = null;
		String uri = request.getRequestURI();
		uri = uri.indexOf("/") == 0 ? uri.substring(1) : uri;
		params = uri.split(Pattern.quote(separator));

		return params;
	}

	public static String getServletParameter(ServletRequest request, String name, String defaultValue) {
		String value = null;
		try {
			value = request.getParameter(name);
			if (value == null) value = defaultValue;
			else value = URLDecoder.decode(value, System.getProperty("system.encoding"));
		}
		catch(UnsupportedEncodingException ex) {}

		return value;
	}

	public static String[] getQueryParameter(HttpServletRequest request, String separator) {
		String[] params = null;
		String query = request.getQueryString();
		params = query.split(Pattern.quote(separator));

		return params;
	}

	public static boolean containsParameterByUriQuery(HttpServletRequest request, String regex) {
		return Pattern.compile(regex).matcher(request.getQueryString()).find();
	}

	public static String getParameterByURIQuery(HttpServletRequest request, String regex) {
		String param = null;
		try {
			Matcher m = Pattern.compile(regex).matcher(request.getQueryString());
			if (m.find()) {
				param = m.group();
			}
		}
		catch(Exception ex) {}

		return param;
	}
}
