package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the dic_rbrvs database table.
 * 
 */
@Entity
@Table(name="dic_rbrvs", schema="medicalimaging")
@NamedQuery(name="DicRbrv.findAll", query="SELECT d FROM DicRbrvs d")
public class DicRbrvs implements Serializable {
	private static final long serialVersionUID = -3403788007341032630L;

	@Id
	@SequenceGenerator(name="DIC_RBRVS_ID_GENERATOR", sequenceName="medicalimaging.DIC_RBRVS_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIC_RBRVS_ID_GENERATOR")
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

	private Double diagnostician;

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

	private String unit;

	private Double price;

	private String status;

	private Double technician;

	private String type;

	private String dept;

	//bi-directional many-to-one association to CalculatePerformance
	@OneToMany(mappedBy="dicRbrvs")
	private List<CalculatePerformance> calculatePerformances;

	//bi-directional many-to-one association to RbrvsPrice
	@OneToMany(mappedBy="dicRbrvs")
	private List<RbrvsPrice> rbrvsPrices;

	public DicRbrvs() {}

	public DicRbrvs(Integer id) {
		this.id = id;
	}

	public DicRbrvs(DicRbrvs rbrvs) {
		this.setId(rbrvs.getId());
		this.setCode(rbrvs.getCode());
		this.setCreateDeptId(rbrvs.getCreateDeptId());
		this.setCreateDeptName(rbrvs.getCreateDeptName());
		this.setCreateTime(rbrvs.getCreateTime());
		this.setCreateUserId(rbrvs.getCreateUserId());
		this.setCreateUserName(rbrvs.getCreateUserName());
		this.setDataFlag(rbrvs.getDataFlag());
		this.setDiagnostician(rbrvs.getDiagnostician());
		this.setKind(rbrvs.getKind());
		this.setModifyDeptId(rbrvs.getModifyDeptId());
		this.setModifyDeptName(rbrvs.getModifyDeptName());
		this.setModifyTime(rbrvs.getModifyTime());
		this.setModifyUserId(rbrvs.getModifyUserId());
		this.setModifyUserName(rbrvs.getModifyUserName());
		this.setName(rbrvs.getName());
		this.setUnit(rbrvs.getUnit());
		this.setPrice(rbrvs.getPrice());
		this.setStatus(rbrvs.getStatus());
		this.setTechnician(rbrvs.getTechnician());
		this.setType(rbrvs.getType());
		this.setDept(rbrvs.getDept());
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

	public Double getDiagnostician() {
		return this.diagnostician;
	}

	public void setDiagnostician(Double diagnostician) {
		this.diagnostician = diagnostician;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTechnician() {
		return this.technician;
	}

	public void setTechnician(Double technician) {
		this.technician = technician;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<CalculatePerformance> getCalculatePerformances() {
		return calculatePerformances;
	}

	public void setCalculatePerformances(
			List<CalculatePerformance> calculatePerformances) {
		this.calculatePerformances = calculatePerformances;
	}

	public List<RbrvsPrice> getRbrvsPrices() {
		return rbrvsPrices;
	}

	public void setRbrvsPrices(List<RbrvsPrice> rbrvsPrices) {
		this.rbrvsPrices = rbrvsPrices;
	}

}