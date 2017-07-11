package com.sojava.beehive.framework.component.medicalimaging.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the v_mi_executed_diagno database table.
 * 
 */
@Entity
@Table(name="v_mi_executed_diagno", schema="medicalimaging")
@NamedQuery(name="VMiExecutedDiagno.findAll", query="SELECT v FROM VMiExecutedDiagno v")
public class VMiExecutedDiagno implements Serializable {
	private static final long serialVersionUID = -3223399674303506860L;

	@EmbeddedId
	private VMiExecutedDiagnoPK id;

	@Column(name="execute_diagnostician")
	private String executeDiagnostician;

	@Column(name="execute_diagnostician_coef")
	private Double executeDiagnosticianCoef;

	@Column(name="execute_diagnostician_staff_id")
	private Integer executeDiagnosticianStaffId;

	@Column(name="execute_verifier")
	private String executeVerifier;

	@Column(name="execute_verifier_coef")
	private Double executeVerifierCoef;

	@Column(name="execute_verifier_staff_id")
	private Integer executeVerifierStaffId;

	private Double price;

	@Column(name="rbrvs_name")
	private String rbrvsName;

	public VMiExecutedDiagno() {
	}

	public VMiExecutedDiagnoPK getId() {
		return id;
	}

	public void setId(VMiExecutedDiagnoPK id) {
		this.id = id;
	}

	public String getExecuteDiagnostician() {
		return this.executeDiagnostician;
	}

	public void setExecuteDiagnostician(String executeDiagnostician) {
		this.executeDiagnostician = executeDiagnostician;
	}

	public Double getExecuteDiagnosticianCoef() {
		return this.executeDiagnosticianCoef;
	}

	public void setExecuteDiagnosticianCoef(Double executeDiagnosticianCoef) {
		this.executeDiagnosticianCoef = executeDiagnosticianCoef;
	}

	public Integer getExecuteDiagnosticianStaffId() {
		return this.executeDiagnosticianStaffId;
	}

	public void setExecuteDiagnosticianStaffId(Integer executeDiagnosticianStaffId) {
		this.executeDiagnosticianStaffId = executeDiagnosticianStaffId;
	}

	public String getExecuteVerifier() {
		return this.executeVerifier;
	}

	public void setExecuteVerifier(String executeVerifier) {
		this.executeVerifier = executeVerifier;
	}

	public Double getExecuteVerifierCoef() {
		return this.executeVerifierCoef;
	}

	public void setExecuteVerifierCoef(Double executeVerifierCoef) {
		this.executeVerifierCoef = executeVerifierCoef;
	}

	public Integer getExecuteVerifierStaffId() {
		return this.executeVerifierStaffId;
	}

	public void setExecuteVerifierStaffId(Integer executeVerifierStaffId) {
		this.executeVerifierStaffId = executeVerifierStaffId;
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