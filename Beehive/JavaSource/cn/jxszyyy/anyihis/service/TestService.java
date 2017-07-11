package cn.jxszyyy.anyihis.service;

import cn.jxszyyy.anyihis.inhospital.bean.ZyBhjfyz;

import com.sojava.beehive.framework.common.bean.Result;
import com.sojava.beehive.framework.define.Filter;
import com.sojava.beehive.framework.define.Order;
import com.sojava.beehive.framework.define.Page;

public interface TestService {

	Result getApplysByInHospitalForGrid(Filter[] filters, Page page, Order[] orders);
	Result getApplysByInHospital(Filter[] filters, Page page, Order[] orders);
	void conformTestFeeByInHospital(ZyBhjfyz[] recs);
}