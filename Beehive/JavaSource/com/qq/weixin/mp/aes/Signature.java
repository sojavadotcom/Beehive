package com.qq.weixin.mp.aes;

public class Signature {

	private String appId;
	private String timestamp;
	private String signature;
	private String jsapi_ticket;
	private String nonceStr;
	private String url;

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
