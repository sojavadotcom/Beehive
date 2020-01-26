package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the wx_userinfo database table.
 * 
 */
@Entity
@Table(name="user", schema="wechat")
@NamedQuery(name="WxUserinfo.findAll", query="SELECT w FROM User w")
public class User implements Serializable {
	private static final long serialVersionUID = 132244651809462502L;

	@Id
	@SequenceGenerator(name="USER_ID_GENERATOR", sequenceName="WECHAT.USER_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ID_GENERATOR")
	private Integer id;

	@Column(name="access_token")
	private String accessToken;

	private String city;

	private String country;

	@Column(name="dept_name")
	private String deptName;

	@Column(name="expires_in")
	private Long expiresIn;

	@Column(name="expires_time")
	private Timestamp expiresTime;

	private String headimgurl;

	@Column(name="mobile_short_number")
	private String mobileShortNumber;

	@Column(name="mobile_number")
	private String mobileNumber;

	private String nickname;

	private String openid;

	private String privilege;

	private String province;

	@Column(name="request_time")
	private Timestamp requestTime;

	private String sex;

	@Column(name="staff_id")
	private Integer staffId;

	@Column(name="staff_name")
	private String staffName;

	@Column(name="admin_duty")
	private String adminDuty;

	@Column(name="job_title")
	private String jobTitle;

	private String unionid;

	private String platform;

	private String role;

	private String status;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getExpiresIn() {
		return this.expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Timestamp getExpiresTime() {
		return this.expiresTime;
	}

	public void setExpiresTime(Timestamp expiresTime) {
		this.expiresTime = expiresTime;
	}

	public String getHeadimgurl() {
		return this.headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Timestamp getRequestTime() {
		return this.requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdminDuty() {
		return adminDuty;
	}

	public void setAdminDuty(String adminDuty) {
		this.adminDuty = adminDuty;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getMobileShortNumber() {
		return mobileShortNumber;
	}

	public void setMobileShortNumber(String mobileShortNumber) {
		this.mobileShortNumber = mobileShortNumber;
	}

}