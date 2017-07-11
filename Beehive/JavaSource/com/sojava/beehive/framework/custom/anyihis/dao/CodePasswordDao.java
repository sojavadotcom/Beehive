package com.sojava.beehive.framework.custom.anyihis.dao;

import com.sojava.beehive.framework.component.user.bean.AnyihisCodePwd;
import com.sojava.beehive.hibernate.dao.BeehiveDao;

import java.util.List;

public interface CodePasswordDao extends BeehiveDao {

	List<AnyihisCodePwd> list() throws Exception;
}