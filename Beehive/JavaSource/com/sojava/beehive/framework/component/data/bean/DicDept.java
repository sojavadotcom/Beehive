package com.sojava.beehive.framework.component.data.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dic_dept database table.
 * 
 */
@Entity
@Table(name="dic_dept", schema="data")
@NamedQuery(name="DicDept.findAll", query="SELECT d FROM DicDept d")
public class DicDept implements Serializable {
	private static final long serialVersionUID = 1639002643051152019L;

	@Id
	private Integer id;

	private String name;

	private String type;

	public DicDept() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}