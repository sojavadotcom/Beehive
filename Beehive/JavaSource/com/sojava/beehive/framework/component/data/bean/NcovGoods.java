package com.sojava.beehive.framework.component.data.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ncov_goods database table.
 * 
 * 	字段对应名称
 *  fhf			防护服(套)
 *  n95			N95口罩
 *  gly			隔离衣(件)
 *  ptkz		普通口罩
 *  wkkz		外科口罩
 *  rjst		乳胶手套(副)
 *  mz			帽子(个)
 *  hmj			护目镜(个)
 *  xt			鞋套
 *  klfhkz		颗粒防护口罩
 *  xdy84		84消毒液
 *  jj75		酒精75%(瓶)
 *  sxdj		手消毒剂(瓶)
 *  twj			体温计(支)
 *  sf			沙方
 *  jj75_45		酒精75%(4.5L/桶)
 *  dqjcst_s	丁腈检查手套S码
 *  dqjcst_l	丁腈检查手套L码
 *  jx			胶靴
 *  
 */
@Entity
@Table(name="ncov_goods", schema="data")
@NamedQuery(name="NcovGood.findAll", query="SELECT n FROM NcovGood n")
public class NcovGoods implements Serializable {
	private static final long serialVersionUID = -7957179269103469250L;

	@Id
	@SequenceGenerator(name="NCOV_GOODS_ID_GENERATOR", sequenceName="DATA.NCOV_GOODS_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NCOV_GOODS_ID_GENERATOR")
	private Integer id;

	@Column(name="dept_dest")
	private String deptDest;

	@Column(name="dept_src")
	private String deptSrc;

	@Column(name="dept_dest_type")
	private String deptDestType;

	@Column(name="dqjcst_l")
	private Double dqjcstL;

	@Column(name="dqjcst_s")
	private Double dqjcstS;

	private Double fhf;

	private Double gly;

	private Double hmj;

	private Double jj75;

	@Column(name="jj75_45")
	private Double jj7545;

	private Double jx;

	private Double klfhkz;

	private Double mz;

	private Double n95;

	private Double ptkz;

	private Double rjst;

	private Double sf;

	private Double sxdj;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private Double twj;

	private Double wkkz;

	private Double xdy84;

	private Double xt;

	private String type;

	private String kind;

	public NcovGoods() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptDest() {
		return this.deptDest;
	}

	public void setDeptDest(String deptDest) {
		this.deptDest = deptDest;
	}

	public String getDeptSrc() {
		return this.deptSrc;
	}

	public void setDeptSrc(String deptSrc) {
		this.deptSrc = deptSrc;
	}

	public Double getDqjcstL() {
		return this.dqjcstL;
	}

	public void setDqjcstL(Double dqjcstL) {
		this.dqjcstL = dqjcstL;
	}

	public Double getDqjcstS() {
		return this.dqjcstS;
	}

	public void setDqjcstS(Double dqjcstS) {
		this.dqjcstS = dqjcstS;
	}

	public Double getFhf() {
		return this.fhf;
	}

	public void setFhf(Double fhf) {
		this.fhf = fhf;
	}

	public Double getGly() {
		return this.gly;
	}

	public void setGly(Double gly) {
		this.gly = gly;
	}

	public Double getHmj() {
		return this.hmj;
	}

	public void setHmj(Double hmj) {
		this.hmj = hmj;
	}

	public Double getJj75() {
		return this.jj75;
	}

	public void setJj75(Double jj75) {
		this.jj75 = jj75;
	}

	public Double getJj7545() {
		return this.jj7545;
	}

	public void setJj7545(Double jj7545) {
		this.jj7545 = jj7545;
	}

	public Double getJx() {
		return this.jx;
	}

	public void setJx(Double jx) {
		this.jx = jx;
	}

	public Double getKlfhkz() {
		return this.klfhkz;
	}

	public void setKlfhkz(Double klfhkz) {
		this.klfhkz = klfhkz;
	}

	public Double getMz() {
		return this.mz;
	}

	public void setMz(Double mz) {
		this.mz = mz;
	}

	public Double getN95() {
		return this.n95;
	}

	public void setN95(Double n95) {
		this.n95 = n95;
	}

	public Double getPtkz() {
		return this.ptkz;
	}

	public void setPtkz(Double ptkz) {
		this.ptkz = ptkz;
	}

	public Double getRjst() {
		return this.rjst;
	}

	public void setRjst(Double rjst) {
		this.rjst = rjst;
	}

	public Double getSf() {
		return this.sf;
	}

	public void setSf(Double sf) {
		this.sf = sf;
	}

	public Double getSxdj() {
		return this.sxdj;
	}

	public void setSxdj(Double sxdj) {
		this.sxdj = sxdj;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getTwj() {
		return this.twj;
	}

	public void setTwj(Double twj) {
		this.twj = twj;
	}

	public Double getWkkz() {
		return this.wkkz;
	}

	public void setWkkz(Double wkkz) {
		this.wkkz = wkkz;
	}

	public Double getXdy84() {
		return this.xdy84;
	}

	public void setXdy84(Double xdy84) {
		this.xdy84 = xdy84;
	}

	public Double getXt() {
		return this.xt;
	}

	public void setXt(Double xt) {
		this.xt = xt;
	}

	public String getDeptSrcType() {
		return deptDestType;
	}

	public void setDeptSrcType(String deptSrcType) {
		this.deptDestType = deptSrcType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

}