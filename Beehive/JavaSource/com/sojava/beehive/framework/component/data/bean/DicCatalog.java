package com.sojava.beehive.framework.component.data.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dic_catalog database table.
 * 
 */
@Entity
@Table(name="dic_catalog", schema="data")
@NamedQuery(name="DicCatalog.findAll", query="SELECT d FROM DicCatalog d")
public class DicCatalog implements Serializable {
	private static final long serialVersionUID = 6339597827401739415L;

	@Id
	private Integer id;

	private String code;

	private String kind;

	private String memo;

	private String name;

	private Integer sort;

	private String type;

	public DicCatalog() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}