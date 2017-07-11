package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ZY_BH4 database table.
 * 
 */
@Entity
@Table(name="ZY_BH4", schema="HIS")
@NamedQuery(name="ZyBh4.findAll", query="SELECT z FROM ZyBh4 z")
public class ZyBh4 implements Serializable {
	private static final long serialVersionUID = -7757713121067863642L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BLFILE")
	private String blfile;

	@Column(name="BLID")
	private Integer blid;

	@Column(name="DLS")
	private Short dls;

	@Column(name="FLAG")
	private Short flag;

	@Column(name="KSBM")
	private String ksbm;

	@Column(name="LX")
	private Short lx;

	@Column(name="LXBM")
	private String lxbm;

	@Column(name="MC")
	private String mc;

	@Lob
	@Column(name="NR")
	private byte[] nr;

	@Column(name="PRNLINE")
	private Integer prnline;

	@Column(name="PRNRQ")
	private Integer prnrq;

	@Column(name="PRNSJ")
	private Integer prnsj;

	@Column(name="QMS")
	private Short qms;

	@Column(name="ROOTLXBM")
	private String rootlxbm;

	@Column(name="RQ")
	private Integer rq;

	@Column(name="SJ")
	private Integer sj;

	@Column(name="XH")
	private Short xh;

	@Column(name="YS")
	private String ys;

	public ZyBh4() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public String getBlfile() {
		return this.blfile;
	}

	public void setBlfile(String blfile) {
		this.blfile = blfile;
	}

	public Integer getBlid() {
		return this.blid;
	}

	public void setBlid(int blid) {
		this.blid = blid;
	}

	public Short getDls() {
		return this.dls;
	}

	public void setDls(short dls) {
		this.dls = dls;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(short flag) {
		this.flag = flag;
	}

	public String getKsbm() {
		return this.ksbm;
	}

	public void setKsbm(String ksbm) {
		this.ksbm = ksbm;
	}

	public Short getLx() {
		return this.lx;
	}

	public void setLx(short lx) {
		this.lx = lx;
	}

	public String getLxbm() {
		return this.lxbm;
	}

	public void setLxbm(String lxbm) {
		this.lxbm = lxbm;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public byte[] getNr() {
		return this.nr;
	}

	public void setNr(byte[] nr) {
		this.nr = nr;
	}

	public Integer getPrnline() {
		return this.prnline;
	}

	public void setPrnline(int prnline) {
		this.prnline = prnline;
	}

	public Integer getPrnrq() {
		return this.prnrq;
	}

	public void setPrnrq(int prnrq) {
		this.prnrq = prnrq;
	}

	public Integer getPrnsj() {
		return this.prnsj;
	}

	public void setPrnsj(int prnsj) {
		this.prnsj = prnsj;
	}

	public Short getQms() {
		return this.qms;
	}

	public void setQms(short qms) {
		this.qms = qms;
	}

	public String getRootlxbm() {
		return this.rootlxbm;
	}

	public void setRootlxbm(String rootlxbm) {
		this.rootlxbm = rootlxbm;
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

	public Short getXh() {
		return this.xh;
	}

	public void setXh(short xh) {
		this.xh = xh;
	}

	public String getYs() {
		return this.ys;
	}

	public void setYs(String ys) {
		this.ys = ys;
	}

}