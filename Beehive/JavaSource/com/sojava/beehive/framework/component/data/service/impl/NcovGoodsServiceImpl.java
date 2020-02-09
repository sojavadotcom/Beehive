package com.sojava.beehive.framework.component.data.service.impl;

import com.sojava.beehive.framework.component.data.bean.NcovGoods;
import com.sojava.beehive.framework.component.data.dao.NcovDataDao;
import com.sojava.beehive.framework.component.data.service.NcovGoodsService;
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
public class NcovGoodsServiceImpl implements NcovGoodsService {

	@Resource private NcovDataDao ncovDataDao;

	@Override
	public NcovGoods[] list(Date date, Order[] orders, Page page) throws ErrorException {
		List<?> list = null;
		try {
			Criterion[] filter = null;
			if (date != null) {
				String startTime = FormatUtil.formatDate(date, "yyyy-MM-dd") + " 00:00:00";
				String endTime = FormatUtil.formatDate(date, "yyyy-MM-dd") + " 23:59:59";
				filter = new Criterion[] {Restrictions.between("time", FormatUtil.parseDateTime(startTime), FormatUtil.parseDateTime(endTime))};
			}
			list = ncovDataDao.query(NcovGoods.class, filter, orders, page, false);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
		return list != null ? list.toArray(new NcovGoods[0]) : null;
	}

	public NcovDataDao getNcovDataDao() {
		return ncovDataDao;
	}

	public void setNcovDataDao(NcovDataDao ncovDataDao) {
		this.ncovDataDao = ncovDataDao;
	}

}
