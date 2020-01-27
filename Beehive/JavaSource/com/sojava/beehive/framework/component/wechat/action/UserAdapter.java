package com.sojava.beehive.framework.component.wechat.action;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.define.Platform;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.component.wechat.define.WxScope;
import com.sojava.beehive.framework.component.wechat.service.WxUserService;
import com.sojava.beehive.framework.exception.ErrorException;

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
			if (Platform.valueOf(getPlatform()).equals(Platform.TQM)) {
				appid = WeChatInfo.TQM_APPID;
				secret = WeChatInfo.TQM_SECRET;
			} else {
				throw new ErrorException("错误的平台操作");
			}
			user = wxUserService.checkWxUser(getResponse(), INDEX_URL, appid, secret, WxScope.snsapi_userinfo, code, state, Platform.valueOf(platform));
			if (user != null) {
				if (user.getStatus() == null || !user.getStatus().equals("已激活")) {
					rest = "signup";
				} else {
					rest = "index";
				}
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
			wxUserService.signup(u);
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
