package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BHBH database table.
 * 
 */
@Entity
@Table(name="ZY_BHBH", schema="HIS")
@NamedQuery(name="ZyBhbh.findAll", query="SELECT z FROM ZyBhbh z")
public class ZyBhbh implements Serializable {
	private static final long serialVersionUID = 9088109067254043335L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BS")
	private String bs;

	@Column(name="CH")
	private String ch;

	@Column(name="CWF")
	private BigDecimal cwf;

	@Column(name="CZRY")
	private String czry;

	@Column(name="F_NEWBC")
	private Integer fNewbc;

	@Column(name="FLAG")
	private Short flag;

	@Column(name="FYLB")
	private Short fylb;

	@Column(name="HSKS")
	private String hsks;

	@Column(name="HSXZ")
	private String hsxz;

	@Column(name="INSTATE")
	private Short instate;

	@Column(name="JSRQ")
	private Integer jsrq;

	@Column(name="JSSJ")
	private Integer jssj;

	@Column(name="KS")
	private String ks;

	@Column(name="KSBM")
	private String ksbm;

	@Column(name="KSRQ")
	private Integer ksrq;

	@Column(name="KSSJ")
	private Integer kssj;

	@Column(name="LYKS")
	private String lyks;

	@Column(name="LYYS")
	private String lyys;

	@Column(name="SFID")
	private Integer sfid;

	@Column(name="SFXMBM")
	private String sfxmbm;

	@Column(name="[STATE]")
	private Short state;

	@Column(name="ZZYS")
	private String zzys;

	public ZyBhbh() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public String getBs() {
		return this.bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public String getCh() {
		return this.ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public BigDecimal getCwf() {
		return this.cwf;
	}

	public void setCwf(BigDecimal cwf) {
		this.cwf = cwf;
	}

	public String getCzry() {
		return this.czry;
	}

	public void setCzry(String czry) {
		this.czry = czry;
	}

	public Integer getFNewbc() {
		return this.fNewbc;
	}

	public void setFNewbc(int fNewbc) {
		this.fNewbc = fNewbc;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(short flag) {
		this.flag = flag;
	}

	public Short getFylb() {
		return this.fylb;
	}

	public void setFylb(short fylb) {
		this.fylb = fylb;
	}

	public String getHsks() {
		return this.hsks;
	}

	public void setHsks(String hsks) {
		this.hsks = hsks;
	}

	public String getHsxz() {
		return this.hsxz;
	}

	public void setHsxz(String hsxz) {
		this.hsxz = hsxz;
	}

	public Short getInstate() {
		return this.instate;
	}

	public void setInstate(short instate) {
		this.instate = instate;
	}

	public Integer getJsrq() {
		return this.jsrq;
	}

	public void setJsrq(int jsrq) {
		this.jsrq = jsrq;
	}

	public Integer getJssj() {
		return this.jssj;
	}

	public void setJssj(int jssj) {
		this.jssj = jssj;
	}

	public String getKs() {
		return this.ks;
	}

	public void setKs(String ks) {
		this.ks = ks;
	}

	public String getKsbm() {
		return this.ksbm;
	}

	public void setKsbm(String ksbm) {
		this.ksbm = ksbm;
	}

	public Integer getKsrq() {
		return this.ksrq;
	}

	public void setKsrq(int ksrq) {
		this.ksrq = ksrq;
	}

	public Integer getKssj() {
		return this.kssj;
	}

	public void setKssj(int kssj) {
		this.kssj = kssj;
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

	public Integer getSfid() {
		return this.sfid;
	}

	public void setSfid(int sfid) {
		this.sfid = sfid;
	}

	public String getSfxmbm() {
		return this.sfxmbm;
	}

	public void setSfxmbm(String sfxmbm) {
		this.sfxmbm = sfxmbm;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public String getZzys() {
		return this.zzys;
	}

	public void setZzys(String zzys) {
		this.zzys = zzys;
	}

}