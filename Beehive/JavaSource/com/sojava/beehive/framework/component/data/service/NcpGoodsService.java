package com.sojava.beehive.framework.component.data.service;

import com.sojava.beehive.framework.component.data.bean.NcpGoods;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;

import java.util.Date;

import org.hibernate.criterion.Order;

public interface NcpGoodsService {

	NcpGoods[] list(Date date, Order[] orders, Page page) throws ErrorException;
}
