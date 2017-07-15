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
//@Table(name="v_mi_exectued_performance", schema="medicalimaging")
@Subselect(
		"select "
		+ "row_number() OVER (order by kind, type, work_statistics_id, rbrvs_id) AS id,"
		+ "a.*"
		+ " from ("
		+ "	select "
		+ "	b.work_statistics_id,"
		+ "	b.id as rbrvs_price_id,"
		+ "	c.id as rbrvs_id,"
		+ "	c.name as rbrvs_name,"
		+ "	b.price,"
		+ "	a.report_date,"
		+ "	a.report_time,"
		+ " a.technician_value as point,"
		+ "	a.execute_technician_staff_id as worker1_id,"
		+ "	a.execute_technician as worker1_name,"
		+ "	a.execute_technician_coef as worker1_coef,"
		+ " a.execute_diagnostician_is_student,"
		+ "	a.execute_technician_associate_staff_id as worker2_id,"
		+ "	a.execute_technician_associate as worker2_name,"
		+ "	a.execute_technician_associate_coef as worker2_coef,"
		+ "	b.type,"
		+ "	b.kind,"
		+ "	a.kind as dept"
		+ "	from "
		+ "	medicalimaging.v_mi_executed a,"
		+ "	medicalimaging.rbrvs_price b,"
		+ "	medicalimaging.dic_rbrvs c"
		+ "	where "
		+ "	(a.execute_technician_staff_id is not null or a.execute_technician_associate_staff_id is not null)"
		+ "	and a.rbrvs_id=b.rbrvs_id"
		+ "	and a.rbrvs_id=c.id"
		+ "	and b.type='投照'"
		+ "	and b.kind='工作量'"
		+ "	union all "
		+ "	select "
		+ "	b.work_statistics_id,"
		+ "	b.id as rbrvs_price_id,"
		+ "	c.id as rbrvs_id,"
		+ "	c.name as rbrvs_name,"
		+ "	b.price,"
		+ "	a.report_date,"
		+ "	a.report_time,"
		+ " a.diagnostician_value as point,"
		+ "	a.execute_diagnostician_staff_id as worker1_id,"
		+ "	a.execute_diagnostician as worker1_name,"
		+ "	a.execute_diagnostician_coef as worker1_coef,"
		+ " a.execute_diagnostician_is_student,"
		+ "	a.execute_verifier_staff_id as worker2_id,"
		+ "	a.execute_verifier as worker2_name,"
		+ "	a.execute_verifier_coef as worker2_coef,"
		+ "	b.type,"
		+ "	b.kind,"
		+ "	a.kind as dept"
		+ "	from "
		+ "	medicalimaging.v_mi_executed a,"
		+ "	medicalimaging.rbrvs_price b,"
		+ "	medicalimaging.dic_rbrvs c"
		+ "	where "
		+ "	a.rbrvs_id=b.rbrvs_id"
		+ "	and a.rbrvs_id=c.id"
		+ "	and a.status='已审核'"
		+ "	and b.type='诊断'"
		+ "	and b.kind='工作量'"
		+ ") a"
	)
@NamedQuery(name="VMiExectuedPerformance.findAll", query="SELECT v FROM VMiExectuedPerformance v")
public class VMiExectuedPerformance implements Serializable {
	private static final long serialVersionUID = -8691153908036595973L;

	@Id
	private Integer id;

	private String dept;

	private String kind;

	private Double price;

	@Column(name="rbrvs_id")
	private Integer rbrvsId;

	@Column(name="rbrvs_name")
	private String rbrvsName;

	@Column(name="rbrvs_price_id")
	private Integer rbrvsPriceId;

	@Temporal(TemporalType.DATE)
	@Column(name="report_date")
	private Date reportDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="report_time")
	private Date reportTime;

	private String type;

	@Column(name="work_statistics_id")
	private Integer workStatisticsId;

	@Column(name="worker1_coef")
	private Double worker1Coef;

	@Column(name="worker1_id")
	private Integer worker1Id;

	@Column(name="worker1_name")
	private String worker1Name;

	@Column(name="worker2_coef")
	private Double worker2Coef;

	@Column(name="worker2_id")
	private Integer worker2Id;

	@Column(name="worker2_name")
	private String worker2Name;

	private Double point;

	@Column(name="execute_diagnostician_is_student")
	private Integer executeDiagnosticianIsStudent;

	public VMiExectuedPerformance() {
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

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

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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

	public Double getWorker1Coef() {
		return this.worker1Coef;
	}

	public void setWorker1Coef(Double worker1Coef) {
		this.worker1Coef = worker1Coef;
	}

	public Integer getWorker1Id() {
		return this.worker1Id;
	}

	public void setWorker1Id(Integer worker1Id) {
		this.worker1Id = worker1Id;
	}

	public String getWorker1Name() {
		return this.worker1Name;
	}

	public void setWorker1Name(String worker1Name) {
		this.worker1Name = worker1Name;
	}

	public Double getWorker2Coef() {
		return this.worker2Coef;
	}

	public void setWorker2Coef(Double worker2Coef) {
		this.worker2Coef = worker2Coef;
	}

	public Integer getWorker2Id() {
		return this.worker2Id;
	}

	public void setWorker2Id(Integer worker2Id) {
		this.worker2Id = worker2Id;
	}

	public String getWorker2Name() {
		return this.worker2Name;
	}

	public void setWorker2Name(String worker2Name) {
		this.worker2Name = worker2Name;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public Integer getExecuteDiagnosticianIsStudent() {
		return executeDiagnosticianIsStudent;
	}

	public void setExecuteDiagnosticianIsStudent(Integer executeDiagnosticianIsStudent) {
		this.executeDiagnosticianIsStudent = executeDiagnosticianIsStudent;
	}

}