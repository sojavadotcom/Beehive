package com.sojava.beehive.framework.component.inpatienthomepage.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the icd_transform database table.
 * 
 */
@Entity
@Table(name="icd_transform", schema="data_transform")
@NamedQuery(name="IcdTransform.findAll", query="SELECT i FROM IcdTransform i")
public class IcdTransform implements Serializable {
	private static final long serialVersionUID = -8790941374625566977L;

	@Id
	@SequenceGenerator(name="ICD_TRANSFORM_ID_GENERATOR", sequenceName="DATA_TRANSFORM.ICD_TRANSFORM_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ICD_TRANSFORM_ID_GENERATOR")
	private Integer id;

	private String catalog;

	private String code;

	private String code1;

	@Column(name="create_dept_id")
	private String createDeptId;

	@Column(name="create_dept_name")
	private String createDeptName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="create_user_id")
	private String createUserId;

	@Column(name="create_user_name")
	private String createUserName;

	@Column(name="data_flag")
	private Integer dataFlag;

	private String diagno;

	private String kind;

	@Column(name="match_id")
	private Integer matchId;

	private String memo;

	@Column(name="modify_dept_id")
	private String modifyDeptId;

	@Column(name="modify_dept_name")
	private String modifyDeptName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modify_time")
	private Date modifyTime;

	@Column(name="modify_user_id")
	private String modifyUserId;

	@Column(name="modify_user_name")
	private String modifyUserName;

	@Column(name="ori_diagno")
	private String oriDiagno;

	@Column(name="orig_code")
	private String origCode;

	@Column(name="parent_code")
	private String parentCode;

	private String status;

	private String type;

	private Integer used;

	private Integer version;

	public IcdTransform() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatalog() {
		return this.catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode1() {
		return this.code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
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

	public Integer getDataFlag() {
		return this.dataFlag;
	}

	public void setDataFlag(Integer dataFlag) {
		this.dataFlag = dataFlag;
	}

	public String getDiagno() {
		return this.diagno;
	}

	public void setDiagno(String diagno) {
		this.diagno = diagno;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getMatchId() {
		return this.matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getOriDiagno() {
		return this.oriDiagno;
	}

	public void setOriDiagno(String oriDiagno) {
		this.oriDiagno = oriDiagno;
	}

	public String getOrigCode() {
		return this.origCode;
	}

	public void setOrigCode(String origCode) {
		this.origCode = origCode;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUsed() {
		return this.used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}