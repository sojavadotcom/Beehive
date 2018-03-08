package com.sojava.beehive.framework.component.medicalimaging.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.common.identifier.UUID;
import com.sojava.beehive.framework.component.medicalimaging.StaffCoefType;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecuted;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecutedPK;
import com.sojava.beehive.framework.component.medicalimaging.bean.Staff;
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

	@Override
	public void save(Object[] entities) throws Exception {

		List<MiExecutedPK> keys = new ArrayList<>();
		for (MiExecuted me : (MiExecuted[]) entities) {
			keys.add(me.getId());
		}
		keys = findMiExecutedPK(keys.toArray(new MiExecutedPK[0]));

		for (MiExecuted me : (MiExecuted[]) entities) {
			String id = null;
			for(MiExecutedPK key: keys) {
				if (key.getMedicalNo().equals(me.getId().getMedicalNo()) && key.getMedicalItem().equals(me.getId().getMedicalItem())) {
					id = key.getId();
					break;
				}
			}
			if (id == null) {
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
				me.getId().setId(id);
//				me.setModifyUserId(getUserInfo().getUserId());
//				me.setModifyUserName(getUserInfo().getName());
//				me.setModifyDeptId(getUserInfo().getDeptName());
//				me.setModifyDeptName(getUserInfo().getDeptName());
				me.setModifyTime(new Date());
				me.setDataFlag(DataFlag.modify);
			}
			super.save(me);
		}
	}

	@Override
	public List<MiExecuted> executedList(String keyword) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<MiExecutedPK> findMiExecutedPK(MiExecutedPK[] keys) {
		List<MiExecutedPK> result = new ArrayList<MiExecutedPK>();

		SQLQuery query = this.getSession().createSQLQuery("select id, medical_no, medical_item from medicalimaging.mi_executed where medical_no||'-'||medical_item in (:keys)");
		String queryStr[] = new String[keys.length];
		for (int i = 0; i < keys.length; i ++) {
			MiExecutedPK key = keys[i];
			queryStr[i] = key.getMedicalNo() + "-" + key.getMedicalItem();
		}
		query.setParameterList("keys", queryStr);
		List<Object[]> list = query.list();
		for(Object[] k: list) {
			result.add(new MiExecutedPK(k[0].toString(), k[1].toString(), k[2].toString()));
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getStaffId(String staffName) throws Exception {
		List<Staff> list = (List<Staff>) this.query(Staff.class, new Criterion[]{Restrictions.eq("name", staffName)}, null, null, true);
		if (list.size() == 1) return list.get(0).getId();
		else return null;
	}

	@Override
	public double getStaffCoef(int staffId, StaffCoefType type) throws Exception {
		Staff staff = (Staff) this.get(Staff.class, staffId);
		if (staff == null) {
			return 0d;
		}
		switch (type) {
			case Tech:
				return staff.getTechCoef().getPoints();
			case Diagnose:
				return staff.getDiagnosCoef().getPoints();
			case Verify:
				return staff.getVerifyCoef().getPoints();
			case Nurse:
				return staff.getNurseCoef().getPoints();
			default:
				return 0d;
		}
	}

}
