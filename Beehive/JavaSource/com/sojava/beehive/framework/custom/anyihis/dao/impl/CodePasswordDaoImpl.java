package com.sojava.beehive.framework.custom.anyihis.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.framework.component.user.bean.AnyihisCodePwd;
import com.sojava.beehive.framework.custom.anyihis.dao.CodePasswordDao;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import java.util.List;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class CodePasswordDaoImpl extends BeehiveDaoImpl implements CodePasswordDao {
	private static final long serialVersionUID = -8096510990839501369L;

	@Override
	@SuppressWarnings("unchecked")
	public List<AnyihisCodePwd> list() throws Exception {
		return (List<AnyihisCodePwd>) query(AnyihisCodePwd.class, null, null, new Page(), true);
	}
}
