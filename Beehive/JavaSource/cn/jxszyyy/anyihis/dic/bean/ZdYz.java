package cn.jxszyyy.anyihis.dic.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ZD_YZ database table.
 * 
 */
@Entity
@Table(name="ZD_YZ", schema="HIS")
@NamedQuery(name="ZdYz.findAll", query="SELECT z FROM ZdYz z")
public class ZdYz implements Serializable {
	private static final long serialVersionUID = 7128913948104577159L;

	@Id
	@Column(name="BM")
	private String bm;

	@Column(name="BZ")
	private String bz;

	@Column(name="BZDW")
	private String bzdw;

	@Column(name="CFYLDW")
	private String cfyldw;

	@Column(name="CFYLDWLX")
	private Short cfyldwlx;

	@Column(name="CFZLDWLX")
	private Short cfzldwlx;

	@Column(name="CLLB")
	private Short cllb;

	@Column(name="DH")
	private String dh;

	@Column(name="DJ")
	private BigDecimal dj;

	@Column(name="DJ1")
	private BigDecimal dj1;

	@Column(name="F_LLRYP")
	private Short fLlryp;

	@Column(name="FYLB")
	private Short fylb;

	@Column(name="GBBM")
	private String gbbm;

	@Column(name="GG")
	private String gg;

	@Column(name="GGBZ")
	private String ggbz;

	@Column(name="JDH")
	private String jdh;

	@Column(name="JL")
	private BigDecimal jl;

	@Column(name="JLDW")
	private String jldw;

	@Column(name="JX")
	private Short jx;

	@Column(name="LB")
	private Integer lb;

	@Column(name="LCFL")
	private String lcfl;

	@Column(name="LX")
	private Short lx;

	@Column(name="MC")
	private String mc;

	@Column(name="PRINTMC")
	private String printmc;

	@Column(name="[STATE]")
	private Short state;

	@Column(name="XMBZ")
	private String xmbz;

	@Column(name="YBBZ")
	private String ybbz;

	@Column(name="YJXMBM")
	private String yjxmbm;

	@Column(name="YJXMMC")
	private String yjxmmc;

	@Column(name="YLDW")
	private String yldw;

	@Column(name="ZXBZ")
	private Short zxbz;

	@Column(name="ZYZLQZFS")
	private Short zyzlqzfs;

	public ZdYz() {
	}

	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBzdw() {
		return this.bzdw;
	}

	public void setBzdw(String bzdw) {
		this.bzdw = bzdw;
	}

	public String getCfyldw() {
		return this.cfyldw;
	}

	public void setCfyldw(String cfyldw) {
		this.cfyldw = cfyldw;
	}

	public Short getCfyldwlx() {
		return this.cfyldwlx;
	}

	public void setCfyldwlx(short cfyldwlx) {
		this.cfyldwlx = cfyldwlx;
	}

	public Short getCfzldwlx() {
		return this.cfzldwlx;
	}

	public void setCfzldwlx(short cfzldwlx) {
		this.cfzldwlx = cfzldwlx;
	}

	public Short getCllb() {
		return this.cllb;
	}

	public void setCllb(short cllb) {
		this.cllb = cllb;
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

	public Short getFLlryp() {
		return this.fLlryp;
	}

	public void setFLlryp(short fLlryp) {
		this.fLlryp = fLlryp;
	}

	public Short getFylb() {
		return this.fylb;
	}

	public void setFylb(short fylb) {
		this.fylb = fylb;
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

	public String getGgbz() {
		return this.ggbz;
	}

	public void setGgbz(String ggbz) {
		this.ggbz = ggbz;
	}

	public String getJdh() {
		return this.jdh;
	}

	public void setJdh(String jdh) {
		this.jdh = jdh;
	}

	public BigDecimal getJl() {
		return this.jl;
	}

	public void setJl(BigDecimal jl) {
		this.jl = jl;
	}

	public String getJldw() {
		return this.jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public Short getJx() {
		return this.jx;
	}

	public void setJx(short jx) {
		this.jx = jx;
	}

	public Integer getLb() {
		return this.lb;
	}

	public void setLb(int lb) {
		this.lb = lb;
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

	public String getYldw() {
		return this.yldw;
	}

	public void setYldw(String yldw) {
		this.yldw = yldw;
	}

	public Short getZxbz() {
		return this.zxbz;
	}

	public void setZxbz(short zxbz) {
		this.zxbz = zxbz;
	}

	public Short getZyzlqzfs() {
		return this.zyzlqzfs;
	}

	public void setZyzlqzfs(short zyzlqzfs) {
		this.zyzlqzfs = zyzlqzfs;
	}

}