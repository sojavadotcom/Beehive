package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Subselect;

import java.util.Date;


/**
 * The persistent class for the mi_executed database table.
 * 
 */
@Entity
@NamedQuery(name="VMiExecuted.findAll", query="SELECT m FROM VMiExecuted m")
@Subselect("select "
		+ "a.id,"
		+ "a.patient_name,"
		+ "a.medical_no,"
		+ "a.inhospital_no,"
		+ "a.register_no,"
		+ "a.apply_dept,"
		+ "a.apply_doctor,"
		+ "a.execute_dept,"
		+ "a.patient_type,"
		+ "a.medical_item,"
		+ "a.rbrvs_id,"
		+ "b.name as rbrvs_name,"
		+ "a.medical_part,"
		+ "b.technician as technician_value,"
		+ "b.diagnostician as diagnostician_value,"
//Technician
		+ "a.execute_technician_staff_id,"
		+ "a.execute_technician,"
		+ "coalesce(tc.points, 0) as execute_technician_coef,"
		+ "b.technician*coalesce(tc.points, 0) as execute_technician_value,"
//Assist
		+ "case when coalesce(a.execute_technician_staff_id, 0)=coalesce(a.execute_technician_associate_staff_id, 0) then null else a.execute_technician_associate_staff_id end as execute_technician_associate_staff_id,"
		+ "case when coalesce(a.execute_technician_staff_id, 0)=coalesce(a.execute_technician_associate_staff_id, 0) then null else a.execute_technician_associate end as execute_technician_associate,"
		+ "case when coalesce(a.execute_technician_staff_id, 0)=coalesce(a.execute_technician_associate_staff_id, 0) then 0 else coalesce(tac.points, 0) end as execute_technician_associate_coef,"
		+ "case when coalesce(a.execute_technician_staff_id, 0)=coalesce(a.execute_technician_associate_staff_id, 0) then 0 else b.technician*coalesce(tac.points, 0) end as execute_assist_value,"
//Diagnostician
		+ "a.execute_diagnostician_staff_id,"
		+ "a.execute_diagnostician,"
		+ "coalesce(dc.points, 0) as execute_diagnostician_coef,"
		+ "b.diagnostician*coalesce(dc.points,0) as execute_diagnostician_value,"
		+ "d.is_student as execute_diagnostician_is_student,"
//Verifier
		+ "case when coalesce(a.execute_diagnostician_staff_id, 0)=coalesce(a.execute_verifier_staff_id, 0) then null else a.execute_verifier_staff_id end as execute_verifier_staff_id,"
		+ "case when coalesce(a.execute_diagnostician_staff_id, 0)=coalesce(a.execute_verifier_staff_id, 0) then null else a.execute_verifier end as execute_verifier,"
		+ "case when coalesce(a.execute_diagnostician_staff_id, 0)=coalesce(a.execute_verifier_staff_id, 0) then 0 else coalesce(vc.points, 0) end as execute_verifier_coef,"
		+ "case when coalesce(a.execute_diagnostician_staff_id, 0)=coalesce(a.execute_verifier_staff_id, 0) then 0 else b.diagnostician*coalesce(vc.points,0) end as execute_verifier_value,"
//Nurse Deprecatede
		+ "a.execute_nurse_staff_id,"
		+ "a.execute_nurse,"
		+ "coalesce(nc.points, 0) as execute_nurse_coef,"
		+ "a.register_time,"
		+ "a.report_time::date as report_date,"
		+ "a.report_time,"
		+ "a.status,"
		+ "a.data_flag,"
		+ "a.type,"
		+ "a.kind"
		+ " from "
		+ "medicalimaging.mi_executed a"
		+ " left join medicalimaging.staff t on a.execute_technician_staff_id=t.id"
		+ " left join medicalimaging.dic_coefficient tc on t.tech_coef_id=tc.id"
		+ " left join medicalimaging.staff ta on a.execute_technician_associate_staff_id=ta.id"
		+ " left join medicalimaging.dic_coefficient tac on ta.tech_assist_coef_id=tac.id"
		+ " left join medicalimaging.staff d on a.execute_diagnostician_staff_id=d.id"
		+ " left join medicalimaging.dic_coefficient dc on d.diagno_coef_id=dc.id"
		+ " left join medicalimaging.staff v on a.execute_verifier_staff_id=v.id"
		+ " left join medicalimaging.dic_coefficient vc on v.verifier_coef_id=vc.id"
		+ " left join medicalimaging.staff n on a.execute_nurse_staff_id=n.id"
		+ " left join medicalimaging.dic_coefficient nc on n.nurse_coef_id=nc.id,"
		+ "medicalimaging.dic_rbrvs b"
		+ " where "
		+ "a.rbrvs_id=b.id")
public class VMiExecuted implements Serializable {
	private static final long serialVersionUID = 3893863153718656317L;

	@Id
	private String id;

	@Column(name="rbrvs_id")
	private Integer rbrvsId;

	@Column(name="patient_name")
	private String patientName;

	@Column(name="medical_no")
	private String medicalNo;

	@Column(name="inhospital_no")
	private String inhospitalNo;

	@Column(name="register_no")
	private String registerNo;

	@Column(name="apply_dept")
	private String applyDept;

	@Column(name="apply_doctor")
	private String applyDoctor;

	@Column(name="execute_dept")
	private String executeDept;

	@Column(name="patient_type")
	private String patientType;

	@Column(name="medical_item")
	private String medicalItem;

	@Column(name="rbrvs_name")
	private String rbrvsName;

	@Column(name="technician_value")
	private Double technicianValue;

	@Column(name="diagnostician_value")
	private Double diagnosticianValue;

	@Column(name="execute_technician_value")
	private Double executeTechnicianValue;

	@Column(name="execute_diagnostician_value")
	private Double executeDiagnosticianValue;

	@Column(name="execute_assist_value")
	private Double executeAssistValue;

	@Column(name="execute_verifier_value")
	private Double executeVerifierValue;

	@Column(name="medical_part")
	private String medicalPart;

	@Column(name="execute_technician_staff_id")
	private Integer executeTechnicianStaffId;

	@Column(name="execute_technician")
	private String executeTechnician;

	@Column(name="execute_technician_coef")
	private Double executeTechnicianCoef;

	@Column(name="execute_technician_associate_staff_id")
	private Integer executeTechnicianAssociateStaffId;

	@Column(name="execute_technician_associate")
	private String executeTechnicianAssociate;

	@Column(name="execute_technician_associate_coef")
	private Double executeTechnicianAssociateCoef;

	@Column(name="execute_diagnostician_staff_id")
	private Integer executeDiagnosticianStaffId;

	@Column(name="execute_diagnostician")
	private String executeDiagnostician;

	@Column(name="execute_diagnostician_coef")
	private Double executeDiagnosticianCoef;

	@Column(name="execute_diagnostician_is_student")
	private Integer executeDiagnosticianIsStudent;

	@Column(name="execute_verifier_staff_id")
	private Integer executeVerifierStaffId;

	@Column(name="execute_verifier")
	private String executeVerifier;

	@Column(name="execute_verifierCoef")
	private Double executeVerifierCoef;

	@Column(name="execute_nurse_staff_id")
	private Integer executeNurseStaffId;

	@Column(name="execute_nurse")
	private String executeNurse;

	@Column(name="execute_nurse_coef")
	private Double executeNurseCoef;

	@Column(name="register_time")
	private Date registerTime;

	@Column(name="report_date")
	private Date reportDate;

	@Column(name="report_time")
	private Date reportTime;

	@Column(name="type")
	private String type;

	@Column(name="kind")
	private String kind;

	private String status;

	@Column(name="data_flag")
	private Short dataFlag;

	public VMiExecuted() {}

	public VMiExecuted(VMiExecuted miExecuted) {
		this.setId(miExecuted.getId());
		this.setMedicalNo(miExecuted.getMedicalNo());
		this.setMedicalItem(miExecuted.getMedicalItem());
		this.setApplyDept(miExecuted.getApplyDept());
		this.setApplyDoctor(miExecuted.getApplyDoctor());
		this.setDataFlag(miExecuted.getDataFlag());
		this.setExecuteDept(miExecuted.getExecuteDept());
		this.setExecuteTechnicianStaffId(miExecuted.getExecuteTechnicianStaffId());
		this.setExecuteTechnician(miExecuted.getExecuteTechnician());
		this.setExecuteTechnicianCoef(miExecuted.getExecuteTechnicianCoef());
		this.setExecuteTechnicianAssociateStaffId(miExecuted.getExecuteTechnicianAssociateStaffId());
		this.setExecuteTechnicianAssociate(miExecuted.getExecuteTechnicianAssociate());
		this.setExecuteTechnicianAssociateCoef(miExecuted.getExecuteTechnicianAssociateCoef());
		this.setExecuteDiagnosticianStaffId(miExecuted.getExecuteDiagnosticianStaffId());
		this.setExecuteDiagnostician(miExecuted.getExecuteDiagnostician());
		this.setExecuteDiagnosticianCoef(miExecuted.getExecuteDiagnosticianCoef());
		this.setExecuteVerifierStaffId(miExecuted.getExecuteVerifierStaffId());
		this.setExecuteVerifier(miExecuted.getExecuteVerifier());
		this.setExecuteVerifierCoef(miExecuted.getExecuteVerifierCoef());
		this.setExecuteNurseStaffId(miExecuted.getExecuteNurseStaffId());
		this.setExecuteNurse(miExecuted.getExecuteNurse());
		this.setExecuteNurseCoef(miExecuted.getExecuteNurseCoef());
		this.setInhospitalNo(miExecuted.getInhospitalNo());
		this.setMedicalPart(miExecuted.getMedicalPart());
		this.setPatientName(miExecuted.getPatientName());
		this.setPatientType(miExecuted.getPatientType());
		this.setRegisterNo(miExecuted.getRegisterNo());
		this.setRegisterTime(miExecuted.getRegisterTime());
		this.setReportDate(miExecuted.getReportDate());
		this.setReportTime(miExecuted.getReportTime());
		this.setStatus(miExecuted.getStatus());
		this.setRbrvsId(miExecuted.getRbrvsId());
		this.setType(miExecuted.getType());
		this.setKind(miExecuted.getKind());
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyDept() {
		return this.applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
	}

	public String getApplyDoctor() {
		return this.applyDoctor;
	}

	public void setApplyDoctor(String applyDoctor) {
		this.applyDoctor = applyDoctor;
	}

	public Short getDataFlag() {
		return this.dataFlag;
	}

	public void setDataFlag(Short dataFlag) {
		this.dataFlag = dataFlag;
	}

	public String getExecuteDept() {
		return this.executeDept;
	}

	public void setExecuteDept(String executeDept) {
		this.executeDept = executeDept;
	}

	public String getExecuteDiagnostician() {
		return this.executeDiagnostician;
	}

	public void setExecuteDiagnostician(String executeDiagnostician) {
		this.executeDiagnostician = executeDiagnostician;
	}

	public String getExecuteNurse() {
		return this.executeNurse;
	}

	public void setExecuteNurse(String executeNurse) {
		this.executeNurse = executeNurse;
	}

	public String getExecuteTechnician() {
		return this.executeTechnician;
	}

	public void setExecuteTechnician(String executeTechnician) {
		this.executeTechnician = executeTechnician;
	}

	public String getExecuteTechnicianAssociate() {
		return this.executeTechnicianAssociate;
	}

	public void setExecuteTechnicianAssociate(String executeTechnicianAssociate) {
		this.executeTechnicianAssociate = executeTechnicianAssociate;
	}

	public String getExecuteVerifier() {
		return this.executeVerifier;
	}

	public void setExecuteVerifier(String executeVerifier) {
		this.executeVerifier = executeVerifier;
	}

	public String getInhospitalNo() {
		return this.inhospitalNo;
	}

	public void setInhospitalNo(String inhospitalNo) {
		this.inhospitalNo = inhospitalNo;
	}

	public String getMedicalPart() {
		return this.medicalPart;
	}

	public void setMedicalPart(String medicalPart) {
		this.medicalPart = medicalPart;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getRegisterNo() {
		return this.registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRbrvsId() {
		return rbrvsId;
	}

	public void setRbrvsId(Integer rbrvsId) {
		this.rbrvsId = rbrvsId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getExecuteTechnicianStaffId() {
		return executeTechnicianStaffId;
	}

	public void setExecuteTechnicianStaffId(Integer executeTechnicianStaffId) {
		this.executeTechnicianStaffId = executeTechnicianStaffId;
	}

	public Integer getExecuteTechnicianAssociateStaffId() {
		return executeTechnicianAssociateStaffId;
	}

	public void setExecuteTechnicianAssociateStaffId(Integer executeTechnicianAssociateStaffId) {
		this.executeTechnicianAssociateStaffId = executeTechnicianAssociateStaffId;
	}

	public Integer getExecuteDiagnosticianStaffId() {
		return executeDiagnosticianStaffId;
	}

	public void setExecuteDiagnosticianStaffId(Integer executeDiagnosticianStaffId) {
		this.executeDiagnosticianStaffId = executeDiagnosticianStaffId;
	}

	public Integer getExecuteVerifierStaffId() {
		return executeVerifierStaffId;
	}

	public void setExecuteVerifierStaffId(Integer executeVerifierStaffId) {
		this.executeVerifierStaffId = executeVerifierStaffId;
	}

	public Integer getExecuteNurseStaffId() {
		return executeNurseStaffId;
	}

	public void setExecuteNurseStaffId(Integer executeNurseStaffId) {
		this.executeNurseStaffId = executeNurseStaffId;
	}

	public String getMedicalNo() {
		return medicalNo;
	}

	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}

	public String getMedicalItem() {
		return medicalItem;
	}

	public void setMedicalItem(String medicalItem) {
		this.medicalItem = medicalItem;
	}

	public Double getExecuteTechnicianCoef() {
		return executeTechnicianCoef;
	}

	public void setExecuteTechnicianCoef(Double executeTechnicianCoef) {
		this.executeTechnicianCoef = executeTechnicianCoef;
	}

	public Double getExecuteTechnicianAssociateCoef() {
		return executeTechnicianAssociateCoef;
	}

	public void setExecuteTechnicianAssociateCoef(
			Double executeTechnicianAssociateCoef) {
		this.executeTechnicianAssociateCoef = executeTechnicianAssociateCoef;
	}

	public Double getExecuteDiagnosticianCoef() {
		return executeDiagnosticianCoef;
	}

	public void setExecuteDiagnosticianCoef(Double executeDiagnosticianCoef) {
		this.executeDiagnosticianCoef = executeDiagnosticianCoef;
	}

	public Double getExecuteVerifierCoef() {
		return executeVerifierCoef;
	}

	public void setExecuteVerifierCoef(Double executeVerifierCoef) {
		this.executeVerifierCoef = executeVerifierCoef;
	}

	public Double getExecuteNurseCoef() {
		return executeNurseCoef;
	}

	public void setExecuteNurseCoef(Double executeNurseCoef) {
		this.executeNurseCoef = executeNurseCoef;
	}

	public Double getTechnicianValue() {
		return technicianValue;
	}

	public void setTechnicianValue(Double technicianValue) {
		this.technicianValue = technicianValue;
	}

	public Double getDiagnosticianValue() {
		return diagnosticianValue;
	}

	public void setDiagnosticianValue(Double diagnosticianValue) {
		this.diagnosticianValue = diagnosticianValue;
	}

	public String getRbrvsName() {
		return rbrvsName;
	}

	public void setRbrvsName(String rbrvsName) {
		this.rbrvsName = rbrvsName;
	}

	public Integer getExecuteDiagnosticianIsStudent() {
		return executeDiagnosticianIsStudent;
	}

	public void setExecuteDiagnosticianIsStudent(Integer executeDiagnosticianIsStudent) {
		this.executeDiagnosticianIsStudent = executeDiagnosticianIsStudent;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Double getExecuteTechnicianValue() {
		return executeTechnicianValue;
	}

	public void setExecuteTechnicianValue(Double executeTechnicianValue) {
		this.executeTechnicianValue = executeTechnicianValue;
	}

	public Double getExecuteDiagnosticianValue() {
		return executeDiagnosticianValue;
	}

	public void setExecuteDiagnosticianValue(Double executeDiagnosticianValue) {
		this.executeDiagnosticianValue = executeDiagnosticianValue;
	}

	public Double getExecuteAssistValue() {
		return executeAssistValue;
	}

	public void setExecuteAssistValue(Double executeAssistValue) {
		this.executeAssistValue = executeAssistValue;
	}

	public Double getExecuteVerifierValue() {
		return executeVerifierValue;
	}

	public void setExecuteVerifierValue(Double executeVerifierValue) {
		this.executeVerifierValue = executeVerifierValue;
	}

}