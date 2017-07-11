package cn.jxszyyy.anyihis.dao;

import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface InHospitalDao extends BeehiveDao {

	List<Map<String, Object>> findCriticalPatient(Page page) throws Exception;
	List<Map<String, Object>> findRefund(Criterion[] filters, Order[] orders, Page page) throws Exception;
}