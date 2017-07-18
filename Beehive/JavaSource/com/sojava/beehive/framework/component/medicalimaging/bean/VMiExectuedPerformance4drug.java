package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the v_mi_exectued_performance database table.
 * 
 */
@Entity
@Table(name="v_mi_exectued_performance4drug", schema="medicalimaging")
@NamedQuery(name="VMiExectuedPerformance4drug.findAll", query="SELECT v FROM VMiExectuedPerformance4drug v")
public class VMiExectuedPerformance4drug implements Serializable {
	private static final long serialVersionUID = -9209971090389462012L;

	@Id
	private Integer id;

	private Double price;

	@Column(name="rbrvs_id")
	private Integer rbrvsId;

	@Column(name="rbrvs_name")
	private String rbrvsName;

	@Column(name="rbrvs_price_id")
	private Integer rbrvsPriceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="report_time")
	private Date reportTime;

	private String type;

	private String kind;

	private String dept;

	@Column(name="work_statistics_id")
	private Integer workStatisticsId;

	@Column(name="worker1_coef")
	private Double worker1Coef;

	@Column(name="worker1_id")
	private Integer worker1Id;

	@Column(name="worker1_name")
	private String worker1Name;

	@Column(name="worker2_coef")
	private Double worker2Coef;

	@Column(name="worker2_id")
	private Integer worker2Id;

	@Column(name="worker2_name")
	private String worker2Name;

	@Column(name="worker3_coef")
	private Double worker3Coef;

	@Column(name="worker3_id")
	private Integer worker3Id;

	@Column(name="worker3_name")
	private String worker3Name;

	public VMiExectuedPerformance4drug() {}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getRbrvsId() {
		return this.rbrvsId;
	}

	public void setRbrvsId(Integer rbrvsId) {
		this.rbrvsId = rbrvsId;
	}

	public String getRbrvsName() {
		return this.rbrvsName;
	}

	public void setRbrvsName(String rbrvsName) {
		this.rbrvsName = rbrvsName;
	}

	public Integer getRbrvsPriceId() {
		return this.rbrvsPriceId;
	}

	public void setRbrvsPriceId(Integer rbrvsPriceId) {
		this.rbrvsPriceId = rbrvsPriceId;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getWorkStatisticsId() {
		return this.workStatisticsId;
	}

	public void setWorkStatisticsId(Integer workStatisticsId) {
		this.workStatisticsId = workStatisticsId;
	}

	public Double getWorker1Coef() {
		return this.worker1Coef;
	}

	public void setWorker1Coef(Double worker1Coef) {
		this.worker1Coef = worker1Coef;
	}

	public Integer getWorker1Id() {
		return this.worker1Id;
	}

	public void setWorker1Id(Integer worker1Id) {
		this.worker1Id = worker1Id;
	}

	public String getWorker1Name() {
		return this.worker1Name;
	}

	public void setWorker1Name(String worker1Name) {
		this.worker1Name = worker1Name;
	}

	public Double getWorker2Coef() {
		return this.worker2Coef;
	}

	public void setWorker2Coef(Double worker2Coef) {
		this.worker2Coef = worker2Coef;
	}

	public Integer getWorker2Id() {
		return this.worker2Id;
	}

	public void setWorker2Id(Integer worker2Id) {
		this.worker2Id = worker2Id;
	}

	public String getWorker2Name() {
		return this.worker2Name;
	}

	public void setWorker2Name(String worker2Name) {
		this.worker2Name = worker2Name;
	}

	public Double getWorker3Coef() {
		return this.worker3Coef;
	}

	public void setWorker3Coef(Double worker3Coef) {
		this.worker3Coef = worker3Coef;
	}

	public Integer getWorker3Id() {
		return this.worker3Id;
	}

	public void setWorker3Id(Integer worker3Id) {
		this.worker3Id = worker3Id;
	}

	public String getWorker3Name() {
		return this.worker3Name;
	}

	public void setWorker3Name(String worker3Name) {
		this.worker3Name = worker3Name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}