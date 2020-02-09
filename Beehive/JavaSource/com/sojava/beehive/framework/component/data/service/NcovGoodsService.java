package com.sojava.beehive.framework.component.data.service;

import com.sojava.beehive.framework.component.data.bean.NcovGoods;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;

import java.util.Date;

import org.hibernate.criterion.Order;

public interface NcovGoodsService {

	NcovGoods[] list(Date date, Order[] orders, Page page) throws ErrorException;
}
