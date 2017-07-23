package com.sojava.beehive.framework.component.medicalimaging.action;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiWorkload;
import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;
import com.sojava.beehive.framework.component.medicalimaging.service.MiPerformanceService;

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
	@Override
	public String input() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		if (actionName.split("\\Q.\\E").length > 1) actionName = actionName.split("\\Q.\\E")[1];

		if (actionName.equalsIgnoreCase("StaffPerformance")) {
				year = year == null ? 0 : year;
				month = month == null ? 0 : month;
				WorkStatistic workStatistic = miExecutedService.findWorkStatistic(year, month);
				VMiExecutedStaffPerformance[] staffPerformances = miExecutedService.findStaffPerformance(workStatistic, null, false);
				MiWorkload[] overtimes = miExecutedService.findWorkload(workStatistic, "误餐费", "补助", null);
				MiWorkload[] nurseWorkloads = miExecutedService.findWorkload(workStatistic, "时数", "护理组", null);
				OutputStream out = getResponse().getOutputStream();
				try {
					String exportFileName = "Medical_Imaging_" + workStatistic.getYear() + "" + workStatistic.getMonth() + "_Staff_Performance.xls";
					getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + exportFileName + "\"");
					HSSFWorkbook book;
					if (files == null) {
						book = miPerformanceService.generateExcelOfStaffPerformance(workStatistic, staffPerformances, overtimes, nurseWorkloads);
					} else {
						File overtimeFile = null, nurseFile = null;
//						for (int i = 0; i < files.size(); i ++) {
//							File file = files.get(i);
//							String fileName = fileNames.get(i);
//							if (fileName.indexOf("误餐费") != -1) overtimeFile = file;
//							else if (fileName.indexOf("护理组工作量") != -1) nurseFile = file;
//						}
						book = miPerformanceService.writeExcelOfStaffPerformance(overtimeFile, nurseFile, workStatistic, staffPerformances);
					}
					book.write(out);
					out.flush();
				}
				catch(Exception ex) {
					System.out.println(ex.getLocalizedMessage());
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
