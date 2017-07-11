package cn.jxszyyy.anyihis.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;

@Controller("AnyihisUser/Entry")
@Scope("prototype")
@Namespace("/AnyihisUser")
public class Entry extends ActionSupport {
	private static final long serialVersionUID = -4069094273031419541L;

	private String action;

	@Action(value = "Entry", results = {
		@Result(name = "login", location = "login.jsp"),
		@Result(name = "password", location = "password.jsp")
	})
	@Override
	public String input() throws Exception {
		super.execute();
		return action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
