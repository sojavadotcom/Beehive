package com.sojava.beehive.framework.component.medicalimaging.service;

import java.util.Date;

public interface MiPerformanceService {

	void merit(Double budget, Double overtimeCost, Double nurseRate, Double medicalRate, Double manageRate, int year, int month, Date begin, Date end, String dept) throws Exception;
}
