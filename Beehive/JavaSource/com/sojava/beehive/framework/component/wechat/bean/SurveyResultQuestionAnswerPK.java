package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the survey_result_question_answer database table.
 * 
 */
@Embeddable
public class SurveyResultQuestionAnswerPK implements Serializable {
	private static final long serialVersionUID = -811909865662818977L;

	private Integer sid;

	private Integer qid;

	private Integer oid;

	public SurveyResultQuestionAnswerPK() {
	}
	public Integer getSid() {
		return this.sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getQid() {
		return this.qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public Integer getOid() {
		return this.oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SurveyResultQuestionAnswerPK)) {
			return false;
		}
		SurveyResultQuestionAnswerPK castOther = (SurveyResultQuestionAnswerPK)other;
		return 
			this.sid.equals(castOther.sid)
			&& this.qid.equals(castOther.qid)
			&& this.oid.equals(castOther.oid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sid.hashCode();
		hash = hash * prime + this.qid.hashCode();
		hash = hash * prime + this.oid.hashCode();
		
		return hash;
	}
}