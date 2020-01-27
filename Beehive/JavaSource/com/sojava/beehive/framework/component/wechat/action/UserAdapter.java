package com.sojava.beehive.framework.component.wechat.action;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.bean.WxUserInfo;
import com.sojava.beehive.framework.component.wechat.define.Platform;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.component.wechat.service.WxUserService;
import com.sojava.beehive.framework.exception.ErrorException;

import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("WeChat/User")
@Scope("prototype")
@Namespace("/WeChat/User")
public class UserAdapter extends ActionSupport {
	private static final long serialVersionUID = 6032138875157807534L;

	@Resource private WxUserService wxUserService;

	private String INDEX_URL = "https://wx.jxszyyy.org.cn/WeChat/User/Index.shtml?platform=TQM";
	private String STATE = "7B801";

	private String platform;
	private String code;
	private String state;
	private User user;
	private String errmsg;

	@Action(value = "Index", results = {
				@Result(name = "index", location = "Index.jsp", params = {"user", "%{user}"}),
				@Result(name = "signup", location = "Register.jsp", params = {"user", "%{user}"}),
				@Result(name = ERROR, location = "../Error.jsp", params = {"errmsg", "%{errmsg}"})
			})
	public String index() throws Exception {
		String rest = null;
		try {
			super.execute();
			String appid = null, secret = null;
			if (getPlatform().equals(Platform.TQM.toString())) {
				appid = WeChatInfo.TQM_APPID;
				secret = WeChatInfo.TQM_SECRET;
			} else {
				throw new ErrorException("错误的平台操作");
			}
			if (code == null) {
				getResponse().sendRedirect(String.format(
							"https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect",
							appid,
							URLEncoder.encode(this.INDEX_URL, System.getProperty("system.encoding", "UTF-8")),
							this.STATE
						));
			} else {
				if (state.equals(this.STATE)) {
					UserToken userToken = wxUserService.getToken(appid, secret, code);
					user = wxUserService.getUser(userToken.getOpenid());
					if (user == null || !user.getStatus().equals("已激活")) {
						rest = "signup";
						WxUserInfo wxUserInfo = wxUserService.getWxUserInfo(userToken.getAccessToken(), userToken.getOpenid());
						if (user == null) {
							user = user == null ? new User() : user;
							user.setOpenid(userToken.getOpenid());
							wxUserService.Signup(userToken, wxUserInfo, null, null, null, null, null, null, null, platform, null, "待登记");
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
							wxUserService.Signup(user);
						}
					} else rest = "index";
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
			}
		}
		catch(ErrorException ex) {
			this.errmsg = ex.getLocalizedMessage();
			rest = ERROR;
		}
		return rest;
	}

	@Action(value = "Signup", results = {
			@Result(name = "index", location = "Index.jsp", params = {"user", "%{user}"}),
			@Result(name = ERROR, location = "../Error.jsp", params = {"errmsg", "%{errmsg}"})
		})
	public String signup() {
		String rest = null;

		try {
			if (code == null) throw new ErrorException("缺少编号信息！");
			User u = wxUserService.getUser(code);
			if (u == null) throw new ErrorException("编号信息错误！");
			u.setStaffName(user.getStaffName());
			u.setDeptName(user.getDeptName());
			u.setMobileShortNumber(user.getMobileShortNumber());
			u.setMobileNumber(user.getMobileNumber());
			u.setStatus("待审核");
			wxUserService.Signup(u);
			user = u;

			rest = "index";
		}
		catch(Exception ex) {
			this.errmsg = ex.getLocalizedMessage();
			rest = ERROR;
		}

		return rest;
	}

	public WxUserService getUserService() {
		return wxUserService;
	}

	public void setUserService(WxUserService userService) {
		this.wxUserService = userService;
	}

	public String getPlatform() {
		return platform == null ? "" : platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
