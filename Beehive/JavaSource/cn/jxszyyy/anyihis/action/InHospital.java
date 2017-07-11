package cn.jxszyyy.anyihis.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jxszyyy.anyihis.service.InHospitalService;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Controller("Anyihis/InHospital")
@Scope("prototype")
@Namespace("/Anyihis/InHospital")
@ParentPackage("json-default")
public class InHospital extends ActionSupport {
	private static final long serialVersionUID = 6050285617826530606L;

	@Resource private InHospitalService inHospitalService;
	private List<Map<String, Object>> list;
	private Map<String, Object> rest;
	private Integer bhid;

	public InHospital() {
		rest = new HashMap<String, Object>();
	}

	@Override
	@Actions(value = {
		@Action(value = "indexCriticalPatient", results = {@Result(name = SUCCESS, location = "indexCriticalPatient.jsp")}),
		@Action(value = "indexRefund", results = {@Result(name = SUCCESS, location = "indexRefund.jsp")}),
		@Action(value = "ListCriticalPatient", results = {@Result(name = SUCCESS, type = "json", params = {"root", "list"})}),
		@Action(value = "ListRefund", results = {@Result(name = SUCCESS, type = "json", params = {"root", "list"})})
	})
	public String input() throws Exception {
		super.execute();
		String name = getActionContext().getName();
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		return (String) getClass().getMethod(name, new Class[0]).invoke(this, new Object[0]);
	}

	public String indexCriticalPatient() {
		return SUCCESS;
	}

	public String indexRefund() {
		return SUCCESS;
	}

	public String listCriticalPatient() {
		try {
			list = inHospitalService.findCriticalPatient(new Page(getStart(), getEnd()));
			return SUCCESS;
		}
		catch(Exception ex) {
			new ErrorException(getClass(), ex);
			rest.put("SUCCESS", false);
			rest.put("message", ex.getMessage());
			return ERROR;
		}
	}

	public String listRefund() {
		try {
			List<Criterion> filters = new ArrayList<Criterion>();
			if (getCondition() != null) filters.add(getCondition());
			if (this.bhid != null) filters.add(Restrictions.eq("bhid", bhid));
			list = inHospitalService.findRefund(filters.toArray(new Criterion[0]), null, new Page(getStart(), getEnd()));
			return SUCCESS;
		}
		catch(Exception ex) {
			new ErrorException(getClass(), ex);
			rest.put("SUCCESS", false);
			rest.put("message", ex.getMessage());
			return ERROR;
		}
	}

	public InHospitalService getInHospitalService() {
		return inHospitalService;
	}

	public void setInHospitalService(InHospitalService inHospitalService) {
		this.inHospitalService = inHospitalService;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public Map<String, Object> getRest() {
		return rest;
	}

	public void setRest(Map<String, Object> rest) {
		this.rest = rest;
	}

	public Integer getBhid() {
		return bhid;
	}

	public void setBhid(Integer bhid) {
		this.bhid = bhid;
	}

}
