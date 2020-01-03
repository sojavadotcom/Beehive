package com.sojava.beehive.framework.component.wechat.bean;

public class JsapiTicket {

	private String jsapiTicket;
	private Integer expiresin;

	public String getJsapiTicket() {
		return jsapiTicket;
	}
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	public int getExpiresin() {
		return expiresin;
	}
	public void setExpiresin(Integer expiresin) {
		this.expiresin = expiresin;
	}

}
