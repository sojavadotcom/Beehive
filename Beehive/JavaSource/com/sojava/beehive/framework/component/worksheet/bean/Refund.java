package com.sojava.beehive.framework.component.worksheet.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the refund database table.
 * 
 */
@Entity
@Table(name = "refund", schema = "worksheet")
@NamedQuery(name="Refund.findAll", query="SELECT r FROM Refund r")
public class Refund implements Serializable {
	private static final long serialVersionUID = 7079162900572040817L;

	@Id
	@SequenceGenerator(name="REFUND_ID_GENERATOR", sequenceName="WORKSHEET.REFUND_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REFUND_ID_GENERATOR")
	private Integer id;

	@Column(name="cashier_cause")
	private String cashierCause;

	@Column(name="data_flag")
	private Short dataFlag;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="dept_id")
	private Integer deptId;

	@Column(name="dept_name")
	private String deptName;

	@Column(name="doctor_cause")
	private String doctorCause;

	@Column(name="patient_cause")
	private String patientCause;

	@Column(name="summary_causer")
	private String summaryCauser;

	@Column(name="invoice_no")
	private Integer invoiceNo;

	private String item;

	private String kind;

	private String operator;

	@Column(name="operator_cause")
	private String operatorCause;

	@Column(name="outpatient_serial")
	private Integer outpatientSerial;

	@Column(name="patient_name")
	private String patientName;

	private BigDecimal sum;

	@Column(name = "create_user_id")
	private String createUserId;
	@Column(name = "create_user_name")
	private String createUserName;
	@Column(name = "create_dept_id")
	private String createDeptId;
	@Column(name = "create_dept_name")
	private String createDeptName;
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@Column(name = "modify_user_id")
	private String modifyUserId;
	@Column(name = "modify_user_name")
	private String modifyUserName;
	@Column(name = "modify_dept_id")
	private String modifyDeptId;
	@Column(name = "modify_dept_name")
	private String modifyDeptName;
	@Column(name = "modify_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;

	public Refund() {
		super();
	}

	public Refund(Refund refund) {
		super();
		imp(refund);
	}

	public void imp(Refund refund) {
		this.id = refund.getId();
		this.dataFlag = refund.getDataFlag();
		this.date = refund.getDate();
		this.deptId = refund.getDeptId();
		this.deptName = refund.getDeptName();
		this.doctorCause = refund.getDoctorCause();
		this.operatorCause = refund.getOperatorCause();
		this.cashierCause = refund.getCashierCause();
		this.patientCause = refund.getPatientCause();
		this.invoiceNo = refund.getInvoiceNo();
		this.item = refund.getItem();
		this.kind = refund.getKind();
		this.operator = refund.getOperator();
		this.outpatientSerial = refund.getOutpatientSerial();
		this.patientName = refund.getPatientName();
		this.sum = refund.getSum();
		this.createUserId = refund.getCreateUserId();
		this.createUserName = refund.getCreateUserName();
		this.createDeptId = refund.getCreateDeptId();
		this.createDeptName = refund.getCreateDeptName();
		this.createTime = refund.getCreateTime();
		this.modifyUserId = refund.getModifyUserId();
		this.modifyUserName = refund.getModifyUserName();
		this.modifyDeptId = refund.getModifyDeptId();
		this.modifyDeptName = refund.getModifyDeptName();
		this.modifyTime = refund.getModifyTime();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCashierCause() {
		return this.cashierCause;
	}

	public void setCashierCause(String cashierCause) {
		this.cashierCause = cashierCause;
	}

	public Short getDataFlag() {
		return this.dataFlag;
	}

	public void setDataFlag(Short dataFlag) {
		this.dataFlag = dataFlag;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDoctorCause() {
		return this.doctorCause;
	}

	public void setDoctorCause(String doctorCause) {
		this.doctorCause = doctorCause;
	}

	public String getPatientCause() {
		return patientCause;
	}

	public void setPatientCause(String patientCause) {
		this.patientCause = patientCause;
	}

	public Integer getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorCause() {
		return this.operatorCause;
	}

	public void setOperatorCause(String operatorCause) {
		this.operatorCause = operatorCause;
	}

	public Integer getOutpatientSerial() {
		return this.outpatientSerial;
	}

	public void setOutpatientSerial(Integer outpatientSerial) {
		this.outpatientSerial = outpatientSerial;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public BigDecimal getSum() {
		return this.sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateDeptId() {
		return createDeptId;
	}

	public void setCreateDeptId(String createDeptId) {
		this.createDeptId = createDeptId;
	}

	public String getCreateDeptName() {
		return createDeptName;
	}

	public void setCreateDeptName(String createDeptName) {
		this.createDeptName = createDeptName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public String getModifyDeptId() {
		return modifyDeptId;
	}

	public void setModifyDeptId(String modifyDeptId) {
		this.modifyDeptId = modifyDeptId;
	}

	public String getModifyDeptName() {
		return modifyDeptName;
	}

	public void setModifyDeptName(String modifyDeptName) {
		this.modifyDeptName = modifyDeptName;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSummaryCauser() {
		return summaryCauser;
	}

	public void setSummaryCauser(String summaryCauser) {
		this.summaryCauser = summaryCauser;
	}

}