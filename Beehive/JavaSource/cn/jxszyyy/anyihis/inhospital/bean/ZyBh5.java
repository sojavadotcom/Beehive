package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the ZY_BH5 database table.
 * 
 */
@Entity
@Table(name="ZY_BH5", schema="HIS")
@NamedQuery(name="ZyBh5.findAll", query="SELECT z FROM ZyBh5 z")
public class ZyBh5 implements Serializable {
	private static final long serialVersionUID = 4135663771441531665L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="CRBK")
	private Short crbk;

	@Column(name="FLCS")
	private Short flcs;

	@Column(name="FLFS")
	private Short flfs;

	@Column(name="FLZZ")
	private Short flzz;

	@Column(name="HLFF")
	private Short hlff;

	@Column(name="HLFS")
	private Short hlfs;

	@Column(name="QYCS")
	private Short qycs;

	@Column(name="QYFS")
	private Short qyfs;

	@Column(name="QYGY")
	private BigDecimal qygy;

	@Column(name="QYJSRQ")
	private Integer qyjsrq;

	@Column(name="QYKSRQ")
	private Integer qyksrq;

	@Column(name="QYTS")
	private Short qyts;

	@Column(name="YFCS")
	private Short yfcs;

	@Column(name="YFFS")
	private Short yffs;

	@Column(name="YFGY")
	private BigDecimal yfgy;

	@Column(name="YFJSRQ")
	private Integer yfjsrq;

	@Column(name="YFKSRQ")
	private Integer yfksrq;

	@Column(name="YFTS")
	private Short yfts;

	@Column(name="ZLBLK")
	private Short zlblk;

	@Column(name="ZLFQ")
	private String zlfq;

	@Column(name="ZLFQLX")
	private Short zlfqlx;

	@Column(name="ZLFQLX_M")
	private Short zlfqlxM;

	@Column(name="ZLFQLX_N")
	private Short zlfqlxN;

	@Column(name="ZLFQLX_T")
	private Short zlfqlxT;

	@Column(name="ZYCS")
	private Short zycs;

	@Column(name="ZYGY")
	private BigDecimal zygy;

	@Column(name="ZYJSRQ")
	private Integer zyjsrq;

	@Column(name="ZYKSRQ")
	private Integer zyksrq;

	@Column(name="ZYTS")
	private Short zyts;

	@Column(name="ZYZ")
	private String zyz;

	public ZyBh5() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public Short getCrbk() {
		return this.crbk;
	}

	public void setCrbk(short crbk) {
		this.crbk = crbk;
	}

	public Short getFlcs() {
		return this.flcs;
	}

	public void setFlcs(short flcs) {
		this.flcs = flcs;
	}

	public Short getFlfs() {
		return this.flfs;
	}

	public void setFlfs(short flfs) {
		this.flfs = flfs;
	}

	public Short getFlzz() {
		return this.flzz;
	}

	public void setFlzz(short flzz) {
		this.flzz = flzz;
	}

	public Short getHlff() {
		return this.hlff;
	}

	public void setHlff(short hlff) {
		this.hlff = hlff;
	}

	public Short getHlfs() {
		return this.hlfs;
	}

	public void setHlfs(short hlfs) {
		this.hlfs = hlfs;
	}

	public Short getQycs() {
		return this.qycs;
	}

	public void setQycs(short qycs) {
		this.qycs = qycs;
	}

	public Short getQyfs() {
		return this.qyfs;
	}

	public void setQyfs(short qyfs) {
		this.qyfs = qyfs;
	}

	public BigDecimal getQygy() {
		return this.qygy;
	}

	public void setQygy(BigDecimal qygy) {
		this.qygy = qygy;
	}

	public Integer getQyjsrq() {
		return this.qyjsrq;
	}

	public void setQyjsrq(int qyjsrq) {
		this.qyjsrq = qyjsrq;
	}

	public Integer getQyksrq() {
		return this.qyksrq;
	}

	public void setQyksrq(int qyksrq) {
		this.qyksrq = qyksrq;
	}

	public Short getQyts() {
		return this.qyts;
	}

	public void setQyts(short qyts) {
		this.qyts = qyts;
	}

	public Short getYfcs() {
		return this.yfcs;
	}

	public void setYfcs(short yfcs) {
		this.yfcs = yfcs;
	}

	public Short getYffs() {
		return this.yffs;
	}

	public void setYffs(short yffs) {
		this.yffs = yffs;
	}

	public BigDecimal getYfgy() {
		return this.yfgy;
	}

	public void setYfgy(BigDecimal yfgy) {
		this.yfgy = yfgy;
	}

	public Integer getYfjsrq() {
		return this.yfjsrq;
	}

	public void setYfjsrq(int yfjsrq) {
		this.yfjsrq = yfjsrq;
	}

	public Integer getYfksrq() {
		return this.yfksrq;
	}

	public void setYfksrq(int yfksrq) {
		this.yfksrq = yfksrq;
	}

	public Short getYfts() {
		return this.yfts;
	}

	public void setYfts(short yfts) {
		this.yfts = yfts;
	}

	public Short getZlblk() {
		return this.zlblk;
	}

	public void setZlblk(short zlblk) {
		this.zlblk = zlblk;
	}

	public String getZlfq() {
		return this.zlfq;
	}

	public void setZlfq(String zlfq) {
		this.zlfq = zlfq;
	}

	public Short getZlfqlx() {
		return this.zlfqlx;
	}

	public void setZlfqlx(short zlfqlx) {
		this.zlfqlx = zlfqlx;
	}

	public Short getZlfqlxM() {
		return this.zlfqlxM;
	}

	public void setZlfqlxM(short zlfqlxM) {
		this.zlfqlxM = zlfqlxM;
	}

	public Short getZlfqlxN() {
		return this.zlfqlxN;
	}

	public void setZlfqlxN(short zlfqlxN) {
		this.zlfqlxN = zlfqlxN;
	}

	public Short getZlfqlxT() {
		return this.zlfqlxT;
	}

	public void setZlfqlxT(short zlfqlxT) {
		this.zlfqlxT = zlfqlxT;
	}

	public Short getZycs() {
		return this.zycs;
	}

	public void setZycs(short zycs) {
		this.zycs = zycs;
	}

	public BigDecimal getZygy() {
		return this.zygy;
	}

	public void setZygy(BigDecimal zygy) {
		this.zygy = zygy;
	}

	public Integer getZyjsrq() {
		return this.zyjsrq;
	}

	public void setZyjsrq(int zyjsrq) {
		this.zyjsrq = zyjsrq;
	}

	public Integer getZyksrq() {
		return this.zyksrq;
	}

	public void setZyksrq(int zyksrq) {
		this.zyksrq = zyksrq;
	}

	public Short getZyts() {
		return this.zyts;
	}

	public void setZyts(short zyts) {
		this.zyts = zyts;
	}

	public String getZyz() {
		return this.zyz;
	}

	public void setZyz(String zyz) {
		this.zyz = zyz;
	}

}