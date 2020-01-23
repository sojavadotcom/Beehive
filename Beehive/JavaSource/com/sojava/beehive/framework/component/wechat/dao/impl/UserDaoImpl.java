package com.sojava.beehive.framework.component.wechat.dao.impl;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.component.wechat.dao.UserDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class UserDaoImpl extends BeehiveDaoImpl implements UserDao {
	private static final long serialVersionUID = 5195040550292695028L;

	@SuppressWarnings("unchecked")
	@Override
	public void signup(User user) throws ErrorException {
		try {
			List<User> list = (List<User>) query(User.class,
					new Criterion[] {
						Restrictions.eq("accessToken", user.getAccessToken())
						},
					null,
					null,
					false);
			if (list.size() == 1) {
				user.setId(list.get(0).getId());
			}
			save(user);
		}
		catch(Exception ex) {
			throw new ErrorException(ex.getLocalizedMessage());
		}
	}

	@Override
	public void updateStaffInfo(User user) throws ErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWxUserInfo(UserToken userToken, WxUserInfo wxUserInfo) throws ErrorException {
		// TODO Auto-generated method stub
		
	}

}
