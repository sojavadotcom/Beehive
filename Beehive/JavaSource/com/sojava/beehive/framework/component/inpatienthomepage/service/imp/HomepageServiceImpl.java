package com.sojava.beehive.framework.component.inpatienthomepage.service.imp;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.DateRangeType;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnalyCheck;
import com.sojava.beehive.framework.component.inpatienthomepage.dao.HomepageDao;
import com.sojava.beehive.framework.component.inpatienthomepage.service.HomepageService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class HomepageServiceImpl implements HomepageService {

	@Resource private HomepageDao homepageDao;

	@Override
	public String[] getTypes(String kind) throws Exception {

		return homepageDao.typeList(kind).toArray(new String[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public InpatientHomepageAnaly[] homepageList(String kind, String[] type, DateRangeType dateType, Date begin, Date end, Criterion[] filters, Order[] orders, Page page) throws Exception {
		List<InpatientHomepageAnaly> list = null;
		try {
			List<Criterion> _filters = new ArrayList<Criterion>();
			_filters.add(Restrictions.eq("kind", kind));
			if (type != null) {
				_filters.add(Restrictions.in("type", type));
			}
			if (dateType != null) {
				_filters.add(Restrictions.between(dateType.toString(), begin == null ? end : begin, end == null ? begin : end));
			}
			_filters.add(Restrictions.and(filters));

			list = (List<InpatientHomepageAnaly>) homepageDao.query(InpatientHomepageAnaly.class, _filters.toArray(new Criterion[0]), orders, page, false);

			return list.toArray(new InpatientHomepageAnaly[0]);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public InpatientHomepageAnalyCheck[] homepageCheckList(Integer pid) throws Exception {
		List<InpatientHomepageAnalyCheck> list = null;
		try {

			list = (List<InpatientHomepageAnalyCheck>) homepageDao.query(InpatientHomepageAnalyCheck.class, new Criterion[]{Restrictions.eq("InpatientHomepageAnaly.id", new InpatientHomepageAnaly(pid))}, new Order[]{Order.asc("id")}, null, false);

			return list.toArray(new InpatientHomepageAnalyCheck[0]);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	public HomepageDao getHomepageDao() {
		return homepageDao;
	}

	public void setHomepageDao(HomepageDao homepageDao) {
		this.homepageDao = homepageDao;
	}
}
