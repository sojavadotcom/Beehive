package com.sojava.beehive.framework.component.wechat.dao;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface UserDao extends BeehiveDao {

	void signup(User user) throws ErrorException;
	void updateWxUserInfo(UserToken userToken, WxUserInfo wxUserInfo) throws ErrorException;
	void updateStaffInfo(User user) throws ErrorException;
}
