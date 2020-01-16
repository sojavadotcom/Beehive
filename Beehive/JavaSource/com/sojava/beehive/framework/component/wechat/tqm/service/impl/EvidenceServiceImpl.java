package com.sojava.beehive.framework.component.wechat.tqm.service.impl;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryEvidence;
import com.sojava.beehive.framework.component.wechat.tqm.dao.EvidenceDao;
import com.sojava.beehive.framework.component.wechat.tqm.service.EvidenceService;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class EvidenceServiceImpl implements EvidenceService {

	@Resource private EvidenceDao evidenceDao;

	private final String REGEX = "^.*\\Qcn.org.jxszyyy.casehistory.evidence.\\E\\d{1,}(\\-\\d{1,}){0,1}$";

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

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseHistoryEvidence> queryEvidence(int standardId, int paperNum) throws Exception {
		List<CaseHistoryEvidence> evidences = (List<CaseHistoryEvidence>) evidenceDao.query(CaseHistoryEvidence.class,
			new Criterion[] {
				Restrictions.eq("standardId", standardId),
				Restrictions.eq("paperNum", paperNum),
			},
			new Order[] {
				Order.asc("itemNum")
			},
		null, false);

		return evidences;
	}

	@Override
	public List<Map<String, Object>> getPhotos(int standardId, int paperNum) throws Exception {
		List<Map<String, Object>> photos = new ArrayList<Map<String, Object>>();

		List<CaseHistoryEvidence> evidences = queryEvidence(standardId, paperNum);
		for (CaseHistoryEvidence evidence : evidences) {
			Map<String, Object> photo = new HashMap<String, Object>();
			photo.put("num", evidence.getItemNum()+"");
			photo.put("label", evidence.getItemLabel());
			photo.put("photo", Base64.getEncoder().encodeToString(evidence.getPhoto()));

			photos.add(photo);
		}

		return photos;
	}

	public EvidenceDao getEvidenceDao() {
		return evidenceDao;
	}

	public void setEvidenceDao(EvidenceDao evidenceDao) {
		this.evidenceDao = evidenceDao;
	}

}
