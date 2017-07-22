package com.sojava.beehive.framework.component.medicalimaging.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.common.identifier.UUID;
import com.sojava.beehive.framework.component.medicalimaging.bean.DicRbrvs;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecuted;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecutedPK;
import com.sojava.beehive.framework.component.medicalimaging.bean.Staff;
import com.sojava.beehive.framework.component.medicalimaging.bean.VGroup;
import com.sojava.beehive.framework.component.medicalimaging.dao.MiExecutedDao;
import com.sojava.beehive.framework.define.DataFlag;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class MiExecutedDaoImpl extends BeehiveDaoImpl implements MiExecutedDao {

	private static final long serialVersionUID = 6738789978458136449L;

	@SuppressWarnings({ "unchecked" })
	@Override
	public void save(Object[] entities) throws Exception {
		for (MiExecuted me : (MiExecuted[]) entities) {
			List<MiExecuted> list = (List<MiExecuted>) this.query(MiExecuted.class, new Criterion[]{Restrictions.eq("id.medicalNo", me.getId().getMedicalNo()), Restrictions.eq("id.medicalItem", me.getId().getMedicalItem())}, null, null, true);
			List<DicRbrvs> rbrvs = (List<DicRbrvs>) this.query(DicRbrvs.class, new Criterion[]{Restrictions.eq("name", me.getId().getMedicalItem().replaceAll("\\Q核磁\\E", "磁共振").replaceAll("\\Q(\\E", "（").replaceAll("\\Q)\\E", "）")), Restrictions.eq("dept", me.getKind())}, null, null, true);
			getSession().flush();
			getSession().clear();
			if (rbrvs.size() == 1) me.setRbrvsId(rbrvs.get(0).getId());
			if (list.size() == 0) {
				MiExecutedPK pk = me.getId();
				pk.setId(UUID.getId());
//				me.setCreateUserId(getUserInfo().getUserId());
//				me.setCreateUserName(getUserInfo().getName());
//				me.setCreateDeptId(getUserInfo().getDeptName());
//				me.setCreateDeptName(getUserInfo().getDeptName());
				me.setCreateTime(new Date());
//				me.setModifyUserId(getUserInfo().getUserId());
//				me.setModifyUserName(getUserInfo().getName());
//				me.setModifyDeptId(getUserInfo().getDeptName());
//				me.setModifyDeptName(getUserInfo().getDeptName());
				me.setModifyTime(me.getCreateTime());
				me.setDataFlag(DataFlag.add);
			} else {
				me.setId(list.get(0).getId());
//				me.setModifyUserId(getUserInfo().getUserId());
//				me.setModifyUserName(getUserInfo().getName());
//				me.setModifyDeptId(getUserInfo().getDeptName());
//				me.setModifyDeptName(getUserInfo().getDeptName());
				me.setModifyTime(new Date());
				me.setDataFlag(DataFlag.modify);
			}
		}
		super.save(entities);
	}

	@Override
	public List<MiExecuted> executedList(String keyword) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getStaffId(String staffName) throws Exception {
		List<Staff> list = (List<Staff>) this.query(Staff.class, new Criterion[]{Restrictions.eq("name", staffName)}, null, null, true);
		if (list.size() == 1) return list.get(0).getId();
		else return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getStaffNurseCoef(String staffName) throws Exception {
		List<VGroup> list = (List<VGroup>) this.query(VGroup.class, new Criterion[]{Restrictions.eq("staffName", staffName)}, null, null, true);
		if (list.size() == 1) return list.get(0).getStaffNurseCoef();
		else return 0d;
	}

}
