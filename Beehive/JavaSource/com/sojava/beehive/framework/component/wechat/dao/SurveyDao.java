package com.sojava.beehive.framework.component.wechat.dao;

import com.sojava.beehive.framework.component.wechat.bean.SurveyMain;
import com.sojava.beehive.framework.define.Page;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface SurveyDao {

	List<SurveyMain> listSurvey(Criterion[] filters, Order[] orders, Page page) throws Exception;
	SurveyMain getSurvey(int id) throws Exception;
}
