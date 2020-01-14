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
		Map<String, Object> main = null;
		List<Map<String, Object>> subList = null;

		CaseHistoryStandard s = caseHistoryDao.queryCaseHistoryStandardByActive();
		List<CaseHistoryStandardItem> items = s.getCaseHistoryStandardItems();
		String catalog = "";
		for (CaseHistoryStandardItem item : items) {
			String _catalog = item.getCatalog();

			if (!catalog.equals(_catalog)) {
				catalog = item.getCatalog();
				subList = new ArrayList<Map<String,Object>>();
				main = new HashMap<String, Object>();
				main.put("label", catalog);
				main.put("items", subList);
				rest.add(main);
			}
			Map<String, Object> r = new HashMap<String, Object>();
			r.put("pid", item.getCaseHistoryStandard().getId());
			r.put("id", item.getId());
			r.put("num", item.getNum());
			r.put("label", item.getLabel());
			r.put("score", item.getScore());
			subList.add(r);
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
