package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the survey_option database table.
 * 
 */
@Entity
@Table(name="survey_option", schema="wechat")
@NamedQuery(name="SurveyOption.findAll", query="SELECT s FROM SurveyOption s")
public class SurveyOption implements Serializable {
	private static final long serialVersionUID = 2955442025748452948L;

	@Id
	@SequenceGenerator(name="SURVEY_OPTION_ID_GENERATOR", sequenceName="WECHAT.SURVEY_OPTION_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SURVEY_OPTION_ID_GENERATOR")
	private Integer id;

	@Column(name="data_flag")
	private Short dataFlag;

	@Column(name="input_showing")
	private Short inputShowing;

	@Column(name="is_right")
	private Short isRight;

	private String label;

	//bi-directional many-to-one association to SurveyQuestion
	@ManyToOne
	@JoinColumn(name="qid")
	private SurveyQuestion surveyQuestion;

	public SurveyOption() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getDataFlag() {
		return this.dataFlag;
	}

	public void setDataFlag(Short dataFlag) {
		this.dataFlag = dataFlag;
	}

	public Short getInputShowing() {
		return this.inputShowing;
	}

	public void setInputShowing(Short inputShowing) {
		this.inputShowing = inputShowing;
	}

	public Short getIsRight() {
		return this.isRight;
	}

	public void setIsRight(Short isRight) {
		this.isRight = isRight;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public SurveyQuestion getSurveyQuestion() {
		return this.surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

}