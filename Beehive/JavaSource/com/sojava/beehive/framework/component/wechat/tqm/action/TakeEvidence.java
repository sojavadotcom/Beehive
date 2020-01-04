package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/TakeEvidence")
@Scope("prototype")
public class TakeEvidence extends ActionSupport {
	private static final long serialVersionUID = 5297527713190499766L;

	private List<Map<String, Object>> list;

	@Action(value = "TakeEvidence.*", results = {
			@Result(name = "index", location = "/welcome.jsp"),
			@Result(name = "CaseHistory", location = "CaseHistoryEvidence.jsp", params = {"list", "%{list}"})
		})
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		try {
			actionName = actionName.split("\\Q.\\E")[1];

			if (actionName.equals("CaseHistory")) {
				list = new ArrayList<Map<String,Object>>();
				for (int i = 1; i <= 50; i ++) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", i);
					item.put("label", "第 " + i + " 项");
					list.add(item);
				}
			} else {
				actionName = "index";
			}
		}
		catch(Exception ex) {
			actionName = "index";
			Logger.error(getClass(), ex);
		}

		return actionName;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

}
