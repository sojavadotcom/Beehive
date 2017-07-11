package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.Column;

//@Entity
//@NamedQuery(name = "FeesByNeedConform.findAll",
//			query = 
//			"select " +
//			"sqd0.*,jyd1.sumfy,jyd1.maxxh,sf.mc" +
//			" from jySqd sqd0" +
//			" inner join (select jydh,sum(fy) sumfy,max(xh) maxxh from jyJyd1 where flag & 8=0 group by jydh) jyd1 on jyd1.jydh = sqd0.jydh" +
//			" inner join zdSf sf on sqd0.jybm=sf.bm" +
//			" where " +
//			" sqd0.flag & 71 = 1" +
//			" and his.af_isnulln(sqd0.zxbz,0) & 1 = 0" +
//			" and sqd0.flag & 32 = 32" +
//			" order by sqd0.zyh, sqd0.sqrq, sqd0.sqsj")
public class FeesByNeedConform implements Serializable {
	private static final long serialVersionUID = -516826075441015971L;

	@Column(name = "year")
	private Integer year;
	@Column(name = "month")
	private Integer month;
	@Column(name = "bhid")
	private Integer bhid;
	@Column(name = "yzid")
	private Integer yzid;
	@Column(name = "yzgid")
	private String yzgid;
	@Column(name = "yzbm")
	private String yzbm;
	@Column(name = "yzmc")
	private String yzmc;
	@Column(name = "jldw")
	private String jldw;
	@Column(name = "yzlx")
	private String yzlx;
	@Column(name = "zhxmbm")
	private String zhxmbm;
	@Column(name = "yzrq")
	private Integer yzrq;
	@Column(name = "rq")
	private Integer rq;
	@Column(name = "sj")
	private Integer sj;
	@Column(name = "ksbm")
	private String ksbm;
	@Column(name = "ysbm")
	private String ysbm;
	@Column(name = "lyks")
	private String lyks;
	@Column(name = "lyys")
	private String lyys;
	@Column(name = "zxks")
	private String zxks;
	@Column(name = "hsxz")
	private String hsxz;
	@Column(name = "ly")
	private Integer ly;
	@Column(name = "dj")
	private double dj;
	@Column(name = "zl")
	private Integer zl;
	@Column(name = "je")
	private double je;
	@Column(name = "fylb")
	private Integer fylb;
	@Column(name = "flag")
	private Integer flag;

	public FeesByNeedConform() {}

	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Integer getBhid() {
		return bhid;
	}
	public void setBhid(int bhid) {
		this.bhid = bhid;
	}
	public Integer getYzid() {
		return yzid;
	}
	public void setYzid(int yzid) {
		this.yzid = yzid;
	}
	public String getYzgid() {
		return yzgid;
	}
	public void setYzgid(String yzgid) {
		this.yzgid = yzgid;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getYzmc() {
		return yzmc;
	}
	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}
	public String getJldw() {
		return jldw;
	}
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	public String getYzlx() {
		return yzlx;
	}
	public void setYzlx(String yzlx) {
		this.yzlx = yzlx;
	}
	public String getZhxmbm() {
		return zhxmbm;
	}
	public void setZhxmbm(String zhxmbm) {
		this.zhxmbm = zhxmbm;
	}
	public Integer getYzrq() {
		return yzrq;
	}
	public void setYzrq(int yzrq) {
		this.yzrq = yzrq;
	}
	public Integer getRq() {
		return rq;
	}
	public void setRq(int rq) {
		this.rq = rq;
	}
	public Integer getSj() {
		return sj;
	}
	public void setSj(int sj) {
		this.sj = sj;
	}
	public String getKsbm() {
		return ksbm;
	}
	public void setKsbm(String ksbm) {
		this.ksbm = ksbm;
	}
	public String getYsbm() {
		return ysbm;
	}
	public void setYsbm(String ysbm) {
		this.ysbm = ysbm;
	}
	public String getLyks() {
		return lyks;
	}
	public void setLyks(String lyks) {
		this.lyks = lyks;
	}
	public String getLyys() {
		return lyys;
	}
	public void setLyys(String lyys) {
		this.lyys = lyys;
	}
	public String getZxks() {
		return zxks;
	}
	public void setZxks(String zxks) {
		this.zxks = zxks;
	}
	public String getHsxz() {
		return hsxz;
	}
	public void setHsxz(String hsxz) {
		this.hsxz = hsxz;
	}
	public Integer getLy() {
		return ly;
	}
	public void setLy(int ly) {
		this.ly = ly;
	}
	public double getDj() {
		return dj;
	}
	public void setDj(double dj) {
		this.dj = dj;
	}
	public Integer getZl() {
		return zl;
	}
	public void setZl(int zl) {
		this.zl = zl;
	}
	public double getJe() {
		return je;
	}
	public void setJe(double je) {
		this.je = je;
	}
	public Integer getFylb() {
		return fylb;
	}
	public void setFylb(int fylb) {
		this.fylb = fylb;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
