package com.sojava.beehive.framework.component.medicalimaging.action;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;
import com.sojava.beehive.framework.component.medicalimaging.service.MiPerformanceService;
import com.sojava.beehive.framework.io.Writer;

import java.io.File;
import java.io.OutputStream;

import javax.annotation.Resource;

@Controller("MedicalImaging/Export")
@Scope("prototype")
@Namespace("/MedicalImaging")
public class Export extends ActionSupport {
	private static final long serialVersionUID = 7651222030838591999L;

	@Resource private MiExecutedService miExecutedService;
	@Resource private MiPerformanceService miPerformanceService;
	private File[] files;
	private String[] fileNames;
	private String[] fileTypes;

	private Integer year;
	private Integer month;

	@Action(value = "Export.*")
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		if (actionName.split("\\Q.\\E").length > 1) actionName = actionName.split("\\Q.\\E")[1];

		if (actionName.equalsIgnoreCase("StaffPerformance")) {
			year = year == null ? 0 : year;
			month = month == null ? 0 : month;
			WorkStatistic workStatistic = miExecutedService.findWorkStatistic(year, month);
			OutputStream out = getResponse().getOutputStream();
			try {
				String exportFileName = "Medical_Imaging_" + workStatistic.getYear() + "" + workStatistic.getMonth() + "_Staff_Performance.xls";
				getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + exportFileName + "\"");
				HSSFWorkbook book = miPerformanceService.generateExcelOfStaffPerformanceReport(workStatistic);
				book.write(out);
				out.flush();
			}
			catch(Exception ex) {
				new Writer(getRequest(), getResponse()).output(ex.getMessage());
			}
			finally {
				out.close();
			}
		}

		return null;
	}

	public MiExecutedService getMiExecutedService() {
		return miExecutedService;
	}

	public void setMiExecutedService(MiExecutedService miExecutedService) {
		this.miExecutedService = miExecutedService;
	}

	public MiPerformanceService getMiPerformanceService() {
		return miPerformanceService;
	}

	public void setMiPerformanceService(MiPerformanceService miPerformanceService) {
		this.miPerformanceService = miPerformanceService;
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

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public String[] getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String[] fileTypes) {
		this.fileTypes = fileTypes;
	}

}
