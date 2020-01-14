package com.sojava.beehive.framework.component.wechat.tqm.service.impl;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryStandard;
import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryStandardItem;
import com.sojava.beehive.framework.component.wechat.tqm.dao.CaseHistoryDao;
import com.sojava.beehive.framework.component.wechat.tqm.service.CaseHistoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class CaseHistoryServiceImpl implements CaseHistoryService {

	@Resource private CaseHistoryDao caseHistoryDao;

	@Override
	public List<Map<String, Object>> getPaper() throws Exception {
		List<Map<String, Object>> rest = new ArrayList<Map<String,Object>>();

		CaseHistoryStandard s = caseHistoryDao.queryCaseHistoryStandardByActive();
		List<CaseHistoryStandardItem> items = s.getCaseHistoryStandardItems();
		for (CaseHistoryStandardItem item : items) {
			Map<String, Object> r = new HashMap<String, Object>();
			r.put("id", item.getId());
			r.put("catalog", item.getCatalog());
			r.put("label", item.getLabel());
			r.put("score", item.getScore());
			rest.add(r);
		}

		return rest;
	}

	public CaseHistoryDao getCaseHistoryDao() {
		return caseHistoryDao;
	}

	public void setCaseHistoryDao(CaseHistoryDao caseHistoryDao) {
		this.caseHistoryDao = caseHistoryDao;
	}

}
