package com.sojava.beehive.framework.component.catcher.dao;

import java.util.Date;

public interface CatchArticleDao {

	Date getLastDate() throws Exception;
	void save(String kind, String title, String url, String date) throws Exception;
}
