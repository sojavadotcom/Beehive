package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BH3 database table.
 * 
 */
@Entity
@Table(name="ZY_BH3", schema="HIS")
@NamedQuery(name="ZyBh3.findAll", query="SELECT z FROM ZyBh3 z")
public class ZyBh3 implements Serializable {
	private static final long serialVersionUID = 2036206069225337107L;

	@Column(name="BABMRY")
	private String babmry;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BLFX")
	private Integer blfx;

	@Column(name="BLH")
	private String blh;

	@Column(name="BLZD")
	private String blzd;

	@Column(name="BLZDBM")
	private String blzdbm;

	@Column(name="BLZL")
	private Short blzl;

	@Column(name="CGCS")
	private Short cgcs;

	@Column(name="CJCS")
	private Short cjcs;

	@Column(name="CT_NO")
	private String ctNo;

	@Column(name="CYFS")
	private Short cyfs;

	@Column(name="CYZZYJH")
	private Short cyzzyjh;

	@Column(name="CYZZYJHMD")
	private String cyzzyjhmd;

	@Column(name="FIRSTCASE")
	private Short firstcase;

	@Column(name="FKFS")
	private Short fkfs;

	@Column(name="FLAG")
	private Short flag;

	@Column(name="FSBL")
	private Short fsbl;

	@Column(name="GMYP1")
	private String gmyp1;

	@Column(name="GMYP2")
	private String gmyp2;

	@Column(name="GMYW")
	private String gmyw;

	@Column(name="GWRS")
	private Short gwrs;

	@Column(name="HBS")
	private Short hbs;

	@Column(name="HCV")
	private Short hcv;

	@Column(name="HIV")
	private Short hiv;

	@Column(name="HL1")
	private Short hl1;

	@Column(name="HL2")
	private Short hl2;

	@Column(name="HL3")
	private Short hl3;

	@Column(name="HL4")
	private Short hl4;

	@Column(name="HL5")
	private Short hl5;

	@Column(name="HL6")
	private Short hl6;

	@Column(name="HL7")
	private Short hl7;

	@Column(name="HZ1")
	private Short hz1;

	@Column(name="HZ2")
	private Short hz2;

	@Column(name="LCBL")
	private Short lcbl;

	@Column(name="LCLJ")
	private Short lclj;

	@Column(name="LNSSHMD0")
	private Short lnsshmd0;

	@Column(name="LNSSHMD1")
	private Short lnsshmd1;

	@Column(name="LNSSHMH0")
	private Short lnsshmh0;

	@Column(name="LNSSHMH1")
	private Short lnsshmh1;

	@Column(name="LNSSHMM0")
	private Short lnsshmm0;

	@Column(name="LNSSHMM1")
	private Short lnsshmm1;

	@Column(name="LYFS")
	private Short lyfs;

	@Column(name="LYJSYLJG")
	private String lyjsyljg;

	@Column(name="MRI_NO")
	private String mriNo;

	@Column(name="MZCY")
	private Short mzcy;

	@Column(name="QJCS")
	private Short qjcs;

	@Column(name="QJFF")
	private Short qjff;

	@Column(name="RH")
	private Short rh;

	@Column(name="RYCY")
	private Short rycy;

	@Column(name="RYTJ")
	private Short rytj;

	@Column(name="SFSJ")
	private Short sfsj;

	@Column(name="SFSZ")
	private Short sfsz;

	@Column(name="SJBL")
	private Short sjbl;

	@Column(name="SQSH")
	private Short sqsh;

	@Column(name="SSBH")
	private Short ssbh;

	@Column(name="SSZDYY")
	private String sszdyy;

	@Column(name="SSZDYYICD")
	private String sszdyyicd;

	@Column(name="SSZDYYICDFLAG")
	private Short sszdyyicdflag;

	@Column(name="SWRQ")
	private Integer swrq;

	@Column(name="SWSJ")
	private Integer swsj;

	@Column(name="SWYY")
	private String swyy;

	@Column(name="SXFY")
	private Short sxfy;

	@Column(name="SXHXB")
	private BigDecimal sxhxb;

	@Column(name="SXQT")
	private BigDecimal sxqt;

	@Column(name="SXQX")
	private BigDecimal sxqx;

	@Column(name="SXXJ")
	private BigDecimal sxxj;

	@Column(name="SXXXB")
	private BigDecimal sxxxb;

	@Column(name="SYFY")
	private Short syfy;

	@Column(name="SZQX")
	private String szqx;

	@Column(name="WYCJ")
	private Short wycj;

	@Column(name="WYZZ")
	private Short wyzz;

	@Column(name="WZJZYN")
	private Short wzjzyn;

	@Column(name="WZJZYN1")
	private Short wzjzyn1;

	@Column(name="WZJZYN2")
	private Short wzjzyn2;

	@Column(name="X_NO")
	private String xNo;

	@Column(name="XSECSTZ")
	private String xsecstz;

	@Column(name="XSERYTZ")
	private String xserytz;

	@Column(name="XX")
	private Short xx;

	@Column(name="YQGL")
	private Short yqgl;

	@Column(name="YSBM1")
	private String ysbm1;

	@Column(name="YSBM2")
	private String ysbm2;

	@Column(name="YSBM3")
	private String ysbm3;

	@Column(name="YSBM4")
	private String ysbm4;

	@Column(name="YSBM5")
	private String ysbm5;

	@Column(name="YSBM6")
	private String ysbm6;

	@Column(name="YSBM7")
	private String ysbm7;

	@Column(name="YWGM")
	private Short ywgm;

	@Column(name="YZTIPS")
	private String yztips;

	@Column(name="ZJPFY")
	private String zjpfy;

	@Column(name="ZKHS")
	private String zkhs;

	@Column(name="ZKQK")
	private String zkqk;

	@Column(name="ZKRQ")
	private Integer zkrq;

	@Column(name="ZKYS")
	private String zkys;

	@Column(name="ZLLB")
	private Short zllb;

	@Column(name="ZLSB")
	private Short zlsb;

	@Column(name="ZRHS")
	private String zrhs;

	@Column(name="ZYJC")
	private Short zyjc;

	@Column(name="ZYMZCY")
	private Short zymzcy;

	@Column(name="ZYRYCY")
	private Short zyrycy;

	@Column(name="ZYZJ")
	private Short zyzj;

	@Column(name="ZYZLJS")
	private Short zyzljs;

	@Column(name="ZZZY")
	private Short zzzy;

	public ZyBh3() {
	}

	public String getBabmry() {
		return this.babmry;
	}

	public void setBabmry(String babmry) {
		this.babmry = babmry;
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public Integer getBlfx() {
		return this.blfx;
	}

	public void setBlfx(int blfx) {
		this.blfx = blfx;
	}

	public String getBlh() {
		return this.blh;
	}

	public void setBlh(String blh) {
		this.blh = blh;
	}

	public String getBlzd() {
		return this.blzd;
	}

	public void setBlzd(String blzd) {
		this.blzd = blzd;
	}

	public String getBlzdbm() {
		return this.blzdbm;
	}

	public void setBlzdbm(String blzdbm) {
		this.blzdbm = blzdbm;
	}

	public Short getBlzl() {
		return this.blzl;
	}

	public void setBlzl(short blzl) {
		this.blzl = blzl;
	}

	public Short getCgcs() {
		return this.cgcs;
	}

	public void setCgcs(short cgcs) {
		this.cgcs = cgcs;
	}

	public Short getCjcs() {
		return this.cjcs;
	}

	public void setCjcs(short cjcs) {
		this.cjcs = cjcs;
	}

	public String getCtNo() {
		return this.ctNo;
	}

	public void setCtNo(String ctNo) {
		this.ctNo = ctNo;
	}

	public Short getCyfs() {
		return this.cyfs;
	}

	public void setCyfs(short cyfs) {
		this.cyfs = cyfs;
	}

	public Short getCyzzyjh() {
		return this.cyzzyjh;
	}

	public void setCyzzyjh(short cyzzyjh) {
		this.cyzzyjh = cyzzyjh;
	}

	public String getCyzzyjhmd() {
		return this.cyzzyjhmd;
	}

	public void setCyzzyjhmd(String cyzzyjhmd) {
		this.cyzzyjhmd = cyzzyjhmd;
	}

	public Short getFirstcase() {
		return this.firstcase;
	}

	public void setFirstcase(short firstcase) {
		this.firstcase = firstcase;
	}

	public Short getFkfs() {
		return this.fkfs;
	}

	public void setFkfs(short fkfs) {
		this.fkfs = fkfs;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(short flag) {
		this.flag = flag;
	}

	public Short getFsbl() {
		return this.fsbl;
	}

	public void setFsbl(short fsbl) {
		this.fsbl = fsbl;
	}

	public String getGmyp1() {
		return this.gmyp1;
	}

	public void setGmyp1(String gmyp1) {
		this.gmyp1 = gmyp1;
	}

	public String getGmyp2() {
		return this.gmyp2;
	}

	public void setGmyp2(String gmyp2) {
		this.gmyp2 = gmyp2;
	}

	public String getGmyw() {
		return this.gmyw;
	}

	public void setGmyw(String gmyw) {
		this.gmyw = gmyw;
	}

	public Short getGwrs() {
		return this.gwrs;
	}

	public void setGwrs(short gwrs) {
		this.gwrs = gwrs;
	}

	public Short getHbs() {
		return this.hbs;
	}

	public void setHbs(short hbs) {
		this.hbs = hbs;
	}

	public Short getHcv() {
		return this.hcv;
	}

	public void setHcv(short hcv) {
		this.hcv = hcv;
	}

	public Short getHiv() {
		return this.hiv;
	}

	public void setHiv(short hiv) {
		this.hiv = hiv;
	}

	public Short getHl1() {
		return this.hl1;
	}

	public void setHl1(short hl1) {
		this.hl1 = hl1;
	}

	public Short getHl2() {
		return this.hl2;
	}

	public void setHl2(short hl2) {
		this.hl2 = hl2;
	}

	public Short getHl3() {
		return this.hl3;
	}

	public void setHl3(short hl3) {
		this.hl3 = hl3;
	}

	public Short getHl4() {
		return this.hl4;
	}

	public void setHl4(short hl4) {
		this.hl4 = hl4;
	}

	public Short getHl5() {
		return this.hl5;
	}

	public void setHl5(short hl5) {
		this.hl5 = hl5;
	}

	public Short getHl6() {
		return this.hl6;
	}

	public void setHl6(short hl6) {
		this.hl6 = hl6;
	}

	public Short getHl7() {
		return this.hl7;
	}

	public void setHl7(short hl7) {
		this.hl7 = hl7;
	}

	public Short getHz1() {
		return this.hz1;
	}

	public void setHz1(short hz1) {
		this.hz1 = hz1;
	}

	public Short getHz2() {
		return this.hz2;
	}

	public void setHz2(short hz2) {
		this.hz2 = hz2;
	}

	public Short getLcbl() {
		return this.lcbl;
	}

	public void setLcbl(short lcbl) {
		this.lcbl = lcbl;
	}

	public Short getLclj() {
		return this.lclj;
	}

	public void setLclj(short lclj) {
		this.lclj = lclj;
	}

	public Short getLnsshmd0() {
		return this.lnsshmd0;
	}

	public void setLnsshmd0(short lnsshmd0) {
		this.lnsshmd0 = lnsshmd0;
	}

	public Short getLnsshmd1() {
		return this.lnsshmd1;
	}

	public void setLnsshmd1(short lnsshmd1) {
		this.lnsshmd1 = lnsshmd1;
	}

	public Short getLnsshmh0() {
		return this.lnsshmh0;
	}

	public void setLnsshmh0(short lnsshmh0) {
		this.lnsshmh0 = lnsshmh0;
	}

	public Short getLnsshmh1() {
		return this.lnsshmh1;
	}

	public void setLnsshmh1(short lnsshmh1) {
		this.lnsshmh1 = lnsshmh1;
	}

	public Short getLnsshmm0() {
		return this.lnsshmm0;
	}

	public void setLnsshmm0(short lnsshmm0) {
		this.lnsshmm0 = lnsshmm0;
	}

	public Short getLnsshmm1() {
		return this.lnsshmm1;
	}

	public void setLnsshmm1(short lnsshmm1) {
		this.lnsshmm1 = lnsshmm1;
	}

	public Short getLyfs() {
		return this.lyfs;
	}

	public void setLyfs(short lyfs) {
		this.lyfs = lyfs;
	}

	public String getLyjsyljg() {
		return this.lyjsyljg;
	}

	public void setLyjsyljg(String lyjsyljg) {
		this.lyjsyljg = lyjsyljg;
	}

	public String getMriNo() {
		return this.mriNo;
	}

	public void setMriNo(String mriNo) {
		this.mriNo = mriNo;
	}

	public Short getMzcy() {
		return this.mzcy;
	}

	public void setMzcy(short mzcy) {
		this.mzcy = mzcy;
	}

	public Short getQjcs() {
		return this.qjcs;
	}

	public void setQjcs(short qjcs) {
		this.qjcs = qjcs;
	}

	public Short getQjff() {
		return this.qjff;
	}

	public void setQjff(short qjff) {
		this.qjff = qjff;
	}

	public Short getRh() {
		return this.rh;
	}

	public void setRh(short rh) {
		this.rh = rh;
	}

	public Short getRycy() {
		return this.rycy;
	}

	public void setRycy(short rycy) {
		this.rycy = rycy;
	}

	public Short getRytj() {
		return this.rytj;
	}

	public void setRytj(short rytj) {
		this.rytj = rytj;
	}

	public Short getSfsj() {
		return this.sfsj;
	}

	public void setSfsj(short sfsj) {
		this.sfsj = sfsj;
	}

	public Short getSfsz() {
		return this.sfsz;
	}

	public void setSfsz(short sfsz) {
		this.sfsz = sfsz;
	}

	public Short getSjbl() {
		return this.sjbl;
	}

	public void setSjbl(short sjbl) {
		this.sjbl = sjbl;
	}

	public Short getSqsh() {
		return this.sqsh;
	}

	public void setSqsh(short sqsh) {
		this.sqsh = sqsh;
	}

	public Short getSsbh() {
		return this.ssbh;
	}

	public void setSsbh(short ssbh) {
		this.ssbh = ssbh;
	}

	public String getSszdyy() {
		return this.sszdyy;
	}

	public void setSszdyy(String sszdyy) {
		this.sszdyy = sszdyy;
	}

	public String getSszdyyicd() {
		return this.sszdyyicd;
	}

	public void setSszdyyicd(String sszdyyicd) {
		this.sszdyyicd = sszdyyicd;
	}

	public Short getSszdyyicdflag() {
		return this.sszdyyicdflag;
	}

	public void setSszdyyicdflag(short sszdyyicdflag) {
		this.sszdyyicdflag = sszdyyicdflag;
	}

	public Integer getSwrq() {
		return this.swrq;
	}

	public void setSwrq(int swrq) {
		this.swrq = swrq;
	}

	public Integer getSwsj() {
		return this.swsj;
	}

	public void setSwsj(int swsj) {
		this.swsj = swsj;
	}

	public String getSwyy() {
		return this.swyy;
	}

	public void setSwyy(String swyy) {
		this.swyy = swyy;
	}

	public Short getSxfy() {
		return this.sxfy;
	}

	public void setSxfy(short sxfy) {
		this.sxfy = sxfy;
	}

	public BigDecimal getSxhxb() {
		return this.sxhxb;
	}

	public void setSxhxb(BigDecimal sxhxb) {
		this.sxhxb = sxhxb;
	}

	public BigDecimal getSxqt() {
		return this.sxqt;
	}

	public void setSxqt(BigDecimal sxqt) {
		this.sxqt = sxqt;
	}

	public BigDecimal getSxqx() {
		return this.sxqx;
	}

	public void setSxqx(BigDecimal sxqx) {
		this.sxqx = sxqx;
	}

	public BigDecimal getSxxj() {
		return this.sxxj;
	}

	public void setSxxj(BigDecimal sxxj) {
		this.sxxj = sxxj;
	}

	public BigDecimal getSxxxb() {
		return this.sxxxb;
	}

	public void setSxxxb(BigDecimal sxxxb) {
		this.sxxxb = sxxxb;
	}

	public Short getSyfy() {
		return this.syfy;
	}

	public void setSyfy(short syfy) {
		this.syfy = syfy;
	}

	public String getSzqx() {
		return this.szqx;
	}

	public void setSzqx(String szqx) {
		this.szqx = szqx;
	}

	public Short getWycj() {
		return this.wycj;
	}

	public void setWycj(short wycj) {
		this.wycj = wycj;
	}

	public Short getWyzz() {
		return this.wyzz;
	}

	public void setWyzz(short wyzz) {
		this.wyzz = wyzz;
	}

	public Short getWzjzyn() {
		return this.wzjzyn;
	}

	public void setWzjzyn(short wzjzyn) {
		this.wzjzyn = wzjzyn;
	}

	public Short getWzjzyn1() {
		return this.wzjzyn1;
	}

	public void setWzjzyn1(short wzjzyn1) {
		this.wzjzyn1 = wzjzyn1;
	}

	public Short getWzjzyn2() {
		return this.wzjzyn2;
	}

	public void setWzjzyn2(short wzjzyn2) {
		this.wzjzyn2 = wzjzyn2;
	}

	public String getXNo() {
		return this.xNo;
	}

	public void setXNo(String xNo) {
		this.xNo = xNo;
	}

	public String getXsecstz() {
		return this.xsecstz;
	}

	public void setXsecstz(String xsecstz) {
		this.xsecstz = xsecstz;
	}

	public String getXserytz() {
		return this.xserytz;
	}

	public void setXserytz(String xserytz) {
		this.xserytz = xserytz;
	}

	public Short getXx() {
		return this.xx;
	}

	public void setXx(short xx) {
		this.xx = xx;
	}

	public Short getYqgl() {
		return this.yqgl;
	}

	public void setYqgl(short yqgl) {
		this.yqgl = yqgl;
	}

	public String getYsbm1() {
		return this.ysbm1;
	}

	public void setYsbm1(String ysbm1) {
		this.ysbm1 = ysbm1;
	}

	public String getYsbm2() {
		return this.ysbm2;
	}

	public void setYsbm2(String ysbm2) {
		this.ysbm2 = ysbm2;
	}

	public String getYsbm3() {
		return this.ysbm3;
	}

	public void setYsbm3(String ysbm3) {
		this.ysbm3 = ysbm3;
	}

	public String getYsbm4() {
		return this.ysbm4;
	}

	public void setYsbm4(String ysbm4) {
		this.ysbm4 = ysbm4;
	}

	public String getYsbm5() {
		return this.ysbm5;
	}

	public void setYsbm5(String ysbm5) {
		this.ysbm5 = ysbm5;
	}

	public String getYsbm6() {
		return this.ysbm6;
	}

	public void setYsbm6(String ysbm6) {
		this.ysbm6 = ysbm6;
	}

	public String getYsbm7() {
		return this.ysbm7;
	}

	public void setYsbm7(String ysbm7) {
		this.ysbm7 = ysbm7;
	}

	public Short getYwgm() {
		return this.ywgm;
	}

	public void setYwgm(short ywgm) {
		this.ywgm = ywgm;
	}

	public String getYztips() {
		return this.yztips;
	}

	public void setYztips(String yztips) {
		this.yztips = yztips;
	}

	public String getZjpfy() {
		return this.zjpfy;
	}

	public void setZjpfy(String zjpfy) {
		this.zjpfy = zjpfy;
	}

	public String getZkhs() {
		return this.zkhs;
	}

	public void setZkhs(String zkhs) {
		this.zkhs = zkhs;
	}

	public String getZkqk() {
		return this.zkqk;
	}

	public void setZkqk(String zkqk) {
		this.zkqk = zkqk;
	}

	public Integer getZkrq() {
		return this.zkrq;
	}

	public void setZkrq(int zkrq) {
		this.zkrq = zkrq;
	}

	public String getZkys() {
		return this.zkys;
	}

	public void setZkys(String zkys) {
		this.zkys = zkys;
	}

	public Short getZllb() {
		return this.zllb;
	}

	public void setZllb(short zllb) {
		this.zllb = zllb;
	}

	public Short getZlsb() {
		return this.zlsb;
	}

	public void setZlsb(short zlsb) {
		this.zlsb = zlsb;
	}

	public String getZrhs() {
		return this.zrhs;
	}

	public void setZrhs(String zrhs) {
		this.zrhs = zrhs;
	}

	public Short getZyjc() {
		return this.zyjc;
	}

	public void setZyjc(short zyjc) {
		this.zyjc = zyjc;
	}

	public Short getZymzcy() {
		return this.zymzcy;
	}

	public void setZymzcy(short zymzcy) {
		this.zymzcy = zymzcy;
	}

	public Short getZyrycy() {
		return this.zyrycy;
	}

	public void setZyrycy(short zyrycy) {
		this.zyrycy = zyrycy;
	}

	public Short getZyzj() {
		return this.zyzj;
	}

	public void setZyzj(short zyzj) {
		this.zyzj = zyzj;
	}

	public Short getZyzljs() {
		return this.zyzljs;
	}

	public void setZyzljs(short zyzljs) {
		this.zyzljs = zyzljs;
	}

	public Short getZzzy() {
		return this.zzzy;
	}

	public void setZzzy(short zzzy) {
		this.zzzy = zzzy;
	}

}