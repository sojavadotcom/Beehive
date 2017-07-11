package com.sojava.beehive.framework.component.worksheet.service.impl;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.sojava.beehive.framework.component.worksheet.bean.Refund;
import com.sojava.beehive.framework.component.worksheet.dao.RefundDao;
import com.sojava.beehive.framework.component.worksheet.service.RefundService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.util.RecordUtil;

@Service
public class RefundServiceImpl implements RefundService {

	@Resource private RefundDao refundDao;

	@Override
	public void save(Refund refund) throws Exception {

		refundDao.save(refund);
	}

	@Override
	public JSONObject get(int id) throws Exception {
		JSONObject result = null;

		Refund r = (Refund) refundDao.get(Refund.class, id);
		result = new RecordUtil().generateJsonByMapping(r);

		return result;
	}

	@Override
	public JSONObject list(Criterion[] filters, Order[] orders, Page page) throws Exception {
		JSONObject result = new RecordUtil().generateJsonByMapping(refundDao.list(filters, orders, page).toArray(new Refund[0]));

		return result;
	}

	public RefundDao getRefundDao() {
		return refundDao;
	}

	public void setRefundDao(RefundDao refundDao) {
		this.refundDao = refundDao;
	}

}
