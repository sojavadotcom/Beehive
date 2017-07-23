package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mi_workload database table.
 * 
 */
@Entity
@Table(name="mi_workload", schema="medicalimaging")
@NamedQuery(name="MiWorkload.findAll", query="SELECT m FROM MiWorkload m")
public class MiWorkload implements Serializable {
	private static final long serialVersionUID = 4531037334629614412L;

	@Id
	@SequenceGenerator(name="MI_WORKLOAD_ID_GENERATOR", sequenceName="MEDICALIMAGING.MI_WORKLOAD_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MI_WORKLOAD_ID_GENERATOR")
	private Integer id;

	private Double item1;

	private Double item2;

	private Double item3;

	private Double amount;

	@Column(name="amount_by_coef")
	private Double amountByCoef;

	private String kind;

	private String type;

	@ManyToOne
	@JoinColumn(name="work_statistics_id")
	private WorkStatistic workStatistic;

	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;

	public MiWorkload() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getItem1() {
		return this.item1;
	}

	public void setItem1(Double item1) {
		this.item1 = item1;
	}

	public Double getItem2() {
		return this.item2;
	}

	public void setItem2(Double item2) {
		this.item2 = item2;
	}

	public Double getItem3() {
		return this.item3;
	}

	public void setItem3(Double item3) {
		this.item3 = item3;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountByCoef() {
		return amountByCoef;
	}

	public void setAmountByCoef(Double amountByCoef) {
		this.amountByCoef = amountByCoef;
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

	public WorkStatistic getWorkStatistic() {
		return workStatistic;
	}

	public void setWorkStatistic(WorkStatistic workStatistic) {
		this.workStatistic = workStatistic;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}