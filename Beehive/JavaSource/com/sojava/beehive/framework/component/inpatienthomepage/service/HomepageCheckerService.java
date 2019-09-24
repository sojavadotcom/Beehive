package com.sojava.beehive.framework.component.inpatienthomepage.service;

import java.io.File;

import org.apache.commons.csv.CSVRecord;

public interface HomepageCheckerService {

	void loadCSV(File[] csvFile) throws Exception;
	void importHomepage(CSVRecord record, String kind, String type, int version) throws Exception;
	void checkColumn(String name, Object value) throws Exception;
}
