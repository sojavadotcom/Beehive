package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the staff_bonus database table.
 * 
 */
@Entity
@Table(name="staff_bonus", schema="medicalimaging")
@NamedQuery(name="StaffBonus.findAll", query="SELECT s FROM StaffBonus s")
public class StaffBonus implements Serializable {
	private static final long serialVersionUID = -8880788031774345173L;

	@EmbeddedId
	private StaffBonusPK id;

	private Double amount;

	private Double price;

	private Integer quantity;

	@Column(name="staff_coef")
	private Double staffCoef;

	public StaffBonus() {
	}

	public StaffBonusPK getId() {
		return this.id;
	}

	public void setId(StaffBonusPK id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Double getStaffCoef() {
		return staffCoef;
	}

	public void setStaffCoef(Double staffCoef) {
		this.staffCoef = staffCoef;
	}

}