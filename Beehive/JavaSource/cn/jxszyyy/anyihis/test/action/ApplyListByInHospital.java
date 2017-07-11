package cn.jxszyyy.anyihis.test.action;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jxszyyy.anyihis.inhospital.bean.TestApplyOfInHospital;
import cn.jxszyyy.anyihis.service.TestService;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.define.Order;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.RecordUtil;
import com.sojava.beehive.framework.util.ServletUtil;

import javax.annotation.Resource;

@Controller("Anyihis/Test/CheckInHospitalApply")
@Scope("prototype")
@Namespace("/Anyihis/Test")
public class ApplyListByInHospital extends ActionSupport {
	private static final long serialVersionUID = -6648732090840165674L;

	@Resource private TestService hisTestService;
	private String action;
	private String filter;

	@Action(value = "CheckInHospitalApply", results = {
		@Result(name = "applyList", location = "applyListByInHospital.jsp")
	})
	@Override
	public String input() throws Exception {
		super.execute();
		String result = null;

		if (getAction().equalsIgnoreCase("getApplys")) applys(); 
		else result = "applyList";

		return result;
	}

	public void applys() {
		JSONObject result = null;
		try {
			String range = getRequest().getHeader("range");
			int startRow = 1;
			int endRow = 20;
			if (range != null) {
				String ranges[] = range.split("=");
				if (ranges[0].equals("items")) {
					String recRange[] = ranges[1].split("-");
					startRow = Integer.parseInt(recRange[0]) + 1;
					endRow = Integer.parseInt(recRange[1]) + 1;
				}
			}
			Order orders[] = null;
			String sorts[] = null;
			String sort = ServletUtil.getParameterByURIQuery(getRequest(), "[\\?|\\&]?\\Qsort(\\E.*\\)");
			if (sort != null) {
				sorts = sort.replaceFirst("[\\?|\\&]?\\Qsort(\\E", "").replaceFirst("\\)", "").split("\\,");
				orders = new Order[sorts.length];
				for (int i = 0; i < sorts.length; i ++) {
					String _direction = sorts[i].startsWith("\\+") ? "asc" : "desc";
					String _column = sorts[i].substring(1);
					orders[i] = new Order(_column, _direction);
				}
			}

			com.sojava.beehive.framework.common.bean.Result data = hisTestService.getApplysByInHospitalForGrid(null, new Page(startRow, endRow), orders);
//			getResponse().setHeader("Content-Range", "items " + data.getPage().getStart() + "-" + data.getPage().getEnd() + "/" + data.getPage().getTotal());
			result = new RecordUtil().generateJsonByMapping(data.getRecords().toArray(new TestApplyOfInHospital[0]));

			new Writer(getRequest(), getResponse(), data.getPage().getTotal()).output(result.getJSONArray("items"));
		}
		catch(Exception ex) {
			new ErrorException(this.getClass(), ex);
		}
	}

	public TestService getHisTestService() {
		return hisTestService;
	}

	public void setHisTestService(TestService hisTestService) {
		this.hisTestService = hisTestService;
	}

	public String getAction() {
		return action == null ? "" : action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
