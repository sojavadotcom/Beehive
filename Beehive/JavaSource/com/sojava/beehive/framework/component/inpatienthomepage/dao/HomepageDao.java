package com.sojava.beehive.framework.component.inpatienthomepage.dao;

import java.util.List;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.Dictionary;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.IcdTransform;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.RecordRangeType;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.VIcdTransform;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

public interface HomepageDao extends BeehiveDao {

	List<String> typeList(String kind) throws Exception;
	List<Dictionary> getDictionary(String kind) throws Exception;
	List<VIcdTransform> getHISDiagnosis(RecordRangeType type) throws Exception;
	List<IcdTransform> getICD20Diagnosis(RecordRangeType type) throws Exception;
	boolean emptyCheckinfo(int pid) throws Exception;
}