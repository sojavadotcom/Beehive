package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

public class StaffPerformanceReport implements Serializable {
	private static final long serialVersionUID = 7168655447527551886L;

	public final static short TYPE_TITLE = 1;
	public final static short TYPE_AMOUNT = 2;
	public final static short TYPE_AMOUNT_TOTAL = 3;

	private Integer groupId;
	private String groupName;
	private Integer staffId;
	private String staffName;
	private Double dianoseCoef;
	private Double medicalAmount;
	private Double manageAmount;
	private Double nurseAmount;
	private Double overtimeAmount;
	private Double amountTotal;
	private Double techPoints;
	private Double diagnosePoints;
	private Double studentPoints;
	private Double pointAmount;
	private short type;

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
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
}
