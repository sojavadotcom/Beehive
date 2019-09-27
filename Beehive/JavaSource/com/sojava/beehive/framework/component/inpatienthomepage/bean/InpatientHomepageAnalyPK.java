package com.sojava.beehive.framework.component.inpatienthomepage.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the inpatient_homepage_analy database table.
 * 
 */
@Embeddable
public class InpatientHomepageAnalyPK implements Serializable {
	private static final long serialVersionUID = 801657570499562805L;

	@SequenceGenerator(name="INPATIENT_HOMEPAGE_ANALY_ID_GENERATOR", sequenceName="DATA_TRANSFORM.INPATIENT_HOMEPAGE_ANALY_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INPATIENT_HOMEPAGE_ANALY_ID_GENERATOR")
	private Integer id;

	private Integer version;

	private String kind;

	private String type;

	public InpatientHomepageAnalyPK() {
	}

	public InpatientHomepageAnalyPK(Integer id, String kind, String type, Integer version) {
		this.id = id;
		this.kind = kind;
		this.type = type;
		this.version = version;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InpatientHomepageAnalyPK)) {
			return false;
		}
		InpatientHomepageAnalyPK castOther = (InpatientHomepageAnalyPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.version.equals(castOther.version)
			&& this.kind.equals(castOther.kind)
			&& this.type.equals(castOther.type);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.version.hashCode();
		hash = hash * prime + this.kind.hashCode();
		hash = hash * prime + this.type.hashCode();
		
		return hash;
	}
}