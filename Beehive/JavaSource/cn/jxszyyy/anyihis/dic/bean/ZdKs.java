package cn.jxszyyy.anyihis.dic.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ZD_KS database table.
 * 
 */
@Entity
@Table(name="ZD_KS", schema="HIS")
@NamedQuery(name="ZdKs.findAll", query="SELECT z FROM ZdKs z")
public class ZdKs implements Serializable {
	private static final long serialVersionUID = 3246286408908680092L;

	@Id
	@Column(name="BM")
	private String bm;

	@Column(name="BMID")
	private Short bmid;

	@Column(name="BZCWS")
	private Short bzcws;

	@Column(name="DH")
	private String dh;

	@Column(name="EHRBM")
	private String ehrbm;

	@Column(name="EHRMC")
	private String ehrmc;

	@Column(name="FLAG")
	private Short flag;

	@Column(name="ISUSE")
	private Short isuse;

	@Column(name="KSSM")
	private String kssm;

	@Column(name="LB")
	private String lb;

	@Column(name="MC")
	private String mc;

	@Column(name="SJBM")
	private String sjbm;

	@Column(name="[STATE]")
	private Integer state;

	@Column(name="TJKSBM")
	private String tjksbm;

	@Column(name="TJKSDH")
	private String tjksdh;

	@Column(name="TJKSMC")
	private String tjksmc;

	@Column(name="XTMK")
	private Integer xtmk;

	@Column(name="YBBM")
	private String ybbm;

	@Column(name="YFDM")
	private String yfdm;

	@Column(name="ZKCWS")
	private Short zkcws;

	public ZdKs() {}

	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public Short getBmid() {
		return this.bmid;
	}

	public void setBmid(Short bmid) {
		this.bmid = bmid;
	}

	public Short getBzcws() {
		return this.bzcws;
	}

	public void setBzcws(Short bzcws) {
		this.bzcws = bzcws;
	}

	public String getDh() {
		return this.dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getEhrbm() {
		return this.ehrbm;
	}

	public void setEhrbm(String ehrbm) {
		this.ehrbm = ehrbm;
	}

	public String getEhrmc() {
		return this.ehrmc;
	}

	public void setEhrmc(String ehrmc) {
		this.ehrmc = ehrmc;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

	public Short getIsuse() {
		return this.isuse;
	}

	public void setIsuse(Short isuse) {
		this.isuse = isuse;
	}

	public String getKssm() {
		return this.kssm;
	}

	public void setKssm(String kssm) {
		this.kssm = kssm;
	}

	public String getLb() {
		return this.lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSjbm() {
		return this.sjbm;
	}

	public void setSjbm(String sjbm) {
		this.sjbm = sjbm;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTjksbm() {
		return this.tjksbm;
	}

	public void setTjksbm(String tjksbm) {
		this.tjksbm = tjksbm;
	}

	public String getTjksdh() {
		return this.tjksdh;
	}

	public void setTjksdh(String tjksdh) {
		this.tjksdh = tjksdh;
	}

	public String getTjksmc() {
		return this.tjksmc;
	}

	public void setTjksmc(String tjksmc) {
		this.tjksmc = tjksmc;
	}

	public Integer getXtmk() {
		return this.xtmk;
	}

	public void setXtmk(int xtmk) {
		this.xtmk = xtmk;
	}

	public String getYbbm() {
		return this.ybbm;
	}

	public void setYbbm(String ybbm) {
		this.ybbm = ybbm;
	}

	public String getYfdm() {
		return this.yfdm;
	}

	public void setYfdm(String yfdm) {
		this.yfdm = yfdm;
	}

	public Short getZkcws() {
		return this.zkcws;
	}

	public void setZkcws(Short zkcws) {
		this.zkcws = zkcws;
	}

}