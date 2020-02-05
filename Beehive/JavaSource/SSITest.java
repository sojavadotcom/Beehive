import com.sojava.beehive.framework.component.data.service.impl.StatisticsServiceImpl;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SSITest {

	private static boolean autoClose = true;

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:conf/spring/applicationContext.xml");
		try {
			StatisticsServiceImpl s = (StatisticsServiceImpl) ctx.getBean("statisticsServiceImpl");
			byte[] r = s.goodsReport(
					FormatUtil.parseDate("2020-02-03"),
					new File("/Users/John/Documents/Works/抗击新型冠状病毒肺炎工作/数据统计模板/[yyyyMMdd] 防护用品库存.xls"),
					"实数"
				);
			FileOutputStream out = new FileOutputStream("/Users/John/Documents/Works/抗击新型冠状病毒肺炎工作/数据统计模板/aa.xls");
			out.write(r);
			out.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (autoClose) ctx.close();
		}
	}

	public static String getParameter(String[] args, String name, String defaultValue) {
		String ret = defaultValue;
		for (String arg : args) {
			if (arg.toLowerCase().startsWith(name.toLowerCase())) {
				String[] grp = arg.split("\\=");
				ret = grp[grp.length - 1];
				break;
			}
		}
		return ret;
	}

}
