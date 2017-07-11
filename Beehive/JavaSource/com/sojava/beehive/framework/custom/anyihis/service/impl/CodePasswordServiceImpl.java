package com.sojava.beehive.framework.custom.anyihis.service.impl;

import com.sojava.beehive.framework.component.user.bean.AnyihisCodePwd;
import com.sojava.beehive.framework.custom.anyihis.dao.CodePasswordDao;
import com.sojava.beehive.framework.custom.anyihis.service.CodePasswordService;
import com.sojava.beehive.framework.define.Page;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class CodePasswordServiceImpl implements CodePasswordService {

	@Resource private CodePasswordDao codePasswordDao;

	@Override
	public List<AnyihisCodePwd> list() throws Exception {
		return codePasswordDao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCode(char word) throws Exception {
		String code = null;
		List<AnyihisCodePwd> words = (List<AnyihisCodePwd>) codePasswordDao.query(AnyihisCodePwd.class, new Criterion[]{Restrictions.eq("word", word+"")}, null, new Page(), true);
		if (!words.isEmpty()) code = words.get(0).getCode();

		return code;
	}

	public CodePasswordDao getCodePasswordDao() {
		return codePasswordDao;
	}

	public void setCodePasswordDao(CodePasswordDao codePasswordDao) {
		this.codePasswordDao = codePasswordDao;
	}
}
