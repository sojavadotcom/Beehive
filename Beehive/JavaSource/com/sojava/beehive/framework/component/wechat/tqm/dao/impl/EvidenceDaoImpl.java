package com.sojava.beehive.framework.component.wechat.tqm.dao.impl;

import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryEvidence;
import com.sojava.beehive.framework.component.wechat.tqm.dao.EvidenceDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class EvidenceDaoImpl extends BeehiveDaoImpl implements EvidenceDao {
	private static final long serialVersionUID = -3066101265689820784L;

	@Override
	public byte[] getPhoto(int id) throws Exception {
		Session session = getSession();
		CaseHistoryEvidence e = (CaseHistoryEvidence) session.get(CaseHistoryEvidence.class, id);

		return e.getPhoto();
	}

	@Override
	public void save(Object evidence) throws Exception {
		Session session = getSession();
		session.save((CaseHistoryEvidence) evidence);
	}
}
