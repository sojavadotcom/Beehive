package com.sojava.beehive.framework.component.inpatienthomepage.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the inpatient_homepage_analy_check database table.
 * 
 */
@Embeddable
public class InpatientHomepageAnalyCheckPK implements Serializable, Cloneable {
	private static final long serialVersionUID = -8721529292604221827L;

	@SequenceGenerator(name="INPATIENT_HOMEPAGE_ANALY_ID_GENERATOR", sequenceName="DATA_TRANSFORM.INPATIENT_HOMEPAGE_ANALY_CHECK_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INPATIENT_HOMEPAGE_ANALY_CHECK_ID_GENERATOR")
	private Integer id;

	private Integer pid;

	private String kind;

	private Float version;

	private String ptype;

	public InpatientHomepageAnalyCheckPK() {
	}

	public InpatientHomepageAnalyCheckPK(Integer id, Integer pid, String kind, String ptype, Float version) {
		this.id = id;
		this.pid = pid;
		this.kind = kind;
		this.ptype = ptype;
		this.version = version;
	}

	@Override
	public InpatientHomepageAnalyCheckPK clone() throws CloneNotSupportedException {
		return (InpatientHomepageAnalyCheckPK) super.clone();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Float getVersion() {
		return this.version;
	}

	public void setVersion(Float version) {
		this.version = version;
	}

	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InpatientHomepageAnalyCheckPK)) {
			return false;
		}
		InpatientHomepageAnalyCheckPK castOther = (InpatientHomepageAnalyCheckPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.pid.equals(castOther.pid)
			&& this.kind.equals(castOther.kind)
			&& this.version.equals(castOther.version)
			&& this.ptype.equals(castOther.ptype);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.pid.hashCode();
		hash = hash * prime + this.kind.hashCode();
		hash = hash * prime + this.version.hashCode();
		hash = hash * prime + this.ptype.hashCode();
		
		return hash;
	}
}