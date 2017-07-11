package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BH0 database table.
 * 
 */
@Entity
@Table(name="ZY_BH0", schema="HIS")
@NamedQuery(name="ZyBh0.findAll", query="SELECT z FROM ZyBh0 z")
public class ZyBh0 implements Serializable {
	private static final long serialVersionUID = -7822998991883320999L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BLDJ")
	private String bldj;

	@Column(name="BLPF")
	private BigDecimal blpf;

	@Column(name="BLSTATE")
	private Integer blstate;

	@Column(name="BLTJRQ")
	private Integer bltjrq;

	@Column(name="BQ")
	private String bq;

	@Column(name="BSLQK")
	private Integer bslqk;

	@Column(name="BSZT")
	private Integer bszt;

	@Column(name="CBH")
	private String cbh;

	@Column(name="CFDJ")
	private Short cfdj;

	@Column(name="CQXDROW")
	private Integer cqxdrow;

	@Column(name="CSRQ")
	private Integer csrq;

	@Column(name="CSSJ")
	private Integer cssj;

	@Column(name="CURKS")
	private String curks;

	@Column(name="CYQK")
	private String cyqk;

	@Column(name="CYRQ")
	private Integer cyrq;

	@Column(name="CYSJ")
	private Integer cysj;

	@Column(name="FB")
	private Short fb;

	@Column(name="FLAG")
	private Integer flag;

	@Column(name="FYFHJG")
	private String fyfhjg;

	@Column(name="FYZT")
	private Short fyzt;

	@Column(name="HF")
	private String hf;

	@Column(name="HLBM")
	private String hlbm;

	@Column(name="HTDW")
	private String htdw;

	@Column(name="ICKH")
	private String ickh;

	@Column(name="ICYE")
	private BigDecimal icye;

	@Column(name="ISBFBZ")
	private Short isbfbz;

	@Column(name="ISDBZ")
	private Short isdbz;

	@Column(name="JG")
	private String jg;

	@Column(name="JKKH")
	private String jkkh;

	@Column(name="JSRQ")
	private Integer jsrq;

	@Column(name="JZRQ")
	private Integer jzrq;

	@Column(name="LSXDROW")
	private Integer lsxdrow;

	@Column(name="MZ")
	private String mz;

	@Column(name="NDTCE")
	private BigDecimal ndtce;

	@Column(name="PRECYRQ")
	private Integer precyrq;

	@Column(name="PRECYSJ")
	private Integer precysj;

	@Column(name="QXSTATE")
	private Integer qxstate;

	@Column(name="QZRQ")
	private Integer qzrq;

	@Column(name="RYCS")
	private Short rycs;

	@Column(name="RYKS")
	private String ryks;

	@Column(name="RYLY")
	private String ryly;

	@Column(name="RYRQ")
	private Integer ryrq;

	@Column(name="RYSJ")
	private Integer rysj;

	@Column(name="SBH")
	private String sbh;

	@Column(name="SECU")
	private Integer secu;

	@Column(name="SF")
	private String sf;

	@Column(name="[STATE]")
	private Integer state;

	@Column(name="SXBL")
	private BigDecimal sxbl;

	@Column(name="SXPF")
	private BigDecimal sxpf;

	@Column(name="XB")
	private String xb;

	@Column(name="XJYJ")
	private BigDecimal xjyj;

	@Column(name="XM")
	private String xm;

	@Column(name="XMPYM")
	private String xmpym;

	@Column(name="XZZ")
	private String xzz;

	@Column(name="XZZDH")
	private String xzzdh;

	@Column(name="XZZYB")
	private String xzzyb;

	@Column(name="YSPF")
	private BigDecimal yspf;

	@Column(name="YSPFDJ")
	private String yspfdj;

	private Short YZFSFlag;

	@Column(name="ZFYSX")
	private BigDecimal zfysx;

	@Column(name="ZPYJ")
	private BigDecimal zpyj;

	@Column(name="ZYH")
	private String zyh;

	public ZyBh0() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public String getBldj() {
		return this.bldj;
	}

	public void setBldj(String bldj) {
		this.bldj = bldj;
	}

	public BigDecimal getBlpf() {
		return this.blpf;
	}

	public void setBlpf(BigDecimal blpf) {
		this.blpf = blpf;
	}

	public Integer getBlstate() {
		return this.blstate;
	}

	public void setBlstate(int blstate) {
		this.blstate = blstate;
	}

	public Integer getBltjrq() {
		return this.bltjrq;
	}

	public void setBltjrq(int bltjrq) {
		this.bltjrq = bltjrq;
	}

	public String getBq() {
		return this.bq;
	}

	public void setBq(String bq) {
		this.bq = bq;
	}

	public Integer getBslqk() {
		return this.bslqk;
	}

	public void setBslqk(int bslqk) {
		this.bslqk = bslqk;
	}

	public Integer getBszt() {
		return this.bszt;
	}

	public void setBszt(int bszt) {
		this.bszt = bszt;
	}

	public String getCbh() {
		return this.cbh;
	}

	public void setCbh(String cbh) {
		this.cbh = cbh;
	}

	public Short getCfdj() {
		return this.cfdj;
	}

	public void setCfdj(short cfdj) {
		this.cfdj = cfdj;
	}

	public Integer getCqxdrow() {
		return this.cqxdrow;
	}

	public void setCqxdrow(int cqxdrow) {
		this.cqxdrow = cqxdrow;
	}

	public Integer getCsrq() {
		return this.csrq;
	}

	public void setCsrq(int csrq) {
		this.csrq = csrq;
	}

	public Integer getCssj() {
		return this.cssj;
	}

	public void setCssj(int cssj) {
		this.cssj = cssj;
	}

	public String getCurks() {
		return this.curks;
	}

	public void setCurks(String curks) {
		this.curks = curks;
	}

	public String getCyqk() {
		return this.cyqk;
	}

	public void setCyqk(String cyqk) {
		this.cyqk = cyqk;
	}

	public Integer getCyrq() {
		return this.cyrq;
	}

	public void setCyrq(int cyrq) {
		this.cyrq = cyrq;
	}

	public Integer getCysj() {
		return this.cysj;
	}

	public void setCysj(int cysj) {
		this.cysj = cysj;
	}

	public Short getFb() {
		return this.fb;
	}

	public void setFb(short fb) {
		this.fb = fb;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getFyfhjg() {
		return this.fyfhjg;
	}

	public void setFyfhjg(String fyfhjg) {
		this.fyfhjg = fyfhjg;
	}

	public Short getFyzt() {
		return this.fyzt;
	}

	public void setFyzt(short fyzt) {
		this.fyzt = fyzt;
	}

	public String getHf() {
		return this.hf;
	}

	public void setHf(String hf) {
		this.hf = hf;
	}

	public String getHlbm() {
		return this.hlbm;
	}

	public void setHlbm(String hlbm) {
		this.hlbm = hlbm;
	}

	public String getHtdw() {
		return this.htdw;
	}

	public void setHtdw(String htdw) {
		this.htdw = htdw;
	}

	public String getIckh() {
		return this.ickh;
	}

	public void setIckh(String ickh) {
		this.ickh = ickh;
	}

	public BigDecimal getIcye() {
		return this.icye;
	}

	public void setIcye(BigDecimal icye) {
		this.icye = icye;
	}

	public Short getIsbfbz() {
		return this.isbfbz;
	}

	public void setIsbfbz(short isbfbz) {
		this.isbfbz = isbfbz;
	}

	public Short getIsdbz() {
		return this.isdbz;
	}

	public void setIsdbz(short isdbz) {
		this.isdbz = isdbz;
	}

	public String getJg() {
		return this.jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJkkh() {
		return this.jkkh;
	}

	public void setJkkh(String jkkh) {
		this.jkkh = jkkh;
	}

	public Integer getJsrq() {
		return this.jsrq;
	}

	public void setJsrq(int jsrq) {
		this.jsrq = jsrq;
	}

	public Integer getJzrq() {
		return this.jzrq;
	}

	public void setJzrq(int jzrq) {
		this.jzrq = jzrq;
	}

	public Integer getLsxdrow() {
		return this.lsxdrow;
	}

	public void setLsxdrow(int lsxdrow) {
		this.lsxdrow = lsxdrow;
	}

	public String getMz() {
		return this.mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public BigDecimal getNdtce() {
		return this.ndtce;
	}

	public void setNdtce(BigDecimal ndtce) {
		this.ndtce = ndtce;
	}

	public Integer getPrecyrq() {
		return this.precyrq;
	}

	public void setPrecyrq(int precyrq) {
		this.precyrq = precyrq;
	}

	public Integer getPrecysj() {
		return this.precysj;
	}

	public void setPrecysj(int precysj) {
		this.precysj = precysj;
	}

	public Integer getQxstate() {
		return this.qxstate;
	}

	public void setQxstate(int qxstate) {
		this.qxstate = qxstate;
	}

	public Integer getQzrq() {
		return this.qzrq;
	}

	public void setQzrq(int qzrq) {
		this.qzrq = qzrq;
	}

	public Short getRycs() {
		return this.rycs;
	}

	public void setRycs(short rycs) {
		this.rycs = rycs;
	}

	public String getRyks() {
		return this.ryks;
	}

	public void setRyks(String ryks) {
		this.ryks = ryks;
	}

	public String getRyly() {
		return this.ryly;
	}

	public void setRyly(String ryly) {
		this.ryly = ryly;
	}

	public Integer getRyrq() {
		return this.ryrq;
	}

	public void setRyrq(int ryrq) {
		this.ryrq = ryrq;
	}

	public Integer getRysj() {
		return this.rysj;
	}

	public void setRysj(int rysj) {
		this.rysj = rysj;
	}

	public String getSbh() {
		return this.sbh;
	}

	public void setSbh(String sbh) {
		this.sbh = sbh;
	}

	public Integer getSecu() {
		return this.secu;
	}

	public void setSecu(int secu) {
		this.secu = secu;
	}

	public String getSf() {
		return this.sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public BigDecimal getSxbl() {
		return this.sxbl;
	}

	public void setSxbl(BigDecimal sxbl) {
		this.sxbl = sxbl;
	}

	public BigDecimal getSxpf() {
		return this.sxpf;
	}

	public void setSxpf(BigDecimal sxpf) {
		this.sxpf = sxpf;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public BigDecimal getXjyj() {
		return this.xjyj;
	}

	public void setXjyj(BigDecimal xjyj) {
		this.xjyj = xjyj;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXmpym() {
		return this.xmpym;
	}

	public void setXmpym(String xmpym) {
		this.xmpym = xmpym;
	}

	public String getXzz() {
		return this.xzz;
	}

	public void setXzz(String xzz) {
		this.xzz = xzz;
	}

	public String getXzzdh() {
		return this.xzzdh;
	}

	public void setXzzdh(String xzzdh) {
		this.xzzdh = xzzdh;
	}

	public String getXzzyb() {
		return this.xzzyb;
	}

	public void setXzzyb(String xzzyb) {
		this.xzzyb = xzzyb;
	}

	public BigDecimal getYspf() {
		return this.yspf;
	}

	public void setYspf(BigDecimal yspf) {
		this.yspf = yspf;
	}

	public String getYspfdj() {
		return this.yspfdj;
	}

	public void setYspfdj(String yspfdj) {
		this.yspfdj = yspfdj;
	}

	public Short getYZFSFlag() {
		return this.YZFSFlag;
	}

	public void setYZFSFlag(short YZFSFlag) {
		this.YZFSFlag = YZFSFlag;
	}

	public BigDecimal getZfysx() {
		return this.zfysx;
	}

	public void setZfysx(BigDecimal zfysx) {
		this.zfysx = zfysx;
	}

	public BigDecimal getZpyj() {
		return this.zpyj;
	}

	public void setZpyj(BigDecimal zpyj) {
		this.zpyj = zpyj;
	}

	public String getZyh() {
		return this.zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

}