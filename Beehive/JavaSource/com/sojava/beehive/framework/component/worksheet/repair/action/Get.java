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

@Controller("Worksheet/Repair/Get")
@Scope("prototype")
@Namespace("/Worksheet/Repair")
public class Get extends ActionSupport {

	private static final long serialVersionUID = 7785453546901698356L;
	@Resource RepairService repairService;
	private Repair repair;

	@Action(value = "Get")
	public String index() throws Exception {
		super.execute();
		get();
		return null;
	}

	public void get() throws Exception {
		JSONObject result = null;
		try {
			result = repairService.get(repair);
		}
		catch(Exception ex) {
			result = new JSONObject();
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

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

}
