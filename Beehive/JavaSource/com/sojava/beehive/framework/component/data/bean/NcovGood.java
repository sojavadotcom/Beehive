package com.sojava.beehive.framework.component.data.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ncov_goods database table.
 * 
 */
@Entity
@Table(name="ncov_goods")
@NamedQuery(name="NcovGood.findAll", query="SELECT n FROM NcovGood n")
public class NcovGood implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NCOV_GOODS_ID_GENERATOR", sequenceName="DATA.NCOV_GOODS_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NCOV_GOODS_ID_GENERATOR")
	private Integer id;

	@Column(name="\"84消毒液\"")
	private Double _4消毒液;

	private Double 防护服;

	private Double 隔离衣;

	private Double 护目镜;

	@Column(name="\"酒精75%\"")
	private Double 酒精75_;

	private Double 颗粒防护口罩;

	private Double 帽子;

	private Double 普通口罩;

	private Double 乳胶手套;

	private Double 沙方;

	private Double 手消毒剂;

	private Double 体温计;

	private Double 外科口罩;

	private Double 鞋套;

	@Column(name="dept_dest")
	private String deptDest;

	@Column(name="dept_src")
	private String deptSrc;

	@Column(name="\"N95\"")
	private Double n95;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	public NcovGood() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double get_4消毒液() {
		return this._4消毒液;
	}

	public void set_4消毒液(Double _4消毒液) {
		this._4消毒液 = _4消毒液;
	}

	public Double get防护服() {
		return this.防护服;
	}

	public void set防护服(Double 防护服) {
		this.防护服 = 防护服;
	}

	public Double get隔离衣() {
		return this.隔离衣;
	}

	public void set隔离衣(Double 隔离衣) {
		this.隔离衣 = 隔离衣;
	}

	public Double get护目镜() {
		return this.护目镜;
	}

	public void set护目镜(Double 护目镜) {
		this.护目镜 = 护目镜;
	}

	public Double get酒精75_() {
		return this.酒精75_;
	}

	public void set酒精75_(Double 酒精75_) {
		this.酒精75_ = 酒精75_;
	}

	public Double get颗粒防护口罩() {
		return this.颗粒防护口罩;
	}

	public void set颗粒防护口罩(Double 颗粒防护口罩) {
		this.颗粒防护口罩 = 颗粒防护口罩;
	}

	public Double get帽子() {
		return this.帽子;
	}

	public void set帽子(Double 帽子) {
		this.帽子 = 帽子;
	}

	public Double get普通口罩() {
		return this.普通口罩;
	}

	public void set普通口罩(Double 普通口罩) {
		this.普通口罩 = 普通口罩;
	}

	public Double get乳胶手套() {
		return this.乳胶手套;
	}

	public void set乳胶手套(Double 乳胶手套) {
		this.乳胶手套 = 乳胶手套;
	}

	public Double get沙方() {
		return this.沙方;
	}

	public void set沙方(Double 沙方) {
		this.沙方 = 沙方;
	}

	public Double get手消毒剂() {
		return this.手消毒剂;
	}

	public void set手消毒剂(Double 手消毒剂) {
		this.手消毒剂 = 手消毒剂;
	}

	public Double get体温计() {
		return this.体温计;
	}

	public void set体温计(Double 体温计) {
		this.体温计 = 体温计;
	}

	public Double get外科口罩() {
		return this.外科口罩;
	}

	public void set外科口罩(Double 外科口罩) {
		this.外科口罩 = 外科口罩;
	}

	public Double get鞋套() {
		return this.鞋套;
	}

	public void set鞋套(Double 鞋套) {
		this.鞋套 = 鞋套;
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

	public Double getN95() {
		return this.n95;
	}

	public void setN95(Double n95) {
		this.n95 = n95;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}