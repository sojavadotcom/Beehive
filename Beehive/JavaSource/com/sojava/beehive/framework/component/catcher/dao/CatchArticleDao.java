package com.sojava.beehive.framework.component.catcher.dao;

import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.Date;

public interface CatchArticleDao extends BeehiveDao {

	Date getLastDate() throws Exception;
	void save(String kind, String title, String url, String date) throws Exception;
}
