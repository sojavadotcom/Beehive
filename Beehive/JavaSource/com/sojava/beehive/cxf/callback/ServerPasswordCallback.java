package com.sojava.beehive.cxf.callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;

public class ServerPasswordCallback implements CallbackHandler {

	Logger log = Logger.getLogger(ServerPasswordCallback.class);
	Map<String, String> user = new HashMap<String, String>();
	{
		user.put("admin", "1234");
		user.put("su", "1234");
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

		WSPasswordCallback wpc = (WSPasswordCallback) callbacks[0];
		if (!user.containsKey(wpc.getIdentifier())) {
			throw new SecurityException("无权限");
		}
		wpc.setPassword(user.get(wpc.getIdentifier()));
	}
}
