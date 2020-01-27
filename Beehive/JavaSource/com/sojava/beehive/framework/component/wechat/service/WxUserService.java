package com.sojava.beehive.framework.component.wechat.service;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.component.wechat.define.Platform;
import com.sojava.beehive.framework.component.wechat.define.WxScope;
import com.sojava.beehive.framework.exception.ErrorException;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface WxUserService {

	UserToken getToken(String appid, String secret, String code) throws ErrorException;
	UserToken refreshToken(String appid, String accessToken) throws ErrorException;
	WxUserInfo getWxUserInfo(String accessToken, String openid) throws ErrorException;
	void signup(UserToken userToken, WxUserInfo wxUserInfo, Integer staffId, String staffName, String adminDuty, String jobTitle, String mobileShortNumber, String mobileNumber, String deptName, Platform platform, String role, String status)  throws ErrorException;
	void signup(User user) throws ErrorException;
	void updateWxUserInfo(String appid, String accessToken) throws ErrorException;
	void updateStaffInfo(String accessToken, String openid, Integer staffId, String staffName, String adminDuty, String jobTitle, String mobileShortNumber, String mobileNumber, String deptName, Platform platform, String role, String status) throws ErrorException;
	boolean verifyToken(String accessToken, String openid) throws ErrorException;
	User[] listUser(Criterion[] filters, Order[] orders) throws ErrorException;
	User getUser(String openid) throws ErrorException;
	User checkWxUser(HttpServletResponse response, String redirectURL, String appid, String secret, WxScope scope, String code, String state, Platform platform) throws ErrorException;
}
