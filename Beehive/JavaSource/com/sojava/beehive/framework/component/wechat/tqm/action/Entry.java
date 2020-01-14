package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.tqm.service.CaseHistoryService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/Entry")
@Scope("prototype")
public class Entry extends ActionSupport {
	private static final long serialVersionUID = -8555460271356972319L;

	@Resource private CaseHistoryService caseHistoryService;

	private Integer step;
	private List<Map<String, Object>> list;

	@Action(value = "Entry.Checkin", results = {
			@Result(name = "Checkin", location = "Checkin.jsp", params = {"step", "%{step}"})
		})
	public String checkin() throws Exception {
		super.execute();

		return "Checkin";
	}

	@Action(value = "Entry.CaseHistory", results = {
			@Result(name = "CaseHistory", location = "CaseHistoryEvidence.jsp", params = {"list", "%{list}"})
		})
	public String caseHistory() throws Exception {
		super.execute();
		list = caseHistoryService.getPaper();

		return "CaseHistory";
	}

	public CaseHistoryService getCaseHistoryService() {
		return caseHistoryService;
	}

	public void setCaseHistoryService(CaseHistoryService caseHistoryService) {
		this.caseHistoryService = caseHistoryService;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

}
