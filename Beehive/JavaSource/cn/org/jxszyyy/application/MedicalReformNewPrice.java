package cn.org.jxszyyy.application;

import com.sojava.beehive.common.datasource.DBConnector;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MedicalReformNewPrice {
	private final static String DB_DRIVER_NAME = "org.postgresql.Driver";
	private final static String DB_URL = "jdbc:postgresql://192.168.200.1:5432/postgres";
	private final static String DB_USER_NAME = "pgsql";
	private final static String DB_PASSWORD = "johnjia";
	private File directory;
	private Connection connect;

	public static void main(String[] args) {
		try {
			if (args.length == 0) throw new ErrorException(MedicalReformNewPrice.class, "未发现参数");
			File dir = new File(args[0]);
			if (!dir.exists()) throw new ErrorException(MedicalReformNewPrice.class, "未找到路径");
			if (!dir.isDirectory()) throw new ErrorException(MedicalReformNewPrice.class, "路径必须是目录");
			new MedicalReformNewPrice(dir)
				.importData();
		}
		catch(ErrorException ex) {}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	protected MedicalReformNewPrice() {}

	public MedicalReformNewPrice(File directory) {
		this.directory = directory;
	}

	public void importData() throws Exception {
		DBConnector dbConnector = new DBConnector(DB_DRIVER_NAME, DB_URL, DB_USER_NAME, DB_PASSWORD);
		connect = dbConnector.getConnect();

		try {
			findFiles(directory);

			connect.commit();
		}
		catch(Exception ex) {
			connect.rollback();
			throw ex;
		}
		finally {
			connect.close();
		}
	}

	public void findFiles(File directory) throws Exception {
		for (String name : directory.list()) {
			File file = new File(directory.getPath() + "/" + name);
			if (file.isDirectory()) {
				findFiles(file);
			} else {
				parseFile(file);
			}
		}
	}

	public void parseFile(File file) throws Exception {
		PreparedStatement stmt;
		ResultSet rs;

		FileInputStream in = null;
		Workbook book = null;
		Sheet sheet = null;
		try {
			in = new FileInputStream(file);
			String fileName = file.getName();
			System.out.println("Processing " + file.toString());
			String extName = fileName.substring(fileName.lastIndexOf('.')+1);
			if (extName.equals("xlsx")) book = new XSSFWorkbook(in);
			else book = new HSSFWorkbook(in);
			sheet = book.getSheetAt(0);
			MainInfo mainInfo = getMainInfo(sheet);
			stmt = connect.prepareStatement("select count(*) from medicalinnovation.calculate_case_main where inhospital_no=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, mainInfo.code);
			rs = stmt.executeQuery();

			rs.first();
			if (rs.getInt(1) == 0) {
				saveMainInfo(mainInfo);
				saveDiagnosis(mainInfo);
				saveSubInfo(getDetail(sheet, mainInfo.code));
			} else {
				saveDiagnosis(mainInfo);
			}
		}
		catch(Exception ex) {
			throw new Exception(file.getName() + " : " + ex.getMessage());
		}
		finally {
			in.close();
		}
	}

	public MainInfo getMainInfo(Sheet sheet) throws Exception {
		MainInfo result = new MainInfo();
		Row row;
		Cell cell;

		row = sheet.getRow(1);
		//住院号
		cell = row.getCell(1);
		result.code = cell.getStringCellValue().trim();
		//姓名
		cell = row.getCell(3);
		result.name = cell.getStringCellValue().trim();
		//入院时间
		cell = row.getCell(5);
		if (!cell.getStringCellValue().trim().equals("")) result.beginDate = FormatUtil.parseDate(cell.getStringCellValue().trim());
		else result.beginDate = null;
		//住院天数
		cell = row.getCell(8);
		result.days = Integer.parseInt(cell.getStringCellValue().trim());

		row = sheet.getRow(2);
		//科室
		cell = row.getCell(1);
		result.dept = cell.getStringCellValue().trim();
		//性质
		cell = row.getCell(3);
		result.type = cell.getStringCellValue().trim();
		//出院时间
		cell = row.getCell(5);
		if (!cell.getStringCellValue().trim().equals("")) result.endDate = FormatUtil.parseDate(cell.getStringCellValue().trim());
		else result.endDate = null;
		//住院总额
		cell = row.getCell(8);
		result.amount = Double.parseDouble(cell.getStringCellValue().trim());

		result.flag = (short) 20170803;

		//诊断
		String diagnosis = " ";
		for (int r = 0; r < sheet.getLastRowNum(); r ++) {
			row = sheet.getRow(r);
			cell = row.getCell(9);
			if (cell == null) continue;
			String text = cell.getStringCellValue().replaceAll("\\Q \\E", "");
			if (text.equals("")) continue;
			diagnosis += text + ",";
		}
		diagnosis = diagnosis.substring(0, diagnosis.length() - 1);
		result.diagnosis = diagnosis.replaceAll("\\d(\\.|、)", "").replaceAll("\\Q、\\E", ",").replaceAll("\\Q。\\E", ",").trim();
		System.out.println("diagnosis: " + result.diagnosis);

		return result;
	}

	public void saveDiagnosis(MainInfo master) throws Exception {
		PreparedStatement stmt;
		stmt = this.connect.prepareStatement("delete from medicalinnovation.calculate_case_diagnosis where inhospital_no=?");
		stmt.setString(1, master.code);
		stmt.executeUpdate();
		stmt.close();

		for (String diagnosis : master.diagnosis.split("\\Q,\\E")) {
			stmt = this.connect.prepareStatement("insert into medicalinnovation.calculate_case_diagnosis (inhospital_no, diagnosis) values (?,?)");
			stmt.setString(1, master.code);
			stmt.setString(2, diagnosis);
			stmt.executeUpdate();
			stmt.close();
		}
	}

	public void saveMainInfo(MainInfo mainInfo) throws Exception {
		PreparedStatement stmt;
		stmt = this.connect.prepareStatement("insert into medicalinnovation.calculate_case_main"
											+ "(inhospital_no,name,begin_date,end_date,total_days,dept,type,amount,data_flag,diagnosis)"
											+ " values "
											+ "(?,?,?,?,?,?,?,?,?,?)");
		stmt.setString(1, mainInfo.code);
		stmt.setString(2, mainInfo.name);
		stmt.setDate(3, mainInfo.beginDate == null ? null : new java.sql.Date(mainInfo.beginDate.getTime()));
		stmt.setDate(4, mainInfo.endDate == null ? null : new java.sql.Date(mainInfo.endDate.getTime()));
		stmt.setInt(5, mainInfo.days);
		stmt.setString(6, mainInfo.dept);
		stmt.setString(7, mainInfo.type);
		stmt.setDouble(8, mainInfo.amount);
		stmt.setShort(9, mainInfo.flag);
		stmt.setString(10, mainInfo.diagnosis);
		stmt.executeUpdate();
	}

	public SubInfo[] getDetail(Sheet sheet, String code) throws Exception {
		List<SubInfo> list = new ArrayList<SubInfo>();
		for(int r = 4; r <= sheet.getLastRowNum(); r ++) {
			Row row = sheet.getRow(r);
			Cell cell = row.getCell(0);
			String kind = cell.getStringCellValue();
			if (kind.equals("小计:") || kind.equals("")) continue;
			SubInfo detail = new SubInfo();
			detail.inhospitalNo = code;
			detail.kind = kind;
			cell = row.getCell(1);
			detail.name = cell.getStringCellValue();
			cell = row.getCell(4);
			detail.class_ = cell.getStringCellValue();
			cell = row.getCell(5);
			detail.unit = cell.getStringCellValue();
			cell = row.getCell(6);
			detail.price = cell.getNumericCellValue();
			cell = row.getCell(7);
			detail.quantity = Double.parseDouble(cell.getStringCellValue());
			cell = row.getCell(8);
			detail.amount = cell.getNumericCellValue();

			list.add(detail);
		}

		return list.toArray(new SubInfo[0]);
	}

	public void saveSubInfo(SubInfo[] subInfos) throws Exception {
		for (SubInfo detail : subInfos) {
			PreparedStatement stmt;
			stmt = this.connect.prepareStatement("insert into medicalinnovation.calculate_case_sub "
					+ "(inhospital_no,name,kind,class,unit,quantity,price,amount)"
					+ " values "
					+ "(?,?,?,?,?,?,?,?)");
			stmt.setString(1, detail.inhospitalNo);
			stmt.setString(2, detail.name);
			stmt.setString(3, detail.kind);
			stmt.setString(4, detail.class_);
			stmt.setString(5, detail.unit);
			stmt.setDouble(6, detail.quantity);
			stmt.setDouble(7, detail.price);
			stmt.setDouble(8, detail.amount);

			stmt.executeUpdate();
		}
	}
}

class MainInfo {
	public String code;
	public String name;
	public String diagnosis;
	public Date beginDate;
	public Date endDate;
	public int days;
	public String dept;
	public String type;
	public double amount;
	public short flag;
}

class SubInfo {
	public String inhospitalNo;
	public String name;
	public String kind;
	public String class_;
	public String unit;
	public double quantity;
	public double price;
	public double amount;
	public double calculatePrice;
	public double calculateAmount;
	public double amountDifference;
}