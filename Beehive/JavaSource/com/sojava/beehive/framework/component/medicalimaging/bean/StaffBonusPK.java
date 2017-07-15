package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the staff_bonus database table.
 * 
 */
@Embeddable
public class StaffBonusPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="work_statistics_id")
	private Integer workStatisticsId;

	@Column(name="staff_id")
	private Integer staffId;

	private String type;

	private String kind;

	private String dept;

	public StaffBonusPK() {
	}

	public StaffBonusPK(Integer workStatisticsId, Integer staffId, String type, String kind, String dept) {
		this.workStatisticsId = workStatisticsId;
		this.staffId = staffId;
		this.type = type;
		this.kind = kind;
		this.dept = dept;
	}

	public Integer getWorkStatisticsId() {
		return this.workStatisticsId;
	}

	public void setWorkStatisticsId(Integer workStatisticsId) {
		this.workStatisticsId = workStatisticsId;
	}
	public Integer getStaffId() {
		return this.staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKind() {
		return this.kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDept() {
		return this.dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StaffBonusPK)) {
			return false;
		}
		StaffBonusPK castOther = (StaffBonusPK)other;
		return 
			this.workStatisticsId.equals(castOther.workStatisticsId)
			&& this.staffId.equals(castOther.staffId)
			&& this.type.equals(castOther.type)
			&& this.kind.equals(castOther.kind)
			&& this.dept.equals(castOther.dept);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.workStatisticsId.hashCode();
		hash = hash * prime + this.staffId.hashCode();
		hash = hash * prime + this.type.hashCode();
		hash = hash * prime + this.kind.hashCode();
		hash = hash * prime + this.dept.hashCode();
		
		return hash;
	}
}