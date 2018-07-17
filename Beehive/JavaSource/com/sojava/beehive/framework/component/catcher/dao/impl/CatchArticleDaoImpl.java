package com.sojava.beehive.framework.component.catcher.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.framework.component.catcher.dao.CatchArticleDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class CatchArticleDaoImpl extends BeehiveDaoImpl implements CatchArticleDao {
	private static final long serialVersionUID = 2573779684479243717L;

}
