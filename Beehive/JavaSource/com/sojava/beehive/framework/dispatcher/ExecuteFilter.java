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
		String domainAccessErrmsg = request.getServletContext().getFilterRegistration(filterRegName).getInitParameter("DomainAccessErrmsg");
		String urlAccessStr = request.getServletContext().getFilterRegistration(filterRegName).getInitParameter("URLAccess");
		urlAccessStr = urlAccessStr == null ? "[]" : urlAccessStr;
		JSONArray urlAccesses = JSONArray.fromObject(urlAccessStr);
		String loginIgnoreRegex = request.getServletContext().getFilterRegistration(filterRegName).getInitParameter("LoginIgnore");
		loginIgnoreRegex = loginIgnoreRegex == null ? "^[\\/]$" : loginIgnoreRegex;
		String uriAssHeaderStr = request.getServletContext().getFilterRegistration(filterRegName).getInitParameter("URIAssHeader");
		uriAssHeaderStr = uriAssHeaderStr == null ? "[]" : uriAssHeaderStr;
		JSONArray uriAssHeaders = JSONArray.fromObject(uriAssHeaderStr);

		try {
			//验证功能（与域名匹配）访问权限
			boolean domainValid = false;
			for (int i = 0; i < urlAccesses.size(); i ++) {
				JSONObject urlAccess = urlAccesses.getJSONObject(i);
				String _domain = urlAccess.getString("domain");
				String _uri = urlAccess.getString("uri");
				String _errmsg = urlAccess.getString("errmsg");

				if (domain.matches(_domain) && !uri.matches(_uri)) {
					throw new ErrorException(_errmsg);
				}
				domainValid = domain.matches(_domain);
				if (domainValid) break;
			}
			if (!domainValid) {
				throw new ErrorException(domainAccessErrmsg);
			}
			//验证功能与Header信息匹配权限
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
			//必须登录后才可进入，但对部分功能不启用（如：登录模块、菜单模块等）
			if (userInfo == null && !uri.matches(loginIgnoreRegex)) {
				try {
					new Writer((HttpServletRequest) request, (HttpServletResponse) response).output("<script>try {top.bee.login.show();} catch(e) {}</script>", ContentType.html);
				}
				catch(Exception ex) {
					new ErrorException(getClass(), ex);
				}
			} else {
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
