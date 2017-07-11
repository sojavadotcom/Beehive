package com.sojava.beehive.framework.util.security;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8870953677404215117L;
	public static final String MD2(String msg) throws NoSuchAlgorithmException {
		return encrypt(msg, EncryptType.MD2);
	}
	public static final String MD5(String msg) throws NoSuchAlgorithmException {
		return encrypt(msg, EncryptType.MD5);
	}
	public static final String SHA(String msg) throws NoSuchAlgorithmException {
		return encrypt(msg, EncryptType.SHA);
	}
	public static final String SHA1(String msg) throws NoSuchAlgorithmException {
		return encrypt(msg, EncryptType.SHA1);
	}
	public static final String SHA256(String msg) throws NoSuchAlgorithmException {
		return encrypt(msg, EncryptType.SHA256);
	}
	public static final String SHA384(String msg) throws NoSuchAlgorithmException {
		return encrypt(msg, EncryptType.SHA384);
	}
	public static final String SHA512(String msg) throws NoSuchAlgorithmException {
		return encrypt(msg, EncryptType.SHA512);
	}
	public static final String encrypt(String msg, EncryptType et) throws NoSuchAlgorithmException {
		MessageDigest md = null;
		switch (et) {
		case MD2:
			md = MessageDigest.getInstance("MD2");
			break;
		case MD5:
			md = MessageDigest.getInstance("MD5");
			break;
		case SHA:
			md = MessageDigest.getInstance("SHA");
			break;
		case SHA1:
			md = MessageDigest.getInstance("SHA-1");
			break;
		case SHA256:
			md = MessageDigest.getInstance("SHA-256");
			break;
		case SHA384:
			md = MessageDigest.getInstance("SHA-384");
			break;
		case SHA512:
			md = MessageDigest.getInstance("SHA-512");
			break;
		}
		md.update(msg.getBytes());
		String str = "";
		for (byte b: md.digest()) {
			String code = Integer.toHexString(b & 0xFF);
			str += code.length() == 1 ? "0" + code : code;
		}
		md.reset();

		return str;
	}

	public static String decrypt(String password) {
		String result = "";
		try {
			password = password.trim().replaceAll("A0", "00").replaceAll("A9", "01").replaceAll("B8", "02");
			String[] passwordGroup = password.split("\\ ");
			int j = 0;
			for (int i = 0; i < passwordGroup.length; i ++) {
				int pwd = Integer.parseInt(passwordGroup[i]+passwordGroup[++i]);
				int charCode = pwd >> (j++%2 == 0 ? 0 : 1);
				result += (char) charCode;
			}
		}
		catch(Exception ex) {}

		return result;
	}
}
