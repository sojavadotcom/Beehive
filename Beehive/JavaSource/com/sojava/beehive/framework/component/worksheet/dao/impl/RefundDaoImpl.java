package com.sojava.beehive.framework.component.worksheet.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.framework.component.worksheet.bean.Refund;
import com.sojava.beehive.framework.component.worksheet.dao.RefundDao;
import com.sojava.beehive.framework.define.DataFlag;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

@Repository
@Scope("prototype")
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class RefundDaoImpl extends BeehiveDaoImpl implements RefundDao {
	private static final long serialVersionUID = -152890504276424246L;

	@Override
	public void save(Refund refund) throws Exception {
		Integer id = refund.getId();
		Refund _refund = (Refund) getSession().get(Refund.class, id);
		if (_refund == null) {
			_refund = refund;
			_refund.setCreateTime(new Date());
			_refund.setDataFlag(DataFlag.add);
		} else {
			_refund.imp(refund);
			_refund.setModifyTime(new Date());
			_refund.setDataFlag(DataFlag.modify);
		}
		getSession().saveOrUpdate(_refund);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Refund> list(Criterion[] filters, Order[] orders, Page page) throws Exception {
//		return (List<Refund>) getSession().createCriteria(Refund.class).list();
		return (List<Refund>) query(Refund.class, filters, orders, page, true);
	}
}
