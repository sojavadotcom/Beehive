package com.sojava.beehive.framework.component.wechat.tqm.service;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryEvidence;

import java.util.List;
import java.util.Map;

public interface EvidenceService {

	boolean verifyQRCode(String qrcode) throws Exception;
	byte[] getPhoto(int id) throws Exception;
	void save(CaseHistoryEvidence evidence) throws Exception;
	List<CaseHistoryEvidence> queryEvidence(int standardId, int paperNum) throws Exception;
	List<Map<String, Object>> getPhotos(int standardId, int paperNum) throws Exception;
}
