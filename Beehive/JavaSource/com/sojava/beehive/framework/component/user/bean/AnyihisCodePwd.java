package com.sojava.beehive.framework.component.user.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the anyihis_code_pwd database table.
 * 
 */
@Entity
@Table(name="anyihis_code_pwd", schema = "dic")
@NamedQuery(name="AnyihisCodePwd.findAll", query="SELECT a FROM AnyihisCodePwd a")
public class AnyihisCodePwd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name="ANYIHIS_CODE_PWD_ID_GENERATOR", sequenceName="DIC.ANYIHIS_CODE_PWD_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANYIHIS_CODE_PWD_ID_GENERATOR")
	private Integer id;

	@Column(name = "code")
	private String code;

	@Column(name = "word")
	private String word;

	@Column(name = "data_flag")
	private Short dataFlag;

	public AnyihisCodePwd() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Short getDataFlag() {
		return this.dataFlag;
	}

	public void setDataFlag(Short dataFlag) {
		this.dataFlag = dataFlag;
	}

}