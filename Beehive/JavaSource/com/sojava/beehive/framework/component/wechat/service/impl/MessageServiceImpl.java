package com.sojava.beehive.framework.component.wechat.service.impl;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.sojava.beehive.framework.component.wechat.bean.Message;
import com.sojava.beehive.framework.component.wechat.dao.MessageDao;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.component.wechat.service.MessageService;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	@Resource MessageDao messageDao;

	@Override
	public void receive(String signature, String timestamp, String nonce, String echostr, String replyMsg) throws Exception {
		WXBizMsgCrypt pc = new WXBizMsgCrypt(WeChatInfo.TOKEN, WeChatInfo.ENCODING_AES_KEY, WeChatInfo.APPID);
		pc.verifyUrl(signature, timestamp, nonce, echostr);

		String decryptMsg = pc.decryptMsg(signature, timestamp, nonce, replyMsg);
		Message message = new Message();
		message.setDatetime(new Date());
		message.setKind("");
		message.setType("");
		message.setMessage(decryptMsg);
		message.setSignature(signature);
		message.setTimestamp(timestamp);
		message.setNonce(nonce);
		message.setEchostr(echostr);
		message.setReplyMsg(replyMsg);
		messageDao.save(message);
	}

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

}
