package cn.jxszyyy.anyihis.inhospital.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ZY_BH1 database table.
 * 
 */
@Entity
@Table(name="ZY_BH1", schema="HIS")
@NamedQuery(name="ZyBh1.findAll", query="SELECT z FROM ZyBh1 z")
public class ZyBh1 implements Serializable {
	private static final long serialVersionUID = 4191284419119525947L;

	@Id
	@Column(name="BHID")
	private Integer bhid;

	@Column(name="BRSSSQ")
	private String brsssq;

	@Column(name="CSD")
	private String csd;

	@Column(name="CSD1")
	private String csd1;

	@Column(name="CSDBM")
	private String csdbm;

	@Column(name="GJ")
	private String gj;

	@Column(name="GZDW")
	private String gzdw;

	@Column(name="GZDWDH")
	private String gzdwdh;

	@Column(name="GZDWYB")
	private String gzdwyb;

	@Column(name="JG")
	private String jg;

	@Column(name="JGBM")
	private String jgbm;

	@Column(name="JTDH")
	private String jtdh;

	@Column(name="JTYB")
	private String jtyb;

	@Column(name="JTZZ")
	private String jtzz;

	@Column(name="JTZZ1")
	private String jtzz1;

	@Column(name="JTZZBM")
	private String jtzzbm;

	@Column(name="LXRDH")
	private String lxrdh;

	@Column(name="LXRDZ")
	private String lxrdz;

	@Column(name="LXRGX")
	private String lxrgx;

	@Column(name="LXRXM")
	private String lxrxm;

	@Column(name="LXRYB")
	private String lxryb;

	@Column(name="LYDQ")
	private Integer lydq;

	@Column(name="LYDQMX")
	private String lydqmx;

	@Column(name="SFZH")
	private String sfzh;

	@Column(name="XZZ")
	private String xzz;

	@Column(name="XZZ1")
	private String xzz1;

	@Column(name="XZZBM")
	private String xzzbm;

	@Column(name="XZZDH")
	private String xzzdh;

	@Column(name="XZZYB")
	private String xzzyb;

	public ZyBh1() {
	}

	public Integer getBhid() {
		return this.bhid;
	}

	public void setBhid(int bhid) {
		this.bhid = bhid;
	}

	public String getBrsssq() {
		return this.brsssq;
	}

	public void setBrsssq(String brsssq) {
		this.brsssq = brsssq;
	}

	public String getCsd() {
		return this.csd;
	}

	public void setCsd(String csd) {
		this.csd = csd;
	}

	public String getCsd1() {
		return this.csd1;
	}

	public void setCsd1(String csd1) {
		this.csd1 = csd1;
	}

	public String getCsdbm() {
		return this.csdbm;
	}

	public void setCsdbm(String csdbm) {
		this.csdbm = csdbm;
	}

	public String getGj() {
		return this.gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getGzdw() {
		return this.gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	public String getGzdwdh() {
		return this.gzdwdh;
	}

	public void setGzdwdh(String gzdwdh) {
		this.gzdwdh = gzdwdh;
	}

	public String getGzdwyb() {
		return this.gzdwyb;
	}

	public void setGzdwyb(String gzdwyb) {
		this.gzdwyb = gzdwyb;
	}

	public String getJg() {
		return this.jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJgbm() {
		return this.jgbm;
	}

	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}

	public String getJtdh() {
		return this.jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtyb() {
		return this.jtyb;
	}

	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}

	public String getJtzz() {
		return this.jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getJtzz1() {
		return this.jtzz1;
	}

	public void setJtzz1(String jtzz1) {
		this.jtzz1 = jtzz1;
	}

	public String getJtzzbm() {
		return this.jtzzbm;
	}

	public void setJtzzbm(String jtzzbm) {
		this.jtzzbm = jtzzbm;
	}

	public String getLxrdh() {
		return this.lxrdh;
	}

	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}

	public String getLxrdz() {
		return this.lxrdz;
	}

	public void setLxrdz(String lxrdz) {
		this.lxrdz = lxrdz;
	}

	public String getLxrgx() {
		return this.lxrgx;
	}

	public void setLxrgx(String lxrgx) {
		this.lxrgx = lxrgx;
	}

	public String getLxrxm() {
		return this.lxrxm;
	}

	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}

	public String getLxryb() {
		return this.lxryb;
	}

	public void setLxryb(String lxryb) {
		this.lxryb = lxryb;
	}

	public Integer getLydq() {
		return this.lydq;
	}

	public void setLydq(int lydq) {
		this.lydq = lydq;
	}

	public String getLydqmx() {
		return this.lydqmx;
	}

	public void setLydqmx(String lydqmx) {
		this.lydqmx = lydqmx;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXzz() {
		return this.xzz;
	}

	public void setXzz(String xzz) {
		this.xzz = xzz;
	}

	public String getXzz1() {
		return this.xzz1;
	}

	public void setXzz1(String xzz1) {
		this.xzz1 = xzz1;
	}

	public String getXzzbm() {
		return this.xzzbm;
	}

	public void setXzzbm(String xzzbm) {
		this.xzzbm = xzzbm;
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

}