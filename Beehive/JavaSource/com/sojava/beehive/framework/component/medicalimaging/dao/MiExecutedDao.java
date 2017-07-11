package com.sojava.beehive.framework.component.medicalimaging.dao;

import java.util.List;

import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecuted;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface MiExecutedDao extends BeehiveDao {

	List<MiExecuted> executedList(String keyword) throws Exception;
	Integer getStaffId(String staffName) throws Exception;
}