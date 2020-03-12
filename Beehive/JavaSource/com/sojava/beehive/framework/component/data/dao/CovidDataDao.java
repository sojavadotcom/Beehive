package com.sojava.beehive.framework.component.data.dao;

import com.sojava.beehive.framework.component.data.bean.CovidGoods;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.List;

public interface CovidDataDao extends BeehiveDao {

	List<CovidGoods> filter() throws ErrorException;
}
