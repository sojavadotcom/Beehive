package com.sojava.beehive.framework.component.data.bean;

import com.sojava.beehive.hibernate.postgresql.datatype.JsonDataUserType;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import net.sf.json.JSON;


/**
 * The persistent class for the covid_goods database table.
 * 
 */
@Entity
@Table(name="covid_goods", schema="data")
@NamedQuery(name="CovidGoods.findAll", query="SELECT c FROM CovidGoods c")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
public class CovidGoods implements Serializable {
	private static final long serialVersionUID = -1121922015861012508L;

	@Id
	@SequenceGenerator(name="COVID_GOODS_ID_GENERATOR", sequenceName="DATA.COVID_GOODS_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COVID_GOODS_ID_GENERATOR")
	private Integer id;

	private Integer amount;

	private String code;

	@Type(type = "JsonDataUserType")
	private JSON info;

	private String kind;

	private String name;

	private Double price;

	private Double total;

	private String type;

	public CovidGoods() {
	}

	public CovidGoods(int id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public JSON getInfo() {
		return this.info;
	}

	public void setInfo(JSON info) {
		this.info = info;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}