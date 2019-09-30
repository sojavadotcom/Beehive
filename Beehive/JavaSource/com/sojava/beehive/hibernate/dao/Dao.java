package com.sojava.beehive.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.framework.define.Page;

public interface Dao {

	SessionFactory getSessionFactory();
	void setSessionFactory(SessionFactory sessionFactory);

	List<?> query(Class<?> entity, Criterion[] filters, Order[] orders, Page page, boolean usableData) throws Exception;
	Object get(Class<?> entity, Serializable id) throws Exception;
	void save(Object[] entities) throws Exception;
	void save(Object entity) throws Exception;

	void setUserInfo(UserInfo userInfo);
	UserInfo getUserInfo();
}