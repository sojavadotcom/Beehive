package cn.jxszyyy.anyihis.user.action;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jxszyyy.anyihis.user.service.AnyihisUserService;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.ExceptionUtil;

@Controller("AnyihisUser/Logout")
@Scope("prototype")
@Namespace("/AnyihisUser")
public class Logout extends ActionSupport {
	private static final long serialVersionUID = 639162791456834602L;

	@Resource private AnyihisUserService anyihisUserService;
	private String sid;

	@Action("Logout")
	public String index() throws Exception {
		super.execute();
		logout();
		return null;
	}

	public void logout() throws Exception {
		JSONObject result = new JSONObject();
		try {
			anyihisUserService.logout(sid, getRequest());
			result.put("success", true);
		}
		catch(Exception ex) {
			result.put("success", true);
			result.put("message", ExceptionUtil.getMessage(ex));
		}
		new Writer(getRequest(), getResponse()).output(result);
	}

	public AnyihisUserService getUserService() {
		return this.anyihisUserService;
	}

	public void setAnyihisUserService(AnyihisUserService anyihisUserService) {
		this.anyihisUserService = anyihisUserService;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
