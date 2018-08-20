
import java.util.Properties;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTester {
	private static Properties HEADER = new Properties();

	public static void main(String[] args) {
		try {
			Connection conn = Jsoup.connect("http://www.nhfpc.gov.cn/interview/MyJsp.jsp?curr_page=2&date=2018-08-14");
//			HEADER.setProperty("Host", "www.nhfpc.gov.cn");
			HEADER.setProperty("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*");
			HEADER.setProperty("Accept-Language", "zh-CN");
			HEADER.setProperty("Accept-Encoding", "gzip, deflate");
			HEADER.setProperty("Cache-Control", "no-cache");
			HEADER.setProperty("Connection", "Keep-Alive");
			conn.userAgent("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)");
			for(String key : HEADER.keySet().toArray(new String[0])) {
				conn.header(key, HEADER.getProperty(key));
			}
			Document doc = conn.get();
			System.out.println(doc.title());
			System.out.println(doc.html());
			String title = doc.title();
			if (title.trim().equals("")) {
				conn.cookies(conn.response().cookies());
				doc = conn.get();
				System.out.println(doc.title());
				System.out.println(doc.html());
			}
			Elements els = doc.getElementsByTag("li");
			for(Element el : els) {
				Element rec = el.selectFirst("a");
				Element dt = el.selectFirst("span");
				System.out.println(rec.text());
				System.out.println(rec.attr("href"));
				System.out.println(dt.text());
			}
			Element page = doc.selectFirst("div.font1 td");
			if (page != null) {
				System.out.println("record size:" + page.select("strong").get(0).text().trim());
				System.out.println("current page:" + page.select("strong").get(1).text().trim());
				System.out.println("page size:" + page.select("strong").get(2).text().trim());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
