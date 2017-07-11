package com.sojava.beehive.framework.component.user.action;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.user.service.UserService;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.ExceptionUtil;

@Controller("User/Login")
@Scope("prototype")
@Namespace("/User")
public class Login extends ActionSupport {
	private static final long serialVersionUID = 7572736280743024096L;
	@Resource private UserService userService;
	private String sid;
	private String userName;
	private String password;
	private String code;

	@Action("Login")
	@Override
	public String input() throws Exception {
		super.execute();
		login();
		return null;
	}

	public void login() throws Exception {
		JSONObject result = null;
		try {
			result = userService.login(sid, userName, password, code, getRequest());
			result.put("success", true);
		}
		catch(Exception ex) {
			result = new JSONObject();
			result.put("success", false);
			result.put("message", ExceptionUtil.getMessage(ex));
		}
		new Writer(getRequest(), getResponse()).output(result);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
