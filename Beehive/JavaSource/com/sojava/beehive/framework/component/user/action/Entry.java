package com.sojava.beehive.framework.component.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;

@Controller("User/Entry")
@Scope("prototype")
@Namespace("/User")
public class Entry extends ActionSupport {

	private static final long serialVersionUID = 8469604168969899250L;
	private String action;

	@Action(value = "Entry", results = {
		@Result(name = "login", location = "login.jsp"),
		@Result(name = "password", location = "password.jsp")
	})
	public String index() throws Exception {
		super.execute();
		return action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
