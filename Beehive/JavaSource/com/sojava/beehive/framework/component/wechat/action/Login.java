package com.sojava.beehive.framework.component.wechat.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;
import sun.net.www.content.text.PlainTextInputStream;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.type.ClientType;
import com.sojava.beehive.framework.component.type.ResultType;
import com.sojava.beehive.framework.component.user.service.UserService;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.io.Writer;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.Resource;

@Controller("WeChat/Login")
@Scope("prototype")
@Namespace("/WeChat")
@ParentPackage("json-default")
public class Login extends ActionSupport {
	private static final long serialVersionUID = -6930643276323395736L;

	@Resource private UserService userService;

	private String code = "";

	public Login() {}

	@Action("Login")
	public String index() throws Exception {
		super.execute();

		byte[] buff = new byte[1024];
		int len = 0;
		JSONObject rest;

		String urlStr = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WeChatInfo.APPID + "&secret=" + WeChatInfo.SECRET + "&js_code=" + code + "&grant_type=authorization_code";
		URL url = new URL(urlStr);
		URLConnection connect = url.openConnection();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PlainTextInputStream in = (PlainTextInputStream) connect.getContent();
		while((len = in.read(buff, 0, 1024)) != -1) {
			out.write(buff, 0, len);
		}
		in.close();
		out.close();
		rest = JSONObject.fromObject(out.toString());

		new Writer(getRequest(), getResponse()).output(ClientType.WeChat, ResultType.Json, rest);

		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
