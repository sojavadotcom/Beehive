package com.sojava.beehive.framework.component.wechat.service;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.exception.ErrorException;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface UserService {

	UserToken getToken(String appid, String secret, String code) throws ErrorException;
	UserToken refreshToken(String appid, String accessToken) throws ErrorException;
	WxUserInfo getUserinfo(String accessToken, String openid) throws ErrorException;
	void Signup(UserToken userToken, WxUserInfo wxUserInfo, Integer staffId, String staffName, String mobileNumber, String deptName, String platform, String role)  throws ErrorException;
	void updateWxUserInfo(String accessToken, String openid) throws ErrorException;
	void updateStaffInfo(Integer staffId, String staffName, String mobileNumber, String deptName, String platform, String role) throws ErrorException;
	boolean verifyToken(String accessToken, String openid) throws ErrorException;
	User[] listUser(Criterion[] filters, Order[] orders) throws ErrorException;
	User getUser(String openid, String platform) throws ErrorException;
}
