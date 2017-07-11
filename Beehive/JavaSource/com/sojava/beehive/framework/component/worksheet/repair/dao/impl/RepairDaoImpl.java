package com.sojava.beehive.framework.component.worksheet.repair.dao.impl;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.framework.component.worksheet.repair.bean.Repair;
import com.sojava.beehive.framework.component.worksheet.repair.dao.RepairDao;
import com.sojava.beehive.framework.define.DataFlag;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class RepairDaoImpl extends BeehiveDaoImpl implements RepairDao {
	private static final long serialVersionUID = -1560828842399093823L;

	@Override
	public void save(Object entity) throws Exception {
		Repair repair = (Repair) entity;
		String id = repair.getId();
		Repair _repair = (Repair) getSession().get(Repair.class, id);
		if (_repair == null) {
			_repair = repair;
			_repair.setCreateTime(new Date());
			_repair.setDataFlag(DataFlag.add);
		} else {
			_repair.setRepairUserId(repair.getRepairUserId());
			_repair.setRepairUserName(repair.getRepairUserName());
			_repair.setRepairDeptId(repair.getRepairDeptId());
			_repair.setRepairDeptName(repair.getRepairDeptName());
			_repair.setClientUserId(repair.getClientUserId());
			_repair.setClientUserName(repair.getClientUserName());
			_repair.setClientDeptId(repair.getClientDeptId());
			_repair.setClientDeptName(repair.getClientDeptName());
			_repair.setRepairDevice(repair.getRepairDevice());
			_repair.setCallTime(repair.getCallTime());
			_repair.setArrivalTime(repair.getArrivalTime());
			_repair.setCompleteTime(repair.getCompleteTime());
			_repair.setMaterials(repair.getMaterials());
			_repair.setConsumables(repair.getConsumables());
			_repair.setOldMaterials(repair.getOldMaterials());
			_repair.setClientEvaluated(repair.getClientEvaluated());
			_repair.setClientSign(repair.getClientSign());
			_repair.setMaterialPutStore(repair.getMaterialPutStore());
			_repair.setStoreUserId(repair.getStoreUserId());
			_repair.setStoreUserName(repair.getStoreUserName());
			_repair.setStoreDeptId(repair.getStoreDeptId());
			_repair.setStoreDeptName(repair.getStoreDeptName());
			_repair.setStorerSign(repair.getStorerSign());
			_repair.setStatus(repair.getStatus());
			_repair.setModifyDeptId(repair.getCreateDeptId());
			_repair.setModifyDeptName(repair.getCreateDeptName());
			_repair.setModifyUserId(repair.getCreateUserId());
			_repair.setModifyUserName(repair.getCreateUserName());
			_repair.setModifyTime(new Date());
			_repair.setDataFlag(DataFlag.modify);
		}
		getSession().saveOrUpdate(_repair);
	}

	@Override
	public void setStatus(Repair repair) throws Exception {

		String id = repair.getId();
		String status = repair.getStatus();
		Repair _repair = (Repair) getSession().get(Repair.class, id);

		if (status.equals("维修中")) {
			if (_repair.getStatus().equals("已派发")) {
				if (_repair.getRepairDeptName().equals(repair.getRepairDeptName())) {
					_repair.setPrinted((short) 1);
					_repair.setPrintTime(new Date());
					_repair.setStatus(status);
				}
				if (!_repair.getRepairUserName().equals(repair.getRepairUserName()) && !repair.getRepairUserName().trim().equals("") && _repair.getRepairDeptName().equals(repair.getRepairDeptName())) {
					_repair.setRepairUserName(repair.getRepairUserName());
					_repair.setRepairDeptName(repair.getRepairDeptName());
				}
			}
		} else {
			_repair.setStatus(status);
		}
		getSession().saveOrUpdate(_repair);
	}
}
