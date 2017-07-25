package com.sojava.beehive.framework.component.medicalimaging.service;

import com.sojava.beehive.framework.component.medicalimaging.bean.MiWorkload;
import com.sojava.beehive.framework.component.medicalimaging.bean.StaffPerformanceReport;
import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface MiPerformanceService {

	Properties calOvertime(HSSFSheet sheet) throws Exception;
	Properties calNurseWorkload(HSSFSheet sheet) throws Exception;
	void merit(Double budget, Double nurseRate, Double medicalRate, Double manageRate, int year, int month, Date begin, Date end, String dept, HSSFWorkbook overtimeBook, HSSFWorkbook nurseWorkloadBook) throws Exception;
	StaffPerformanceReport[] generateStaffPerformance(WorkStatistic workStatistic, VMiExecutedStaffPerformance[] staffPerformances, MiWorkload[] overtimes, MiWorkload[] nurseWorkloads, boolean scale) throws Exception;
	void saveStaffPerformanceReport(WorkStatistic workStatistic, VMiExecutedStaffPerformance[] staffPerformances, MiWorkload[] overtimes, MiWorkload[] nurseWorkloads, boolean scale) throws Exception;
	StaffPerformanceReport[] findStaffPerformanceReport(WorkStatistic workStatistic) throws Exception;
	HSSFWorkbook writeExcelOfStaffPerformance(File overtimeFile, File nurseFile, WorkStatistic workStatistic, VMiExecutedStaffPerformance[] list) throws Exception;
	HSSFWorkbook generateExcelOfStaffPerformance(WorkStatistic workStatistic, VMiExecutedStaffPerformance[] staffPerformances, MiWorkload[] overtimes, MiWorkload[] nurseWorkloads) throws Exception;
	HSSFWorkbook generateExcelOfStaffPerformanceReport(WorkStatistic workStatistic) throws Exception;
}
