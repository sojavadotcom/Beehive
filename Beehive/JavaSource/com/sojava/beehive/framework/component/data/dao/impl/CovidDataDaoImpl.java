package com.sojava.beehive.framework.component.data.dao.impl;

import com.sojava.beehive.framework.component.data.bean.CovidGoods;
import com.sojava.beehive.framework.component.data.dao.CovidDataDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class CovidDataDaoImpl extends BeehiveDaoImpl implements CovidDataDao {
	private static final long serialVersionUID = -7429967644785424496L;

	@SuppressWarnings("unchecked")
	@Override
	public List<CovidGoods> filter() throws ErrorException {
		List<CovidGoods> rest = null;
		try {
			rest = (List<CovidGoods>) this.query(CovidGoods.class, null, null, null, false);
		}
		catch(Exception ex) {
			throw new ErrorException(ex);
		}

		return rest;
	}
}
