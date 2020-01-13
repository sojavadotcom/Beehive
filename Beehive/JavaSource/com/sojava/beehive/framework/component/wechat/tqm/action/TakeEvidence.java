package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.log.Logger;
import com.sojava.beehive.framework.io.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/TakeEvidence")
@Scope("prototype")
public class TakeEvidence extends ActionSupport {
	private static final long serialVersionUID = 5297527713190499766L;

	private List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	private String code;
	private Integer itemNum;
	private String itemLabel;
	private String data;
	private String wxId;
	private String wxName;

//	@Actions(value = {
//		@Action(value = "TakeEvidence.Put"),
//	})
	/*
	@Action(value = "TakeEvidence.*", results = {
			@Result(name = "index", location = "/welcome.jsp"),
			@Result(name = "CaseHistory", location = "CaseHistoryEvidence.jsp", params = {"list", "%{list}"}),
			@Result(name = "Put")
		})*/
	public String input() throws Exception {
System.out.println("======----input()-----=============");
return null;
/*
		super.execute();
		String actionName = this.getActionContext().getName();
		try {
			actionName = actionName.split("\\Q.\\E")[1];
System.out.println(actionName);
			if (actionName.equals("CaseHistory")) {
				list = new ArrayList<Map<String,Object>>();
				for (int i = 1; i <= 50; i ++) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", i);
					item.put("label", "第 " + i + " 项");
					list.add(item);
				}
			} else if (actionName.equals("Put")) {
				actionName = null;
				JSONObject ret = new JSONObject();
				ret.put("success", true);
				new Writer(getRequest(), getResponse()).output(ret);
			} else {
				System.out.println("====================");
				System.out.println(code);
				System.out.println(itemNum);
				System.out.println(itemLabel);
				System.out.println(data);
				actionName = "index";
			}
		}
		catch(Exception ex) {
			actionName = "index";
			Logger.error(getClass(), ex);
		}

		return actionName;
*/
	}

	@Action(value = "TakeEvidence.index", results = {
		@Result(name = "index", location = "/welcome.jsp")
	})
	public String index() throws Exception {
		super.execute();
		return "index";
	}

	@Action(value = "TakeEvidence.CaseHistory", results = {
		@Result(name = "Evidence", location = "/CaseHistoryEvidence.jsp", params = {"list", "%{list}"}, type = "dispatcher")
	})
	public String CaseHistory() throws Exception {
		super.execute();
//		list = new ArrayList<Map<String,Object>>();
		for (int i = 1; i <= 50; i ++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", i);
			item.put("label", "第 " + i + " 项");
			list.add(item);
		}
		return "Evidence";
	}

	@Action(value = "TakeEvidence.Put", results = {
			@Result(name = "Put")
		})
	public void Put() throws Exception {
		super.execute();
		System.out.println("=======put====");
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getWxName() {
		return wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}

}
