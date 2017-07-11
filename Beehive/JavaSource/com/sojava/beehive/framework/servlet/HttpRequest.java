package com.sojava.beehive.framework.servlet;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpRequest extends HttpServletRequestWrapper {

	private Map<String, Serializable> params;

	public HttpRequest(HttpServletRequest request) {
		super((HttpServletRequest) request);

		params = new HashMap<String, Serializable>();
		params.putAll(request.getParameterMap());
	}

	public void setParameter(String key, String value) throws ArrayIndexOutOfBoundsException {
		setParameter(key, 0, value);
	}

	public void setParameter(String key, int index, String value) throws ArrayIndexOutOfBoundsException {
		Object values = this.params.get(key);
		String[] _values = {value};
		if (values != null) {
			_values = (String[]) values;
			try {
				_values[index] = value;
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				throw new ArrayIndexOutOfBoundsException(ex.getLocalizedMessage() + " (设置参数时，所指定的位置超出已知范围)");
			}
		}
		this.params.put(key, _values);
	}

	public String getParameter(String key) {
		Object result = params.get(key);
		if (result != null) {
			return ((String[]) result)[0];
		} else {
			return (String) result;
		}
	}

	@Override
	public String[] getParameterValues(String name) {
		return (String[]) this.params.get(name);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Enumeration getParameterNames() {
		return Collections.enumeration(this.params.keySet());
	}

	public void setParameters(Map<String, Serializable> parameter) {
		this.params.clear();
		this.params.putAll(parameter);
	}

}
