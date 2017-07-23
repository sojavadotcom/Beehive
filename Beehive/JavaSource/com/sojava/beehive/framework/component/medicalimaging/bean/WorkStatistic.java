package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the work_statistics database table.
 * 
 */
@Entity
@Table(name="work_statistics", schema="medicalimaging")
@NamedQuery(name="WorkStatistic.findAll", query="SELECT w FROM WorkStatistic w")
public class WorkStatistic implements Serializable {
	private static final long serialVersionUID = 4156680540914000463L;

	@Id
	@SequenceGenerator(name="WORK_STATISTICS_ID_GENERATOR", sequenceName="medicalimaging.WORK_STATSTIC_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WORK_STATISTICS_ID_GENERATOR")
	private Integer id;

	private Integer year;

	private Integer month;

	private Double budget;

	@Column(name="overtime_cost")
	private Double overtimeCost;

	@Column(name="nurse_rate")
	private Double nurseRate;

	@Column(name="nurse_cost")
	private Double nurseCost;

	@Column(name="nurse_hours")
	private Double nurseHours;

	@Column(name="performance_total")
	private Double performanceTotal;

	@Column(name="medical_rate")
	private Double medicalRate;

	@Column(name="medical_total")
	private Double medicalTotal;

	@Column(name="manage_rate")
	private Double manageRate;

	@Column(name="manage_total")
	private Double manageTotal;

	@Column(name="diagno_rate")
	private Double diagnoRate;

	@Column(name="diagno_total")
	private Double diagnoTotal;

	@Column(name="diagno_point_value")
	private Double diagnoPointValue;

	@Column(name="diagno_point_total")
	private Double diagnoPointTotal;

	@Column(name="tech_rate")
	private Double techRate;

	@Column(name="tech_total")
	private Double techTotal;

	@Column(name="tech_point_value")
	private Double techPointValue;

	@Column(name="tech_point_total")
	private Double techPointTotal;

	@Deprecated
	@Column(name="point_value")
	private Double pointValue;

	@Deprecated
	@Column(name="point_total")
	private Double pointTotal;

	@Temporal(TemporalType.DATE)
	@Column(name="begin_date")
	private Date beginDate;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String dept;

	//bi-directional many-to-one association to CalculatePerformance
	@OneToMany(mappedBy="workStatistic")
	private List<CalculatePerformance> calculatePerformances;

	//bi-directional many-to-one association to RbrvsPrice
	@OneToMany(mappedBy="workStatistic")
	private List<RbrvsPrice> rbrvsPrices;

	public WorkStatistic() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Double getBudget() {
		return this.budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Double getPointTotal() {
		return this.pointTotal;
	}

	public void setPointTotal(Double pointTotal) {
		this.pointTotal = pointTotal;
	}

	public Double getPointValue() {
		return this.pointValue;
	}

	public void setPointValue(Double pointValue) {
		this.pointValue = pointValue;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<RbrvsPrice> getRbrvsPrices() {
		return this.rbrvsPrices;
	}

	public void setRbrvsPrices(List<RbrvsPrice> rbrvsPrices) {
		this.rbrvsPrices = rbrvsPrices;
	}

	public RbrvsPrice addRbrvsPrice(RbrvsPrice rbrvsPrice) {
		getRbrvsPrices().add(rbrvsPrice);
		rbrvsPrice.setWorkStatistic(this);

		return rbrvsPrice;
	}

	public RbrvsPrice removeRbrvsPrice(RbrvsPrice rbrvsPrice) {
		getRbrvsPrices().remove(rbrvsPrice);
		rbrvsPrice.setWorkStatistic(null);

		return rbrvsPrice;
	}

	public List<CalculatePerformance> getCalculatePerformances() {
		return calculatePerformances;
	}

	public void setCalculatePerformances(
			List<CalculatePerformance> calculatePerformances) {
		this.calculatePerformances = calculatePerformances;
	}

	public Double getMedicalRate() {
		return medicalRate;
	}

	public void setMedicalRate(Double medicalRate) {
		this.medicalRate = medicalRate;
	}

	public Double getMedicalTotal() {
		return medicalTotal;
	}

	public void setMedicalTotal(Double medicalTotal) {
		this.medicalTotal = medicalTotal;
	}

	public Double getManageRate() {
		return manageRate;
	}

	public void setManageRate(Double manageRate) {
		this.manageRate = manageRate;
	}

	public Double getManageTotal() {
		return manageTotal;
	}

	public void setManageTotal(Double manageTotal) {
		this.manageTotal = manageTotal;
	}

	public Double getDiagnoRate() {
		return diagnoRate;
	}

	public void setDiagnoRate(Double diagnoRate) {
		this.diagnoRate = diagnoRate;
	}

	public Double getDiagnoTotal() {
		return diagnoTotal;
	}

	public void setDiagnoTotal(Double diagnoTotal) {
		this.diagnoTotal = diagnoTotal;
	}

	public Double getTechRate() {
		return techRate;
	}

	public void setTechRate(Double techRate) {
		this.techRate = techRate;
	}

	public Double getTechTotal() {
		return techTotal;
	}

	public void setTechTotal(Double techTotal) {
		this.techTotal = techTotal;
	}

	public Double getDiagnoPointValue() {
		return diagnoPointValue;
	}

	public void setDiagnoPointValue(Double diagnoPointValue) {
		this.diagnoPointValue = diagnoPointValue;
	}

	public Double getDiagnoPointTotal() {
		return diagnoPointTotal;
	}

	public void setDiagnoPointTotal(Double diagnoPointTotal) {
		this.diagnoPointTotal = diagnoPointTotal;
	}

	public Double getTechPointValue() {
		return techPointValue;
	}

	public void setTechPointValue(Double techPointValue) {
		this.techPointValue = techPointValue;
	}

	public Double getTechPointTotal() {
		return techPointTotal;
	}

	public void setTechPointTotal(Double techPointTotal) {
		this.techPointTotal = techPointTotal;
	}

	public Double getOvertimeCost() {
		return overtimeCost;
	}

	public void setOvertimeCost(Double overtimeCost) {
		this.overtimeCost = overtimeCost;
	}

	public Double getNurseRate() {
		return nurseRate;
	}

	public void setNurseRate(Double nurseRate) {
		this.nurseRate = nurseRate;
	}

	public Double getNurseCost() {
		return nurseCost;
	}

	public void setNurseCost(Double nurseCost) {
		this.nurseCost = nurseCost;
	}

	public Double getNurseHours() {
		return nurseHours;
	}

	public void setNurseHours(Double nurseHours) {
		this.nurseHours = nurseHours;
	}

	public Double getPerformanceTotal() {
		return performanceTotal;
	}

	public void setPerformanceTotal(Double performanceTotal) {
		this.performanceTotal = performanceTotal;
	}

}