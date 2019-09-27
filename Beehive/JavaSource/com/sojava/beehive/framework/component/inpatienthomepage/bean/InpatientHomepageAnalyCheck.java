package com.sojava.beehive.framework.component.inpatienthomepage.bean;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the inpatient_homepage_analy_check database table.
 * 
 */
@Entity
@Table(name="inpatient_homepage_analy_check", schema="data_transform")
@NamedQuery(name="InpatientHomepageAnalyCheck.findAll", query="SELECT i FROM InpatientHomepageAnalyCheck i")
public class InpatientHomepageAnalyCheck implements Serializable {
	private static final long serialVersionUID = 8073299895163196562L;

	@EmbeddedId
	private InpatientHomepageAnalyCheckPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="check_time")
	private Date checkTime;

	private String code;

	private String message;

	private String name;

	private String type;

	private String value;

	public InpatientHomepageAnalyCheck() {
	}

	public InpatientHomepageAnalyCheck(Integer id, InpatientHomepageAnalyPK pid, String code, String name, String value, String message, String type) {
		this.id = new InpatientHomepageAnalyCheckPK(id, pid.getId(), pid.getKind(), pid.getType(), pid.getVersion());
		this.code = code;
		this.name = name;
		this.value = value;
		this.message = message;
		this.type = type;
		this.checkTime = new Date();
	}

	public InpatientHomepageAnalyCheckPK getId() {
		return this.id;
	}

	public void setId(InpatientHomepageAnalyCheckPK id) {
		this.id = id;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}