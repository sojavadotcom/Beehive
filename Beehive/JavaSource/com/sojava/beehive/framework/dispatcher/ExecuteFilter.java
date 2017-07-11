package com.sojava.beehive.framework.dispatcher;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.sojava.beehive.framework.servlet.HttpRequest;

public class ExecuteFilter extends StrutsPrepareAndExecuteFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setCharacterEncoding(System.getProperty("system.encoding", "UTF-8"));
		response.setCharacterEncoding(System.getProperty("system.encoding", "UTF-8"));
		request.setCharacterEncoding(System.getProperty("system.encoding", "UTF-8"));
		String contentType = request.getContentType();
		contentType = contentType == null ? "application/x-www-form-urlencoded" : contentType;

		if (contentType.toLowerCase().indexOf("application/x-www-form-urlencoded") != -1) {
			HttpRequest req = new HttpRequest((HttpServletRequest) request);
			Map<String, Serializable> parameter = new HashMap<String, Serializable>();
			parameter.putAll(req.getParameterMap());
			Iterator<String> keys = parameter.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				String[] values = (String[]) parameter.get(key);
				if (values != null) {
					for(int i = 0; i < values.length; i ++) {
						String value = values[i];
						try {
							value = URLDecoder.decode(value, "UTF-8");
							value = URLDecoder.decode(value, "UTF-8");
						}
						catch(IllegalArgumentException e) {}
						values[i] = value;
					}
					parameter.put(key, values);
				}
			}
			req.setParameters(parameter);
			request = req;
		}

		super.doFilter(request, resp, filterChain);
	}
}