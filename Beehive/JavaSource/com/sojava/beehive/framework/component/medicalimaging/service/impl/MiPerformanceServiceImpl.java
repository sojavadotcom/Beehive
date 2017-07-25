package com.sojava.beehive.framework.component.medicalimaging.service.impl;

import com.sojava.beehive.framework.component.medicalimaging.StaffCoefType;
import com.sojava.beehive.framework.component.medicalimaging.StaffPerformanceReportType;
import com.sojava.beehive.framework.component.medicalimaging.bean.DicCoefficient;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiWorkload;
import com.sojava.beehive.framework.component.medicalimaging.bean.Staff;
import com.sojava.beehive.framework.component.medicalimaging.bean.StaffPerformanceReport;
import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.dao.MiExecutedDao;
import com.sojava.beehive.framework.component.medicalimaging.dao.MiPerformanceDao;
import com.sojava.beehive.framework.component.medicalimaging.service.MiPerformanceService;
import com.sojava.beehive.framework.math.Arith;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class MiPerformanceServiceImpl implements MiPerformanceService {

	@Resource private MiPerformanceDao miPerformanceDao;
	@Resource private MiExecutedDao miExecutedDao;

	@Override
	@SuppressWarnings("unchecked")
	public void merit(Double budget, Double nurseRate, Double medicalRate, Double manageRate, int year, int month, Date begin, Date end, String dept, HSSFWorkbook overtimeBook, HSSFWorkbook nurseWorkloadBook) throws Exception {
		WorkStatistic workStatistic;
		double overtimeCost = 0d, nurseHours = 0d;
		MiWorkload[] overtimeList = null;
		MiWorkload[] nurseWorkloadList = null;
		if (overtimeBook != null) {
			HSSFSheet sheet = overtimeBook.getSheetAt(0);
			Properties ret = calOvertime(sheet);
			overtimeCost = (double) ret.get("cost");
			overtimeList = ((List<MiWorkload>) ret.get("list")).toArray(new MiWorkload[0]);
		}
		if (nurseWorkloadBook != null) {
			HSSFSheet sheet = nurseWorkloadBook.getSheetAt(0);
			Properties ret = calNurseWorkload(sheet);
			nurseHours = (double) ret.get("hours");
			nurseWorkloadList = ((List<MiWorkload>) ret.get("list")).toArray(new MiWorkload[0]);
		}
		List<WorkStatistic> list = (List<WorkStatistic>) miPerformanceDao.query(WorkStatistic.class, new Criterion[]{Restrictions.eq("year", (Integer) year), Restrictions.eq("month", (Integer) month), Restrictions.eq("dept", dept)}, null, null, false);
		if (list.size() > 0) workStatistic = list.get(0);
		else workStatistic = new WorkStatistic();
		workStatistic.setBudget(budget);
		workStatistic.setOvertimeCost(overtimeCost);
		workStatistic.setNurseRate(nurseRate);
		workStatistic.setNurseCost(Arith.mul(
										Arith.div(workStatistic.getNurseRate(), 100),
										(workStatistic.getBudget()-workStatistic.getOvertimeCost())
									));
		workStatistic.setNurseHours(nurseHours);
		workStatistic.setPerformanceTotal(workStatistic.getBudget() - workStatistic.getOvertimeCost() - workStatistic.getNurseCost());
		workStatistic.setMedicalRate(medicalRate);
		workStatistic.setMedicalTotal(Arith.mul(
										Arith.div(workStatistic.getMedicalRate(), 100),
										workStatistic.getPerformanceTotal()
									));
		workStatistic.setManageRate(manageRate);
		workStatistic.setManageTotal(Arith.mul(
										Arith.div(workStatistic.getManageRate(), 100),
										workStatistic.getPerformanceTotal()
									));
		workStatistic.setYear(year);
		workStatistic.setMonth(month);
		workStatistic.setBeginDate(begin);
		workStatistic.setEndDate(end);
		workStatistic.setDept(dept);
		miPerformanceDao.calRbrvsPrice(workStatistic, overtimeList, nurseWorkloadList);
	}

	@Override
	public Properties calOvertime(HSSFSheet sheet) throws Exception {
		Properties result = new Properties();
		List<MiWorkload> miWorkloads = new ArrayList<MiWorkload>();
		double overtimeCost = 0d;
		for (int i = 0; i <= sheet.getLastRowNum(); i ++) {
			HSSFCell cell;
			HSSFRow row = sheet.getRow(i);
			String staffName = row.getCell(0).getStringCellValue();
			Integer staffId = miExecutedDao.getStaffId(staffName);
			if (staffId == null) continue;
			double moring = 0d;
			cell = row.getCell(1);
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				moring = cell.getNumericCellValue();
			}
			double night = 0d;
			cell = row.getCell(2);
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				night = cell.getNumericCellValue();
			}

			MiWorkload miWorkload = new MiWorkload();
			miWorkload.setStaff(new Staff(staffId));
			miWorkload.setItem1(moring);
			miWorkload.setItem2(night);
			miWorkload.setAmount(moring + night);
			miWorkload.setType("误餐费");
			miWorkload.setKind("补助");
			miWorkloads.add(miWorkload);

			overtimeCost += miWorkload.getAmount();
		}
		result.put("list", miWorkloads);
		result.put("cost", overtimeCost);

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Properties calNurseWorkload(HSSFSheet sheet) throws Exception {
		Properties result = new Properties();
		List<MiWorkload> miWorkloads = new ArrayList<MiWorkload>();
		//班次系数
		double masterCoef = 0d, subCoef = 0d, recepCoef = 0d;
		List<DicCoefficient> nurseCoefs = (List<DicCoefficient>) miExecutedDao.query(DicCoefficient.class, new Criterion[]{Restrictions.in("name", new String[]{"主班", "副班", "登记"})}, null, null, true);
		for(DicCoefficient nurseCoef: nurseCoefs) {
			if (nurseCoef.getName().equals("主班")) {
				masterCoef = nurseCoef.getPoints();
			} else if (nurseCoef.getName().equals("副班")) {
				subCoef = nurseCoef.getPoints();
			} else if (nurseCoef.getName().equals("登记")) {
				recepCoef = nurseCoef.getPoints();
			}
		}
		//
		double hourAmount = 0d;
		for (int i = 3; i <= sheet.getLastRowNum(); i ++) {
			HSSFRow row = sheet.getRow(i);
			//姓名
			String staffName = row.getCell(0).getStringCellValue();
			Integer staffId = miExecutedDao.getStaffId(staffName);

			if (staffId == null) continue;

			//主班时数
			HSSFCell masterCell = row.getCell(2);
			double masterHours = 0d;
			if (masterCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				masterHours = masterCell.getNumericCellValue();
			}
			//副班时数
			HSSFCell subCell = row.getCell(3);
			double subHours = 0d;
			if (subCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				subHours = subCell.getNumericCellValue();
			}
			//登记班时数
			HSSFCell recepCell = row.getCell(4);
			double recepHours = 0d;
			if (recepCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				recepHours = masterCell.getNumericCellValue();
			}
			//职称系数
			double titleCoef = miExecutedDao.getStaffCoef(staffId, StaffCoefType.Nurse);
			double hourAmout = Arith.mul(masterHours, masterCoef) + Arith.mul(subHours, subCoef) + Arith.mul(recepHours, recepCoef);
			double hourTotal = Arith.mul(hourAmout, titleCoef);

			MiWorkload miWorkload = new MiWorkload();
			miWorkload.setStaff(new Staff(staffId));
			miWorkload.setItem1(masterCoef);
			miWorkload.setItem2(subCoef);
			miWorkload.setItem3(recepCoef);
			miWorkload.setAmount(masterCoef + subCoef + recepCoef);
			miWorkload.setAmountByCoef(hourTotal);
			miWorkload.setType("时数");
			miWorkload.setKind("护理组");
			miWorkloads.add(miWorkload);

			hourAmount += miWorkload.getAmountByCoef();
		}
		result.put("list", miWorkloads);
		result.put("hours", hourAmount);

		return result;
	}

	@Override
	public HSSFWorkbook writeExcelOfStaffPerformance(File overtimeFile, File nurseFile, WorkStatistic workStatistic, VMiExecutedStaffPerformance[] list) throws Exception {
/*
		HSSFWorkbook book = generateExcelOfStaffPerformance(workStatistic, list);
		HSSFWorkbook overtimeBook = null, nurseBook = null;
		double nurseMasterCoef = 0d, nurseSubCoef = 0d, nurseRecepCoef = 0d;
		List<DicCoefficient> nurseCoefs = (List<DicCoefficient>) miPerformanceDao.query(DicCoefficient.class, new Criterion[]{Restrictions.in("name", new String[]{"主班", "副班", "登记"})}, null, null, true);
		for(DicCoefficient nurseCoef: nurseCoefs) {
			if (nurseCoef.getName().equals("主班")) {
				nurseMasterCoef = nurseCoef.getPoints();
			} else if (nurseCoef.getName().equals("副班")) {
				nurseSubCoef = nurseCoef.getPoints();
			} else if (nurseCoef.getName().equals("登记班")) {
				nurseRecepCoef = nurseCoef.getPoints();
			}
		}
		FileInputStream in = null;
		if (overtimeFile != null) {
			try {
				in = new FileInputStream(overtimeFile);
				overtimeBook = new HSSFWorkbook(in);
			}
			finally {
				in.close();
			}
		}
		if (nurseFile != null) {
			try {
				in = new FileInputStream(nurseFile);
				nurseBook = new HSSFWorkbook(in);
			}
			finally {
				if (in != null) in.close();
			}
		}
		HSSFSheet nurseSheet = nurseBook == null ? null : nurseBook.getSheetAt(0);
		Map<String, Double> nurseWorkload = calNurseJob(nurseSheet, nurseMasterCoef, nurseSubCoef, nurseRecepCoef);
		double nurseHoursTotal = nurseWorkload.get("总时数");
		double nursePrice = Arith.div(workStatistic.getNurseCost(), nurseHoursTotal);
		try {
			HSSFSheet sheet = book.getSheetAt(0);
			HSSFSheet overtimeSheet = overtimeBook == null ? null : overtimeBook.getSheetAt(0);

			for (int r = 4; r <= sheet.getLastRowNum(); r ++) {
				HSSFRow row = sheet.getRow(r);
				HSSFCell cell = row.getCell(0);
				String text = cell.getStringCellValue();
				if (text.equalsIgnoreCase("")
						|| text.replaceAll("\\Q　\\E", "").replaceAll("\\Q \\E", "").equalsIgnoreCase("合计")
						|| text.replaceAll("\\Q　\\E", "").replaceAll("\\Q \\E", "").equalsIgnoreCase("总计")
						|| isMerged(cell, sheet)
					) continue;
				HSSFCell overtimeCell = row.getCell(4);
				HSSFCell nurseCell = row.getCell(3);

				Object[] ret = getCellsValue(overtimeSheet, text, 3);
				if (ret.length > 0) {
					if (ret[0] instanceof Double) {
						overtimeCell.setCellValue(Double.parseDouble(ret[0].toString()));
					} else {
						overtimeCell.setCellValue(ret[0].toString());
					}
				}
				nurseCell.setCellValue(Arith.mul(nursePrice, nurseWorkload.get(text).doubleValue()));
			}
			autoSizeColumn(sheet);
		}
		finally {
			in.close();
		}

		return book;
*/
		return null;
	}

	public Map<String, Double> calNurseJob(HSSFSheet sheet, double masterCoef, double subCoef, double recepCoef) throws Exception {
		Map<String, Double> nurse = new HashMap<String, Double>();
		double amountTotal = 0d;
		for (int i = 0; i <= sheet.getLastRowNum(); i ++) {
			HSSFRow row = sheet.getRow(i);
			//姓名
			String staffName = row.getCell(0).getStringCellValue();
			Integer staffId = miExecutedDao.getStaffId(staffName);
			if (staffId == null) continue;
			//主班时数
			HSSFCell masterCell = row.getCell(2);
			double masterHours = 0d;
			if (!masterCell.getStringCellValue().trim().equals("")) {
				masterHours = masterCell.getNumericCellValue();
			}
			//副班时数
			HSSFCell subCell = row.getCell(3);
			double subHours = 0d;
			if (!subCell.getStringCellValue().trim().equals("")) {
				subHours = subCell.getNumericCellValue();
			}
			//登记班时数
			HSSFCell recepCell = row.getCell(4);
			double recepHours = 0d;
			if (!recepCell.getStringCellValue().trim().equals("")) {
				recepHours = masterCell.getNumericCellValue();
			}
			//职称系数
			double titleCoef = miExecutedDao.getStaffCoef(staffId, StaffCoefType.Nurse);
			double hourAmout = Arith.mul(masterHours, masterCoef) + Arith.mul(subHours, subCoef) + Arith.mul(recepHours, recepCoef);
			double hourTotal = Arith.mul(hourAmout, titleCoef);
			nurse.put(staffName, hourTotal);
			amountTotal += hourTotal;
		}
		nurse.put("总时数", amountTotal);

		return nurse;
	}

	public Object[] getCellsValue(HSSFSheet sheet, String key, int... columnIndexs) throws Exception {
		List<Object> result = new ArrayList<Object>();
		if (sheet != null) {
			for (int r = 0; r <= sheet.getLastRowNum(); r ++) {
				HSSFRow row = sheet.getRow(r);
				HSSFCell firstCell = row.getCell(0);
				String text = firstCell.getStringCellValue();
				if (text.equalsIgnoreCase("")
						|| text.replaceAll("\\Q　\\E", "").replaceAll("\\Q \\E", "").equalsIgnoreCase("合计")
						|| text.replaceAll("\\Q　\\E", "").replaceAll("\\Q \\E", "").equalsIgnoreCase("总计")
						|| isMerged(firstCell, sheet)
						|| !key.equalsIgnoreCase(text)
					) continue;
	
				HSSFCell cell = null;
				for (int columnIndex : columnIndexs) {
					cell = row.getCell(columnIndex);
					Object ret = null;
					switch(cell.getCellType()) {
						case HSSFCell.CELL_TYPE_BLANK:
							ret = 0d;
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							ret = cell.getCellFormula();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							ret = cell.getNumericCellValue();
							break;
						default:
							ret = cell.getStringCellValue();
							break;
					}
					result.add(ret);
				}
			}
		}

		return result.toArray(new Object[0]);
	}

	public boolean isMerged(HSSFCell cell, HSSFSheet sheet) throws Exception {
		boolean ret = false;
		for (int i = 0; i < sheet.getNumMergedRegions(); i ++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			ret = range.isInRange(cell.getRowIndex(), cell.getColumnIndex());
		}

		return ret;
	}

	public void autoSizeColumn(HSSFSheet sheet) throws Exception {
		HSSFRow row = sheet.getRow(0);
		for (int i = 0; i <= row.getLastCellNum(); i ++) {
			sheet.autoSizeColumn(i);
		}
	}

	@Override
	public HSSFWorkbook generateExcelOfStaffPerformance(WorkStatistic workStatistic, VMiExecutedStaffPerformance[] staffPerformances, MiWorkload[] overtimes, MiWorkload[] nurseWorkloads) throws Exception {
		int rowIndex = 0;
		int dataRowIndex = 0;
		List<Integer> amountRowIndex = new ArrayList<Integer>();
		HSSFWorkbook book = new HSSFWorkbook();
		String title = workStatistic.getDept() + workStatistic.getYear() + "年" + workStatistic.getMonth() + "月";
		String typeFormat = "_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)";
		HSSFSheet sheet = book.createSheet(title);
		/*
		 * Header of Row
		 */
		HSSFRow headerRow = sheet.createRow(rowIndex ++);
		headerRow.setHeightInPoints((short) 25);
		HSSFCell cell = HSSFCellUtil.createCell(headerRow, 0, title + "绩效核算表");

		HSSFFont headerFont = book.createFont();
		headerFont.setFontName("黑体");
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 14);

		HSSFCellStyle headerStyle = book.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFont(headerFont);
		cell.setCellStyle(headerStyle);

		sheet.addMergedRegion(CellRangeAddress.valueOf("A1:J1"));

		HSSFFont titleFont = book.createFont();
		titleFont.setFontName("宋体");
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontHeightInPoints((short) 12);

		HSSFCellStyle titleStyle = book.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setBorderTop((short) 1);
		titleStyle.setBorderRight((short) 1);
		titleStyle.setBorderBottom((short) 1);
		titleStyle.setBorderLeft((short) 1);

		/*
		 * Title of Row
		 */
		HSSFRow titleRow = sheet.createRow(rowIndex ++);
		titleRow.setHeightInPoints((short) 15);
		HSSFCellUtil.createCell(titleRow, 0, "姓名", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("A2:A3"));
		HSSFCellUtil.createCell(titleRow, 1, "医疗绩效", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:B3"));
		HSSFCellUtil.createCell(titleRow, 2, "经营管理绩效", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("C2:C3"));
		HSSFCellUtil.createCell(titleRow, 3, "护理绩效", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("D2:D3"));
		HSSFCellUtil.createCell(titleRow, 4, "误餐费", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("E2:E3"));
		HSSFCellUtil.createCell(titleRow, 5, "奖金合计", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("F2:F3"));
		HSSFCellUtil.createCell(titleRow, 6, "工作量(点值：" + workStatistic.getPointValue() + ")", titleStyle);
		HSSFCellUtil.createCell(titleRow, 7, "", titleStyle);
		HSSFCellUtil.createCell(titleRow, 8, "", titleStyle);
		HSSFCellUtil.createCell(titleRow, 9, "", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("G2:J2"));

		HSSFRow subTitleRow = sheet.createRow(rowIndex ++);
		subTitleRow.setHeightInPoints((short) 15);
		HSSFCellUtil.createCell(subTitleRow, 0, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 1, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 2, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 3, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 4, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 5, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 6, "投照点数", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 7, "诊断点数", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 8, "辅助点数", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 9, "点数合计", titleStyle);

		/*
		 * Content
		 */
		HSSFFont groupFont = book.createFont();
		groupFont.setFontName("宋体");
		groupFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		groupFont.setFontHeightInPoints((short) 12);

		HSSFCellStyle groupStyle = book.createCellStyle();
		groupStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		groupStyle.setFont(groupFont);
		groupStyle.setBorderTop((short) 1);
		groupStyle.setBorderRight((short) 1);
		groupStyle.setBorderBottom((short) 1);
		groupStyle.setBorderLeft((short) 1);

		HSSFFont amountFont = book.createFont();
		amountFont.setFontName("宋体");
		amountFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		amountFont.setFontHeightInPoints((short) 12);

		HSSFCellStyle amountStyle = book.createCellStyle();
		amountStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		amountStyle.setFont(amountFont);
		amountStyle.setBorderTop((short) 1);
		amountStyle.setBorderRight((short) 1);
		amountStyle.setBorderBottom((short) 1);
		amountStyle.setBorderLeft((short) 1);
		amountStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(typeFormat));

		HSSFFont contentFont = book.createFont();
		contentFont.setFontName("宋体");
		contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		contentFont.setFontHeightInPoints((short) 12);

		HSSFCellStyle contentStyle = book.createCellStyle();
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop((short) 1);
		contentStyle.setBorderRight((short) 1);
		contentStyle.setBorderBottom((short) 1);
		contentStyle.setBorderLeft((short) 1);
		contentStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(typeFormat));

		String groupName = "";
		for (VMiExecutedStaffPerformance staffPerformance: staffPerformances) {
			String _groupName = staffPerformance.getGroupName();
			String staffName = staffPerformance.getStaffName();

			if (staffName.equalsIgnoreCase("合计") || staffName.equalsIgnoreCase("总计")) {
				createAmountRow(sheet, rowIndex ++, dataRowIndex, amountRowIndex.toArray(new Integer[0]), staffPerformance, amountStyle);
				if (staffName.equals("合计")) amountRowIndex.add(rowIndex);
			} else {
				if (!groupName.equals(_groupName)) {
					createGroupRow(sheet, rowIndex ++, staffPerformance, groupStyle);
					groupName = _groupName;
					dataRowIndex = rowIndex+1;
				}
				createContentRow(
						sheet,
						rowIndex ++,
						staffPerformance,
						getWorkload(overtimes, staffPerformance.getStaffId()),
						getWorkload(nurseWorkloads, staffPerformance.getStaffId()),
						contentStyle);
			}
		}
		cell.setAsActiveCell();

		return book;
	}

	public HSSFCell createCell(HSSFRow row, int cellIndex, double value, HSSFCellStyle style) {
		HSSFCell cell = row.createCell(cellIndex);
		cell.setCellValue(value);
		cell.setCellStyle(style);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

		return cell;
	}

	public void createGroupRow(HSSFSheet sheet, int rowIndex, VMiExecutedStaffPerformance rec, HSSFCellStyle style) {
		HSSFRow row = sheet.createRow(rowIndex);
		HSSFCellUtil.createCell(row, 0, rec.getGroupName(), style);
		for (int i = 1; i < 10; i ++) HSSFCellUtil.createCell(row, i, "", style);
		sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (rowIndex+1) + ":J" + (rowIndex+1)));

		row.setHeightInPoints(15);
	}

	//合计、总计
	public void createAmountRow(HSSFSheet sheet, int rowIndex, int dataRowIndex, Integer[] amountRowIndex, VMiExecutedStaffPerformance rec, HSSFCellStyle style) {
		int cellIndex = 0;
		boolean isTotal = rec.getStaffName().equals("合计");
		String formulaStr = "";
		String label;

		if (isTotal) {
			label = "合　计";
		} else {
			label = "总　计";
			for (int a: amountRowIndex) formulaStr += "*" + a + "+";
			formulaStr = formulaStr.length() > 0 ? formulaStr.substring(0, formulaStr.length() - 1) : "";
		}
		HSSFRow row = sheet.createRow(rowIndex);
		HSSFCellUtil.createCell(row, cellIndex ++, label, style);
		HSSFCell cell = createCell(row, cellIndex ++, rec.getMedicalTotal(), style);
		cell.setCellFormula(isTotal ? "SUM(B" + dataRowIndex + ":B" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "B"));

		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula(isTotal ? "SUM(C" + dataRowIndex + ":C" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "C"));
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula(isTotal ? "SUM(D" + dataRowIndex + ":D" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "D"));
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula(isTotal ? "SUM(E" + dataRowIndex + ":E" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "E"));

		cell = createCell(row, cellIndex ++, rec.getTotalAmount(), style);
		cell.setCellFormula(isTotal ? "SUM(F" + dataRowIndex + ":F" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "F"));
		cell = createCell(row, cellIndex ++, rec.getTechPointTotal(), style);
		cell.setCellFormula(isTotal ? "SUM(G" + dataRowIndex + ":G" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "G"));
		cell = createCell(row, cellIndex ++, rec.getDiagnoPointTotal(), style);
		cell.setCellFormula(isTotal ? "SUM(H" + dataRowIndex + ":H" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "H"));
		cell = createCell(row, cellIndex ++, rec.getAssistPointTotal(), style);
		cell.setCellFormula(isTotal ? "SUM(I" + dataRowIndex + ":I" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "I"));
		cell = createCell(row, cellIndex ++, rec.getPointTotal(), style);
		cell.setCellFormula(isTotal ? "SUM(J" + dataRowIndex + ":J" + rowIndex + ")" : formulaStr.replaceAll("\\Q*\\E", "J"));

		row.setHeightInPoints(15);
	}

	public void createContentRow(HSSFSheet sheet, int rowIndex, VMiExecutedStaffPerformance staffPerformance, MiWorkload overtime, MiWorkload nurseWorkload, HSSFCellStyle style) {
		int cellIndex = 0;
		HSSFRow row = sheet.createRow(rowIndex);
		HSSFCellUtil.createCell(row, cellIndex ++, staffPerformance.getStaffName(), style);
		createCell(row, cellIndex ++, staffPerformance.getMedicalTotal(), style);
		createCell(row, cellIndex ++, staffPerformance.getManageTotal(), style);
		createCell(row, cellIndex ++, (nurseWorkload == null ? 0d : nurseWorkload.getAmount()), style);
		createCell(row, cellIndex ++, (overtime == null ? 0d : overtime.getAmount()), style);
		HSSFCell cell = createCell(row, cellIndex ++, staffPerformance.getTotalAmount(), style);
		cell.setCellFormula("sum(B" + (rowIndex+1) + ":E" + (rowIndex+1) + ")");
		createCell(row, cellIndex ++, staffPerformance.getTechPointTotal(), style);
		createCell(row, cellIndex ++, staffPerformance.getDiagnoPointTotal(), style);
		createCell(row, cellIndex ++, staffPerformance.getAssistPointTotal(), style);
		cell = createCell(row, cellIndex ++, staffPerformance.getPointTotal(), style);
		cell.setCellFormula("sum(G" + (rowIndex+1) + ":I" + (rowIndex+1) + ")");

		row.setHeightInPoints(15);
	}

	public MiWorkload getWorkload(MiWorkload[] workloads, int staffId) throws Exception {
		MiWorkload ret = null;
		for (MiWorkload workload: workloads) {
			if (workload.getStaff().getId() == staffId) {
				ret = workload;
				break;
			}
		}
		return ret;
	}


	@Override
	public HSSFWorkbook generateExcelOfStaffPerformanceReport(WorkStatistic workStatistic) throws Exception {
		int rowIndex = 0;
		HSSFWorkbook book = new HSSFWorkbook();
		String title = workStatistic.getDept() + workStatistic.getYear() + "年" + workStatistic.getMonth() + "月";
		String typeFormat = "_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)";
		HSSFSheet sheet = book.createSheet(title);
		/*
		 * Header of Row
		 */
		HSSFRow headerRow = sheet.createRow(rowIndex ++);
		headerRow.setHeightInPoints((short) 25);
		HSSFCell cell = HSSFCellUtil.createCell(headerRow, 0, title + "绩效核算表");

		HSSFFont headerFont = book.createFont();
		headerFont.setFontName("黑体");
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 14);

		HSSFCellStyle headerStyle = book.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFont(headerFont);
		cell.setCellStyle(headerStyle);

		sheet.addMergedRegion(CellRangeAddress.valueOf("A1:K1"));

		HSSFFont titleFont = book.createFont();
		titleFont.setFontName("宋体");
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontHeightInPoints((short) 12);

		HSSFCellStyle titleStyle = book.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setBorderTop((short) 1);
		titleStyle.setBorderRight((short) 1);
		titleStyle.setBorderBottom((short) 1);
		titleStyle.setBorderLeft((short) 1);

		/*
		 * Title of Row
		 */
		HSSFRow titleRow = sheet.createRow(rowIndex ++);
		titleRow.setHeightInPoints((short) 15);
		HSSFCellUtil.createCell(titleRow, 0, "姓名", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("A2:A3"));
		HSSFCellUtil.createCell(titleRow, 1, "系数", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:B3"));
		HSSFCellUtil.createCell(titleRow, 2, "医疗绩效", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("C2:C3"));
		HSSFCellUtil.createCell(titleRow, 3, "经营管理绩效", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("D2:D3"));
		HSSFCellUtil.createCell(titleRow, 4, "护理绩效", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("E2:E3"));
		HSSFCellUtil.createCell(titleRow, 5, "误餐费", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("F2:F3"));
		HSSFCellUtil.createCell(titleRow, 6, "奖金合计", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("G2:G3"));
		HSSFCellUtil.createCell(titleRow, 7, "工作量(点值：" + workStatistic.getPointValue() + ")", titleStyle);
		HSSFCellUtil.createCell(titleRow, 8, "", titleStyle);
		HSSFCellUtil.createCell(titleRow, 9, "", titleStyle);
		HSSFCellUtil.createCell(titleRow, 10, "", titleStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("H2:K2"));

		HSSFRow subTitleRow = sheet.createRow(rowIndex ++);
		subTitleRow.setHeightInPoints((short) 15);
		HSSFCellUtil.createCell(subTitleRow, 0, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 1, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 2, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 3, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 4, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 5, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 6, "", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 7, "投照点数", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 8, "诊断点数", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 9, "辅助点数", titleStyle);
		HSSFCellUtil.createCell(subTitleRow, 10, "点数合计", titleStyle);

		/*
		 * Content
		 */
		HSSFFont groupFont = book.createFont();
		groupFont.setFontName("宋体");
		groupFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		groupFont.setFontHeightInPoints((short) 12);

		HSSFCellStyle groupStyle = book.createCellStyle();
		groupStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		groupStyle.setFont(groupFont);
		groupStyle.setBorderTop((short) 1);
		groupStyle.setBorderRight((short) 1);
		groupStyle.setBorderBottom((short) 1);
		groupStyle.setBorderLeft((short) 1);

		HSSFFont amountFont = book.createFont();
		amountFont.setFontName("宋体");
		amountFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		amountFont.setFontHeightInPoints((short) 12);

		HSSFCellStyle amountStyle = book.createCellStyle();
		amountStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		amountStyle.setFont(amountFont);
		amountStyle.setBorderTop((short) 1);
		amountStyle.setBorderRight((short) 1);
		amountStyle.setBorderBottom((short) 1);
		amountStyle.setBorderLeft((short) 1);
		amountStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(typeFormat));

		HSSFFont contentFont = book.createFont();
		contentFont.setFontName("宋体");
		contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		contentFont.setFontHeightInPoints((short) 12);

		HSSFCellStyle contentStyle = book.createCellStyle();
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop((short) 1);
		contentStyle.setBorderRight((short) 1);
		contentStyle.setBorderBottom((short) 1);
		contentStyle.setBorderLeft((short) 1);
		contentStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(typeFormat));

		StaffPerformanceReport[] staffPerformanceReports = findStaffPerformanceReport(workStatistic);
		int recRowIndex = 0;
		List<Integer> amountRows = new ArrayList<Integer>();
		for (StaffPerformanceReport staffPerformanceReport: staffPerformanceReports) {

			HSSFCellStyle cellStyle = null;
			switch(staffPerformanceReport.getType()) {
				case TITLE:
					recRowIndex = 0;
					cellStyle = groupStyle;
					createRow(sheet, rowIndex ++, staffPerformanceReport, cellStyle);
					break;
				case RECORD:
					recRowIndex = recRowIndex == 0 ? rowIndex + 1 : recRowIndex;
					cellStyle = contentStyle;
					createRow(sheet, rowIndex ++, staffPerformanceReport, cellStyle);
					break;
				case AMOUNT:
					cellStyle = amountStyle;
					createAmountRow(sheet, rowIndex ++, new Integer[]{recRowIndex}, staffPerformanceReport, cellStyle);
					amountRows.add(rowIndex);
					break;
				case AMOUNT_TOTAL:
					cellStyle = amountStyle;
					createAmountRow(sheet, rowIndex ++, amountRows.toArray(new Integer[0]), staffPerformanceReport, cellStyle);
					break;
				default:
					break;
			}
		}
		cell.setAsActiveCell();

		return book;
	}

	public HSSFRow createRow(HSSFSheet sheet, int rowIndex, StaffPerformanceReport staffPerformanceReport, HSSFCellStyle style) {
		int cellIndex = 0;
		HSSFRow row = sheet.createRow(rowIndex);
		if (staffPerformanceReport.getType().equals(StaffPerformanceReportType.TITLE)) {
			HSSFCellUtil.createCell(row, cellIndex ++, staffPerformanceReport.getGroupName(), style);
			for (int i = 1; i < 11; i ++) HSSFCellUtil.createCell(row, i, "", style);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (rowIndex+1) + ":K" + (rowIndex+1)));
		} else {
			HSSFCellUtil.createCell(row, cellIndex ++, staffPerformanceReport.getStaffName(), style);
			createCell(row, cellIndex ++, staffPerformanceReport.getDianoseCoef() == null ? 0 : staffPerformanceReport.getDianoseCoef(), style);
			createCell(row, cellIndex ++, staffPerformanceReport.getMedicalAmount(), style);
			createCell(row, cellIndex ++, staffPerformanceReport.getManageAmount(), style);
			createCell(row, cellIndex ++, staffPerformanceReport.getNurseAmount(), style);
			createCell(row, cellIndex ++, staffPerformanceReport.getOvertimeAmount(), style);
			HSSFCell cell = createCell(row, cellIndex ++, staffPerformanceReport.getAmountTotal(), style);
			cell.setCellFormula("sum(C" + (rowIndex+1) + ":F" + (rowIndex+1) + ")");
			createCell(row, cellIndex ++, staffPerformanceReport.getTechPoints(), style);
			createCell(row, cellIndex ++, staffPerformanceReport.getDiagnosePoints(), style);
			createCell(row, cellIndex ++, staffPerformanceReport.getStudentPoints(), style);
			cell = createCell(row, cellIndex ++, staffPerformanceReport.getPointAmount(), style);
			cell.setCellFormula("sum(H" + (rowIndex+1) + ":J" + (rowIndex+1) + ")");
		}

		row.setHeightInPoints(15);

		return row;
	}

	public HSSFRow createAmountRow(HSSFSheet sheet, int rowIndex, Integer[] amountRows, StaffPerformanceReport staffPerformanceReport, HSSFCellStyle style) {
		int cellIndex = 0;
		HSSFCell cell;
		String str = "";
		if (staffPerformanceReport.getType().equals(StaffPerformanceReportType.AMOUNT)) {
			str = "*" + amountRows[0] + ":*" + rowIndex;
		} else if (staffPerformanceReport.getType().equals(StaffPerformanceReportType.AMOUNT_TOTAL)) {
			for (int amountRow: amountRows) {
				str += "*" + amountRow + ",";
			}
			str = str.length() > 0 ? str.substring(0, str.length()-1) : str;
		}

		HSSFRow row = sheet.createRow(rowIndex);
		HSSFCellUtil.createCell(row, cellIndex ++, staffPerformanceReport.getStaffName(), style);
		cell = createCell(row, cellIndex ++, 0, style);
		cell.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
		sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (rowIndex+1) + ":B" + (rowIndex+1)));
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "C") + ")");
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "D") + ")");
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "E") + ")");
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "F") + ")");
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "G") + ")");
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "H") + ")");
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "I") + ")");
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "J") + ")");
		cell = createCell(row, cellIndex ++, 0, style);
		cell.setCellFormula("SUM(" + str.replaceAll("\\Q*\\E", "K") + ")");

		row.setHeightInPoints(15);

		return row;
	}

	@Override
	public StaffPerformanceReport[] generateStaffPerformance(WorkStatistic workStatistic, VMiExecutedStaffPerformance[] staffPerformances, MiWorkload[] overtimes, MiWorkload[] nurseWorkloads, boolean scale) throws Exception {
		List<StaffPerformanceReport> list = new ArrayList<StaffPerformanceReport>();
		int groupId = 0, preGroupId = 0;
		String groupName = "", preGroupName = "";

		double medicalAmount = 0d,
				manageAmount = 0d,
				nurseAmount = 0d,
				overtimeAmount = 0d,
				amount = 0d,
				pointAmount = 0d;
		double medicalTotal = 0d,
				manageTotal = 0d,
				nurseTotal = 0d,
				overtimeTotal = 0d,
				amountTotal = 0d,
				pointTotal = 0d;

		for (int i = 0; i < staffPerformances.length; i ++) {
			VMiExecutedStaffPerformance staffPerformance = staffPerformances[i];
			StaffPerformanceReport report;
			double _nurseAmount = 0d;
			double _overtimeAmount = 0d;
			double _amount = 0d;
			double _pointAmount = 0d;

			if (i == 0) {
				preGroupId = staffPerformance.getGroupId();
				preGroupName = staffPerformance.getGroupName();
			}
			if (groupId != staffPerformance.getGroupId()) {
				if (list.size() > 0) {
					report = new StaffPerformanceReport();
					report.setWorkStatisticId(workStatistic.getId());
					report.setGroupId(preGroupId);
					report.setGroupName(preGroupName);
					report.setStaffName("合计");
					report.setMedicalAmount(scale ? FormatUtil.formatDecimal(medicalAmount, 2) : medicalAmount);
					report.setManageAmount(scale ? FormatUtil.formatDecimal(manageAmount, 2) : manageAmount);
					report.setNurseAmount(scale ? FormatUtil.formatDecimal(nurseAmount, 2) : nurseAmount);
					report.setOvertimeAmount(scale ? FormatUtil.formatDecimal(overtimeAmount, 2) : overtimeAmount);
					report.setAmountTotal(scale ? FormatUtil.formatDecimal(amount, 2) : amount);
					report.setPointAmount(scale ? FormatUtil.formatDecimal(pointAmount, 4) : pointAmount);
					report.setType(StaffPerformanceReportType.AMOUNT);
					list.add(report);

					medicalAmount = 0d;
					manageAmount = 0d;
					nurseAmount = 0d;
					overtimeAmount = 0d;
					amount = 0d;
					pointAmount = 0d;
				}

				groupId = staffPerformance.getGroupId();
				groupName = staffPerformance.getGroupName();
				report = new StaffPerformanceReport();
				report.setWorkStatisticId(workStatistic.getId());
				report.setGroupId(staffPerformance.getGroupId());
				report.setGroupName(staffPerformance.getGroupName());
				report.setType(StaffPerformanceReportType.TITLE);
				list.add(report);
			}

			for (MiWorkload nurseWorkload: nurseWorkloads) {
				if (staffPerformance.getStaffId() == nurseWorkload.getStaff().getId()) {
					_nurseAmount = Arith.mul(
										Arith.div(workStatistic.getNurseCost(), workStatistic.getNurseHours()),
										nurseWorkload.getAmountByCoef()
									);
					break;
				}
			}
			for (MiWorkload overtime: overtimes) {
				if (staffPerformance.getStaffId() == overtime.getStaff().getId()) {
					_overtimeAmount = overtime.getAmount();
					break;
				}
			}
			_amount = staffPerformance.getTotalAmount() + _nurseAmount + _overtimeAmount;
			_pointAmount = staffPerformance.getTechPointTotal() + staffPerformance.getDiagnoPointTotal() + staffPerformance.getAssistPointTotal();

			medicalAmount += staffPerformance.getMedicalTotal();
			manageAmount += staffPerformance.getManageTotal();
			nurseAmount += _nurseAmount;
			overtimeAmount += _overtimeAmount;
			amount += _amount;
			pointAmount += _pointAmount;

			medicalTotal += staffPerformance.getMedicalTotal();
			manageTotal += staffPerformance.getManageTotal();
			nurseTotal += _nurseAmount;
			overtimeTotal += _overtimeAmount;
			amountTotal += _amount;
			pointTotal += _pointAmount;

			report = new StaffPerformanceReport();
			report.setWorkStatisticId(workStatistic.getId());
			report.setGroupId(staffPerformance.getGroupId());
			report.setGroupName(staffPerformance.getGroupName());
			report.setStaffId(staffPerformance.getStaffId());
			report.setStaffName(staffPerformance.getStaffName());
			report.setDianoseCoef(staffPerformance.getStaffDiagnoCoef());
			report.setMedicalAmount(scale ? FormatUtil.formatDecimal(staffPerformance.getMedicalTotal(), 2) : staffPerformance.getMedicalTotal());
			report.setManageAmount(scale ? FormatUtil.formatDecimal(staffPerformance.getManageTotal(), 2) : staffPerformance.getManageTotal());
			report.setNurseAmount(scale ? FormatUtil.formatDecimal(_nurseAmount, 2) : _nurseAmount);
			report.setOvertimeAmount(scale ? FormatUtil.formatDecimal(_overtimeAmount, 2) : _overtimeAmount);
			report.setAmountTotal(scale ? FormatUtil.formatDecimal(_amount, 2) : _amount);
			report.setTechPoints(scale ? FormatUtil.formatDecimal(staffPerformance.getTechPointTotal(), 4) : staffPerformance.getTechPointTotal());
			report.setDiagnosePoints(scale ? FormatUtil.formatDecimal(staffPerformance.getDiagnoPointTotal(), 4) : staffPerformance.getDiagnoPointTotal());
			report.setStudentPoints(scale ? FormatUtil.formatDecimal(staffPerformance.getAssistPointTotal(), 4) : staffPerformance.getAssistPointTotal());
			report.setPointAmount(scale ? FormatUtil.formatDecimal(_pointAmount, 4) : _pointAmount);
			report.setType(StaffPerformanceReportType.RECORD);

			list.add(report);

			if (i+1 == staffPerformances.length) {
				report = new StaffPerformanceReport();
				report.setWorkStatisticId(workStatistic.getId());
				report.setGroupId(groupId);
				report.setGroupName(groupName);
				report.setStaffName("合计");
				report.setMedicalAmount(scale ? FormatUtil.formatDecimal(medicalAmount, 2) : medicalAmount);
				report.setManageAmount(scale ? FormatUtil.formatDecimal(manageAmount, 2) : manageAmount);
				report.setNurseAmount(scale ? FormatUtil.formatDecimal(nurseAmount, 2) : nurseAmount);
				report.setOvertimeAmount(scale ? FormatUtil.formatDecimal(overtimeAmount, 2) : overtimeAmount);
				report.setAmountTotal(scale ? FormatUtil.formatDecimal(amount, 2) : amount);
				report.setPointAmount(scale ? FormatUtil.formatDecimal(pointAmount, 4) : pointAmount);
				report.setType(StaffPerformanceReportType.AMOUNT);
				list.add(report);

				report = new StaffPerformanceReport();
				report.setWorkStatisticId(workStatistic.getId());
				report.setGroupId(999);
				report.setGroupName("");
				report.setStaffName("总计");
				report.setMedicalAmount(scale ? FormatUtil.formatDecimal(medicalTotal, 2) : medicalTotal);
				report.setManageAmount(scale ? FormatUtil.formatDecimal(manageTotal, 2) : manageTotal);
				report.setNurseAmount(scale ? FormatUtil.formatDecimal(nurseTotal, 2) : nurseTotal);
				report.setOvertimeAmount(scale ? FormatUtil.formatDecimal(overtimeTotal, 2) : overtimeTotal);
				report.setAmountTotal(scale ? FormatUtil.formatDecimal(amountTotal, 2) : amountTotal);
				report.setPointAmount(scale ? FormatUtil.formatDecimal(pointTotal, 4) : pointTotal);
				report.setType(StaffPerformanceReportType.AMOUNT_TOTAL);
				list.add(report);
			}
			preGroupId = groupId;
			preGroupName = groupName;
		}

		return list.toArray(new StaffPerformanceReport[0]);
	}

	@Override
	public void saveStaffPerformanceReport(WorkStatistic workStatistic, VMiExecutedStaffPerformance[] staffPerformances, MiWorkload[] overtimes, MiWorkload[] nurseWorkloads, boolean scale) throws Exception {
		StaffPerformanceReport[] reports = this.generateStaffPerformance(workStatistic, staffPerformances, overtimes, nurseWorkloads, true);
		miPerformanceDao.saveStaffPerformanceReport(reports);
	}

	@SuppressWarnings("unchecked")
	@Override
	public StaffPerformanceReport[] findStaffPerformanceReport(WorkStatistic workStatistic) throws Exception {
		List<StaffPerformanceReport> list = (List<StaffPerformanceReport>) miPerformanceDao.query(
				StaffPerformanceReport.class,
				new Criterion[]{
						Restrictions.eq("workStatisticId", workStatistic.getId())
					},
				new Order[]{
						Order.asc("workStatisticId"),
						Order.asc("groupId"),
						Order.asc("type"),
						Order.asc("staffId")
					},
				null,
				false);

		return list.toArray(new StaffPerformanceReport[0]);
	}

	public MiPerformanceDao getMiPerformanceDao() {
		return miPerformanceDao;
	}

	public void setMiPerformanceDao(MiPerformanceDao miPerformanceDao) {
		this.miPerformanceDao = miPerformanceDao;
	}

	public MiExecutedDao getMiExecutedDao() {
		return miExecutedDao;
	}

	public void setMiExecutedDao(MiExecutedDao miExecutedDao) {
		this.miExecutedDao = miExecutedDao;
	}
}
