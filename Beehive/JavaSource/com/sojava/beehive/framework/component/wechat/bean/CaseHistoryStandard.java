package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the case_history_standard database table.
 * 
 */
@Entity
@Table(name="case_history_standard", schema = "quality")
@NamedQuery(name="CaseHistoryStandard.findAll", query="SELECT c FROM CaseHistoryStandard c")
public class CaseHistoryStandard implements Serializable {
	private static final long serialVersionUID = -6593218659629572139L;

	@Id
	@SequenceGenerator(name="CASE_HISTORY_STANDARD_ID_GENERATOR", sequenceName="QUALITY.CASE_HISTORY_STANDARD_ID", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASE_HISTORY_STANDARD_ID_GENERATOR")
	private Integer id;

	private String kind;

	private String type;

	private Integer year;

	private Integer quarter;

	private Integer month;

	private String dept;

	private String status;

	//bi-directional many-to-one association to CaseHistoryStandardItem
	@OneToMany(mappedBy="caseHistoryStandard", fetch = FetchType.LAZY)
	private List<CaseHistoryStandardItem> caseHistoryStandardItems;

	public CaseHistoryStandard() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getQuarter() {
		return this.quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<CaseHistoryStandardItem> getCaseHistoryStandardItems() {
		return this.caseHistoryStandardItems;
	}

	public void setCaseHistoryStandardItems(List<CaseHistoryStandardItem> caseHistoryStandardItems) {
		this.caseHistoryStandardItems = caseHistoryStandardItems;
	}

	public CaseHistoryStandardItem addCaseHistoryStandardItem(CaseHistoryStandardItem caseHistoryStandardItem) {
		getCaseHistoryStandardItems().add(caseHistoryStandardItem);
		caseHistoryStandardItem.setCaseHistoryStandard(this);

		return caseHistoryStandardItem;
	}

	public CaseHistoryStandardItem removeCaseHistoryStandardItem(CaseHistoryStandardItem caseHistoryStandardItem) {
		getCaseHistoryStandardItems().remove(caseHistoryStandardItem);
		caseHistoryStandardItem.setCaseHistoryStandard(null);

		return caseHistoryStandardItem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}