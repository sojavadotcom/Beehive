package com.sojava.beehive.framework.component.wechat.action;

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
public class TQMEntry extends ActionSupport {
	private static final long serialVersionUID = 5297527713190499766L;

	@Action(value = "Entry.*", results = {
			@Result(name = "index", location = "/welcome.jsp"),
			@Result(name = "camera", location = "camera.jsp"/*, params = {"appId", "%{appId}", "timestamp", "%{timestamp}", "nonce", "%{nonce}", "nonceStr", "%{nonceStr}"}*/)
		})
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		try {
			actionName = actionName.split("\\Q.\\E")[1];

			if (actionName.equalsIgnoreCase("camera")) {
			} else {
				actionName = "index";
			}
		}
		catch(Exception ex) {
			actionName = "index";
			Logger.error(getClass(), ex);
		}

		return actionName.toLowerCase();
	}

}
