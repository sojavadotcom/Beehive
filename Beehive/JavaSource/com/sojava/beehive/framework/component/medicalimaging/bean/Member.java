package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@Table(name="member", schema="medicalimaging")
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 3896378587533105930L;

	@Id
	@SequenceGenerator(name="MEMBER_ID_GENERATOR", sequenceName="medicalimaging.MEMBER_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEMBER_ID_GENERATOR")
	private Integer id;

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

	private String status;

	private String type;

	//bi-directional many-to-one association to Group
	@ManyToOne
	private Group group;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	private Staff staff;

	public Member() {}

	public Member(Member member) {
		this.setId(member.getId());
		this.setCreateDeptId(member.getCreateDeptId());
		this.setCreateDeptName(member.getCreateDeptName());
		this.setCreateTime(member.getCreateTime());
		this.setCreateUserId(member.getCreateUserId());
		this.setCreateUserName(member.getCreateUserName());
		this.setDataFlag(member.getDataFlag());
		this.setModifyDeptId(member.getModifyDeptId());
		this.setModifyDeptName(member.getModifyDeptName());
		this.setModifyTime(member.getModifyTime());
		this.setModifyUserId(member.getModifyUserId());
		this.setModifyUserName(member.getModifyUserName());
		this.setStatus(member.getStatus());
		this.setType(member.getType());
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}