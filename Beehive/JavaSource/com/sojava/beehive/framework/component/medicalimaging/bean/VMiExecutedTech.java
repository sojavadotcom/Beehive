package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the v_mi_executed_tech database table.
 * 
 */
@Entity
@Table(name="v_mi_executed_tech", schema="medicalimaging")
@NamedQuery(name="VMiExecutedTech.findAll", query="SELECT v FROM VMiExecutedTech v")
public class VMiExecutedTech implements Serializable {
	private static final long serialVersionUID = 6315817799123490234L;

	@EmbeddedId
	private VMiExecutedTechPK id;

	@Column(name="execute_technician")
	private String executeTechnician;

	@Column(name="execute_technician_associate")
	private String executeTechnicianAssociate;

	@Column(name="execute_technician_associate_coef")
	private Double executeTechnicianAssociateCoef;

	@Column(name="execute_technician_associate_staff_id")
	private Integer executeTechnicianAssociateStaffId;

	@Column(name="execute_technician_coef")
	private Double executeTechnicianCoef;

	@Column(name="execute_technician_staff_id")
	private Integer executeTechnicianStaffId;

	private Double price;

	@Column(name="rbrvs_name")
	private String rbrvsName;

	public VMiExecutedTech() {
	}

	public VMiExecutedTechPK getId() {
		return id;
	}

	public void setId(VMiExecutedTechPK id) {
		this.id = id;
	}

	public String getExecuteTechnician() {
		return this.executeTechnician;
	}

	public void setExecuteTechnician(String executeTechnician) {
		this.executeTechnician = executeTechnician;
	}

	public String getExecuteTechnicianAssociate() {
		return this.executeTechnicianAssociate;
	}

	public void setExecuteTechnicianAssociate(String executeTechnicianAssociate) {
		this.executeTechnicianAssociate = executeTechnicianAssociate;
	}

	public Double getExecuteTechnicianAssociateCoef() {
		return this.executeTechnicianAssociateCoef;
	}

	public void setExecuteTechnicianAssociateCoef(Double executeTechnicianAssociateCoef) {
		this.executeTechnicianAssociateCoef = executeTechnicianAssociateCoef;
	}

	public Integer getExecuteTechnicianAssociateStaffId() {
		return this.executeTechnicianAssociateStaffId;
	}

	public void setExecuteTechnicianAssociateStaffId(Integer executeTechnicianAssociateStaffId) {
		this.executeTechnicianAssociateStaffId = executeTechnicianAssociateStaffId;
	}

	public Double getExecuteTechnicianCoef() {
		return this.executeTechnicianCoef;
	}

	public void setExecuteTechnicianCoef(Double executeTechnicianCoef) {
		this.executeTechnicianCoef = executeTechnicianCoef;
	}

	public Integer getExecuteTechnicianStaffId() {
		return this.executeTechnicianStaffId;
	}

	public void setExecuteTechnicianStaffId(Integer executeTechnicianStaffId) {
		this.executeTechnicianStaffId = executeTechnicianStaffId;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRbrvsName() {
		return this.rbrvsName;
	}

	public void setRbrvsName(String rbrvsName) {
		this.rbrvsName = rbrvsName;
	}

}