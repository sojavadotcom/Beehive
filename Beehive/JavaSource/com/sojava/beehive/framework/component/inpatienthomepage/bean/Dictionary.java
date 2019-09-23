package com.sojava.beehive.framework.component.inpatienthomepage.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dictionary database table.
 * 
 */
@Entity
@Table(schema="data_transform")
@NamedQuery(name="Dictionary.findAll", query="SELECT d FROM Dictionary d")
public class Dictionary implements Serializable {
	private static final long serialVersionUID = -8750605033002726331L;

	@Id
	@SequenceGenerator(name="DICTIONARY_ID_GENERATOR", sequenceName="DATA_TRANSFORM.DICTIONARY_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DICTIONARY_ID_GENERATOR")
	private Integer id;

	private String code;

	private String kind;

	private String label;

	private String memo;

	private String type;

	public Dictionary() {
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

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}