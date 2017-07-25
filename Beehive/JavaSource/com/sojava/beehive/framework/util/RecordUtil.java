package com.sojava.beehive.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import com.sojava.beehive.framework.component.type.ResultType;
import com.sojava.beehive.framework.define.BaseMapping;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.CommonException;

public class RecordUtil {

	@SuppressWarnings("unchecked")
	public Map<String, Object> JSONObjectToMap(JSONObject src) throws Exception {
		Map<String, Object> rest = new HashMap<String, Object>();
		Iterator<String> keys = src.keys();
		while(keys.hasNext()) {
			String key = keys.next();
			rest.put(key, src.get(key));
		}

		return rest;
	}

	public List<Object> JSONArrayToList(JSONArray src) throws Exception {
		List<Object> rest = new ArrayList<Object>();

		for (int i = 0; i < src.size(); i ++) {
			rest.add(src.getJSONObject(i));
		}

		return rest;
	}

	public Object generate(Object content, ResultType resultType) throws Exception {
		if (resultType.equals(ResultType.Xml)) {
			return generateXmlByMapping(content, null);
		} else if (resultType.equals(ResultType.Json)) {
			return generateJsonByMapping(content);
		}
		return null;
	}

	public Document generateXmlByMapping(Object[] entities, String[] withOutFields) throws Exception {
		Document doc = DocumentFactory.getInstance().createDocument(System.getProperty("system.encoding"));
		Element root = null;
		switch (entities.length) {
		case 0:
			doc.addElement("Empty");
			break;
		default :
			for(int i = 0; i < entities.length; i ++) {
				if (i == 0) {
					root = doc.addElement(entities[0].getClass().getSimpleName() + "List");
					root.addAttribute("success", "true");
				}
				root.add(generateXmlByMapping(entities[i], withOutFields).getRootElement());
			}
		}
		return doc;
	}

	public Document generateXmlByMapping(Object entity, String[] withOutFields) throws SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, CommonException {
		Class<?> paramTypes[] = {};
		Class<?> entityClass = entity.getClass();
		Document doc = DocumentFactory.getInstance().createDocument(System.getProperty("system.encoding"));
		Element root = doc.addElement(entityClass.getSimpleName());
		String prefix = entityClass.getSimpleName();
		prefix = prefix.substring(0, 1).toLowerCase() + prefix.substring(1);

		String woFields = ",";
		if (withOutFields != null) {
			for (int i = 0; i < withOutFields.length; i ++) {
				String field = withOutFields[i];
				field = field.substring(0, 1).toUpperCase() + field.substring(1);
				woFields += "get" + field + ",";
			}
		}
		Method methods[] = entityClass.getMethods();
		for(int i = 0; i < methods.length; i ++) {
			String name = methods[i].getName();
			if (name.indexOf("get") != 0 || name.equals("getClass") || woFields.indexOf(name) > -1) continue;
			try {
				Method method = entityClass.getMethod(name, paramTypes);
				Class<?> returnType = method.getReturnType();
				if (returnType.equals(Set.class)) continue;
				Object value = entityClass.getMethod(name, paramTypes).invoke(entity, new Object[0]);
				String str = null;
				name = name.substring(3);
//				name = name.substring(0, 1).toLowerCase() + name.substring(1);
//				name = prefix + "." + name;
				if (returnType.equals(Date.class) || returnType.equals(java.sql.Date.class)) {
					if (value == null)
						str = "";
					else if (value.toString().length() <= 10)
						str = FormatUtil.DATE_FORMAT.format(value);
					else
						str = FormatUtil.DATETIME_FORMAT.format(value);
				} else if (returnType.equals(Timestamp.class)) {
					str = FormatUtil.DATETIME_FORMAT.format(value);
				} else if (returnType.equals(Time.class)) {
					str = FormatUtil.TIME_FORMAT.format(value);
				} else {
					str = value == null ? "" : value.toString();
				}
				root.addElement(name).addCDATA(str);
			}
			catch(NoSuchMethodException ex) {}
		}

		return doc;
	}

	public JSONObject generateJsonByMapping(Object[] entities) throws Exception {
		return generateJsonByMapping(entities, null, null);
	}

	public JSONObject generateJsonByMapping(Object[] entities, String[] withOutFields) throws Exception {
		return generateJsonByMapping(entities, null, withOutFields);
	}

	public JSONObject generateJsonByMapping(Object[] entities, Page page, String[] withOutFields) throws Exception {
		JSONObject result = new JSONObject();
		result.put("success", true);
		if (page != null) result.put("page", page);

		JSONArray list = new JSONArray();
		if (entities != null) {
			for (Object entity: entities) list.add(generateJsonByMapping(entity, withOutFields));
		}
		result.put("items", list);
		if (page != null) {
			JSONObject pageData = JSONObject.fromObject(page);
			for (Object key: pageData.keySet()) result.put(key, pageData.get(key));
		}

		return result;
	}

	public JSONObject generateJsonByMapping(Object entity) throws SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NumberFormatException, CommonException, Exception {
		return generateJsonByMapping(entity, null);
	}

	public JSONObject generateJsonByMapping(Object entity, String[] withOutFields) throws SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NumberFormatException, CommonException, Exception {
		JSONObject result = new JSONObject();
		result.put("success", true);

		Class<?> paramTypes[] = {};
		Class<?> entityClass = entity.getClass();

		Method methods[] = entityClass.getMethods();
		for(int i = 0; i < methods.length; i ++) {
			String name = methods[i].getName();
			String fieldName = "";
			if (name.indexOf("get") == 0) fieldName = name.replaceFirst(Pattern.quote("get"), "");
			else if (name.indexOf("is") == 0) fieldName = name.replaceFirst(Pattern.quote("is"), "");
			if (fieldName.equals("")) continue;
			fieldName = fieldName.equals("") ? "" : (fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1));//fieldName.replace(fieldName.charAt(0), (char) (fieldName.charAt(0)+32));
			if ((name.indexOf("get") != 0 && name.indexOf("is") != 0) || name.equals("getClass") || (withOutFields != null && Arrays.binarySearch(withOutFields, fieldName) >= 0)) continue;
			try {
				Method method = entityClass.getMethod(name, paramTypes);
				Class<?> returnType = method.getReturnType();
//				if (returnType.equals(Set.class)) continue;
				Object value = entityClass.getMethod(name, paramTypes).invoke(entity, new Object[0]);
				if (returnType.equals(int.class) || 
					returnType.equals(Integer.class) || 
					returnType.equals(short.class) ||
					returnType.equals(Short.class) ||
					returnType.equals(byte.class) ||
					returnType.equals(Byte.class)) {

					result.put(fieldName, value == null ? "" : Integer.parseInt(value.toString()));
				} else if (returnType.equals(long.class) || returnType.equals(Long.class)) {
					result.put(fieldName, value == null ? "" : Long.parseLong(value.toString()));
				} else if (returnType.equals(BigDecimal.class) || returnType.equals(double.class) || returnType.equals(Double.class) || returnType.equals(float.class) || returnType.equals(Float.class)) {
					result.put(fieldName, value == null ? "" : Double.parseDouble(value.toString()));
				} else if (returnType.equals(Date.class) || returnType.equals(java.sql.Date.class)) {
					if (value == null)
						result.put(fieldName, "");
					else if (value.toString().length() <= 10)
						result.put(fieldName, FormatUtil.DATE_FORMAT.format(value));
					else
						result.put(fieldName, FormatUtil.DATETIME_FORMAT.format(value));
				} else if (returnType.equals(Timestamp.class)) {
					result.put(fieldName, value == null ? "" : FormatUtil.DATETIME_FORMAT.format(value));
				} else if (returnType.equals(Time.class)) {
					result.put(fieldName, value == null ? "" : FormatUtil.TIME_FORMAT.format(value));
				} else if (returnType.equals(boolean.class) || returnType.equals(Boolean.class)) {
					result.put(fieldName, value == null ? false : value);
				} else if (returnType.equals(byte[].class)) {
					result.put(fieldName, value == null ? "" : Base64.encodeBase64String((byte[]) value));
				} else if (returnType.equals(String.class) || returnType.equals(char.class) || returnType.equals(Character.class) || returnType.equals(Object.class)) {
					result.put(fieldName, value == null ? "" : value.toString());
				} else if (value instanceof Enum<?>) {
					result.put(fieldName, ((Enum<?>) value).ordinal());
				} else if (value instanceof BaseMapping) {
					if (value != null) result.put(fieldName, generateJsonByMapping(value, withOutFields));
				}
			}
			catch(NoSuchMethodException ex) {}
			catch(Exception ex) {}
		}

		return result;
	}
}
