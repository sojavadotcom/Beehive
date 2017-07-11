package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BH6 database table.
 * 
 */
@Entity
@Table(name="ZY_BH6", schema="HIS")
@NamedQuery(name="ZyBh6.findAll", query="SELECT z FROM ZyBh6 z")
public class ZyBh6 implements Serializable {
	private static final long serialVersionUID = 3721791166393971673L;

	@Column(name="AUTOINC")
	private Integer autoinc;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BM")
	private String bm;

	@Column(name="DW")
	private String dw;

	@Column(name="JL")
	private BigDecimal jl;

	@Column(name="JSRQ")
	private Integer jsrq;

	@Column(name="KSRQ")
	private Integer ksrq;

	@Column(name="LC")
	private Short lc;

	@Column(name="LX")
	private String lx;

	@Column(name="MC")
	private String mc;

	@Column(name="RQ")
	private Integer rq;

	public ZyBh6() {
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

	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getDw() {
		return this.dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public BigDecimal getJl() {
		return this.jl;
	}

	public void setJl(BigDecimal jl) {
		this.jl = jl;
	}

	public Integer getJsrq() {
		return this.jsrq;
	}

	public void setJsrq(int jsrq) {
		this.jsrq = jsrq;
	}

	public Integer getKsrq() {
		return this.ksrq;
	}

	public void setKsrq(int ksrq) {
		this.ksrq = ksrq;
	}

	public Short getLc() {
		return this.lc;
	}

	public void setLc(short lc) {
		this.lc = lc;
	}

	public String getLx() {
		return this.lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public Integer getRq() {
		return this.rq;
	}

	public void setRq(int rq) {
		this.rq = rq;
	}

}