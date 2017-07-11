package com.sojava.beehive.framework.component.user.service;

import javax.servlet.http.HttpServletRequest;

import com.sojava.beehive.framework.component.user.bean.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface UserService {

	JSONObject login(String sessionId, String userName, String password, String code, HttpServletRequest request) throws Exception;
	boolean passwordIsInit(String userName) throws Exception;
	void logout(String sessionId, HttpServletRequest request) throws Exception;
	JSONObject nameByUserName(String userName) throws Exception;
	JSONArray depts(String label) throws Exception;
	JSONArray users(String label) throws Exception;
	JSONObject validUser(UserInfo user, String password) throws Exception;
	void changePassword(UserInfo user, String oldPassword, String password) throws Exception;
}