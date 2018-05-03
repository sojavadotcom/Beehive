package com.sojava.beehive.framework.component.wechat.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SurveyService {

	JSONArray listSurvey() throws Exception;
	JSONObject getSurvey(int id) throws Exception;
}
