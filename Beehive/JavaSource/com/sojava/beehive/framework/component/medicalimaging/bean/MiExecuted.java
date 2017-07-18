package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mi_executed database table.
 * 
 */
@Entity
@Table(name="mi_executed", schema="medicalimaging")
@NamedQuery(name="MiExecuted.findAll", query="SELECT m FROM MiExecuted m")
public class MiExecuted implements Serializable {
	private static final long serialVersionUID = 3893863153718656317L;

	@EmbeddedId
	private MiExecutedPK id;

	@Column(name="apply_dept")
	private String applyDept;

	@Column(name="apply_doctor")
	private String applyDoctor;

	@Column(name="create_dept_id")
	private String createDeptId;

	@Column(name="create_dept_name")
	private String createDeptName;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="create_user_id")
	private String createUserId;

	@Column(name="create_user_name")
	private String createUserName;

	@Column(name="data_flag")
	private Short dataFlag;

	@Column(name="execute_dept")
	private String executeDept;

	@Column(name="execute_technician_staff_id")
	private Integer executeTechnicianStaffId;

	@Column(name="execute_technician")
	private String executeTechnician;

	@Column(name="execute_technician_associate_staff_id")
	private Integer executeTechnicianAssociateStaffId;

	@Column(name="execute_technician_associate")
	private String executeTechnicianAssociate;

	@Column(name="execute_technician_drug_staff_id")
	private Integer executeTechnicianDrugStaffId;

	@Column(name="execute_technician_drug")
	private String executeTechnicianDrug;

	@Column(name="execute_diagnostician_staff_id")
	private Integer executeDiagnosticianStaffId;

	@Column(name="execute_diagnostician")
	private String executeDiagnostician;

	@Column(name="execute_verifier_staff_id")
	private Integer executeVerifierStaffId;

	@Column(name="execute_verifier")
	private String executeVerifier;

	@Column(name="execute_nurse_staff_id")
	private Integer executeNurseStaffId;

	@Column(name="execute_nurse")
	private String executeNurse;

	@Column(name="inhospital_no")
	private String inhospitalNo;

	@Column(name="medical_part")
	private String medicalPart;

	@Column(name="modify_dept_id")
	private String modifyDeptId;

	@Column(name="modify_dept_name")
	private String modifyDeptName;

	@Column(name="modify_time")
	private Date modifyTime;

	@Column(name="modify_user_id")
	private String modifyUserId;

	@Column(name="modify_user_name")
	private String modifyUserName;

	@Column(name="patient_name")
	private String patientName;

	@Column(name="patient_type")
	private String patientType;

	@Column(name="register_no")
	private String registerNo;

	@Column(name="register_time")
	private Date registerTime;

	@Column(name="report_time")
	private Date reportTime;

	private String status;

	@Column(name="rbrvs_id")
	private Integer rbrvsId;

	@Column(name="type")
	private String type;

	@Column(name="kind")
	private String kind;

	public MiExecuted() {
		this.id = new MiExecutedPK();
	}

	public MiExecuted(MiExecuted miExecuted) {
		MiExecutedPK pk = new MiExecutedPK(miExecuted.getId());
		this.setId(pk);
		this.setApplyDept(miExecuted.getApplyDept());
		this.setApplyDoctor(miExecuted.getApplyDoctor());
		this.setCreateDeptId(miExecuted.getCreateDeptId());
		this.setCreateDeptName(miExecuted.getCreateDeptName());
		this.setCreateTime(miExecuted.getCreateTime());
		this.setCreateUserId(miExecuted.getCreateUserId());
		this.setCreateUserName(miExecuted.getCreateUserName());
		this.setDataFlag(miExecuted.getDataFlag());
		this.setExecuteDept(miExecuted.getExecuteDept());
		this.setExecuteTechnicianStaffId(miExecuted.getExecuteTechnicianStaffId());
		this.setExecuteTechnician(miExecuted.getExecuteTechnician());
		this.setExecuteTechnicianAssociateStaffId(miExecuted.getExecuteTechnicianAssociateStaffId());
		this.setExecuteTechnicianAssociate(miExecuted.getExecuteTechnicianAssociate());
		this.setExecuteTechnicianDrugStaffId(miExecuted.getExecuteTechnicianDrugStaffId());
		this.setExecuteTechnicianDrug(miExecuted.getExecuteTechnicianDrug());
		this.setExecuteDiagnosticianStaffId(miExecuted.getExecuteDiagnosticianStaffId());
		this.setExecuteDiagnostician(miExecuted.getExecuteDiagnostician());
		this.setExecuteVerifierStaffId(miExecuted.getExecuteVerifierStaffId());
		this.setExecuteVerifier(miExecuted.getExecuteVerifier());
		this.setExecuteNurseStaffId(miExecuted.getExecuteNurseStaffId());
		this.setExecuteNurse(miExecuted.getExecuteNurse());
		this.setInhospitalNo(miExecuted.getInhospitalNo());
		this.setMedicalPart(miExecuted.getMedicalPart());
		this.setModifyDeptId(miExecuted.getModifyDeptId());
		this.setModifyDeptName(miExecuted.getModifyDeptName());
		this.setModifyTime(miExecuted.getModifyTime());
		this.setModifyUserId(miExecuted.getModifyUserId());
		this.setModifyUserName(miExecuted.getModifyUserName());
		this.setPatientName(miExecuted.getPatientName());
		this.setPatientType(miExecuted.getPatientType());
		this.setRegisterNo(miExecuted.getRegisterNo());
		this.setRegisterTime(miExecuted.getRegisterTime());
		this.setReportTime(miExecuted.getReportTime());
		this.setStatus(miExecuted.getStatus());
		this.setRbrvsId(miExecuted.getRbrvsId());
		this.setType(miExecuted.getType());
		this.setKind(miExecuted.getKind());
	}

	public MiExecutedPK getId() {
		return this.id;
	}

	public void setId(MiExecutedPK id) {
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

	public String getCreateDeptId() {
		return this.createDeptId;
	}

	public void setCreateDeptId(String createDeptId) {
		this.createDeptId = createDeptId;
	}

	public String getCreateDeptName() {
		return this.createDeptName;
	}

	public void setCreateDeptName(String createDeptName) {
		this.createDeptName = createDeptName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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

	public String getModifyDeptId() {
		return this.modifyDeptId;
	}

	public void setModifyDeptId(String modifyDeptId) {
		this.modifyDeptId = modifyDeptId;
	}

	public String getModifyDeptName() {
		return this.modifyDeptName;
	}

	public void setModifyDeptName(String modifyDeptName) {
		this.modifyDeptName = modifyDeptName;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return this.modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
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

	public Integer getExecuteTechnicianDrugStaffId() {
		return executeTechnicianDrugStaffId;
	}

	public void setExecuteTechnicianDrugStaffId(Integer executeTechnicianDrugStaffId) {
		this.executeTechnicianDrugStaffId = executeTechnicianDrugStaffId;
	}

	public String getExecuteTechnicianDrug() {
		return executeTechnicianDrug;
	}

	public void setExecuteTechnicianDrug(String executeTechnicianDrug) {
		this.executeTechnicianDrug = executeTechnicianDrug;
	}

}