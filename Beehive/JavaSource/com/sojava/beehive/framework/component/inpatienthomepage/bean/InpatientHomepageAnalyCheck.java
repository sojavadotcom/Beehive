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
	private static final long serialVersionUID = -477490732679336027L;

	@Id
	@SequenceGenerator(name="INPATIENT_HOMEPAGE_ANALY_CHECK_ID_GENERATOR", sequenceName="DATA_TRANSFORM.INPATIENT_HOMEPAGE_ANALY_CHECK_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INPATIENT_HOMEPAGE_ANALY_CHECK_ID_GENERATOR")
	private Integer id;

	@Column(name="check_time")
	private Date checkTime;

	private String memo;

	private String type;

	//bi-directional many-to-one association to InpatientHomepageAnaly
	@ManyToOne
	@JoinColumn(name="pid")
	private InpatientHomepageAnaly inpatientHomepageAnaly;

	public InpatientHomepageAnalyCheck() {
	}

	public InpatientHomepageAnalyCheck(int pid, String memo) {
		this.inpatientHomepageAnaly = new InpatientHomepageAnaly(pid);
		this.checkTime = new Date();
		this.memo = memo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
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

	public InpatientHomepageAnaly getInpatientHomepageAnaly() {
		return this.inpatientHomepageAnaly;
	}

	public void setInpatientHomepageAnaly(InpatientHomepageAnaly inpatientHomepageAnaly) {
		this.inpatientHomepageAnaly = inpatientHomepageAnaly;
	}

}