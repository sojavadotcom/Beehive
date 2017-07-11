package com.sojava.beehive.framework.component.home.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.user.service.UserService;

@Controller("Index")
@Scope("prototype")
@Namespace("/")
public class Index extends ActionSupport {
	private static final long serialVersionUID = 5227203157732501368L;

	@Resource private UserService userService;
	private boolean online;
	private boolean PInit;

	@Override
	@Actions(value = {
		@Action(
			value = "index",
			results = {
				@Result(name = SUCCESS, location = "index.jsp")
			}
		),
		@Action(
			value = "welcome",
			results = {
				@Result(name = SUCCESS, location = "welcome.jsp")
			}
		)
	})
	public String input() throws Exception {
		super.execute();
		return (String) this.getClass().getMethod(getActionContext().getName(), new Class[0]).invoke(this, new Object[0]);
	}

	public String welcome() throws Exception {
		super.execute();
		return SUCCESS;
	}

	public String index() throws Exception {
		setOnline(getUserInfo() != null);
		if (isOnline()) setPInit(userService.passwordIsInit(getUserInfo().getUserName()));
		else setPInit(false);

		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isPInit() {
		return PInit;
	}

	public void setPInit(boolean pInit) {
		PInit = pInit;
	}
}
