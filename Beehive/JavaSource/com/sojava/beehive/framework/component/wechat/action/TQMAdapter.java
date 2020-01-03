package com.sojava.beehive.framework.component.wechat.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.io.Writer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller("WeChat/TQM/Adapter")
@Scope("prototype")
@Namespace("/WeChat/TQM")
@ParentPackage("json-default")
public class TQMAdapter extends ActionSupport {
	private static final long serialVersionUID = 8732195833246317990L;

	private String signature;// = "389ab0960499c04cf662af4e86133f754d270cd2";
	private String echostr;// = "5512701662590795775";
	private String timestamp;// = "1577900695";
	private String nonce;// = "382127749";
	private String openid;

	@Action("Adapter")
	public String index() throws Exception {
		super.execute();
//		System.out.println("===================================================");
//		System.out.println("signature=" + (signature == null ? "null" : signature));
//		System.out.println("echostr=" + (echostr == null ? "null" : echostr));
//		System.out.println("timestamp=" + (timestamp == null ? "null" : timestamp));
//		System.out.println("nonce=" + (nonce == null ? "null" : nonce));
		WXBizMsgCrypt pc = new WXBizMsgCrypt(WeChatInfo.TQM_TOKEN, WeChatInfo.TQM_ENCODING_AES_KEY, WeChatInfo.TQM_APPID);
		pc.verifyUrl(signature, timestamp, nonce);
		InputStream in = getRequest().getInputStream();
		if (in.available() == 0) new Writer(getRequest(), getResponse()).output(echostr);
		else {
			BufferedReader reader = null;
			StringBuilder sb = new StringBuilder();
			try {
				reader = new BufferedReader(new InputStreamReader(in));
				String line = null;
				while ((line = reader.readLine()) != null){
					sb.append(line);
				}
			}
			finally {
				if (reader != null) reader.close();
			}
			System.out.println("用户信息(" + (openid == null ? "" : openid) + ")：" + sb.toString());
		}

		return null;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
