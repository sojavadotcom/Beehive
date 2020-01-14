package com.sojava.beehive.framework.component.wechat.tqm.dao;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryStandard;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface CaseHistoryDao extends BeehiveDao {

	CaseHistoryStandard queryCaseHistoryStandardByActive() throws Exception;
}
