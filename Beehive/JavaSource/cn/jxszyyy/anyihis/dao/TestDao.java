package cn.jxszyyy.anyihis.dao;

import com.sojava.beehive.framework.common.bean.Result;
import com.sojava.beehive.framework.define.Filter;
import com.sojava.beehive.framework.define.Order;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.hibernate.dao.AnyihisDao;

public interface TestDao extends AnyihisDao {

	Result listFeesByNeedConform(Filter[] filters, Page page, Order[] order);
	void confirmApplyByInHospital(int jydh) throws Exception;
}