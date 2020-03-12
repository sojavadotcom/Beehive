import com.sojava.beehive.framework.component.data.bean.CovidGoods;
import com.sojava.beehive.framework.component.data.dao.CovidDataDao;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import net.sf.json.JSONObject;

public class PgJsonbTest {

	private static boolean autoClose = true;

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:conf/spring/applicationContext.xml");
		try {
			CovidDataDao covid = (CovidDataDao) ctx.getBean("covidDataDaoImpl");
			List<CovidGoods> goods = covid.filter();
			for (CovidGoods g : goods) {
				System.out.print(g.getInfo() instanceof JSONObject);
				System.out.println(g.getInfo());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (autoClose) ctx.close();
		}
	}

}
