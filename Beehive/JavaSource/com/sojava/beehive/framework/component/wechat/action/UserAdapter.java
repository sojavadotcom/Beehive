package com.sojava.beehive.framework.component.wechat.action;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.bean.UserToken;
import com.sojava.beehive.framework.component.wechat.define.Platform;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.component.wechat.service.UserService;
import com.sojava.beehive.framework.exception.ErrorException;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("WeChat/User")
@Scope("prototype")
@Namespace("/WeChat")
public class UserAdapter extends ActionSupport {
	private static final long serialVersionUID = 6032138875157807534L;

	@Resource private UserService userService;

	private String INDEX_URL = "http://wx.jxszyyy.org.cn/WeChat/User/index.shtml";

	private String platform;
	private String code;
	private String state;
	private User user;
	private String errmsg;

	@Action(value = "index", results = {
				@Result(name = "index", location = "Index.jsp", params = {"user", "%{user}"}),
				@Result(name = "signup", location = "Signup.jsp", params = {"user", "%{user}"}),
				@Result(name = ERROR, location = "Error.jsp", params = {"errmsg", "%{errmsg}"})
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
							"https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=7B801#wechat_redirect",
							appid,
							this.INDEX_URL
						));
			} else {
				if (state.equals("10003")) throw new ErrorException("redirect_uri域名与后台配置不一致");
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

				UserToken userToken = userService.getToken(appid, secret, code);
				user = userService.getUser(userToken.getOpenid());
				if (user == null) rest = "signup";
				else rest = "index";
			}
		}
		catch(ErrorException ex) {
			this.errmsg = ex.getLocalizedMessage();
			rest = ERROR;
		}
		return rest;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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
