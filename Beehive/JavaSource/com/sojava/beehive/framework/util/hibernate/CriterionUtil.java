package com.sojava.beehive.framework.util.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.define.DataFlag;

public class CriterionUtil {
	private Session session;
	private List<Criterion> criteria;
	private List<Order> orders;
	private ProjectionList projectionList;
	private Criterion authorizedScope;

	protected CriterionUtil() {}

	public CriterionUtil(Session session) {
		this.session = session;
		this.authorizedScope = null;

		this.criteria = new ArrayList<Criterion>();
		this.orders = new ArrayList<Order>();
		this.projectionList = Projections.projectionList().create();
	}

	public CriterionUtil(Session session, Criterion authorizedScope) {
		this.session = session;
		this.authorizedScope = authorizedScope;

		this.criteria = new ArrayList<Criterion>();
		this.orders = new ArrayList<Order>();
		this.projectionList = Projections.projectionList().create();
	}

	public CriterionUtil addCriterion(Criterion...criteria) {
		if (criteria != null) {
			for (Criterion criterion: criteria) {
				if (criterion != null) this.criteria.add(criterion);
			}
		}

		return this;
	}

	public void clearCriterion() {
		this.criteria.clear();
	}

	public CriterionUtil addOrder(Order...orders) {
		if (orders != null) {
			for (Order order: orders) {
				if (order != null) this.orders.add(order);
			}
		}

		return this;
	}

	public void clearOrder() {
		this.orders.clear();
	}

	public CriterionUtil addProjection(Projection...projs) {
		if (projs != null) {
			for (Projection proj: projs) {
				if (proj != null) this.projectionList.add(proj);
			}
		}
		return this;
	}

	public void clearProjectionList() {
		this.projectionList = Projections.projectionList().create();
	}

	public Criteria createCriteria(Class<?> c) throws Exception {
		return createCriteria(c, null, true);
	}

	public Criteria createCriteria(Class<?> c, Page page) throws Exception {
		return createCriteria(c, page, true);
	}

	public Criteria createCriteria(Class<?> c, Page page, boolean usableData) throws Exception {
		Criterion _criteria = null;
		for (Criterion criterion: this.criteria) {
			_criteria = (_criteria != null ? Restrictions.and(_criteria, criterion) : criterion);
		}

		Criteria criteria = session.createCriteria(c);
		if (_criteria != null) criteria.add(Restrictions.conjunction().add(_criteria));
		if (this.projectionList.getLength() > 0) criteria.setProjection(this.projectionList);
		if (authorizedScope != null) criteria.add(Restrictions.conjunction().add(authorizedScope));
		if (usableData) criteria.add(Restrictions.conjunction().add(Restrictions.in("dataFlag", new Short[]{DataFlag.add, DataFlag.modify, DataFlag.synchronized_add, DataFlag.synchronized_modify})));
		if (page != null) {
			page.setTotal(((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue());
			criteria.setProjection(null);
			if (page.getStart() >= 0) criteria.setFirstResult(page.getStart());
			if (page.getLimit() > 0) criteria.setMaxResults(page.getLimit());
		}
		if (this.projectionList.getLength() > 0) criteria.setProjection(this.projectionList);
		for (Order order: this.orders) criteria.addOrder(order);

		return criteria;
	}

	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public List<Criterion> getCriteria() {
		return criteria;
	}
	public void setCriteria(List<Criterion> criteria) {
		this.criteria = criteria;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public ProjectionList getProjections() {
		return projectionList;
	}
	public void setProjections(ProjectionList projections) {
		this.projectionList = projections;
	}
	public Criterion getAuthorizedScope() {
		return authorizedScope;
	}
	public void setAuthorizedScope(Criterion authorizedScope) {
		this.authorizedScope = authorizedScope;
	}
}
