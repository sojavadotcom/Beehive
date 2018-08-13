package com.sojava.beehive.framework.component.imp.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tertiary_accounting_table database table.
 * 
 */
@Entity
@Table(name="tertiary_accounting_table", schema="imp")
@NamedQuery(name="TertiaryAccountingTable.findAll", query="SELECT t FROM TertiaryAccountingTable t")
public class TertiaryAccountingTable implements Serializable {
	private static final long serialVersionUID = 1157793256171766578L;

	@Id
	@SequenceGenerator(name="TERTIARY_ACCOUNTING_TABLE_ID_GENERATOR", sequenceName="TERTIARY_ACCOUNTING_TABLE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TERTIARY_ACCOUNTING_TABLE_ID_GENERATOR")
	private Integer id;

	@Column(name="create_dept_id")
	private String createDeptId;

	@Column(name="create_dept_name")
	private String createDeptName;

	@Column(name="create_time")
	private Timestamp createTime;

	@Column(name="create_user_id")
	private String createUserId;

	@Column(name="create_user_name")
	private String createUserName;

	private String date;

	private String kind;

	@Column(name="modify_dept_id")
	private String modifyDeptId;

	@Column(name="modify_dept_name")
	private String modifyDeptName;

	@Column(name="modify_time")
	private Timestamp modifyTime;

	@Column(name="modify_user_id")
	private String modifyUserId;

	@Column(name="modify_user_name")
	private String modifyUserName;

	private String title;

	private String type;

	//bi-directional many-to-one association to TertiaryAccountingTableItem
	@OneToMany(mappedBy="tertiaryAccountingTable")
	private List<TertiaryAccountingTableItem> tertiaryAccountingTableItems;

	public TertiaryAccountingTable() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateDeptId() {
		return this.createDeptId;
	}

	public void setCreateDeptId(String createDeptId) {
		this.createDeptId = createDeptId;
	}

	public String getCreateDeptName() {
		return this.createDeptName;
	}

	public void setCreateDeptName(String createDeptName) {
		this.createDeptName = createDeptName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getModifyDeptId() {
		return this.modifyDeptId;
	}

	public void setModifyDeptId(String modifyDeptId) {
		this.modifyDeptId = modifyDeptId;
	}

	public String getModifyDeptName() {
		return this.modifyDeptName;
	}

	public void setModifyDeptName(String modifyDeptName) {
		this.modifyDeptName = modifyDeptName;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return this.modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TertiaryAccountingTableItem> getTertiaryAccountingTableItems() {
		return this.tertiaryAccountingTableItems;
	}

	public void setTertiaryAccountingTableItems(List<TertiaryAccountingTableItem> tertiaryAccountingTableItems) {
		this.tertiaryAccountingTableItems = tertiaryAccountingTableItems;
	}

	public TertiaryAccountingTableItem addTertiaryAccountingTableItem(TertiaryAccountingTableItem tertiaryAccountingTableItem) {
		getTertiaryAccountingTableItems().add(tertiaryAccountingTableItem);
		tertiaryAccountingTableItem.setTertiaryAccountingTable(this);

		return tertiaryAccountingTableItem;
	}

	public TertiaryAccountingTableItem removeTertiaryAccountingTableItem(TertiaryAccountingTableItem tertiaryAccountingTableItem) {
		getTertiaryAccountingTableItems().remove(tertiaryAccountingTableItem);
		tertiaryAccountingTableItem.setTertiaryAccountingTable(null);

		return tertiaryAccountingTableItem;
	}

}