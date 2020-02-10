package com.sojava.beehive.framework.component.data.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;

@Controller("Data/NCP")
@Scope("prototype")
@Namespace("/Data/NCP")
@ParentPackage("json-default")
@Result(name = "error", location = "../../index.jsp", params = {"errmsg", "%{errmsg}"})
public class Entry extends ActionSupport {
	private static final long serialVersionUID = -2380029069271174556L;

	private String errmsg;

	@Actions(value = {
			@Action(value = "Entry.*", results = {
				@Result(name = "welcome", location = "/welcome.jsp"),
				@Result(name = "Goods", location = "Goods/index.jsp"),
				@Result(name = "GoodsExport", location = "Goods/export.jsp"),
				@Result(name = "GoodsAdd", location = "Goods/add.jsp")
			})
		})
	public String index() throws Exception {
		String rest = "welcome";
		try {
			super.execute();
			String actionName = this.getActionContext().getName();
			String[] actions = actionName.split("\\Q.\\E");
			String action = actions.length > 0 ? actions[1] : "welcome";
	
			String[] actionList = {"Goods", "GoodsExport", "GoodsAdd"};
			for (String act : actionList) {
				if (action.equals(act)) rest = action;
			}
		}
		catch(NullPointerException ex) {
			rest = ERROR;
			this.errmsg = "对象为空的错误";
		}
		catch(Exception ex) {
			rest = ERROR;
			this.errmsg = ex.getMessage();
		}

		return rest;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
