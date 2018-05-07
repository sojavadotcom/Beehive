package com.sojava.beehive.framework.component.medicalimaging.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.medicalimaging.StaffPerformanceReportType;
import com.sojava.beehive.framework.component.medicalimaging.bean.StaffPerformanceReport;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;
import com.sojava.beehive.framework.component.medicalimaging.service.MiPerformanceService;
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
	@Resource private MiPerformanceService miPerformanceService;
	private String action;

	private Integer year;
	private Integer month;

	@Action(value = "Entry.*", results = {
			@Result(name = "index", location = "index.jsp"),
			@Result(name = "import", location = "import.jsp"),
			@Result(name = "merit", location = "merit.jsp"),
			@Result(name = "export", location = "export.jsp")
		})
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		if (actionName.split("\\Q.\\E").length > 1) actionName = actionName.split("\\Q.\\E")[1];
		else actionName = "index";

		if (actionName.equalsIgnoreCase("query")) {
			String action = this.getActionContext().getName();
			if (action.split("\\Q.\\E").length > 2) action = action.split("\\Q.\\E")[2];
			if (action.equalsIgnoreCase("workStatistics")) {
				year = year == null ? FormatUtil.currentYear() : year;
				month = month == null ? FormatUtil.currentMonth() : month;
				JSONObject json = new RecordUtil().generateJsonByMapping(miExecutedService.findWorkStatistic(year, month));
				new Writer(getRequest(), getResponse()).output(json);
			} else if (action.equalsIgnoreCase("staffPerformance")) {
				year = year == null ? 2017 : year;
				month = month == null ? 1 : month;
				WorkStatistic workStatistic = miExecutedService.findWorkStatistic(year, month);
				StaffPerformanceReport[] reports = miPerformanceService.findStaffPerformanceReport(workStatistic);
				RecordUtil recordUtil = new RecordUtil();
				JSONObject result = recordUtil.generateJsonByMapping(reports);
				JSONArray items = result.getJSONArray("items");
				for (int i = 0; i < items.size(); i ++) {
					JSONObject item = items.getJSONObject(i);
					String _groupName = item.getString("groupName");
					int _type = item.getInt("type");
					if (_type == StaffPerformanceReportType.TITLE.ordinal()) {
						item.put("staffName", _groupName);
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

	public MiPerformanceService getMiPerformanceService() {
		return miPerformanceService;
	}

	public void setMiPerformanceService(MiPerformanceService miPerformanceService) {
		this.miPerformanceService = miPerformanceService;
	}

}
