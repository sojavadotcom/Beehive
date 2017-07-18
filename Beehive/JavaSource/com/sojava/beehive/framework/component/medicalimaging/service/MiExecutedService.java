package com.sojava.beehive.framework.component.medicalimaging.service;

import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.define.Page;

import java.io.InputStream;


public interface MiExecutedService {

	void importRecords(InputStream in, String kind) throws Exception;
	WorkStatistic findWorkStatistic(int year, int month) throws Exception;
	VMiExecutedStaffPerformance[] findStaffPerformance(WorkStatistic workStatistic, Page page, boolean scale) throws Exception;
}
