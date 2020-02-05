package com.sojava.beehive.framework.component.data.service.impl;

import com.sojava.beehive.framework.component.data.bean.NcovGoods;
import com.sojava.beehive.framework.component.data.dao.NcovDataDao;
import com.sojava.beehive.framework.component.data.service.StatisticsService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Resource private NcovDataDao ncovDataDao;

	public byte[] goodsReport(Date date, String srcDept, File template) throws ErrorException {
		byte[] report = null;
//		List<NcovGoods> list = ncovDataDao.goodsSumByDestType(date, srcDept, "实数");
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(template);
			HSSFWorkbook book = new HSSFWorkbook(in);
			for (int i = 0; i < book.getNumberOfSheets(); i ++) {
				HSSFSheet sheet = book.getSheetAt(i);
				String sheetName = sheet.getSheetName();
				HSSFCell cell = null;
				/*
				 * 	读取定义
				 */
				cell = sheet.getRow(0).getCell(0);
				JSONObject def = JSONObject.fromObject(cell.getStringCellValue());
				/*
				 * 	标题处理
				 */
				if (def.containsKey("title")) {
					int defTitle[] = {Integer.parseInt(def.getString("title").split("\\Q,\\E")[1])-1, Integer.parseInt(def.getString("title").split("\\Q,\\E")[0])-1};
					cell = sheet.getRow(defTitle[0]).getCell(defTitle[1]);
					String title = cell.getStringCellValue();
					String expStr = title.replaceAll("(.*\\[)|(\\].*$)", "");
					if (expStr.indexOf("date:") != -1) {
						String formatStr = expStr.split("\\Q:\\E")[1].replaceAll("\\Q]\\E", "");
						String dateStr = FormatUtil.formatDate(date, formatStr);
						title = title.replaceAll("\\Q[" + expStr + "]\\E", dateStr);
						cell.setCellValue(title);
					}
				}
				/*
				 * 	数据处理
				 */
				if (def.containsKey("data")) {
					
				}
				/*
				 * 	删除定义行
				 */
				sheet.removeRow(sheet.getRow(0));
				sheet.shiftRows(1, sheet.getLastRowNum(), -1, true, true);
				sheet.setActive(true);
				sheet.getRow(0).getCell(0).setAsActiveCell();
			}
			out = new FileOutputStream("C:/aa.xls");
			book.write(out);
			out.flush();
		}
		catch(IOException ex) {
			throw new ErrorException(getClass(), ex);
		}
		finally {
			try {in.close();} catch(Exception e) {}
			try {out.close();} catch(Exception e) {}
		}

		return report;
	}

	public NcovDataDao getNcovDataDao() {
		return ncovDataDao;
	}

	public void setNcovDataDao(NcovDataDao ncovDataDao) {
		this.ncovDataDao = ncovDataDao;
	}
}
