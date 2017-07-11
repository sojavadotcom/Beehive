package com.sojava.beehive.framework.component.user.dao;

import java.util.List;

import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface UserDao extends BeehiveDao {

	List<String> deptList(String keyword) throws Exception;
	List<UserInfo> userList(String keyword) throws Exception;
	boolean validPassword(String userName, String password) throws Exception;
	void changePassword(String userName, String password) throws Exception;
}