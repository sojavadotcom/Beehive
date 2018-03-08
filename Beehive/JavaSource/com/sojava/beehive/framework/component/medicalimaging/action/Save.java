package com.sojava.beehive.framework.component.medicalimaging.action;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiWorkload;
import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;
import com.sojava.beehive.framework.component.medicalimaging.service.MiPerformanceService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.io.Writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

@Controller("MedicalImaging/Save")
@Scope("prototype")
@Namespace("/MedicalImaging")
public class Save extends ActionSupport {
	private static final long serialVersionUID = 4567102317046354934L;

	@Resource private MiExecutedService miExecutedService;
	@Resource private MiPerformanceService miPerformanceService;

    private File workloadFile;
    private File overtimeFile;
    private File nurseFile;
    private Double budget;
    private Double nurseRate;
    private Double nurseCost;
    private Double performanceCost;
    private Double medicalRate;
    private Double medicalTotal;
    private Double manageRate;
    private Double manageTotal;
    private Double diagnoRate;
    private Double diagnoTotal;
    private Double techRate;
    private Double techTotal;
    private Integer year;
    private Integer month;
    private Date beginDate;
    private Date endDate;
    private String dept;
    private WorkStatistic workStatistic;

    @Action(value = "Save.*")
	@Override
	public String input() throws Exception {
		super.execute();
		String contextName = this.getActionContext().getName();
		if (contextName.equalsIgnoreCase("save.import")) {
			import0();
			return null;
		} else if (contextName.equalsIgnoreCase("save.merit")) {
			merit();
			return null;
		} else {
			return SUCCESS;
		}
	}

	public void import0() throws Exception {
		InputStream in = null;
		Writer out = new Writer(getRequest(), getResponse());
		DocumentFactory df = DocumentFactory.getInstance();
		Document doc = df.createDocument(df.createElement("msg"));
		Element msg = doc.getRootElement();
		try {
			if (workloadFile == null) throw new ErrorException(getClass(), "未上传数据文件");
			in = new FileInputStream(workloadFile);
			miExecutedService.importRecords(in, dept);
			msg.addAttribute("success", "true");
			msg.setText("导入完成");
		}
		catch (Exception ex) {
			msg.addAttribute("success", "false");
			msg.setText(ex.getMessage());
		}
		finally {
			if (in != null) in.close();
			out.output(doc);
		}
	}

	public void merit() throws Exception {
		DocumentFactory df = DocumentFactory.getInstance();
		Document doc = df.createDocument(df.createElement("msg"));
		Element msg = doc.getRootElement();

		HSSFWorkbook overtimeBook = null;
		HSSFWorkbook nurseBook = null;
		FileInputStream in = null;
		try {
			if (overtimeFile != null) {
				try {
					in = new FileInputStream(overtimeFile);
					overtimeBook = new HSSFWorkbook(in);
				}
				catch(Exception ex) {
					throw new Exception("核算数据准备中，读取误餐费Excel数据文件时发生：" + ex.getMessage());
				}
				finally {
					in.close();
				}
			}
			if (nurseFile != null) {
				try {
					in = new FileInputStream(nurseFile);
					nurseBook = new HSSFWorkbook(in);
				}
				catch(Exception ex) {
					throw new Exception("核算数据准备中，读取护理组工作量Excel数据文件时发生：" + ex.getMessage());
				}
				finally {
					in.close();
				}
			}
			msg.setText("正在核算");
			miPerformanceService.merit(budget, nurseRate, medicalRate, manageRate, year, month, beginDate, endDate, dept, overtimeBook, nurseBook);
			msg.setText("获取统计主信息");
			WorkStatistic workStatistic = miExecutedService.findWorkStatistic(year, month);
			msg.setText("获取核算数据");
			VMiExecutedStaffPerformance[] staffPerformances = miExecutedService.findStaffPerformance(workStatistic, null, false);
			msg.setText("获取误餐费");
			MiWorkload[] overtimes = miExecutedService.findWorkload(workStatistic, "误餐费", "补助", null);
			msg.setText("获取护理组工作量");
			MiWorkload[] nurseWorkloads = miExecutedService.findWorkload(workStatistic, "时数", "护理组", null);
			msg.setText("生成绩效报表");
			miPerformanceService.saveStaffPerformanceReport(workStatistic, staffPerformances, overtimes, nurseWorkloads, false);
			msg.setText("核算完成");

			msg.addAttribute("success", "true");
		}
		catch(Exception ex) {
			msg.addAttribute("success", "false");
			msg.setText(msg.getText() + "时发生：" + ex.getMessage());
		}
		finally {
			new Writer(getRequest(), getResponse()).output(doc);
		}
	}

	public MiExecutedService getMiExecutedService() {
		return miExecutedService;
	}

	public void setMiExecutedService(MiExecutedService miExecutedService) {
		this.miExecutedService = miExecutedService;
	}

	public File getWorkloadFile() {
		return workloadFile;
	}

	public void setWorkloadFile(File workloadFile) {
		this.workloadFile = workloadFile;
	}

	public File getOvertimeFile() {
		return overtimeFile;
	}

	public void setOvertimeFile(File overtimeFile) {
		this.overtimeFile = overtimeFile;
	}

	public File getNurseFile() {
		return nurseFile;
	}

	public void setNurseFile(File nurseFile) {
		this.nurseFile = nurseFile;
	}

	public MiPerformanceService getMiPerformanceService() {
		return miPerformanceService;
	}

	public void setMiPerformanceService(MiPerformanceService miPerformanceService) {
		this.miPerformanceService = miPerformanceService;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Double getMedicalRate() {
		return medicalRate;
	}

	public void setMedicalRate(Double medicalRate) {
		this.medicalRate = medicalRate;
	}

	public Double getManageRate() {
		return manageRate;
	}

	public void setManageRate(Double manageRate) {
		this.manageRate = manageRate;
	}

	public Double getMedicalTotal() {
		return medicalTotal;
	}

	public void setMedicalTotal(Double medicalTotal) {
		this.medicalTotal = medicalTotal;
	}

	public Double getManageTotal() {
		return manageTotal;
	}

	public void setManageTotal(Double manageTotal) {
		this.manageTotal = manageTotal;
	}

	public WorkStatistic getWorkStatistic() {
		return workStatistic;
	}

	public void setWorkStatistic(WorkStatistic workStatistic) {
		this.workStatistic = workStatistic;
	}

	public Double getDiagnoRate() {
		return diagnoRate;
	}

	public void setDiagnoRate(Double diagnoRate) {
		this.diagnoRate = diagnoRate;
	}

	public Double getDiagnoTotal() {
		return diagnoTotal;
	}

	public void setDiagnoTotal(Double diagnoTotal) {
		this.diagnoTotal = diagnoTotal;
	}

	public Double getTechRate() {
		return techRate;
	}

	public void setTechRate(Double techRate) {
		this.techRate = techRate;
	}

	public Double getTechTotal() {
		return techTotal;
	}

	public void setTechTotal(Double techTotal) {
		this.techTotal = techTotal;
	}

	public Double getNurseRate() {
		return nurseRate;
	}

	public void setNurseRate(Double nurseRate) {
		this.nurseRate = nurseRate;
	}

	public Double getNurseCost() {
		return nurseCost;
	}

	public void setNurseCost(Double nurseCost) {
		this.nurseCost = nurseCost;
	}

	public Double getPerformanceCost() {
		return performanceCost;
	}

	public void setPerformanceCost(Double performanceCost) {
		this.performanceCost = performanceCost;
	}

}
