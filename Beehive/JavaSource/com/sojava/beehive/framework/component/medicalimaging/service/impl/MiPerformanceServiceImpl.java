package com.sojava.beehive.framework.component.medicalimaging.service.impl;

import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.dao.MiPerformanceDao;
import com.sojava.beehive.framework.component.medicalimaging.service.MiPerformanceService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class MiPerformanceServiceImpl implements MiPerformanceService {

	@Resource private MiPerformanceDao miPerformanceDao;

	@Override
	@SuppressWarnings("unchecked")
	public void merit(Double budget, Double medicalRate, Double manageRate, Double diagnoRate, Double techRate, int year, int month, Date begin, Date end, String dept) throws Exception {
		WorkStatistic workStatistic;
		List<WorkStatistic> list = (List<WorkStatistic>) miPerformanceDao.query(WorkStatistic.class, new Criterion[]{Restrictions.eq("year", (Integer) year), Restrictions.eq("month", (Integer) month), Restrictions.eq("dept", dept)}, null, null, false);
		if (list.size() > 0) workStatistic = list.get(0);
		else workStatistic = new WorkStatistic();
		workStatistic.setBudget(budget);
		workStatistic.setMedicalRate(medicalRate);
		workStatistic.setMedicalTotal(workStatistic.getMedicalRate()/100*workStatistic.getBudget());
		workStatistic.setManageRate(manageRate);
		workStatistic.setManageTotal(workStatistic.getManageRate()/100*workStatistic.getBudget());
		workStatistic.setDiagnoRate(diagnoRate);
		workStatistic.setDiagnoTotal(workStatistic.getDiagnoRate()/100*workStatistic.getMedicalTotal());
		workStatistic.setTechRate(techRate);
		workStatistic.setTechTotal(workStatistic.getTechRate()/100*workStatistic.getMedicalTotal());
		workStatistic.setYear(year);
		workStatistic.setMonth(month);
		workStatistic.setBeginDate(begin);
		workStatistic.setEndDate(end);
		workStatistic.setDept(dept);
		miPerformanceDao.calRbrvsPrice(workStatistic);
	}

	public MiPerformanceDao getMiPerformanceDao() {
		return miPerformanceDao;
	}

	public void setMiPerformanceDao(MiPerformanceDao miPerformanceDao) {
		this.miPerformanceDao = miPerformanceDao;
	}
}
