package cn.jxszyyy.anyihis.test.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the JY_SQD database table.
 * 
 */
@Entity
@Table(name="JY_SQD", schema="HIS")
@NamedQuery(name="JySqd.findAll", query="SELECT j FROM JySqd j")
public class JySqd implements Serializable {
	private static final long serialVersionUID = -7928153311464594450L;

	@Id
	@Column(name="JYDH")
	private Integer jydh;

	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BHXM")
	private String bhxm;

	@Column(name="CH")
	private String ch;

	@Column(name="CSRQ")
	private Integer csrq;

	@Column(name="CZRY")
	private String czry;

	@Column(name="DYCS")
	private Integer dycs;

	@Column(name="F_ISJF")
	private Short fIsjf;

	@Column(name="FLAG")
	private Short flag;

	@Column(name="FY")
	private BigDecimal fy;

	@Column(name="FYKS")
	private String fyks;

	@Column(name="JYBM")
	private String jybm;

	@Column(name="JYGROUPID")
	private Integer jygroupid;

	@Column(name="JYRQ")
	private Integer jyrq;

	@Column(name="JYSM")
	private String jysm;

	@Column(name="JZRQ")
	private Integer jzrq;

	@Column(name="KFID")
	private Integer kfid;

	@Column(name="LRRY")
	private String lrry;

	@Column(name="LYKS")
	private String lyks;

	@Column(name="LYYS")
	private String lyys;

	@Column(name="PRINTSTATE")
	private Short prIntegerstate;

	@Column(name="PrintTime")
	private String PrintTime;

	@Column(name="QRTKFlag")
	private Short qrtkflag;

	@Column(name="QZD")
	private String qzd;

	@Column(name="SHRQ")
	private Integer shrq;

	@Column(name="SHRY")
	private String shry;

	@Column(name="SHRYGroupNo")
	private Integer SHRYGroupNo;

	@Column(name="SJRQ")
	private Integer sjrq;

	@Column(name="SJSJ")
	private Integer sjsj;

	@Column(name="SM")
	private String sm;

	@Column(name="SQKS")
	private String sqks;

	@Column(name="SQRQ")
	private Integer sqrq;

	@Column(name="SQSJ")
	private Integer sqsj;

	@Column(name="SQYS")
	private String sqys;

	@Column(name="TM")
	private String tm;

	@Column(name="XB")
	private String xb;

	@Column(name="YBH")
	private Integer ybh;

	@Column(name="YBLB")
	private Integer yblb;

	@Column(name="YQBM")
	private String yqbm;

	@Column(name="YSJG")
	private Integer ysjg;

	@Column(name="YSRY")
	private String ysry;

	@Column(name="YSSM")
	private String yssm;

	@Column(name="YZTM")
	private String yztm;

	@Column(name="YZXH")
	private Short yzxh;

	@Column(name="YZZH")
	private Integer yzzh;

	@Column(name="ZFRQ")
	private Integer zfrq;

	@Column(name="ZFRY")
	private String zfry;

	@Column(name="ZFSJ")
	private Integer zfsj;

	@Column(name="ZXBZ")
	private Short zxbz;

	@Column(name="ZYH")
	private String zyh;

	public JySqd() {}

	public Integer getJydh() {
		return this.jydh;
	}

	public void setJydh(Integer jydh) {
		this.jydh = jydh;
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(Integer bhid) {
		this.bhid = bhid;
	}

	public String getBhxm() {
		return this.bhxm;
	}

	public void setBhxm(String bhxm) {
		this.bhxm = bhxm;
	}

	public String getCh() {
		return this.ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public Integer getCsrq() {
		return this.csrq;
	}

	public void setCsrq(Integer csrq) {
		this.csrq = csrq;
	}

	public String getCzry() {
		return this.czry;
	}

	public void setCzry(String czry) {
		this.czry = czry;
	}

	public Integer getDycs() {
		return this.dycs;
	}

	public void setDycs(Integer dycs) {
		this.dycs = dycs;
	}

	public Short getFIsjf() {
		return this.fIsjf;
	}

	public void setFIsjf(Short fIsjf) {
		this.fIsjf = fIsjf;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

	public BigDecimal getFy() {
		return this.fy;
	}

	public void setFy(BigDecimal fy) {
		this.fy = fy;
	}

	public String getFyks() {
		return this.fyks;
	}

	public void setFyks(String fyks) {
		this.fyks = fyks;
	}

	public String getJybm() {
		return this.jybm;
	}

	public void setJybm(String jybm) {
		this.jybm = jybm;
	}

	public Integer getJygroupid() {
		return this.jygroupid;
	}

	public void setJygroupid(Integer jygroupid) {
		this.jygroupid = jygroupid;
	}

	public Integer getJyrq() {
		return this.jyrq;
	}

	public void setJyrq(Integer jyrq) {
		this.jyrq = jyrq;
	}

	public String getJysm() {
		return this.jysm;
	}

	public void setJysm(String jysm) {
		this.jysm = jysm;
	}

	public Integer getJzrq() {
		return this.jzrq;
	}

	public void setJzrq(Integer jzrq) {
		this.jzrq = jzrq;
	}

	public Integer getKfid() {
		return this.kfid;
	}

	public void setKfid(Integer kfid) {
		this.kfid = kfid;
	}

	public String getLrry() {
		return this.lrry;
	}

	public void setLrry(String lrry) {
		this.lrry = lrry;
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

	public Short getPrIntegerstate() {
		return this.prIntegerstate;
	}

	public void setPrIntegerstate(Short prIntegerstate) {
		this.prIntegerstate = prIntegerstate;
	}

	public String getPrIntegerTime() {
		return this.PrintTime;
	}

	public void setPrIntegerTime(String PrintTime) {
		this.PrintTime = PrintTime;
	}

	public Short getQrtkflag() {
		return this.qrtkflag;
	}

	public void setQrtkflag(Short qrtkflag) {
		this.qrtkflag = qrtkflag;
	}

	public String getQzd() {
		return this.qzd;
	}

	public void setQzd(String qzd) {
		this.qzd = qzd;
	}

	public Integer getShrq() {
		return this.shrq;
	}

	public void setShrq(Integer shrq) {
		this.shrq = shrq;
	}

	public String getShry() {
		return this.shry;
	}

	public void setShry(String shry) {
		this.shry = shry;
	}

	public Integer getSHRYGroupNo() {
		return this.SHRYGroupNo;
	}

	public void setSHRYGroupNo(Integer SHRYGroupNo) {
		this.SHRYGroupNo = SHRYGroupNo;
	}

	public Integer getSjrq() {
		return this.sjrq;
	}

	public void setSjrq(Integer sjrq) {
		this.sjrq = sjrq;
	}

	public Integer getSjsj() {
		return this.sjsj;
	}

	public void setSjsj(Integer sjsj) {
		this.sjsj = sjsj;
	}

	public String getSm() {
		return this.sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getSqks() {
		return this.sqks;
	}

	public void setSqks(String sqks) {
		this.sqks = sqks;
	}

	public Integer getSqrq() {
		return this.sqrq;
	}

	public void setSqrq(Integer sqrq) {
		this.sqrq = sqrq;
	}

	public Integer getSqsj() {
		return this.sqsj;
	}

	public void setSqsj(Integer sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqys() {
		return this.sqys;
	}

	public void setSqys(String sqys) {
		this.sqys = sqys;
	}

	public String getTm() {
		return this.tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public Integer getYbh() {
		return this.ybh;
	}

	public void setYbh(Integer ybh) {
		this.ybh = ybh;
	}

	public Integer getYblb() {
		return this.yblb;
	}

	public void setYblb(Integer yblb) {
		this.yblb = yblb;
	}

	public String getYqbm() {
		return this.yqbm;
	}

	public void setYqbm(String yqbm) {
		this.yqbm = yqbm;
	}

	public Integer getYsjg() {
		return this.ysjg;
	}

	public void setYsjg(Integer ysjg) {
		this.ysjg = ysjg;
	}

	public String getYsry() {
		return this.ysry;
	}

	public void setYsry(String ysry) {
		this.ysry = ysry;
	}

	public String getYssm() {
		return this.yssm;
	}

	public void setYssm(String yssm) {
		this.yssm = yssm;
	}

	public String getYztm() {
		return this.yztm;
	}

	public void setYztm(String yztm) {
		this.yztm = yztm;
	}

	public Short getYzxh() {
		return this.yzxh;
	}

	public void setYzxh(Short yzxh) {
		this.yzxh = yzxh;
	}

	public Integer getYzzh() {
		return this.yzzh;
	}

	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}

	public Integer getZfrq() {
		return this.zfrq;
	}

	public void setZfrq(Integer zfrq) {
		this.zfrq = zfrq;
	}

	public String getZfry() {
		return this.zfry;
	}

	public void setZfry(String zfry) {
		this.zfry = zfry;
	}

	public Integer getZfsj() {
		return this.zfsj;
	}

	public void setZfsj(Integer zfsj) {
		this.zfsj = zfsj;
	}

	public Short getZxbz() {
		return this.zxbz;
	}

	public void setZxbz(Short zxbz) {
		this.zxbz = zxbz;
	}

	public String getZyh() {
		return this.zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

}