package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BH2 database table.
 * 
 */
@Entity
@Table(name="ZY_BH2", schema="HIS")
@NamedQuery(name="ZyBh2.findAll", query="SELECT z FROM ZyBh2 z")
public class ZyBh2 implements Serializable {
	private static final long serialVersionUID = 8480652882447931244L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BYLYZG")
	private String bylyzg;

	@Column(name="CZRY")
	private String czry;

	@Column(name="DBRY")
	private String dbry;

	@Column(name="JGYS")
	private String jgys;

	@Column(name="JSYS")
	private String jsys;

	@Column(name="JSYSBM")
	private String jsysbm;

	@Column(name="LYYS")
	private String lyys;

	@Column(name="LYYY")
	private Short lyyy;

	@Column(name="ORIGCYRQ")
	private Integer origcyrq;

	@Column(name="XYEYJ")
	private BigDecimal xyeyj;

	public ZyBh2() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public String getBylyzg() {
		return this.bylyzg;
	}

	public void setBylyzg(String bylyzg) {
		this.bylyzg = bylyzg;
	}

	public String getCzry() {
		return this.czry;
	}

	public void setCzry(String czry) {
		this.czry = czry;
	}

	public String getDbry() {
		return this.dbry;
	}

	public void setDbry(String dbry) {
		this.dbry = dbry;
	}

	public String getJgys() {
		return this.jgys;
	}

	public void setJgys(String jgys) {
		this.jgys = jgys;
	}

	public String getJsys() {
		return this.jsys;
	}

	public void setJsys(String jsys) {
		this.jsys = jsys;
	}

	public String getJsysbm() {
		return this.jsysbm;
	}

	public void setJsysbm(String jsysbm) {
		this.jsysbm = jsysbm;
	}

	public String getLyys() {
		return this.lyys;
	}

	public void setLyys(String lyys) {
		this.lyys = lyys;
	}

	public Short getLyyy() {
		return this.lyyy;
	}

	public void setLyyy(short lyyy) {
		this.lyyy = lyyy;
	}

	public Integer getOrigcyrq() {
		return this.origcyrq;
	}

	public void setOrigcyrq(int origcyrq) {
		this.origcyrq = origcyrq;
	}

	public BigDecimal getXyeyj() {
		return this.xyeyj;
	}

	public void setXyeyj(BigDecimal xyeyj) {
		this.xyeyj = xyeyj;
	}

}