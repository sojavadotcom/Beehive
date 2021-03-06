package com.sojava.beehive.framework.component.inpatienthomepage.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.DicAdministrativeDivision;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.Dictionary;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.IcdTransform;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnalyCheck;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.RecordRangeType;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.VIcdTransform;
import com.sojava.beehive.framework.component.inpatienthomepage.dao.HomepageDao;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.hibernate.dao.impl.BeehiveDaoImpl;

@Repository
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {CommonException.class, ErrorException.class, WarnException.class, Exception.class, Throwable.class})
public class HomepageDaoImpl extends BeehiveDaoImpl implements HomepageDao {
	private static final long serialVersionUID = 5318913848127245067L;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> typeList(String kind) throws Exception {
		List<String> list = null;

		try {
			String _kind = kind == null ? "" : " and kind='" + kind + "'";
			SQLQuery stmt = getSession().createSQLQuery("select distinct type from data_transform.inpatient_homepage_analy where 1=1" + _kind + " order by type");
			list = stmt.list();

			return list;
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> getDictionary(String kind) throws Exception {
		List<Dictionary> list = null;
		try {
			list = (List<Dictionary>) query(Dictionary.class, new Criterion[]{Restrictions.eq("kind", kind)}, new Order[]{Order.asc("id")}, null, false);

			return list;
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DicAdministrativeDivision> getAdministrativeDivision(int level) throws Exception {
		List<DicAdministrativeDivision> list = null;
		try {
			Criterion[] filter = null;
			if (level > 0) filter = new Criterion[] {Restrictions.eq("level", level)};
			list = (List<DicAdministrativeDivision>) query(DicAdministrativeDivision.class, filter, new Order[]{Order.asc("id")}, null, false);

			return list;
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VIcdTransform> getHISDiagnosis(RecordRangeType type) throws Exception {
		List<VIcdTransform> list = null;
		String typeStr = "";
		if (type.equals(RecordRangeType.DIAGNOSIS_CHINESE)) typeStr = "中医诊断";
		else if (type.equals(RecordRangeType.DIAGNOSIS_WESTERN)) typeStr = "疾病诊断";
		else if (type.equals(RecordRangeType.DIAGNOSIS_OPERATION)) typeStr = "手术诊断";
		else if (type.equals(RecordRangeType.DIAGNOSIS_PATHOLOGY)) typeStr = "肿瘤形态学";

		try {
			list = (List<VIcdTransform>) query(
					VIcdTransform.class,
					new Criterion[]{
						Restrictions.eq("type", typeStr)
					},
					null,
					null,
					false
				);

			return list;
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IcdTransform> getICD20Diagnosis(RecordRangeType type) throws Exception {
		List<IcdTransform> list = null;
		String typeStr = "";
		if (type.equals(RecordRangeType.DIAGNOSIS_CHINESE)) typeStr = "中医诊断";
		else if (type.equals(RecordRangeType.DIAGNOSIS_WESTERN)) typeStr = "疾病诊断";
		else if (type.equals(RecordRangeType.DIAGNOSIS_OPERATION)) typeStr = "手术诊断";
		else if (type.equals(RecordRangeType.DIAGNOSIS_PATHOLOGY)) typeStr = "肿瘤形态学";

		try {
			list = (List<IcdTransform>) query(
					IcdTransform.class,
					new Criterion[]{
						Restrictions.eq("kind", "ICD11"),
						Restrictions.eq("type", typeStr)
					},
					null,
					null,
					false
				);

			return list;
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	@Override
	public boolean emptyCheckinfo(int pid) throws Exception {

		Query stmt = getSession().createQuery("delete from InpatientHomepageAnalyCheck where inpatientHomepageAnaly.id=:pid");
		stmt.setInteger("pid", pid);
		return stmt.executeUpdate() > 0;
	}

	@Override
	public void importHomepagesAndChecks(InpatientHomepageAnaly[] homepageList) throws Exception {
		Session session = getSessionFactory().openSession();
		Transaction trans = null;
		String status = "";
		try {
			trans = session.beginTransaction();
			status = "查验homepageList是否为空，(homepageList.length > 0)";
			if (homepageList.length > 0) {
				status = "根据kind,type,version清空InpatientHomepageAnaly历时数据";
				Query stmt = session.createQuery("delete from InpatientHomepageAnaly where kind=:kind and type=:type and version=:version");
				stmt.setString("kind", homepageList[0].getId().getKind());
				stmt.setString("type", homepageList[0].getId().getType());
				stmt.setFloat("version", homepageList[0].getId().getVersion());
				stmt.executeUpdate();
			}
			for (InpatientHomepageAnaly homepage : homepageList) {
				if (homepage instanceof InpatientHomepageAnaly) {
					status = "保存解析数据第" + homepage.getId().getId() + "条(bah:" + homepage.getBah() + ";zycs:" + homepage.getZycs() + ")";
					session.save(homepage);
					for (InpatientHomepageAnalyCheck check : homepage.getInpatientHomepageAnalyChecks()) {
						session.saveOrUpdate(check);
					}
				}
			}
			trans.commit();
		}
		catch(Exception ex) {
			trans.rollback();
			throw new Exception(status + "发生[" + ex.getMessage() + "]");
		}
	}

}
