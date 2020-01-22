package com.sojava.beehive.framework.dispatcher;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.io.ContentType;
import com.sojava.beehive.framework.io.Writer;
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

		HttpSession session = ((HttpServletRequest) request).getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
		String uri = ((HttpServletRequest) request).getRequestURI();
		String filterRegName = "beehiveFilter";
		Map<String, ? extends FilterRegistration> filterReg = request.getServletContext().getFilterRegistrations();
		for (String name : filterReg.keySet()) {
			if (filterReg.get(name).getClassName().equals(this.getClass().getName())) {
				filterRegName = name;
				break;
			}
		}
		String domain = request.getScheme() + "://" + request.getServerName();
		String domainAccessStr = request.getServletContext().getFilterRegistration(filterRegName).getInitParameter("DomainAccess");
		domainAccessStr = domainAccessStr == null ? "[]" : domainAccessStr;
		JSONArray domainAccesses = JSONArray.fromObject(domainAccessStr);
		String uriAccessRegex = request.getServletContext().getFilterRegistration(filterRegName).getInitParameter("URIAccess");
		uriAccessRegex = uriAccessRegex == null ? "^[\\/]$" : uriAccessRegex;
		String uriAssHeaderStr = request.getServletContext().getFilterRegistration(filterRegName).getInitParameter("URIAssHeader");
		uriAssHeaderStr = uriAssHeaderStr == null ? "[]" : uriAssHeaderStr;
		JSONArray uriAssHeaders = JSONArray.fromObject(uriAssHeaderStr);

		try {
			boolean domainValid = false;
			for (int i = 0; i < domainAccesses.size(); i ++) {
				JSONObject domainAccess = domainAccesses.getJSONObject(i);
				String _domain = domainAccess.getString("domain");
				String _uri = domainAccess.getString("uri");

				if (domain.matches(_domain) && !uri.matches(_uri)) {
					throw new ErrorException("功能未授权，不能访问");
				}
				if (domain.matches(_domain)) {
					domainValid = true;
					break;
				}
			}
			if (!domainValid) {
				throw new ErrorException("地址未授权，不能访问");
			}
			if (userInfo == null && !uri.matches(uriAccessRegex)) {
				try {
					new Writer((HttpServletRequest) request, (HttpServletResponse) response).output("<script>try {top.bee.login.show();} catch(e) {}</script>", ContentType.html);
				}
				catch(Exception ex) {
					new ErrorException(getClass(), ex);
				}
			} else {
				for (int i = 0; i < uriAssHeaders.size(); i ++) {
					JSONObject uriAssHeader = uriAssHeaders.getJSONObject(i);
					String _uri = uriAssHeader.getString("uri");
					String _name = uriAssHeader.getString("name");
					String _value = uriAssHeader.getString("value");
					String _errmsg = uriAssHeader.getString("errmsg");
					if (uri.matches(_uri) && !getHeadValue(request, _name).matches(_value)) {
						throw new ErrorException(_errmsg);
					}
				}
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
									value = URLDecoder.decode(value, System.getProperty("system.encoding", "UTF-8"));
									value = URLDecoder.decode(value, System.getProperty("system.encoding", "UTF-8"));
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
		catch(ErrorException ex) {
			try {
				new Writer((HttpServletRequest) request, (HttpServletResponse) response).output(ex.getMessage(), ContentType.html);
			}
			catch(Exception e) {
				new ErrorException(getClass(), ex);
			}
		}
	}

	private String getHeadValue(ServletRequest request, String name) {
		Enumeration<String> names = ((HttpServletRequest) request).getHeaderNames();
		while(names.hasMoreElements()) {
			String _name = names.nextElement();
			if (_name.equals(name)) return ((HttpServletRequest) request).getHeader(_name);
		}
		return null;
	}
}
