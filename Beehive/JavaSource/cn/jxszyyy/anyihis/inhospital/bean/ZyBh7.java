package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ZY_BH7 database table.
 * 
 */
@Entity
@Table(name="ZY_BH7", schema="HIS")
@NamedQuery(name="ZyBh7.findAll", query="SELECT z FROM ZyBh7 z")
public class ZyBh7 implements Serializable {
	private static final long serialVersionUID = -7946675765266637376L;

	@Id
	@Column(name="AUTOINC")
	private Integer autoinc;

	@Column(name="BHID")
	private Integer bhid;

	@Column(name="FLAG")
	private Short flag;

	@Column(name="KSRQ")
	private Integer ksrq;

	@Column(name="KSSJ")
	private Integer kssj;

	@Column(name="MZFF1")
	private String mzff1;

	@Column(name="MZFF2")
	private String mzff2;

	@Column(name="MZFF3")
	private String mzff3;

	@Column(name="MZYS1")
	private String mzys1;

	@Column(name="MZYS2")
	private String mzys2;

	@Column(name="MZYS3")
	private String mzys3;

	@Column(name="QK1")
	private String qk1;

	@Column(name="QK2")
	private String qk2;

	@Column(name="SSBM")
	private String ssbm;

	@Column(name="SSDH")
	private Integer ssdh;

	@Column(name="SSICD")
	private String ssicd;

	@Column(name="SSJB")
	private String ssjb;

	@Column(name="SSMC")
	private String ssmc;

	@Column(name="SSYS")
	private String ssys;

	@Column(name="ZS1")
	private String zs1;

	@Column(name="ZS2")
	private String zs2;

	public ZyBh7() {
	}

	public Integer getAutoinc() {
		return this.autoinc;
	}

	public void setAutoinc(int autoinc) {
		this.autoinc = autoinc;
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(short flag) {
		this.flag = flag;
	}

	public Integer getKsrq() {
		return this.ksrq;
	}

	public void setKsrq(int ksrq) {
		this.ksrq = ksrq;
	}

	public Integer getKssj() {
		return this.kssj;
	}

	public void setKssj(int kssj) {
		this.kssj = kssj;
	}

	public String getMzff1() {
		return this.mzff1;
	}

	public void setMzff1(String mzff1) {
		this.mzff1 = mzff1;
	}

	public String getMzff2() {
		return this.mzff2;
	}

	public void setMzff2(String mzff2) {
		this.mzff2 = mzff2;
	}

	public String getMzff3() {
		return this.mzff3;
	}

	public void setMzff3(String mzff3) {
		this.mzff3 = mzff3;
	}

	public String getMzys1() {
		return this.mzys1;
	}

	public void setMzys1(String mzys1) {
		this.mzys1 = mzys1;
	}

	public String getMzys2() {
		return this.mzys2;
	}

	public void setMzys2(String mzys2) {
		this.mzys2 = mzys2;
	}

	public String getMzys3() {
		return this.mzys3;
	}

	public void setMzys3(String mzys3) {
		this.mzys3 = mzys3;
	}

	public String getQk1() {
		return this.qk1;
	}

	public void setQk1(String qk1) {
		this.qk1 = qk1;
	}

	public String getQk2() {
		return this.qk2;
	}

	public void setQk2(String qk2) {
		this.qk2 = qk2;
	}

	public String getSsbm() {
		return this.ssbm;
	}

	public void setSsbm(String ssbm) {
		this.ssbm = ssbm;
	}

	public Integer getSsdh() {
		return this.ssdh;
	}

	public void setSsdh(int ssdh) {
		this.ssdh = ssdh;
	}

	public String getSsicd() {
		return this.ssicd;
	}

	public void setSsicd(String ssicd) {
		this.ssicd = ssicd;
	}

	public String getSsjb() {
		return this.ssjb;
	}

	public void setSsjb(String ssjb) {
		this.ssjb = ssjb;
	}

	public String getSsmc() {
		return this.ssmc;
	}

	public void setSsmc(String ssmc) {
		this.ssmc = ssmc;
	}

	public String getSsys() {
		return this.ssys;
	}

	public void setSsys(String ssys) {
		this.ssys = ssys;
	}

	public String getZs1() {
		return this.zs1;
	}

	public void setZs1(String zs1) {
		this.zs1 = zs1;
	}

	public String getZs2() {
		return this.zs2;
	}

	public void setZs2(String zs2) {
		this.zs2 = zs2;
	}

}