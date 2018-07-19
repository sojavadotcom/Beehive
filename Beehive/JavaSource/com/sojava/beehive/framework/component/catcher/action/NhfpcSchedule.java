package com.sojava.beehive.framework.component.catcher.action;

import com.sojava.beehive.framework.component.catcher.dao.CatchArticleDao;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class NhfpcSchedule {

	private final Date DEFAULT_DATE = new Date(1422756036185L);

	private final String HOST = "www.nhfpc.gov.cn";
	private final String URL = "http://www.nhfpc.gov.cn/interview/MyJsp.jsp";
	private final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)";
	private final String ACCEPT = "text/html, application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*";
	private final String ACCEPT_LANGUAGE = "zh-CN";
	private final String ACCEPT_ENCODING = "gzip, deflate";
	private final String CONNECTION = "Keep-Alive";

	private String JSESSIONID = "4B1356F7D9618141000B33CF628D316A";
	private String SECURITY_SESSION_VERIFY = null;
	private String FSSBBIl1UgzbN7N80T = null;
	private String FSSBBIl1UgzbN7N80S = null;
	private String BANGGOO_NUVA_COOKIE = null;

	private HttpClient client = HttpClientBuilder.create().build();

	private int currPage = 1;
	private Date currDate = null;
	private final Date ed = new Date();

	@Resource private CatchArticleDao catchArticleDao;

	public static void main(String[] args) {
		NhfpcSchedule n = new NhfpcSchedule();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2015);
		c.set(Calendar.MONTH, 5);
		c.set(Calendar.DAY_OF_MONTH, 22);
		n.currDate = c.getTime();
		try {
			n.getPageContent(n.URL);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Scheduled(cron = "6 2 0/10,11,14,16,18,20,22 * * ?")
	public void execute() throws Exception {

		try {
			if (currDate == null) {
				currDate = catchArticleDao.getLastDate();
				currDate = currDate == null ? DEFAULT_DATE : currDate;
			}
			String content = getPageContent(URL);
			List<Object> list = parse(content);
			int _totalPage = 0, _currPage = 0;

			for(Object item: list) {
				if (item instanceof Properties) {
					_totalPage = Integer.parseInt(((Properties) item).getProperty("totalPage"));
					_currPage = Integer.parseInt(((Properties) item).getProperty("currPage"));
				} else {
					String _title = ((Map<String, String>) item).get("title");
					String _url = ((Map<String, String>) item).get("url");
					String _date = ((Map<String, String>) item).get("date");

					catchArticleDao.save("卫健委每日更新", _title, _url, _date);
				}
			}
			if (_currPage < _totalPage) {
				currPage = ++ _currPage;
			} else {
				currPage = 1;
				long _time = currDate.getTime() + 86400000L;
				if (_time <= ed.getTime()) {
					currDate.setTime(_time);
				} else {
					currDate.setTime(ed.getTime());
				}
			}
		}
		catch(ErrorException ex) {}
	}

	public String getPageContent(String url) throws Exception {

		RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		HttpGet request = new HttpGet(url + "?curr_page=" + currPage + "&date=" + FormatUtil.DATE_FORMAT.format(currDate));
		request.setConfig(defaultConfig);
		request.setHeader("Accept", ACCEPT);
		request.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept-Encoding", ACCEPT_ENCODING);
		request.setHeader("Connection", CONNECTION);
		String cookie = "";
		if (JSESSIONID != null) cookie = JSESSIONID;
		if (SECURITY_SESSION_VERIFY != null) cookie = (cookie.length() > 0 ? ";" : "") + SECURITY_SESSION_VERIFY;
		if (BANGGOO_NUVA_COOKIE != null) cookie = (cookie.length() > 0 ? ";" : "") + BANGGOO_NUVA_COOKIE;
		if (FSSBBIl1UgzbN7N80T != null) {
			cookie = (cookie.length() > 0 ? ";" : "") + FSSBBIl1UgzbN7N80T + ";" + FSSBBIl1UgzbN7N80S;
		}
		request.setHeader("Cookie", cookie);

		HttpHost host = new HttpHost(HOST);
		HttpResponse response = client.execute(host, request);
		for (Header header : response.getAllHeaders()) {
			String name = header.getName();
			String value = header.getValue();
			if (name.equalsIgnoreCase("Set-Cookie")) {
				if (value.indexOf("JSESSIONID") == 0) {
					JSESSIONID = value.substring(0, value.indexOf(";"));
				} else if (value.indexOf("SECURITY_SESSION_VERIFY") == 0) {
					SECURITY_SESSION_VERIFY = value.substring(0, value.indexOf(";"));
				} else if (value.indexOf("FSSBBIl1UgzbN7N80T") == 0) {
					FSSBBIl1UgzbN7N80T = value.substring(0, value.indexOf(";"));
				} else if (value.indexOf("FSSBBIl1UgzbN7N80S") == 0) {
					FSSBBIl1UgzbN7N80S = value.substring(0, value.indexOf(";"));
				} else if (value.indexOf("banggoo.nuva.cookie") == 0) {
					BANGGOO_NUVA_COOKIE = value.substring(0, value.indexOf(";"));
				}
			}
		}
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}

		return result.toString();
	}

	public List<Object> parse(String content) throws Exception {
		List<Object> list = new ArrayList<Object>();

		try {
			if (content.indexOf("该日没有更新稿件") != -1) throw new NotFoundException("该日没有更新稿件");
			if (content.indexOf("<title>每日更新</title>") == -1) throw new ErrorException(getClass(), "无法解析内容[page:" + currPage + ",date:" + FormatUtil.DATE_FORMAT.format(currDate) + "]");
			int _currPage = 0, _totalPage = 0;
			Pattern p;
			Matcher m;
			String str = "";
			Properties prop = new Properties();
			//总页数
			p = Pattern.compile("\\Q共\\E.*\\Q页\\E");
			m = p.matcher(content);
			if (m.find()) {
				str = m.group();

				p = Pattern.compile("\\D*");
				m = p.matcher(str);
				_totalPage = Integer.parseInt(m.replaceAll(""));
				prop.setProperty("totalPage", _totalPage+"");
			}
			//当前页数
			p = Pattern.compile("\\Q当前第\\E.*\\Q页\\E");
			m = p.matcher(content);
			if (m.find()) {
				str = m.group();

				p = Pattern.compile("\\D*");
				m = p.matcher(str);
				_currPage = Integer.parseInt(m.replaceAll(""));
				prop.setProperty("currPage", _currPage+"");
			}
			list.add(prop);
			//读取条目
			p = Pattern.compile("\\Q<li>·<a \\E.*\\Q</span></li>\\E");
			m = p.matcher(content);
			while (m.find()) {
				String _item = m.group();
				String uri = "";
				String title = "";
				String date = "";
				Pattern _p = Pattern.compile("\\Q\"\\E.*\\Q\" \\E");
				Matcher _m = _p.matcher(_item);
				if (_m.find()) {
					uri = _m.group().replaceAll("\\Q\"\\E", "");
				}
				_p = Pattern.compile("\\Q\">\\E.*\\Q</a>\\E");
				_m = _p.matcher(_item);
				if (_m.find()) {
					title = _m.group().replaceAll("\\Q\">\\E", "").replaceAll("\\Q</a>\\E", "");
				}
				_p = Pattern.compile("\\Q<span>\\E.*\\Q</span>\\E");
				_m = _p.matcher(_item);
				if (_m.find()) {
					date = _m.group().replaceAll("\\Q<\\E\\/{0,1}\\Qspan>\\E", "");
				}

				Map<String, String> item = new HashMap<String, String>();
				item.put("title", title.trim());
				item.put("url", "http://" + HOST + uri.trim());
				item.put("date", date.trim());
				list.add(item);
			}
		}
		catch(NotFoundException ex) {}
		catch(ErrorException ex) {
			throw ex;
		}
		catch(Exception ex) {}

		return list;
	}

	public CatchArticleDao getCatchArticleDao() {
		return catchArticleDao;
	}

	public void setCatchArticleDao(CatchArticleDao catchArticleDao) {
		this.catchArticleDao = catchArticleDao;
	}

}
