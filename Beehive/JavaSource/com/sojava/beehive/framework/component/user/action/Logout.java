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

@Controller("User/Logout")
@Scope("prototype")
@Namespace("/User")
public class Logout extends ActionSupport {
	private static final long serialVersionUID = 2032890619549075117L;
	@Resource private UserService userService;
	private String sid;

	@Action("Logout")
	public String index() throws Exception {
		super.execute();
		logout();
		return null;
	}

	public void logout() throws Exception {
		JSONObject result = new JSONObject();
		try {
			userService.logout(sid, getRequest());
			result.put("success", true);
		}
		catch(Exception ex) {
			result.put("success", true);
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

}
