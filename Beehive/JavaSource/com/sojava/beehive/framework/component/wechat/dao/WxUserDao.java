package com.sojava.beehive.framework.component.wechat.dao;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface WxUserDao extends BeehiveDao {

	void signup(User user) throws ErrorException;
	void updateWxUserInfo(UserToken userToken, WxUserInfo wxUserInfo) throws ErrorException;
	void updateStaffInfo(User user) throws ErrorException;
	List<User> listUser(Criterion[] filters, Order[] orders, Page page, boolean release) throws Exception;
	User getUser(String openid, boolean release) throws ErrorException;
}
