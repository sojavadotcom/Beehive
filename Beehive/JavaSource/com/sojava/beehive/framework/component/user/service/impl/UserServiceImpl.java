package com.sojava.beehive.framework.component.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.framework.component.user.dao.UserDao;
import com.sojava.beehive.framework.component.user.service.UserService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.ExceptionUtil;
import com.sojava.beehive.framework.util.RecordUtil;
import com.sojava.beehive.framework.util.security.Encrypt;

@Service
public class UserServiceImpl implements UserService {

	@Resource private UserDao userDao;

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject login(String sessionId, String userName, String password, String code, HttpServletRequest request) throws Exception {
		JSONObject result = null;
		HttpSession session = request.getSession();
		boolean accept = userDao.validPassword(userName, password);
		if (accept) {
			List<UserInfo> list = (List<UserInfo>) userDao.query(UserInfo.class, new Criterion[] {Restrictions.eq("userName", userName).ignoreCase()}, null, null, false);
			if (list.size() == 0) throw new Exception("用户名或密码错误");
			else if (list.size() > 1) throw new Exception("找到多个用户，系统无法识别");
			else {
				UserInfo user = list.get(0);
//				session.setAttribute("sessionId", sessionId);
				session.setAttribute("UserInfo", user);

				result = new RecordUtil().generateJsonByMapping(user);
				result.remove("password");
				if (user.getPassword().equalsIgnoreCase(Encrypt.MD5(""))) {
					result.put("isPInit", true);
				} else {
					result.put("isPInit", false);
				}
			}
		} else {
			throw new ErrorException(this.getClass(), "用户名或密码错误");
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean passwordIsInit(String userName) throws Exception {
		List<UserInfo> list = (List<UserInfo>) userDao.query(UserInfo.class, new Criterion[] {Restrictions.eq("userName", userName).ignoreCase()}, null, null, false);
		UserInfo user = list.get(0);
		if (user.getPassword().equalsIgnoreCase(Encrypt.MD5(""))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void logout(String sessionId, HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject nameByUserName(String userName) throws Exception {
		JSONObject result = null;
		try {
			if (userName == null || userName.trim().equals("")) throw new Exception("系统未收到用户名");
			List<UserInfo> list = (List<UserInfo>) userDao.query(UserInfo.class, new Criterion[] {Restrictions.eq("userName", userName).ignoreCase()}, null, null, false);
			if (list.size() == 0) throw new Exception("用户名错误");
			else if (list.size() > 1) throw new Exception("找到多个用户，系统无法识别");
			else {
				UserInfo user = list.get(0);
				result = new JSONObject();
				result.put("userName", user.getUserName());
				result.put("name", user.getName());
				result.put("success", true);
			}
		}
		catch(Exception ex) {
			result = new JSONObject();
			result.put("success", false);
			result.put("message", ExceptionUtil.getMessage(ex));
		}

		return result;
	}

	@Override
	public JSONArray depts(String label) throws Exception {
		JSONArray result = new JSONArray();
		if (!label.equals("")) {
			label = label.equals("*") ? "" : label;
			label = label.lastIndexOf("*") != -1 ? label.substring(0, label.length()-1) : label;
			List<String> list = userDao.deptList(label);
			for (String d : list) {
				JSONObject dept = new JSONObject();
				dept.put("id", "");
				dept.put("label", d);
				result.add(dept);
			}
		}

		return result;
	}

	@Override
	public JSONArray users(String label) throws Exception {
		JSONArray result = new JSONArray();
		if (!label.equals("")) {
			label = label.equals("*") ? "" : label;
			label = label.lastIndexOf("*") != -1 ? label.substring(0, label.length()-1) : label;
			List<UserInfo> list = (List<UserInfo>) userDao.userList(label);
			RecordUtil recordUtil = new RecordUtil();

			for (UserInfo u : list) {
				JSONObject user = recordUtil.generateJsonByMapping(u);
				user.remove("password");
				user.put("label", u.getName() + "(" + u.getDeptName() + ")");
				result.add(user);
			}
		}

		return result;
	}

	@Override
	public JSONObject validUser(UserInfo user, String password) throws Exception {
		JSONObject result = new JSONObject();
		boolean accept = userDao.validPassword(user.getUserName(), password);
		result.put("accept", accept);

		return result;
	}

	@Override
	public void changePassword(UserInfo user, String oldPassword, String password) throws Exception {
		boolean accept = userDao.validPassword(user.getUserName(), oldPassword);
		if (accept) {
			userDao.changePassword(user.getUserName(), password);
		} else {
			throw new ErrorException(this.getClass(), "用户名或原密码不正确");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getUserByUserId(String userId) throws Exception {

		List<UserInfo> list = (List<UserInfo>) userDao.query(UserInfo.class, new Criterion[]{Restrictions.eq("userId", userId)}, null, null, false);

		if (list.size() == 0) return null;
		else return list.get(0);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
