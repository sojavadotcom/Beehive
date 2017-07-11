package com.sojava.beehive.framework.component.medicalimaging.service;

import java.io.InputStream;


public interface MiExecutedService {

	void importRecords(InputStream in, String kind) throws Exception;
}
