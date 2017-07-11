package cn.jxszyyy.anyihis.service;

import java.util.Date;

import net.sf.json.JSONArray;

public interface StatisticService {

	JSONArray inhospitalByDay(Date start, Date end) throws Exception;
	JSONArray outpatientByDay(Date start, Date end) throws Exception;
}