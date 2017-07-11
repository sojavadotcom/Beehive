package cn.jxszyyy.anyihis.dic.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ZD_RY database table.
 * 
 */
@Entity
@Table(name="ZD_RY", schema="HIS")
@NamedQuery(name="ZdRy.findAll", query="SELECT z FROM ZdRy z")
public class ZdRy implements Serializable {
	private static final long serialVersionUID = -4582797096365259023L;

	@Id
	@Column(name="BM")
	private String bm;

	@Column(name="BXH")
	private String bxh;

	@Column(name="CABM")
	private String cabm;

	private Short CAFlag;

	@Column(name="DH")
	private String dh;

	@Column(name="FZHSXZ")
	private String fzhsxz;

	@Column(name="GHFLAG")
	private Short ghflag;

	@Column(name="GROUPNO")
	private Integer groupno;

	@Column(name="HSXZ")
	private String hsxz;

	@Column(name="KSBM")
	private String ksbm;

	@Column(name="LB")
	private String lb;

	@Column(name="LOGINKS")
	private String loginks;

	@Column(name="LXDH")
	private String lxdh;

	@Column(name="MC")
	private String mc;

	@Column(name="MZHSXZ")
	private String mzhsxz;

	@Column(name="MZKS")
	private String mzks;

	@Column(name="ONDUTYKS")
	private String ondutyks;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="RANK")
	private String rank;

	private String ylxz;

	@Column(name="YPJB")
	private Integer ypjb;

	@Column(name="YSJB")
	private Short ysjb;

	@Column(name="ZC")
	private String zc;

	@Column(name="ZKKS")
	private String zkks;

	@Column(name="ZW")
	private String zw;

	public ZdRy() {
	}

	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getBxh() {
		return this.bxh;
	}

	public void setBxh(String bxh) {
		this.bxh = bxh;
	}

	public String getCabm() {
		return this.cabm;
	}

	public void setCabm(String cabm) {
		this.cabm = cabm;
	}

	public Short getCAFlag() {
		return this.CAFlag;
	}

	public void setCAFlag(Short CAFlag) {
		this.CAFlag = CAFlag;
	}

	public String getDh() {
		return this.dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getFzhsxz() {
		return this.fzhsxz;
	}

	public void setFzhsxz(String fzhsxz) {
		this.fzhsxz = fzhsxz;
	}

	public Short getGhflag() {
		return this.ghflag;
	}

	public void setGhflag(Short ghflag) {
		this.ghflag = ghflag;
	}

	public Integer getGroupno() {
		return this.groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	public String getHsxz() {
		return this.hsxz;
	}

	public void setHsxz(String hsxz) {
		this.hsxz = hsxz;
	}

	public String getKsbm() {
		return this.ksbm;
	}

	public void setKsbm(String ksbm) {
		this.ksbm = ksbm;
	}

	public String getLb() {
		return this.lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getLoginks() {
		return this.loginks;
	}

	public void setLoginks(String loginks) {
		this.loginks = loginks;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getMzhsxz() {
		return this.mzhsxz;
	}

	public void setMzhsxz(String mzhsxz) {
		this.mzhsxz = mzhsxz;
	}

	public String getMzks() {
		return this.mzks;
	}

	public void setMzks(String mzks) {
		this.mzks = mzks;
	}

	public String getOndutyks() {
		return this.ondutyks;
	}

	public void setOndutyks(String ondutyks) {
		this.ondutyks = ondutyks;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getYlxz() {
		return this.ylxz;
	}

	public void setYlxz(String ylxz) {
		this.ylxz = ylxz;
	}

	public Integer getYpjb() {
		return this.ypjb;
	}

	public void setYpjb(int ypjb) {
		this.ypjb = ypjb;
	}

	public Short getYsjb() {
		return this.ysjb;
	}

	public void setYsjb(Short ysjb) {
		this.ysjb = ysjb;
	}

	public String getZc() {
		return this.zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getZkks() {
		return this.zkks;
	}

	public void setZkks(String zkks) {
		this.zkks = zkks;
	}

	public String getZw() {
		return this.zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

}