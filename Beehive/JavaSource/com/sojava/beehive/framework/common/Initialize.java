package com.sojava.beehive.framework.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class Initialize extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println(config.getServletContext().getAttribute("system.encoding"));
	}
}
