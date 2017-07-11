package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BH8 database table.
 * 
 */
@Entity
@Table(name="ZY_BH8", schema="HIS")
@NamedQuery(name="ZyBh8.findAll", query="SELECT z FROM ZyBh8 z")
public class ZyBh8 implements Serializable {
	private static final long serialVersionUID = 1349465010406305290L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BLDJ")
	private String bldj;

	@Column(name="BLPF")
	private BigDecimal blpf;

	@Column(name="RQ")
	private Integer rq;

	@Column(name="SJ")
	private Integer sj;

	public ZyBh8() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public String getBldj() {
		return this.bldj;
	}

	public void setBldj(String bldj) {
		this.bldj = bldj;
	}

	public BigDecimal getBlpf() {
		return this.blpf;
	}

	public void setBlpf(BigDecimal blpf) {
		this.blpf = blpf;
	}

	public Integer getRq() {
		return this.rq;
	}

	public void setRq(int rq) {
		this.rq = rq;
	}

	public Integer getSj() {
		return this.sj;
	}

	public void setSj(int sj) {
		this.sj = sj;
	}

}