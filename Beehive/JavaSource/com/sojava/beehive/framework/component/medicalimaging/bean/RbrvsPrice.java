package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the rbrvs_price database table.
 * 
 */
@Entity
@Table(name="rbrvs_price", schema="medicalimaging")
@NamedQuery(name="RbrvsPrice.findAll", query="SELECT r FROM RbrvsPrice r")
public class RbrvsPrice implements Serializable {
	private static final long serialVersionUID = 6777709206735479276L;

	@Id
	@SequenceGenerator(name="RBRVS_PRICE_ID_GENERATOR", sequenceName="medicalimaging.RBRVS_PRICE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RBRVS_PRICE_ID_GENERATOR")
	private Integer id;

	private String kind;

	private Double point;

	private Double points;

	private Double price;

	private Integer quantity;

	private String type;

	private Double amount;

	//bi-directional many-to-one association to CalculatePerformance
	@OneToMany(mappedBy="rbrvsPrice")
	private List<CalculatePerformance> calculatePerformances;

	//bi-directional many-to-one association to DicRbrv
	@ManyToOne
	@JoinColumn(name="rbrvs_id")
	private DicRbrvs dicRbrvs;

	//bi-directional many-to-one association to WorkStatistic
	@ManyToOne
	@JoinColumn(name="work_statistics_id")
	private WorkStatistic workStatistic;

	public RbrvsPrice() {}

	public RbrvsPrice(Integer id) {
		this.id = id;
	}

	public RbrvsPrice(RbrvsPrice rbrvsPrice) {
		this.id = rbrvsPrice.getId();
		this.workStatistic = rbrvsPrice.getWorkStatistic();
		this.dicRbrvs = rbrvsPrice.getDicRbrvs();
		this.point = rbrvsPrice.getPoint();
		this.points = rbrvsPrice.getPoints();
		this.price = rbrvsPrice.getPrice();
		this.amount = rbrvsPrice.getAmount();
		this.quantity = rbrvsPrice.getQuantity();
		this.type = rbrvsPrice.getType();
		this.kind = rbrvsPrice.getKind();
	}

	public RbrvsPrice(WorkStatistic workStatistic, DicRbrvs dicRbrvs, double point, double points, double price, double amount, int quantity, String type, String kind) {
		this.workStatistic = workStatistic;
		this.dicRbrvs = dicRbrvs;
		this.point = point;
		this.points = points;
		this.price = price;
		this.amount = amount;
		this.quantity = quantity;
		this.type = type;
		this.kind = kind;
	}

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

	public Double getPoint() {
		return this.point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public Double getPoints() {
		return this.points;
	}

	public void setPoints(Double points) {
		this.points = points;
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

	public WorkStatistic getWorkStatistic() {
		return this.workStatistic;
	}

	public void setWorkStatistic(WorkStatistic workStatistic) {
		this.workStatistic = workStatistic;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<CalculatePerformance> getCalculatePerformances() {
		return calculatePerformances;
	}

	public void setCalculatePerformances(List<CalculatePerformance> calculatePerformances) {
		this.calculatePerformances = calculatePerformances;
	}

	public DicRbrvs getDicRbrvs() {
		return dicRbrvs;
	}

	public void setDicRbrvs(DicRbrvs dicRbrvs) {
		this.dicRbrvs = dicRbrvs;
	}

}