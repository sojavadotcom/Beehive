package com.sojava.beehive.framework.util;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FilterUtil {
	/*
	 * @param
	 * JSONArray filters([{key: "fieldName", expression: "eq", value: "anyting", ignoreCase: true[, value2: "anyting"]}])
	 * 
	 */
	public static Criterion[] createCriterion(JSONArray filters) {
		int len = 0;
		if (filters != null) len = filters.size();
		Criterion[] result = new Criterion[len];
		for (int i = 0; i < len; i ++) {
			JSONObject criterion = filters.getJSONObject(i);
			String expression = criterion.getString("expression");
			boolean ignoreCase = criterion.containsKey("ignoreCase") ? criterion.getBoolean("ignoreCase") : false;
			if (expression.equalsIgnoreCase("eq") || expression.equals("=") || expression.equals("==")) result[i] = Restrictions.eq(criterion.getString("key"), criterion.getString("value"));
			else if (expression.equalsIgnoreCase("ne") || expression.equals("!=") || expression.equals("<>")) result[i] = Restrictions.ne(criterion.getString("key"), criterion.getString("value"));
			else if (expression.equalsIgnoreCase("le") || expression.equals("<=")) result[i] = Restrictions.le(criterion.getString("key"), criterion.getString("value"));
			else if (expression.equalsIgnoreCase("lt") || expression.equals("<")) result[i] = Restrictions.lt(criterion.getString("key"), criterion.getString("value"));
			else if (expression.equalsIgnoreCase("ge") || expression.equals(">=")) result[i] = Restrictions.ge(criterion.getString("key"), criterion.getString("value"));
			else if (expression.equalsIgnoreCase("gt") || expression.equals(">")) result[i] = Restrictions.gt(criterion.getString("key"), criterion.getString("value"));
			else if (expression.equalsIgnoreCase("in")) result[i] = Restrictions.in(criterion.getString("key"), criterion.getJSONArray("value"));
			else if (expression.equalsIgnoreCase("notIn")) result[i] = Restrictions.not(Restrictions.in(criterion.getString("key"), criterion.getJSONArray("value")));
			else if (expression.equalsIgnoreCase("between")) result[i] = Restrictions.between(criterion.getString("key"), criterion.getString("value"), criterion.getString("value2"));
			else if (expression.equalsIgnoreCase("like")) result[i] = Restrictions.like(criterion.getString("key"), criterion.getString("value"));
			else if (expression.equalsIgnoreCase("isEmpty") || expression.equals("")) result[i] = Restrictions.isEmpty(criterion.getString("key"));
			else if (expression.equalsIgnoreCase("isNotEmpty")) result[i] = Restrictions.isNotEmpty(criterion.getString("key"));
			else if (expression.equalsIgnoreCase("isNull")) result[i] = Restrictions.isNull(criterion.getString("key"));
			else if (expression.equalsIgnoreCase("isNotNull")) result[i] = Restrictions.isNotNull(criterion.getString("key"));

			if (ignoreCase) ((SimpleExpression) result[i]).ignoreCase();
		}

		return result;
	}

	/*
	 * @param
	 * JSONArray filters([{key: "fieldName", expression: "eq", value: "anyting", ignoreCase: true[, value2: "anyting"]}])
	 * 
	 */
	public static Criterion[] createCriterion(Class<?> c, JSONArray filters) throws ParseException, Exception {
		int len = 0;
		if (filters != null) len = filters.size();
		Criterion[] result = new Criterion[len];
		for (int i = 0; i < len; i ++) {
			JSONObject criterion = filters.getJSONObject(i);
			String key = criterion.getString("key");
			String expression = criterion.getString("expression");
			boolean ignoreCase = criterion.containsKey("ignoreCase") ? criterion.getBoolean("ignoreCase") : false;

			if (expression.equalsIgnoreCase("eq") || expression.equals("=") || expression.equals("==")) result[i] = Restrictions.eq(key, getValue(c, key, criterion, "value"));
			else if (expression.equalsIgnoreCase("ne") || expression.equals("!=") || expression.equals("<>")) result[i] = Restrictions.ne(key, getValue(c, key, criterion, "value"));
			else if (expression.equalsIgnoreCase("le") || expression.equals("<=")) result[i] = Restrictions.le(key, getValue(c, key, criterion, "value"));
			else if (expression.equalsIgnoreCase("lt") || expression.equals("<")) result[i] = Restrictions.lt(key, getValue(c, key, criterion, "value"));
			else if (expression.equalsIgnoreCase("ge") || expression.equals(">=")) result[i] = Restrictions.ge(key, getValue(c, key, criterion, "value"));
			else if (expression.equalsIgnoreCase("gt") || expression.equals(">")) result[i] = Restrictions.gt(key, getValue(c, key, criterion, "value"));
			else if (expression.equalsIgnoreCase("in")) result[i] = Restrictions.in(key, criterion.getJSONArray("value"));
			else if (expression.equalsIgnoreCase("notIn")) result[i] = Restrictions.not(Restrictions.in(key, criterion.getJSONArray("value")));
			else if (expression.equalsIgnoreCase("between")) result[i] = Restrictions.between(key, getValue(c, key, criterion, "value"), getValue(c, key, criterion, "value2"));
			else if (expression.equalsIgnoreCase("like")) result[i] = Restrictions.like(key, getValue(c, key, criterion, "value"));
			else if (expression.equalsIgnoreCase("isEmpty") || expression.equals("")) result[i] = Restrictions.isEmpty(key);
			else if (expression.equalsIgnoreCase("isNotEmpty")) result[i] = Restrictions.isNotEmpty(key);
			else if (expression.equalsIgnoreCase("isNull")) result[i] = Restrictions.isNull(key);
			else if (expression.equalsIgnoreCase("isNotNull")) result[i] = Restrictions.isNotNull(key);

			if (ignoreCase) ((SimpleExpression) result[i]).ignoreCase();
		}

		return result;
	}

	public static Class<?> getReturnType(Class<?> c, String fieldName) {
		String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		try {
			return c.getMethod(methodName, new Class<?>[0]).getReturnType();
		}
		catch(NoSuchMethodException e1) {
			try {
				methodName = methodName.replaceFirst(Pattern.quote("get"), "is");
				return c.getMethod(methodName, new Class<?>[0]).getReturnType();
			}
			catch(NoSuchMethodException e2) {}
		}
		return String.class;
	}

	public static Object getValue(Class<?> c, String fieldName, JSONObject criterion, String valueName) throws ParseException, Exception {
		if (getReturnType(c, fieldName).equals(int.class) || 
			getReturnType(c, fieldName).equals(Integer.class) || 
			getReturnType(c, fieldName).equals(short.class) ||
			getReturnType(c, fieldName).equals(Short.class) ||
			getReturnType(c, fieldName).equals(byte.class) ||
			getReturnType(c, fieldName).equals(Byte.class)) {

			return criterion.getInt(valueName);
		} else if (getReturnType(c, fieldName).equals(long.class) || getReturnType(c, fieldName).equals(Long.class)) {
			return criterion.getLong(valueName);
		} else if (getReturnType(c, fieldName).equals(BigDecimal.class) || getReturnType(c, fieldName).equals(double.class) || getReturnType(c, fieldName).equals(Double.class) || getReturnType(c, fieldName).equals(float.class) || getReturnType(c, fieldName).equals(Float.class)) {
			return criterion.getDouble(valueName);
		} else if (getReturnType(c, fieldName).equals(Date.class) || getReturnType(c, fieldName).equals(java.sql.Date.class)) {
			String value = criterion.getString(valueName);
			if (value == null)
				return null;
			else if (value.toString().length() <= 10)
				return FormatUtil.DATE_FORMAT.parse(value);
			else
				return FormatUtil.DATETIME_FORMAT.parse(value);
		} else if (getReturnType(c, fieldName).equals(Timestamp.class)) {
			return FormatUtil.DATETIME_FORMAT.parse(criterion.getString(valueName));
		} else if (getReturnType(c, fieldName).equals(Time.class)) {
			return FormatUtil.TIME_FORMAT.format(criterion.getString(valueName));
		} else if (getReturnType(c, fieldName).equals(boolean.class) || getReturnType(c, fieldName).equals(Boolean.class)) {
			return criterion.getBoolean(valueName);
		}

		return criterion.getString(valueName);
	}
}
