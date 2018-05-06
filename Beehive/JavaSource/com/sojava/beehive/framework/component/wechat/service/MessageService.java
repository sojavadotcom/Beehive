package com.sojava.beehive.framework.component.wechat.service;

public interface MessageService {

	void receive(String signature, String timestamp, String nonce, String echostr, String replyMsg) throws Exception;
}
