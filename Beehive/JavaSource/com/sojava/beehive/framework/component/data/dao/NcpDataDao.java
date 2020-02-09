package com.sojava.beehive.framework.component.data.dao;

import com.sojava.beehive.framework.component.data.bean.NcpGoods;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.Date;
import java.util.List;

public interface NcpDataDao extends BeehiveDao {

	List<NcpGoods> goodsSumByDestType(Date date, String srcDept, String destDept, String type, String kind) throws ErrorException;
}
