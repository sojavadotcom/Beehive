import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;
import com.sojava.beehive.framework.component.inpatienthomepage.service.HomepageCheckerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AloneTest {

	private static boolean autoClose = true;

	public static void main(String[] args) {
		String filepath = getParameter(args, "filepath", null);
		File file = null;
		String[] fileGrp = null;
		String extName = "";
		
		if (filepath == null) {
			System.out.println("未指定参数 filepath");
			return;
		}
		file = new File(filepath);
		if (!file.exists()) {
			System.out.println("未找到文件：" + filepath);
			return;
		} else if (!file.isFile()) {
			System.out.println("指定的路径不是文件：" + filepath);
			return;
		}
		fileGrp = file.getName().split("\\Q.\\E");
		if (fileGrp.length == 0) {
			System.out.println("指定的文件非.csv或.obj：" + filepath);
			return;
		}
		extName = fileGrp[fileGrp.length - 1].toLowerCase();
		if (!extName.equalsIgnoreCase("csv") && !extName.equalsIgnoreCase("obj")) {
			System.out.println("指定的文件非.csv或.obj：" + filepath);
			return;
		}
		ConfigurableApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:conf/spring/applicationContext.xml");
		try {
			HomepageCheckerService checker = (HomepageCheckerService) ctx.getBean("homepageCheckerServiceImpl");
			System.out.println(System.currentTimeMillis());
			if (extName.equals("csv")) {
			/*
			 * 以加载本地源数据方式处理
			 */
			//new File("D:/MyDocument/鸡西市中医医院/质量管理办公室/公立医院绩效考核/病案首页/TCMMS_201901-08_14023.csv")
			checker.loadCSV(new File[] {file});
			} else if (extName.equals("obj")) {
				/*
				 * 以加载本地预处理后的数据对象处理
				 */
				InpatientHomepageAnaly[] homepageList = null;
				FileInputStream fileStream = null;
				ObjectInputStream in = null;
				try {
					//new File("D:/MyDocument/鸡西市中医医院/质量管理办公室/公立医院绩效考核/病案首页/20190928 201901-08信息中心处理并上报的数据14023.obj")
					fileStream = new FileInputStream(file);
					in = new ObjectInputStream(fileStream);
					homepageList = (InpatientHomepageAnaly[]) in.readObject();
				}
				catch(Exception ex) {
					throw ex;
				}
				finally {
					in.close();
					fileStream.close();
				}
				if (getParameter(args, "saveHomepage", "0").equals("1")) checker.saveHomepage(homepageList);
				//修正数据
				List<InpatientHomepageAnaly> patchList = checker.homepagePatch(homepageList);
				//导入修正数据
				checker.getHomepageDao().importHomepagesAndChecks(patchList.toArray(new InpatientHomepageAnaly[0]));
			}

			System.out.println(System.currentTimeMillis());
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
