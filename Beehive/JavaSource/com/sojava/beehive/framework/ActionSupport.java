package com.sojava.beehive.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.sojava.beehive.framework.component.type.ActionType;
import com.sojava.beehive.framework.component.type.ClientType;
import com.sojava.beehive.framework.component.type.ResultType;
import com.sojava.beehive.framework.component.user.bean.UserInfo;
import com.sojava.beehive.framework.util.DojoEGFilterUtil;
import com.sojava.beehive.framework.util.ServletUtil;

public class ActionSupport extends com.opensymphony.xwork2.ActionSupport {
	private static final long serialVersionUID = -1625664042044967550L;

	private ActionContext actionContext;
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String sessionId;
	private UserInfo userInfo;
	private String organId;
	private String moduleId;
	private String processId;
	private ActionType actionType;
	private ClientType clientType;
	private ResultType resultType;
	private String filter;
	private String sort;
	private int start;
	private int end;
	private Criterion condition;
	private Order orders[];

	@Before
	public String execute() throws Exception {
		this.actionContext = ServletActionContext.getContext();
		this.context = ServletActionContext.getServletContext();
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		this.session = request.getSession();
		sessionId = (String) session.getAttribute("sessionId");
		userInfo = (UserInfo) session.getAttribute("UserInfo");//OnlineUser.getUser(sessionId);
		clientType = (clientType == null ? ClientType.Normal : clientType);
		resultType = (resultType == null ? ResultType.Json : resultType);
		actionType = (actionType == null ? ActionType.valueOf(ServletUtil.getServletParameter(request, "actionType", "None")) : actionType);
		//解析filter
		if (this.filter != null) {
			try {
				this.condition = DojoEGFilterUtil.createGroup(JSONObject.fromObject(filter));
			}
			catch(Exception e) {
				this.condition = null;
			}
		}
		//解析来自header的数据范围
		String range = getRequest().getHeader("range");
		if (range != null) {
			range = range.replaceFirst("[\\D]*", "");
			String ranges[] = range.split("\\Q-\\E");
			this.start = Integer.parseInt(ranges[0]);
			this.end = ranges.length > 1 ? Integer.parseInt(ranges[1]) : 0;
		}
		//解析排序
		if (this.sort != null) {
			String[] sorts = this.sort.split("\\Q,\\E");
			this.orders = new Order[sorts.length];
			for (int i = 0; i < sorts.length; i ++) {
				String s = sorts[i];
				String name = s.substring(1);
				this.orders[i] = (s.charAt(0) == '-' ? Order.desc(name) : Order.asc(name));
			}
		}

		return SUCCESS;
	}

	public void setRange(int total) {
		response.setHeader("Content-Range", "items " + this.start + "-" + this.end + "/" + total);
	}

	public ActionContext getActionContext() {
		return actionContext;
	}

	public void setActionContext(ActionContext actionContext) {
		this.actionContext = actionContext;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	protected void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	protected void setSession(HttpSession session) {
		this.session = session;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Order[] getOrders() {
		return orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

	public Criterion getCondition() {
		return condition;
	}

	public void setCondition(Criterion condition) {
		this.condition = condition;
	}

}
