package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.tqm.service.CaseHistoryService;
import com.sojava.beehive.framework.component.wechat.tqm.service.EvidenceService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/Entry")
@Scope("prototype")
public class Entry extends ActionSupport {
	private static final long serialVersionUID = -8555460271356972319L;

	@Resource private CaseHistoryService caseHistoryService;
	@Resource private EvidenceService evidenceService;

	private Integer step;
	private List<Map<String, Object>> list;
	private String qrcode;

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

	@Action(value = "Entry.ViewEvidence", results = {
			@Result(name = "ViewEvidence", location = "ViewEvidence.jsp", params = {"list", "%{list}"})
		})
	public String viewEvidence() throws Exception {
		super.execute();
		int standardId = 1, paperNum = 0;
		if (qrcode != null && evidenceService.verifyQRCode(qrcode)) {
			String[] str = qrcode.replaceAll("^.*\\Qcn.org.jxszyyy.casehistory.evidence.\\E", "").split("\\Q-\\E");
			if (str.length == 2) {
				standardId = Integer.parseInt(str[0]);
				paperNum = Integer.parseInt(str[1]);
			} else {
				paperNum = Integer.parseInt(str[0]);
			}
		}
		list = evidenceService.getPhotos(standardId, paperNum);

		return "ViewEvidence";
	}

	public CaseHistoryService getCaseHistoryService() {
		return caseHistoryService;
	}

	public void setCaseHistoryService(CaseHistoryService caseHistoryService) {
		this.caseHistoryService = caseHistoryService;
	}

	public EvidenceService getEvidenceService() {
		return evidenceService;
	}

	public void setEvidenceService(EvidenceService evidenceService) {
		this.evidenceService = evidenceService;
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

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

}
