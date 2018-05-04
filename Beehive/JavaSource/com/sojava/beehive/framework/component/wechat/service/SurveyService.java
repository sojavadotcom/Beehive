package com.sojava.beehive.framework.component.wechat.service;

import java.util.Map;

import net.sf.json.JSONArray;

public interface SurveyService {

	JSONArray listSurvey() throws Exception;
	Map<String, Object> getSurvey(int id) throws Exception;
	void save(String data) throws Exception;
}
