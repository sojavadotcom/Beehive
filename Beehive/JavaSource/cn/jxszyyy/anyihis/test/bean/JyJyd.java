package cn.jxszyyy.anyihis.test.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the JY_JYD database table.
 * 
 */
@Entity
@Table(name="JY_JYD", schema="HIS")
@NamedQuery(name="JyJyd.findAll", query="SELECT j FROM JyJyd j")
public class JyJyd implements Serializable {
	private static final long serialVersionUID = 2875240190964054724L;

	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BHLY")
	private Short bhly;

	@Column(name="BLH")
	private String blh;

	@Column(name="FLAG")
	private Short flag;

	@Id
	@Column(name="JYDH")
	private Integer jydh;

	@Column(name="JYGROUPID")
	private Integer jygroupid;

	@Column(name="JYNR")
	private String jynr;

	@Column(name="JYRQ")
	private Integer jyrq;

	@Column(name="JYSJ")
	private Integer jysj;

	@Column(name="SHRQ")
	private Integer shrq;

	@Column(name="SHSJ")
	private Integer shsj;

	@Column(name="SQKS")
	private String sqks;

	@Column(name="SQRQ")
	private Integer sqrq;

	@Column(name="SQYS")
	private String sqys;

	@Column(name="TM")
	private String tm;

	@Column(name="YBH")
	private Integer ybh;

	@Column(name="YQBM")
	private String yqbm;

	@Column(name="YZZH")
	private Integer yzzh;

	public JyJyd() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(Integer bhid) {
		this.bhid = bhid;
	}

	public Short getBhly() {
		return this.bhly;
	}

	public void setBhly(Short bhly) {
		this.bhly = bhly;
	}

	public String getBlh() {
		return this.blh;
	}

	public void setBlh(String blh) {
		this.blh = blh;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

	public Integer getJydh() {
		return this.jydh;
	}

	public void setJydh(Integer jydh) {
		this.jydh = jydh;
	}

	public Integer getJygroupid() {
		return this.jygroupid;
	}

	public void setJygroupid(Integer jygroupid) {
		this.jygroupid = jygroupid;
	}

	public String getJynr() {
		return this.jynr;
	}

	public void setJynr(String jynr) {
		this.jynr = jynr;
	}

	public Integer getJyrq() {
		return this.jyrq;
	}

	public void setJyrq(Integer jyrq) {
		this.jyrq = jyrq;
	}

	public Integer getJysj() {
		return this.jysj;
	}

	public void setJysj(Integer jysj) {
		this.jysj = jysj;
	}

	public Integer getShrq() {
		return this.shrq;
	}

	public void setShrq(Integer shrq) {
		this.shrq = shrq;
	}

	public Integer getShsj() {
		return this.shsj;
	}

	public void setShsj(Integer shsj) {
		this.shsj = shsj;
	}

	public String getSqks() {
		return this.sqks;
	}

	public void setSqks(String sqks) {
		this.sqks = sqks;
	}

	public Integer getSqrq() {
		return this.sqrq;
	}

	public void setSqrq(Integer sqrq) {
		this.sqrq = sqrq;
	}

	public String getSqys() {
		return this.sqys;
	}

	public void setSqys(String sqys) {
		this.sqys = sqys;
	}

	public String getTm() {
		return this.tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public Integer getYbh() {
		return this.ybh;
	}

	public void setYbh(Integer ybh) {
		this.ybh = ybh;
	}

	public String getYqbm() {
		return this.yqbm;
	}

	public void setYqbm(String yqbm) {
		this.yqbm = yqbm;
	}

	public Integer getYzzh() {
		return this.yzzh;
	}

	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}

}