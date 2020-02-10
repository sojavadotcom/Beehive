package com.sojava.beehive.framework.component.data.service.impl;

import com.sojava.beehive.framework.component.data.bean.NcpGoods;
import com.sojava.beehive.framework.component.data.dao.NcpDataDao;
import com.sojava.beehive.framework.component.data.service.StatisticsService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;
import com.sojava.beehive.framework.util.ValueUtil;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Resource private NcpDataDao ncpDataDao;

	private Map<String, String> deptType;
	private Date date;

	public StatisticsServiceImpl() {
		this.deptType = new HashMap<String, String>();
		deptType.put("卫材库", "后勤保障");
		deptType.put("发热门诊", "发热门诊");
		deptType.put("门诊部", "行政科室");
	}

	@Override
	public byte[] goodsReport(Date date, File template, String type) throws ErrorException {
		byte[] report = null;
		this.date = date;

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
				//处理页眉
				HSSFHeader header = sheet.getHeader();
				if (!StringUtils.isEmpty(header.getLeft())) {
					header.setLeft(expression(header.getLeft()));
				}
				if (!StringUtils.isEmpty(header.getCenter())) {
					header.setCenter(expression(header.getCenter()));
				}
				if (!StringUtils.isEmpty(header.getRight())) {
					header.setRight(expression(header.getRight()));
				}
				//处理页脚
				HSSFFooter footer = sheet.getFooter();
				if (!StringUtils.isEmpty(footer.getLeft())) {
					footer.setLeft(expression(footer.getLeft()));
				}
				if (!StringUtils.isEmpty(footer.getCenter())) {
					footer.setCenter(expression(footer.getCenter()));
				}
				if (!StringUtils.isEmpty(footer.getRight())) {
					footer.setRight(expression(footer.getRight()));
				}
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
					cell.setCellValue(expression(title));
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
					List<NcpGoods> list = ncpDataDao.goodsSumByDestType(c.getTime(), sheetName, null, type, "存量");
					if (list.size() > 0) {
						NcpGoods goods = list.get(0);
						String origStr[] = dataObj.getString("orig").split("\\Q:\\E");
						int[] origPos = {
								Integer.parseInt(origStr[0].split("\\Q,\\E")[1])-1, //start row
								Integer.parseInt(origStr[0].split("\\Q,\\E")[0])-1, //start cell
								Integer.parseInt(origStr[1].split("\\Q,\\E")[1])-1, //end row
								Integer.parseInt(origStr[1].split("\\Q,\\E")[0])-1 //end cell
							};
						int n = 0;
						for(int j = origPos[0]; j <= origPos[2]; j ++) {
							Method m = Class.forName(NcpGoods.class.getName()).getMethod("get" + items.get(n).substring(0, 1).toUpperCase() + items.get(n).substring(1), new Class[0]);
							sheet.getRow(j).getCell(origPos[1]).setCellValue(Double.parseDouble(m.invoke(goods, new Object[0]).toString()));
							n ++;
						}

						//消耗
						list = ncpDataDao.goodsSumByDestType(date, sheetName, null, type, "消耗");
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
							for (NcpGoods _goods : list) {
								if (_goods.getDeptDest().equals(deptDest)) {
									for(int k = expPos[0]; k <= expPos[2]; k ++) {
										Method m = Class.forName(NcpGoods.class.getName()).getMethod("get" + items.get(n).substring(0, 1).toUpperCase() + items.get(n).substring(1), new Class[0]);
										sheet.getRow(k).getCell(j).setCellValue(Double.parseDouble(m.invoke(_goods, new Object[0]).toString()));
										n ++;
									}
									break;
								}
							}
						}
					}

					//入库
					list = ncpDataDao.goodsSumByDestType(date, dataObj.getString("impDept"), sheetName, type, "消耗");
					if (list.size() > 0) {
						NcpGoods goods = list.get(0);
						String impStr[] = dataObj.getString("imp").split("\\Q:\\E");
						int[] impPos = {
								Integer.parseInt(impStr[0].split("\\Q,\\E")[1])-1, //start row
								Integer.parseInt(impStr[0].split("\\Q,\\E")[0])-1, //start cell
								Integer.parseInt(impStr[1].split("\\Q,\\E")[1])-1, //end row
								Integer.parseInt(impStr[1].split("\\Q,\\E")[0])-1 //end cell
							};
						int n = 0;
						for(int j = impPos[0]; j <= impPos[2]; j ++) {
							Method m = Class.forName(NcpGoods.class.getName()).getMethod("get" + items.get(n).substring(0, 1).toUpperCase() + items.get(n).substring(1), new Class[0]);
							sheet.getRow(j).getCell(impPos[1]).setCellValue(Double.parseDouble(m.invoke(goods, new Object[0]).toString()));
							n ++;
						}
						/*
						 * 备注
						 * {column_name:"memo text..."}
						 */
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
						NcpGoods goods = null;
						Date totalDatetime = FormatUtil.parseDateTime(FormatUtil.formatDate(date, "yyyy-MM-dd") + " " + "14:00:00");
						List<?> goodsList = ncpDataDao.query(NcpGoods.class,
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
						if (goodsList.size() > 0) goods = (NcpGoods) goodsList.get(0);
						else {
							goods = new NcpGoods();
							goods.setDeptSrc(sheetName);
							goods.setDeptDest(sheetName);
							goods.setDeptDestType(this.deptType.get(sheetName));
							goods.setTime(totalDatetime);
							goods.setKind("存量");
							goods.setType(type);
						}

						for(int j = totalPos[0]; j <= totalPos[2]; j ++) {
							Method m = Class.forName(NcpGoods.class.getName()).getMethod("set" + items.get(n).substring(0, 1).toUpperCase() + items.get(n).substring(1), new Class[] {Double.class});
							HSSFCell _cell = sheet.getRow(j).getCell(totalPos[1]);
							if (_cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) fe.evaluateFormulaCell(_cell);
							m.invoke(goods, new Object[] {_cell.getNumericCellValue()});
							n ++;
						}
						ncpDataDao.save(goods);
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

	@SuppressWarnings("unchecked")
	@Override
	public byte[] goodsReportByOutside(Date date, File template, String type) throws ErrorException {
		byte[] report = null;
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		this.date = date;
		try {
			in = new FileInputStream(template);
			HSSFWorkbook book = new HSSFWorkbook(in);
			FormulaEvaluator fe = book.getCreationHelper().createFormulaEvaluator();
			//配置
			HSSFSheet sheet = book.getSheet("define");
			JSONObject def = JSONObject.fromObject(HSSFCellUtil.getCell(sheet.getRow(0), 0).getStringCellValue());
			book.removeSheetAt(book.getSheetIndex(sheet));
			sheet = book.getSheetAt(def.getInt("sheetIndex"));
			//time
			String timeStr = def.getString("time");
			//允许基数持久化
			boolean dataBaseSave = def.containsKey("dataBaseSave") && def.getBoolean("dataBaseSave");
			/*
			 * 	处理标题
			 */
			if (def.containsKey("title")) {
				String titleStr = def.getString("title");
				int[] titlePos = new int[] {Integer.parseInt(titleStr.split("\\Q,\\E")[1])-1, Integer.parseInt(titleStr.split("\\Q,\\E")[0])-1};

				String title = HSSFCellUtil.getCell(sheet.getRow(titlePos[0]), titlePos[1]).getStringCellValue();
				HSSFCellUtil.getCell(sheet.getRow(titlePos[0]), titlePos[1]).setCellValue(expression(title));
			}
			//deptDest
			String deptDest = def.getString("expDept");
			//"当日消耗"标题字样
			String goodsExpTitle = def.getString("goodsExp");
			//kind位置
			int kindPos = def.getInt("destDept")-1;
			//项目
			String itemsStr[] = def.getString("items").split("\\Q:\\E");
			int[] itemsPos = {
				Integer.parseInt(itemsStr[0].split("\\Q,\\E")[1])-1, //start row
				Integer.parseInt(itemsStr[0].split("\\Q,\\E")[0])-1, //start cell
				Integer.parseInt(itemsStr[1].split("\\Q,\\E")[1])-1, //end row
				Integer.parseInt(itemsStr[1].split("\\Q,\\E")[0])-1 //end cell
			};
			//写数据区域
			String dataStr[] = def.getString("data").split("\\Q:\\E");
			int[] dataPos = {
				Integer.parseInt(dataStr[0].split("\\Q,\\E")[1])-1, //start row
				Integer.parseInt(dataStr[0].split("\\Q,\\E")[0])-1, //start cell
				Integer.parseInt(dataStr[1].split("\\Q,\\E")[1])-1, //end row
				Integer.parseInt(dataStr[1].split("\\Q,\\E")[0])-1 //end cell
			};
			//前日基数
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, -1);
			Date startTime = FormatUtil.parseDateTime(FormatUtil.formatDate(c.getTime(), "yyyy-MM-dd") + " 00:00:00");
			Date endTime = FormatUtil.parseDateTime(FormatUtil.formatDate(c.getTime(), "yyyy-MM-dd") + " 23:59:59");
			List<NcpGoods> goodsBaseList = (List<NcpGoods>) ncpDataDao.query(NcpGoods.class, new Criterion[] {
					Restrictions.between("time", startTime, endTime),
					Restrictions.eq("deptDest", deptDest),
					Restrictions.eq("type", type)
				}, null, null, false);
			//当日消耗
			startTime = FormatUtil.parseDateTime(FormatUtil.formatDate(date, "yyyy-MM-dd") + " 00:00:00");
			endTime = FormatUtil.parseDateTime(FormatUtil.formatDate(date, "yyyy-MM-dd") + " 23:59:59");
			List<NcpGoods> goodsExpList = ncpDataDao.goodsSumByDestType(startTime, null, null, "实数", "消耗");
			NcpGoods goodsExp = new NcpGoods();
			for (NcpGoods gExp : goodsExpList) {
				goodsExp.setPtkz(ValueUtil.get(goodsExp.getPtkz(), 0d) + ValueUtil.get(gExp.getPtkz(), 0d));
				goodsExp.setWkkz(ValueUtil.get(goodsExp.getWkkz(), 0d) + ValueUtil.get(gExp.getWkkz(), 0d));
				goodsExp.setN95(ValueUtil.get(goodsExp.getN95(), 0d) + ValueUtil.get(gExp.getN95(), 0d));
				goodsExp.setKlfhkz(ValueUtil.get(goodsExp.getKlfhkz(), 0d) + ValueUtil.get(gExp.getKlfhkz(), 0d));
				goodsExp.setFhf(ValueUtil.get(goodsExp.getFhf(), 0d) + ValueUtil.get(gExp.getFhf(), 0d));
				goodsExp.setGly(ValueUtil.get(goodsExp.getGly(), 0d) + ValueUtil.get(gExp.getGly(), 0d));
				goodsExp.setRjst(ValueUtil.get(goodsExp.getRjst(), 0d) + ValueUtil.get(gExp.getRjst(), 0d));
				goodsExp.setMz(ValueUtil.get(goodsExp.getMz(), 0d) + ValueUtil.get(gExp.getMz(), 0d));
				goodsExp.setHmj(ValueUtil.get(goodsExp.getHmj(), 0d) + ValueUtil.get(gExp.getHmj(), 0d));
				goodsExp.setXdy84(ValueUtil.get(goodsExp.getXdy84(), 0d) + ValueUtil.get(gExp.getXdy84(), 0d));
				goodsExp.setJj75(ValueUtil.get(goodsExp.getJj75(), 0d) + ValueUtil.get(gExp.getJj75(), 0d));
				goodsExp.setSxdj(ValueUtil.get(goodsExp.getSxdj(), 0d) + ValueUtil.get(gExp.getSxdj(), 0d));
				goodsExp.setTwj(ValueUtil.get(goodsExp.getTwj(), 0d) + ValueUtil.get(gExp.getTwj(), 0d));
				goodsExp.setJj7545(ValueUtil.get(goodsExp.getJj7545(), 0d) + ValueUtil.get(gExp.getJj7545(), 0d));
			}
			//当日基数
			List<NcpGoods> goodsBaseTodayList = (List<NcpGoods>) ncpDataDao.query(NcpGoods.class, new Criterion[] {
					Restrictions.between("time", startTime, endTime),
					Restrictions.eq("deptDest", deptDest),
					Restrictions.eq("type", type)
				}, null, null, false);

			List<String> items = new ArrayList<String>();
			int n = 0;
			for (int i = itemsPos[0]; i <= itemsPos[2]; i ++) {
				HSSFRow row = sheet.getRow(i);
				HSSFCell cell = HSSFCellUtil.getCell(row, itemsPos[1]);
				String str = cell.getStringCellValue().trim();
				items.add(str);
				row.removeCell(cell);
			}
			/*
			 * 	填充数据
			 */
			for (int j = dataPos[1]; j <= dataPos[3]; j ++) {
				n = 0;
				String kind = HSSFCellUtil.getCell(sheet.getRow(kindPos), j).getStringCellValue();
				NcpGoods goods = null;
				if (kind.equals(goodsExpTitle)) {
					goods = goodsExp;
				} else {
					for (NcpGoods goodsBase : goodsBaseList) {
						if (kind.equals(goodsBase.getKind())) {
							goods = goodsBase;
							break;
						}
					}
				}
				/*
				 * 	今日基数对象，准备表填完后持久化到数据库
				 */
				NcpGoods goodsBaseToday = null;
				for (NcpGoods _goodsBaseToday : goodsBaseTodayList) {
					if (kind.equals(_goodsBaseToday.getKind())) {
						goodsBaseToday = _goodsBaseToday;
						break;
					}
				}
				if (goodsBaseToday == null && !kind.equals(goodsExpTitle)) {
					goodsBaseToday = new NcpGoods();
					goodsBaseToday.setTime(FormatUtil.parseDateTime(FormatUtil.formatDate(date, "yyyy-MM-dd") + " " + timeStr));
					goodsBaseToday.setDeptSrc("鸡西市中医医院");
					goodsBaseToday.setDeptDest(deptDest);
					goodsBaseToday.setDeptDestType(deptDest);
					goodsBaseToday.setKind(kind);
					goodsBaseToday.setType(type);
					goodsBaseTodayList.add(goodsBaseToday);
				}
				/*
				 * 	填充数据
				 */
				for(int k = dataPos[0]; k <= dataPos[2]; k ++) {
					String colName = items.get(n).trim();
					if (!StringUtils.isEmpty(colName)) {
						Method getMethod = Class.forName(NcpGoods.class.getName()).getMethod("get" + colName.substring(0, 1).toUpperCase() + colName.substring(1), new Class[0]);
						Method setMethod = Class.forName(NcpGoods.class.getName()).getMethod("set" + colName.substring(0, 1).toUpperCase() + colName.substring(1), new Class[] {Double.class});
						HSSFCell cell = HSSFCellUtil.getCell(sheet.getRow(k), j);

						/*
						 * 	单元格数据
						 * 	如果kind为基数时，val为基数数据
						 * 	如果kind为消耗时，val为消耗数据
						 */
						double val = ValueUtil.get(getMethod.invoke(goods, new Object[0]), 0d);

						if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
							String value = cell.getCellFormula().replaceFirst("\\Qvalue\\E", val+"");
							cell.setCellFormula(value);
							fe.evaluate(cell);
							if (goodsBaseToday != null) setMethod.invoke(goodsBaseToday, val);
						} else {
							cell.setCellValue(val);
							if (goodsBaseToday != null) setMethod.invoke(goodsBaseToday, val);
						}
					}
					n ++;
				}
			}
			sheet.setActive(true);
			fe.evaluateAll();
			if (dataBaseSave) {
				ncpDataDao.save(goodsBaseTodayList.toArray(new NcpGoods[0]));
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

	public String expression(String text) {
		String rest = text;
		String regex = "\\[.*\\]";
		Pattern p = Pattern.compile(regex);
		String textArray[] = text.split("\\]");
		for(String t : textArray) {
			Matcher m = p.matcher(t + "]");
			if (m.find()) {
				String str = m.group();
				str = str.substring(1, str.length() - 1);
				if (str.indexOf("date:") != -1) {
					String formatStr = str.replaceFirst("^.*:", "");
					String dateStr = FormatUtil.formatDate(this.date == null ? new Date() : this.date, formatStr);
					rest = rest.replaceAll("\\Q[" + str + "]\\E", dateStr);
				}
			}
		}

		return rest;
	}

	public NcpDataDao getNcpDataDao() {
		return ncpDataDao;
	}

	public void setNcpDataDao(NcpDataDao ncpDataDao) {
		this.ncpDataDao = ncpDataDao;
	}
}
