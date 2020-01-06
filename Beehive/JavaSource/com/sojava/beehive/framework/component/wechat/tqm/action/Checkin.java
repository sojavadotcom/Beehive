package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.log.Logger;
import com.sojava.beehive.framework.io.Writer;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/Checkin")
@Scope("prototype")
public class Checkin extends ActionSupport {
	private static final long serialVersionUID = -7769517762177631734L;

	private String url;

	@Action("Checkin")
	public String index() throws Exception {
		super.execute();
		JSONObject rest = new JSONObject();
		rest.put("success", true);
		rest.put("errmsg", "ok");
		try {
		}
		catch(Exception ex) {
			Logger.error(getClass(), ex);
			rest.put("success", false);
			rest.put("errmsg", ex.getMessage() == null ? "NullPointer" : ex.getMessage());
		}
		finally {
			new Writer(getRequest(), getResponse()).output(rest);
		}

		return null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
