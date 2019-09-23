package com.sojava.beehive.framework.component.inpatienthomepage.service;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.DateRangeType;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnalyCheck;
import com.sojava.beehive.framework.define.Page;

import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface HomepageService {

	String[] getTypes(String kind) throws Exception;
	InpatientHomepageAnaly[] homepageList(String kind, String[] type, DateRangeType dateType, Date begin, Date end, Criterion[] filters, Order[] orders, Page page) throws Exception;
	InpatientHomepageAnalyCheck[] homepageCheckList(Integer pid) throws Exception;
}
