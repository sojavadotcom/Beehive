package com.sojava.beehive.framework.dispatcher;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

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
