package com.sojava.beehive.framework.component.data.service;

import com.sojava.beehive.framework.exception.ErrorException;

import java.io.File;
import java.util.Date;

public interface StatisticsService {

	byte[] goodsReport(Date date, File template, String type) throws ErrorException;
	byte[] goodsReportByOutside(Date date, File template, String type) throws ErrorException;
}
