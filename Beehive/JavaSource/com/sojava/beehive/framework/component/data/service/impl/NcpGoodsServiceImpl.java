package com.sojava.beehive.framework.component.data.service.impl;

import com.sojava.beehive.framework.component.data.bean.NcpGoods;
import com.sojava.beehive.framework.component.data.dao.NcpDataDao;
import com.sojava.beehive.framework.component.data.service.NcpGoodsService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class NcpGoodsServiceImpl implements NcpGoodsService {

	@Resource private NcpDataDao ncpDataDao;

	@Override
	public NcpGoods[] list(Date date, Order[] orders, Page page) throws ErrorException {
		List<?> list = null;
		try {
			Criterion[] filter = null;
			if (date != null) {
				String startTime = FormatUtil.formatDate(date, "yyyy-MM-dd") + " 00:00:00";
				String endTime = FormatUtil.formatDate(date, "yyyy-MM-dd") + " 23:59:59";
				filter = new Criterion[] {Restrictions.between("time", FormatUtil.parseDateTime(startTime), FormatUtil.parseDateTime(endTime))};
			}
			list = ncpDataDao.query(NcpGoods.class, filter, orders, page, false);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
		return list != null ? list.toArray(new NcpGoods[0]) : null;
	}

	public NcpDataDao getNcovDataDao() {
		return ncpDataDao;
	}

	public void setNcovDataDao(NcpDataDao ncpDataDao) {
		this.ncpDataDao = ncpDataDao;
	}

}
