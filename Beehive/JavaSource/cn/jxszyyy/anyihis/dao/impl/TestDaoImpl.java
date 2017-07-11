package cn.jxszyyy.anyihis.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jxszyyy.anyihis.dao.TestDao;
import cn.jxszyyy.anyihis.test.bean.JySqd;

import com.sojava.beehive.framework.common.bean.Result;
import com.sojava.beehive.framework.component.log.service.LogService;
import com.sojava.beehive.framework.define.Filter;
import com.sojava.beehive.framework.define.Order;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.framework.util.FormatUtil;
import com.sojava.beehive.hibernate.dao.impl.AnyihisDaoImpl;

import java.util.Calendar;

import javax.annotation.Resource;

@Repository
@Scope("prototype")
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class TestDaoImpl extends AnyihisDaoImpl implements TestDao {
	private static final long serialVersionUID = 4505803078484643328L;

	@Resource LogService logService;

	@SuppressWarnings("unchecked")
	@Override
	public Result listFeesByNeedConform(Filter[] filters, Page page, Order[] orders) {
		String whereStr = " where 1=1";
		String sortStr = "";
		if (filters != null) {
			for (Filter filter: filters) {
				whereStr += " and " + filter.name + filter.expression + filter.value;
			}
		}
		if (page.getStart() >= 0 && page.getEnd() != 0) {
			if (page.getStart() >= 0) whereStr += " and rownumber >= " + page.getStart();
			if (page.getEnd() != 0) whereStr += " and rownumber <= " + page.getEnd();
		}
		if (orders != null && orders.length > 0) {
			sortStr = " order by ";
			for(Order sort: orders) {
				sortStr += sort.getOrder() + ",";
			}
			sortStr = sortStr.substring(0, sortStr.length()-1);
		}
		String sql= "select * from (" +
					"select " +
					"his.af_year(his.af_inttodate(sqd.sqrq)) as nian, his.af_month(his.af_inttodate(sqd.sqrq)) as yue," +
					"sqd.bhid as bhid," +
					"sqd.yzxh as yzid, sqd.yzzh as yzgid, yz.bm as yzbm, yz.mc as jyxm, yz.jldw as jldw, jyd.yzlx as yzlx," +
					"sqd.jybm as zhxmbm," +
					"sqd.sqrq as sqrq," +
					"his.af_datetoint(CONVERT(date, getdate())) as rq, DATEDIFF(second, CONVERT(date, getdate()), getdate()) as sj," +
					"sqd.sqks as ksbm, sqd.sqys as ysbm," +
					"sqd.lyks as lyks, sqd.lyys as lyys," +
					"'5005' as zxks," +
					"sqd.sqks as hsxz," +
					"11 as ly," +
					"jyd.dj as dj, jyd.sl as zl, jyd.fy as je, jyd.fylb as fylb," +
					"3 as flag," +
					"sqd.zyh, bh0.xm, bh0.xb, ks.mc as ks, ry.mc as ys, sqd.jydh," +
					"row_number() over (order by sqd.zyh, sqd.sqrq, sqd.sqsj) as rownumber" +
					" from " +
					"his.zy_bh0 bh0," +
					"his.zd_yz yz," +
					"his.jy_sqd sqd" +
					" inner join (select jydh,dj,sum(sl) as sl, sum(fy) fy,max(xh) maxxh, fylb, yzlx from his.jy_jyd1 where flag & 8=0 group by jydh, dj, yzlx, fylb) jyd on jyd.jydh = sqd.jydh" +
					" inner join his.zd_sf sf on sqd.jybm=sf.bm" +
					" inner join his.zd_ks ks on sqd.sqks=ks.bm" +
					" inner join his.zd_ry ry on sqd.sqys=ry.bm" +
					" where " +
					" bh0.bhid=sqd.bhid" +
					" and sqd.jybm=yz.bm" +
					" and sqd.flag & 71 = 1" +
					" and his.af_isnulln(sqd.zxbz, 0) & 1 = 0" +
					" and sqd.flag & 32 = 32" +
					") a";
		SQLQuery query = null;
		query = getSession().createSQLQuery("select count(*) from (" + sql + ") a");
		page.setTotal((int) query.uniqueResult());
		query = getSession().createSQLQuery("select * from (" + sql + ") a" + whereStr + sortStr);
		Result result = new Result();
		result.setPage(page);
		result.setRecordArray(query.list());

		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void confirmApplyByInHospital(int jydh) throws Exception {
		Session session = openSession();
		String logInfo = "";
		try {
			JySqd sqd = (JySqd) get(JySqd.class, jydh);
			logInfo="住院患者检验自动确费" +
					"\r\njydh=" + sqd.getJydh() +
					"\r\nbhid=" + sqd.getBhid() +
					"\r\nzyh=" + sqd.getZyh() +
					"\r\njybm=" + sqd.getJybm() +
					"\r\nyzzh=" + sqd.getYzzh() +
					"\r\njygroupid=" + sqd.getJygroupid() +
					"\r\nch=" + sqd.getCh() +
					"\r\nsqks=" + sqd.getSqks() +
					"\r\nsqys=" + sqd.getSqys() +
					"\r\nsqrq=" + sqd.getSqrq() +
					"\r\nsqsj=" + sqd.getSqsj() +
					"\r\nxm=" + sqd.getBhxm();
			int checkCount = 0;
			Calendar c = Calendar.getInstance();
			c.set(1899, 11, 30);
			c.add(Calendar.DAY_OF_MONTH, sqd.getSqrq());
			SuffixDefined sd = new SuffixDefined(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
			String suffixNames[] = new String[2];
			suffixNames[0] =  sd.year + "" + (sd.month < 10 ? "0" + sd.month : sd.month);
			sd.year = FormatUtil.currentYear();
			sd.month = FormatUtil.currentMonth();
			suffixNames[1] = sd.year + "" + (sd.month < 10 ? "0" + sd.month : sd.month);
			String saveSuffixName = suffixNames[0];
			//数据验证
			if (checkJySqd(session, jydh) == 0) checkCount ++;
			for (String suffixName: suffixNames) {
				checkCount += checkDuplicateByBHJFYZ(session, suffixName, sqd.getBhid(), sqd.getYzzh(), 3, sqd.getSqrq(), 11);
				if (checkCount > 0) saveSuffixName = suffixName;
			}
			if (checkCount > 0) return;

			Transaction tx = session.beginTransaction();
			try {
				SQLQuery q = null;
				q = session.createSQLQuery("update his.jy_sqd set flag = flag & 0xffdf, shry = '1115', shrq = his.af_datetoint(CONVERT(date, getdate())) where jydh = :jydh");
				q.setInteger("jydh", jydh);
				logInfo += "\r\n\r\n" + q.executeUpdate() + " : update his.jy_sqd set flag = flag & 0xffdf, shry = '1115', shrq = his.af_datetoint(CONVERT(date, getdate())) where jydh = " + jydh;
				q = session.createSQLQuery("update his.jy_jyd1 set flag = his.af_bitor(flag, 2), shry = '1115', shrq = his.af_datetoint(CONVERT(date, getdate())), shsj = DATEDIFF(second, CONVERT(date, getdate()), getdate()) where jydh = :jydh");
				q.setInteger("jydh", jydh);
				logInfo += "\r\n" + q.executeUpdate() + " : update his.jy_jyd1 set flag = his.af_bitor(flag, 2), shry = '1115', shrq = his.af_datetoint(CONVERT(date, getdate())), shsj = DATEDIFF(second, CONVERT(date, getdate()), getdate()) where jydh = " + jydh;
				q = session.createSQLQuery( "insert into his.zy_bhjfyz" + saveSuffixName + " (nian,yue,bhid,yzid,yzgid,yzbm,yzmc,dw,yzlx,zhxmbm,yzrq,rq,sj,ksbm,ysbm,lyks,lyys,zxks,hsxz,ly,dj,zl,je,fylb,flag) " +
											"select " +
											":year as nian, :month as yue," +
											"sqd.bhid as bhid," +
											"coalesce(sqd.yzxh, 0) as yzid, sqd.yzzh as yzgid, yz.bm as yzbm, yz.mc as yzmc, yz.jldw as dw, jyd.yzlx as yzlx," +
											"sqd.jybm as zhxmbm," +
											"sqd.sqrq as yzrq," +
											"his.af_datetoint(CONVERT(date, getdate())) as rq, DATEDIFF(second, CONVERT(date, getdate()), getdate()) as sj," +
											"sqd.sqks as ksbm, sqd.sqys as ysbm," +
											"sqd.lyks as lyks, sqd.lyys as lyys," +
											"'5005' as zxks," +
											"sqd.sqks as hsxz," +
											"11 as ly," +
											"jyd.dj as dj, jyd.sl as zl, jyd.fy as je, jyd.fylb as fylb," +
											"3 as flag" +
											" from " +
											"his.jy_jyd1 jyd," +
											"his.jy_sqd sqd," +
											"his.zd_yz yz," +
											"his.zy_bfyz0 yz0" +
											" where " +
											" jyd.jydh=sqd.jydh" +
											" and sqd.jybm=yz.bm" +
											" and yz0.bhid=sqd.bhid" +
											" and yz0.mgroupid=sqd.yzzh" +
											" and jyd.jydh = :jydh");
				q.setInteger("year", sd.year);
				q.setInteger("month", sd.month);
				q.setInteger("jydh", jydh);
				logInfo +=  "\r\n" + q.executeUpdate() + " : insert into his.zy_bhjfyz" + saveSuffixName + " (nian,yue,bhid,yzid,yzgid,yzbm,yzmc,dw,yzlx,zhxmbm,yzrq,rq,sj,ksbm,ysbm,lyks,lyys,zxks,hsxz,ly,dj,zl,je,fylb,flag) " +
							"select " +
							sd.year + " as nian, " + sd.month + " as yue," +
							"sqd.bhid as bhid," +
							"coalesce(sqd.yzxh, 0) as yzid, sqd.yzzh as yzgid, yz.bm as yzbm, yz.mc as yzmc, yz.jldw as dw, jyd.yzlx as yzlx," +
							"sqd.jybm as zhxmbm," +
							"sqd.sqrq as yzrq," +
							"his.af_datetoint(CONVERT(date, getdate())) as rq, DATEDIFF(second, CONVERT(date, getdate()), getdate()) as sj," +
							"sqd.sqks as ksbm, sqd.sqys as ysbm," +
							"sqd.lyks as lyks, sqd.lyys as lyys," +
							"'5005' as zxks," +
							"sqd.sqks as hsxz," +
							"11 as ly," +
							"jyd.dj as dj, jyd.sl as zl, jyd.fy as je, jyd.fylb as fylb," +
							"3 as flag" +
							" from " +
							"his.jy_jyd1 jyd," +
							"his.jy_sqd sqd," +
							"his.zd_yz yz," +
							"his.zy_bfyz0 yz0" +
							" where " +
							" jyd.jydh=sqd.jydh" +
							" and sqd.jybm=yz.bm" +
							" and yz0.bhid=sqd.bhid" +
							" and yz0.mgroupid=sqd.yzzh" +
							" and jyd.jydh = " + jydh;
				tx.commit();
				logInfo = "[Success]" + logInfo;
			}
			catch(Exception ex) {
				tx.rollback();
				logInfo = "[Fail] - " + ex.getMessage() + "\r\n" + logInfo;
				throw new ErrorException(this.getClass(), ex);
			}
		}
		finally {
			session.close();
			try {
			logService.record(getClass().getName() + ".confirmApplyByInHospital(int jydh)", "INFO", logInfo);
			}
			catch(Exception ex) {
				new ErrorException(getClass(), ex);
			}
		}
	}

	private int checkJySqd(Session session, int jydh) {
		SQLQuery query = session.createSQLQuery("select " +
												"count(bhid)" +
												" from " +
												"his.jy_sqd sqd" +
												" where " +
												" sqd.flag & 71 = 1" +
												" and his.af_isnulln(sqd.zxbz, 0) & 1 = 0" +
												" and sqd.flag & 32 = 32" +
												" and sqd.jydh=:jydh");
		query.setInteger("jydh", jydh);
		return (int) query.uniqueResult();
	}
	private int checkDuplicateByBHJFYZ(Session session, String suffixName, int bhid, int yzgid, int flag, int yzrq, int ly) {

		SQLQuery query = session.createSQLQuery("select count(*) from his.zy_bhjfyz" + suffixName + " where bhid=:bhid and yzgid=:yzgid and flag=:flag and yzrq=:yzrq and ly=:ly");
		query.setInteger("bhid", bhid);
		query.setInteger("yzgid", yzgid);
		query.setInteger("flag", flag);
		query.setInteger("yzrq", yzrq);
		query.setInteger("ly", ly);
		return (int) query.uniqueResult();
	}

	private class SuffixDefined {
		public int year;
		public int month;

		public SuffixDefined(int year, int month) {
			this.year = year;
			this.month = month;
		}
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
