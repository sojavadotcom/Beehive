package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the staff database table.
 * 
 */
@Entity
@Table(name="staff", schema="medicalimaging")
@NamedQuery(name="Staff.findAll", query="SELECT s FROM Staff s")
public class Staff implements Serializable {
	private static final long serialVersionUID = -5428146485688687119L;

	@Id
	@SequenceGenerator(name="STAFF_ID_GENERATOR", sequenceName="medicalimaging.STAFF_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STAFF_ID_GENERATOR")
	private Integer id;

	private String code;

//	@Column(name="tech_coef_id")
//	private Integer techCoefId;
	@ManyToOne
	@JoinColumn(name="tech_coef_id")
	private DicCoefficient techCoef;

//	@Column(name="diagno_coef_id")
//	private Integer diagnoCoefId;

	@ManyToOne
	@JoinColumn(name="diagno_coef_id")
	private DicCoefficient diagnosCoef;

//	@Column(name="verifier_coef_id")
//	private Integer verifierCoefId;

	@ManyToOne
	@JoinColumn(name="verifier_coef_id")
	private DicCoefficient verifyCoef;

//	@Column(name="nurse_coef_id")
//	private Integer nurseCoefId;
	@ManyToOne
	@JoinColumn(name="nurse_coef_id")
	private DicCoefficient nurseCoef;

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

	private String edu;

	@Column(name="is_student")
	private Short isStudent;

	private String job;

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

	private String practice;

	private String status;

	private String title;

	//bi-directional many-to-one association to Group
	@OneToMany(mappedBy="staff")
	private List<Group> groups;

	//bi-directional many-to-one association to Member
	@OneToMany(mappedBy="staff")
	private List<Member> members;

	@OneToMany(mappedBy="staff")
	private List<MiWorkload> miWorkloads;

	public Staff() {}

	public Staff(int id) {
		this.id = id;
	}

	public Staff(Staff staff) {
		this.setId(staff.getId());
		this.setCode(staff.getCode());
		this.setTechCoef(staff.getTechCoef());
		this.setDiagnosCoef(staff.getDiagnosCoef());
		this.setVerifyCoef(staff.getVerifyCoef());
		this.setNurseCoef(staff.getNurseCoef());
		this.setCreateDeptId(staff.getCreateDeptId());
		this.setCreateDeptName(staff.getCreateDeptName());
		this.setCreateTime(staff.getCreateTime());
		this.setCreateUserId(staff.getCreateUserId());
		this.setCreateUserName(staff.getCreateUserName());
		this.setDataFlag(staff.getDataFlag());
		this.setEdu(staff.getEdu());
		this.setIsStudent(staff.getIsStudent());
		this.setJob(staff.getJob());
		this.setKind(staff.getKind());
		this.setModifyDeptId(staff.getModifyDeptId());
		this.setModifyDeptName(staff.getModifyDeptName());
		this.setModifyTime(staff.getModifyTime());
		this.setModifyUserId(staff.getModifyUserId());
		this.setModifyUserName(staff.getModifyUserName());
		this.setName(staff.getName());
		this.setPractice(staff.getPractice());
		this.setStatus(staff.getStatus());
		this.setTitle(staff.getTitle());
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

	public String getEdu() {
		return this.edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public Short getIsStudent() {
		return this.isStudent;
	}

	public void setIsStudent(Short isStudent) {
		this.isStudent = isStudent;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
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

	public String getPractice() {
		return this.practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Group addGroup(Group group) {
		getGroups().add(group);
		group.setStaff(this);

		return group;
	}

	public Group removeGroup(Group group) {
		getGroups().remove(group);
		group.setStaff(null);

		return group;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Member addMember(Member member) {
		getMembers().add(member);
		member.setStaff(this);

		return member;
	}

	public Member removeMember(Member member) {
		getMembers().remove(member);
		member.setStaff(null);

		return member;
	}

	public List<MiWorkload> getMiWorkloads() {
		return miWorkloads;
	}

	public void setMiWorkloads(List<MiWorkload> miWorkloads) {
		this.miWorkloads = miWorkloads;
	}

	public DicCoefficient getTechCoef() {
		return techCoef;
	}

	public void setTechCoef(DicCoefficient techCoef) {
		this.techCoef = techCoef;
	}

	public DicCoefficient getDiagnosCoef() {
		return diagnosCoef;
	}

	public void setDiagnosCoef(DicCoefficient diagnosCoef) {
		this.diagnosCoef = diagnosCoef;
	}

	public DicCoefficient getVerifyCoef() {
		return verifyCoef;
	}

	public void setVerifyCoef(DicCoefficient verifyCoef) {
		this.verifyCoef = verifyCoef;
	}

	public DicCoefficient getNurseCoef() {
		return nurseCoef;
	}

	public void setNurseCoef(DicCoefficient nurseCoef) {
		this.nurseCoef = nurseCoef;
	}

}