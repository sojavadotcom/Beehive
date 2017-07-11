package com.sojava.beehive.framework.message;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sojava.beehive.framework.util.FormatUtil;

import cn.b2m.eucp.sdkhttp.Mo;
import cn.b2m.eucp.sdkhttp.SDKClient;
import cn.b2m.eucp.sdkhttp.SDKClientProxy;

public class SMS {

	private SDKClient client;
	private String account;
	private String password;

	public SMS() {
		this.client = new SDKClientProxy();
		this.account = System.getProperty("sms.account");
		this.password = System.getProperty("sms.password");
	}

	public int send(String number, String msg) throws RemoteException {
		return send(null, new String[]{number}, msg);
	}

	public int send(Date sendTime, String[] numbers, String msg) throws RemoteException {
		String _sendTime = null;
		if (sendTime != null) {
			_sendTime = FormatUtil.DATETIME_FORMAT.format(sendTime).replaceAll("\\-|\\ |\\:", "");
		}
		return this.client.sendSMS(
				this.account,
				this.password,
				_sendTime,
				numbers,
				msg,
				null,
				null,
				5,
				0
			);
	}

	@SuppressWarnings("rawtypes")
	public Map[] receive() throws RemoteException {
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		Mo[] mos = this.client.getMO(this.account, this.password);

		if (mos != null) {
			for (Mo mo: mos) {
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("addSerial", mo.getAddSerial());
				msg.put("addSerialRev", mo.getAddSerialRev());
				msg.put("channelnumber", mo.getChannelnumber());
				msg.put("mobileNumber", mo.getMobileNumber());
				msg.put("sentTime", mo.getSentTime());
				msg.put("smsContent", mo.getSmsContent());

				result.add(msg);
			}
		}

		return result.toArray(new Map[0]);
	}

	public double getBalance() throws RemoteException {
		return this.client.getBalance(this.account, this.password);
	}

}
