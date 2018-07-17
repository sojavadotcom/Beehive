package com.sojava.beehive.framework.component.catcher.action;

import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class Nhfpc {

	private final boolean init = false;
	private final Date bd = new Date(1422756036185L);

	private final String HOST = "www.nhfpc.gov.cn";
	private final String URL = "http://www.nhfpc.gov.cn/interview/MyJsp.jsp";
	private final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)";
	private final String ACCEPT = "text/html,application/xhtml+xml,application/xml,*/*; q=0.1";
	private final String ACCEPT_LANGUAGE = "zh-CN";
	private final String ACCEPT_ENCODING = "gzip, deflate";
	private final String CONTENT_TYPE = "application/x-www-form-urlencoded";

	private HttpClient client = HttpClientBuilder.create().build();

	private int currPage = 1;
	private String currDate = "2018-07-16";

//	@Scheduled(cron = "* */10 * * * ?")
//	public void execute() throws Exception {
	public static void main(String[] args) throws Exception {

		Nhfpc nhfpc = new Nhfpc();
		if (nhfpc.init) nhfpc.init();
		nhfpc.currPage = 1;
		nhfpc.currDate = "2018-07-16";
		String content = nhfpc.getPageContent(nhfpc.URL);
		System.out.println(content);
		try {
			nhfpc.parser(content);
		}
		catch(ErrorException ex) {}
	}

	public void init() throws Exception {
		Date ed = new Date();
	}

	public String getPageContent(String url) throws Exception {

		RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		HttpGet request = new HttpGet(url + "?curr_page=" + currPage + "&date=" + currDate);
		request.setConfig(defaultConfig);
		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept", ACCEPT);
		request.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		request.setHeader("Accept-Encoding", ACCEPT_ENCODING);
		request.setHeader("Content-Type", CONTENT_TYPE);
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

	public List<Map<String, ?>> parser(String content) throws Exception {
		List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();

		try {
			if (content.indexOf("该日没有更新稿件") != -1) throw new NotFoundException("该日没有更新稿件");
			if (content.indexOf("<title>每日更新</title>") == -1) throw new ErrorException(getClass(), "无法解析内容");
			int _currPage = 0, _totalPage = 0;
			Pattern p;
			Matcher m;
			String str = "";
			//总页数
			p = Pattern.compile("\\Q共\\E.*\\Q页\\E");
			m = p.matcher(content);
			if (m.find()) {
				str = m.group();

				p = Pattern.compile("\\D*");
				m = p.matcher(str);
				_totalPage = Integer.parseInt(m.replaceAll(""));
				Map<String, Integer> item = new HashMap<String, Integer>();
				item.put("totalPage", _totalPage);
				list.add(item);
			}
			//当前页数
			p = Pattern.compile("\\Q当前第\\E.*\\Q页\\E");
			m = p.matcher(content);
			if (m.find()) {
				str = m.group();

				p = Pattern.compile("\\D*");
				m = p.matcher(str);
				_currPage = Integer.parseInt(m.replaceAll(""));
				Map<String, Integer> item = new HashMap<String, Integer>();
				item.put("currPage", _currPage);
				list.add(item);
			}
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
				item.put("title", title);
				item.put("url", "http://" + HOST + uri);
				item.put("date", date);
				list.add(item);
			}
		}
		catch(NotFoundException ex) {}
		catch(Exception ex) {}

		return list;
	}

}
