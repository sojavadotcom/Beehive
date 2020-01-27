package com.sojava.beehive.framework.component.wechat.service.impl;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.component.wechat.dao.WxUserDao;
import com.sojava.beehive.framework.component.wechat.service.WxUserService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.NetworkUtil;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class WxUserServiceImpl implements WxUserService {

	@Resource private WxUserDao wxUserDao;

	private NetworkUtil networkUtil;

	public WxUserServiceImpl() {
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
			if (rec.containsKey("errcode")) {
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
	public WxUserInfo getWxUserInfo(String accessToken, String openid) throws ErrorException {
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
	public void Signup(UserToken userToken, WxUserInfo wxUserInfo, Integer staffId, String staffName, String adminDuty, String jobTitle, String mobileShortNumber, String mobileNumber, String deptName, String platform, String role, String status) throws ErrorException {

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
			user.setMobileShortNumber(mobileShortNumber);
			user.setMobileNumber(mobileNumber);
			user.setDeptName(deptName);
			user.setPlatform(platform);
			user.setRole(role);
			user.setStatus(status);

			wxUserDao.signup(user);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}
	}

	@Override
	public void Signup(User user) throws ErrorException {
		try {
			wxUserDao.save(user);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}
	}

	@Override
	public void updateWxUserInfo(String appid, String accessToken) throws ErrorException {
		UserToken userToken = refreshToken(appid, accessToken);
		WxUserInfo wxUserInfo = getWxUserInfo(userToken.getRefreshToken(), userToken.getOpenid());
		wxUserDao.updateWxUserInfo(userToken, wxUserInfo);
	}

	@Override
	public void updateStaffInfo(String accessToken, String openid, Integer staffId, String staffName, String adminDuty, String jobTitle, String mobileShortNumber, String mobileNumber, String deptName, String platform, String role, String status) throws ErrorException {
		User user = new User();
		user.setAccessToken(accessToken);
		user.setOpenid(openid);
		user.setStaffId(staffId);
		user.setStaffName(staffName);
		user.setAdminDuty(adminDuty);
		user.setJobTitle(jobTitle);
		user.setMobileShortNumber(mobileShortNumber);
		user.setMobileNumber(mobileNumber);
		user.setDeptName(deptName);
		user.setPlatform(platform);
		user.setRole(role);
		user.setStatus(status);

		wxUserDao.updateStaffInfo(user);
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

	@Override
	public User[] listUser(Criterion[] filters, Order[] orders) throws ErrorException {
		List<User> rest = null;

		try {
			rest = (List<User>) wxUserDao.listUser(filters, orders, null, true);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}

		return rest.toArray(new User[0]);
	}

	@Override
	public User getUser(String openid) throws ErrorException {
		User user = null;

		try {
			user = wxUserDao.getUser(openid, true);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}

		return user;
	}

	public WxUserDao getUserDao() {
		return wxUserDao;
	}

	public void setUserDao(WxUserDao userDao) {
		this.wxUserDao = userDao;
	}
}
