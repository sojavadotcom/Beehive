package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ZY_BH10 database table.
 * 
 */
@Entity
@Table(name="ZY_BH10", schema="HIS")
@NamedQuery(name="ZyBh10.findAll", query="SELECT z FROM ZyBh10 z")
public class ZyBh10 implements Serializable {
	private static final long serialVersionUID = -5049210423648678838L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="LX")
	private Integer lx;

	@Column(name="[ROW]")
	private Integer row;

	@Column(name="RQ")
	private Integer rq;

	public ZyBh10() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public Integer getLx() {
		return this.lx;
	}

	public void setLx(int lx) {
		this.lx = lx;
	}

	public Integer getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Integer getRq() {
		return this.rq;
	}

	public void setRq(int rq) {
		this.rq = rq;
	}

}