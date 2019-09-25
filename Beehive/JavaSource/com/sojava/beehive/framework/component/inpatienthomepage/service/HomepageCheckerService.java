package com.sojava.beehive.framework.component.inpatienthomepage.service;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;

import java.io.File;

import org.apache.commons.csv.CSVRecord;

public interface HomepageCheckerService {

	void loadCSV(File[] csvFile) throws Exception;
	void importHomepage(CSVRecord record, String kind, String type, int version) throws Exception;
	Object checkValue(String name, String value, Class<?> type) throws Exception;
	void checkColumn(InpatientHomepageAnaly homepage) throws Exception;
}
