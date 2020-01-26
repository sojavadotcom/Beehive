package com.sojava.beehive.framework.component.wechat.service.impl;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.component.wechat.dao.UserDao;
import com.sojava.beehive.framework.component.wechat.service.UserService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.NetworkUtil;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class UserServiceImpl implements UserService {

	@Resource private UserDao userDao;

	private NetworkUtil networkUtil;

	public UserServiceImpl() {
		super();
		this.networkUtil = new NetworkUtil();
	}

	@Override
	public UserToken getToken(String appid, String secret, String code) throws ErrorException {
		UserToken rest = null;

		try {
			long start = System.currentTimeMillis();
			String data = this.networkUtil.getHttpsResponse(String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appid, secret, code), "GET");
			JSONObject rec = JSONObject.fromObject(data);
			if (rec.containsKey("errcode") && (!rec.getString("errcode").equals("ok") || !rec.getString("errcode").equals("0"))) {
				throw new ErrorException(rec.getString("errcode") + "[" + rec.getString("errmsg") + "]");
			} else {
				rest = new UserToken();
				rest.setStart(start);
				rest.setAccessToken(rec.getString("access_token"));
				rest.setOpenid(rec.getString("openid"));
				rest.setExpiresIn(rec.getLong("expires_in"));
				rest.setRefreshToken(rec.getString("refresh_token"));
				rest.setScope(rec.getString("scope"));
			}
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}

		return rest;
	}

	@Override
	public UserToken refreshToken(String appid, String accessToken) throws ErrorException {
		UserToken rest = null;

		try {
			long start = System.currentTimeMillis();
			String data = this.networkUtil.getHttpsResponse(String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s", appid, accessToken), "GET");
			JSONObject rec = JSONObject.fromObject(data);
			if (rec.containsKey("errcode") && (!rec.getString("errcode").equals("ok") || !rec.getString("errcode").equals("0"))) {
				throw new ErrorException(rec.getString("errcode") + "[" + rec.getString("errmsg") + "]");
			} else {
				rest = new UserToken();
				rest.setStart(start);
				rest.setAccessToken(rec.getString("access_token"));
				rest.setOpenid(rec.getString("openid"));
				rest.setExpiresIn(rec.getLong("expires_in"));
				rest.setRefreshToken(rec.getString("refresh_token"));
				rest.setScope(rec.getString("scope"));
			}
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}

		return rest;
	}

	@Override
	public WxUserInfo getUserinfo(String accessToken, String openid) throws ErrorException {
		WxUserInfo rest = null;

		try {
			String data = this.networkUtil.getHttpsResponse(String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", accessToken, openid), "GET");
			JSONObject rec = JSONObject.fromObject(data);
			if (rec.containsKey("errcode") && (!rec.getString("errcode").equals("ok") || !rec.getString("errcode").equals("0"))) {
				throw new ErrorException(rec.getString("errcode") + "[" + rec.getString("errmsg") + "]");
			} else {
				rest = new WxUserInfo();
				rest.setOpenid(rec.getString("openid"));
				rest.setNickname(rec.getString("nickname"));
				rest.setSex(rec.getString("sex"));
				rest.setProvince(rec.getString("province"));
				rest.setCity(rec.getString("city"));
				rest.setCountry(rec.getString("country"));
				rest.setHeadimgurl(rec.getString("headimgurl"));
				rest.setPrivilege(rec.getString("privilege"));
				rest.setUnionid(rec.containsKey("unionid") ? rec.getString("unionid") : null);
			}
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}

		return rest;
	}

	@Override
	public void Signup(UserToken userToken, WxUserInfo wxUserInfo, Integer staffId, String staffName, String adminDuty, String jobTitle, String mobileNumber, String deptName, String platform, String role, String status) throws ErrorException {

		try {
			User user = new User();
			//access token信息
			user.setAccessToken(userToken.getAccessToken());
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
			//人员信息
			user.setStaffId(staffId);
			user.setStaffName(staffName);
			user.setAdminDuty(adminDuty);
			user.setJobTitle(jobTitle);
			user.setMobileNumber(mobileNumber);
			user.setDeptName(deptName);
			user.setPlatform(platform);
			user.setRole(role);
			user.setStatus(status);

			userDao.signup(user);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}
	}

	@Override
	public void updateWxUserInfo(String appid, String accessToken) throws ErrorException {
		UserToken userToken = refreshToken(appid, accessToken);
		WxUserInfo wxUserInfo = getUserinfo(userToken.getRefreshToken(), userToken.getOpenid());
		userDao.updateWxUserInfo(userToken, wxUserInfo);
	}

	@Override
	public void updateStaffInfo(String accessToken, String openid, Integer staffId, String staffName, String adminDuty, String jobTitle, String mobileNumber, String deptName, String platform, String role, String status) throws ErrorException {
		User user = new User();
		user.setAccessToken(accessToken);
		user.setOpenid(openid);
		user.setStaffId(staffId);
		user.setStaffName(staffName);
		user.setAdminDuty(adminDuty);
		user.setJobTitle(jobTitle);
		user.setMobileNumber(mobileNumber);
		user.setDeptName(deptName);
		user.setPlatform(platform);
		user.setRole(role);
		user.setStatus(status);

		userDao.updateStaffInfo(user);
	}

	@Override
	public boolean verifyToken(String accessToken, String openid) throws ErrorException {
		boolean rest = false;

		try {
			String data = this.networkUtil.getHttpsResponse(String.format("https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID", accessToken, openid), "GET");
			JSONObject rec = JSONObject.fromObject(data);
			rest = rec.containsKey("errcode") && (!rec.getString("errcode").equals("ok") || !rec.getString("errcode").equals("0"));
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}

		return rest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User[] listUser(Criterion[] filters, Order[] orders) throws ErrorException {
		List<User> rest = null;

		try {
			rest = (List<User>) userDao.query(User.class, filters, orders, null, false);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}

		return rest.toArray(new User[0]);
	}

	@Override
	public User getUser(String openid) throws ErrorException {
		User rest = null;

		try {
			User[] list = listUser(new Criterion[] {Restrictions.eq("openid", openid)}, null);
			if (list.length == 1) rest = list[0];
			else if (list.length > 1) throw new ErrorException("找到多个重复微信用户");
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}

		return rest;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
