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
public class Entry extends ActionSupport {
	private static final long serialVersionUID = -2380029069271174556L;

	@Actions(value = {
			@Action(value = "Entry.*", results = {
				@Result(name = "welcome", location = "/welcome.jsp"),
				@Result(name = "Goods", location = "Goods/index.jsp"),
				@Result(name = "GoodsExport", location = "Goods/export.jsp"),
				@Result(name = "GoodsAdd", location = "Goods/add.jsp")
			})
		})
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		String[] actions = actionName.split("\\Q.\\E");
		String action = actions.length > 0 ? actions[1] : "welcome";
		String rest = "welcome";

		String[] actionList = {"Goods", "GoodsExport", "GoodsAdd"};
		for (String act : actionList) {
			if (action.equals(act)) rest = action;
		}

		return rest;
	}

}
