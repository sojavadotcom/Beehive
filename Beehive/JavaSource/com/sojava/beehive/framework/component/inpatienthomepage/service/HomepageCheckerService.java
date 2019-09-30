package com.sojava.beehive.framework.component.inpatienthomepage.service;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;

import java.io.File;

import org.apache.commons.csv.CSVRecord;

public interface HomepageCheckerService {

	void loadCSV(File[] csvFile) throws Exception;
	void importHomepage(CSVRecord record, String kind, String type, int version) throws Exception;
	Object transValueType(String name, String value, Class<?> type) throws Exception;
	void dataVerify(InpatientHomepageAnaly homepage) throws Exception;
	void saveHomepage(InpatientHomepageAnaly[] homepageList) throws Exception;
}
