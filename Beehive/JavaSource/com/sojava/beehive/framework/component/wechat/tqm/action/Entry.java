package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.log.Logger;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/Entry")
@Scope("prototype")
public class Entry extends ActionSupport {
	private static final long serialVersionUID = -8555460271356972319L;

	private Integer step;

	@Action(value = "Entry.*", results = {
			@Result(name = "index", location = "/welcome.jsp"),
			@Result(name = "Checkin", location = "Checkin.jsp", params = {"step", "%{step}"})
		})
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		try {
			actionName = actionName.split("\\Q.\\E")[1];

			if (actionName.equals("Checkin")) {
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

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

}
