package com.sojava.beehive.framework.component.worksheet.service;

import net.sf.json.JSONObject;

import com.sojava.beehive.framework.component.worksheet.bean.Refund;
import com.sojava.beehive.framework.define.Page;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface RefundService {

	void save(Refund refund) throws Exception;
	JSONObject get(int id) throws Exception;
	JSONObject list(Criterion[] filters, Order[] orders, Page page) throws Exception;
}