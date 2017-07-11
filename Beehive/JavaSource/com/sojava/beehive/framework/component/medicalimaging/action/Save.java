package com.sojava.beehive.framework.component.medicalimaging.action;

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

    private File uploadFile;
    private Double budget;
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
			merit(budget, medicalRate, manageRate, diagnoRate, techRate, year, month, beginDate, endDate, dept);
			return null;
		} else {
			return SUCCESS;
		}
	}

	public void import0() throws Exception {
		InputStream in = null;
		try {
			in = new FileInputStream(uploadFile);
			miExecutedService.importRecords(in, dept);
		}
		finally {
			in.close();
			new Writer(getRequest(), getResponse()).output("导入完成");
		}
	}

	public void merit(Double budget, Double medicalRate, Double manageRate, Double diagnoRate, Double techRate, int year, int month, Date begin, Date end, String dept) throws Exception {
		try {
			miPerformanceService.merit(budget, medicalRate, manageRate, diagnoRate, techRate, year, month, beginDate, endDate, dept);
		}
		finally {
			new Writer(getRequest(), getResponse()).output("核算完成");
		}
	}

	public MiExecutedService getMiExecutedService() {
		return miExecutedService;
	}

	public void setMiExecutedService(MiExecutedService miExecutedService) {
		this.miExecutedService = miExecutedService;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
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

}
