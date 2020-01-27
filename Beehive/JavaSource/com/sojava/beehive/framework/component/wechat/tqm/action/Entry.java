package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.define.Platform;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.component.wechat.define.WxScope;
import com.sojava.beehive.framework.component.wechat.service.WxUserService;
import com.sojava.beehive.framework.component.wechat.tqm.service.CaseHistoryService;
import com.sojava.beehive.framework.component.wechat.tqm.service.EvidenceService;
import com.sojava.beehive.framework.exception.ErrorException;

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
	@Resource private WxUserService wxUserService;

	private Integer step;
	private List<Map<String, Object>> list;
	private User user;
	private String qrcode;
	private String code;
	private String state;
	private String errmsg;

	@Action(value = "Entry.UserInterface", results = {
			@Result(name = "UserInterface", location = "UserInterface.jsp")
		})
	public String userInterface() throws Exception {
		super.execute();

		return "UserInterface";
	}

	@Action(value = "Entry.Checkin", results = {
			@Result(name = "Checkin", location = "Checkin.jsp", params = {"step", "%{step}", "wxstate", "state"})
		})
	public String checkin() throws Exception {
		super.execute();

		return "Checkin";
	}

	@Action(value = "Entry.CaseHistory", results = {
			@Result(name = "CaseHistory", location = "CaseHistoryEvidence.jsp", params = {"list", "%{list}", "user", "%{user}"}),
			@Result(name = ERROR, location = "../Error.jsp", params = {"errmsg", "%{errmsg}"})
		})
	public String caseHistory() throws Exception {
		String rest = null;
		try {
			super.execute();
			user = wxUserService.checkWxUser(
					getResponse(),
					"https://wx.jxszyyy.org.cn/WeChat/TQM/Entry.CaseHistory.shtml",
					WeChatInfo.TQM_APPID,
					WeChatInfo.TQM_SECRET,
					WxScope.snsapi_base,
					code,
					state,
					Platform.TQM
				);
			if (user != null) {
				if (user.getStatus() != null && user.getStatus().equals("已激活")) {
					list = caseHistoryService.getPaper();
					rest = "CaseHistory";
				} else {
					throw new ErrorException("用户未激活，不能操作");
				}
			} else {
				throw new ErrorException("用户未登记，不能操作");
			}
		}
		catch(Exception ex) {
			errmsg = ex.getLocalizedMessage();
			rest = ERROR;
		}

		return rest;
	}

	@Action(value = "Entry.ViewEvidence", results = {
			@Result(name = "ViewEvidence", location = "ViewEvidence.jsp", params = {"list", "%{list}", "user", "%{user}"}),
			@Result(name = ERROR, location = "../Error.jsp", params = {"errmsg", "%{errmsg}"})
		})
	public String viewEvidence() throws Exception {
		String rest = null;
		try {
			super.execute();
			int standardId = 1, paperNum = 0;
			user = wxUserService.checkWxUser(
					getResponse(),
					"https://wx.jxszyyy.org.cn/WeChat/TQM/Entry.ViewEvidence.shtml",
					WeChatInfo.TQM_APPID,
					WeChatInfo.TQM_SECRET,
					WxScope.snsapi_base,
					code,
					state,
					Platform.TQM
				);
			if (user != null) {
				if (user.getStatus() != null && user.getStatus().equals("已激活")) {
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
					rest = "ViewEvidence";
				} else {
					throw new ErrorException("用户未激活，不能操作");
				}
			} else {
				throw new ErrorException("用户未登记，不能操作");
			}
		}
		catch(Exception ex) {
			errmsg = ex.getLocalizedMessage();
			rest = ERROR;
		}

		return rest;
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

	public WxUserService getWxUserService() {
		return wxUserService;
	}

	public void setWxUserService(WxUserService wxUserService) {
		this.wxUserService = wxUserService;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
