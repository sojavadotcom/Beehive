package com.sojava.beehive.framework.component.worksheet.repair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sojava.beehive.common.identifier.UUID;
import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.framework.component.user.dao.UserDao;
import com.sojava.beehive.framework.component.worksheet.repair.bean.Repair;
import com.sojava.beehive.framework.component.worksheet.repair.dao.RepairDao;
import com.sojava.beehive.framework.component.worksheet.repair.service.RepairService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.RecordUtil;

@Service
public class RepairServiceImpl implements RepairService {

	@Resource private RepairDao repairDao;
	@Resource private UserDao userDao;

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject currentRepairs(String userName, String deptName) throws Exception {
		JSONObject result = new JSONObject();
		Page page = new Page(0, 1000);
		List<Criterion> filters = new ArrayList<Criterion>();

		if (deptName.equals("客服中心")) {
			filters.add(Restrictions.in("status", new String[] {"已登记", "已派发", "维修中"}));
		} else {
			filters.add(Restrictions.eq("repairDeptName", deptName));
			filters.add(Restrictions.in("status", new String[] {"已派发", "维修中"}));
		}
		List<Repair> list = (List<Repair>) repairDao.query(Repair.class, filters.toArray(new Criterion[0]), new Order[] {Order.desc("callTime")}, page, true);

		JSONArray _list = new JSONArray();
		for (Repair repair : list) {
			JSONObject _rec = new JSONObject();
			_rec.put("id", repair.getId());
			_rec.put("clientDeptName", repair.getClientDeptName());
			_rec.put("clientUserName", repair.getClientUserName());
			_rec.put("repairDevice", repair.getRepairDevice());
			_rec.put("status", repair.getStatus());
			_rec.put("label", repair.getClientDeptName() + "-" + repair.getRepairDevice() + "[" + repair.getStatus() + "]");
			_list.add(_rec);
		}
		result.put("items", _list);

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject historyRepairs(String userName, String deptName) throws Exception {
		JSONObject result = new JSONObject();
		Page page = new Page(0, 1000);
		List<Criterion> filters = new ArrayList<Criterion>();

		if (deptName.equals("客服中心")) {
			filters.add(Restrictions.in("status", new String[] {"已完成", "已取消"}));
		} else {
			filters.add(Restrictions.eq("repairDeptName", deptName));
			filters.add(Restrictions.in("status", new String[] {"已完成", "已取消"}));
		}
		List<Repair> list = (List<Repair>) repairDao.query(Repair.class, filters.toArray(new Criterion[0]), new Order[] {Order.desc("callTime")}, page, true);

		JSONArray _list = new JSONArray();
		for (Repair repair : list) {
			JSONObject _rec = new JSONObject();
			_rec.put("id", repair.getId());
			_rec.put("clientDeptName", repair.getClientDeptName());
			_rec.put("clientUserName", repair.getClientUserName());
			_rec.put("repairDevice", repair.getRepairDevice());
			_rec.put("status", repair.getStatus());
			_rec.put("label", repair.getClientDeptName() + "-" + repair.getRepairDevice() + "[" + repair.getStatus() + "]");
			_list.add(_rec);
		}
		result.put("items", _list);

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(Repair repair) throws Exception {
		String id = repair.getId();
		String clientDept = repair.getClientDeptName();
		String device = repair.getRepairDevice();
		String clientName = repair.getClientUserName();
		Date callTime = repair.getCallTime();
		String status = repair.getStatus();
		if (id.equals("")) repair.setId(UUID.getId());
		if (clientDept.equals("")) throw new ErrorException(this.getClass(), "请选择报修科室");
		if (device.equals("")) throw new ErrorException(this.getClass(), "请填写报修项目");
		if (clientName.equals("")) throw new ErrorException(this.getClass(), "请填写报修人");
		if (callTime == null) repair.setCallTime(new Date());
		if (status.trim().equals("")) repair.setStatus("已登记");

		if (!repair.getRepairUserName().equals("")) {
			List<UserInfo> list = (List<UserInfo>) userDao.query(UserInfo.class, new Criterion[] {Restrictions.eq("userName", repair.getRepairUserName()).ignoreCase()}, null, null, false);
			if (list.size() == 0) throw new ErrorException(this.getClass(), "未找到该维修人");
			UserInfo user = list.get(0);
			if (user.getDeptName() == null || user.getDeptName().equals("")) repair.setRepairDeptName(user.getUserName());
			else repair.setRepairDeptName(user.getDeptName());
		}

		repairDao.save(repair);
	}

	public void send(Repair repair) throws Exception {
		String status = repair.getStatus().trim();
		if (status.equals("已登记")) {
			repair.setStatus("已派发");
		} else if (status.equals("已派发")) {
			throw new ErrorException(this.getClass(), "该任务已派发过");
		} else {
			throw new ErrorException(this.getClass(), "该任务不能派发");
		}
		repairDao.setStatus(repair);
	}

	@Override
	public JSONObject get(Repair repair) throws Exception {
		JSONObject result = null;

		Repair r = repair;
		if (r != null && r.getId() != null && !r.getId().equals("")) r = (Repair) repairDao.get(Repair.class, r.getId());
		else {
			r = new Repair();
			r.setId(UUID.getId());
			r.setCallTime(new Date());
		}

		result = new RecordUtil().generateJsonByMapping(r);

		return result;
	}

	@Override
	public void switchStatus(String action, Repair repair) throws Exception {
		if (repair == null) throw new ErrorException(this.getClass(), "未接收到数据");
		if (repair.getId() == null || repair.getId().equals("")) throw new ErrorException(this.getClass(), "数据中未设定编号");
		if (action.equals("send")) {
			repair.setStatus("已派发");
		} else if (action.equals("finish")) {
			repair.setStatus("已完成");
		} else if (action.equals("cancel")) {
			repair.setStatus("已取消");
		} else if (action.equals("print")) {
			repair.setStatus("维修中");
		} else {
			throw new ErrorException(this.getClass(), "不能识别的操作");
		}

		repairDao.setStatus(repair);
	}

	public RepairDao getRepairDao() {
		return repairDao;
	}

	public void setRepairDao(RepairDao repairDao) {
		this.repairDao = repairDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
