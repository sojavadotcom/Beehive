package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the survey_result_question database table.
 * 
 */
@Entity
@Table(name="survey_result_question", schema="wechat")
@NamedQuery(name="SurveyResultQuestion.findAll", query="SELECT s FROM SurveyResultQuestion s")
public class SurveyResultQuestion implements Serializable {
	private static final long serialVersionUID = -6844991905834903246L;

	@EmbeddedId
	private SurveyResultQuestionPK id;

	private String text;

	public SurveyResultQuestion() {
	}

	public SurveyResultQuestionPK getId() {
		return this.id;
	}

	public void setId(SurveyResultQuestionPK id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}