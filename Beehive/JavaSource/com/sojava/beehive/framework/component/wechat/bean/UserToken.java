package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;

public class UserToken implements Serializable {
	private static final long serialVersionUID = -5919799292229034827L;

	private String accessToken;
	private Long expiresIn;
	private String refreshToken;
	private String openid;
	private String scope;
	private long start;

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}

}
