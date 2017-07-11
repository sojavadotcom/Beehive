package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the mi_executed database table.
 * 
 */
@Embeddable
public class MiExecutedPK implements Serializable {
	private static final long serialVersionUID = -8831813472498629341L;

	private String id;

	@Column(name="medical_no")
	private String medicalNo;

	@Column(name="medical_item")
	private String medicalItem;

	public MiExecutedPK() {
		this.id = null;
		this.medicalNo = null;
		this.medicalItem = null;
	}

	public MiExecutedPK(String id, String medicalNo, String medicalItem) {
		this.id = id;
		this.medicalNo = medicalNo;
		this.medicalItem = medicalItem;
	}

	public MiExecutedPK(MiExecutedPK miExecutedPK) {
		this.id = miExecutedPK.getId();
		this.medicalNo = miExecutedPK.getMedicalNo();
		this.medicalItem = miExecutedPK.getMedicalItem();
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMedicalNo() {
		return this.medicalNo;
	}
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}

	public String getMedicalItem() {
		return this.medicalItem;
	}

	public void setMedicalItem(String medicalItem) {
		this.medicalItem = medicalItem;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MiExecutedPK)) {
			return false;
		}
		MiExecutedPK castOther = (MiExecutedPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.medicalNo.equals(castOther.medicalNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.medicalNo.hashCode();
		
		return hash;
	}
}