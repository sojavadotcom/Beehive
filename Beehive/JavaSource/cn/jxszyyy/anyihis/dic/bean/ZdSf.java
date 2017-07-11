package cn.jxszyyy.anyihis.dic.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ZD_SF database table.
 * 
 */
@Entity
@Table(name="ZD_SF", schema="HIS")
@NamedQuery(name="ZdSf.findAll", query="SELECT z FROM ZdSf z")
public class ZdSf implements Serializable {
	private static final long serialVersionUID = -8284302980571752031L;

	@Id
	@Column(name="BM")
	private String bm;

	@Column(name="CLLB")
	private Short cllb;

	@Column(name="Color")
	private String color;

	@Column(name="DH")
	private String dh;

	@Column(name="DJ")
	private BigDecimal dj;

	@Column(name="DJ1")
	private BigDecimal dj1;

	@Column(name="DW")
	private String dw;

	@Column(name="GBBM")
	private String gbbm;

	@Column(name="GG")
	private String gg;

	@Column(name="JDH")
	private String jdh;

	@Column(name="JYBGMB")
	private Integer jybgmb;

	@Column(name="JYXZ")
	private String jyxz;

	@Column(name="LCFL")
	private String lcfl;

	@Column(name="LX")
	private Short lx;

	@Column(name="MC")
	private String mc;

	@Column(name="MZFYLB")
	private Short mzfylb;

	@Column(name="PRINTMC")
	private String printmc;

	@Column(name="[STATE]")
	private Short state;

	@Column(name="SYFW")
	private Short syfw;

	@Column(name="XMBZ")
	private String xmbz;

	@Column(name="YBBZ")
	private String ybbz;

	@Column(name="YJXMBM")
	private String yjxmbm;

	@Column(name="YJXMMC")
	private String yjxmmc;

	@Column(name="ZFBL")
	private BigDecimal zfbl;

	@Column(name="ZXBZ")
	private Short zxbz;

	@Column(name="ZXKS")
	private String zxks;

	@Column(name="ZYFYLB")
	private Short zyfylb;

	@Column(name="ZYTJLB")
	private Integer zytjlb;

	public ZdSf() {
	}

	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public Short getCllb() {
		return this.cllb;
	}

	public void setCllb(short cllb) {
		this.cllb = cllb;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDh() {
		return this.dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public BigDecimal getDj() {
		return this.dj;
	}

	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}

	public BigDecimal getDj1() {
		return this.dj1;
	}

	public void setDj1(BigDecimal dj1) {
		this.dj1 = dj1;
	}

	public String getDw() {
		return this.dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getGbbm() {
		return this.gbbm;
	}

	public void setGbbm(String gbbm) {
		this.gbbm = gbbm;
	}

	public String getGg() {
		return this.gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public String getJdh() {
		return this.jdh;
	}

	public void setJdh(String jdh) {
		this.jdh = jdh;
	}

	public Integer getJybgmb() {
		return this.jybgmb;
	}

	public void setJybgmb(int jybgmb) {
		this.jybgmb = jybgmb;
	}

	public String getJyxz() {
		return this.jyxz;
	}

	public void setJyxz(String jyxz) {
		this.jyxz = jyxz;
	}

	public String getLcfl() {
		return this.lcfl;
	}

	public void setLcfl(String lcfl) {
		this.lcfl = lcfl;
	}

	public Short getLx() {
		return this.lx;
	}

	public void setLx(short lx) {
		this.lx = lx;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public Short getMzfylb() {
		return this.mzfylb;
	}

	public void setMzfylb(short mzfylb) {
		this.mzfylb = mzfylb;
	}

	public String getPrintmc() {
		return this.printmc;
	}

	public void setPrintmc(String printmc) {
		this.printmc = printmc;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Short getSyfw() {
		return this.syfw;
	}

	public void setSyfw(short syfw) {
		this.syfw = syfw;
	}

	public String getXmbz() {
		return this.xmbz;
	}

	public void setXmbz(String xmbz) {
		this.xmbz = xmbz;
	}

	public String getYbbz() {
		return this.ybbz;
	}

	public void setYbbz(String ybbz) {
		this.ybbz = ybbz;
	}

	public String getYjxmbm() {
		return this.yjxmbm;
	}

	public void setYjxmbm(String yjxmbm) {
		this.yjxmbm = yjxmbm;
	}

	public String getYjxmmc() {
		return this.yjxmmc;
	}

	public void setYjxmmc(String yjxmmc) {
		this.yjxmmc = yjxmmc;
	}

	public BigDecimal getZfbl() {
		return this.zfbl;
	}

	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}

	public Short getZxbz() {
		return this.zxbz;
	}

	public void setZxbz(short zxbz) {
		this.zxbz = zxbz;
	}

	public String getZxks() {
		return this.zxks;
	}

	public void setZxks(String zxks) {
		this.zxks = zxks;
	}

	public Short getZyfylb() {
		return this.zyfylb;
	}

	public void setZyfylb(short zyfylb) {
		this.zyfylb = zyfylb;
	}

	public Integer getZytjlb() {
		return this.zytjlb;
	}

	public void setZytjlb(int zytjlb) {
		this.zytjlb = zytjlb;
	}

}