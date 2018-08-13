package com.sojava.beehive.framework.component.imp.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tertiary_accounting_table_item database table.
 * 
 */
@Entity
@Table(name="tertiary_accounting_table_item", schema="imp")
@NamedQuery(name="TertiaryAccountingTableItem.findAll", query="SELECT t FROM TertiaryAccountingTableItem t")
public class TertiaryAccountingTableItem implements Serializable {
	private static final long serialVersionUID = -4510485532116328748L;

	@Id
	@SequenceGenerator(name="TERTIARY_ACCOUNTING_TABLE_ITEM_ID_GENERATOR", sequenceName="TERTIARY_ACCOUNTING_TABLE_ITEM_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TERTIARY_ACCOUNTING_TABLE_ITEM_ID_GENERATOR")
	private Integer id;

	private String dept;

	private String name;

	private BigDecimal total;

	//bi-directional many-to-one association to TertiaryAccountingTableDetail
	@OneToMany(mappedBy="tertiaryAccountingTableItem", fetch=FetchType.EAGER)
	private List<TertiaryAccountingTableDetail> tertiaryAccountingTableDetails;

	//bi-directional many-to-one association to TertiaryAccountingTable
	@ManyToOne
	@JoinColumn(name="pid")
	private TertiaryAccountingTable tertiaryAccountingTable;

	public TertiaryAccountingTableItem() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<TertiaryAccountingTableDetail> getTertiaryAccountingTableDetails() {
		return this.tertiaryAccountingTableDetails;
	}

	public void setTertiaryAccountingTableDetails(List<TertiaryAccountingTableDetail> tertiaryAccountingTableDetails) {
		this.tertiaryAccountingTableDetails = tertiaryAccountingTableDetails;
	}

	public TertiaryAccountingTableDetail addTertiaryAccountingTableDetail(TertiaryAccountingTableDetail tertiaryAccountingTableDetail) {
		getTertiaryAccountingTableDetails().add(tertiaryAccountingTableDetail);
		tertiaryAccountingTableDetail.setTertiaryAccountingTableItem(this);

		return tertiaryAccountingTableDetail;
	}

	public TertiaryAccountingTableDetail removeTertiaryAccountingTableDetail(TertiaryAccountingTableDetail tertiaryAccountingTableDetail) {
		getTertiaryAccountingTableDetails().remove(tertiaryAccountingTableDetail);
		tertiaryAccountingTableDetail.setTertiaryAccountingTableItem(null);

		return tertiaryAccountingTableDetail;
	}

	public TertiaryAccountingTable getTertiaryAccountingTable() {
		return this.tertiaryAccountingTable;
	}

	public void setTertiaryAccountingTable(TertiaryAccountingTable tertiaryAccountingTable) {
		this.tertiaryAccountingTable = tertiaryAccountingTable;
	}

}