package com.sojava.beehive.framework.component.wechat.tqm.dao.impl;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryStandard;
import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryStandardItem;
import com.sojava.beehive.framework.component.wechat.tqm.dao.CaseHistoryDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class CaseHistoryDaoImpl extends BeehiveDaoImpl implements CaseHistoryDao {
	private static final long serialVersionUID = -4373536040564009387L;

	@Override
	public CaseHistoryStandard queryCaseHistoryStandardByActive() throws Exception {
		return queryCaseHistoryStandard(new Criterion[] {Restrictions.eq("status", "已启用")});
	}

	@Override
	public CaseHistoryStandard queryCaseHistoryStandardById(int id) throws Exception {
		return queryCaseHistoryStandard(new Criterion[] {Restrictions.eq("id", id)});
	}

	@SuppressWarnings("unchecked")
	@Override
	public CaseHistoryStandard queryCaseHistoryStandard(Criterion[] filters) throws Exception {

		List<CaseHistoryStandard> c = (List<CaseHistoryStandard>) this.query(
				CaseHistoryStandard.class,
				filters,
				null, null, false);
		CaseHistoryStandard s = c.get(0);
		List<CaseHistoryStandardItem> items = (List<CaseHistoryStandardItem>) this.query(
				CaseHistoryStandardItem.class,
				new Criterion[] {Restrictions.eq("caseHistoryStandard.id", s.getId())},
				new Order[] {Order.asc("num")},
				null, false);
		s.setCaseHistoryStandardItems(items);

		return s;
	}
}
