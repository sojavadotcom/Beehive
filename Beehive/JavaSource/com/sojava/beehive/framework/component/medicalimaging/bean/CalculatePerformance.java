package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the calculate_performance database table.
 * 
 */
@Entity
@Table(name="calculate_performance", schema="medicalimaging")
@NamedQuery(name="CalculatePerformance.findAll", query="SELECT c FROM CalculatePerformance c")
public class CalculatePerformance implements Serializable {
	private static final long serialVersionUID = 2972253612310243496L;

	@Id
	@SequenceGenerator(name="CALCULATE_PERFORMANCE_ID_GENERATOR", sequenceName="MEDICALIMAGING.CALCULATE_PERFORMANCE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CALCULATE_PERFORMANCE_ID_GENERATOR")
	private Integer id;

	private Double amount;

	private Double price;

	private Integer quantity;

	private String type;

	private String kind;

	private String dept;

	@Column(name="worker_coef_total")
	private Double workerCoefTotal;

	@Column(name="worker_price")
	private Double workerPrice;

	@Column(name="worker1_coef")
	private Double worker1Coef;

	@Column(name="worker1_staff_id")
	private Integer worker1StaffId;

	@Column(name="worker1_total")
	private Double worker1Total;

	@Column(name="worker2_coef")
	private Double worker2Coef;

	@Column(name="worker2_staff_id")
	private Integer worker2StaffId;

	@Column(name="worker2_total")
	private Double worker2Total;

	@Column(name="worker3_coef")
	private Double worker3Coef;

	@Column(name="worker3_staff_id")
	private Integer worker3StaffId;

	@Column(name="worker3_total")
	private Double worker3Total;

	//bi-directional many-to-one association to DicRbrv
	@ManyToOne
	@JoinColumn(name="rbrvs_id")
	private DicRbrvs dicRbrvs;

	//bi-directional many-to-one association to RbrvsPrice
	@ManyToOne
	@JoinColumn(name="rbrvs_price_id")
	private RbrvsPrice rbrvsPrice;

	//bi-directional many-to-one association to WorkStatistic
	@ManyToOne
	@JoinColumn(name="work_statistics_id")
	private WorkStatistic workStatistic;

	public CalculatePerformance() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getWorkerCoefTotal() {
		return this.workerCoefTotal;
	}

	public void setWorkerCoefTotal(Double workerCoefTotal) {
		this.workerCoefTotal = workerCoefTotal;
	}

	public Double getWorkerPrice() {
		return this.workerPrice;
	}

	public void setWorkerPrice(Double workerPrice) {
		this.workerPrice = workerPrice;
	}

	public Double getWorker1Coef() {
		return this.worker1Coef;
	}

	public void setWorker1Coef(Double worker1Coef) {
		this.worker1Coef = worker1Coef;
	}

	public Integer getWorker1StaffId() {
		return this.worker1StaffId;
	}

	public void setWorker1StaffId(Integer worker1StaffId) {
		this.worker1StaffId = worker1StaffId;
	}

	public Double getWorker1Total() {
		return this.worker1Total;
	}

	public void setWorker1Total(Double worker1Total) {
		this.worker1Total = worker1Total;
	}

	public Double getWorker2Coef() {
		return this.worker2Coef;
	}

	public void setWorker2Coef(Double worker2Coef) {
		this.worker2Coef = worker2Coef;
	}

	public Integer getWorker2StaffId() {
		return this.worker2StaffId;
	}

	public void setWorker2StaffId(Integer worker2StaffId) {
		this.worker2StaffId = worker2StaffId;
	}

	public Double getWorker2Total() {
		return this.worker2Total;
	}

	public void setWorker2Total(Double worker2Total) {
		this.worker2Total = worker2Total;
	}

	public Double getWorker3Coef() {
		return this.worker3Coef;
	}

	public void setWorker3Coef(Double worker3Coef) {
		this.worker3Coef = worker3Coef;
	}

	public Integer getWorker3StaffId() {
		return this.worker3StaffId;
	}

	public void setWorker3StaffId(Integer worker3StaffId) {
		this.worker3StaffId = worker3StaffId;
	}

	public Double getWorker3Total() {
		return this.worker3Total;
	}

	public void setWorker3Total(Double worker3Total) {
		this.worker3Total = worker3Total;
	}

	public WorkStatistic getWorkStatistic() {
		return workStatistic;
	}

	public void setWorkStatistic(WorkStatistic workStatistic) {
		this.workStatistic = workStatistic;
	}

	public RbrvsPrice getRbrvsPrice() {
		return rbrvsPrice;
	}

	public void setRbrvsPrice(RbrvsPrice rbrvsPrice) {
		this.rbrvsPrice = rbrvsPrice;
	}

	public DicRbrvs getDicRbrvs() {
		return dicRbrvs;
	}

	public void setDicRbrvs(DicRbrvs dicRbrvs) {
		this.dicRbrvs = dicRbrvs;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}