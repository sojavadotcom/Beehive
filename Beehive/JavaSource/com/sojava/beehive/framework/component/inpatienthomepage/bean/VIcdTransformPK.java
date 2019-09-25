package com.sojava.beehive.framework.component.inpatienthomepage.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class VIcdTransformPK implements Serializable {
	private static final long serialVersionUID = -3423779197399482795L;

	private String code;

	private String diagno;

	public VIcdTransformPK() {
	}

	public VIcdTransformPK(String code, String diagno) {
		this.code = code;
		this.diagno = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDiagno() {
		return diagno;
	}

	public void setDiagno(String diagno) {
		this.diagno = diagno;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VIcdTransformPK)) {
			return false;
		}
		VIcdTransformPK castOther = (VIcdTransformPK) other;
		return 
			this.code.equals(castOther.code)
			&& this.diagno.equals(castOther.diagno);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.code.hashCode();
		hash = hash * prime + this.diagno.hashCode();
		
		return hash;
	}

}
