package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ZY_BHADD database table.
 * 
 */
@Entity
@Table(name="ZY_BHADD", schema="HIS")
@NamedQuery(name="ZyBhadd.findAll", query="SELECT z FROM ZyBhadd z")
public class ZyBhadd implements Serializable {
	private static final long serialVersionUID = -8790318270898046757L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="DQBM")
	private String dqbm;

	@Column(name="JZDW")
	private String jzdw;

	@Column(name="YBDJH")
	private String ybdjh;

	@Column(name="YBLB")
	private String yblb;

	@Column(name="YBLSH")
	private String yblsh;

	public ZyBhadd() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public String getDqbm() {
		return this.dqbm;
	}

	public void setDqbm(String dqbm) {
		this.dqbm = dqbm;
	}

	public String getJzdw() {
		return this.jzdw;
	}

	public void setJzdw(String jzdw) {
		this.jzdw = jzdw;
	}

	public String getYbdjh() {
		return this.ybdjh;
	}

	public void setYbdjh(String ybdjh) {
		this.ybdjh = ybdjh;
	}

	public String getYblb() {
		return this.yblb;
	}

	public void setYblb(String yblb) {
		this.yblb = yblb;
	}

	public String getYblsh() {
		return this.yblsh;
	}

	public void setYblsh(String yblsh) {
		this.yblsh = yblsh;
	}

}