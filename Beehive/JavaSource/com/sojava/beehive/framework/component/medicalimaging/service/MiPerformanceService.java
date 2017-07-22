package com.sojava.beehive.framework.component.medicalimaging.service;

import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;

import java.io.File;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface MiPerformanceService {

	void merit(Double budget, Double overtimeCost, Double nurseRate, Double medicalRate, Double manageRate, int year, int month, Date begin, Date end, String dept) throws Exception;
	HSSFWorkbook writeExcelOfStaffPerformance(File overtimeFile, File nurseFile, WorkStatistic workStatistic, VMiExecutedStaffPerformance[] list) throws Exception;
	HSSFWorkbook generateExcelOfStaffPerformance(WorkStatistic workStatistic, VMiExecutedStaffPerformance[] list) throws Exception;
}
