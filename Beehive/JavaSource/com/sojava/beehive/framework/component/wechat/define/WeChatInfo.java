package com.sojava.beehive.framework.component.wechat.define;

import com.sojava.beehive.framework.component.wechat.bean.AccessToken;
import com.sojava.beehive.framework.component.wechat.bean.JsapiTicket;

public class WeChatInfo {

	private static AccessToken TQMAccessToken = null;
	private static JsapiTicket TQMJsapiTicket = null;

	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	public final static String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

	public static final String APP_APPID = "wx781e209ee205bcd3";
	public static final String APP_SECRET = "0d058faa4438296b21f8883955c4cbc6";
	public static final String APP_TOKEN = "wxmsg";
	public static final String APP_ENCODING_AES_KEY = "zrxB8XLjSYtxcoqHy22hUFYjytUfdDnfdvHYN0qhret";

	public static final String TQM_APPID = "wxd5b66e9aa4391045";
	public static final String TQM_SECRET = "4426b4b826210dc7896b3742ca12a6fa";
	public static final String TQM_TOKEN = "TQM";
	public static final String TQM_ENCODING_AES_KEY = "RFzK3lGVIh5z6xYWfcdNd1FotKYWr7Xjg0rzyLkcucw";

	public static AccessToken getTQMAccessToken() {
		return TQMAccessToken;
	}
	public static void setTQMAccessToken(AccessToken tQMAccessToken) {
		TQMAccessToken = tQMAccessToken;
	}
	public static JsapiTicket getTQMJsapiTicket() {
		return TQMJsapiTicket;
	}
	public static void setTQMJsapiTicket(JsapiTicket tQMJsapiTicket) {
		TQMJsapiTicket = tQMJsapiTicket;
	}

	
}
