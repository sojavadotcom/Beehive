package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the wx_userinfo database table.
 * 
 */
@Entity
@Table(name="wx_userinfo")
@NamedQuery(name="WxUserinfo.findAll", query="SELECT w FROM WxUserinfo w")
public class WxUserinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WX_USERINFO_ID_GENERATOR", sequenceName="QUALITY.CASE_HISTORY_STANDARD_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WX_USERINFO_ID_GENERATOR")
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

	private String unionid;

	public WxUserinfo() {
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

}