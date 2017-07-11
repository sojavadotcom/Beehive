package com.sojava.beehive.framework.component.worksheet.repair.dao;

import com.sojava.beehive.framework.component.worksheet.repair.bean.Repair;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface RepairDao extends BeehiveDao {

	void setStatus(Repair repair) throws Exception;
}