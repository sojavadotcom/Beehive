package com.sojava.beehive.framework.component.worksheet.action;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.user.bean.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("Worksheet/TestStrutsJson")
@Scope("prototype")
@Namespace("/Worksheet/TestStrutsJson")
@ParentPackage("json-default")
public class TestStrutsJson extends ActionSupport {
	private static final long serialVersionUID = 5175428737199627423L;

	private Map<String, Object> result;

	public TestStrutsJson() {
		result = new HashMap<String, Object>();
	}

	@Action(value = "index", results = {
		@Result(name = SUCCESS, type = "json", params = {"root", "result"})
	})
	public String index() throws Exception {
		super.execute();
		result.clear();
		result.put("userInfo", getUserInfo());
		List<UserInfo> list = new ArrayList<UserInfo>();
		list.add(getUserInfo());list.add(getUserInfo());
		result.put("List", list);
		result.put("array", list.toArray(new UserInfo[0]));
		result.put("aa", 123);

		return SUCCESS;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public Map<String, Object> getResult() {
		return this.result;
	}
}
