package com.sojava.beehive.framework.component.inpatienthomepage.service;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.RecordRangeType;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnalyCheck;
import com.sojava.beehive.framework.define.Page;

import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface HomepageService {

	String[] getTypes(String kind) throws Exception;
	InpatientHomepageAnaly[] homepageList(String kind, String[] type, RecordRangeType dateType, Date begin, Date end, Criterion[] filters, Order[] orders, Page page) throws Exception;
	InpatientHomepageAnalyCheck[] homepageCheckList(Integer pid) throws Exception;
	void checkHomepage(String kind, String[] type) throws Exception;
}
