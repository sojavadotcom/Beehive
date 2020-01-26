package com.sojava.beehive.framework.component.wechat.dao.impl;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.component.wechat.dao.UserDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import java.sql.Timestamp;
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
						Restrictions.eq("accessToken", user.getAccessToken()),
						Restrictions.eq("openid", user.getOpenid()),
						Restrictions.eq("platform", user.getPlatform())
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

	@SuppressWarnings("unchecked")
	@Override
	public void updateStaffInfo(User user) throws ErrorException {
		try {
			List<User> list = (List<User>) query(User.class,
				new Criterion[] {
					Restrictions.eq("accessToken", user.getAccessToken()),
					Restrictions.eq("openid", user.getOpenid())
				},
				null,
				null,
				false);
			if (list.size() == 0) throw new ErrorException("未找到用户[token:" + user.getAccessToken() + "; openid:" + user.getOpenid() + "]");
			else if (list.size() > 1) throw new ErrorException("找到多个用户[token:" + user.getAccessToken() + "; openid:" + user.getOpenid() + "]");
			User updateUser = list.get(0);
			updateUser.setStaffId(user.getStaffId());
			updateUser.setStaffName(user.getStaffName());
			updateUser.setMobileNumber(user.getMobileNumber());
			updateUser.setDeptName(user.getDeptName());
			updateUser.setPlatform(user.getPlatform());
			updateUser.setRole(user.getRole());
			updateUser.setStatus(user.getStatus());

			getSession().update(updateUser);
		}
		catch(Exception ex) {
			throw new ErrorException(ex.getLocalizedMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateWxUserInfo(UserToken userToken, WxUserInfo wxUserInfo) throws ErrorException {
		try {
			List<User> list = (List<User>) query(User.class,
				new Criterion[] {
					Restrictions.eq("accessToken", userToken.getAccessToken()),
					Restrictions.eq("openid", userToken.getOpenid())
				},
				null,
				null,
				false);
			if (list.size() == 0) throw new ErrorException("未找到微信用户[token:" + userToken.getAccessToken() + "; openid:" + userToken.getOpenid() + "]");
			else if (list.size() > 1) throw new ErrorException("找到多个微信用户[token:" + userToken.getAccessToken() + "; openid:" + userToken.getOpenid() + "]");
			User user = list.get(0);
			//access token信息
			user.setAccessToken(userToken.getRefreshToken());
			user.setOpenid(userToken.getOpenid());
			user.setRequestTime(new Timestamp(userToken.getStart()));
			user.setExpiresIn(userToken.getExpiresIn());
			user.setExpiresTime(new Timestamp(userToken.getStart() + userToken.getExpiresIn() * 1000));
			//微信用户信息
			user.setNickname(wxUserInfo.getNickname());
			user.setSex(wxUserInfo.getSex());
			user.setProvince(wxUserInfo.getProvince());
			user.setCity(wxUserInfo.getCity());
			user.setCountry(wxUserInfo.getCountry());
			user.setHeadimgurl(wxUserInfo.getHeadimgurl());
			user.setPrivilege(wxUserInfo.getPrivilege());
			user.setUnionid(wxUserInfo.getUnionid());

			getSession().update(user);
		}
		catch(Exception ex) {
			throw new ErrorException(ex.getLocalizedMessage());
		}
	}

}
