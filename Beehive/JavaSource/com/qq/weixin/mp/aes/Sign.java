package com.qq.weixin.mp.aes;

import com.sojava.beehive.framework.exception.ErrorException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

public class Sign {

	public static Signature sign(String appId, String jsapiTicket, String url) throws ErrorException {
		Signature ret = new Signature();
		String nonceStr = createNonce();
		String timestamp = createTimestamp();
		String signature = "";

		//注意这里参数名必须全部小写，且必须有序
		String string1 = "jsapi_ticket=" + jsapiTicket +
			"&noncestr=" + nonceStr +
			"&timestamp=" + timestamp +
			"&url=" + url;

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		}
		catch (NoSuchAlgorithmException e) {
			throw new ErrorException(Sign.class, e);
		}
		catch (UnsupportedEncodingException e) {
			throw new ErrorException(Sign.class, e);
		}

		ret.setAppId(appId);
		ret.setUrl(url);
		ret.setJsapi_ticket(jsapiTicket);
		ret.setNonceStr(nonceStr);
		ret.setTimestamp(timestamp);
		ret.setSignature(signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();

		return result;
	}

	private static String createNonce() {
		return UUID.randomUUID().toString();
	}

	private static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
