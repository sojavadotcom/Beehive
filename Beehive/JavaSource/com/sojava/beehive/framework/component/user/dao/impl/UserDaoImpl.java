package com.sojava.beehive.framework.component.user.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.framework.component.user.dao.UserDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.framework.util.hibernate.CriterionUtil;
import com.sojava.beehive.framework.util.security.Encrypt;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class UserDaoImpl extends BeehiveDaoImpl implements UserDao {

	private static final long serialVersionUID = 8803625084780400164L;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> deptList(String keyword) throws Exception {
		List<String> list = (List<String>) getSession().createSQLQuery("select distinct dept_name from dic.user_info where upper(dept_name_py) like '%" + keyword.toUpperCase() + "%'").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> userList(String keyword) throws Exception {
		CriterionUtil cu = new CriterionUtil(getSession());
		cu.addCriterion(Restrictions.like("userName", "%" + keyword + "%").ignoreCase());
		List<UserInfo> list = (List<UserInfo>)cu.createCriteria(UserInfo.class, null, false).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validPassword(String userName, String password) throws Exception {
		boolean result = false;
		CriterionUtil cu = new CriterionUtil(getSession());
		cu.addCriterion(Restrictions.eq("userName", userName).ignoreCase());
		cu.addCriterion(Restrictions.eq("password", Encrypt.MD5(password)));
		List<UserInfo> list = (List<UserInfo>) cu.createCriteria(UserInfo.class, null, false).list();
		result = list.size() > 0;

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void changePassword(String userName, String password) throws Exception {
		CriterionUtil cu = new CriterionUtil(getSession());
		cu.addCriterion(Restrictions.eq("userName", userName).ignoreCase());
		List<UserInfo> list = (List<UserInfo>) cu.createCriteria(UserInfo.class, null, false).list();
		if (list.size() > 1) {
			throw new ErrorException(this.getClass(), "该用户名有多个用户，系统无法识别");
		} else if (list.size() == 0) {
			throw new ErrorException(this.getClass(), "未找到该用户");
		} else {
			UserInfo user = list.get(0);
			user.setPassword(Encrypt.MD5(password));
			getSession().update(user);
		}
	}

}
