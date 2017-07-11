package cn.jxszyyy.anyihis.user.dao;

import java.util.List;

import cn.jxszyyy.anyihis.dic.bean.ZdKs;
import cn.jxszyyy.anyihis.dic.bean.ZdRy;

import com.sojava.beehive.hibernate.dao.AnyihisDao;

public interface AnyihisUserDao extends AnyihisDao {

	List<ZdKs> deptList(String keyword) throws Exception;
	List<ZdRy> userList(String keyword) throws Exception;
	boolean validPassword(String userName, String password) throws Exception;
	void changePassword(String userName, String password) throws Exception;
}