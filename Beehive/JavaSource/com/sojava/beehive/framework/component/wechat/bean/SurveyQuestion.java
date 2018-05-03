package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the survey_question database table.
 * 
 */
@Entity
@Table(name="survey_question", schema="wechat")
@NamedQuery(name="SurveyQuestion.findAll", query="SELECT s FROM SurveyQuestion s")
public class SurveyQuestion implements Serializable {
	private static final long serialVersionUID = 5643091684225264425L;

	@Id
	@SequenceGenerator(name="SURVEY_QUESTION_ID_GENERATOR", sequenceName="SURVEY_QUESTION_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SURVEY_QUESTION_ID_GENERATOR")
	private Integer id;

	@Column(name="data_flag")
	private Short dataFlag;

	@Column(name="input_showing")
	private Short inputShowing;

	@Column(name="multi_select")
	private Short multiSelect;

	@Column(name="option_count")
	private Short optionCount;

	private String placeholder;

	@Column(name="sub_title")
	private String subTitle;

	private String title;

	//bi-directional many-to-one association to SurveyOption
	@OneToMany(mappedBy="surveyQuestion", fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	private List<SurveyOption> surveyOptions;

	//bi-directional many-to-one association to SurveyMain
	@ManyToOne
	@JoinColumn(name="mid")
	private SurveyMain surveyMain;

	public SurveyQuestion() {
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

	public Short getMultiSelect() {
		return this.multiSelect;
	}

	public void setMultiSelect(Short multiSelect) {
		this.multiSelect = multiSelect;
	}

	public Short getOptionCount() {
		return this.optionCount;
	}

	public void setOptionCount(Short optionCount) {
		this.optionCount = optionCount;
	}

	public String getPlaceholder() {
		return this.placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getSubTitle() {
		return this.subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SurveyOption> getSurveyOptions() {
		return this.surveyOptions;
	}

	public void setSurveyOptions(List<SurveyOption> surveyOptions) {
		this.surveyOptions = surveyOptions;
	}

	public SurveyOption addSurveyOption(SurveyOption surveyOption) {
		getSurveyOptions().add(surveyOption);
		surveyOption.setSurveyQuestion(this);

		return surveyOption;
	}

	public SurveyOption removeSurveyOption(SurveyOption surveyOption) {
		getSurveyOptions().remove(surveyOption);
		surveyOption.setSurveyQuestion(null);

		return surveyOption;
	}

	public SurveyMain getSurveyMain() {
		return this.surveyMain;
	}

	public void setSurveyMain(SurveyMain surveyMain) {
		this.surveyMain = surveyMain;
	}

}