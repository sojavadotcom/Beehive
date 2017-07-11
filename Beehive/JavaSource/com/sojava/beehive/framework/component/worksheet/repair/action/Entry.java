package com.sojava.beehive.framework.component.worksheet.repair.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;

@Controller("Worksheet/Repair/Entry")
@Scope("prototype")
@Namespace("/Worksheet/Repair")
public class Entry extends ActionSupport {

	private static final long serialVersionUID = 1696067306770607507L;
	private String action;

	@Action(value = "Entry", results = {
		@Result(name = "index", location = "index.jsp"),
		@Result(name = "preview", location = "preview.jsp")
	})
	@Override
	public String input() throws Exception {
		super.execute();
		return getAction();
	}

	public String getAction() {
		return action == null ? "index" : action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
