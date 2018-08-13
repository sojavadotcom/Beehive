import com.sojava.beehive.framework.component.catcher.action.NhfpcSchedule;

import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AloneTest {

	private static boolean autoClose = false;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:conf/spring/applicationContext.xml");
		try {
			NhfpcSchedule schedule = (NhfpcSchedule) ctx.getBean("nhfpcSchedule");
			Date dt = new Date();
			dt.setTime(1533830400000l);
			schedule.setCurrDate(dt);
			System.out.println(schedule.getCurrDate());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (autoClose) ctx.close();
		}
	}

}
