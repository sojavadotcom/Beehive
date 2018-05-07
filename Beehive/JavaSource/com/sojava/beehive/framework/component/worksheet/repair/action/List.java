package com.sojava.beehive.framework.component.worksheet.repair.action;


import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.worksheet.repair.service.RepairService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.io.Writer;

@Controller("Worksheet/Repair/List")
@Scope("prototype")
@Namespace("/Worksheet/Repair")
public class List extends ActionSupport {
	private static final long serialVersionUID = 6478427303536827131L;

	@Resource private RepairService repairService;
	private String action;

	@Action(value = "List")
	public String index() throws Exception {
		super.execute();
		list();

		return null;
	}

	public void list() throws Exception {
		JSONObject result = null;
		try {
			if (getAction().equals("currentRepair")) {
				result = repairService.currentRepairs(getUserInfo().getUserName(), getUserInfo().getDeptName());
				result.put("id", "");
				result.put("label", "当前报修");
			} else if (getAction().equals("historyRepair")) {
				result = repairService.historyRepairs(getUserInfo().getUserName(), getUserInfo().getDeptName());
				result.put("id", "");
				result.put("label", "以往报修");
			} else {
				throw new ErrorException(this.getClass(), "未知操作");
			}
			result.put("success", true);

			new Writer(getRequest(), getResponse()).output(result.getJSONArray("items"));
		}
		catch(Exception ex) {
//			result = new JSONObject();
//			result.put("success", false);
//			result.put("message", ExceptionUtil.getMessage(ex));
			new ErrorException(this.getClass(), ex);
		}
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

}
