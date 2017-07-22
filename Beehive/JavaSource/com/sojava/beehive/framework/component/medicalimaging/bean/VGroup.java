package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect(
		"select "
		+ "row_number() over(order by g.id, s.id) as id,"
		+ "g.id as group_id,"
		+ "g.name as group_name,"
		+ "g.kind as group_kind,"
		+ "g.status as group_status,"
		+ "case when g.staff_id=s.id then '组长' else '组员' end as group_leader,"
		+ "s.id as staff_id,"
		+ "s.name as staff_name,"
		+ "s.job as staff_job,"
		+ "s.title as staff_title,"
		+ "s.edu as staff_edu,"
		+ "s.practice as staff_practice,"
		+ "s.is_student as staff_is_student,"
		+ "tc.points as staff_tech_coef,"
		+ "tac.points as staff_tech_assist_coef,"
		+ "dc.points as staff_diagno_coef,"
		+ "vc.points as staff_verifier_coef,"
		+ "jc.points as staff_job_coef,"
		+ "nc.points as staff_nurse_coef,"
		+ "s.kind as staff_kind,"
		+ "statis.id as work_statistics_id,"
		+ "statis.year as year,"
		+ "statis.month as month,"
		+ "statis.overtime_cost,"
		+ "statis.manage_total,"
		+ "statis.nurse_cost,"
		+ "statis.dept as dept"
		+ " from "
		+ "medicalimaging.\"group\" g,"
		+ "medicalimaging.member m,"
		+ "medicalimaging.work_statistics statis,"
		+ "medicalimaging.staff s"
		+ " left join dic_coefficient tc on s.tech_coef_id=tc.id"
		+ " left join dic_coefficient tac on s.tech_assist_coef_id=tac.id"
		+ " left join dic_coefficient dc on s.diagno_coef_id=dc.id"
		+ " left join dic_coefficient vc on s.verifier_coef_id=vc.id"
		+ " left join dic_coefficient jc on s.job_coef_id=jc.id"
		+ " left join dic_coefficient nc on s.nurse_coef_id=nc.id"
		+ " where "
		+ " g.id=m.group_id"
		+ " and m.staff_id=s.id"
	)
@NamedQuery(name="VGroup.findAll", query="SELECT v FROM VGroup v")
public class VGroup implements Serializable {
	private static final long serialVersionUID = 4270416234076825642L;

	@Id
	private Integer id;

	@Column(name="group_id")
	private Integer groupId;

	@Column(name="group_name")
	private String groupName;

	@Column(name="group_kind")
	private String groupKind;

	@Column(name="group_leader")
	private String groupLeader;

	@Column(name="group_status")
	private String status;

	@Column(name="staff_id")
	private Integer staffId;

	@Column(name="staff_name")
	private String staffName;

	@Column(name="staff_job")
	private String staffJob;

	@Column(name="staff_title")
	private String staffTitle;

	@Column(name="staff_edu")
	private String staffEdu;

	@Column(name="staff_practice")
	private String staffPractice;

	@Column(name="staff_is_student")
	private Integer staffIsStudent;

	@Column(name="staff_tech_coef")
	private Double staffTechCoef;

	@Column(name="staff_tech_assist_coef")
	private Double staffTechAssistCoef;

	@Column(name="staff_diagno_coef")
	private Double staffDiagnoCoef;

	@Column(name="staff_verifier_coef")
	private Double staffVerifierCoef;

	@Column(name="staff_job_coef")
	private Double staffJobCoef;

	@Column(name="staff_nurse_coef")
	private Double staffNurseCoef;

	@Column(name="staff_kind")
	private String staffKind;

	@Column(name="work_statistics_id")
	private Integer workStatisticsId;

	private Integer year;

	private Integer month;

	@Column(name="overtime_cost")
	private Double overtimeCost;

	@Column(name="manage_total")
	private Double manageTotal;

	@Column(name="nurse_cost")
	private Double nurseCost;

	private String dept;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getGroupKind() {
		return groupKind;
	}

	public void setGroupKind(String groupKind) {
		this.groupKind = groupKind;
	}

	public String getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getStaffJob() {
		return staffJob;
	}

	public void setStaffJob(String staffJob) {
		this.staffJob = staffJob;
	}

	public String getStaffTitle() {
		return staffTitle;
	}

	public void setStaffTitle(String staffTitle) {
		this.staffTitle = staffTitle;
	}

	public String getStaffEdu() {
		return staffEdu;
	}

	public void setStaffEdu(String staffEdu) {
		this.staffEdu = staffEdu;
	}

	public String getStaffPractice() {
		return staffPractice;
	}

	public void setStaffPractice(String staffPractice) {
		this.staffPractice = staffPractice;
	}

	public Integer getStaffIsStudent() {
		return staffIsStudent;
	}

	public void setStaffIsStudent(Integer staffIsStudent) {
		this.staffIsStudent = staffIsStudent;
	}

	public Double getStaffTechCoef() {
		return staffTechCoef;
	}

	public void setStaffTechCoef(Double staffTechCoef) {
		this.staffTechCoef = staffTechCoef;
	}

	public Double getStaffTechAssistCoef() {
		return staffTechAssistCoef;
	}

	public void setStaffTechAssistCoef(Double staffTechAssistCoef) {
		this.staffTechAssistCoef = staffTechAssistCoef;
	}

	public Double getStaffDiagnoCoef() {
		return staffDiagnoCoef;
	}

	public void setStaffDiagnoCoef(Double staffDiagnoCoef) {
		this.staffDiagnoCoef = staffDiagnoCoef;
	}

	public Double getStaffVerifierCoef() {
		return staffVerifierCoef;
	}

	public void setStaffVerifierCoef(Double staffVerifierCoef) {
		this.staffVerifierCoef = staffVerifierCoef;
	}

	public Double getStaffJobCoef() {
		return staffJobCoef;
	}

	public void setStaffJobCoef(Double staffJobCoef) {
		this.staffJobCoef = staffJobCoef;
	}

	public Double getStaffNurseCoef() {
		return staffNurseCoef;
	}

	public void setStaffNurseCoef(Double staffNurseCoef) {
		this.staffNurseCoef = staffNurseCoef;
	}

	public String getStaffKind() {
		return staffKind;
	}

	public void setStaffKind(String staffKind) {
		this.staffKind = staffKind;
	}

	public Integer getWorkStatisticsId() {
		return workStatisticsId;
	}

	public void setWorkStatisticsId(Integer workStatisticsId) {
		this.workStatisticsId = workStatisticsId;
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

	public Double getOvertimeCost() {
		return overtimeCost;
	}

	public void setOvertimeCost(Double overtimeCost) {
		this.overtimeCost = overtimeCost;
	}

	public Double getManageTotal() {
		return manageTotal;
	}

	public void setManageTotal(Double manageTotal) {
		this.manageTotal = manageTotal;
	}

	public Double getNurseCost() {
		return nurseCost;
	}

	public void setNurseCost(Double nurseCost) {
		this.nurseCost = nurseCost;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
