package com.sojava.beehive.framework.component.wechat.service.impl;

import com.sojava.beehive.framework.component.wechat.bean.SurveyMain;
import com.sojava.beehive.framework.component.wechat.dao.SurveyDao;
import com.sojava.beehive.framework.component.wechat.service.SurveyService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.util.FormatUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Resource private SurveyDao surveyDao;

	@Override
	public JSONArray listSurvey() throws Exception {
		JSONArray result = new JSONArray();
		Page page = new Page(0, 1000);
		List<Criterion> filters = new ArrayList<Criterion>();
		filters.add(Restrictions.eq("status", "启用"));

		List<SurveyMain> list = surveyDao.listSurvey(filters.toArray(new Criterion[0]), new Order[] {Order.desc("beginTime")}, page);
		for(SurveyMain survey : list) {
			JSONObject item = new JSONObject();
			item.accumulate("id", survey.getId());
			item.accumulate("title", survey.getTitle());
			item.accumulate("subTitle", survey.getSubTitle());
			item.accumulate("beginTime", FormatUtil.DATETIME_FORMAT.format(survey.getBeginTime()));
			if (survey.getEndTime() != null) item.accumulate("endTime", FormatUtil.DATETIME_FORMAT.format(survey.getEndTime()));
			item.accumulate("kind", survey.getKind());

			result.add(item);
		}

		return result;
	}

	@Override
	public JSONObject getSurvey(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public SurveyDao getSurveyDao() {
		return surveyDao;
	}

	public void setSurveyDao(SurveyDao surveyDao) {
		this.surveyDao = surveyDao;
	}

}
