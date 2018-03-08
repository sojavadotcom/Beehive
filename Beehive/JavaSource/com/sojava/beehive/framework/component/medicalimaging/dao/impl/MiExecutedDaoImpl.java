package com.sojava.beehive.framework.component.medicalimaging.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.common.identifier.UUID;
import com.sojava.beehive.framework.component.medicalimaging.StaffCoefType;
import com.sojava.beehive.framework.component.medicalimaging.bean.DicRbrvs;
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
	private final Properties property = new Properties();

	@SuppressWarnings("unchecked")
	@Override
	public void save(Object[] entities) throws Exception {

		//调取所有kind有关的RBRVS
		for(DicRbrvs r: (List<DicRbrvs>) this.query(DicRbrvs.class, new Criterion[]{Restrictions.eq("dept", this.property.getProperty("kind"))}, null, null, true)) {
			this.property.put("RBRVS:" + r.getName(), r.getId());
		}
		findMiExecutedPK(((List<MiExecutedPK>) this.property.get("keys")).toArray(new MiExecutedPK[0]));

		for (MiExecuted me : (MiExecuted[]) entities) {
			String id = this.property.getProperty("PK:" + me.getId().getMedicalNo() + "-" + me.getId().getMedicalItem());
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

	public Properties getProperty() {
		return this.property;
	}

	@Override
	public List<MiExecuted> executedList(String keyword) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	public void findMiExecutedPK(MiExecutedPK[] keys) {

		SQLQuery query = this.getSession().createSQLQuery("select id, medical_no, medical_item from medicalimaging.mi_executed where medical_no||'-'||medical_item in (:keys)");
		String queryStr[] = new String[keys.length];
		for (int i = 0; i < keys.length; i ++) {
			MiExecutedPK key = keys[i];
			queryStr[i] = key.getMedicalNo() + "-" + key.getMedicalItem();
		}
		query.setParameterList("keys", queryStr);
		for(Object[] k: (List<Object[]>) query.list()) {
			this.property.setProperty("PK:" + k[1].toString() + "-" + k[2].toString(), k[0].toString());
		}
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
