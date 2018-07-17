package cn.jxszyyy.dthealth.user.action;

import com.sojava.beehive.framework.ActionSupport;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
	private final static String USER_NAME = "A0208";
	private final static String USER_PWD = "829966EA95734CB4E286DF714886B670";
	private final static String DEPARTMENT_ALIAS = "ZLGLBGS";
	private final static String DEPARTMENT = "%D6%CA%C1%BF%B9%DC%C0%ED%B0%EC%B9%AB%CA%D2";
	private final static String DEFAULT_PARAMS[] = {
			"TFORM=SSUserLogon",
			"TPAGID=490794529",
			"TEVENT=d1473iLogon",
			"TOVERRIDE",
			"TDIRTY=1",
			"TWKFL",
			"TWKFLI",
			"TWKFLL",
			"TWKFLJ",
			"TREPORT",
			"TRELOADID",
			"TFRAME",
			"TCONTEXT",
			"IPAddress",
			"DNSHostName",
			"MACAddr",
			"RELOGON",
			"LocationListFlag=1",
			"SSUSERGROUPDESC=%BF%C6%CA%D2%D6%CA%BF%D8%D4%B1",
			"changeinlogonhosp",
			"Hospital=%BC%A6%CE%F7%CA%D0%D6%D0%D2%BD%D2%BD%D4%BA",
			"EmployeeNo",
			"ADErrMessage",
			"LocationListFlagXH=0",
			"isADEmployee=1",
			"GetCurrentSeverDateTime=WglrCqdMd8_4t7rxQzE7Qt_pIvHJlPNowQU1YdheProaGKPtWIvwAGYU0Op$gcM9RLv_5DtQYOQmxfOUfXmCVQ--",
			"caslogin",
			"ticket",
			"directpage"
		};

	private String cookies;
	private HttpClient client = HttpClientBuilder.create().build();

	public static void main(String[] args) {

		String html = null;
		try {
			Login login = new Login();
//			String url = BASE_URL + LOGIN_URI;
//			html = login.getPageContent(url);
//			List<NameValuePair> params = new ArrayList<NameValuePair>();
//			params.add(new BasicNameValuePair("id", "52323"));
			html = login.login("http://192.168.100.122/dthealth/web/csp/dhc.logon.csp?TFORM=SSUserLogon&TPAGID=490794529&TEVENT=d1473iLogon&TOVERRIDE&TDIRTY=1&TWKFL&TWKFLI&TWKFLL&TWKFLJ&TREPORT&TRELOADID&TFRAME&TCONTEXT&IPAddress&DNSHostName&MACAddr&RELOGON&LocationListFlag=1&SSUSERGROUPDESC=%BF%C6%CA%D2%D6%CA%BF%D8%D4%B1&changeinlogonhosp&Hospital=%BC%A6%CE%F7%CA%D0%D6%D0%D2%BD%D2%BD%D4%BA&EmployeeNo&ADErrMessage&LocationListFlagXH=0&isADEmployee=1&GetCurrentSeverDateTime=WglrCqdMd8_4t7rxQzE7Qt_pIvHJlPNowQU1YdheProaGKPtWIvwAGYU0Op$gcM9RLv_5DtQYOQmxfOUfXmCVQ--&caslogin&ticket&directpage&DEPARTMENTAlias=ZLGLBGS&USERNAME=A0208&PASSWORD=829966EA95734CB4E286DF714886B670&DEPARTMENT=%D6%CA%C1%BF%B9%DC%C0%ED%B0%EC%B9%AB%CA%D2");
			html = login.getPageContent("http://192.168.100.122/dthealth/web/csp/websys.csp?TFORM=DHCExamPatList&TPAGID=491255329&TEVENT=d54899iFind&TOVERRIDE&TDIRTY=1&TWKFL&TWKFLI&TWKFLL&TWKFLJ&TREPORT&TRELOADID=202611502&TFRAME&GetMethodByPatNo=QYKOGnMyVtfumie_ZWfMv6r6gCbiKQqx9xhpCJMqbk_YyjEclfOmQfhS2OFyTId5&GetMethodByPatMed=RAPSfd3MqQGd4BTX7IHIxAwfPoDEiwjGimgovvORSdv2pGn_HYp2B6tJnC2HwOTd&ReadAccEncrypt=amn9XHlDkqxzfyHW0YzkIkRLTyWKlGYANiUF6pL9Gv5mLbohiOCi5tgbZrj2KINVdbmltixtR1HRmtAgl82Kvw--&ReadAccExpEncrypt=ItqgvV7bygLvVG_OU_whsJ74TzMCRlas6KjO1SoR7X_mh91LAEo6hMvKdEU034VB2P$5lJTI3D7hXs_tP7RgrA--&CardTypeEncrypt=CpC$PQl_oaF6aryD4h$5MO9GDcCM3cI$jeyNuIX4x_17AjdW1p$6FNM40Hq$8ONipFflyMbvHZlOmC$cC09Ntg--&PapmiDr&WardID=22&GetDefaultWard=zFXxJZ0WbA_XE4EpXuJPBwwfPoDEiwjGimgovvORSdunAi8yBShy$fd3wqJCSaJe&printpath=g_7VDgci0SoKhuDCOrdAsBFHv_d_bpS7FvJSATkdUTJDPRc3lV4qTvimkpwfuFjd&UserID=4376&GetPrintMessage=O1yqiHcUrtoJJ7dwqjt7JgwfPoDEiwjGimgovvORSdtHPMLrko3LnfFFRv3KKoQAeRiLPrg4XkwdQXp6zigdkw--&GetRegID=mPB6fLxLXL70jOPTd0oZM6X3GSlaAsGXLJRyOWKnGv1rzh9tfe4CpnO7fafX99rG&GetPatientMes=r_bIdZBcVTYsibNfPuyhtwwfPoDEiwjGimgovvORSdvCNdeZasi6HP_4q9wXzh3h&GetPatientSex=GAmIetw0gMkhvXiLLrvNnCfWxlb57leV0bU8ELBqqzI-&GetMethodByPatName=qhgFXhfKOVVkOe2iUH6LGgwfPoDEiwjGimgovvORSdsK6o6zhtUcmSU8ySlSfmr0&CardNo&CardTypeDefine=2%5E02%5E%D7%A2%B2%E1%D2%BD%C1%C6%BF%A8%5EC%5EN%5EL%5E1%5EY%5EY%5E62182%5E%5EIE%5EN%5ECardNo%5E%5E%5EHandle%5E14%5EN%5EN%5EName%5EY%5EReadCard%5EN%5ECA%5EUDHCCardInvPrt%5E%5EY%5EPC%5E%5ECQU%5EY%5EY%5EY%5E%5E&Startdate=25/06/2018&Enddate=25/06/2018&PatNo&Name&Sex&Birth&InStatus=on&PatMed&CTLoc&WardDesc=%B6%F9%BF%C6%BB%A4%C0%ED%B5%A5%D4%AA&PatientIDz1=270766&EpisodeIDz1=493011");
			System.out.println(html);
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

	public Login() {}

	private String login(String url) throws Exception {
		HttpGet get = new HttpGet(url);
		get.setHeader("Host", HEADER_HOST);
		get.setHeader("User-Agent", HEADER_USER_AGENT);
		get.setHeader("Accept", HEADER_ACCEPT);
		get.setHeader("Accept-Language", HEADER_ACCEPT_LANGUAGE);
		get.setHeader("Cookie", cookies);
		get.setHeader("Connection", HEADER_CONNECTION);
		get.setHeader("Referer", BASE_URL + LOGIN_URI + "?LANGID=1");
		get.setHeader("Content-Type", HEADER_CONTENT_TYPE);

		HttpResponse response = client.execute(get);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}

		return result.toString();
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
		BufferedInputStream in = new BufferedInputStream(response.getEntity().getContent());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int len = 0;
		while((len=in.read(buff, 0, 1024)) != -1) {
			out.write(buff, 0, len);
		}
		in.close();
		buff = out.toByteArray();
		out.close();
		String result = new String(buff, Charset.forName("GB18030"));
/*
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}
*/
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
		nameValuePairs.add(new BasicNameValuePair("SSUSERGROUPDESC", "%BF%C6%CA%D2%D6%CA%BF%D8%D4%B1"));
		nameValuePairs.add(new BasicNameValuePair("changeinlogonhosp", ""));
		nameValuePairs.add(new BasicNameValuePair("Hospital", "%BC%A6%CE%F7%CA%D0%D6%D0%D2%BD%D2%BD%D4%BA"));
		nameValuePairs.add(new BasicNameValuePair("EmployeeNo", ""));
		nameValuePairs.add(new BasicNameValuePair("ADErrMessage", ""));
		nameValuePairs.add(new BasicNameValuePair("LocationListFlagXH", "0"));
		nameValuePairs.add(new BasicNameValuePair("isADEmployee", "1"));
		nameValuePairs.add(new BasicNameValuePair("caslogin", ""));
		nameValuePairs.add(new BasicNameValuePair("ticket", ""));
		nameValuePairs.add(new BasicNameValuePair("directpage", ""));
		nameValuePairs.add(new BasicNameValuePair("DEPARTMENTAlias", DEPARTMENT_ALIAS));
		nameValuePairs.add(new BasicNameValuePair("USERNAME", USER_NAME));
		nameValuePairs.add(new BasicNameValuePair("PASSWORD", USER_PWD));
		nameValuePairs.add(new BasicNameValuePair("DEPARTMENT", DEPARTMENT));

		return nameValuePairs;
	}

}
