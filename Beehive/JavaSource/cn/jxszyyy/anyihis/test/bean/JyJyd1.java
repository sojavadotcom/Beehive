package cn.jxszyyy.anyihis.test.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the JY_JYD1 database table.
 * 
 */
@Entity
@Table(name="JY_JYD1", schema="HIS")
@NamedQuery(name="JyJyd1.findAll", query="SELECT j FROM JyJyd1 j")
public class JyJyd1 implements Serializable {
	private static final long serialVersionUID = -6448261127746155752L;

	@EmbeddedId
	private JyJyd1PK id;

	@Column(name="CJRQ")
	private Integer cjrq;

	@Column(name="CJRY")
	private String cjry;

	@Column(name="CJSHRQ")
	private Integer cjshrq;

	@Column(name="CJSHRY")
	private String cjshry;

	@Column(name="CJSHSJ")
	private Integer cjshsj;

	@Column(name="CJSJ")
	private Integer cjsj;

	@Column(name="CZRQ")
	private Integer czrq;

	@Column(name="CZRY")
	private String czry;

	@Column(name="CZSJ")
	private Integer czsj;

	@Column(name="DJ")
	private BigDecimal dj;

	@Column(name="FLAG")
	private Short flag;

	@Column(name="FY")
	private BigDecimal fy;

	@Column(name="FYLB")
	private Short fylb;

	@Column(name="SHRQ")
	private Integer shrq;

	@Column(name="SHRY")
	private String shry;

	@Column(name="SHSJ")
	private Integer shsj;

	@Column(name="SL")
	private Short sl;

	@Column(name="YZBM")
	private String yzbm;

	@Column(name="YZLX")
	private Short yzlx;

	@Column(name="ZXBZ")
	private Short zxbz;

	public JyJyd1() {}

	public JyJyd1PK getId() {
		return id;
	}

	public void setId(JyJyd1PK id) {
		this.id = id;
	}

	public Integer getCjrq() {
		return this.cjrq;
	}

	public void setCjrq(Integer cjrq) {
		this.cjrq = cjrq;
	}

	public String getCjry() {
		return this.cjry;
	}

	public void setCjry(String cjry) {
		this.cjry = cjry;
	}

	public Integer getCjshrq() {
		return this.cjshrq;
	}

	public void setCjshrq(Integer cjshrq) {
		this.cjshrq = cjshrq;
	}

	public String getCjshry() {
		return this.cjshry;
	}

	public void setCjshry(String cjshry) {
		this.cjshry = cjshry;
	}

	public Integer getCjshsj() {
		return this.cjshsj;
	}

	public void setCjshsj(Integer cjshsj) {
		this.cjshsj = cjshsj;
	}

	public Integer getCjsj() {
		return this.cjsj;
	}

	public void setCjsj(Integer cjsj) {
		this.cjsj = cjsj;
	}

	public Integer getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Integer czrq) {
		this.czrq = czrq;
	}

	public String getCzry() {
		return this.czry;
	}

	public void setCzry(String czry) {
		this.czry = czry;
	}

	public Integer getCzsj() {
		return this.czsj;
	}

	public void setCzsj(Integer czsj) {
		this.czsj = czsj;
	}

	public BigDecimal getDj() {
		return this.dj;
	}

	public void setDj(BigDecimal dj) {
		this.dj = dj;
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

	public Short getFylb() {
		return this.fylb;
	}

	public void setFylb(Short fylb) {
		this.fylb = fylb;
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

	public Integer getShsj() {
		return this.shsj;
	}

	public void setShsj(Integer shsj) {
		this.shsj = shsj;
	}

	public Short getSl() {
		return this.sl;
	}

	public void setSl(Short sl) {
		this.sl = sl;
	}

	public String getYzbm() {
		return this.yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public Short getYzlx() {
		return this.yzlx;
	}

	public void setYzlx(Short yzlx) {
		this.yzlx = yzlx;
	}

	public Short getZxbz() {
		return this.zxbz;
	}

	public void setZxbz(Short zxbz) {
		this.zxbz = zxbz;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}