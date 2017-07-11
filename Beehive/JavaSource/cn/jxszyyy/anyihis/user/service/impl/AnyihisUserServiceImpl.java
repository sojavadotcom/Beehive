package cn.jxszyyy.anyihis.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import cn.jxszyyy.anyihis.dic.bean.ZdKs;
import cn.jxszyyy.anyihis.dic.bean.ZdRy;
import cn.jxszyyy.anyihis.user.dao.AnyihisUserDao;
import cn.jxszyyy.anyihis.user.service.AnyihisUserService;

import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.ExceptionUtil;
import com.sojava.beehive.framework.util.RecordUtil;
import com.sojava.beehive.framework.util.security.Encrypt;

@Service
public class AnyihisUserServiceImpl implements AnyihisUserService {

	@Resource private AnyihisUserDao anyihisUserDao;

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject login(String sessionId, String userName, String password, String code, HttpServletRequest request) throws Exception {
		JSONObject result = null;
		HttpSession session = request.getSession();
		boolean accept = anyihisUserDao.validPassword(userName, password);
		if (accept) {
			List<ZdRy> list = (List<ZdRy>) anyihisUserDao.query(ZdRy.class, new Criterion[] {Restrictions.eq("dh", userName).ignoreCase()}, null, null, false);
			if (list.isEmpty()) throw new Exception("用户名或密码错误");
			else if (list.size() > 1) throw new Exception("找到多个用户，系统无法识别");
			else {
				ZdRy ry = list.get(0);
				ZdKs ks = (ZdKs) anyihisUserDao.get(ZdKs.class, ry.getKsbm());
				UserInfo user = new UserInfo();
				user.setName(ry.getMc());
				user.setUserId(ry.getBm());
				user.setUserName(ry.getDh());
				user.setPassword(ry.getPassword());
				user.setJob(ry.getZw());
				user.setTitle(ry.getZc());
				user.setDeptName(ks.getMc());
				user.setDeptNamePy(ks.getDh());
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

	@Override
	public boolean passwordIsInit(String userName) throws Exception {
		return false;
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
			List<ZdRy> list = (List<ZdRy>) anyihisUserDao.query(ZdRy.class, new Criterion[] {Restrictions.eq("dh", userName).ignoreCase()}, null, null, false);
			if (list.size() == 0) throw new Exception("用户名错误");
			else if (list.size() > 1) throw new Exception("找到多个用户，系统无法识别");
			else {
				ZdRy ry = list.get(0);
				result = new JSONObject();
				result.put("userName", ry.getDh());
				result.put("name", ry.getMc());
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
			List<ZdKs> list = anyihisUserDao.deptList(label);
			for (ZdKs d : list) {
				JSONObject dept = new JSONObject();
				dept.put("id", "");
				dept.put("label", d.getMc());
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
			List<ZdRy> list = (List<ZdRy>) anyihisUserDao.userList(label);
			RecordUtil recordUtil = new RecordUtil();

			for (ZdRy ry : list) {
				ZdKs ks = (ZdKs) anyihisUserDao.get(ZdKs.class, ry.getKsbm());
				UserInfo u = new UserInfo();
				u.setName(ry.getMc());
				u.setUserId(ry.getBm());
				u.setUserName(ry.getDh());
				u.setPassword(ry.getPassword());
				u.setJob(ry.getZw());
				u.setTitle(ry.getZc());
				u.setDeptName(ks.getMc());
				u.setDeptNamePy(ks.getDh());
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
		boolean accept = anyihisUserDao.validPassword(user.getUserName(), password);
		result.put("accept", accept);

		return result;
	}

	@Override
	public void changePassword(UserInfo user, String oldPassword, String password) throws Exception {
		boolean accept = anyihisUserDao.validPassword(user.getUserName(), oldPassword);
		if (accept) {
			anyihisUserDao.changePassword(user.getUserName(), password);
		} else {
			throw new ErrorException(this.getClass(), "用户名或原密码不正确");
		}
	}

	public AnyihisUserDao getAnyihisUserDao() {
		return anyihisUserDao;
	}

	public void setAnyihisUserDao(AnyihisUserDao anyihisUserDao) {
		this.anyihisUserDao = anyihisUserDao;
	}

}
