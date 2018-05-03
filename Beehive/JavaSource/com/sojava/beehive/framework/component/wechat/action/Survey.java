package com.sojava.beehive.framework.component.wechat.action;


import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.service.SurveyService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

@Controller("WeChat/Survey")
@Scope("prototype")
@Namespace("/WeChat/Survey")
@ParentPackage("json-default")
public class Survey extends ActionSupport {
	private static final long serialVersionUID = -8599976251807712835L;

	@Resource private SurveyService surveyService;
	private Map<String, Object> rest;
	private Object[] list;

	public Survey() {
		rest = new HashMap<String, Object>();
	}

	@Override
	@Actions(value = {
		@Action(value = "List", results = {
			@Result(name = SUCCESS, type = "json", params = {"root", "list"}),
			@Result(name = ERROR, type = "json", params = {"root", "rest"})
		})
	})
	public String input() throws Exception {
		super.execute();
		return (String) this.getClass().getMethod(this.getActionContext().getName().toLowerCase(), new Class[0]).invoke(this, new Object[0]);
	}

	public String index() {
		return SUCCESS;
	}

	public String edit() {
		return SUCCESS;
	}

	public String list() {
		try {
			Page page = new Page(this.getStart(), this.getEnd());
			JSONArray data = surveyService.listSurvey();
			this.setRange(page.getTotal());
			list = data.toArray();

			return SUCCESS;
		}
		catch(Exception ex) {
			new ErrorException(getClass(), ex);
			rest.put("success", false);
			rest.put("message", ex.getMessage());
		}
		return ERROR;
	}

	public Map<String, Object> getRest() {
		return rest;
	}

	public void setRest(Map<String, Object> rest) {
		this.rest = rest;
	}

	public Object[] getList() {
		return list;
	}

	public void setList(Object[] list) {
		this.list = list;
	}

	public SurveyService getSurveyService() {
		return surveyService;
	}

	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

}
