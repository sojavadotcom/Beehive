package com.sojava.beehive.framework.component.medicalimaging.action;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;
import com.sojava.beehive.framework.component.medicalimaging.service.MiPerformanceService;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Controller("MedicalImaging/Export")
@Scope("prototype")
@Namespace("/MedicalImaging")
public class Export extends ActionSupport {
	private static final long serialVersionUID = 7651222030838591999L;

	@Resource private MiExecutedService miExecutedService;
	@Resource private MiPerformanceService miPerformanceService;
	private List<File> files = new ArrayList<File>();
	private List<String> fileNames = new ArrayList<String>();
	private List<String> fileTypes = new ArrayList<String>();

	private Integer year;
	private Integer month;

	@Action(value = "Export.*",
			interceptorRefs={
			@InterceptorRef(
//					params={"allowedTypes","image/jpeg,application/zip", "maximumSize","1000000"},
					value="files"),
			@InterceptorRef("defaultStack"),
			@InterceptorRef("validation")
		}
	)
	@Override
	public String input() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		if (actionName.split("\\Q.\\E").length > 1) actionName = actionName.split("\\Q.\\E")[1];

		if (actionName.equalsIgnoreCase("StaffPerformance")) {
				year = year == null ? 0 : year;
				month = month == null ? 0 : month;
				WorkStatistic workStatistic = miExecutedService.findWorkStatistic(year, month);
				VMiExecutedStaffPerformance[] list = miExecutedService.findStaffPerformance(workStatistic, null, false);
				OutputStream out = getResponse().getOutputStream();
				try {
					String exportFileName = "Medical_Imaging_" + workStatistic.getYear() + "" + workStatistic.getMonth() + "_Staff_Performance.xls";
					getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + exportFileName + "\"");
					HSSFWorkbook book;
					if (files == null) {
						book = miPerformanceService.generateExcelOfStaffPerformance(workStatistic, list);
					} else {
						File overtimeFile = null, nurseFile = null;
//						for (int i = 0; i < files.size(); i ++) {
//							File file = files.get(i);
//							String fileName = fileNames.get(i);
//							if (fileName.indexOf("误餐费") != -1) overtimeFile = file;
//							else if (fileName.indexOf("护理组工作量") != -1) nurseFile = file;
//						}
						book = miPerformanceService.writeExcelOfStaffPerformance(overtimeFile, nurseFile, workStatistic, list);
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

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public List<String> getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(List<String> fileTypes) {
		this.fileTypes = fileTypes;
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
