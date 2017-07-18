package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the mi_executed database table.
 * 
 */
@Embeddable
public class VMiExecutedTechPK implements Serializable {
	private static final long serialVersionUID = 2652204167313121731L;

	@Column(name="work_statistics_id")
	private Integer workStatisticsId;

	@Column(name="rbrvs_price_id")
	private Integer rbrvsPriceId;

	@Column(name="rbrvs_id")
	private Integer rbrvsId;

	@Column(name="report_time")
	private Date reportTime;

	public VMiExecutedTechPK() {
	}

	public VMiExecutedTechPK(VMiExecutedTechPK miExecutedPK) {
	}

	public Integer getRbrvsPriceId() {
		return this.rbrvsPriceId;
	}

	public void setRbrvsPriceId(Integer rbrvsPriceId) {
		this.rbrvsPriceId = rbrvsPriceId;
	}

	public Integer getRbrvsId() {
		return this.rbrvsId;
	}

	public void setRbrvsId(Integer rbrvsId) {
		this.rbrvsId = rbrvsId;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VMiExecutedTechPK)) {
			return false;
		}
		VMiExecutedTechPK castOther = (VMiExecutedTechPK)other;
		return 
			this.workStatisticsId.equals(castOther.workStatisticsId)
			&& this.rbrvsPriceId.equals(castOther.rbrvsPriceId)
			&& this.rbrvsId.equals(castOther.rbrvsId)
			&& this.reportTime.equals(castOther.reportTime);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.workStatisticsId.hashCode();
		hash = hash * prime + this.rbrvsPriceId.hashCode();
		hash = hash * prime + this.rbrvsId.hashCode();
		hash = hash * prime + this.reportTime.hashCode();
		
		return hash;
	}

	public Integer getWorkStatisticsId() {
		return workStatisticsId;
	}

	public void setWorkStatisticsId(Integer workStatisticsId) {
		this.workStatisticsId = workStatisticsId;
	}
}