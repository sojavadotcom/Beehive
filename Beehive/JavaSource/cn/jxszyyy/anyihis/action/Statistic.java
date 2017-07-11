package cn.jxszyyy.anyihis.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jxszyyy.anyihis.service.StatisticService;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.FormatUtil;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

@Controller("Anyihis/Statistic")
@Scope("prototype")
@Namespace("/Anyihis")
public class Statistic extends ActionSupport {
	private static final long serialVersionUID = 7595365378264595613L;

	@Resource private StatisticService statisticService;
	private String startDate;
	private String endDate;

	@Override
	@Actions(value = {
		@Action(value = "StatisticInhospital"),
		@Action(value = "StatisticOutpatient")
	})
	public String input() throws Exception {
		super.execute();

		getClass().getMethod(getActionContext().getName().replaceFirst("\\QStatistic\\E", "").toLowerCase(), new Class[0]).invoke(this, new Object[0]);

		return null;
	}

	public void inhospital() {
		JSONObject result = new JSONObject();
		try {
/*
			Date start, end;
			Calendar c = Calendar.getInstance();
			if (c.get(Calendar.DAY_OF_MONTH) < 26) {
				c.set(Calendar.DAY_OF_MONTH, 25);
				end = c.getTime();
				c.set(Calendar.DAY_OF_MONTH, 26);
				c.add(Calendar.MONTH, -1);
				start = c.getTime();
			} else {
				c.add(Calendar.MONTH, 1);
				c.set(Calendar.DAY_OF_MONTH, 25);
				end = c.getTime();
				c.set(Calendar.DAY_OF_MONTH, 26);
				start = c.getTime();
			}
			JSONObject result = new JSONObject();
			JSONObject thisYear = statisticService.outpatientByMonth(start, end);
			result.accumulate("thisYear", thisYear.getJSONArray("items"));
			c.setTime(start);
			c.add(Calendar.YEAR, -1);
			start = c.getTime();
			c.setTime(end);
			c.add(Calendar.YEAR, -1);
			JSONObject lastYear = statisticService.outpatientByMonth(start, end);
			result.accumulate("lastYear", lastYear.getJSONArray("items"));
			new Writer().output(result);
*/
			Date start = FormatUtil.parseDate("2016-05-26");
			Date end = FormatUtil.parseDate("2016-06-25");
			Calendar c = Calendar.getInstance();
			c.setTime(start);
			JSONArray xLabels = new JSONArray();
			int i = 1;
			while (c.getTimeInMillis() <= end.getTime()) {
				JSONObject xLabel = new JSONObject();
				xLabel.accumulate("value", i ++);
				xLabel.accumulate("text", c.get(Calendar.MONTH)+1 + "月" + c.get(Calendar.DAY_OF_MONTH) + "日");
				xLabels.add(xLabel);
				c.add(Calendar.DAY_OF_MONTH, 1);
			}
			result.accumulate("x", xLabels);
			JSONArray y = statisticService.inhospitalByDay(start, end);
			JSONArray newY = new JSONArray();
			for (i = 0; i < y.size(); i ++) {
				JSONObject val = new JSONObject();
				val.accumulate("x", i+1);
				val.accumulate("y", y.getJSONObject(i).getInt("value"));
				newY.add(val);
			}
			result.accumulate("y", newY);
			new Writer(getRequest(), getResponse()).output(result);
		}
		catch(Exception ex) {
			new ErrorException(getClass(), ex);
		}
	}

	public void outpatient() {
		JSONObject result = new JSONObject();
		try {
			result.accumulate("identifier", "id");
			result.accumulate("idAttribute", "id");
			result.accumulate("label", "amount");
			parseOutpatientData(result, statisticService.outpatientByDay(FormatUtil.DATE_FORMAT.parse("2015-05-26"), FormatUtil.DATE_FORMAT.parse("2015-06-25")));
			parseOutpatientData(result, statisticService.outpatientByDay(FormatUtil.DATE_FORMAT.parse("2016-05-26"), FormatUtil.DATE_FORMAT.parse("2016-06-25")));

			new Writer(getRequest(), getResponse()).output(result);
		}
		catch(Exception ex) {
			new ErrorException(getClass(), ex);
		}
	}

	public void parseOutpatientData(JSONObject data, JSONArray recs) {
		JSONArray items = data.get("items") == null ? new JSONArray() : data.getJSONArray("items");
		for(int i = 0; i < recs.size(); i ++) {
			JSONObject rec = recs.getJSONObject(i);
			String date[] = rec.getString("type").substring(0, 10).split("\\-");
			int amount = rec.getInt("amount");
			JSONObject item = new JSONObject();
			item.accumulate("id", items.size()+1);
			item.accumulate("sort", rec.getString("type").substring(0, 10).replaceAll("\\-", ""));
			item.accumulate("monthDay", date[1] + "月" + date[2] + "日");
			item.accumulate("amount", amount);
			item.accumulate("year", date[0]);
			item.accumulate("month", date[1]);
			item.accumulate("day", date[2]);
			items.add(item);
		}
		data.put("items", items);
	}

	public StatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
