package com.sojava.beehive.framework.component.menu.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;

@Controller("Menu/Entry")
@Scope("prototype")
@Namespace("/Menu")
public class Entry extends ActionSupport {

	private static final long serialVersionUID = 8469604168969899250L;

	@Action(value = "Entry", results = {
		@Result(name = SUCCESS, location = "index.jsp")
	})
	public String index() throws Exception {
		super.execute();
		return SUCCESS;
	}

}
