package com.sojava.beehive.framework.util;

import java.util.ArrayList;
import java.util.List;

public class Array {
	public static Object[] deleteAt(Object[] src, int index) {
		if (src == null) return null;
		List<Object> result = new ArrayList<Object>();
		for (int i = 0; i < src.length; i ++) {
			if (i != index) result.add(src[i]);
		}

		return result.toArray();
	}
	public static Object[] deleteAt(Object[] src, Object target) {
		if (src == null) return null;
		List<Object> result = new ArrayList<Object>();
		for (Object value: src) {
			if (target.equals(value)) result.add(value);
		}

		return result.toArray();
	}
}
