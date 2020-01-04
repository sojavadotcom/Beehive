package com.sojava.beehive.framework.component.wechat.action;

import com.sojava.beehive.framework.component.wechat.bean.AccessToken;
import com.sojava.beehive.framework.component.wechat.bean.JsapiTicket;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.log.Logger;
import com.sojava.beehive.framework.util.NetworkUtil;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class TokenSchedule {

	@Scheduled(initialDelay = 1000, fixedDelay = 7000 * 1000)	//每小时执行一次
	public void execute() throws Exception {

		try {
			AccessToken accessToken = getAccessToken();
			WeChatInfo.setTQMAccessToken(accessToken);
			if (accessToken == null) {
				Thread.sleep(1000 * 1);
				execute();
			} else {
				JsapiTicket jsapiTicket = getJsapiTicket(accessToken.getAccessToken());
				WeChatInfo.setTQMJsapiTicket(jsapiTicket);
			}
		}
		catch(ErrorException ex) {
			Logger.error(getClass(), ex);
		}
		catch(Exception ex) {
			Logger.error(getClass(), ex);
			Thread.sleep(1000 * 3);
			execute();
		}
	}

	/**
     * 获取access_token
     * @return
     */
	private AccessToken getAccessToken() throws ErrorException {
		NetworkUtil netHelper = new NetworkUtil();
		String result = netHelper.getHttpsResponse(String.format(WeChatInfo.ACCESS_TOKEN_URL, WeChatInfo.TQM_APPID, WeChatInfo.TQM_SECRET), "GET");
		Logger.debug(getClass(), result);
		JSONObject json = JSONObject.fromObject(result);

		AccessToken token = null;
		if (!json.containsKey("errcode") || json.getInt("errcode") == 0) {
			token = new AccessToken();
			token.setAccessToken(json.getString("access_token"));
			token.setExpiresin(json.getInt("expires_in"));
		} else {
			throw new ErrorException("AccessToken getAccessToken() " + json.getString("errmsg"));
		}

		return token;
	}

    /**
     * 获取jsapi_ticket
     * @return
     */
    private JsapiTicket getJsapiTicket(String accessToken) throws ErrorException {
    	NetworkUtil netHelper = new NetworkUtil();
        String result = netHelper.getHttpsResponse(String.format(WeChatInfo.JSAPI_TICKET_URL, accessToken), "GET");
        Logger.debug(getClass(), result);
        JSONObject json = JSONObject.fromObject(result);

        JsapiTicket jsapiTicket = null;
        if (json.getInt("errcode") == 0) {
	        jsapiTicket = new JsapiTicket();
	        jsapiTicket.setJsapiTicket(json.getString("ticket"));
	        jsapiTicket.setExpiresin(json.getInt("expires_in"));
        } else {
        	throw new ErrorException("JsapiTicket getJsapiTicket(String accessToken) " + json.getString("errmsg"));
        }

        return jsapiTicket;
    }

}
