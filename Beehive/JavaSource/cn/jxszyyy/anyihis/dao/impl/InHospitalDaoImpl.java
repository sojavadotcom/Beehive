package cn.jxszyyy.anyihis.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jxszyyy.anyihis.dao.InHospitalDao;

import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.AnyihisDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Scope("prototype")
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class InHospitalDaoImpl extends AnyihisDaoImpl implements InHospitalDao {
	private static final long serialVersionUID = -613201315382089751L;

	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findCriticalPatient(Page page) throws Exception {
		List<Map<String, Object>> rest = new ArrayList<Map<String, Object>>();
		SQLQuery query = getSession().createSQLQuery(
				"select " +
				"bh.bhid as bhid, bh.zyh as zyh, bh.xm as xm, bh.xb as xb, bh.ksmc as ks, cw.ch as ch, ry.mc as ys, yz.mc as hljb" +
				" from " +
				"his.view_cw cw" +
				" left join (select * from his.view_zybh where state = 1) bh on cw.ksbm=bh.ks and cw.bs=bh.bs and cw.ch=bh.ch" +
				" left join (select * from his.zd_yz where lx=1) yz on bh.hlbm=yz.bm " +
				" left join his.zd_ry ry on bh.zzys = ry.bm" +
				" where " +
				" cw.state<2" +
				" and yz.mc in ('一级护理', '肿瘤科Ⅰ级护理', '重症监护')" +
				" order by bh.ksmc, cw.ch");
		for(Object[] rs: (List<Object[]>) query.list()) {
			Map<String, Object> rec = new HashMap<String, Object>();
			rec.put("bhid", rs[0]);
			rec.put("zyh", rs[1]);
			rec.put("xm", rs[2]);
			rec.put("xb", rs[3]);
			rec.put("ks", rs[4]);
			rec.put("ch", rs[5]);
			rec.put("ys", rs[6]);
			rec.put("hljb", rs[7]);
			rest.add(rec);
		}

		return rest;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findRefund(Criterion[] filters, Order[] orders, Page page) throws Exception {
		List<Map<String, Object>> rest = new ArrayList<Map<String, Object>>();
		String where = "1=1";
		if (filters != null) {
			for(Criterion c: filters) {
				where += " and (" + c.toString() + ")";
			}
		}
		SQLQuery query = getSession().createSQLQuery(
				"select * from (" +
				"select " +
				"cj.bhid as bhid,bh.zyh as zyh,bh.xm as xm,bh.xb as xb," +
				"dateadd(dd, cj.rq, '18991230')+dateadd(second, cj.sj, '19000101') as cjsj, cjr.mc as cjr, ks.mc as ks," +
				"his.af_str(case when his.af_nullc(yz.mc)=0 then yz.mc else cj.yzmc end) as yz, cj.cjyy as yy," +
				"dateadd(dd, cj.yzrq, '18991230') as yzrq, cj.dj as dj,cj.sl as sl, cj.fy as je, yzys.mc as ys, his.af_isnullc(zxks.mc, ks.mc) as zxks," +
				"dateadd(dd, cj.shrq, '18991230')+dateadd(second, cj.shsj, '19000101') as shsj, shr.mc as shr, yz.jldw as jldw" +
				" from " +
				"his.zy_bh0 bh," +
				"his.zd_ks ks," +
				"his.zd_ry yzys," +
				"his.zd_ry cjr," +
				"his.zd_ry shr," +
				"his.zy_cjyz cj" +
				" left join his.zd_ks zxks on cj.zxks=zxks.bm" +
				" left join his.zd_yz yz on yz.bm=cj.yzbm and yz.lx not in (0,3,4)" +
				" left join his.zy_bfyz0 yz0 on yz0.bhid=cj.bhid and yz0.mgroupid=cj.groupid" +
				" left join his.zy_bfyz1 yz1 on yz1.dgroupid=yz0.mgroupid and yz1.xh=cj.yzxh" +
				" where " +
				" cj.bhid=bh.bhid" +
				" and cj.ksbm=ks.bm" +
				" and cj.yzys=yzys.bm" +
				" and cj.lrry=cjr.bm" +
				" and cj.shry=shr.bm" +
				" and cj.yzlx not in (0,3,4) and cj.flag & 128=0" +
				" and cj.yzzl in(0,1,2,9,15,16,17,18,22,24,26,27,28,31,33)" +
				" and bh.state & 0x91=0" +
				") a" +
				" where " + where +
				" order by zyh,cjsj");
		for(Object[] rs: (List<Object[]>) query.list()) {
			Map<String, Object> rec = new HashMap<String, Object>();
			rec.put("bhid", rs[0]);
			rec.put("zyh", rs[1]);
			rec.put("xm", rs[2]);
			rec.put("xb", rs[3]);
			rec.put("cjsj", rs[4]);
			rec.put("cjr", rs[5]);
			rec.put("ks", rs[6]);
			rec.put("yz", rs[7]);
			rec.put("yy", rs[8]);
			rec.put("yzrq", rs[9]);
			rec.put("dj", rs[10]);
			rec.put("sl", rs[11]);
			rec.put("je", rs[12]);
			rec.put("ys", rs[13]);
			rec.put("zxks", rs[14]);
			rec.put("shsj", rs[15]);
			rec.put("shr", rs[16]);
			rec.put("jldw", rs[17]);
			rest.add(rec);
		}

		return rest;
	}
}
