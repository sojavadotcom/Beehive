package com.sojava.beehive.framework.component.wechat.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.qq.weixin.mp.aes.Sign;
import com.qq.weixin.mp.aes.Signature;
import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.log.Logger;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.io.Writer;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/Query")
@Scope("prototype")
public class TQMQuery extends ActionSupport {
	private static final long serialVersionUID = -6792358151658105592L;

	private String url;

	@Action("Query.*")
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		JSONObject rest = new JSONObject();
		rest.put("success", true);
		rest.put("errmsg", "ok");
		try {
			actionName = actionName.split("\\Q.\\E")[1];
			if (WeChatInfo.getTQMJsapiTicket() == null) throw new ErrorException("jsapi_ticket未准备好");
			String jsapiTicket = WeChatInfo.getTQMJsapiTicket().getJsapiTicket();

			if (actionName.equals("Signature")) {
				Signature signature = Sign.sign(WeChatInfo.TQM_APPID, jsapiTicket, url);
				rest.put("data", JSONObject.fromObject(signature));
			}
		}
		catch(Exception ex) {
			Logger.error(getClass(), ex);
			rest.put("success", false);
			rest.put("errmsg", ex.getMessage() == null ? "NullPointer" : ex.getMessage());
		}
		finally {
			new Writer(getRequest(), getResponse()).output(rest);
		}

		return null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
