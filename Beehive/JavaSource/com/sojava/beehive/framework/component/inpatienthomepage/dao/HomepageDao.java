package com.sojava.beehive.framework.component.inpatienthomepage.dao;

import java.util.List;

import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface HomepageDao extends BeehiveDao {

	List<String> typeList(String kind) throws Exception;
}