package cn.jxszyyy.anyihis.dao;

import cn.jxszyyy.anyihis.bean.InHospitalOfDept;

import com.sojava.beehive.framework.define.Filter;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.Date;
import java.util.List;

public interface StatisticDao extends BeehiveDao {

	List<InHospitalOfDept> inhospitalOfDept(Filter[] filters, Page page) throws Exception;
	int inhospitalTotal(Date start, Date end) throws Exception;
	int currentInhospitalForTotal() throws Exception;
	List<InHospitalOfDept> currentInhospitalForDept() throws Exception;
	List<InHospitalOfDept> outpatientTotal(Date start, Date end) throws Exception;
}