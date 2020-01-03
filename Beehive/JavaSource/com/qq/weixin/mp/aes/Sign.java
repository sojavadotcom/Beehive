package com.qq.weixin.mp.aes;

import com.sojava.beehive.framework.log.Logger;

import java.util.UUID;

public class Sign {

	public static Signature sign(String appId, String jsapiTicket, String url) {
		Signature ret = new Signature();
		String nonceStr = createNonce();
		String timestamp = createTimestamp();
//		String string1;
		String signature = "";

		//注意这里参数名必须全部小写，且必须有序
//		string1 = "jsapi_ticket=" + jsapi_ticket +
//			"&noncestr=" + nonce_str +
//			"&timestamp=" + timestamp +
//			"&url=" + url;
//		Logger.debug(getcl, string1);

		try {
			signature = SHA1.getSHA1(jsapiTicket, timestamp, nonceStr, url);
		}
		catch(AesException e) {
			Logger.error(Sign.class, e);
		}
//		try {
//
//			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//			crypt.reset();
//			crypt.update(string1.getBytes("UTF-8"));
//			signature = byteToHex(crypt.digest());
//		}
//		catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}

		ret.setAppId(appId);
		ret.setUrl(url);
		ret.setJsapi_ticket(jsapiTicket);
		ret.setNonceStr(nonceStr);
		ret.setTimestamp(timestamp);
		ret.setSignature(signature);

		return ret;
	}

//	private static String byteToHex(final byte[] hash) {
//		Formatter formatter = new Formatter();
//		for (byte b : hash) {
//			formatter.format("%02x", b);
//		}
//		String result = formatter.toString();
//		formatter.close();
//
//		return result;
//	}

	private static String createNonce() {
		return UUID.randomUUID().toString();
	}

	private static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
