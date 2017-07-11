package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class TestApplyOfInHospital implements Serializable {
	private static final long serialVersionUID = 3580024859466034311L;

	private Integer bhid;
	private Integer zyh;
	private Integer jydh;
	private String xm;
	private String xb;
	private String ks;
	private String ys;
	private String jyxm;
	private String sqrq;
	private String jldw;
	private BigDecimal dj;
	private Integer sl;
	private BigDecimal je;

	public Integer getBhid() {
		return bhid;
	}
	public void setBhid(Integer bhid) {
		this.bhid = bhid;
	}
	public Integer getZyh() {
		return zyh;
	}
	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	public Integer getJydh() {
		return jydh;
	}
	public void setJydh(Integer jydh) {
		this.jydh = jydh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getKs() {
		return ks;
	}
	public void setKs(String ks) {
		this.ks = ks;
	}
	public String getYs() {
		return ys;
	}
	public void setYs(String ys) {
		this.ys = ys;
	}
	public String getJyxm() {
		return jyxm;
	}
	public void setJyxm(String jyxm) {
		this.jyxm = jyxm;
	}
	public String getSqrq() {
		return sqrq;
	}
	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}
	public String getJldw() {
		return jldw;
	}
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public BigDecimal getJe() {
		return je;
	}
	public void setJe(BigDecimal je) {
		this.je = je;
	}
}
