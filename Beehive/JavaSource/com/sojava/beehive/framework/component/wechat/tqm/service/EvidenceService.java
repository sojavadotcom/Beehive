package com.sojava.beehive.framework.component.wechat.tqm.service;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryEvidence;

public interface EvidenceService {

	boolean verifyQRCode(String qrcode) throws Exception;
	byte[] getPhoto(int id) throws Exception;
	void save(CaseHistoryEvidence evidence) throws Exception;
}
