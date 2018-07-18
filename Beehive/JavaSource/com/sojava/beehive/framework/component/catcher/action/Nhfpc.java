package com.sojava.beehive.framework.component.catcher.action;

import com.sojava.beehive.framework.component.catcher.dao.CatchArticleDao;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

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
public class Nhfpc {

	private final Date DEFAULT_DATE = new Date(1422756036185L);

	private final String HOST = "www.nhfpc.gov.cn";
	private final String URL = "http://www.nhfpc.gov.cn/interview/MyJsp.jsp";
	private final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)";
	private final String ACCEPT = "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*";
	private final String ACCEPT_LANGUAGE = "zh-CN";
	private final String ACCEPT_ENCODING = "gzip, deflate";
	private final String CONNECTION = "Keep-Alive";

	private HttpClient client = HttpClientBuilder.create().build();

	private int currPage = 1;
	private Date currDate = null;
	private final Date ed = new Date();

	private final long INTERVAL=1000*5;

	@Resource private CatchArticleDao catchArticleDao;

	@Scheduled(cron = "0 */1 * * * ?")
	public void execute() throws Exception {

		try {
			currDate = catchArticleDao.getLastDate();
			currDate = currDate == null ? DEFAULT_DATE : currDate;
			NhfpcProcessor np = new NhfpcProcessor();
			np.start();
/*
			while(currDate.getTime() <= ed.getTime()) {
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
*/
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
		HttpHost host = new HttpHost(HOST);
		HttpResponse response = client.execute(host, request);
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

	class NhfpcProcessor extends Thread {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			super.run();

			try {
				while(currDate.getTime() <= ed.getTime()) {
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
			}
			catch(ErrorException ex) {}
			catch(Exception ex) {
				new ErrorException(this.getClass(), ex);
			}
		}

		public String getPageContent(String url) throws Exception {

			RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
			HttpGet request = new HttpGet(url + "?curr_page=" + currPage + "&date=" + FormatUtil.DATE_FORMAT.format(currDate));
			request.setConfig(defaultConfig);
			request.setHeader("Accept", ACCEPT);
			request.setHeader("Accept-Language", ACCEPT_LANGUAGE);
			request.setHeader("User-Agent", USER_AGENT);
			request.setHeader("Accept-Encoding", ACCEPT_ENCODING);
			request.setHeader("Connection", CONNECTION);request.setHeader("Cookie", "JSESSIONID=5DCA4C846C9070A2F37B2AC4537455FB; banggoo.nuva.cookie=3|W073h|W073d; FSSBBIl1UgzbN7N80T=2dJFGibxMNBHDtS8srpeaSZK6ge_j2JLrosCCWzs9AA7pg30WxpMKR8qaeE_30Y92kg3HZxPO.oGawmbAPdErOUP4ijgzOJ2Ye_Ji3sK2gLkJX54dD4AKJnTd4OJ_PTHgNXDYuXT8BuBE_PQNIxUrXTIfS6qBTV3TK5BFPHHyqVgaZSwsUbZOsi9aaKXZf5T.8R2.FMx2P8dH8ztRdUnOzLZ8QimHgn7MlrE76fowLxhoLwb33Wgi6Dkj6fMbaf4rzTG1cwXm1AZYM.pKPm984Ru5sdE6l20.0o3wW1IQrcVQwa; FSSBBIl1UgzbN7N80S=HtSzlGPn9fgyMWKQMySu3nbXYKrKzNPWFl5OrHNFEBZDkBRO93yscLlG5u1GZbbs; security_session_verify=389d686db75a6c6650c9988b1ec84794");
			HttpHost host = new HttpHost(HOST);
			sleep(INTERVAL);
			HttpResponse response = client.execute(host, request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line + "\n");
			}

			return result.toString();
		}
	}
}
