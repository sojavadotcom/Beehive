import com.sojava.beehive.framework.component.inpatienthomepage.service.HomepageCheckerService;

import java.io.File;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AloneTest {

	private static boolean autoClose = true;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:conf/spring/applicationContext.xml");
		try {
			HomepageCheckerService checker = (HomepageCheckerService) ctx.getBean("homepageCheckerServiceImpl");
			checker.loadCSV(new File[] {new File("/Users/John/Documents/Works/公立医院绩效考核/病案首页/TCMMS_201901-08_14023.csv")});
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (autoClose) ctx.close();
		}
	}

}
