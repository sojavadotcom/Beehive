package com.sojava.beehive.framework.component.wechat.service.impl;

import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.component.wechat.dao.WxUserDao;
import com.sojava.beehive.framework.component.wechat.define.Platform;
import com.sojava.beehive.framework.component.wechat.define.WxScope;
import com.sojava.beehive.framework.component.wechat.service.WxUserService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.NetworkUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class WxUserServiceImpl implements WxUserService {

	@Resource private WxUserDao wxUserDao;

	private final String STATE = "7B801";

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
	public void signup(UserToken userToken, WxUserInfo wxUserInfo, Integer staffId, String staffName, String adminDuty, String jobTitle, String mobileShortNumber, String mobileNumber, String deptName, Platform platform, String role, String status) throws ErrorException {

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
			user.setPlatform(platform.name());
			user.setRole(role);
			user.setStatus(status);

			wxUserDao.signup(user);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex.getLocalizedMessage());
		}
	}

	@Override
	public void signup(User user) throws ErrorException {
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
	public void updateStaffInfo(String accessToken, String openid, Integer staffId, String staffName, String adminDuty, String jobTitle, String mobileShortNumber, String mobileNumber, String deptName, Platform platform, String role, String status) throws ErrorException {
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
		user.setPlatform(platform.name());
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

	@Override
	public User checkWxUser(HttpServletResponse response, String redirectURL, String appid, String secret, WxScope scope, String code, String state, String wxid, Platform platform) throws ErrorException {
		User user = null;
		try {
			String openid = null;
			UserToken userToken = null;
			if ((code == null || code.trim().equals("")) && (wxid == null || wxid.trim().equals(""))) {
				response.sendRedirect(String.format(
							"https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
							appid,
							URLEncoder.encode(redirectURL, System.getProperty("system.encoding", "UTF-8")),
							scope.name(),
							this.STATE
						));
			} else if (state != null && !state.trim().equals("")) {
				if (state.equals(this.STATE)) {
					userToken = getToken(appid, secret, code);
					openid = userToken.getOpenid();
				}
				else if (state.equals("10003")) throw new ErrorException("redirect_uri域名与后台配置不一致");
				else if (state.equals("10004")) throw new ErrorException("此公众号被封禁");
				else if (state.equals("10005")) throw new ErrorException("此公众号并没有这些scope的权限");
				else if (state.equals("10006")) throw new ErrorException("必须关注“鸡西市中医医院质量管理平台”后才能进入");
				else if (state.equals("10009")) throw new ErrorException("操作太频繁了，请稍后重试");
				else if (state.equals("10010")) throw new ErrorException("scope不能为空");
				else if (state.equals("10011")) throw new ErrorException("redirect_uri不能为空");
				else if (state.equals("10012")) throw new ErrorException("appid不能为空");
				else if (state.equals("10013")) throw new ErrorException("state不能为空");
				else if (state.equals("10015")) throw new ErrorException("公众号未授权第三方平台，请检查授权状态");
				else if (state.equals("10016")) throw new ErrorException("不支持微信开放平台的Appid，请使用公众号Appid");
			} else if (wxid != null && !wxid.trim().equals("")) {
				openid = wxid;
			}

			user = getUser(openid);
			if (userToken != null) {
				if (user == null || !user.getStatus().equals("已激活")) {
					WxUserInfo wxUserInfo = getWxUserInfo(userToken.getAccessToken(), userToken.getOpenid());
					if (user == null) {
						user = new User();
						user.setAccessToken(userToken.getAccessToken());
						user.setOpenid(userToken.getOpenid());
						signup(userToken, wxUserInfo, null, null, null, null, null, null, null, platform, null, "待登记");
					} else {
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
						signup(user);
					}
				}
			} else if (user == null) {
				throw new ErrorException("用户未登记，不能操作");
			} else if (!user.getStatus().equals("已激活")) {
				throw new ErrorException("用户未激活，不能操作");
			}
		}
		catch(ErrorException ex) {
			//处理40163 code已使用错误
			if (ex.getLocalizedMessage().startsWith("40163[code")) {
				try {
					user = null;
					response.sendRedirect(String.format(
							"https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
							appid,
							URLEncoder.encode(redirectURL, System.getProperty("system.encoding", "UTF-8")),
							scope.name(),
							this.STATE
						));
				}
				catch(IOException e) {
					throw new ErrorException(e);
				}
			} else throw ex;
		}
		catch(Exception ex) {
			throw new ErrorException(ex);
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
