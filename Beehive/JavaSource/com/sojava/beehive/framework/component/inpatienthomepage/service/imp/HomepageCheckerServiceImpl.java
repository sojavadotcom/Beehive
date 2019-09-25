package com.sojava.beehive.framework.component.inpatienthomepage.service.imp;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.Dictionary;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.IcdTransform;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnalyCheck;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.RecordRangeType;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.VIcdTransform;
import com.sojava.beehive.framework.component.inpatienthomepage.dao.HomepageDao;
import com.sojava.beehive.framework.component.inpatienthomepage.service.HomepageCheckerService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class HomepageCheckerServiceImpl implements HomepageCheckerService {

	@Resource private HomepageDao homepageDao;
	private List<InpatientHomepageAnaly> homepageList;
	private List<InpatientHomepageAnalyCheck> checkRecord;
	private int homepageIndex = 1;
	private int checkIndex = 1;

	private static Dictionary[] rc001 = null; //性别代码
	private static Dictionary[] rc002 = null; //婚姻状况
	private static Dictionary[] rc003 = null; //职业代码
	private static Dictionary[] rc011 = null; //病案质量
	private static Dictionary[] rc013 = null; //麻醉方式
	private static Dictionary[] rc014_1 = null; //切口愈合等级
	private static Dictionary[] rc014_2 = null; //切口愈合类别
	private static Dictionary[] rc016 = null; //判断代码
	private static Dictionary[] rc019 = null; //离院方式
	private static Dictionary[] rc023 = null; //科室代码
	private static Dictionary[] rc026 = null; //入院途径代码
	private static Dictionary[] rc027 = null; //入院病情
	private static Dictionary[] rc028 = null; //药物过敏
	private static Dictionary[] rc029 = null; //手术级别
	private static Dictionary[] rc030 = null; //血型编码
	private static Dictionary[] rc031 = null; //Rh血型
	private static Dictionary[] rc032 = null; //医疗付费方式
	private static Dictionary[] rc033 = null; //联系人关系
	private static Dictionary[] rc035 = null; //民族代码
	private static Dictionary[] rc036 = null; //籍贯代码
	private static Dictionary[] rc037 = null; //有无字典
	private static Dictionary[] rc038 = null; //国籍字典
	private static Dictionary[] rc039 = null; //治疗类别
	private static Dictionary[] rc040 = null; //实施临床路径
	/*
	 * ICD对照
	 */
	private static VIcdTransform[] icdHis = null;
	private static IcdTransform[] icd2 = null;

	public HomepageCheckerServiceImpl() throws Exception {
		homepageList = new ArrayList<InpatientHomepageAnaly>();
		checkRecord = new ArrayList<InpatientHomepageAnalyCheck>();
		String _status = "";
		try {
			_status = "RC001";
			if (rc001 == null) rc001 = homepageDao.getDictionary(RecordRangeType.rc001).toArray(new Dictionary[0]);
			_status = "RC002";
			if (rc002 == null) rc002 = homepageDao.getDictionary(RecordRangeType.rc002).toArray(new Dictionary[0]);
			_status = "RC003";
			if (rc003 == null) rc003 = homepageDao.getDictionary(RecordRangeType.rc003).toArray(new Dictionary[0]);
			_status = "RC011";
			if (rc011 == null) rc011 = homepageDao.getDictionary(RecordRangeType.rc011).toArray(new Dictionary[0]);
			_status = "RC013";
			if (rc013 == null) rc013 = homepageDao.getDictionary(RecordRangeType.rc013).toArray(new Dictionary[0]);
			_status = "RC014_1";
			if (rc014_1 == null) rc014_1 = homepageDao.getDictionary(RecordRangeType.rc014_1).toArray(new Dictionary[0]);
			_status = "RC014_2";
			if (rc014_2 == null) rc014_2 = homepageDao.getDictionary(RecordRangeType.rc014_2).toArray(new Dictionary[0]);
			_status = "RC016";
			if (rc016 == null) rc016 = homepageDao.getDictionary(RecordRangeType.rc016).toArray(new Dictionary[0]);
			_status = "RC019";
			if (rc019 == null) rc019 = homepageDao.getDictionary(RecordRangeType.rc019).toArray(new Dictionary[0]);
			_status = "RC023";
			if (rc023 == null) rc023 = homepageDao.getDictionary(RecordRangeType.rc023).toArray(new Dictionary[0]);
			_status = "RC026";
			if (rc026 == null) rc026 = homepageDao.getDictionary(RecordRangeType.rc026).toArray(new Dictionary[0]);
			_status = "RC027";
			if (rc027 == null) rc027 = homepageDao.getDictionary(RecordRangeType.rc027).toArray(new Dictionary[0]);
			_status = "RC028";
			if (rc028 == null) rc028 = homepageDao.getDictionary(RecordRangeType.rc028).toArray(new Dictionary[0]);
			_status = "RC029";
			if (rc029 == null) rc029 = homepageDao.getDictionary(RecordRangeType.rc029).toArray(new Dictionary[0]);
			_status = "RC030";
			if (rc030 == null) rc030 = homepageDao.getDictionary(RecordRangeType.rc030).toArray(new Dictionary[0]);
			_status = "RC031";
			if (rc031 == null) rc031 = homepageDao.getDictionary(RecordRangeType.rc031).toArray(new Dictionary[0]);
			_status = "RC032";
			if (rc032 == null) rc032 = homepageDao.getDictionary(RecordRangeType.rc032).toArray(new Dictionary[0]);
			_status = "RC033";
			if (rc033 == null) rc033 = homepageDao.getDictionary(RecordRangeType.rc033).toArray(new Dictionary[0]);
			_status = "RC035";
			if (rc035 == null) rc035 = homepageDao.getDictionary(RecordRangeType.rc035).toArray(new Dictionary[0]);
			_status = "RC036";
			if (rc036 == null) rc036 = homepageDao.getDictionary(RecordRangeType.rc036).toArray(new Dictionary[0]);
			_status = "RC037";
			if (rc037 == null) rc037 = homepageDao.getDictionary(RecordRangeType.rc037).toArray(new Dictionary[0]);
			_status = "RC038";
			if (rc038 == null) rc038 = homepageDao.getDictionary(RecordRangeType.rc038).toArray(new Dictionary[0]);
			_status = "RC039";
			if (rc039 == null) rc039 = homepageDao.getDictionary(RecordRangeType.rc039).toArray(new Dictionary[0]);
			_status = "RC040";
			if (rc040 == null) rc040 = homepageDao.getDictionary(RecordRangeType.rc040).toArray(new Dictionary[0]);
			_status = "HIS诊断对照";
			if (icdHis == null) icdHis = homepageDao.getHISDiagno().toArray(new VIcdTransform[0]);
			_status = "ICD2.0";
			if (icd2 == null) icd2 = homepageDao.getICD2().toArray(new IcdTransform[0]);
		}
		catch (Exception ex) {
			throw new ErrorException(getClass(), "初始化字典数据[" + _status + "]时发生错误 - " + ex.getClass().getName() + " : " + ex.getMessage());
		}
	}

	@Override
	public void loadCSV(File[] csvFile) throws Exception {
		for(File file : csvFile) {
			String fileName = file.getName();
			String[] fileProperty = fileName.split("\\Q.\\E")[0].split("\\Q_\\E");
			if (fileProperty.length != 3) throw new ErrorException(this.getClass(), "文件名格式错误[TCMMS_201901-08_1]");
			String kind = fileProperty[0];
			String type = fileProperty[1];
			int version = Integer.parseInt(fileProperty[2]);
			CSVParser parser = CSVParser.parse(new FileReader(file), CSVFormat.DEFAULT);
			parser.forEach(rec -> importHomepage(rec, kind, type, version));
		}
	}

	@Override
	public void importHomepage(CSVRecord record, String homepageKind, String homepageType, int version) {
		InpatientHomepageAnaly homepage = new InpatientHomepageAnaly(this.homepageIndex ++, homepageKind, homepageType, version);
		short checked = 0;
		String name = null, value = null;
		try {
			for (Field field : homepage.getClass().getFields()) {
				name = field.getName();
				if (name.equals("id") || name.equals("kind") || name.equals("type") || name.equals("checked") || name.equals("inpatientHomepageAnalyChecks")) continue;
				String method = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				Class<?> type = field.getType();
				value = record.get(name);

				try {
					Object val = checkValue(name, value, type);
					homepage.getClass().getMethod(method, Double.class).invoke(homepage, val);
				}
				catch(Exception ex) {
					checked ++;
					this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, ex.getMessage(), "Item"));
				}
			}
			checkColumn(homepage);
		}
		catch(Exception ex) {
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					name,
					value,
					"在处理第 " + record.getRecordNumber() + " 行" + (name != null ? "[" + name + "=" + (value == null ? "" : value) + "]" : "") + "时发生严重错误 [" + ex.getMessage() + "]",
					"Entry"
				)
			);
		}
		homepage.setChecked(checked);
		homepageList.add(homepage);
	}

	@Override
	public Object checkValue(String name, String value, Class<?> type) throws Exception {
		Object result = value;

		/***************************/
		/******* 数据类型转换 ********/ 
		/**************************/
		if (type.equals(Double.class)) {
			Double val = null;
			try {
				val = value.isEmpty() ? null : Double.parseDouble(value);
			}
			catch(NumberFormatException e) {
				throw new Exception("不是合法的数值" + e.getMessage());
			}
			result = val;
		} else if (type.equals(Integer.class)) {
			Integer val = null;
			try {
				val = value.isEmpty() ? null : Integer.parseInt(value);
			}
			catch(NumberFormatException e) {
				throw new Exception("不是合法的整数" + e.getMessage());
			}
			result = val;
		} else if (type.equals(Short.class)) {
			Short val = null;
			try {
				val = value.isEmpty() ? null : Short.parseShort(value);
			}
			catch(NumberFormatException e) {
				throw new Exception("不是合法的整数" + e.getMessage());
			}
			result = val;
		} else if (type.equals(Date.class)) {
			Date val = null;
			try {
				val = value.isEmpty() ? null : FormatUtil.parseDate(value);
			}
			catch(ParseException e) {
				throw new Exception("不是合法的日期" + e.getMessage());
			}
			result = val;
		} else if (type.equals(Timestamp.class)) {
			Timestamp val = null;
			try {
				val = value.isEmpty() ? null : new Timestamp(FormatUtil.parseDateTime(value).getTime());
			}
			catch(ParseException e) {
				throw new Exception("不是合法的时间" + e.getMessage());
			}
			result = val;
		}

		return result;
	}

	@Override
	public void checkColumn(InpatientHomepageAnaly homepage) throws Exception {

		/***************************************************************************************/
		/******* 病案首页数据逻辑检查，依据《三级公立中医医院病案首页数据采集接口标准与质量要求》。 ********/ 
		/***************************************************************************************/
		short checked = homepage.getChecked();
		/*
		 * 1. ZZJGDM	组织机构代码	字符	22	必填	指医疗机构执业许可证上面的机构代码。
		 */
		if (homepage.getZzjgdm().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZZJGDM",
					homepage.getZzjgdm(),
					"[ZZJGDM:组织机构代码=" + homepage.getZzjgdm() + "]未填写组织机构代码",
					"Item"
				));
		} else if (homepage.getZzjgdm().length() != 18) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZZJGDM",
					homepage.getZzjgdm(),
					"[ZZJGDM:组织机构代码=" + homepage.getZzjgdm() + "]组织机构代码应为18位",
					"Item"
				));
		}
		/*
		 * 2. JGMC	医疗机构名称	字符	80	必填
		 */
		if (homepage.getJgmc().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"JGMC",
					homepage.getZzjgdm(),
					"[JGMC:组织机构名称=" + homepage.getJgmc() + "]未填写组织机构名称",
					"Item"
				));
		}
		/*
		 * 3. USERNAME	对应的系统登录用户名	字符	10	必填	医院名称或者编码
		 */
		if (homepage.getUsername().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"USERNAME",
					homepage.getUsername(),
					"[USERNAME:系统登录用户名=" + homepage.getUsername() + "]未填写系统登录用户名",
					"Item"
				));
		}
		/*
		 * 4. YLFKFS	医疗付款方式	字符	3	必填	《值域范围参考RC032-医疗付费方式代码》
		 */
		if (homepage.getYlfkfs().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"YLFKFS",
					homepage.getYlfkfs(),
					"[YLFKFS:医疗付款方式=" + homepage.getYlfkfs() + "]未填写医疗付款方式",
					"Item"
				));
		} else if (!compareDic(rc032, RecordRangeType.code, homepage.getYlfkfs())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"YLFKFS",
					homepage.getYlfkfs(),
					"[YLFKFS:医疗付款方式=" + homepage.getYlfkfs() + "]代码填写不正确，《值域范围参考RC032-医疗付费方式代码》",
					"Item"
				));
		}
		/*
		 * 5. JKKH	健康卡号	字符	50		在已统一发放“中华人民共和国居民健康卡”的地区填写健康卡号码，尚未发放“健康卡”的地区填写“-”
		 */
		if (homepage.getJkkh().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"JKKH",
					homepage.getJkkh(),
					"[JKKH:医疗付款方式=" + homepage.getJkkh() + "]未填写健康卡号，尚未发放“健康卡”的地区填写“-”",
					"Item"
				));
		}
		/*
		 * 6. ZYCS	住院次数	数字	4	必填	大于0的整数
		 */
		if (homepage.getZycs().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZYCS",
					homepage.getZycs(),
					"[ZYCS:住院次数=" + homepage.getZycs() + "]未填写住院次数",
					"Item"
				));
		} else if (!isInteger(homepage.getZycs())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZYCS",
					homepage.getZycs(),
					"[ZYCS:住院次数=" + homepage.getZycs() + "]填写不正确，必须是大于0的整数",
					"Item"
				));
		} else if (Integer.parseInt(homepage.getZycs()) <= 0) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZYCS",
					homepage.getZycs(),
					"[ZYCS:住院次数=" + homepage.getZycs() + "]住院次数必须大于0",
					"Item"
				));
		}
		/*
		 * 7. BAH	病案号	字符	50	必填
		 */
		if (homepage.getBah().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"BAH",
					homepage.getBah(),
					"[BAH:病案号=" + homepage.getBah() + "]未填写病案号",
					"Item"
				));
		}
		/*
		 * 8. XM	姓名	字符	40	必填
		 */
		if (homepage.getXm().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XM",
					homepage.getXm(),
					"[XM:姓名=" + homepage.getXm() + "]未填写患者姓名",
					"Item"
				));
		}
		/*
		 * 9. XB	性别	数字	1	必填	《值域范围参考RC001-性别代码》
		 */
		if (homepage.getXb().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XB",
					homepage.getXb(),
					"[XB:性别=" + homepage.getXb() + "]未填写性别",
					"Item"
				));
		} else if (!compareDic(rc001, RecordRangeType.code, homepage.getXb())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XB",
					homepage.getXb(),
					"[XB:性别=" + homepage.getXb() + "]代码填写不正确，《值域范围参考RC001-性别代码》",
					"Item"
				));
		}
		/*
		 * 10. CSRQ	出生日期	日期	10	必填	yyyy-mm-dd
		 */
		if (homepage.getCsrq().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"CSRQ",
					homepage.getCsrq(),
					"[CSRQ:出生日期=" + homepage.getCsrq() + "]未填写出生日期",
					"Item"
				));
		} else if (!isDate(homepage.getCsrq())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"CSRQ",
					homepage.getCsrq(),
					"[CSRQ:出生日期=" + homepage.getCsrq() + "]格式不正确，格式 yyyy-MM-dd",
					"Item"
				));
		}
		/*
		 * 11. NL	年龄（岁）	数字	3	必填	患者入院年龄，指患者入院时按照日历计算的历法年龄，应以实足年龄的相应整数填写。大于或等于0的整数
		 */
		Integer _age = null;
		try {_age = Integer.parseInt(homepage.getNl());} catch(Exception e) {}
		if (homepage.getNl().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"NL",
					homepage.getNl(),
					"[NL:年龄（岁）=" + homepage.getNl() + "]未填写",
					"Item"
				));
		} else if (!isInteger(homepage.getNl())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"NL",
					homepage.getNl(),
					"[NL:年龄（岁）=" + homepage.getNl() + "]不是数字(整数)",
					"Item"
				));
		} else if (_age < 0) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"NL",
					homepage.getNl(),
					"[NL:年龄（岁）=" + homepage.getNl() + "]必须是大于或等于0的整数",
					"Item"
				));
		}
		/*
		 * 12. GJ	国籍	字符	40	必填	《值域范围参考RC038-国籍字典》
		 */
		if (homepage.getGj().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"GJ",
					homepage.getGj(),
					"[GJ:国籍=" + homepage.getGj() + "]未填写国籍",
					"Item"
				));
		} else if (!compareDic(rc038, RecordRangeType.code, homepage.getGj())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"GJ",
					homepage.getGj(),
					"[GJ:国籍=" + homepage.getGj() + "]代码填写不正确，《值域范围参考RC038-国籍字典》",
					"Item"
				));
		}
		/*
		 * 13. BZYZS_NL	年龄不足1周岁的年龄（天）	数字	3	条件必填	新生儿病例
		 */
		if (_age != null && _age == 0) {
			if (homepage.getBzyzsNl().isEmpty()) {
				checked ++;
				this.checkRecord.add(new InpatientHomepageAnalyCheck(
						this.checkIndex ++,
						homepage.getId(),
						"BZYZS_NL",
						homepage.getBzyzsNl(),
						"[BZYZS_NL:年龄不足1周岁的年龄（天）=" + homepage.getBzyzsNl() + "]年龄不足1周岁的未填写",
						"Item"
					));
			} else if(!isFloat(homepage.getBzyzsNl())) {
				checked ++;
				this.checkRecord.add(new InpatientHomepageAnalyCheck(
						this.checkIndex ++,
						homepage.getId(),
						"BZYZS_NL",
						homepage.getBzyzsNl(),
						"[BZYZS_NL:年龄不足1周岁的年龄（天）=" + homepage.getBzyzsNl() + "]必须为数字",
						"Item"
					));
			}
		}
		/*
		 * 14. XSETZ	新生儿出生体重(克)	数字	6	条件必填	测量新生儿体重要求精确到10克；应在活产后一小时内称取重量。
		 * 1、产妇和新生儿病案填写，从出生到28天为新生儿期，双胎及以上不同胎儿体重则继续填写下面的新生儿出生体重。
		 * 2、新生儿体重范围：100克-9999克，产妇的主要诊断或其他诊断编码中含有Z37.0,Z37.2, Z37.3, Z37.5, Z37.6编码时，未填写新生儿出生体重
		 */
//		TODO 新生儿体重
//		if () {
//			
//		}
		/*
		 * 15. XSETZ2	新生儿出生体重(克)2	数字	6		新生儿体重范围：100克-9999克
		 */
		if (!homepage.getXsetz2().isEmpty() && !isFloat(homepage.getXsetz2())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XSETZ2",
					homepage.getXsetz2(),
					"[XSETZ2:新生儿出生体重(克)2=" + homepage.getXsetz2() + "]必须为数字",
					"Item"
				));
		}
		/*
		 * 16. XSETZ3	新生儿出生体重(克)3	数字	6		新生儿体重范围：100克-9999克
		 */
		if (!homepage.getXsetz3().isEmpty() && !isFloat(homepage.getXsetz3())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XSETZ3",
					homepage.getXsetz3(),
					"[XSETZ3:新生儿出生体重(克)3=" + homepage.getXsetz3() + "]必须为数字",
					"Item"
				));
		}
		/*
		 * 17. XSETZ4	新生儿出生体重(克)4	数字	6		新生儿体重范围：100克-9999克
		 */
		if (!homepage.getXsetz4().isEmpty() && !isFloat(homepage.getXsetz4())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XSETZ4",
					homepage.getXsetz4(),
					"[XSETZ4:新生儿出生体重(克)4=" + homepage.getXsetz4() + "]必须为数字",
					"Item"
				));
		}
		/*
		 * 18. XSETZ5	新生儿出生体重(克)5	数字	6		新生儿体重范围：100克-9999克
		 */
		if (!homepage.getXsetz5().isEmpty() && !isFloat(homepage.getXsetz5())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XSETZ5",
					homepage.getXsetz5(),
					"[XSETZ5:新生儿出生体重(克)5=" + homepage.getXsetz5() + "]必须为数字",
					"Item"
				));
		}
		/*
		 * 19. XSERYTZ	新生儿入院体重（克）	数字	6	条件必填	100克-9999克，精确到10克；新生儿入院当日的体重；
		 * 小于等于28天的新生儿必填，填写了新生儿入出院体重的，未填写年龄不足1周岁的年龄（天），且必须小于等于28天。
		 */
		// TODO 新生儿入院体重
//		if () {
//			
//		}
		/*
		 * 20. CSD	出生地	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		if (homepage.getCsd().isEmpty() || homepage.getCsd().length() <= 2) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"CSD",
					homepage.getCsd(),
					"[CSD:出生地=" + homepage.getCsd() + "]未填写出生地",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getCsd())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"CSD",
					homepage.getCsd(),
					"[CSD:出生地=" + homepage.getCsd() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 21. GG	籍贯	字符	50	必填	《值域范围参考RC036-籍贯》
		 */
		if (homepage.getGg().isEmpty() || homepage.getGg().length() <= 2) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"GG",
					homepage.getGg(),
					"[GG:籍贯=" + homepage.getGg() + "]未填写籍贯",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getGg())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"GG",
					homepage.getGg(),
					"[GG:籍贯=" + homepage.getGg() + "]填写不正确，《值域范围参考RC036-籍贯》",
					"Item"
				));
		}
		/*
		 * 22. MZ	民族	字符	2	必填	《值域范围参考RC035-民族代码》
		 */
		if (homepage.getMz().isEmpty() || homepage.getMz().length() <= 2) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"MZ",
					homepage.getMz(),
					"[MZ:民族=" + homepage.getMz() + "]未填写民族",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getMz())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"MZ",
					homepage.getMz(),
					"[MZ:民族=" + homepage.getMz() + "]填写不正确，《值域范围参考RC035-民族代码》",
					"Item"
				));
		}
		/*
		 * 23. SFZH	身份证号	字符	18	必填	住院患者入院时要如实填写15位或18位身份证号码
		 */
		if (homepage.getSfzh().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"SFZH",
					homepage.getSfzh(),
					"[SFZH:身份证号=" + homepage.getSfzh() + "]未填写身份证号",
					"Item"
				));
		} else if (isInteger(homepage.getSfzh()) && homepage.getSfzh().length() != 15 && homepage.getSfzh().length() != 18) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"SFZH",
					homepage.getSfzh(),
					"[SFZH:身份证号=" + homepage.getSfzh() + "]填写不正确，必须是15位或18位",
					"Item"
				));
		}
		/*
		 * 24. ZY	职业	字符	2	必填	《值域范围参考RC003-职业代码》
		 */
		if (homepage.getZy().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZY",
					homepage.getZy(),
					"[ZY:职业=" + homepage.getZy() + "]未填写职业",
					"Item"
				));
		} else if (!compareDic(rc003, RecordRangeType.code, homepage.getZy())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZY",
					homepage.getZy(),
					"[ZY:职业=" + homepage.getZy() + "]填写不正确，《值域范围参考RC003-职业代码》",
					"Item"
				));
		}
		/*
		 * 25. HY	婚姻	字符	1	必填	《值域范围参考RC002-婚姻代码》
		 */
		if (homepage.getHy().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"HY",
					homepage.getHy(),
					"[HY:婚姻=" + homepage.getHy() + "]未填写婚姻",
					"Item"
				));
		} else if (!compareDic(rc002, RecordRangeType.code, homepage.getHy())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"HY",
					homepage.getHy(),
					"[HY:婚姻=" + homepage.getHy() + "]填写不正确，《值域范围参考RC002-婚姻代码》",
					"Item"
				));
		}
		/*
		 * 26. XZZ	现住址	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		if (homepage.getXzz().isEmpty() || homepage.getXzz().length() <= 2) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XZZ",
					homepage.getXzz(),
					"[XZZ:现住址=" + homepage.getXzz() + "]未填写现住址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getXzz())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"XZZ",
					homepage.getXzz(),
					"[XZZ:现住址=" + homepage.getXzz() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 27. DH	现住址电话	字符	40	必填
		 */
		if (homepage.getDh().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"DH",
					homepage.getDh(),
					"[DH:现住址电话=" + homepage.getDh() + "]未填写现住址电话",
					"Item"
				));
		}
		/*
		 * 28. YB1	现住址邮政编码	字符	6	必填	6位数字
		 */
		if (homepage.getYb1().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"YB1",
					homepage.getYb1(),
					"[YB1:现住址邮政编码=" + homepage.getYb1() + "]未填写现住址邮政编码",
					"Item"
				));
		}
		/*
		 * 29. HKDZ	户口地址	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		if (homepage.getHkdz().isEmpty() || homepage.getHkdz().length() <= 2) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"HKDZ",
					homepage.getHkdz(),
					"[HKDZ:户口地址=" + homepage.getHkdz() + "]未填写户口地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getHkdz())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"HKDZ",
					homepage.getHkdz(),
					"[HKDZ:户口地址=" + homepage.getHkdz() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 30. YB2	户口地址邮政编码	字符	6	必填	6位数字
		 */
		if (homepage.getYb2().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"YB2",
					homepage.getYb2(),
					"[YB2:户口地址邮政编码=" + homepage.getYb2() + "]未填写户口地址邮政编码",
					"Item"
				));
		}
		/*
		 * 31. GZDWJDZ	工作单位及地址	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		if (homepage.getGzdwjdz().isEmpty() || homepage.getGzdwjdz().length() <= 2) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"GZDWJDZ",
					homepage.getGzdwjdz(),
					"[GZDWJDZ:工作单位及地址=" + homepage.getGzdwjdz() + "]未填写工作单位及地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getGzdwjdz())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"GZDWJDZ",
					homepage.getGzdwjdz(),
					"[GZDWJDZ:工作单位=" + homepage.getGzdwjdz() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 32. DWDH	工作单位电话	字符	20	必填
		 */
		if (homepage.getDwdh().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"DWDH",
					homepage.getDwdh(),
					"[DWDH:工作单位电话=" + homepage.getDwdh() + "]未填写工作单位电话",
					"Item"
				));
		}
		/*
		 * 33. YB3	工作单位邮政编码	字符	6	必填	6位数字
		 */
		if (homepage.getYb3().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"YB3",
					homepage.getYb3(),
					"[YB3:工作单位邮政编码=" + homepage.getYb3() + "]未填写工作单位邮政编码",
					"Item"
				));
		}
		/*
		 * 34. LXRXM	联系人姓名	字符	40	必填
		 */
		if (homepage.getLxrxm().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"LXRXM",
					homepage.getLxrxm(),
					"[LXRXM:联系人姓名=" + homepage.getLxrxm() + "]未填写联系人姓名",
					"Item"
				));
		}
		/*
		 * 35. GX	联系人关系	字符	1	必填	《值域范围参考RC033-联系人关系代码》
		 */
		if (homepage.getGx().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"GX",
					homepage.getGx(),
					"[GX:联系人关系=" + homepage.getGx() + "]未填写联系人关系",
					"Item"
				));
		} else if (!compareDic(rc033, RecordRangeType.code, homepage.getGx())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"GX",
					homepage.getGx(),
					"[GX:联系人关系=" + homepage.getGx() + "]填写不正确，《值域范围参考RC033-联系人关系代码》",
					"Item"
				));
		}
		/*
		 * 36. DZ	联系人地址	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		if (homepage.getDz().isEmpty() || homepage.getDz().length() <= 2) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"DZ",
					homepage.getDz(),
					"[DZ:联系人地址=" + homepage.getDz() + "]未填写联系人地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getDz())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"DZ",
					homepage.getDz(),
					"[DZ:联系人地址=" + homepage.getDz() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 37. DH1	联系人电话	字符	40	必填
		 */
		if (homepage.getDh1().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"DH1",
					homepage.getDh1(),
					"[DH1:联系人电话=" + homepage.getDh1() + "]未填写工作单位电话",
					"Item"
				));
		}
		/*
		 * 38. RYTJ	入院途径	字符	1	必填	《值域范围参考RC026-入院途径代码》
		 */
		if (homepage.getRytj().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYTJ",
					homepage.getRytj(),
					"[RYTJ:入院途径=" + homepage.getRytj() + "]未填写入院途径",
					"Item"
				));
		} else if (!compareDic(rc026, RecordRangeType.code, homepage.getRytj())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYTJ",
					homepage.getRytj(),
					"[RYTJ:入院途径=" + homepage.getRytj() + "]填写不正确，《值域范围参考RC026-入院途径代码》",
					"Item"
				));
		}
		/*
		 * 39. ZLLB	治疗类别	字符	3		《值域范围参考RC039-治疗类别字典》
		 */
		if (!homepage.getZllb().isEmpty() && !compareDic(rc039, RecordRangeType.code, homepage.getZllb())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZLLB",
					homepage.getZllb(),
					"[ZLLB:治疗类别=" + homepage.getZllb() + "]填写不正确，《值域范围参考RC039-治疗类别字典》",
					"Item"
				));
		}
		/*
		 * 40. RYSJ	入院时间	日期		必填	格式 yyyy-MM-dd HH:mm:ss；入院时间不能晚于出院时间
		 */
		if (homepage.getRysj().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYSJ",
					homepage.getRysj(),
					"[RYSJ:入院时间=" + homepage.getRysj() + "]未填写入院时间",
					"Item"
					));
		} else if (!isDatetime(homepage.getRysj())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYSJ",
					homepage.getRysj(),
					"[RYSJ:入院时间=" + homepage.getRysj() + "]填写不正确，格式 yyyy-MM-dd HH:mm:ss",
					"Item"
					));
		}
		/*
		 * 41. RYSJ_S	时			必填	24小时制
		 */
		if (homepage.getRysjS().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYSJ_S",
					homepage.getRysjS(),
					"[RYSJ_S:入院时间（时）=" + homepage.getRysjS() + "]未填写入院时间（时）",
					"Item"
					));
		} else if (!isInteger(homepage.getRysjS())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYSJ_S",
					homepage.getRysjS(),
					"[RYSJ_S:入院时间（时）=" + homepage.getRysjS() + "]填写不正确，小时数（24小时制）",
					"Item"
					));
		} else {
			if (isDatetime(homepage.getRysj())) {
				Date _rysj = FormatUtil.parseDate(homepage.getRysj());
				Calendar _rysjCal = Calendar.getInstance();
				_rysjCal.setTime(_rysj);
				int _hour = _rysjCal.get(Calendar.HOUR);
				if (_hour != Integer.parseInt(homepage.getRysjS())) {
					checked ++;
					this.checkRecord.add(new InpatientHomepageAnalyCheck(
							this.checkIndex ++,
							homepage.getId(),
							"RYSJ_S",
							homepage.getRysjS(),
							"[RYSJ:入院时间=" + homepage.getRysj() + "；RYSJ_S:入院时间（时）=" + homepage.getRysjS() + "]时间（时）不匹配",
							"Item"
							));
				}
			}
		}
		/*
		 * 42. RYKB	入院科别	字符	6	必填	《值域范围参考RC023-科室代码》
		 */
		if (homepage.getRykb().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYKB",
					homepage.getRykb(),
					"[RYKB:入院科别=" + homepage.getRykb() + "]未填写入院科别",
					"Item"
				));
		} else if (!compareDic(rc023, RecordRangeType.code, homepage.getRykb())) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYKB",
					homepage.getRykb(),
					"[RYKB:入院科别=" + homepage.getRykb() + "]填写不正确，《值域范围参考RC023-科室代码》",
					"Item"
				));
		}
		/*
		 * 43. RYBF	入院病房	字符	30	必填
		 */
		if (homepage.getRybf().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"RYBF",
					homepage.getRybf(),
					"[RYBF:入院病房=" + homepage.getRybf() + "]未填写入院病房",
					"Item"
				));
		}
		/*
		 * 44. ZKKB	转科科别	集合	可以多选		《值域范围参考RC023-科室代码》
		 */
		if (homepage.getZkkb().isEmpty()) {
			checked ++;
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					"ZKKB",
					homepage.getZkkb(),
					"[ZKKB:转科科别=" + homepage.getZkkb() + "]未填写转科科别",
					"Item"
				));
		} else {
			String[] _zkkbs = homepage.getZkkb().split("\\Q,\\E");
			for (String _zkkb : _zkkbs) {
				if (!compareDic(rc023, RecordRangeType.code, _zkkb.trim())) {
					
				}
			}
		}

		homepage.setChecked(checked);
	}

	public boolean compareDic(Dictionary[] dic, RecordRangeType type, String keyword) {
		boolean result = false;
		for (Dictionary d : dic) {
			if (dic.equals(rc036) || dic.equals(rc035)) {
				result = d.getLabel().substring(0, 2).equals(keyword.substring(0, 2));
			} else {
				if (type == RecordRangeType.code) {
					result = d.getCode().equals(keyword);
				} else if (type == RecordRangeType.name) {
					result = d.getLabel().equals(keyword);
				}
			}
			if (result) return result;
		}
		return result;
	}

	public boolean compareDate(String gdate, String ldate, Integer age) throws Exception {
		Date _gdate = null, _ldate = null;
		Calendar _gcal = null, _lcal = null;
		try {
			_gdate = FormatUtil.parseDate(gdate);
			_gcal = Calendar.getInstance();
			_gcal.setTime(_gdate);
			if (ldate != null) {
				_ldate = FormatUtil.parseDate(ldate);
				_lcal = Calendar.getInstance();
				_lcal.setTime(_ldate);
			}

			if (age != null && ldate != null) 
				if (_gcal.get(Calendar.YEAR) - _lcal.get(Calendar.YEAR) != age) throw new ErrorException(null, "年龄与出生日期不符");

			return true;
		}
		catch (ParseException ex) {
			throw new Exception("格式错误(yyyy-MM-dd)");
		}
		catch (ErrorException ex) {
			throw new Exception(ex.getMessage());
		}
		catch (Exception ex) {
			throw new Exception("错误");
		}
	}

	public boolean isDate(String date) {
		try {
			FormatUtil.parseDate(date);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean isDatetime(String datetime) {
		try {
			FormatUtil.parseDateTime(datetime);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean isNumber(String num, boolean isInt) {
		try {
			if (isInt) Long.parseLong(num);
			else Double.parseDouble(num);

			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean isInteger(String num) {
		return isNumber(num, true);
	}

	public boolean isFloat(String num) {
		return isNumber(num, false);
	}

	public HomepageDao getHomepageDao() {
		return homepageDao;
	}

	public void setHomepageDao(HomepageDao homepageDao) {
		this.homepageDao = homepageDao;
	}

	public List<InpatientHomepageAnaly> getHomepageList() {
		return homepageList;
	}

	public void setHomepageList(List<InpatientHomepageAnaly> homepageList) {
		this.homepageList = homepageList;
	}

	public List<InpatientHomepageAnalyCheck> getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(List<InpatientHomepageAnalyCheck> checkRecord) {
		this.checkRecord = checkRecord;
	}

}
