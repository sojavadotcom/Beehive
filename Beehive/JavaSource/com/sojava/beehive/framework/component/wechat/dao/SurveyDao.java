package com.sojava.beehive.framework.component.wechat.dao;

import com.sojava.beehive.framework.component.wechat.bean.SurveyMain;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface SurveyDao extends BeehiveDao {

	List<SurveyMain> listSurvey(Criterion[] filters, Order[] orders, Page page) throws Exception;
	SurveyMain getSurvey(int id) throws Exception;
}
