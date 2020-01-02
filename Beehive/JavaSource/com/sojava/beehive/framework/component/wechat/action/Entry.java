package com.sojava.beehive.framework.component.wechat.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;

@Controller("WeChat/Entry")
@Scope("prototype")
@Namespace("/WeChat")
public class Entry extends ActionSupport {
	private static final long serialVersionUID = 5297527713190499766L;

	private Long timestamp;

	@Action(value = "Entry.*", results = {
			@Result(name = "index", location = "index.jsp"),
			@Result(name = "camera", location = "camera.jsp", params = {"timestamp"})
		})
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		if (actionName.split("\\Q.\\E").length > 1) actionName = actionName.split("\\Q.\\E")[1];
		else actionName = "index";

		if (actionName.equalsIgnoreCase("camera")) {
			getRequest().setAttribute("appId", "wxd5b66e9aa4391045");
			this.timestamp = System.currentTimeMillis();
			getRequest().setAttribute("nonceStr", "5512701662590795775");
			getRequest().setAttribute("nonce", "382127749");
		}
		return actionName;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}


}
