package com.sojava.beehive.framework.component.worksheet.repair.action;


import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.worksheet.repair.bean.Repair;
import com.sojava.beehive.framework.component.worksheet.repair.service.RepairService;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.ExceptionUtil;

@Controller("Worksheet/Repair/Operator")
@Scope("prototype")
@Namespace("/Worksheet/Repair")
public class Operator extends ActionSupport {

	private static final long serialVersionUID = 6539130692318441614L;
	@Resource RepairService repairService;
	private String action;
	private Repair repair;

	@Action(value = "Operate")
	@Override
	public String input() throws Exception {
		super.execute();
		operator();
		return null;
	}

	public void operator() throws Exception {
		JSONObject result = new JSONObject();
		try {
			repair.setRepairUserName(getUserInfo().getUserName());
			repair.setRepairDeptName(getUserInfo().getDeptName());
			repairService.switchStatus(getAction(), repair);
			result.put("success", true);
		}
		catch(Exception ex) {
			result.put("success", false);
			result.put("message", ExceptionUtil.getMessage(ex));
		}
		new Writer(getRequest(), getResponse()).output(result);
	}

	public RepairService getRepairService() {
		return repairService;
	}

	public void setRepairService(RepairService repairService) {
		this.repairService = repairService;
	}

	public String getAction() {
		return action == null ? "" : action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

}
