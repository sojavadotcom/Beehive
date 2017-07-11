package com.sojava.beehive.framework.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtil {

	public static boolean ping(String host, Integer timeout) {
		boolean result = false;
		try {
			InetAddress inetAddress = InetAddress.getByName(host);
			result = true;
			if (timeout != null) result = inetAddress.isReachable(timeout);
		}
		catch (UnknownHostException ex) {}
		catch (IOException ex) {}

		return result;
	}
}
