package com.sojava.beehive.framework.component.wechat.dao.impl;

import com.sojava.beehive.framework.component.wechat.bean.SurveyMain;
import com.sojava.beehive.framework.component.wechat.bean.SurveyOption;
import com.sojava.beehive.framework.component.wechat.bean.SurveyQuestion;
import com.sojava.beehive.framework.component.wechat.dao.SurveyDao;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import java.util.Comparator;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("prototype")
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class SurveyDaoImpl extends BeehiveDaoImpl implements SurveyDao {
	private static final long serialVersionUID = -2532283659792925968L;

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyMain> listSurvey(Criterion[] filters, Order[] orders, Page page) throws Exception {

		return (List<SurveyMain>) query(SurveyMain.class, filters, orders, page, true);
	}

	@Override
	public SurveyMain getSurvey(int id) throws Exception {
		SurveyMain rest = null;

		rest = (SurveyMain) get(SurveyMain.class, id);

		for (SurveyQuestion quest: rest.getSurveyQuestions()) {
			quest.getSurveyOptions().sort(new Comparator<SurveyOption>() {
				@Override
				public int compare(SurveyOption o1, SurveyOption o2) {
					return o1.getId().compareTo(o2.getId());
				};
			});
		}
		rest.getSurveyQuestions().sort(new Comparator<SurveyQuestion>() {
			@Override
			public int compare(SurveyQuestion o1, SurveyQuestion o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});

		return rest;
	}

}
