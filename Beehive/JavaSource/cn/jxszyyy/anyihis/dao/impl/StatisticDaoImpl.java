package cn.jxszyyy.anyihis.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jxszyyy.anyihis.bean.InHospitalOfDept;
import cn.jxszyyy.anyihis.dao.StatisticDao;

import com.sojava.beehive.framework.define.Filter;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.AnyihisDaoImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Scope("prototype")
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class StatisticDaoImpl extends AnyihisDaoImpl implements StatisticDao {
	private static final long serialVersionUID = 3947101735259289060L;

	@SuppressWarnings("unchecked")
	@Override
	public List<InHospitalOfDept> inhospitalOfDept(Filter[] filters, Page page) throws Exception {
		List<InHospitalOfDept> result = new ArrayList<InHospitalOfDept>();
		String sql= "select " +
					"cr.ksbm," +
					"ks.mc," +
					"cr.f_lx," +
					"case cr.f_lx" +
					" when 1 then '入院'" +
					" when 2 then '转入'" +
					" when 3 then '取消出院'" +
					" when 4 then '取消转出'" +
					" when 101 then '转出'" +
					" when 102 then '出院'" +
					" else convert(varchar, cr.f_lx) end as '类型'," +
					"count(*) as rs" +
					" from " +
					"his.zy_bhcrbh cr" +
					" left join his.zy_bhbaby baby on baby.bbid=cr.bhid," +
					"his.zd_ks ks" +
					" where " +
					"his.af_nulln(baby.bbid)=1" +
					" and cr.ksbm=ks.bm" +
					" and his.af_inttodate(cr.rq) between '20160426' and '20160525'" +
					" group by cr.ksbm, ks.mc, cr.f_lx";
		SQLQuery sq = getSession().createSQLQuery(sql);
		for (Object[] recs: (List<Object[]>) sq.list()) {
			result.add(new InHospitalOfDept((String) recs[0], (String) recs[1], (String) recs[3], Integer.parseInt(String.valueOf(recs[4]))));
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int inhospitalTotal(Date start, Date end) throws Exception {
		InhospitalNum in = new InhospitalNum();
		InhospitalNum in1 = new InhospitalNum();
		int currentTotal = currentInhospitalForTotal();
		String sql= "select " +
					"cr.f_lx," +
					"case cr.f_lx" +
					" when 1 then '入院'" +
					" when 2 then '转入'" +
					" when 3 then '取消出院'" +
					" when 4 then '取消转出'" +
					" when 101 then '转出'" +
					" when 102 then '出院'" +
					" else convert(varchar, cr.f_lx) end as '类型'," +
					"count(*) as rs" +
					" from " +
					"his.zy_bhcrbh cr" +
					" left join his.zy_bhbaby baby on baby.bbid=cr.bhid," +
					"his.zd_ks ks" +
					" where " +
					"his.af_nulln(baby.bbid)=1" +
					" and cr.ksbm=ks.bm" +
					" and his.af_inttodate(cr.rq) between :start and :end" +
					" group by cr.f_lx" +
					" order by cr.f_lx";
		Calendar c = Calendar.getInstance();
		c.setTime(end);
		c.add(Calendar.DAY_OF_MONTH, 1);
		Date nextStart = c.getTime();

		SQLQuery sq = getSession().createSQLQuery(sql);
		sq.setDate("start", start);
		sq.setDate("end", end);
		for (Object[] rs: (List<Object[]>) sq.list()) {
			String _type = rs[1].toString();
			String _val = rs[2].toString();
			if (_type.equals("入院")) in.ry = Integer.parseInt(_val);
			else if (_type.equals("转入")) in.zr = Integer.parseInt(_val);
			else if (_type.equals("取消出院")) in.qxcy = Integer.parseInt(_val);
			else if (_type.equals("取消转出")) in.qxzc = Integer.parseInt(_val);
			else if (_type.equals("转出")) in.zc = Integer.parseInt(_val);
			else if (_type.equals("出院")) in.cy = Integer.parseInt(_val);
		}
		in.amount = in.ry + in.zr + in.qxcy + in.qxzc - in.zc - in.cy;

		sq = getSession().createSQLQuery(sql);
		sq.setDate("start", nextStart);
		sq.setDate("end", new Date());
		for (Object[] rs: (List<Object[]>) sq.list()) {
			String _type = rs[1].toString();
			String _val = rs[2].toString();
			if (_type.equals("入院")) in1.ry = Integer.parseInt(_val);
			else if (_type.equals("转入")) in1.zr = Integer.parseInt(_val);
			else if (_type.equals("取消出院")) in1.qxcy = Integer.parseInt(_val);
			else if (_type.equals("取消转出")) in1.qxzc = Integer.parseInt(_val);
			else if (_type.equals("转出")) in1.zc = Integer.parseInt(_val);
			else if (_type.equals("出院")) in1.cy = Integer.parseInt(_val);
		}
		in1.amount = in1.ry + in1.zr + in1.qxcy + in1.qxzc - in1.zc - in1.cy;

		return currentTotal - in1.amount;
	}

	@Override
	public int currentInhospitalForTotal() throws Exception {
		String sql= "select sum(rs) from (" +
					"select " +
					"bhbh.ksbm, count(*) as rs" +
					" from his.zy_bhbh bhbh" +
					" left join his.zy_bhbaby baby on baby.bbid=bhbh.bhid" +
					" where " +
					"bhbh.state=1" +
					" and his.af_isnullc(bhbh.ch,'a')<>'a'" +
					" and his.af_nulln(baby.bbid)=1" +
					" group by bhbh.ksbm" +
					") a";
		SQLQuery sq = getSession().createSQLQuery(sql);

		return (int) sq.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<InHospitalOfDept> currentInhospitalForDept() throws Exception {
		List<InHospitalOfDept> result = new ArrayList<InHospitalOfDept>();
		String sql= "select " +
					"bhbh.ksbm, ks.mc, count(*) as rs" +
					" from his.zy_bhbh bhbh" +
					" left join his.zy_bhbaby baby on baby.bbid=bhbh.bhid," +
					"his.zy_ks ks" +
					" where " +
					"bhbh.state=1" +
					" and his.af_isnullc(bhbh.ch,'a')<>'a'" +
					" and his.af_nulln(baby.bbid)=1" +
					" and bhbh.ksbm=ks.bm" +
					" group by bhbh.ksbm, ks.mc";
		SQLQuery sq = getSession().createSQLQuery(sql);
		for (Object[] recs: (List<Object[]>) sq.list()) {
			result.add(new InHospitalOfDept((String) recs[0], (String) recs[1], null, Integer.parseInt(String.valueOf(recs[2]))));
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InHospitalOfDept> outpatientTotal(Date start, Date end) throws Exception {
		List<InHospitalOfDept> result = new ArrayList<InHospitalOfDept>();
		String sql= "select " +
					"count(bh.lsh) as amount," +
					"his.af_inttodate(sf.sfrq) as d" +
					" from " +
					"(select *, '201501' as kind from his.mz_bh201501 union all select *, '201502' as kind from his.mz_bh201502 union all select *, '201503' as kind from his.mz_bh201503 union all select *, '201504' as kind from his.mz_bh201504 union all select *, '201505' as kind from his.mz_bh201505 union all select *, '201506' as kind from his.mz_bh201506 union all select *, '201507' as kind from his.mz_bh201507 union all select *, '201508' as kind from his.mz_bh201508 union all select *, '201509' as kind from his.mz_bh201509 union all select *, '201510' as kind from his.mz_bh201510 union all select *, '201511' as kind from his.mz_bh201511 union all select *, '201512' as kind from his.mz_bh201512 union all select *, '201601' as kind from his.mz_bh201601 union all select *, '201602' as kind from his.mz_bh201602 union all select *, '201603' as kind from his.mz_bh201603 union all select *, '201604' as kind from his.mz_bh201604 union all select *, '201605' as kind from his.mz_bh201605 union all select *, '201606' as kind from his.mz_bh201606 union all select *, '201607' as kind from his.mz_bh201607 union all select *, '201608' as kind from his.mz_bh201608 union all select *, '201609' as kind from his.mz_bh201609 union all select *, '201610' as kind from his.mz_bh201610 union all select *, '201611' as kind from his.mz_bh201611 union all select *, '201612' as kind from his.mz_bh201612) as bh" +
					" left join (select *, '201501' as kind from his.mz_sf201501 union all select *, '201502' as kind from his.mz_sf201502 union all select *, '201503' as kind from his.mz_sf201503 union all select *, '201504' as kind from his.mz_sf201504 union all select *, '201505' as kind from his.mz_sf201505 union all select *, '201506' as kind from his.mz_sf201506 union all select *, '201507' as kind from his.mz_sf201507 union all select *, '201508' as kind from his.mz_sf201508 union all select *, '201509' as kind from his.mz_sf201509 union all select *, '201510' as kind from his.mz_sf201510 union all select *, '201511' as kind from his.mz_sf201511 union all select *, '201512' as kind from his.mz_sf201512 union all select *, '201601' as kind from his.mz_sf201601 union all select *, '201602' as kind from his.mz_sf201602 union all select *, '201603' as kind from his.mz_sf201603 union all select *, '201604' as kind from his.mz_sf201604 union all select *, '201605' as kind from his.mz_sf201605 union all select *, '201606' as kind from his.mz_sf201606 union all select *, '201607' as kind from his.mz_sf201607 union all select *, '201608' as kind from his.mz_sf201608 union all select *, '201609' as kind from his.mz_sf201609 union all select *, '201610' as kind from his.mz_sf201610 union all select *, '201611' as kind from his.mz_sf201611 union all select *, '201612' as kind from his.mz_sf201612) as sf on bh.kind=sf.kind and bh.lsh=sf.lsh" +
					" left join his.mz_tk tk on tk.state not in (7) and sf.sfid=tk.sfid" +
					" left join (select *, '201501' as kind from his.mz_fy201501 union all select *, '201502' as kind from his.mz_fy201502 union all select *, '201503' as kind from his.mz_fy201503 union all select *, '201504' as kind from his.mz_fy201504 union all select *, '201505' as kind from his.mz_fy201505 union all select *, '201506' as kind from his.mz_fy201506 union all select *, '201507' as kind from his.mz_fy201507 union all select *, '201508' as kind from his.mz_fy201508 union all select *, '201509' as kind from his.mz_fy201509 union all select *, '201510' as kind from his.mz_fy201510 union all select *, '201511' as kind from his.mz_fy201511 union all select *, '201512' as kind from his.mz_fy201512 union all select *, '201601' as kind from his.mz_fy201601 union all select *, '201602' as kind from his.mz_fy201602 union all select *, '201603' as kind from his.mz_fy201603 union all select *, '201604' as kind from his.mz_fy201604 union all select *, '201605' as kind from his.mz_fy201605 union all select *, '201606' as kind from his.mz_fy201606 union all select *, '201607' as kind from his.mz_fy201607 union all select *, '201608' as kind from his.mz_fy201608 union all select *, '201609' as kind from his.mz_fy201609 union all select *, '201610' as kind from his.mz_fy201610 union all select *, '201611' as kind from his.mz_fy201611 union all select *, '201612' as kind from his.mz_fy201612) as fy on bh.kind=fy.kind and sf.sfid=fy.sfid" +
					" left join his.zd_mzhs1 hs1 on fy.hsff = hs1.hsff and fy.fylb=hs1.flxh" +
					" where " +
					"sf.lsh=bh.lsh and sf.kind=bh.kind" +
					" and hs1.hsff = 0" +
					" and fy.sfid = sf.sfid" +
					" and fy.kind = sf.kind" +
					" and fy.fylb in (53, 54, 57, 58)" +
					" and his.af_inttodate(sf.sfrq) between :start and :end" +
					" group by his.af_inttodate(sf.sfrq)" +
					" order by his.af_inttodate(sf.sfrq)";
		SQLQuery sq = getSession().createSQLQuery(sql);
		sq.setDate("start", start);
		sq.setDate("end", end);
		for(Object[] rs: (List<Object[]>) sq.list()) {
			result.add(new InHospitalOfDept(null, null, rs[1].toString(), Integer.parseInt(rs[0].toString())));
		}

		return result;
	}

	private class InhospitalNum {
		public int ry;
		public int zr;
		public int qxcy;
		public int qxzc;
		public int zc;
		public int cy;
		public int amount;

		public InhospitalNum() {
			this.ry = 0;
			this.zr = 0;
			this.qxcy = 0;
			this.qxzc = 0;
			this.zc = 0;
			this.cy = 0;
			this.amount = 0;
		}
	}
}
