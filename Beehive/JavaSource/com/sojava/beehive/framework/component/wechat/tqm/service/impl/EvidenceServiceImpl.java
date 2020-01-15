package com.sojava.beehive.framework.component.wechat.tqm.service.impl;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryEvidence;
import com.sojava.beehive.framework.component.wechat.tqm.dao.EvidenceDao;
import com.sojava.beehive.framework.component.wechat.tqm.service.EvidenceService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class EvidenceServiceImpl implements EvidenceService {

	@Resource private EvidenceDao evidenceDao;

	private final String REGEX = "^.*(cn\\.org\\.jxszyyy\\.casehistory\\.evidence\\.)\\d{1,}$";

	@Override
	public boolean verifyQRCode(String qrcode) throws Exception {

		return qrcode.matches(REGEX);
	}

	@Override
	public byte[] getPhoto(int id) throws Exception {
		CaseHistoryEvidence e = (CaseHistoryEvidence) evidenceDao.get(CaseHistoryEvidence.class, id);
		return e.getPhoto();
	}

	@Override
	public void save(CaseHistoryEvidence evidence) throws Exception {
		evidenceDao.save(evidence);
	}

	public EvidenceDao getEvidenceDao() {
		return evidenceDao;
	}

	public void setEvidenceDao(EvidenceDao evidenceDao) {
		this.evidenceDao = evidenceDao;
	}

}
