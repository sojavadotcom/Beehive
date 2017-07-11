package com.sojava.beehive.framework.context;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sojava.beehive.framework.dispatcher.SessionContext;

public class ContextLoaderListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		Enumeration<?> names = context.getInitParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement().toString();
			String value = context.getInitParameter(name);
			System.setProperty(name, value);
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		Enumeration<?> names = context.getInitParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement().toString();
			System.getProperties().remove(name);
		}
	}

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if (session != null) {
			String sessionId = session.getId();
			SessionContext.add(sessionId, session);
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if (session != null) {
			String sessionId = session.getId();
			SessionContext.remove(sessionId);
		}
	}

	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		if (session != null) {
			String sessionId = session.getId();
			SessionContext.add(sessionId, session);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		this.attributeAdded(event);
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		this.attributeAdded(event);
	}
}
