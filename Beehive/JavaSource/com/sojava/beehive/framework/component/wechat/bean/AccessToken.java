package com.sojava.beehive.framework.component.wechat.bean;

public class AccessToken {

	private String accessToken;
	private Integer expiresin;

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresin() {
		return expiresin;
	}
	public void setExpiresin(Integer expiresin) {
		this.expiresin = expiresin;
	}

}
