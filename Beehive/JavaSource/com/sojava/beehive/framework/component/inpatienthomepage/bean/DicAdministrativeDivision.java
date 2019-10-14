package com.sojava.beehive.framework.component.inpatienthomepage.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dic_administrative_division database table.
 * 
 */
@Entity
@Table(name="dic_administrative_division")
@NamedQuery(name="DicAdministrativeDivision.findAll", query="SELECT d FROM DicAdministrativeDivision d")
public class DicAdministrativeDivision implements Serializable {
	private static final long serialVersionUID = 652269161030560624L;

	@Id
	private Integer id;

	@Column(name="area_code")
	private String areaCode;

	private String capital;

	private String city;

	@Column(name="city_ename")
	private String cityEname;

	@Column(name="city_sname")
	private String citySname;

	private String code;

	private String dist;

	@Column(name="dist_ename")
	private String distEname;

	@Column(name="dist_sname")
	private String distSname;

	private String ename;

	@Column(name="ename_short")
	private String enameShort;

	private float latitude;

	private Integer level;

	private float longitude;

	private String memo;

	private String name;

	private String path;

	private Integer pid;

	private String postcode;

	private String prov;

	@Column(name="prov_ename")
	private String provEname;

	@Column(name="prov_sname")
	private String provSname;

	@Column(name="short_name")
	private String shortName;

	private String type;

	public DicAdministrativeDivision() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCapital() {
		return this.capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityEname() {
		return this.cityEname;
	}

	public void setCityEname(String cityEname) {
		this.cityEname = cityEname;
	}

	public String getCitySname() {
		return this.citySname;
	}

	public void setCitySname(String citySname) {
		this.citySname = citySname;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDist() {
		return this.dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getDistEname() {
		return this.distEname;
	}

	public void setDistEname(String distEname) {
		this.distEname = distEname;
	}

	public String getDistSname() {
		return this.distSname;
	}

	public void setDistSname(String distSname) {
		this.distSname = distSname;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEnameShort() {
		return this.enameShort;
	}

	public void setEnameShort(String enameShort) {
		this.enameShort = enameShort;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getProv() {
		return this.prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getProvEname() {
		return this.provEname;
	}

	public void setProvEname(String provEname) {
		this.provEname = provEname;
	}

	public String getProvSname() {
		return this.provSname;
	}

	public void setProvSname(String provSname) {
		this.provSname = provSname;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}