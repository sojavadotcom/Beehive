package com.sojava.beehive.framework.component.medicalimaging.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiWorkload;
import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.FormatUtil;
import com.sojava.beehive.framework.util.RecordUtil;

import javax.annotation.Resource;

@Controller("MedicalImaging/Entry")
@Scope("prototype")
@Namespace("/MedicalImaging")
public class Entry extends ActionSupport {
	private static final long serialVersionUID = 8469604168969899250L;

	@Resource private MiExecutedService miExecutedService;
	private String action;

	private Integer year;
	private Integer month;

	@Action(value = "Entry.*", results = {
			@Result(name = "index", location = "index.jsp"),
			@Result(name = "import", location = "import.jsp"),
			@Result(name = "merit", location = "merit.jsp"),
			@Result(name = "export", location = "export.jsp")
		})
	@Override
	public String input() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		if (actionName.split("\\Q.\\E").length > 1) actionName = actionName.split("\\Q.\\E")[1];
		else actionName = "index";

		if (actionName.equalsIgnoreCase("query")) {
			String action = this.getActionContext().getName();
			if (action.split("\\Q.\\E").length > 2) action = action.split("\\Q.\\E")[2];
			if (action.equalsIgnoreCase("workStatistics")) {
				year = year == null || year == 0 ? FormatUtil.currentYear() : year;
				month = month == null || month == 0 ? FormatUtil.currentMonth() : month;
				JSONObject json = new RecordUtil().generateJsonByMapping(miExecutedService.findWorkStatistic(year, month));
				new Writer(getRequest(), getResponse()).output(json);
			} else if (action.equalsIgnoreCase("staffPerformance")) {
				year = year == null ? 0 : year;
				month = month == null ? 0 : month;
				WorkStatistic workStatistic = miExecutedService.findWorkStatistic(year, month);
				VMiExecutedStaffPerformance[] staffPerformances = miExecutedService.findStaffPerformance(workStatistic, null, true);
				MiWorkload[] overtimes = miExecutedService.findWorkload(workStatistic, "误餐费", "补助", null);
				MiWorkload[] nurseWorkloads = miExecutedService.findWorkload(workStatistic, "时数", "护理组", null);
				RecordUtil recordUtil = new RecordUtil();
				JSONObject result = recordUtil.generateJsonByMapping(staffPerformances);
				JSONArray items = result.getJSONArray("items");
				String groupName = "";
				for (int i = 0; i < items.size(); i ++) {
					JSONObject item = items.getJSONObject(i);
					String _groupName = item.getString("groupName");
					if (!groupName.equalsIgnoreCase(_groupName)) {
						groupName = _groupName;
						JSONObject groupItem = new JSONObject();
						groupItem.put("groupName", groupName);
						groupItem.put("staffName", groupName);
						items.add(i == 0 ? i : i+1, groupItem);
					}
				}
				new Writer(getRequest(), getResponse()).output(items);
			}

			return null;
		} else {
			return actionName;
		}
	}

	public MiExecutedService getMiExecutedService() {
		return miExecutedService;
	}

	public void setMiExecutedService(MiExecutedService miExecutedService) {
		this.miExecutedService = miExecutedService;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
