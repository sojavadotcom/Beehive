package com.sojava.beehive.common.identifier;


//Universally Unique Identifier
public class UUID {
	public static String getId() {
		return java.util.UUID.randomUUID().toString().replaceAll("\\-", "");
	}
}
