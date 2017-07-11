package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BHJFYZ201611 database table.
 * 
 */
@Entity
@Table(name="ZY_BHJFYZ201611", schema="HIS")
@NamedQuery(name="ZyBhjfyz201611.findAll", query="SELECT z FROM ZyBhjfyz201611 z")
public class ZyBhjfyz201611 extends ZyBhjfyz implements Serializable {
	private static final long serialVersionUID = -2652403560672066666L;

	@Id
	@Column(name="AUTOINC")
	private Integer autoinc;

	@Column(name="BHID")
	private Integer bhid;

	@Column(name="CBDJ")
	private BigDecimal cbdj;

	@Column(name="DJ")
	private BigDecimal dj;

	@Column(name="DW")
	private String dw;

	@Column(name="FLAG")
	private Integer flag;

	@Column(name="FY")
	private Integer fy;

	@Column(name="FYLB")
	private Integer fylb;

	@Column(name="HSXZ")
	private String hsxz;

	@Column(name="JE")
	private BigDecimal je;

	@Column(name="KSBM")
	private String ksbm;

	@Column(name="LY")
	private Integer ly;

	@Column(name="LYKS")
	private String lyks;

	@Column(name="LYYS")
	private String lyys;

	@Column(name="NIAN")
	private Integer nian;

	@Column(name="RQ")
	private Integer rq;

	@Column(name="SFID")
	private Integer sfid;

	@Column(name="SJ")
	private Integer sj;

	@Column(name="YBBL")
	private BigDecimal ybbl;

	@Column(name="YBBZ")
	private String ybbz;

	@Column(name="YBFLAG")
	private Integer ybflag;

	@Column(name="YBSTATE")
	private Integer ybstate;

	@Column(name="YDJ")
	private BigDecimal ydj;

	@Column(name="YSBM")
	private String ysbm;

	@Column(name="YUE")
	private Integer yue;

	@Column(name="YZBM")
	private String yzbm;

	@Column(name="YZGID")
	private Integer yzgid;

	@Column(name="YZID")
	private Integer yzid;

	@Column(name="YZLX")
	private Integer yzlx;

	@Column(name="YZMC")
	private String yzmc;

	@Column(name="YZRQ")
	private Integer yzrq;

	@Column(name="ZHXMBM")
	private String zhxmbm;

	@Column(name="ZL")
	private Integer zl;

	@Column(name="ZXKS")
	private String zxks;

	public ZyBhjfyz201611() {
	}

	public Integer getAutoinc() {
		return this.autoinc;
	}

	public void setAutoinc(Integer autoinc) {
		this.autoinc = autoinc;
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(Integer bhid) {
		this.bhid = bhid;
	}

	public BigDecimal getCbdj() {
		return this.cbdj;
	}

	public void setCbdj(BigDecimal cbdj) {
		this.cbdj = cbdj;
	}

	public BigDecimal getDj() {
		return this.dj;
	}

	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}

	public String getDw() {
		return this.dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getFy() {
		return this.fy;
	}

	public void setFy(Integer fy) {
		this.fy = fy;
	}

	public Integer getFylb() {
		return this.fylb;
	}

	public void setFylb(Integer fylb) {
		this.fylb = fylb;
	}

	public String getHsxz() {
		return this.hsxz;
	}

	public void setHsxz(String hsxz) {
		this.hsxz = hsxz;
	}

	public BigDecimal getJe() {
		return this.je;
	}

	public void setJe(BigDecimal je) {
		this.je = je;
	}

	public String getKsbm() {
		return this.ksbm;
	}

	public void setKsbm(String ksbm) {
		this.ksbm = ksbm;
	}

	public Integer getLy() {
		return this.ly;
	}

	public void setLy(Integer ly) {
		this.ly = ly;
	}

	public String getLyks() {
		return this.lyks;
	}

	public void setLyks(String lyks) {
		this.lyks = lyks;
	}

	public String getLyys() {
		return this.lyys;
	}

	public void setLyys(String lyys) {
		this.lyys = lyys;
	}

	public Integer getNian() {
		return this.nian;
	}

	public void setNian(Integer nian) {
		this.nian = nian;
	}

	public Integer getRq() {
		return this.rq;
	}

	public void setRq(Integer rq) {
		this.rq = rq;
	}

	public Integer getSfid() {
		return this.sfid;
	}

	public void setSfid(Integer sfid) {
		this.sfid = sfid;
	}

	public Integer getSj() {
		return this.sj;
	}

	public void setSj(Integer sj) {
		this.sj = sj;
	}

	public BigDecimal getYbbl() {
		return this.ybbl;
	}

	public void setYbbl(BigDecimal ybbl) {
		this.ybbl = ybbl;
	}

	public String getYbbz() {
		return this.ybbz;
	}

	public void setYbbz(String ybbz) {
		this.ybbz = ybbz;
	}

	public Integer getYbflag() {
		return this.ybflag;
	}

	public void setYbflag(Integer ybflag) {
		this.ybflag = ybflag;
	}

	public Integer getYbstate() {
		return this.ybstate;
	}

	public void setYbstate(Integer ybstate) {
		this.ybstate = ybstate;
	}

	public BigDecimal getYdj() {
		return this.ydj;
	}

	public void setYdj(BigDecimal ydj) {
		this.ydj = ydj;
	}

	public String getYsbm() {
		return this.ysbm;
	}

	public void setYsbm(String ysbm) {
		this.ysbm = ysbm;
	}

	public Integer getYue() {
		return this.yue;
	}

	public void setYue(Integer yue) {
		this.yue = yue;
	}

	public String getYzbm() {
		return this.yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public Integer getYzgid() {
		return this.yzgid;
	}

	public void setYzgid(Integer yzgid) {
		this.yzgid = yzgid;
	}

	public Integer getYzid() {
		return this.yzid;
	}

	public void setYzid(Integer yzid) {
		this.yzid = yzid;
	}

	public Integer getYzlx() {
		return this.yzlx;
	}

	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}

	public String getYzmc() {
		return this.yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public Integer getYzrq() {
		return this.yzrq;
	}

	public void setYzrq(Integer yzrq) {
		this.yzrq = yzrq;
	}

	public String getZhxmbm() {
		return this.zhxmbm;
	}

	public void setZhxmbm(String zhxmbm) {
		this.zhxmbm = zhxmbm;
	}

	public Integer getZl() {
		return this.zl;
	}

	public void setZl(Integer zl) {
		this.zl = zl;
	}

	public String getZxks() {
		return this.zxks;
	}

	public void setZxks(String zxks) {
		this.zxks = zxks;
	}

}