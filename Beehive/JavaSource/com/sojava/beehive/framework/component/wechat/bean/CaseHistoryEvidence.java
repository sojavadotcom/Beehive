package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the case_history_evidence photobase table.
 * 
 */
@Entity
@Table(name = "case_history_evidence", schema = "quality")
@NamedQuery(name = "CaseHistoryEvidence.findAll", query = "SELECT c FROM CaseHistoryEvidence c")
public class CaseHistoryEvidence implements Serializable {
	private static final long serialVersionUID = -1795354356510863248L;

	@Id
	@SequenceGenerator(name="CASE_HISTORY_EVIDENCE_ID_GENERATOR", sequenceName="QUALITY.CASE_HISTORY_EVIDENCE_ID", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASE_HISTORY_EVIDENCE_ID_GENERATOR")
	private Integer id;

	@Column(name="paper_num")
	private Integer paperNum;

	@Column(name="checker_name")
	private String checkerName;

	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private byte[] photo;

	@Column(name="item_label")
	private String itemLabel;

	@Column(name="item_num")
	private Integer itemNum;

	@Column(name="wx_id")
	private String wxId;

	@Column(name="wx_name")
	private String wxName;

	@Column(name="standard_id")
	private Integer standardId;

	@Column(name="item_id")
	private Integer itemId;

	public CaseHistoryEvidence() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaperNum() {
		return paperNum;
	}

	public void setPaperNum(Integer paperNum) {
		this.paperNum = paperNum;
	}

	public String getCheckerName() {
		return this.checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getItemLabel() {
		return this.itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public Integer getItemNum() {
		return this.itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public String getWxId() {
		return this.wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getWxName() {
		return this.wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}

	public Integer getStandardId() {
		return standardId;
	}

	public void setStandardId(Integer standardId) {
		this.standardId = standardId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

}