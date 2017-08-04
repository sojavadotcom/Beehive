package cn.jxszyyy.dthealth.user.action;

import com.sojava.beehive.framework.ActionSupport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
import org.apache.struts2.convention.annotation.Namespace;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("dthealthUser/Login")
@Scope("prototype")
@Namespace("/DthealthUser")
public class Login extends ActionSupport {
	private static final long serialVersionUID = 9166304239137423071L;

	private final static String HEADER_USER_AGENT = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)";
	private final static String HEADER_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	private final static String HEADER_ACCEPT_LANGUAGE = "zh-CN,en-US;q=0.5";
	private final static String HEADER_ACCEPT_ENCODING = "gzip, deflate";
	private final static String HEADER_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private final static String HEADER_CONNECTION = "keep-alive";
	private final static String HEADER_HOST = "192.168.100.122";

	private final static String BASE_URL = "http://192.168.100.122/dthealth/web";
	private final static String LOGIN_URI = "/csp/dhc.logon.csp";
	private final static String USER_NAME = "xxk";
	private final static String USER_PWD = "0AD2692E4D0A32F862EA2B400B31BE7A";

	private String cookies;
	private HttpClient client = HttpClientBuilder.create().build();

	public static void main(String[] args) {
//		String uri = "http://192.168.100.122/dthealth/web/csp/dhc.logon.csp";
//		HttpPost post = new HttpPost(uri);
//		post.setHeader("User-Agent", USER_AGENT);

		try {
			Login login = new Login();
			String url = BASE_URL + LOGIN_URI;
			String html = login.getPageContent(url);
//			List<NameValuePair> params = login.getFormParams(html);
//			html = login.login(url, params);
//			params = login.getFormParams(html);
//			html = login.login(url, params);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", "52323"));
			html = login.login("http://192.168.100.122/dthealth/web/csp/ext.websys.datatrans.csp?pClassName=ext.websys.Menu&pClassMethod=ShowBarJson", params);
			System.out.println(html);
			/*
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("TFORM", "SSUserLogon"));
			nameValuePairs.add(new BasicNameValuePair("TPAGID", "283512012"));
			nameValuePairs.add(new BasicNameValuePair("TEVENT", "d1473iLogon"));
			nameValuePairs.add(new BasicNameValuePair("TOVERRIDE", ""));
			nameValuePairs.add(new BasicNameValuePair("TDIRTY", "1"));
			nameValuePairs.add(new BasicNameValuePair("TWKFL", ""));
			nameValuePairs.add(new BasicNameValuePair("TWKFLI", ""));
			nameValuePairs.add(new BasicNameValuePair("TWKFLL", ""));
			nameValuePairs.add(new BasicNameValuePair("TWKFLJ", ""));
			nameValuePairs.add(new BasicNameValuePair("TREPORT", ""));
			nameValuePairs.add(new BasicNameValuePair("TRELOADID", ""));
			nameValuePairs.add(new BasicNameValuePair("TFRAME", ""));
			nameValuePairs.add(new BasicNameValuePair("TCONTEXT", ""));
			nameValuePairs.add(new BasicNameValuePair("IPAddress", ""));
			nameValuePairs.add(new BasicNameValuePair("DNSHostName", ""));
			nameValuePairs.add(new BasicNameValuePair("MACAddr", ""));
			nameValuePairs.add(new BasicNameValuePair("RELOGON", ""));
			nameValuePairs.add(new BasicNameValuePair("LocationListFlag", "1"));
			nameValuePairs.add(new BasicNameValuePair("SSUSERGROUPDESC", "Demo+Group"));
			nameValuePairs.add(new BasicNameValuePair("changeinlogonhosp", ""));
//			nameValuePairs.add(new BasicNameValuePair("Hospital", "鸡西市中医医院"));
			nameValuePairs.add(new BasicNameValuePair("Hospital", "%BC%A6%CE%F7%CA%D0%D6%D0%D2%BD%D2%BD%D4%BA"));
			nameValuePairs.add(new BasicNameValuePair("EmployeeNo", ""));
			nameValuePairs.add(new BasicNameValuePair("ADErrMessage", ""));
			nameValuePairs.add(new BasicNameValuePair("LocationListFlagXH", "0"));
			nameValuePairs.add(new BasicNameValuePair("isADEmployee", "1"));
			nameValuePairs.add(new BasicNameValuePair("GetCurrentSeverDateTime", "zc6CbUyWVPWzTLRxbdvIiHGR1CUEjY…Exknc$aImXD8hzPgnJfPNZC5nzQ--"));
			nameValuePairs.add(new BasicNameValuePair("caslogin", ""));
			nameValuePairs.add(new BasicNameValuePair("ticket", ""));
			nameValuePairs.add(new BasicNameValuePair("directpage", ""));
			nameValuePairs.add(new BasicNameValuePair("DEPARTMENTAlias", "XXZX"));
			nameValuePairs.add(new BasicNameValuePair("USERNAME", "xxk"));
			nameValuePairs.add(new BasicNameValuePair("PASSWORD", "0AD2692E4D0A32F862EA2B400B31BE7A"));
//			nameValuePairs.add(new BasicNameValuePair("DEPARTMENT", "信息中心"));
			nameValuePairs.add(new BasicNameValuePair("DEPARTMENT", "%D0%C5%CF%A2%D6%D0%D0%C4"));
	        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        HttpResponse response = client.execute(post);
	        HttpEntity entity = response.getEntity();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
	        String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(new String(line.getBytes("gbk")));
            }
*/
		}
//		catch(UnsupportedEncodingException ex) {
//			ex.printStackTrace();
//		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
		}
	}

	public Login() {
	}

	private String login(String url, List<NameValuePair> postParams) throws Exception {

		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("Host", HEADER_HOST);
		post.setHeader("User-Agent", HEADER_USER_AGENT);
		post.setHeader("Accept", HEADER_ACCEPT);
		post.setHeader("Accept-Language", HEADER_ACCEPT_LANGUAGE);
		post.setHeader("Cookie", cookies);
		post.setHeader("Connection", HEADER_CONNECTION);
		post.setHeader("Referer", url + "?LANGID=1");
		post.setHeader("Content-Type", HEADER_CONTENT_TYPE);

		post.setEntity(new UrlEncodedFormEntity(postParams));

		HttpResponse response = client.execute(post);

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
		request.setHeader("User-Agent", HEADER_USER_AGENT);
		request.setHeader("Accept", HEADER_ACCEPT);
		request.setHeader("Accept-Language", HEADER_ACCEPT_LANGUAGE);
		request.setHeader("Accept-Encoding", HEADER_ACCEPT_ENCODING);

		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}
		cookies = response.getFirstHeader("Set-Cookie") == null ? "" : response.getFirstHeader("Set-Cookie").toString();

		return result.toString();
	}

	public List<NameValuePair> getFormParams(String html) throws UnsupportedEncodingException {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		Document doc = Jsoup.parse(html);

		Element loginform = doc.getElementsByTag("form").first();
		for(Element inputElement: loginform.getElementsByTag("input")) {
			String name = inputElement.attr("name");
			String value = inputElement.attr("value");
			if (name.equalsIgnoreCase("TPAGID")) nameValuePairs.add(new BasicNameValuePair("TPAGID", value));
			else if (name.equalsIgnoreCase("TEVENT")) nameValuePairs.add(new BasicNameValuePair("TEVENT", value));
			else if (name.equalsIgnoreCase("GetCurrentSeverDateTime")) nameValuePairs.add(new BasicNameValuePair("GetCurrentSeverDateTime", value));
		}

		nameValuePairs.add(new BasicNameValuePair("CSPCHD", cookies.substring(cookies.indexOf("=")+1, cookies.indexOf(";"))));
		nameValuePairs.add(new BasicNameValuePair("TFORM", "SSUserLogon"));
		nameValuePairs.add(new BasicNameValuePair("TOVERRIDE", ""));
		nameValuePairs.add(new BasicNameValuePair("TDIRTY", "1"));
		nameValuePairs.add(new BasicNameValuePair("TWKFL", ""));
		nameValuePairs.add(new BasicNameValuePair("TWKFLI", ""));
		nameValuePairs.add(new BasicNameValuePair("TWKFLL", ""));
		nameValuePairs.add(new BasicNameValuePair("TWKFLJ", ""));
		nameValuePairs.add(new BasicNameValuePair("TREPORT", ""));
		nameValuePairs.add(new BasicNameValuePair("TRELOADID", ""));
		nameValuePairs.add(new BasicNameValuePair("TFRAME", ""));
		nameValuePairs.add(new BasicNameValuePair("TCONTEXT", ""));
		nameValuePairs.add(new BasicNameValuePair("IPAddress", ""));
		nameValuePairs.add(new BasicNameValuePair("DNSHostName", ""));
		nameValuePairs.add(new BasicNameValuePair("MACAddr", ""));
		nameValuePairs.add(new BasicNameValuePair("RELOGON", ""));
		nameValuePairs.add(new BasicNameValuePair("LocationListFlag", "1"));
		nameValuePairs.add(new BasicNameValuePair("SSUSERGROUPDESC", "Demo+Group"));
		nameValuePairs.add(new BasicNameValuePair("changeinlogonhosp", ""));
		nameValuePairs.add(new BasicNameValuePair("Hospital", "%BC%A6%CE%F7%CA%D0%D6%D0%D2%BD%D2%BD%D4%BA"));
		nameValuePairs.add(new BasicNameValuePair("EmployeeNo", ""));
		nameValuePairs.add(new BasicNameValuePair("ADErrMessage", ""));
		nameValuePairs.add(new BasicNameValuePair("LocationListFlagXH", "0"));
		nameValuePairs.add(new BasicNameValuePair("isADEmployee", "1"));
		nameValuePairs.add(new BasicNameValuePair("caslogin", ""));
		nameValuePairs.add(new BasicNameValuePair("ticket", ""));
		nameValuePairs.add(new BasicNameValuePair("directpage", ""));
		nameValuePairs.add(new BasicNameValuePair("DEPARTMENTAlias", "XXZX"));
		nameValuePairs.add(new BasicNameValuePair("USERNAME", USER_NAME));
		nameValuePairs.add(new BasicNameValuePair("PASSWORD", USER_PWD));
		nameValuePairs.add(new BasicNameValuePair("DEPARTMENT", "%D0%C5%CF%A2%D6%D0%D0%C4"));

		return nameValuePairs;
	}

}
