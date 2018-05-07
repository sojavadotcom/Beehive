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

@Controller("AnyihisUser/Login")
@Scope("prototype")
@Namespace("/AnyihisUser")
public class Login extends ActionSupport {
	private static final long serialVersionUID = -2727236401382250745L;

	@Resource private AnyihisUserService anyihisUserService;
	private String sid;
	private String userName;
	private String password;
	private String code;

	@Action("Login")
	public String index() throws Exception {
		super.execute();
		login();
		return null;
	}

	public void login() throws Exception {
		JSONObject result = null;
		try {
			result = anyihisUserService.login(sid, userName, password, code, getRequest());
			result.put("success", true);
		}
		catch(Exception ex) {
			result = new JSONObject();
			result.put("success", false);
			result.put("message", ExceptionUtil.getMessage(ex));
		}
		new Writer(getRequest(), getResponse()).output(result);
	}

	public AnyihisUserService getAnyihisUserService() {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
