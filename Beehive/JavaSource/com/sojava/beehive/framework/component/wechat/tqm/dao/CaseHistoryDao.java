package com.sojava.beehive.framework.component.wechat.tqm.dao;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryStandard;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import org.hibernate.criterion.Criterion;

public interface CaseHistoryDao extends BeehiveDao {

	CaseHistoryStandard queryCaseHistoryStandardByActive() throws Exception;
	CaseHistoryStandard queryCaseHistoryStandardById(int id) throws Exception;
	CaseHistoryStandard queryCaseHistoryStandard(Criterion[] filters) throws Exception;
}
