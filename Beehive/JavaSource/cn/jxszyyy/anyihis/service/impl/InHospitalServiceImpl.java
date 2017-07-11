package cn.jxszyyy.anyihis.service.impl;

import com.sojava.beehive.framework.define.Page;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import cn.jxszyyy.anyihis.dao.InHospitalDao;
import cn.jxszyyy.anyihis.service.InHospitalService;

@Service
public class InHospitalServiceImpl implements InHospitalService {

	@Resource private InHospitalDao hisInHospitalDao;

	@Override
	public List<Map<String, Object>> findCriticalPatient(Page page) throws Exception {
		return this.hisInHospitalDao.findCriticalPatient(page);
	}

	@Override
	public List<Map<String, Object>> findRefund(Criterion[] filters, Order[] orders, Page page) throws Exception {
		return this.hisInHospitalDao.findRefund(filters, orders, page);
	}

	public InHospitalDao getHisInHospitalDao() {
		return hisInHospitalDao;
	}

	public void setHisInHospitalDao(InHospitalDao hisInHospitalDao) {
		this.hisInHospitalDao = hisInHospitalDao;
	}

}
