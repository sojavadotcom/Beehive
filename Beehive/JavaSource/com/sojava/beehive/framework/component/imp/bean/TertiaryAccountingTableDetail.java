package com.sojava.beehive.framework.component.imp.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tertiary_accounting_table_detail database table.
 * 
 */
@Entity
@Table(name="tertiary_accounting_table_detail", schema="imp")
@NamedQuery(name="TertiaryAccountingTableDetail.findAll", query="SELECT t FROM TertiaryAccountingTableDetail t")
public class TertiaryAccountingTableDetail implements Serializable {
	private static final long serialVersionUID = -7055840430754703253L;

	@Id
	@SequenceGenerator(name="TERTIARY_ACCOUNTING_TABLE_DETAIL_ID_GENERATOR", sequenceName="TERTIARY_ACCOUNTING_TABLE_DETAIL_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TERTIARY_ACCOUNTING_TABLE_DETAIL_ID_GENERATOR")
	private Integer id;

	private String item;

	private BigDecimal val;

	//bi-directional many-to-one association to TertiaryAccountingTableItem
	@ManyToOne
	@JoinColumn(name="pid")
	private TertiaryAccountingTableItem tertiaryAccountingTableItem;

	public TertiaryAccountingTableDetail() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public BigDecimal getVal() {
		return this.val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	public TertiaryAccountingTableItem getTertiaryAccountingTableItem() {
		return this.tertiaryAccountingTableItem;
	}

	public void setTertiaryAccountingTableItem(TertiaryAccountingTableItem tertiaryAccountingTableItem) {
		this.tertiaryAccountingTableItem = tertiaryAccountingTableItem;
	}

}