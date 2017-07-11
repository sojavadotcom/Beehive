package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Subselect;

import java.util.Date;


/**
 * The persistent class for the v_mi_exectued_performance database table.
 * 
 */
@Entity
@NamedQuery(name="VMiExectuedPerformanceSingleMode.findAll", query="SELECT v FROM VMiExectuedPerformanceSingleMode v")
@Subselect(
		"select "
		+ "row_number() OVER (order by kind, type, work_statistics_id, rbrvs_id) AS id,"
		+ "a.*"
		+ " from ("
		+ "	select "
		+ "	b.work_statistics_id,"
		+ "	b.id as rbrvs_price_id,"
		+ "	a.rbrvs_id,"
		+ "	a.rbrvs_name,"
		+ "	b.price,"
		+ "	a.report_date,"
		+ "	a.report_time,"
		+ "	a.execute_technician_staff_id as staff_id,"
		+ "	a.execute_technician as staff_name,"
		+ "	a.execute_technician_coef as staff_coef,"
		+ "	b.type,"
		+ "	b.kind,"
		+ "	a.kind as dept"
		+ "	from medicalimaging.v_mi_executed a,"
		+ "	medicalimaging.rbrvs_price b"
		+ "	where "
		+ "	a.execute_technician_staff_id is not null"
		+ "	and coalesce(a.execute_technician_coef, 0) > 0"
		+ "	and a.rbrvs_id=b.rbrvs_id"
		+ "	and b.type='操作'"
		+ "	and b.kind='工作量'"
		+ "	union all "
		+ "	select "
		+ "	b.work_statistics_id,"
		+ "	b.id as rbrvs_price_id,"
		+ "	a.rbrvs_id,"
		+ "	a.rbrvs_name,"
		+ "	b.price,"
		+ "	a.report_date,"
		+ "	a.report_time,"
		+ "	a.execute_technician_associate_staff_id as staff_id,"
		+ "	a.execute_technician_associate as staff_name,"
		+ "	a.execute_technician_associate_coef as staff_coef,"
		+ "	b.type,"
		+ "	b.kind,"
		+ "	a.kind as dept"
		+ "	from medicalimaging.v_mi_executed a,"
		+ "	medicalimaging.rbrvs_price b"
		+ "	where "
		+ " a.execute_technician_associate_staff_id is not null"
		+ "	and coalesce(a.execute_technician_associate_coef, 0) > 0"
		+ "	and a.rbrvs_id=b.rbrvs_id"
		+ "	and b.type='辅助'"
		+ "	and b.kind='工作量'"
		+ "	union all "
		+ "	select "
		+ "	b.work_statistics_id,"
		+ "	b.id as rbrvs_price_id,"
		+ "	a.rbrvs_id,"
		+ "	a.rbrvs_name,"
		+ "	b.price,"
		+ "	a.report_date,"
		+ "	a.report_time,"
		+ "	a.execute_diagnostician_staff_id as staff_id,"
		+ "	a.execute_diagnostician as staff_name,"
		+ "	a.execute_diagnostician_coef as staff_coef,"
		+ "	b.type,"
		+ "	b.kind,"
		+ "	a.kind as dept"
		+ "	from medicalimaging.v_mi_executed a,"
		+ "	medicalimaging.rbrvs_price b"
		+ "	where "
		+ "	a.execute_diagnostician_staff_id is not null"
		+ "	and coalesce(a.execute_diagnostician_coef, 0) > 0"
		+ "	and a.rbrvs_id=b.rbrvs_id"
		+ "	and b.type='阅片'"
		+ "	and b.kind='工作量'"
		+ "	and a.status='已审核'"
		+ "	union all "
		+ "	select "
		+ "	b.work_statistics_id,"
		+ "	b.id as rbrvs_price_id,"
		+ "	a.rbrvs_id,"
		+ "	a.rbrvs_name,"
		+ "	b.price,"
		+ "	a.report_date,"
		+ "	a.report_time,"
		+ "	a.execute_verifier_staff_id as staff_id,"
		+ "	a.execute_verifier as staff_name,"
		+ "	a.execute_verifier_coef as staff_coef,"
		+ "	b.type,"
		+ "	b.kind,"
		+ "	a.kind as dept"
		+ "	from medicalimaging.v_mi_executed a,"
		+ "	medicalimaging.rbrvs_price b"
		+ "	where "
		+ "	a.execute_verifier_staff_id is not null"
		+ "	and coalesce(a.execute_verifier_coef, 0) > 0"
		+ " and execute_diagnostician_is_student=1"
		+ "	and a.rbrvs_id=b.rbrvs_id"
		+ "	and b.type='审片'"
		+ "	and b.kind='工作量'"
		+ "	and a.status='已审核'"
		+ ") a"
	)
public class VMiExectuedPerformanceSingleMode implements Serializable {
	private static final long serialVersionUID = 4428779434428300573L;

	@Id
	private Integer id;

	private Double price;

	@Column(name="rbrvs_id")
	private Integer rbrvsId;

	@Column(name="rbrvs_name")
	private String rbrvsName;

	@Column(name="rbrvs_price_id")
	private Integer rbrvsPriceId;

	@Column(name="report_date")
	private Date reportDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="report_time")
	private Date reportTime;

	private String type;

	private String kind;

	private String dept;

	@Column(name="work_statistics_id")
	private Integer workStatisticsId;

	@Column(name="staff_id")
	private Integer staffId;

	@Column(name="staff_name")
	private String staffName;

	@Column(name="staff_coef")
	private Double staffCoef;

	public VMiExectuedPerformanceSingleMode() {}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getRbrvsId() {
		return this.rbrvsId;
	}

	public void setRbrvsId(Integer rbrvsId) {
		this.rbrvsId = rbrvsId;
	}

	public String getRbrvsName() {
		return this.rbrvsName;
	}

	public void setRbrvsName(String rbrvsName) {
		this.rbrvsName = rbrvsName;
	}

	public Integer getRbrvsPriceId() {
		return this.rbrvsPriceId;
	}

	public void setRbrvsPriceId(Integer rbrvsPriceId) {
		this.rbrvsPriceId = rbrvsPriceId;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getWorkStatisticsId() {
		return this.workStatisticsId;
	}

	public void setWorkStatisticsId(Integer workStatisticsId) {
		this.workStatisticsId = workStatisticsId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Double getStaffCoef() {
		return staffCoef;
	}

	public void setStaffCoef(Double staffCoef) {
		this.staffCoef = staffCoef;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}