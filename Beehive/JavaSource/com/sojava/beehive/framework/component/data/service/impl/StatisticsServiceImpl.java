package com.sojava.beehive.framework.component.data.service.impl;

import com.sojava.beehive.framework.component.data.bean.NcovGoods;
import com.sojava.beehive.framework.component.data.dao.NcovDataDao;
import com.sojava.beehive.framework.component.data.service.StatisticsService;
import com.sojava.beehive.framework.exception.ErrorException;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Resource private NcovDataDao ncovDataDao;

	public byte[] goodsReport(Date date, String srcDept, File template) throws ErrorException {
		byte[] report = null;
		List<NcovGoods> list = ncovDataDao.goodsSumByDestType(date, srcDept, "实数");

		return report;
	}

	public NcovDataDao getNcovDataDao() {
		return ncovDataDao;
	}

	public void setNcovDataDao(NcovDataDao ncovDataDao) {
		this.ncovDataDao = ncovDataDao;
	}
}
