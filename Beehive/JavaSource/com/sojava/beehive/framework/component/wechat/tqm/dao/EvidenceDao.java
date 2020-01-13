package com.sojava.beehive.framework.component.wechat.tqm.dao;

import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface EvidenceDao extends BeehiveDao {

	byte[] getPhoto(int id) throws Exception;
}
