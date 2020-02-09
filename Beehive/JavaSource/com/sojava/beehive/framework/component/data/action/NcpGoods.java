package com.sojava.beehive.framework.component.data.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.data.service.NcpGoodsService;
import com.sojava.beehive.framework.component.data.service.StatisticsService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.FormatUtil;
import com.sojava.beehive.framework.util.RecordUtil;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller("Data/NCP/Goods")
@Scope("prototype")
@Namespace("/Data/NCP/Goods")
@ParentPackage("json-default")
public class NcpGoods extends ActionSupport {
	private static final long serialVersionUID = 2527227111889250699L;

	@Resource private NcpGoodsService ncpGoodsService;
	@Resource private StatisticsService statisticsService;

	private String date;
	private File file;
	private String fileName;
	private String type;

	@Action(value = "Query.list")
	public void list() throws Exception {
		super.execute();
		Date time = StringUtils.isEmpty(date) ? new Date() : FormatUtil.parseDate(date);
		Page page = new Page(getStart(), getEnd());
		com.sojava.beehive.framework.component.data.bean.NcpGoods[] list = ncpGoodsService.list(time, getOrders(), page);
		this.setRange(page.getTotal());
		RecordUtil recordUtil = new RecordUtil();
		JSONObject result = recordUtil.generateJsonByMapping(list);
		JSONArray items = result.getJSONArray("items");

		new Writer(getRequest(), getResponse()).output(items);
	}

	@Action(value = "Export")
	public void export() throws Exception {
		super.execute();
		if (StringUtils.isEmpty(date)) throw new ErrorException("统计日期不能为空");
		if (StringUtils.isEmpty(type)) throw new ErrorException("数据类型不能为空");
		if (file == null) throw new ErrorException("报表模板未上传");
		Date expDate = FormatUtil.parseDate(date);
		byte[] report = statisticsService.goodsReport(expDate, file, type);

		String expFilename = fileName;
		String format = expFilename.replaceAll("(^.*\\[)|(\\].*$)", "");
		expFilename = expFilename.replaceAll("\\Q[" + format + "]\\E", FormatUtil.formatDate(expDate, format));

		HttpServletResponse response = getResponse();
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(expFilename, "UTF-8"));
		OutputStream out = response.getOutputStream();
		out.write(report);
		out.flush();
		out.close();
	}

	public NcpGoodsService getNcovGoodsService() {
		return ncpGoodsService;
	}

	public void setNcovGoodsService(NcpGoodsService ncpGoodsService) {
		this.ncpGoodsService = ncpGoodsService;
	}

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
