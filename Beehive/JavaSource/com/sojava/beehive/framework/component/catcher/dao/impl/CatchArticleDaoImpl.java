package com.sojava.beehive.framework.component.catcher.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.framework.component.catcher.bean.CatchArticle;
import com.sojava.beehive.framework.component.catcher.dao.CatchArticleDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.framework.util.FormatUtil;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import java.math.BigInteger;
import java.util.Date;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class CatchArticleDaoImpl extends BeehiveDaoImpl implements CatchArticleDao {
	private static final long serialVersionUID = 2573779684479243717L;

	@Override
	public Date getLastDate() throws Exception {
		Date dt = null;

		String sql = "select max(date) from public.catch_article";
		SQLQuery q = getSession().createSQLQuery(sql);
		String ret = (String) q.uniqueResult();
		if (ret != null) {
			dt = FormatUtil.parseDate(ret);
		}

		return dt;
	}

	@Override
	public void save(String kind, String title, String url, String date) throws Exception {
		CatchArticle ca = null;
		String sql = "select count(id) from public.catch_article where title=:title and url=:url and date=:date";
		SQLQuery q = getSession().createSQLQuery(sql);
		q.setString("title", title);
		q.setString("url", url);
		q.setString("date", date);
		BigInteger ret = (BigInteger) q.uniqueResult();
		if (ret.intValue() == 0) {
			ca = new CatchArticle(kind, title, url, date);
			save(ca);
		}
	}
}
