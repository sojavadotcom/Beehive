package com.sojava.beehive.framework.component.catcher.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.catcher.bean.CatchArticle;
import com.sojava.beehive.framework.component.catcher.dao.CatchArticleDao;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.RecordUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Controller("Catcher")
@Scope("prototype")
@Namespace("/Catcher")
@ParentPackage("json-default")
public class Entry extends ActionSupport {
	private static final long serialVersionUID = 359455482944018005L;

	@Resource private CatchArticleDao catchArticleDao;

	private String title = null;
	private String order;
	private Integer count;

	private Map<String, Object> rest;
	private Object[] list;
	private com.sojava.beehive.framework.define.result.Result msg;

	@Actions(value = {
			@Action(value = "Index", results = {
				@Result(name = SUCCESS, location = "index.jsp"),
			}),
			@Action(value = "Query", results = {
				@Result(name = SUCCESS, type = "json", params = {"root", "rest"}),
				@Result(name = ERROR, type = "json", params = {"root", "msg"})
			})
		})
	public String index() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();

		if (actionName.equals("Index")) {
			return SUCCESS;
		} else {
			return (String) this.getClass().getMethod(this.getActionContext().getName().toLowerCase(), new Class[0]).invoke(this, new Object[0]);
		}

	}

	@SuppressWarnings("unchecked")
	public String query() throws Exception {
		try {
			Page page = new Page(this.getStart(), this.getEnd());
			List<Criterion> filters = new ArrayList<Criterion>();
			if (title != null) {
				filters.add(Restrictions.like("title", title));
			}
			List<CatchArticle> list = (List<CatchArticle>) catchArticleDao.query(CatchArticle.class, filters.toArray(new Criterion[0]), new Order[]{Order.desc("date")}, page, false);
			this.setRange(page.getTotal());
			rest = new HashMap<String, Object>();
			rest.put("start", page.getStart());
			rest.put("end", page.getEnd());
			rest.put("total", page.getTotal());
			rest.put("data", new RecordUtil().generateJsonByMapping(list.toArray(new CatchArticle[0])).getJSONArray("items").toArray());

			return SUCCESS;
		}
		catch(Exception ex) {
			new ErrorException(getClass(), ex);
			msg = new com.sojava.beehive.framework.define.result.Result(false);
			msg.setMessage(ex.getMessage());
			return ERROR;
		}
	}

	public CatchArticleDao getCatchArticleDao() {
		return catchArticleDao;
	}

	public void setCatchArticleDao(CatchArticleDao catchArticleDao) {
		this.catchArticleDao = catchArticleDao;
	}

	public Map<String, Object> getRest() {
		return rest;
	}

	public void setRest(Map<String, Object> rest) {
		this.rest = rest;
	}

	public Object[] getList() {
		return list;
	}

	public void setList(Object[] list) {
		this.list = list;
	}

	public com.sojava.beehive.framework.define.result.Result getMsg() {
		return msg;
	}

	public void setMsg(com.sojava.beehive.framework.define.result.Result msg) {
		this.msg = msg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
