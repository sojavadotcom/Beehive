package com.sojava.beehive.framework.component.worksheet.dao;

import com.sojava.beehive.framework.component.worksheet.bean.Refund;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface RefundDao extends BeehiveDao {

	void save(Refund refund) throws Exception;
	List<Refund> list(Criterion[] filters, Order[] orders, Page page) throws Exception;
}
