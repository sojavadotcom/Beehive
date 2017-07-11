package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dic_coefficient database table.
 * 
 */
@Entity
@Table(name="dic_coefficient", schema="medicalimaging")
@NamedQuery(name="DicCoefficient.findAll", query="SELECT d FROM DicCoefficient d")
public class DicCoefficient implements Serializable {
	private static final long serialVersionUID = 5267881302687353221L;

	@Id
	@SequenceGenerator(name="DIC_COEFFICIENT_ID_GENERATOR", sequenceName="medicalimaging.DIC_COEFFICIENT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIC_COEFFICIENT_ID_GENERATOR")
	private Integer id;

	private String code;

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

	private String kind;

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

	private String name;

	private Double points;

	private String status;

	private String type;

	public DicCoefficient() {}

	public DicCoefficient(DicCoefficient coefficient) {
		this.setId(coefficient.getId());
		this.setCode(coefficient.getCode());
		this.setCreateDeptId(coefficient.getCreateDeptId());
		this.setCreateDeptName(coefficient.getCreateDeptName());
		this.setCreateTime(coefficient.getCreateTime());
		this.setCreateUserId(coefficient.getCreateUserId());
		this.setCreateUserName(coefficient.getCreateUserName());
		this.setDataFlag(coefficient.getDataFlag());
		this.setKind(coefficient.getKind());
		this.setModifyDeptId(coefficient.getModifyDeptId());
		this.setModifyDeptName(coefficient.getModifyDeptName());
		this.setModifyTime(coefficient.getModifyTime());
		this.setModifyUserId(coefficient.getModifyUserId());
		this.setModifyUserName(coefficient.getModifyUserName());
		this.setName(coefficient.getName());
		this.setPoints(coefficient.getPoints());
		this.setStatus(coefficient.getStatus());
		this.setType(coefficient.getType());
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPoints() {
		return this.points;
	}

	public void setPoints(Double points) {
		this.points = points;
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

}