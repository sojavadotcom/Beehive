package com.sojava.beehive.framework.component.medicalimaging.dao;

import com.sojava.beehive.framework.component.medicalimaging.bean.MiWorkload;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface MiPerformanceDao extends BeehiveDao {

	void calRbrvsPrice(WorkStatistic workStatistic, MiWorkload[] overtimeList, MiWorkload[] nurseWorkloadList) throws Exception;
}
