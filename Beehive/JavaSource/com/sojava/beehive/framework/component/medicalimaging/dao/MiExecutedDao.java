package com.sojava.beehive.framework.component.medicalimaging.dao;

import java.util.List;
import java.util.Properties;

import com.sojava.beehive.framework.component.medicalimaging.StaffCoefType;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecuted;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface MiExecutedDao extends BeehiveDao {

	Properties getProperty();
	List<MiExecuted> executedList(String keyword) throws Exception;
	Integer getStaffId(String staffName) throws Exception;
	double getStaffCoef(int staffId, StaffCoefType type) throws Exception;
}