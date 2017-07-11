package com.sojava.beehive.framework.component.worksheet.action;


import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.Criterion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.worksheet.service.RefundService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

@Controller("Worksheet/Refund")
@Scope("prototype")
@Namespace("/Worksheet/Refund")
@ParentPackage("json-default")
public class Refund extends ActionSupport {
	private static final long serialVersionUID = 8016804615919566682L;

	@Resource private RefundService refundService;
	private Map<String, Object> rest;
	private Object[] list;

	public Refund() {
		rest = new HashMap<String, Object>();
	}

	@Override
	@Actions(value = {
		@Action(value = "Index", results = {
			@Result(name = SUCCESS, location = "index.jsp")
		}),
		@Action(value = "Edit", results = {
			@Result(name = SUCCESS, location = "editor.jsp")
		}),
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
			JSONObject data = refundService.list(new Criterion[] {}, this.getOrders(), page);
			this.setRange(page.getTotal());
			list = data.getJSONArray("items").toArray();

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

	public RefundService getRefundService() {
		return refundService;
	}

	public void setRefundService(RefundService refundService) {
		this.refundService = refundService;
	}

}
