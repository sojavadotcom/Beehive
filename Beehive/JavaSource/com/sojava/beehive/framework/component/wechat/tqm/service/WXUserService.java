package com.sojava.beehive.framework.component.wechat.tqm.service;

import com.sojava.beehive.framework.exception.ErrorException;

public interface WXUserService {

	void getToken(String code, String state) throws ErrorException;
	void refreshToken(String accessToken) throws ErrorException;
}
