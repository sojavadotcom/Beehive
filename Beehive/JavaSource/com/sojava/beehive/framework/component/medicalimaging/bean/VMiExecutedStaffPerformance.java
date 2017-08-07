package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect(
		"select "
		+ "row_number() over(order by g.work_statistics_id, g.group_id, g.staff_id) as id,"
		+ "g.work_statistics_id,"
		+ "g.group_id,"
		+ "g.group_name,"
		+ "g.staff_id,"
		+ "g.staff_name,"
		+ "g.staff_title,"
		+ "g.group_leader,"
		+ "g.staff_diagno_coef,"
		+ "g.staff_job_coef,"
		+ "tech.amount as tech_worker_amount,"
		+ "tech.worker_quantity as tech_worker_quantity,"
		+ "tech.worker_statis_quantity as tech_worker_statis_quantity,"
		+ "tech.point_total as tech_point_total,"
		+ "case when g.staff_kind='医生' and g.staff_title is not null then diagno.amount else null end as diagno_worker_amount,"
		+ "case when g.staff_kind='医生' and g.staff_title is not null then diagno.worker_quantity else null end as diagno_worker_quantity,"
		+ "case when g.staff_kind='医生' and g.staff_title is not null then diagno.worker_statis_quantity else null end as diagno_worker_statis_quantity,"
		+ "case when g.staff_kind='医生' and g.staff_title is not null then diagno.point_total else null end as diagno_point_total,"
		+ "case when g.staff_kind='医生' and g.staff_title is null then diagno.amount else null end as assist_worker_amount,"
		+ "case when g.staff_kind='医生' and g.staff_title is null then diagno.worker_quantity else null end as assist_worker_quantity,"
		+ "case when g.staff_kind='医生' and g.staff_title is null then diagno.worker_statis_quantity else null end as assist_worker_statis_quantity,"
		+ "case when g.staff_kind='医生' and g.staff_title is null then diagno.point_total else null end as assist_point_total"
		+ " from "
		+ "medicalimaging.v_group g"
		+ " left join medicalimaging.v_mi_executed_workload tech on g.staff_id=tech.worker_id and g.work_statistics_id=tech.work_statistics_id and tech.type in ('投照')"
		+ " left join medicalimaging.v_mi_executed_workload diagno on g.staff_id=diagno.worker_id and g.work_statistics_id=diagno.work_statistics_id and diagno.type in ('诊断')"
		+ " order by g.work_statistics_id, g.group_id, g.staff_id"
	)
@NamedQuery(name="VMiExecutedStaffPerformance.findAll", query="SELECT v FROM VMiExecutedStaffPerformance v")
public class VMiExecutedStaffPerformance implements Serializable {
	private static final long serialVersionUID = -6606241887098930852L;

	@Id
	private Integer id;

	@Column(name="work_statistics_id")
	private Integer workStatisticsId;

	@Column(name="group_id")
	private Integer groupId;

	@Column(name="group_name")
	private String groupName;

	@Column(name="staff_id")
	private Integer staffId;

	@Column(name="staff_name")
	private String staffName;

	@Column(name="staff_title")
	private String staffTitle;

	@Column(name="group_leader")
	private String groupLeader;

	@Column(name="staff_diagno_coef")
	private Double staffDiagnoCoef;

	@Column(name="staff_job_coef")
	private Double staffJobCoef;

	@Transient
	private Double medicalTotal;

	@Transient
	private Double manageTotal;

	@Transient
	private Double totalAmount;

	@Column(name="tech_worker_amount")
	private Double techWorkerAmount;

	@Column(name="tech_worker_quantity")
	private Double techWorkerQuantity;

	@Column(name="tech_worker_statis_quantity")
	private Double techWorkerStatisQuantity;

	@Column(name="tech_point_total")
	private Double techPointTotal;

	@Column(name="diagno_worker_amount")
	private Double diagnoWorkerAmount;

	@Column(name="diagno_worker_quantity")
	private Double diagnoWorkerQuantity;

	@Column(name="diagno_worker_statis_quantity")
	private Double diagnoWorkerStatisQuantity;

	@Column(name="diagno_point_total")
	private Double diagnoPointTotal;

	@Column(name="assist_worker_amount")
	private Double assistWorkerAmount;

	@Column(name="assist_worker_quantity")
	private Double assistWorkerQuantity;

	@Column(name="assist_worker_statis_quantity")
	private Double assistWorkerStatisQuantity;

	@Column(name="assist_point_total")
	private Double assistPointTotal;

	@Transient
	private Double pointTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkStatisticsId() {
		return workStatisticsId;
	}

	public void setWorkStatisticsId(Integer workStatisticsId) {
		this.workStatisticsId = workStatisticsId;
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

	public String getStaffTitle() {
		return staffTitle;
	}

	public void setStaffTitle(String staffTitle) {
		this.staffTitle = staffTitle;
	}

	public String getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}

	public Double getStaffDiagnoCoef() {
		return staffDiagnoCoef;
	}

	public void setStaffDiagnoCoef(Double staffDiagnoCoef) {
		this.staffDiagnoCoef = staffDiagnoCoef;
	}

	public Double getStaffJobCoef() {
		return staffJobCoef;
	}

	public void setStaffJobCoef(Double staffJobCoef) {
		this.staffJobCoef = staffJobCoef;
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

	public Double getTechWorkerAmount() {
		return techWorkerAmount;
	}

	public void setTechWorkerAmount(Double techWorkerAmount) {
		this.techWorkerAmount = techWorkerAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTechWorkerQuantity() {
		return techWorkerQuantity;
	}

	public void setTechWorkerQuantity(Double techWorkerQuantity) {
		this.techWorkerQuantity = techWorkerQuantity;
	}

	public Double getTechWorkerStatisQuantity() {
		return techWorkerStatisQuantity;
	}

	public void setTechWorkerStatisQuantity(Double techWorkerStatisQuantity) {
		this.techWorkerStatisQuantity = techWorkerStatisQuantity;
	}

	public Double getTechPointTotal() {
		return techPointTotal;
	}

	public void setTechPointTotal(Double techPointTotal) {
		this.techPointTotal = techPointTotal;
	}

	public Double getDiagnoWorkerAmount() {
		return diagnoWorkerAmount;
	}

	public void setDiagnoWorkerAmount(Double diagnoWorkerAmount) {
		this.diagnoWorkerAmount = diagnoWorkerAmount;
	}

	public Double getDiagnoWorkerQuantity() {
		return diagnoWorkerQuantity;
	}

	public void setDiagnoWorkerQuantity(Double diagnoWorkerQuantity) {
		this.diagnoWorkerQuantity = diagnoWorkerQuantity;
	}

	public Double getDiagnoWorkerStatisQuantity() {
		return diagnoWorkerStatisQuantity;
	}

	public void setDiagnoWorkerStatisQuantity(Double diagnoWorkerStatisQuantity) {
		this.diagnoWorkerStatisQuantity = diagnoWorkerStatisQuantity;
	}

	public Double getDiagnoPointTotal() {
		return diagnoPointTotal;
	}

	public void setDiagnoPointTotal(Double diagnoPointTotal) {
		this.diagnoPointTotal = diagnoPointTotal;
	}

	public Double getAssistWorkerAmount() {
		return assistWorkerAmount;
	}

	public void setAssistWorkerAmount(Double assistWorkerAmount) {
		this.assistWorkerAmount = assistWorkerAmount;
	}

	public Double getAssistWorkerQuantity() {
		return assistWorkerQuantity;
	}

	public void setAssistWorkerQuantity(Double assistWorkerQuantity) {
		this.assistWorkerQuantity = assistWorkerQuantity;
	}

	public Double getAssistWorkerStatisQuantity() {
		return assistWorkerStatisQuantity;
	}

	public void setAssistWorkerStatisQuantity(Double assistWorkerStatisQuantity) {
		this.assistWorkerStatisQuantity = assistWorkerStatisQuantity;
	}

	public Double getAssistPointTotal() {
		return assistPointTotal;
	}

	public void setAssistPointTotal(Double assistPointTotal) {
		this.assistPointTotal = assistPointTotal;
	}

	public Double getPointTotal() {
		return pointTotal;
	}

	public void setPointTotal(Double pointTotal) {
		this.pointTotal = pointTotal;
	}
}
