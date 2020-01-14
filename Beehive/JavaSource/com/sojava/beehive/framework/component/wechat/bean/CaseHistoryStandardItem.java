package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the case_history_standard_items database table.
 * 
 */
@Entity
@Table(name="case_history_standard_items", schema = "quality")
@NamedQuery(name="CaseHistoryStandardItem.findAll", query="SELECT c FROM CaseHistoryStandardItem c")
public class CaseHistoryStandardItem implements Serializable {
	private static final long serialVersionUID = 8979743367158909542L;

	@Id
	@SequenceGenerator(name="CASE_HISTORY_STANDARD_ITEMS_ID_GENERATOR", sequenceName="QUALITY.CASE_HISTORY_STANDARD_ITEM_ID", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASE_HISTORY_STANDARD_ITEMS_ID_GENERATOR")
	private Integer id;

	private String catalog;

	private String label;

	private Integer num;

	private String score;

	//bi-directional many-to-one association to CaseHistoryStandard
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pid")
	private CaseHistoryStandard caseHistoryStandard;

	public CaseHistoryStandardItem() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatalog() {
		return this.catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public CaseHistoryStandard getCaseHistoryStandard() {
		return this.caseHistoryStandard;
	}

	public void setCaseHistoryStandard(CaseHistoryStandard caseHistoryStandard) {
		this.caseHistoryStandard = caseHistoryStandard;
	}

}