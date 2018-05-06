package com.sojava.beehive.framework.component.wechat.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.service.MessageService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.io.ContentType;
import com.sojava.beehive.framework.io.Writer;

import javax.annotation.Resource;

@Controller("WeChat/Message")
@Scope("prototype")
@Namespace("/WeChat/Message")
@ParentPackage("json-default")
public class Message extends ActionSupport {
	private static final long serialVersionUID = 3513448784787019830L;

	@Resource private MessageService messageService;

	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private String replyMsg;

	@Override
	@Action(value = "Receive")
	public String input() throws Exception {
		super.execute();
		return (String) this.getClass().getMethod(this.getActionContext().getName().toLowerCase(), new Class[0]).invoke(this, new Object[0]);
	}

	public String receive() throws Exception {
		try {
			messageService.receive(signature, timestamp, nonce, echostr, replyMsg);
		}
		catch(Exception ex) {
			new ErrorException(getClass(), ex);
		}
		finally {
			new Writer(getRequest(), getResponse()).output(echostr, ContentType.text);
		}
		return null;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}

}
