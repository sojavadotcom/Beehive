package com.sojava.beehive.framework.component.user.action;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.user.service.UserService;
import com.sojava.beehive.framework.exception.TimeoutException;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.ExceptionUtil;

@Controller("User/Password")
@Scope("prototype")
@Namespace("/User")
public class Password extends ActionSupport {

	private static final long serialVersionUID = -2102775927543873998L;
	@Resource private UserService userService;
	private String action;
	private String password;
	private String oldPassword;

	@Action("Password")
	@Override
	public String input() throws Exception {
		super.execute();
		password();
		return null;
	}

	public void password() throws Exception {
		JSONObject result = null;
		try {
			if (getUserInfo() == null) throw new TimeoutException("登录超时");
			if (getAction().equals("validPassword")) {
				result = userService.validUser(getUserInfo(), password);
			} else if (getAction().equals("save")) {
				userService.changePassword(getUserInfo(), oldPassword, password);
				result = new JSONObject();
			} else {
				throw new Exception("不能识别的操作");
			}
			result.put("success", true);
		}
		catch(TimeoutException ex) {
			result = new JSONObject();
			result.put("success", false);
			result.put("loginTimeout", true);
			result.put("message", ExceptionUtil.getMessage(ex));
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

	public String getAction() {
		return action == null ? "" : action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
