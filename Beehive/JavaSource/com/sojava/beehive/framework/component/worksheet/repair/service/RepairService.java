package com.sojava.beehive.framework.component.worksheet.repair.service;

import net.sf.json.JSONObject;

import com.sojava.beehive.framework.component.worksheet.repair.bean.Repair;

public interface RepairService {

	JSONObject currentRepairs(String userName, String deptName) throws Exception;
	JSONObject historyRepairs(String userName, String deptName) throws Exception;
	void save(Repair repair) throws Exception;
	JSONObject get(Repair repair) throws Exception;
	void switchStatus(String action, Repair repair) throws Exception;
}