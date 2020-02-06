package com.sojava.beehive.framework.component.data.service.impl;

import com.sojava.beehive.framework.component.data.bean.NcovGoods;
import com.sojava.beehive.framework.component.data.dao.NcovDataDao;
import com.sojava.beehive.framework.component.data.service.StatisticsService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Resource private NcovDataDao ncovDataDao;

	private Map<String, String> deptType;

	public StatisticsServiceImpl() {
		this.deptType = new HashMap<String, String>();
		deptType.put("卫材库", "后勤保障");
		deptType.put("发热门诊", "发热门诊");
		deptType.put("门诊部", "行政科室");
	}

	@Override
	public byte[] goodsReport(Date date, File template, String type) throws ErrorException {
		byte[] report = null;

		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new FileInputStream(template);
			HSSFWorkbook book = new HSSFWorkbook(in);
			FormulaEvaluator fe = book.getCreationHelper().createFormulaEvaluator();
			for (int i = 0; i < book.getNumberOfSheets(); i ++) {
				HSSFSheet sheet = book.getSheetAt(i);
				sheet.setForceFormulaRecalculation(true);
				sheet.setColumnHidden(0, true);
				sheet.setActive(true);
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
					//数据定义
					JSONObject dataObj = JSONObject.fromObject(def.getString("data"));

					//物资项目
					List<String> items = new ArrayList<String>();
					String itemsStr[] = dataObj.getString("items").split("\\Q:\\E");
					int[] itemsPos = {
							Integer.parseInt(itemsStr[0].split("\\Q,\\E")[1])-1, //start row
							Integer.parseInt(itemsStr[0].split("\\Q,\\E")[0])-1, //start cell
							Integer.parseInt(itemsStr[1].split("\\Q,\\E")[1])-1, //end row
							Integer.parseInt(itemsStr[1].split("\\Q,\\E")[0])-1 //end cell
						};
					for(int j = itemsPos[0]; j <= itemsPos[2]; j ++) {
						String item = sheet.getRow(j).getCell(itemsPos[1]).getStringCellValue().replaceAll("\\Q其中：\\E", "").replaceAll("\\Q　\\E", "");
						items.add(item);
					}

					//基数
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					c.add(Calendar.DAY_OF_MONTH, -1);
					List<NcovGoods> list = ncovDataDao.goodsSumByDestType(c.getTime(), sheetName, null, type, "存量");
					if (list.size() > 0) {
						NcovGoods goods = list.get(0);
						String origStr[] = dataObj.getString("orig").split("\\Q:\\E");
						int[] origPos = {
								Integer.parseInt(origStr[0].split("\\Q,\\E")[1])-1, //start row
								Integer.parseInt(origStr[0].split("\\Q,\\E")[0])-1, //start cell
								Integer.parseInt(origStr[1].split("\\Q,\\E")[1])-1, //end row
								Integer.parseInt(origStr[1].split("\\Q,\\E")[0])-1 //end cell
							};
						int n = 0;
						for(int j = origPos[0]; j <= origPos[2]; j ++) {
							Method m = Class.forName(NcovGoods.class.getName()).getMethod("get" + items.get(n).substring(0, 1).toUpperCase() + items.get(n).substring(1), new Class[0]);
							sheet.getRow(j).getCell(origPos[1]).setCellValue(Double.parseDouble(m.invoke(goods, new Object[0]).toString()));
							n ++;
						}

						//消耗
						list = ncovDataDao.goodsSumByDestType(date, sheetName, null, type, "消耗");
						String expStr[] = dataObj.getString("exp").split("\\Q:\\E");
						int[] expPos = {
								Integer.parseInt(expStr[0].split("\\Q,\\E")[1])-1, //start row
								Integer.parseInt(expStr[0].split("\\Q,\\E")[0])-1, //start cell
								Integer.parseInt(expStr[1].split("\\Q,\\E")[1])-1, //end row
								Integer.parseInt(expStr[1].split("\\Q,\\E")[0])-1 //end cell
							};
						int deptDestRow = dataObj.getInt("deptDest")-1;
						for (int j = expPos[1]; j <= expPos[3]; j ++) {
							n = 0;
							String deptDest = sheet.getRow(deptDestRow).getCell(j).getStringCellValue();
							for (NcovGoods _goods : list) {
								if (_goods.getDeptDest().equals(deptDest)) {
									for(int k = expPos[0]; k <= expPos[2]; k ++) {
										Method m = Class.forName(NcovGoods.class.getName()).getMethod("get" + items.get(n).substring(0, 1).toUpperCase() + items.get(n).substring(1), new Class[0]);
										sheet.getRow(k).getCell(j).setCellValue(Double.parseDouble(m.invoke(_goods, new Object[0]).toString()));
										n ++;
									}
									break;
								}
							}
						}
					}

					//入库
					list = ncovDataDao.goodsSumByDestType(date, dataObj.getString("impDept"), sheetName, type, "消耗");
					if (list.size() > 0) {
						NcovGoods goods = list.get(0);
						String impStr[] = dataObj.getString("imp").split("\\Q:\\E");
						int[] impPos = {
								Integer.parseInt(impStr[0].split("\\Q,\\E")[1])-1, //start row
								Integer.parseInt(impStr[0].split("\\Q,\\E")[0])-1, //start cell
								Integer.parseInt(impStr[1].split("\\Q,\\E")[1])-1, //end row
								Integer.parseInt(impStr[1].split("\\Q,\\E")[0])-1 //end cell
							};
						int n = 0;
						for(int j = impPos[0]; j <= impPos[2]; j ++) {
							Method m = Class.forName(NcovGoods.class.getName()).getMethod("get" + items.get(n).substring(0, 1).toUpperCase() + items.get(n).substring(1), new Class[0]);
							sheet.getRow(j).getCell(impPos[1]).setCellValue(Double.parseDouble(m.invoke(goods, new Object[0]).toString()));
							n ++;
						}
						//备注
						if (dataObj.containsKey("memo") && goods.getMemo() != null) {
							try {
								String memoStr[] = dataObj.getString("memo").split("\\Q:\\E");
								JSONObject memoObj = JSONObject.fromObject(goods.getMemo());
								int[] memoPos = {
										Integer.parseInt(memoStr[0].split("\\Q,\\E")[1])-1, //start row
										Integer.parseInt(memoStr[0].split("\\Q,\\E")[0])-1, //start cell
										Integer.parseInt(memoStr[1].split("\\Q,\\E")[1])-1, //end row
										Integer.parseInt(memoStr[1].split("\\Q,\\E")[0])-1 //end cell
									};
								n = 0;
								for(int j = memoPos[0]; j <= memoPos[2]; j ++) {
									HSSFCell _cell = sheet.getRow(j).getCell(memoPos[1]);
									String _name = items.get(n).toLowerCase();
									if (memoObj.containsKey(_name)) {
										_cell.setCellValue(memoObj.getString(_name));
									}
									n ++;
								}
							}
							catch(JSONException e) {}
						}
					}

					//合计，计入基数
					if (dataObj.containsKey("total")) {
						String totalStr[] = dataObj.getString("total").split("\\Q:\\E");
						int[] totalPos = {
								Integer.parseInt(totalStr[0].split("\\Q,\\E")[1])-1, //start row
								Integer.parseInt(totalStr[0].split("\\Q,\\E")[0])-1, //start cell
								Integer.parseInt(totalStr[1].split("\\Q,\\E")[1])-1, //end row
								Integer.parseInt(totalStr[1].split("\\Q,\\E")[0])-1 //end cell
							};
						int n = 0;
						NcovGoods goods = null;
						Date totalDatetime = FormatUtil.parseDateTime(FormatUtil.formatDate(date, "yyyy-MM-dd") + " " + "14:00:00");
						List<?> goodsList = ncovDataDao.query(NcovGoods.class,
								new Criterion[] {
										Restrictions.eq("deptSrc", sheetName),
										Restrictions.eq("deptDest", sheetName),
										Restrictions.eq("deptDestType", this.deptType.get(sheetName)),
										Restrictions.eq("time", totalDatetime),
										Restrictions.eq("kind", "存量"),
										Restrictions.eq("type", type)
									},
								null,
								null,
								false
							);
						if (goodsList.size() > 0) goods = (NcovGoods) goodsList.get(0);
						else {
							goods = new NcovGoods();
							goods.setDeptSrc(sheetName);
							goods.setDeptDest(sheetName);
							goods.setDeptDestType(this.deptType.get(sheetName));
							goods.setTime(totalDatetime);
							goods.setKind("存量");
							goods.setType(type);
						}

						for(int j = totalPos[0]; j <= totalPos[2]; j ++) {
							Method m = Class.forName(NcovGoods.class.getName()).getMethod("set" + items.get(n).substring(0, 1).toUpperCase() + items.get(n).substring(1), new Class[] {Double.class});
							HSSFCell _cell = sheet.getRow(j).getCell(totalPos[1]);
							if (_cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) fe.evaluateFormulaCell(_cell);
							m.invoke(goods, new Object[] {_cell.getNumericCellValue()});
							n ++;
						}
						ncovDataDao.save(goods);
					}
				}
				cell = sheet.getRow(0).getCell(1);
				cell.setAsActiveCell();
			}
			out = new ByteArrayOutputStream();
			book.write(out);
			out.flush();
			report = out.toByteArray();
		}
		catch(IOException ex) {
			throw new ErrorException(getClass(), ex);
		}
		catch(ClassNotFoundException ex) {
			throw new ErrorException(getClass(), ex);
		}
		catch(NoSuchMethodException ex) {
			throw new ErrorException(getClass(), ex);
		}
		catch(InvocationTargetException ex) {
			throw new ErrorException(getClass(), ex);
		}
		catch(IllegalAccessException ex) {
			throw new ErrorException(getClass(), ex);
		}
		catch(Exception ex) {
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
