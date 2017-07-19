package com.sojava.beehive.framework.component.medicalimaging.action;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
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

import javax.annotation.Resource;

@Controller("MedicalImaging/Export")
@Scope("prototype")
@Namespace("/MedicalImaging")
public class Export extends ActionSupport {
	private static final long serialVersionUID = 7651222030838591999L;

	@Resource private MiExecutedService miExecutedService;
	@Resource private MiPerformanceService miPerformanceService;
	private File file;
	private String fileName;
	private String fileType;

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
				VMiExecutedStaffPerformance[] list = miExecutedService.findStaffPerformance(workStatistic, null, false);

				OutputStream out = getResponse().getOutputStream();
				try {
					String fileName = "Medical_Imaging_" + workStatistic.getYear() + "" + workStatistic.getMonth() + "_Staff_Performance.xls";
					getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					HSSFWorkbook book;
					if (file == null) {
						book = miPerformanceService.generateExcelOfStaffPerformance(workStatistic, list);
					} else {
						book = miPerformanceService.writeExcelOfStaffPerformance(file, workStatistic, list);
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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
