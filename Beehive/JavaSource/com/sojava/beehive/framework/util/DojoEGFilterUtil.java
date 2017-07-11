package com.sojava.beehive.framework.util;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DojoEGFilterUtil {

	public static Criterion createGroup(JSONObject filter) throws Exception {
		Criterion condition = null;
		String operator = filter.getString("op");
		JSONArray data = filter.getJSONArray("data");
		for (int i = 0; i < data.size(); i ++) {
			JSONObject item = data.getJSONObject(i);
			if (operator.equals("all")) {
				condition = condition != null ? Restrictions.and(condition, Restrictions.and(createItem(item))) : Restrictions.and(createItem(item));
			} else if (operator.equals("any")) {
				condition = condition != null ? Restrictions.or(condition, Restrictions.or(createItem(item))) : Restrictions.or(createItem(item));
			} else {
				condition = createItem(filter);
			}
		}
		return condition;
	}

	public static Criterion createItem(JSONObject filter) throws Exception {
		Criterion condition = null;
		String exp = filter.getString("op");
		if (exp.equals("all") || exp.equals("any")) {
			condition = createGroup(filter);
		} else {
			String dataType = null;
			String column = null;
			String val = null;
			JSONArray data = filter.getJSONArray("data");
			if(exp.equals("not")) {
//				condition = Restrictions.not(createItem(data.getJSONObject(0)));
			} else {
				JSONObject item0 = data.getJSONObject(0);
				JSONObject item1 = data.getJSONObject(1);
				dataType = item0.getString("op");
				column = item0.getString("data");
				val = item1.getString("data");
				if (exp.equals("contains")) {
					condition = Restrictions.like(column, "%" + val + "%");
				} else if (exp.equals("equal")) {
					condition = Restrictions.eq(column, val);
				} else if (exp.equals("startsWith")) {
					condition = Restrictions.like(column, val + "%");
				} else if (exp.equals("endsWith")) {
					condition = Restrictions.like(column, "%" + val);
				} else if (exp.equals("isEmpty")) {
					condition = Restrictions.isEmpty(column);
				}
				if (dataType != null && dataType.equalsIgnoreCase("string")) condition = ((SimpleExpression) condition).ignoreCase();
			}
		}

		return condition;
	}
/*
	public static Criterion createCriterion(JSONObject filter) throws Exception {
		Criterion condition = null;
		String operator = filter.getString("op");
		JSONArray data = filter.getJSONArray("data");
		for (int i = 0; i < data.size(); i ++) {
			JSONObject item = data.getJSONObject(i);
			if (operator.equals("all")) {
				condition = condition != null ? Restrictions.and(condition, Restrictions.and(createItem(item))) : createItem(item);
			} else if (operator.equals("any")) {
				condition = condition != null ? Restrictions.or(condition, Restrictions.and(createItem(item))) : createItem(item);
			}
		}

		return condition;
	}

	public static Criterion createItem(JSONObject filter) throws Exception {
		Criterion condition = null;
		String exp = filter.getString("op");
		JSONArray data = filter.getJSONArray("data");
		String dataType = null;
		String column = null;
		String val = null;
		JSONObject item = null;

		for (int i = 0; i < data.size(); i ++) {
			item = data.getJSONObject(i);
			if (item.containsKey("isCol") && item.getBoolean("isCol")) {
				dataType = item.getString("op");
				column = item.getString("data");
			} else if (item.containsKey("isCol") && !item.getBoolean("isCol")) {
				val = item.getString("data");
			}
		}
		if (exp.equals("contains")) {
			condition = Restrictions.like(column, "%" + val + "%");
		} else if (exp.equals("equal")) {
			condition = Restrictions.eq(column, val);
		} else if (exp.equals("startsWith")) {
			condition = Restrictions.like(column, val + "%");
		} else if (exp.equals("endsWith")) {
			condition = Restrictions.like(column, "%" + val);
		} else if (exp.equals("not")) {
			condition = Restrictions.not(createItem(item));
		} else if (exp.equals("isEmpty")) {
			condition = Restrictions.isEmpty(column);
		} else {
			createCriterion(item);
		}
		if (dataType != null && dataType.equalsIgnoreCase("string")) ((SimpleExpression) condition).ignoreCase();

		return condition;
	}
*/
}
