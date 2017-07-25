package com.sojava.beehive.framework.component.medicalimaging.bean;

import com.sojava.beehive.framework.component.medicalimaging.StaffPerformanceReportType;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="staff_performance_report", schema="medicalimaging")
@NamedQuery(name="StaffPerformanceReport.findAll", query="SELECT s FROM StaffPerformanceReport s")
public class StaffPerformanceReport implements Serializable {
	private static final long serialVersionUID = 7168655447527551886L;

	public transient final static short TYPE_RECORD = 0;
	public transient final static short TYPE_TITLE = 1;
	public transient final static short TYPE_AMOUNT = 2;
	public transient final static short TYPE_AMOUNT_TOTAL = 3;

	@Id
	@SequenceGenerator(name="STAFF_PERFORMANCE_REPORT_ID_GENERATOR", sequenceName="medicalimaging.STAFF_PERFORMANCE_REPORT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STAFF_PERFORMANCE_REPORT_ID_GENERATOR")
	private Integer id;

	@Column(name="work_statistics_id")
	private Integer workStatisticId;

	@Column(name="group_id")
	private Integer groupId;

	@Column(name="group_name", length=32)
	private String groupName;

	@Column(name="staff_id")
	private Integer staffId;

	@Column(name="staff_name", length=32)
	private String staffName;

	@Column(name="dianose_coef", length=20, scale=6)
	private Double dianoseCoef;

	@Column(name="medical_amount", length=20, scale=6)
	private Double medicalAmount;

	@Column(name="manage_amount", length=20, scale=6)
	private Double manageAmount;

	@Column(name="nurse_amount", length=20, scale=6)
	private Double nurseAmount;

	@Column(name="overtime_amount", length=20, scale=6)
	private Double overtimeAmount;

	@Column(name="amount_total", length=20, scale=6)
	private Double amountTotal;

	@Column(name="tech_points", length=50, scale=20)
	private Double techPoints;

	@Column(name="diagnose_points", length=50, scale=20)
	private Double diagnosePoints;

	@Column(name="student_points", length=50, scale=20)
	private Double studentPoints;

	@Column(name="point_amount", length=50, scale=20)
	private Double pointAmount;

	@Enumerated(EnumType.ORDINAL)
	private StaffPerformanceReportType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkStatisticId() {
		return workStatisticId;
	}

	public void setWorkStatisticId(Integer workStatisticId) {
		this.workStatisticId = workStatisticId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public Double getDianoseCoef() {
		return dianoseCoef;
	}

	public void setDianoseCoef(Double dianoseCoef) {
		this.dianoseCoef = dianoseCoef;
	}

	public Double getMedicalAmount() {
		return medicalAmount;
	}

	public void setMedicalAmount(Double medicalAmount) {
		this.medicalAmount = medicalAmount;
	}

	public Double getManageAmount() {
		return manageAmount;
	}

	public void setManageAmount(Double manageAmount) {
		this.manageAmount = manageAmount;
	}

	public Double getNurseAmount() {
		return nurseAmount;
	}

	public void setNurseAmount(Double nurseAmount) {
		this.nurseAmount = nurseAmount;
	}

	public Double getOvertimeAmount() {
		return overtimeAmount;
	}

	public void setOvertimeAmount(Double overtimeAmount) {
		this.overtimeAmount = overtimeAmount;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public Double getTechPoints() {
		return techPoints;
	}

	public void setTechPoints(Double techPoints) {
		this.techPoints = techPoints;
	}

	public Double getDiagnosePoints() {
		return diagnosePoints;
	}

	public void setDiagnosePoints(Double diagnosePoints) {
		this.diagnosePoints = diagnosePoints;
	}

	public Double getStudentPoints() {
		return studentPoints;
	}

	public void setStudentPoints(Double studentPoints) {
		this.studentPoints = studentPoints;
	}

	public Double getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(Double pointAmount) {
		this.pointAmount = pointAmount;
	}

	public StaffPerformanceReportType getType() {
		return type;
	}

	public void setType(StaffPerformanceReportType type) {
		this.type = type;
	}
}
