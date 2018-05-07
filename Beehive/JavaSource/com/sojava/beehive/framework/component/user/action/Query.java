package com.sojava.beehive.framework.component.user.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.user.service.UserService;
import com.sojava.beehive.framework.io.Writer;

@Controller("User/Query")
@Scope("prototype")
@Namespace("/User")
public class Query extends ActionSupport {

	private static final long serialVersionUID = -1366822693428487555L;
	@Resource private UserService userService;
	private String action;
	private String value;
	private String label;

	@Action("Query.*")
	public String index() throws Exception {
		super.execute();
		query();
		return null;
	}

	public void query() throws Exception {

		String actionName = this.getActionContext().getName();
		if (actionName.split("\\Q.\\E").length > 1) actionName = actionName.split("\\Q.\\E")[1];
		if (actionName.equalsIgnoreCase("name")) {
			setAction("nameByUserName");
		}

		Writer out = new Writer(getRequest(), getResponse());
		if (getAction().equals("depts")) {
			String keyword = getValue().equals("") ? getLabel() : getValue();
			out.output(userService.depts(keyword));
		} else if (getAction().equals("users")) {
			String keyword = getValue().equals("") ? getLabel() : getValue();
			out.output(userService.users(keyword));
		} else if (getAction().equals("nameByUserName")) {
			out.output(userService.nameByUserName(value));
		}
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

	public String getValue() {
		return value == null ? "" : value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label == null ? "" : label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
