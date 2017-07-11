package cn.jxszyyy.anyihis.bean;

import java.io.Serializable;

public class InHospitalOfDept implements Serializable {
	private static final long serialVersionUID = -1636426988950209130L;

	private String ksbm;
	private String ksmc;
	private String type;
	private int amount;

	public InHospitalOfDept() {}

	public InHospitalOfDept(String ksbm, String ksmc, String type, int amount) {
		this.ksbm = ksbm;
		this.ksmc = ksmc;
		this.type = type;
		this.amount = amount;
	}

	public String getKsbm() {
		return ksbm;
	}
	public void setKsbm(String ksbm) {
		this.ksbm = ksbm;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
