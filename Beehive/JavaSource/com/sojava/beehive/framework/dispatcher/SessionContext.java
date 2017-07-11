package com.sojava.beehive.framework.dispatcher;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionContext {
	private static Map<String, HttpSession> sessionInstances = null;
	private synchronized static void init() {
		if (sessionInstances == null)
			sessionInstances = new HashMap<String, HttpSession>();
	}
	public synchronized static void add(String sessionId, HttpSession session) {
		init();
		sessionInstances.put(sessionId, session);
	}
	public synchronized static HttpSession get(String sessionId) {
		if (sessionInstances == null) return null;
		return sessionInstances.get(sessionId);
	}
	public synchronized static void remove(String sessionId) {
		init();
		if (sessionInstances.containsKey(sessionId)) sessionInstances.remove(sessionId);
	}
}
