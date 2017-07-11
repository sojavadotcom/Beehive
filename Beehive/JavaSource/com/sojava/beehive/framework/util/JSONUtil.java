package com.sojava.beehive.framework.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	public static JSONObject merge(JSONObject...jsonObjects) throws Exception {
		JSONObject result = new JSONObject();
		for (JSONObject jsonObject: jsonObjects) {
			for (Object key: jsonObject.keySet()) {
				result.put(key, jsonObject.get(key));
			}
		}

		return result;
	}

	public static JSONArray merge(JSONArray...jsonArrays) throws Exception {
		JSONArray result = new JSONArray();
		for (JSONArray jsonArray: jsonArrays) {
			for (int i = 0; i < jsonArray.size(); i ++) result.add(jsonArray.get(i));
		}

		return result;
	}
}
