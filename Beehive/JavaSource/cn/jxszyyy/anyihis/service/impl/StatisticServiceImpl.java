package cn.jxszyyy.anyihis.service.impl;

import com.sojava.beehive.framework.util.FormatUtil;
import com.sojava.beehive.framework.util.RecordUtil;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.jxszyyy.anyihis.dao.StatisticDao;
import cn.jxszyyy.anyihis.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Resource private StatisticDao statisticDao;

	@Override
	public JSONArray inhospitalByDay(Date start, Date end) throws Exception {
		JSONArray result = new JSONArray();

		Calendar c = Calendar.getInstance();
		c.setTime(start);
		while (c.getTimeInMillis() <= end.getTime()) {
			int total = statisticDao.inhospitalTotal(c.getTime(), c.getTime());
			JSONObject _date = new JSONObject();
			_date.accumulate("date", FormatUtil.DATE_FORMAT.format(c.getTime()));
			_date.accumulate("value", total);
			result.add(_date);

			c.add(Calendar.DAY_OF_MONTH, 1);
		}

		return result;
	}

	@Override
	public JSONArray outpatientByDay(Date start, Date end) throws Exception {
		JSONArray result = null;

		result = new RecordUtil().generateJsonByMapping(statisticDao.outpatientTotal(start, end).toArray()).getJSONArray("items");

		return result;
	}

	public StatisticDao getStatisticDao() {
		return statisticDao;
	}

	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}

}
