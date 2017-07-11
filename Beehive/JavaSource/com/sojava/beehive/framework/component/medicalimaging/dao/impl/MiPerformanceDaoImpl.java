package com.sojava.beehive.framework.component.medicalimaging.dao.impl;

import com.sojava.beehive.framework.component.medicalimaging.bean.CalculatePerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.DicRbrvs;
import com.sojava.beehive.framework.component.medicalimaging.bean.RbrvsPrice;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.dao.MiPerformanceDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class MiPerformanceDaoImpl extends BeehiveDaoImpl implements MiPerformanceDao {
	private static final long serialVersionUID = 64685891293059799L;

	public void calRbrvsPrice(WorkStatistic workStatistic) throws Exception {
		Session session = null;
		Transaction t = null;
		try {
			session = getSessionFactory().openSession();
			t = session.beginTransaction();

			Mode calMode;
			/*
			 * Sharing Mode
			 */
//			calMode = new SharingMode(getSession(), session, workStatistic);
			/*
			 * Single Mode
			 */
			calMode = new SingleMode(getSession(), session, workStatistic);

			//Calculate the Main data of Work statistic.
			calMode.calMainInfo();
			//Calculate items of Workload for Price of RBRVS.
			calMode.calRbrvsPrice();

			t.commit();

			/*
			 * 核算开始
			 */
			t = session.beginTransaction();

			calMode.calPerformance();

			t.commit();
		}
		catch(Exception ex) {
			t.rollback();
			throw ex;
		}
		finally {
			session.flush();
			session.clear();
		}
	}
}

interface Mode {
	void calMainInfo() throws Exception;
	void calRbrvsPrice() throws Exception;
	void calPerformance() throws Exception;
}

class SharingMode implements Mode {
	private Session querySession;
	private Session updateSession;
	private WorkStatistic workStatistic;

	public SharingMode(Session querySession, Session updateSession, WorkStatistic workStatistic) {
		this.querySession = querySession;
		this.updateSession = updateSession;
		this.workStatistic = workStatistic;
	}

	public void calMainInfo() throws Exception {
		Query stmt;

		/*
		 * Sum the Point total.
		 * include Technician,Diagnostician,Verifier
		 */
		workStatistic.setPointTotal(0d);
		//Technician Group
		stmt = this.querySession.createQuery(
				"select "
				+ "sum(b.technician) as amount"
				+ " from "
				+ "	MiExecuted a,"
				+ "	DicRbrvs b"
				+ " where "
				+ "	a.rbrvsId=b.id"
				+ "	and (a.executeTechnician<>'' or a.executeTechnicianAssociate<>'')"
				+ " and a.reportTime between :begin and :end"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
//		workStatistic.setPointTotal(workStatistic.getPointTotal() + (double) stmt.uniqueResult());
		workStatistic.setTechPointTotal((double) stmt.uniqueResult());
		//Diagnostician Group
		stmt = this.querySession.createQuery(
				"select "
				+ " sum(b.diagnostician) as amount"
				+ " from "
				+ "	MiExecuted a,"
				+ "	DicRbrvs b"
				+ " where "
				+ "	a.rbrvsId=b.id"
				+ "	 and a.reportTime between :begin and :end"
				+ "	 and a.status=:status"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		stmt.setString("status", "已审核");
//		workStatistic.setPointTotal(workStatistic.getPointTotal() + (double) stmt.uniqueResult());
		workStatistic.setDiagnoPointTotal((double) stmt.uniqueResult());

		/*
		 * Calculateing the Point value.
		 */
//		workStatistic.setPointValue(workStatistic.getMedicalTotal()/workStatistic.getPointTotal());
		workStatistic.setTechPointValue(workStatistic.getTechTotal()/workStatistic.getTechPointTotal());
		workStatistic.setDiagnoPointValue(workStatistic.getDiagnoTotal()/workStatistic.getDiagnoPointTotal());

		this.updateSession.saveOrUpdate(workStatistic);
	}

	@SuppressWarnings("unchecked")
	public void calRbrvsPrice() throws Exception {
		Query stmt;

		/*
		 * Empty the RbrvsPrice of WorkStatistic.
		 */
		stmt = this.updateSession.createQuery("delete from RbrvsPrice where workStatistic=:workStatistic");
		stmt.setEntity("workStatistic", workStatistic);
		stmt.executeUpdate();

		/*
		 * Sum Workload for Technician,Diagnostician,Verifier.
		 */
		//投照组
		stmt = this.querySession.createQuery(
				"select "
				+ "b.id, b.technician, sum(b.technician) as amount, count(b.technician) as quantity"
				+ " from "
				+ "MiExecuted a,"
				+ "DicRbrvs b"
				+ " where "
				+ "a.rbrvsId=b.id"
				+ " and (a.executeTechnician<>'' or a.executeTechnicianAssociate<>'')"
				+ " and a.reportTime between :begin and :end"
				+ " group by b.id, b.technician"
				+ " order by b.id"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		for(Object[] recs: (List<Object[]>) stmt.list()) {
			RbrvsPrice rbrvsPrice = new RbrvsPrice(
					workStatistic,
					new DicRbrvs((int) recs[0]),
					(double) recs[1],
					(double) recs[2],
					0,
					0,
					Integer.parseInt(Long.toString((long) recs[3])),
					"投照",
					"工作量"
				);
//			rbrvsPrice.setPrice(workStatistic.getPointValue()*rbrvsPrice.getPoint());
			rbrvsPrice.setPrice(workStatistic.getTechPointValue()*rbrvsPrice.getPoint());
			rbrvsPrice.setAmount(rbrvsPrice.getPrice()*rbrvsPrice.getQuantity());
			this.updateSession.save(rbrvsPrice);
		}
		//诊断组
		stmt = this.querySession.createQuery(
				"select "
				+ "b.id, b.diagnostician, sum(b.diagnostician) as amount, count(b.diagnostician) as quantity"
				+ " from "
				+ "MiExecuted a,"
				+ "DicRbrvs b"
				+ " where "
				+ "a.rbrvsId=b.id"
				+ " and a.reportTime between :begin and :end"
				+ " and a.status=:status"
				+ " group by b.id, b.diagnostician"
				+ " order by b.id"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		stmt.setString("status", "已审核");
		for(Object[] recs: (List<Object[]>) stmt.list()) {
			RbrvsPrice rbrvsPrice = new RbrvsPrice(
					workStatistic,
					new DicRbrvs((int) recs[0]),
					(double) recs[1],
					(double) recs[2],
					0,
					0,
					Integer.parseInt(Long.toString((long) recs[3])),
					"诊断",
					"工作量"
				);
			rbrvsPrice.setPrice(workStatistic.getDiagnoPointValue()*rbrvsPrice.getPoint());
			rbrvsPrice.setAmount(rbrvsPrice.getPrice()*rbrvsPrice.getQuantity());
			this.updateSession.save(rbrvsPrice);
		}
	}

	@SuppressWarnings("unchecked")
	public void calPerformance() throws Exception {
		Query stmt;

		stmt = this.updateSession.createQuery("delete from CalculatePerformance where workStatistic=:workStatistic");
		stmt.setEntity("workStatistic", workStatistic);
		stmt.executeUpdate();

		stmt = this.querySession.createQuery(
				"select "
				+ "rbrvsPriceId,"
				+ "rbrvsId,"
				+ "price,"
				+ "count(rbrvsId) as quantity,"
				+ "coalesce(worker1Id, 0) as worker1Id,"
				+ "coalesce(worker1Coef, 0) as worker1Coef,"
				+ "coalesce(worker2Id, 0) as worker2Id,"
				+ "coalesce(worker2Coef, 0) as worker2Coef,"
				+ "coalesce(worker3Id, 0) as worker3Id,"
				+ "coalesce(worker3Coef, 0) as worker3Coef,"
				+ "type,"
				+ "kind,"
				+ "dept"
				+ " from "
				+ "VMiExectuedPerformance"
				+ " where "
				+ "workStatisticsId=:workStatisticsId"
				+ " and reportTime between :begin and :end"
				+ " group by rbrvsPriceId,rbrvsId,price,worker1Id,worker1Coef,worker2Id,worker2Coef,worker3Id,worker3Coef,type,kind,dept"
			);
		stmt.setInteger("workStatisticsId", workStatistic.getId());
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		for(Object[] recs: (List<Object[]>) stmt.list()) {
			CalculatePerformance calculatePerformance = new CalculatePerformance();
			calculatePerformance.setWorkStatistic(workStatistic);
			calculatePerformance.setRbrvsPrice(new RbrvsPrice((int) recs[0]));
			calculatePerformance.setDicRbrvs(new DicRbrvs((int) recs[1]));
			calculatePerformance.setPrice((double) recs[2]);
			calculatePerformance.setQuantity(Integer.parseInt(Long.toString((long) recs[3])));
			calculatePerformance.setWorker1StaffId((Integer) recs[4]);
			calculatePerformance.setWorker1Coef((Double) recs[5]);
			calculatePerformance.setWorker2StaffId((Integer) recs[6]);
			calculatePerformance.setWorker2Coef((Double) recs[7]);
			calculatePerformance.setWorker3StaffId((Integer) recs[8]);
			calculatePerformance.setWorker3Coef((Double) recs[9]);
			calculatePerformance.setType((String) recs[10]);
			calculatePerformance.setKind((String) recs[11]);
			calculatePerformance.setDept((String) recs[12]);
			calculatePerformance.setWorkerCoefTotal(calculatePerformance.getWorker1Coef()+calculatePerformance.getWorker2Coef()+calculatePerformance.getWorker3Coef());
			calculatePerformance.setAmount(calculatePerformance.getPrice()*calculatePerformance.getQuantity());
			calculatePerformance.setWorkerPrice(calculatePerformance.getAmount()/calculatePerformance.getWorkerCoefTotal());
			calculatePerformance.setWorker1Total(calculatePerformance.getWorker1Coef()*calculatePerformance.getWorkerPrice());
			calculatePerformance.setWorker2Total(calculatePerformance.getWorker2Coef()*calculatePerformance.getWorkerPrice());
			calculatePerformance.setWorker3Total(calculatePerformance.getWorker3Coef()*calculatePerformance.getWorkerPrice());
			this.updateSession.save(calculatePerformance);
		}
	}
}

class SingleMode implements Mode {
	private Session querySession;
	private Session updateSession;
	private WorkStatistic workStatistic;

	public SingleMode(Session querySession, Session updateSession, WorkStatistic workStatistic) {
		this.querySession = querySession;
		this.updateSession = updateSession;
		this.workStatistic = workStatistic;
	}

	public void calMainInfo() throws Exception {
		Query stmt;

		/*
		 * Sum the Point total.
		 * include Technician,Associate,Diagnostician,Verifier
		 */
		workStatistic.setPointTotal(0d);
		workStatistic.setTechPointTotal(0d);
		workStatistic.setDiagnoPointTotal(0d);
		//Technician
		stmt = this.querySession.createQuery(
				"select "
				+ "sum(technicianValue) as amount"
				+ " from "
				+ "VMiExecuted"
				+ " where "
				+ "executeTechnicianStaffId is not null"
				+ " and executeTechnicianCoef > 0"
				+ " and reportDate between :begin and :end"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		workStatistic.setTechPointTotal(workStatistic.getTechPointTotal() + (double) stmt.uniqueResult());
		//Technician Associate
		stmt = this.querySession.createQuery(
				"select "
				+ "sum(technicianValue) as amount"
				+ " from "
				+ "VMiExecuted"
				+ " where "
				+ "executeTechnicianAssociateStaffId is not null"
				+ " and executeTechnicianAssociateCoef > 0"
				+ " and reportDate between :begin and :end"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		workStatistic.setTechPointTotal(workStatistic.getTechPointTotal() + (double) stmt.uniqueResult());
		//Diagnostician
		stmt = this.querySession.createQuery(
				"select "
				+ "sum(diagnosticianValue) as amount"
				+ " from "
				+ "VMiExecuted"
				+ " where "
				+ "executeDiagnosticianStaffId is not null"
				+ " and executeDiagnosticianCoef > 0"
				+ " and reportDate between :begin and :end"
				+ " and status=:status"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		stmt.setString("status", "已审核");
		workStatistic.setDiagnoPointTotal(workStatistic.getDiagnoPointTotal() + (double) stmt.uniqueResult());
		//Verifier
		stmt = this.querySession.createQuery(
				"select "
				+ "sum(diagnosticianValue) as amount"
				+ " from "
				+ "VMiExecuted"
				+ " where "
				+ "executeVerifierStaffId is not null"
				+ " and executeVerifierCoef > 0"
				+ " and executeDiagnosticianIsStudent = 1"
				+ " and reportDate between :begin and :end"
				+ " and status=:status"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		stmt.setString("status", "已审核");
		workStatistic.setDiagnoPointTotal(workStatistic.getDiagnoPointTotal() + (double) stmt.uniqueResult());

		/*
		 * Calculateing the Point value.
		 */
		workStatistic.setTechPointValue(workStatistic.getTechTotal()/workStatistic.getTechPointTotal());
		workStatistic.setDiagnoPointValue(workStatistic.getDiagnoTotal()/workStatistic.getDiagnoPointTotal());

		this.updateSession.saveOrUpdate(workStatistic);
	}

	@SuppressWarnings("unchecked")
	public void calRbrvsPrice() throws Exception {
		Query stmt;

		/*
		 * Empty the RbrvsPrice of WorkStatistic.
		 */
		stmt = this.updateSession.createQuery("delete from RbrvsPrice where workStatistic=:workStatistic");
		stmt.setEntity("workStatistic", workStatistic);
		stmt.executeUpdate();

		/*
		 * Sum Workload for Technician,Associate,Diagnostician,Verifier.
		 */
		//Technician
		stmt = this.querySession.createQuery(
				"select "
				+ "rbrvsId, technicianValue, sum(technicianValue) as amount, count(id) as quantity"
				+ " from "
				+ "VMiExecuted"
				+ " where "
				+ " executeTechnicianStaffId is not null"
				+ " and executeTechnicianCoef > 0"
				+ " and reportDate between :begin and :end"
				+ " group by rbrvsId, technicianValue"
				+ " order by rbrvsId"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		for(Object[] recs: (List<Object[]>) stmt.list()) {
			RbrvsPrice rbrvsPrice = new RbrvsPrice(
					workStatistic,
					new DicRbrvs((int) recs[0]),
					(double) recs[1],
					(double) recs[2],
					0,
					0,
					Integer.parseInt(Long.toString((long) recs[3])),
					"操作",
					"工作量"
				);
			rbrvsPrice.setPrice(workStatistic.getTechPointValue()*rbrvsPrice.getPoint());
			rbrvsPrice.setAmount(rbrvsPrice.getPrice()*rbrvsPrice.getQuantity());

			this.updateSession.save(rbrvsPrice);
		}
		//Associate
		stmt = this.querySession.createQuery(
				"select "
				+ "rbrvsId, technicianValue, sum(technicianValue) as amount, count(id) as quantity"
				+ " from "
				+ "VMiExecuted"
				+ " where "
				+ "executeTechnicianAssociateStaffId is not null"
				+ " and executeTechnicianAssociateCoef > 0"
				+ " and reportDate between :begin and :end"
				+ " group by rbrvsId, technicianValue"
				+ " order by rbrvsId"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		for(Object[] recs: (List<Object[]>) stmt.list()) {
			RbrvsPrice rbrvsPrice = new RbrvsPrice(
					workStatistic,
					new DicRbrvs((int) recs[0]),
					(double) recs[1],
					(double) recs[2],
					0,
					0,
					Integer.parseInt(Long.toString((long) recs[3])),
					"辅助",
					"工作量"
				);
			rbrvsPrice.setPrice(workStatistic.getTechPointValue()*rbrvsPrice.getPoint());
			rbrvsPrice.setAmount(rbrvsPrice.getPrice()*rbrvsPrice.getQuantity());

			this.updateSession.save(rbrvsPrice);
		}
		//Diagnostician
		stmt = this.querySession.createQuery(
				"select "
				+ "rbrvsId, diagnosticianValue, sum(diagnosticianValue) as amount, count(id) as quantity"
				+ " from "
				+ "VMiExecuted"
				+ " where "
				+ "executeDiagnosticianStaffId is not null"
				+ " and executeDiagnosticianCoef > 0"
				+ " and reportDate between :begin and :end"
				+ " and status=:status"
				+ " group by rbrvsId, diagnosticianValue"
				+ " order by rbrvsId"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		stmt.setString("status", "已审核");
		for(Object[] recs: (List<Object[]>) stmt.list()) {
			RbrvsPrice rbrvsPrice = new RbrvsPrice(
					workStatistic,
					new DicRbrvs((int) recs[0]),
					(double) recs[1],
					(double) recs[2],
					0,
					0,
					Integer.parseInt(Long.toString((long) recs[3])),
					"阅片",
					"工作量"
				);
			rbrvsPrice.setPrice(workStatistic.getDiagnoPointValue()*rbrvsPrice.getPoint());
			rbrvsPrice.setAmount(rbrvsPrice.getPrice()*rbrvsPrice.getQuantity());

			this.updateSession.save(rbrvsPrice);
		}
		//Verifier
		stmt = this.querySession.createQuery(
				"select "
				+ "rbrvsId, diagnosticianValue, sum(diagnosticianValue) as amount, count(id) as quantity"
				+ " from "
				+ "VMiExecuted"
				+ " where "
				+ "executeVerifierStaffId is not null"
				+ " and executeVerifierCoef > 0"
				+ " and executeDiagnosticianIsStudent = 1"
				+ " and reportDate between :begin and :end"
				+ " and status=:status"
				+ " group by rbrvsId, diagnosticianValue"
				+ " order by rbrvsId"
			);
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		stmt.setString("status", "已审核");
		for(Object[] recs: (List<Object[]>) stmt.list()) {
			RbrvsPrice rbrvsPrice = new RbrvsPrice(
					workStatistic,
					new DicRbrvs((int) recs[0]),
					(double) recs[1],
					(double) recs[2],
					0,
					0,
					Integer.parseInt(Long.toString((long) recs[3])),
					"审片",
					"工作量"
				);
			rbrvsPrice.setPrice(workStatistic.getDiagnoPointValue()*rbrvsPrice.getPoint());
			rbrvsPrice.setAmount(rbrvsPrice.getPrice()*rbrvsPrice.getQuantity());

			this.updateSession.save(rbrvsPrice);
		}
	}

	public void calPerformance() throws Exception {
/*
		Query stmt;

		stmt = this.querySession.createQuery(
				"select "
				+ "	b.price,"
				+ "count() as amount"
				+ "	a.reportDate,"
				+ "	a.reportTime,"
				+ "	a.executeTechnicianStaffId as staffId,"
				+ "	a.executeTechnician as staffName,"
				+ "	a.executeTechnicianCoef as staffCoef,"
				+ "	b.type,"
				+ "	b.kind,"
				+ "	a.kind as dept"
				+ "	from VMiExecuted a,"
				+ "	RbrvsPrice b"
				+ "	where "
				+ "	a.executeTechnicianStaffId is not null"
				+ "	and coalesce(a.executeTechnicianCoef, 0) > 0"
				+ "	and a.rbrvsId=b.rbrvsId"
				+ "	and b.type=:type"
				+ "	and b.kind=:kind"
				+ " and a.kind=:dept"
				+ " and a.reportDate between :begin and :end"
				+ " and b.workStatisticsId=:workStatisticsId"
			);
		stmt.setInteger("workStatisticsId", workStatistic.getId());
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		stmt.setString("type", "操作");
		stmt.setString("kind", "工作量");
		stmt.setString("dept", "影像科");
		for (Object[] recs: (List<Object[]>) stmt.list()) {
			RbrvsPrice rbrvsPrice = new RbrvsPrice(
					workStatistic,
					new DicRbrvs((int) recs[0]),
					(double) recs[1],
					(double) recs[2],
					0,
					0,
					Integer.parseInt(Long.toString((long) recs[3])),
					"审片",
					"工作量"
				);
			rbrvsPrice.setPrice(workStatistic.getDiagnoPointValue()*rbrvsPrice.getPoint());
			rbrvsPrice.setAmount(rbrvsPrice.getPrice()*rbrvsPrice.getQuantity());
		}
*/
/*
		double techCoef, assocCoef, diagnoCoef, verifierCoef;
		//Tech Coef
		stmt = querySession.createQuery(
				"select sum(coef) amount from ("
				+ "select "
				+ "executeTechnicianStaffId as staffId,"
				+ "executeTechnicianCoef as coef"
				+ "	from "
				+ "VMiExecuted"
				+ "	where "
				+ "	reportTime between :begin and :end"
				+ "	and executeTechnicianStaffId is not null"
				+ " group by executeTechnicianStaffId, executeTechnicianCoef"
				+ ") a");
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		techCoef = Double.parseDouble(Long.toString((long) stmt.uniqueResult()));
		//Associate Coef
		stmt = querySession.createQuery(
				"select sum(coef) amount from ("
				+ "select "
				+ "executeTechnicianAssociateStaffId as staffId,"
				+ "executeTechnicianAssociateCoef as coef"
				+ "	from "
				+ "VMiExecuted"
				+ "	where "
				+ "	reportTime between :begin and :end"
				+ "	and executeTechnicianAssociateStaffId is not null"
				+ " group by executeTechnicianAssociateStaffId, executeTechnicianAssociateCoef"
				+ ") a");
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		assocCoef = Double.parseDouble(Long.toString((long) stmt.uniqueResult()));
		//Diagno Coef
		stmt = querySession.createQuery(
				"select sum(coef) amount from ("
				+ "select "
				+ "executeDiagnosticianStaffId as staffId,"
				+ "executeDiagnosticianCoef as coef"
				+ "	from "
				+ "VMiExecuted"
				+ "	where "
				+ "	reportTime between :begin and :end"
				+ "	and executeDiagnosticianStaffId is not null"
				+ " group by executeDiagnosticianStaffId, executeDiagnosticianCoef"
				+ ") a");
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		diagnoCoef = Double.parseDouble(Long.toString((long) stmt.uniqueResult()));
		//Verifier Coef
		stmt = querySession.createQuery(
				"select sum(coef) amount from ("
				+ "select "
				+ "executeVerifierStaffId as staffId,"
				+ "executeVerifierCoef as coef"
				+ "	from "
				+ "VMiExecuted"
				+ "	where "
				+ "	reportTime between :begin and :end"
				+ "	and executeVerifierStaffId is not null"
				+ " group by executeVerifierStaffId, executeVerifierCoef"
				+ ") a");
		stmt.setDate("begin", workStatistic.getBeginDate());
		stmt.setDate("end", workStatistic.getEndDate());
		verifierCoef = Double.parseDouble(Long.toString((long) stmt.uniqueResult()));
*/
	}	
}