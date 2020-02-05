package com.sojava.beehive.framework.component.data.dao;

import com.sojava.beehive.framework.component.data.bean.NcovGoods;
import com.sojava.beehive.framework.exception.ErrorException;

import java.util.Date;
import java.util.List;

public interface NcovDataDao {

	List<NcovGoods> goodsSumByDestType(Date date, String srcDept, String destDept, String type, String kind) throws ErrorException;
}
