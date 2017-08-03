package cn.org.jxszyyy.application;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AutoLoginPan {
	private final static String HOST = "pan.jxszyyy.org.cn";
	private final static String URL = "http://pan.jxszyyy.org.cn/login";
	private final static String USER_AGENT = "Mozilla/5.0";
	private final static String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	private final static String ACCEPT_LANGUAGE = "en-US,en;q=0.5";
	private final static String ACCEPT_ENCODING = "gzip, deflate";
	private final static String CONTENT_TYPE = "application/x-www-form-urlencoded";

	private String cookies;
	private HttpClient client = HttpClientBuilder.create().build();

	public static void main(String[] args) {
		try {
			CookieHandler.setDefault(new CookieManager());
			AutoLoginPan autoLogin = new AutoLoginPan();
			String page = autoLogin.getPageContent(URL);
			List<NameValuePair> postParams = autoLogin.getFormParams(page);
			page = autoLogin.sendPost(postParams);
			System.out.println(page);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
		}
	}

	private String sendPost(List<NameValuePair> postParams) throws Exception {

		HttpPost post = new HttpPost(URL);

		// add header
		post.setHeader("Host", HOST);
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept", ACCEPT);
		post.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		post.setHeader("Accept-Encoding", ACCEPT_ENCODING);
		post.setHeader("Cookie", getCookies());
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Referer", URL);
		post.setHeader("Content-Type", CONTENT_TYPE);

		post.setEntity(new UrlEncodedFormEntity(postParams));

		HttpResponse response = client.execute(post);

		int responseCode = response.getStatusLine().getStatusCode();

		System.out.println("\nSending 'POST' request to URL : " + URL);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader rd = new BufferedReader(
		new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}

		return result.toString();

	}

	public String getPageContent(String url) throws Exception {
		HttpGet request = new HttpGet(url);
		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept", "text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-Language", "zh-CN,en-US;q=0.5");

		HttpResponse response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}

		// set cookies
		setCookies(response.getFirstHeader("Set-Cookie") == null ? "" : response.getFirstHeader("Set-Cookie").toString());

		return result.toString();
	}

	public List<NameValuePair> getFormParams(String html) throws UnsupportedEncodingException {
		System.out.println("Extracting form's data...");

		Document doc = Jsoup.parse(html);

		Element loginform = doc.getElementsByTag("form").get(0);
		Elements inputElements = loginform.getElementsByTag("input");

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");

			if (key.equals("requesttoken")) paramList.add(new BasicNameValuePair(key, value));
		}
		paramList.add(new BasicNameValuePair("user", "john"));
		paramList.add(new BasicNameValuePair("password", "johnjia"));
		paramList.add(new BasicNameValuePair("timezone", "Asia/Shanghai"));
		paramList.add(new BasicNameValuePair("timezone-offset", "8"));

		return paramList;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

}
