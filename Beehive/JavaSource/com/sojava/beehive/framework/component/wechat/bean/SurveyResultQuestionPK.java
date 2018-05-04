package com.sojava.beehive.framework.component.wechat.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the survey_result_question database table.
 * 
 */
@Embeddable
public class SurveyResultQuestionPK implements Serializable {
	private static final long serialVersionUID = -1208303981132327389L;

	private Integer sid;

	private Integer qid;

	public SurveyResultQuestionPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SurveyResultQuestionPK)) {
			return false;
		}
		SurveyResultQuestionPK castOther = (SurveyResultQuestionPK)other;
		return 
			this.sid.equals(castOther.sid)
			&& this.qid.equals(castOther.qid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sid.hashCode();
		hash = hash * prime + this.qid.hashCode();
		
		return hash;
	}
}