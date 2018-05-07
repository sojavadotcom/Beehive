package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the survey_result_question_answer database table.
 * 
 */
@Entity
@Table(name="survey_result_question_answer", schema="wechat")
@NamedQuery(name="SurveyResultQuestionAnswer.findAll", query="SELECT s FROM SurveyResultQuestionAnswer s")
public class SurveyResultQuestionAnswer implements Serializable {
	private static final long serialVersionUID = -4420204214421291306L;

	@EmbeddedId
	private SurveyResultQuestionAnswerPK id;

	private String value;

	public SurveyResultQuestionAnswer() {
	}

	public SurveyResultQuestionAnswerPK getId() {
		return this.id;
	}

	public void setId(SurveyResultQuestionAnswerPK id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}