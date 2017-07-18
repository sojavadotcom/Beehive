package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect(
		"select "
		+ "row_number() over(order by a.dept, a.kind, a.worker_id, a.type) as id,"
		+ "a.work_statistics_id,"
		+ "a.worker_id,"
		+ "a.worker_name,"
		+ "sum(a.quantity) as quantity,"
		+ "sum(a.worker_quantity) as worker_quantity,"
		+ "sum(a.worker_statis_quantity) as worker_statis_quantity,"
		+ "sum(a.point_total) as point_total,"
		+ "sum(a.point_total) * b.point_value as amount,"
		+ "b.point_value,"
		+ "a.type,"
		+ "a.kind,"
		+ "a.dept"
		+ " from ("
		+ "	select "
		+ " a.work_statistics_id,"
		+ "	w.id as worker_id,"
		+ "	w.name as worker_name,"
		+ "	sum(a.worker1_point_total) as point_total,"
		+ "	sum(a.quantity) as quantity,"
		+ "	sum(a.worker1_quantity) as worker_quantity,"
		+ "	sum(a.worker1_statis_quantity) as worker_statis_quantity,"
		+ "	'操作' as class,"
		+ "	a.type,"
		+ "	a.kind,"
		+ "	a.dept"
		+ "	 from "
		+ "	calculate_performance a,"
		+ "	staff w"
		+ "	 where "
		+ "	 a.worker1_staff_id=w.id"
		+ "	 and type='投照'"
		+ "	 and a.dept='影像科'"
		+ "	group by a.work_statistics_id,w.id,w.name,a.type,a.kind,a.dept"
		+ "	union all"
		+ "	select "
		+ " a.work_statistics_id,"
		+ "	w.id as worker_id,"
		+ "	w.name as worker_name,"
		+ "	sum(a.worker2_point_total) as point_total,"
		+ "	0 as quantity,"
		+ "	sum(a.worker2_quantity) as worker_quantity,"
		+ "	sum(a.worker2_statis_quantity) as worker_statis_quantity,"
		+ "	'辅助' as class,"
		+ "	a.type,"
		+ "	a.kind,"
		+ "	a.dept"
		+ "	 from "
		+ "	calculate_performance a,"
		+ "	staff w"
		+ "	 where "
		+ "	 a.worker2_staff_id=w.id"
		+ "	 and type='投照'"
		+ "	 and a.dept='影像科'"
		+ "	group by a.work_statistics_id,w.id,w.name,a.type,a.kind,a.dept"
		+ "	union all "
		+ "	select "
		+ " a.work_statistics_id,"
		+ "	w.id as worker_id,"
		+ "	w.name as worker_name,"
		+ "	sum(a.worker1_point_total) as point_total,"
		+ "	sum(a.quantity) as quantity,"
		+ "	sum(a.worker1_quantity) as worker_quantity,"
		+ "	sum(a.worker1_statis_quantity) as worker_statis_quantity,"
		+ "	'阅片' as class,"
		+ "	a.type,"
		+ "	a.kind,"
		+ "	a.dept"
		+ "	 from "
		+ "	calculate_performance a,"
		+ "	staff w"
		+ "	 where "
		+ "	 a.worker1_staff_id=w.id"
		+ "	 and type='诊断'"
		+ "	 and a.dept='影像科'"
		+ "	group by a.work_statistics_id,w.id,w.name,a.type,a.kind,a.dept"
		+ "	union all "
		+ "	select "
		+ " a.work_statistics_id,"
		+ "	w.id as worker_id,"
		+ "	w.name as worker_name,"
		+ "	sum(a.worker2_point_total) as point_total,"
		+ "	0 as quantity,"
		+ "	sum(a.worker2_quantity) as worker_quantity,"
		+ "	sum(a.worker2_statis_quantity) as worker_statis_quantity,"
		+ "	'审核' as class,"
		+ "	a.type,"
		+ "	a.kind,"
		+ "	a.dept"
		+ "	 from "
		+ "	calculate_performance a,"
		+ "	staff w"
		+ "	 where "
		+ "	 a.worker2_staff_id=w.id"
		+ "	 and type='诊断'"
		+ "	 and a.dept='影像科'"
		+ "	group by a.work_statistics_id,w.id,w.name,a.type,a.kind,a.dept"
		+ ") a,"
		+ "work_statistics b"
		+ " where "
		+ "a.work_statistics_id=b.id"
		+ " group by a.work_statistics_id,a.worker_id, a.worker_name, a.type, a.kind, a.dept, b.point_value"
		+ " order by a.worker_id, a.type"
	)
@NamedQuery(name="VMiExectuedWorkload.findAll", query="SELECT v FROM VMiExectuedWorkload v")
public class VMiExectuedWorkload implements Serializable {
	private static final long serialVersionUID = 3105017520125088594L;

	@Id
	private Integer id;

	@Column(name="worker_id")
	private Integer worker_id;

	@Column(name="worker_name")
	private String workerName;

	private Integer quantity;

	@Column(name="worker_quantity")
	private Double workerQuantity;

	@Column(name="worker_statis_quantity")
	private Double workerStatisQuantity;

	@Column(name="point_total")
	private Double pointTotal;

	private Double amount;

	@Column(name="point_value")
	private Double pointValue;

	private String type;

	private String kind;

	private String dept;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Integer worker_id) {
		this.worker_id = worker_id;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getWorkerQuantity() {
		return workerQuantity;
	}

	public void setWorkerQuantity(Double workerQuantity) {
		this.workerQuantity = workerQuantity;
	}

	public Double getWorkerStatisQuantity() {
		return workerStatisQuantity;
	}

	public void setWorkerStatisQuantity(Double workerStatisQuantity) {
		this.workerStatisQuantity = workerStatisQuantity;
	}

	public Double getPointTotal() {
		return pointTotal;
	}

	public void setPointTotal(Double pointTotal) {
		this.pointTotal = pointTotal;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPointValue() {
		return pointValue;
	}

	public void setPointValue(Double pointValue) {
		this.pointValue = pointValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
