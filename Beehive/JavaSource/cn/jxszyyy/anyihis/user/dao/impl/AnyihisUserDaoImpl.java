package cn.jxszyyy.anyihis.user.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jxszyyy.anyihis.dic.bean.ZdKs;
import cn.jxszyyy.anyihis.dic.bean.ZdRy;
import cn.jxszyyy.anyihis.user.dao.AnyihisUserDao;

import com.sojava.beehive.framework.custom.anyihis.service.CodePasswordService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.framework.util.hibernate.CriterionUtil;
import com.sojava.beehive.hibernate.dao.impl.AnyihisDaoImpl;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class AnyihisUserDaoImpl extends AnyihisDaoImpl implements AnyihisUserDao {
	private static final long serialVersionUID = -5196415943060825011L;

	@Resource CodePasswordService codePasswordService;

	@SuppressWarnings("unchecked")
	@Override
	public List<ZdKs> deptList(String keyword) throws Exception {
		List<ZdKs> list = (List<ZdKs>) query(ZdKs.class, new Criterion[]{Restrictions.like("dh", keyword).ignoreCase()}, null, new Page(), false);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZdRy> userList(String keyword) throws Exception {
		List<ZdRy> list = (List<ZdRy>) query(ZdRy.class, new Criterion[]{Restrictions.like("dh", keyword).ignoreCase()}, null, new Page(), false);

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validPassword(String userName, String password) throws Exception {
		boolean result = false;
		String code = "";
		for (char key : password.toCharArray()) {
			code += codePasswordService.getCode(key);
		}
		CriterionUtil cu = new CriterionUtil(getSession());
		cu.addCriterion(Restrictions.eq("dh", userName).ignoreCase());
		cu.addCriterion(Restrictions.eq("password", code));
		List<ZdRy> list = (List<ZdRy>) cu.createCriteria(ZdRy.class, null, false).list();
		result = !list.isEmpty();

		return result;
	}

	@Override
	public void changePassword(String userName, String password) throws Exception {}

	public CodePasswordService getCodePasswordService() {
		return codePasswordService;
	}

	public void setCodePasswordService(CodePasswordService codePasswordService) {
		this.codePasswordService = codePasswordService;
	}

}
