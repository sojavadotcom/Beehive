package cn.jxszyyy.anyihis.user.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jxszyyy.anyihis.user.service.AnyihisUserService;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.io.Writer;

@Controller("AnyihisUser/Query")
@Scope("prototype")
@Namespace("/AnyihisUser")
public class Query extends ActionSupport {
	private static final long serialVersionUID = 6581634740084642790L;

	@Resource private AnyihisUserService anyihisUserService;
	private String action;
	private String value;
	private String label;

	@Action("Query")
	public String index() throws Exception {
		super.execute();
		query();
		return null;
	}

	public void query() throws Exception {

		Writer out = new Writer(getRequest(), getResponse());
		if (getAction().equals("depts")) {
			String keyword = getValue().equals("") ? getLabel() : getValue();
			out.output(anyihisUserService.depts(keyword));
		} else if (getAction().equals("users")) {
			String keyword = getValue().equals("") ? getLabel() : getValue();
			out.output(anyihisUserService.users(keyword));
		} else if (getAction().equals("nameByUserName")) {
			out.output(anyihisUserService.nameByUserName(value));
		}
	}

	public AnyihisUserService getAnyihisUserService() {
		return this.anyihisUserService;
	}

	public void setAnyihisUserService(AnyihisUserService anyihisUserService) {
		this.anyihisUserService = anyihisUserService;
	}

	public String getAction() {
		return action == null ? "" : action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getValue() {
		return value == null ? "" : value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label == null ? "" : label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
