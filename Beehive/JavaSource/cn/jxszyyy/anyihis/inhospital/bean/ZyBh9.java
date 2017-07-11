package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BH9 database table.
 * 
 */
@Entity
@Table(name="ZY_BH9", schema="HIS")
@NamedQuery(name="ZyBh9.findAll", query="SELECT z FROM ZyBh9 z")
public class ZyBh9 implements Serializable {
	private static final long serialVersionUID = 6833278166317951237L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="FLMC")
	private String flmc;

	@Column(name="FLXH")
	private Integer flxh;

	@Column(name="FY")
	private BigDecimal fy;

	public ZyBh9() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public String getFlmc() {
		return this.flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	public Integer getFlxh() {
		return this.flxh;
	}

	public void setFlxh(int flxh) {
		this.flxh = flxh;
	}

	public BigDecimal getFy() {
		return this.fy;
	}

	public void setFy(BigDecimal fy) {
		this.fy = fy;
	}

}