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
	private int homepageIndex = 1;

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
	private static VIcdTransform[] hisDiagnosisChinese = null;
	private static VIcdTransform[] hisDiagnosisWestern = null;
	private static VIcdTransform[] hisDiagnosisOperation = null;
	private static VIcdTransform[] hisDiagnosisPathology = null;
	private static IcdTransform[] icd20DiagnosisChinese = null;
	private static IcdTransform[] icd20DiagnosisWestern = null;
	private static IcdTransform[] icd20DiagnosisOperation = null;
	private static IcdTransform[] icd20DiagnosisPathology = null;

	public HomepageCheckerServiceImpl() throws Exception {
		homepageList = new ArrayList<InpatientHomepageAnaly>();
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
			if (hisDiagnosisChinese == null) hisDiagnosisChinese = homepageDao.getHISDiagnosis(RecordRangeType.DIAGNOSIS_CHINESE).toArray(new VIcdTransform[0]);
			if (hisDiagnosisWestern == null) hisDiagnosisWestern = homepageDao.getHISDiagnosis(RecordRangeType.DIAGNOSIS_WESTERN).toArray(new VIcdTransform[0]);
			if (hisDiagnosisOperation == null) hisDiagnosisOperation = homepageDao.getHISDiagnosis(RecordRangeType.DIAGNOSIS_OPERATION).toArray(new VIcdTransform[0]);
			if (hisDiagnosisPathology == null) hisDiagnosisPathology = homepageDao.getHISDiagnosis(RecordRangeType.DIAGNOSIS_PATHOLOGY).toArray(new VIcdTransform[0]);
			_status = "ICD2.0";
			if (icd20DiagnosisChinese == null) icd20DiagnosisChinese = homepageDao.getICD20Diagnosis(RecordRangeType.DIAGNOSIS_CHINESE).toArray(new IcdTransform[0]);
			if (icd20DiagnosisWestern == null) icd20DiagnosisWestern = homepageDao.getICD20Diagnosis(RecordRangeType.DIAGNOSIS_WESTERN).toArray(new IcdTransform[0]);
			if (icd20DiagnosisOperation == null) icd20DiagnosisOperation = homepageDao.getICD20Diagnosis(RecordRangeType.DIAGNOSIS_OPERATION).toArray(new IcdTransform[0]);
			if (icd20DiagnosisPathology == null) icd20DiagnosisPathology = homepageDao.getICD20Diagnosis(RecordRangeType.DIAGNOSIS_PATHOLOGY).toArray(new IcdTransform[0]);
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
		homepage.setInpatientHomepageAnalyChecks(new ArrayList<InpatientHomepageAnalyCheck>());
		List<InpatientHomepageAnalyCheck> checkRecord = homepage.getInpatientHomepageAnalyChecks();
		String name = null, value = null;
		int checkIndex = checkRecord.size() + 1;
		try {
			for (Field field : homepage.getClass().getFields()) {
				name = field.getName();
				if (name.equals("id") || name.equals("kind") || name.equals("type") || name.equals("checked") || name.equals("inpatientHomepageAnalyChecks")) continue;
				String method = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				Class<?> type = field.getType();
				value = record.get(name);

				try {
					Object val = transValueType(name, value, type);
					homepage.getClass().getMethod(method, Double.class).invoke(homepage, val);
				}
				catch(Exception ex) {
					checkRecord.add(new InpatientHomepageAnalyCheck(checkIndex ++, homepage.getId(), name, value, ex.getMessage(), "Item"));
				}
			}
			dataVerify(homepage);
		}
		catch(Exception ex) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					name,
					value,
					"在处理第 " + record.getRecordNumber() + " 行" + (name != null ? "[" + name + "=" + (value == null ? "" : value) + "]" : "") + "时发生严重错误 [" + ex.getMessage() + "]",
					"Entry"
				)
			);
		}
		homepage.setChecked(checkRecord.size());
		homepageList.add(homepage);
	}

	@Override
	public Object transValueType(String name, String value, Class<?> type) throws Exception {
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
	public void dataVerify(InpatientHomepageAnaly homepage) throws Exception {
		List<InpatientHomepageAnalyCheck> checkRecord = homepage.getInpatientHomepageAnalyChecks();
		int checkIndex = checkRecord.size() + 1;

		/***************************************************************************************/
		/******* 病案首页数据逻辑检查，依据《三级公立中医医院病案首页数据采集接口标准与质量要求》。 ********/ 
		/***************************************************************************************/
		/*
		 * 1. ZZJGDM	组织机构代码	字符	22	必填	指医疗机构执业许可证上面的机构代码。
		 */
		if (homepage.getZzjgdm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZZJGDM",
					homepage.getZzjgdm(),
					"[ZZJGDM:组织机构代码=" + homepage.getZzjgdm() + "]未填写组织机构代码",
					"Item"
				));
		} else if (homepage.getZzjgdm().length() != 18) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YLFKFS",
					homepage.getYlfkfs(),
					"[YLFKFS:医疗付款方式=" + homepage.getYlfkfs() + "]未填写医疗付款方式",
					"Item"
				));
		} else if (!compareDic(rc032, RecordRangeType.code, homepage.getYlfkfs())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYCS",
					homepage.getZycs(),
					"[ZYCS:住院次数=" + homepage.getZycs() + "]未填写住院次数",
					"Item"
				));
		} else if (!isInteger(homepage.getZycs())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYCS",
					homepage.getZycs(),
					"[ZYCS:住院次数=" + homepage.getZycs() + "]填写不正确，必须是大于0的整数",
					"Item"
				));
		} else if (Integer.parseInt(homepage.getZycs()) <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XB",
					homepage.getXb(),
					"[XB:性别=" + homepage.getXb() + "]未填写性别",
					"Item"
				));
		} else if (!compareDic(rc001, RecordRangeType.code, homepage.getXb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CSRQ",
					homepage.getCsrq(),
					"[CSRQ:出生日期=" + homepage.getCsrq() + "]未填写出生日期",
					"Item"
				));
		} else if (!isDate(homepage.getCsrq())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"NL",
					homepage.getNl(),
					"[NL:年龄（岁）=" + homepage.getNl() + "]未填写",
					"Item"
				));
		} else if (!isInteger(homepage.getNl())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"NL",
					homepage.getNl(),
					"[NL:年龄（岁）=" + homepage.getNl() + "]不是数字(整数)",
					"Item"
				));
		} else if (_age < 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GJ",
					homepage.getGj(),
					"[GJ:国籍=" + homepage.getGj() + "]未填写国籍",
					"Item"
				));
		} else if (!compareDic(rc038, RecordRangeType.code, homepage.getGj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
					checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"BZYZS_NL",
						homepage.getBzyzsNl(),
						"[BZYZS_NL:年龄不足1周岁的年龄（天）=" + homepage.getBzyzsNl() + "]年龄不足1周岁的未填写",
						"Item"
					));
			} else if(!isFloat(homepage.getBzyzsNl())) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CSD",
					homepage.getCsd(),
					"[CSD:出生地=" + homepage.getCsd() + "]未填写出生地",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getCsd())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GG",
					homepage.getGg(),
					"[GG:籍贯=" + homepage.getGg() + "]未填写籍贯",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getGg())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MZ",
					homepage.getMz(),
					"[MZ:民族=" + homepage.getMz() + "]未填写民族",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getMz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SFZH",
					homepage.getSfzh(),
					"[SFZH:身份证号=" + homepage.getSfzh() + "]未填写身份证号",
					"Item"
				));
		} else if (isInteger(homepage.getSfzh()) && homepage.getSfzh().length() != 15 && homepage.getSfzh().length() != 18) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZY",
					homepage.getZy(),
					"[ZY:职业=" + homepage.getZy() + "]未填写职业",
					"Item"
				));
		} else if (!compareDic(rc003, RecordRangeType.code, homepage.getZy())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"HY",
					homepage.getHy(),
					"[HY:婚姻=" + homepage.getHy() + "]未填写婚姻",
					"Item"
				));
		} else if (!compareDic(rc002, RecordRangeType.code, homepage.getHy())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XZZ",
					homepage.getXzz(),
					"[XZZ:现住址=" + homepage.getXzz() + "]未填写现住址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getXzz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"HKDZ",
					homepage.getHkdz(),
					"[HKDZ:户口地址=" + homepage.getHkdz() + "]未填写户口地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getHkdz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GZDWJDZ",
					homepage.getGzdwjdz(),
					"[GZDWJDZ:工作单位及地址=" + homepage.getGzdwjdz() + "]未填写工作单位及地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getGzdwjdz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GX",
					homepage.getGx(),
					"[GX:联系人关系=" + homepage.getGx() + "]未填写联系人关系",
					"Item"
				));
		} else if (!compareDic(rc033, RecordRangeType.code, homepage.getGx())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"DZ",
					homepage.getDz(),
					"[DZ:联系人地址=" + homepage.getDz() + "]未填写联系人地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getDz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYTJ",
					homepage.getRytj(),
					"[RYTJ:入院途径=" + homepage.getRytj() + "]未填写入院途径",
					"Item"
				));
		} else if (!compareDic(rc026, RecordRangeType.code, homepage.getRytj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYSJ",
					homepage.getRysj(),
					"[RYSJ:入院时间=" + homepage.getRysj() + "]未填写入院时间",
					"Item"
					));
		} else if (!isDatetime(homepage.getRysj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYSJ_S",
					homepage.getRysjS(),
					"[RYSJ_S:入院时间（时）=" + homepage.getRysjS() + "]未填写入院时间（时）",
					"Item"
					));
		} else if (!isInteger(homepage.getRysjS())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
							checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"RYSJ_S",
							homepage.getRysjS(),
							"[RYSJ:入院时间=" + homepage.getRysj() + "；RYSJ_S:入院时间（时）=" + homepage.getRysjS() + "]逻辑验证未通过，入院时间不匹配",
							"Item"
							));
				}
			}
		}
		/*
		 * 42. RYKB	入院科别	字符	6	必填	《值域范围参考RC023-科室代码》
		 */
		if (homepage.getRykb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYKB",
					homepage.getRykb(),
					"[RYKB:入院科别=" + homepage.getRykb() + "]未填写入院科别",
					"Item"
				));
		} else if (!compareDic(rc023, RecordRangeType.code, homepage.getRykb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
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
							checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"ZKKB",
							homepage.getZkkb(),
							"[ZKKB:转科科别=" + homepage.getZkkb() + "]" + _zkkb + "填写不正确，《值域范围参考RC023-科室代码》",
							"Item"
						));
				}
			}
		}
		/*
		 * 45. CYSJ	出院时间	日期		必填	格式 yyyy-MM-dd HH:mm:ss；入院时间不能晚于出院时间
		 */
		if (homepage.getCysj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ",
					homepage.getCysj(),
					"[CYSJ:出院时间=" + homepage.getCysj() + "]未填写出院时间",
					"Item"
					));
		} else if (!isDatetime(homepage.getCysj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ",
					homepage.getCysj(),
					"[CYSJ:出院时间=" + homepage.getCysj() + "]填写不正确，格式 yyyy-MM-dd HH:mm:ss",
					"Item"
					));
		}
		// 出入院时间逻辑校验
		if (isDatetime(homepage.getRysj())
			&& isDatetime(homepage.getCysj())
			&& FormatUtil.parseDateTime(homepage.getRysj()).getTime() > FormatUtil.parseDateTime(homepage.getCysj()).getTime()) {

			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ",
					homepage.getCysj(),
					"[CYSJ:出院时间=" + homepage.getCysj() + "；RYSJ:入院时间=" + homepage.getRysj() + "]逻辑验证未通过，出院时间小于入院时间",
					"Item"
					));
		}
		/*
		 * 46. CYSJ_S	时			必填
		 */
		if (homepage.getCysjS().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ_S",
					homepage.getCysjS(),
					"[CYSJ_S:出院时间（时）=" + homepage.getCysjS() + "]未填写出院时间（时）",
					"Item"
					));
		} else if (!isInteger(homepage.getCysjS())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ_S",
					homepage.getCysjS(),
					"[CYSJ_S:出院时间（时）=" + homepage.getCysjS() + "]填写不正确，小时数（24小时制）",
					"Item"
					));
		} else {
			if (isDatetime(homepage.getCysj())) {
				Date _cysj = FormatUtil.parseDate(homepage.getCysj());
				Calendar _cysjCal = Calendar.getInstance();
				_cysjCal.setTime(_cysj);
				int _hour = _cysjCal.get(Calendar.HOUR);
				if (_hour != Integer.parseInt(homepage.getCysjS())) {
							checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"CYSJ_S",
							homepage.getCysjS(),
							"[CYSJ:出院时间=" + homepage.getCysj() + "；CYSJ_S:出院时间（时）=" + homepage.getCysjS() + "]逻辑验证未通过，出院时间不匹配",
							"Item"
							));
				}
			}
		}
		/*
		 * 47. CYKB	出院科别	字符	6	必填	《值域范围参考RC023-科室代码》；国家重点专科科室必须有数据。
		 */
		if (homepage.getCykb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYKB",
					homepage.getCykb(),
					"[CYKB:出院科别=" + homepage.getCykb() + "]未填写出院科别",
					"Item"
				));
		} else if (!compareDic(rc023, RecordRangeType.code, homepage.getCykb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYKB",
					homepage.getCykb(),
					"[CYKB:出院科别=" + homepage.getCykb() + "]填写不正确，《值域范围参考RC023-科室代码》",
					"Item"
				));
		}
		/*
		 * 48. CYBF	出院病房	字符	30	必填
		 */
		if (homepage.getCybf().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYBF",
					homepage.getCybf(),
					"[CYBF:出院病房=" + homepage.getCybf() + "]未填写出院病房",
					"Item"
				));
		}
		/*
		 * 49. SJZY	实际住院(天)	数字	6	必填	大于0整数。入院时间与出院时间只计算一天，例如：2018年6月12日入院，2018年6月15日出院，计住院天数为3天
		 */
		if (homepage.getSjzy().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SJZY",
					homepage.getSjzy(),
					"[SJZY:实际住院(天)=" + homepage.getSjzy() + "]未填写实际住院(天)",
					"Item"
				));
		} else if (!isInteger(homepage.getSjzy()) || Integer.parseInt(homepage.getSjzy()) <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SJZY",
					homepage.getSjzy(),
					"[SJZY:实际住院(天)=" + homepage.getSjzy() + "]填写不正确，必填大于0整数",
					"Item"
				));
		} else if (isDatetime(homepage.getCysj()) && isDatetime(homepage.getRysj())) {
			long _cysj = FormatUtil.parseDateTime(homepage.getCysj()).getTime();
			long _rysj = FormatUtil.parseDateTime(homepage.getRysj()).getTime();
			int _sjzy = (int) (_cysj - _rysj);
			_sjzy = _sjzy == 0 ? 1 : _sjzy;
			if (_sjzy > 0 && _sjzy != Integer.parseInt(homepage.getSjzy())) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"SJZY",
						homepage.getSjzy(),
						"[SJZY:实际住院(天)=" + homepage.getSjzy() + "]逻辑验证未通过，与出入院时间不符[" + _sjzy + "]",
						"Item"
					));
			}
		}
		/*
		 * 50. MZD_ZYZD	门(急)诊诊断(中医诊断)	字符	100	必填	采用中医病症分类代码国标版95（TCM95）与编码对应的病诊断名称
		 */
		if (homepage.getMzdZyzd().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MZD_ZYZD",
					homepage.getMzdZyzd(),
					"[MZD_ZYZD:门(急)诊诊断(中医诊断)=" + homepage.getMzdZyzd() + "]未填写门(急)诊诊断(中医诊断)",
					"Item"
				));
		}
		/*
		 * 51. JBDM	门(急)诊诊断疾病代码(中医诊断)	字符	20	必填	采用中医病症分类代码国标版95（TCM95）
		 */
		if (homepage.getJbdm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JBDM",
					homepage.getJbdm(),
					"[JBDM:门(急)诊诊断疾病代码(中医诊断)=" + homepage.getJbdm() + "]未填写门(急)诊诊断疾病代码(中医诊断)",
					"Item"
				));
		}
		// 诊断逻辑校验
		if (!homepage.getJbdm().isEmpty()
			&& !homepage.getMzdZyzd().isEmpty()
			&& !diagnosisVerify(homepage.getJbdm(), homepage.getMzdZyzd(), RecordRangeType.DIAGNOSIS_CHINESE)) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JBDM",
					homepage.getJbdm(),
					"[MZD_ZYZD:门(急)诊诊断(中医诊断)=" + homepage.getMzdZyzd() + "；JBDM:门(急)诊诊断疾病代码(中医诊断)=" + homepage.getJbdm() + "]逻辑验证未通过，与《中医病症分类代码国标版95（TCM95）》不匹配",
					"Item"
				));
		}
		/*
		 * 52. MZZD_XYZD	门（急）诊诊断名称(西医诊断)	字符	100	必填	采用疾病分类代码国家临床版2.0(ICD-10)与编码对应的诊断名称
		 */
		if (homepage.getMzzdXyzd().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MZZD_XYZD",
					homepage.getMzzdXyzd(),
					"[MZZD_XYZD:门（急）诊诊断名称(西医诊断)=" + homepage.getMzzdXyzd() + "]未填写门（急）诊诊断名称(西医诊断)",
					"Item"
				));
		}
		/*
		 * 53. JBBM	门（急）诊诊断编码(西医诊断)	字符	20	必填	采用疾病分类代码国家临床版2.0编码（ICD-10）
		 */
		if (homepage.getJbbm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JBBM",
					homepage.getJbbm(),
					"[JBBM:门（急）诊诊断编码(西医诊断)=" + homepage.getJbbm() + "]未填写门（急）诊诊断编码(西医诊断)",
					"Item"
				));
		}
		// 诊断逻辑校验
		if (!homepage.getJbbm().isEmpty()
			&& !homepage.getMzzdXyzd().isEmpty()
			&& !diagnosisVerify(homepage.getJbbm(), homepage.getMzzdXyzd(), RecordRangeType.DIAGNOSIS_WESTERN)) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JBBM",
					homepage.getJbbm(),
					"[MZZD_XYZD:门（急）诊诊断名称(西医诊断)=" + homepage.getMzzdXyzd() + "；JBBM:门（急）诊诊断编码(西医诊断)=" + homepage.getJbbm() + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0编码（ICD-10）》不匹配",
					"Item"
				));
		}
		/*
		 * 54. SSLCLJ	实施临床路径	字符	1	必填	《值域范围参考RC040-实施临床路径字典》
		 */
		if (homepage.getSslclj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SSLCLJ",
					homepage.getSslclj(),
					"[SSLCLJ:实施临床路径=" + homepage.getSslclj() + "]未填写实施临床路径",
					"Item"
				));
		} else if (!compareDic(rc040, RecordRangeType.code, homepage.getSslclj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SSLCLJ",
					homepage.getSslclj(),
					"[SSLCLJ:实施临床路径=" + homepage.getSslclj() + "]填写不正确，《值域范围参考RC040-实施临床路径字典》",
					"Item"
				));
		}
		/*
		 * 55. ZYYJ	使用医疗机构中药制剂	字符	1	必填	《值域范围参考RC016》；参照卫统4-2病案首页值域；当值为"是"时，医疗机构中药制剂费>0
		 */
		if (homepage.getZyyj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYJ",
					homepage.getZyyj(),
					"[ZYYJ:使用医疗机构中药制剂=" + homepage.getZyyj() + "]未填写使用医疗机构中药制剂",
					"Item"
				));
		} else if (!compareDic(rc016, RecordRangeType.code, homepage.getZyyj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYJ",
					homepage.getZyyj(),
					"[ZYYJ:使用医疗机构中药制剂=" + homepage.getZyyj() + "]填写不正确，《值域范围参考RC016》",
					"Item"
				));
		}
		// 诊断逻辑校验
		if (!homepage.getZyyj().isEmpty() && compareDic(rc016, RecordRangeType.code, homepage.getZyyj())) {
			double _zyzjf = homepage.getZyzjf() == null ? 0 : homepage.getZyzjf();
			if (homepage.getZyyj().equals("1") && _zyzjf <= 0) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYJ",
					homepage.getZyyj(),
					"[ZYYJ:使用医疗机构中药制剂=" + homepage.getZyyj() + "；ZYZJF:中药制剂费=" + _zyzjf + "]逻辑验证未通过，当值为\"是\"时，医疗机构中药制剂费>0",
					"Item"
				));
			} else if (_zyzjf > 0 && !homepage.getZyyj().equals("1")) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYJ",
					homepage.getZyyj(),
					"[ZYYJ:使用医疗机构中药制剂=" + homepage.getZyyj() + "；ZYZJF:中药制剂费=" + _zyzjf + "]逻辑验证未通过，医疗机构中药制剂费>0，值应为\"是\"",
					"Item"
				));
			}
		}
		/*
		 * 56. ZYZLSB	使用中医诊疗设备	字符	1	必填	《值域范围参考RC016》；参照卫统4-2病案首页值域
		 */
		if (homepage.getZyzlsb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZLSB",
					homepage.getZyzlsb(),
					"[ZYZLSB:使用中医诊疗设备=" + homepage.getZyzlsb() + "]未填写使用中医诊疗设备",
					"Item"
				));
		} else if (!compareDic(rc016, RecordRangeType.code, homepage.getZyzlsb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZLSB",
					homepage.getZyzlsb(),
					"[ZYZLSB:使用中医诊疗设备=" + homepage.getZyzlsb() + "]填写不正确，《值域范围参考RC016》",
					"Item"
				));
		}
		/*
		 * 57. ZYZLJS	使用中医诊疗技术	字符	1	必填	《值域范围参考RC016》；参照卫统4-2病案首页值域；中医类（中医和民族医医疗服务）费>0
		 */
		if (homepage.getZyzljs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZLJS",
					homepage.getZyzljs(),
					"[ZYZLJS:使用中医诊疗技术=" + homepage.getZyzljs() + "]未填写使用中医诊疗技术",
					"Item"
				));
		} else if (!compareDic(rc016, RecordRangeType.code, homepage.getZyzljs())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZLJS",
					homepage.getZyzljs(),
					"[ZYZLJS:使用中医诊疗技术=" + homepage.getZyzljs() + "]填写不正确，《值域范围参考RC016》",
					"Item"
				));
		}
		/*
		 * 58. BZSH	辩证施护	字符	1	必填	《值域范围参考RC016》；参照卫统4-2病案首页值域
		 */
		if (homepage.getBzsh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BZSH",
					homepage.getBzsh(),
					"[BZSH:辩证施护=" + homepage.getBzsh() + "]未填写辩证施护",
					"Item"
				));
		} else if (!compareDic(rc016, RecordRangeType.code, homepage.getBzsh())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BZSH",
					homepage.getBzsh(),
					"[BZSH:辩证施护=" + homepage.getBzsh() + "]填写不正确，《值域范围参考RC016》",
					"Item"
				));
		}
		/*
		 * 59. ZB	主病出院中医诊断	字符	100	必填	采用中医病症分类代码国标版95（TCM95）与编码对应的病诊断名称
		 */
		if (homepage.getZb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB",
					homepage.getZb(),
					"[ZB:主病出院中医诊断=" + homepage.getZb() + "]未填写主病出院中医诊断",
					"Item"
				));
		}
		/*
		 * 60. ZB_JBBM	主病疾病编码	字符	20	必填	采用中医病症分类代码国标版95（TCM95）
		 */
		if (homepage.getZbJbbm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB_JBBM",
					homepage.getZbJbbm(),
					"[ZB_JBBM:主病疾病编码=" + homepage.getZbJbbm() + "]未填写主病疾病编码",
					"Item"
				));
		}
		// 诊断逻辑校验
		if (!homepage.getZbJbbm().isEmpty()
			&& !homepage.getZb().isEmpty()
			&& !diagnosisVerify(homepage.getZbJbbm(), homepage.getMzdZyzd(), RecordRangeType.DIAGNOSIS_CHINESE)) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB_JBBM",
					homepage.getZbJbbm(),
					"[ZB:主病出院中医诊断=" + homepage.getZb() + "；ZB_JBBM:主病疾病编码=" + homepage.getZbJbbm() + "]逻辑验证未通过，与《中医病症分类代码国标版95（TCM95）》不匹配",
					"Item"
				));
		}
		/*
		 * 61. ZB_RYBQ	主病入院病情	字符	1	必填	《值域范围参考RC027-入院病情》
		 */
		if (homepage.getZbRybq().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB_RYBQ",
					homepage.getZbRybq(),
					"[ZB_RYBQ:主病入院病情=" + homepage.getZbRybq() + "]未填写主病入院病情",
					"Item"
				));
		} else if (!compareDic(rc027, RecordRangeType.code, homepage.getZbRybq())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB_RYBQ",
					homepage.getZbRybq(),
					"[ZB_RYBQ:主病入院病情=" + homepage.getZbRybq() + "]填写不正确，《值域范围参考RC027-入院病情》",
					"Item"
				));
		}
		/*
		 * 62. ZYZD	出院主要诊断名称(西医)	字符	100	必填	采用疾病分类代码国家临床版2.0(ICD-10)与编码对应的诊断名称
		 */
		if (homepage.getZyzd().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZD",
					homepage.getZyzd(),
					"[ZYZD:出院主要诊断名称(西医)=" + homepage.getZyzd() + "]未填写出院主要诊断名称(西医)",
					"Item"
				));
		}
		/*
		 * 63. ZYZD_JBBM	出院主要诊断编码(西医)	字符	20	必填	采用疾病分类代码国家临床版2.0编码（ICD-10）
		 */
		if (homepage.getZyzdJbbm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZD_JBBM",
					homepage.getZyzdJbbm(),
					"[ZYZD_JBBM:出院主要诊断编码(西医)=" + homepage.getZyzdJbbm() + "]未填写出院主要诊断编码(西医)",
					"Item"
				));
		}
		// 诊断逻辑校验
		if (!homepage.getZyzdJbbm().isEmpty()
			&& !homepage.getZyzd().isEmpty()
			&& !diagnosisVerify(homepage.getZyzdJbbm(), homepage.getMzdZyzd(), RecordRangeType.DIAGNOSIS_CHINESE)) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZD_JBBM",
					homepage.getZyzdJbbm(),
					"[ZYZD:出院主要诊断名称(西医)=" + homepage.getZyzd() + "；ZYZD_JBBM:出院主要诊断编码(西医)=" + homepage.getZyzdJbbm() + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0编码（ICD-10）》不匹配",
					"Item"
				));
		}
		/*
		 * 64. XY_RYBQ	出院主要诊断入院病情(西医)	字符	1	必填	《值域范围参考RC027-入院病情》
		 */
		if (homepage.getXyRybq().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XY_RYBQ",
					homepage.getXyRybq(),
					"[XY_RYBQ:出院主要诊断入院病情(西医)=" + homepage.getXyRybq() + "]未填写出院主要诊断入院病情(西医)",
					"Item"
				));
		} else if (!compareDic(rc027, RecordRangeType.code, homepage.getXyRybq())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XY_RYBQ",
					homepage.getXyRybq(),
					"[XY_RYBQ:出院主要诊断入院病情(西医)=" + homepage.getXyRybq() + "]填写不正确，《值域范围参考RC027-入院病情》",
					"Item"
				));
		}
		/*
		 * 65. ZZ1-ZZ7	主证出院中医诊断	字符	100	必填	最多采集7条；采用中医病症分类代码国标版95（TCM95）与编码对应的病诊断名称
		 * 66. ZZ_JBBM1-ZZ_JBBM7	主证疾病编码	字符	20	必填	最多采集7条；采用中医病症分类代码国标版95（TCM95）
		 * 67. ZZ_RYBQ1-ZZ_RYBQ7	主证住入院病情	字符	1	必填	最多采集7条；《值域范围参考RC027-入院病情》
		 */
		for (int i = 1; i <= 7; i ++) {
			String zzValue = homepage.getClass().getMethod("getZz" + i, new Class[0])
							 .invoke(homepage, new Object[0]).toString();
			String jbbmValue = homepage.getClass().getMethod("getZzJbbm" + i, new Class[0])
							   .invoke(homepage, new Object[0]).toString();
			String rybqValue = homepage.getClass().getMethod("getZzRybq" + i, new Class[0])
							   .invoke(homepage, new Object[0]).toString();
			if (!zzValue.isEmpty() || !jbbmValue.isEmpty() || !rybqValue.isEmpty()) {
				if (!diagnosisVerify(jbbmValue, zzValue, RecordRangeType.DIAGNOSIS_CHINESE)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"ZZ_JBBM" + i,
							jbbmValue,
							"[ZZ_JBBM" + i + ":主证疾病编码" + i + "=" + jbbmValue + "；ZZ" + i + ":主证出院中医诊断" + i + "=" + zzValue + "]逻辑验证未通过，与《中医病症分类代码国标版95（TCM95）》不匹配",
							"Item"
						));
				}
				if (rybqValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"ZZ_RYBQ" + i,
							rybqValue,
							"[ZZ_RYBQ" + i + ":主证住入院病情" + i + "=" + rybqValue + "]未填写主证住入院病情" + i,
							"Item"
						));
				} else if (!compareDic(rc027, RecordRangeType.code, rybqValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"ZZ_RYBQ" + i,
							rybqValue,
							"[ZZ_RYBQ" + i + ":主证住入院病情" + i + "=" + rybqValue + "]填写不正确，《值域范围参考RC027-入院病情》",
							"Item"
						));
				}
			}
		}
		/*
		 * 68. QTZD1-QTZD40	出院其他诊断名称(西医)	字符	100		最多收集40条；采用疾病分类代码国家临床版2.0(ICD-10)与编码对应的诊断名称
		 * 69. ZYZD_JBBM1-ZYZD_JBBM40	出院其他诊断编码(西医)	字符	20		最多收集40条；采用疾病分类代码国家临床版2.0编码（ICD-10）
		 * 70. RYBQ1-RYBQ40	出院其他诊断入院病情(西医)	字符	1		《值域范围参考RC027-入院病情》
		 */
		for (int i = 1; i <= 40; i ++) {
			String qtzdValue = homepage.getClass().getMethod("getQtzd" + i, new Class[0])
							 .invoke(homepage, new Object[0]).toString();
			String jbbmValue = homepage.getClass().getMethod("getZyzdJbbm" + i, new Class[0])
							   .invoke(homepage, new Object[0]).toString();
			String rybqValue = homepage.getClass().getMethod("getRybq" + i, new Class[0])
							   .invoke(homepage, new Object[0]).toString();
			if (!qtzdValue.isEmpty() || !jbbmValue.isEmpty() || !rybqValue.isEmpty()) {
				if (!diagnosisVerify(jbbmValue, qtzdValue, RecordRangeType.DIAGNOSIS_CHINESE)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"ZYZD_JBBM" + i,
							jbbmValue,
							"[ZYZD_JBBM" + i + ":出院其他诊断编码(西医)" + i + "=" + jbbmValue + "；QTZD" + i + ":出院其他诊断名称(西医)" + i + "=" + qtzdValue + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0(ICD-10)》不匹配",
							"Item"
						));
				}
				if (rybqValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"RYBQ" + i,
							rybqValue,
							"[RYBQ" + i + ":出院其他诊断入院病情(西医)" + i + "=" + rybqValue + "]未填写出院其他诊断入院病情(西医)" + i,
							"Item"
						));
				} else if (!compareDic(rc027, RecordRangeType.code, rybqValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"RYBQ" + i,
							rybqValue,
							"[RYBQ" + i + ":出院其他诊断入院病情(西医)" + i + "=" + rybqValue + "]填写不正确，《值域范围参考RC027-入院病情》",
							"Item"
						));
				}
			}
		}
		/*
		 * 71. WBYY	损伤、中毒外部原因名称	字符	100	条件必填	采用疾病分类代码国家临床版2.0(ICD-10)编码对应的外部原因名称；主要诊断ICD编码首字母为S或T时必填
		 * 72. JBBM1	损伤、中毒外部原因编码	字符	20	条件必填	采用疾病分类代码国家临床版2.0的编码(ICD-10)，主要诊断ICD编码首字母为S或T时必填
		 */
		if ((!homepage.getWbyy().isEmpty() || !homepage.getJbbm1().isEmpty()) && !diagnosisVerify(homepage.getJbbm1(), homepage.getWbyy(), RecordRangeType.DIAGNOSIS_WESTERN)) {
			if (homepage.getWbyy().isEmpty()) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"JBBM1",
						homepage.getJbbm1(),
						"[JBBM1:损伤、中毒外部原因编码=" + homepage.getJbbm1() + "；WBYY:损伤、中毒外部原因名称=" + homepage.getWbyy() + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0(ICD-10)》不匹配",
						"Item"
					));
			}
		}
		if (!homepage.getZyzdJbbm().isEmpty()
			&& (homepage.getZyzdJbbm().charAt(0) == 'S' || homepage.getZyzdJbbm().charAt(0) == 'T')
			&& (homepage.getWbyy().isEmpty() || homepage.getJbbm1().isEmpty())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JBBM1",
					homepage.getJbbm1(),
					"[JBBM1:损伤、中毒外部原因编码=" + homepage.getJbbm1() + "；WBYY:损伤、中毒外部原因名称=" + homepage.getWbyy() + "]逻辑验证未通过，主要诊断(" + homepage.getZyzdJbbm() + ")ICD编码首字母为S或T时必填",
					"Item"
				));
		}
		/*
		 * 73. BLZD	病理诊断名称	字符	100	条件必填	采用疾病分类代码国家临床版2.0版肿瘤形态学编码(M码)与编码对应的病理名称；主要诊断ICD编码首字母为C或D00-D48时必填
		 * 74. JBBM2	病理诊断编码	字符	20	条件必填	采用疾病分类代码国家临床版2.0版肿瘤形态学编码(M码)；主要诊断ICD编码首字母为C或D00-D48时必填
		 */
		if ((!homepage.getBlzd().isEmpty() || !homepage.getJbbm2().isEmpty()) && !diagnosisVerify(homepage.getJbbm2(), homepage.getBlzd(), RecordRangeType.DIAGNOSIS_PATHOLOGY)) {
			if (homepage.getWbyy().isEmpty()) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"JBBM2",
						homepage.getJbbm2(),
						"[JBBM2:病理诊断编码=" + homepage.getJbbm2() + "；BLZD:病理诊断名称=" + homepage.getBlzd() + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0(ICD-10)》不匹配",
						"Item"
					));
			}
		}
		if (!homepage.getZyzdJbbm().isEmpty() && homepage.getZyzdJbbm().length() >= 3) {
			int diagnoCode = Integer.parseInt(homepage.getZyzdJbbm().substring(1, 3));
			if ((homepage.getZyzdJbbm().charAt(0) == 'C' || (diagnoCode >= 0 && diagnoCode <= 48))
				&& homepage.getJbbm2().isEmpty()) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"JBBM2",
						homepage.getJbbm2(),
						"[JBBM2:病理诊断编码=" + homepage.getJbbm2() + "；BLZD:病理诊断名称=" + homepage.getBlzd() + "]逻辑验证未通过，主要诊断(" + homepage.getZyzdJbbm() + ")ICD编码首字母为C或D00-D48时必填",
						"Item"
					));
			}
		}
		/*
		 * 75. BLH	病理号	字符	50	条件必填	有病理诊断编码时必填
		 */
		if (!homepage.getJbbm2().isEmpty() && homepage.getBlh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BLH",
					homepage.getBlh(),
					"[BLH:病理号=" + homepage.getBlh() + "]逻辑验证未通过，有病理诊断编码(" + homepage.getJbbm2() + ")时必填",
					"Item"
				));
		}
		/*
		 * 76. YWGM	有无药物过敏	字符	1	必填	《值域范围参考RC028-药物过敏》
		 */
		if (homepage.getYwgm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YWGM",
					homepage.getYwgm(),
					"[YWGM:有无药物过敏=" + homepage.getYwgm() + "]未填写有无药物过敏",
					"Item"
				));
		} else if (!compareDic(rc028, RecordRangeType.code, homepage.getYwgm())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YWGM",
					homepage.getYwgm(),
					"[YWGM:有无药物过敏=" + homepage.getYwgm() + "]填写不正确，《值域范围参考RC028-药物过敏》",
					"Item"
				));
		}
		/*
		 * 77. GMYW	过敏药物名称	字符	200	条件必填	“有无药物过敏”为“有”时必填；多种药物用英文逗号进行分隔
		 */
		if (!homepage.getYwgm().isEmpty() && homepage.getYwgm().equals("2") && homepage.getGmyw().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GMYW",
					homepage.getGmyw(),
					"[GMYW:过敏药物名称=" + homepage.getGmyw() + "；YWGM:有无药物过敏=" + homepage.getYwgm() + "]逻辑验证未通过，“有无药物过敏”为“有”时必填；多种药物用英文逗号进行分隔",
					"Item"
				));
		}
		/*
		 * 78. SJ	死亡患者尸检	字符	1	条件必填	《值域范围参考RC016-是否》；当离院方式为5死亡时，尸检为“1”或“2”。
		 */
		if (!homepage.getLyfs().isEmpty() && homepage.getLyfs().equals("5") && (homepage.getSj().isEmpty() || !compareDic(rc016, RecordRangeType.code, homepage.getSj()))) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SJ",
					homepage.getSj(),
					"[SJ:死亡患者尸检=" + homepage.getSj() + "]逻辑验证未通过，当离院方式为5死亡时，尸检为“1”或“2”",
					"Item"
				));
		}
		/*
		 * 79. XX	ABO血型	字符	1	必填	《值域范围参考RC030-血型编码》
		 */
		if (homepage.getXx().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XX",
					homepage.getXx(),
					"[XX:ABO血型=" + homepage.getXx() + "]未填写ABO血型",
					"Item"
				));
		} else if (!compareDic(rc030, RecordRangeType.code, homepage.getXx())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XX",
					homepage.getXx(),
					"[XX:ABO血型=" + homepage.getXx() + "]填写不正确，《值域范围参考RC030-血型编码》",
					"Item"
				));
		}
		/*
		 * 80. RH	Rh血型	字符	1	必填	《值域范围参考RC031-Rh血型》；当血费>0时，RH血型值为字典值。
		 */
		if (homepage.getRh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RH",
					homepage.getRh(),
					"[RH:Rh血型=" + homepage.getRh() + "]未填写Rh血型",
					"Item"
				));
		} else if (!compareDic(rc031, RecordRangeType.code, homepage.getXx())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RH",
					homepage.getRh(),
					"[RH:Rh血型=" + homepage.getRh() + "]填写不正确，《值域范围参考RC031-Rh血型》",
					"Item"
				));
		}
		if (homepage.getXf() != null && homepage.getXf() > 0 && !homepage.getRh().equals("1") && !homepage.getRh().equals("2")) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RH",
					homepage.getRh(),
					"[RH:Rh血型=" + homepage.getRh() + "]逻辑验证未通过，当血费>0时，RH血型值为“1”或“2”",
					"Item"
				));
		}
		/*
		 * 81. KZR	科主任	字符	40	必填
		 */
		if (homepage.getKzr().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"KZR",
					homepage.getKzr(),
					"[KZR:科主任=" + homepage.getKzr() + "]未填写科主任",
					"Item"
				));
		}
		/*
		 * 82. ZRYS	主任（副主任）医师	字符	40	必填
		 */
		if (homepage.getZrys().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZRYS",
					homepage.getZrys(),
					"[ZRYS:主任（副主任）医师=" + homepage.getZrys() + "]未填写主任（副主任）医师",
					"Item"
				));
		}
		/*
		 * 83. ZZYS	主治医师	字符	40	必填
		 */
		if (homepage.getZzys().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZZYS",
					homepage.getZzys(),
					"[ZZYS:主治医师=" + homepage.getZzys() + "]未填写主治医师",
					"Item"
				));
		}
		/*
		 * 84. ZYYS	住院医师	字符	40	必填
		 */
		if (homepage.getZyys().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYS",
					homepage.getZyys(),
					"[ZYYS:住院医师=" + homepage.getZyys() + "]未填写住院医师",
					"Item"
				));
		}
		/*
		 * 85. ZRHS	责任护士	字符	40	必填
		 */
		if (homepage.getZrhs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZRHS",
					homepage.getZrhs(),
					"[ZRHS:责任护士=" + homepage.getZrhs() + "]未填写责任护士",
					"Item"
				));
		}
		/********* 不验证 **********
		 * 86. JXYS	进修医师	字符	40
		 * 87. SXYS	实习医师	字符	40
		 **************************/
		/*
		 * 88. BMY	编码员	字符	40	必填
		 */
		if (homepage.getBmy().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BMY",
					homepage.getBmy(),
					"[BMY:编码员=" + homepage.getBmy() + "]未填写编码员",
					"Item"
				));
		}
		/*
		 * 89. BAZL	病案质量	字符	1		《值域范围参考RC011-病案质量》
		 */
		if (!homepage.getBazl().isEmpty() && !compareDic(rc011, RecordRangeType.code, homepage.getBazl())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BAZL",
					homepage.getBazl(),
					"[BAZL:病案质量=" + homepage.getBazl() + "]填写不正确，《值域范围参考RC011-病案质量》",
					"Item"
				));
		}
		/********* 不验证 **********
		 * 90. ZKYS	质控医师	字符	40
		 * 91. ZKHS	质控护士	字符	40
		 **************************/
		/*
		 * 92. ZKRQ	质控日期	日期			格式 yyyy-MM-dd
		 */
		if (!homepage.getZkrq().isEmpty() && !isDate(homepage.getZkrq())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZKRQ",
					homepage.getZkrq(),
					"[ZKRQ:质控日期=" + homepage.getZkrq() + "]填写不正确，格式yyyy-MM-dd",
					"Item"
				));
		}
		/*
		 * 93. SSJCZBM1	主要手术操作编码	字符	20	必填	手术操作名称第一行为“主要手术操作”，采用手术操作分类代码国家临床版2.0编码（ICD-9-CM3）
		 * 94. SSJCZMC1	主要手术操作名称	字符	100	必填	手术操作名称第一行为“主要手术操作”，采用手术操作分类代码国家临床版2.0（ICD-9-CM3）编码对应的名称
		 * 95. SSJCZRQ1	主要手术操作日期	日期		必填	格式 yyyy-MM-dd HH:mm:ss
		 * 96. SHJB1	主要手术操作级别	字符	1	条件必填	手术及操作编码属性为手术或介入治疗代码时必填。《值域范围参考RC029》
		 * 97. SZ1	主要手术操作术者	字符	40	条件必填	手术及操作编码属性为手术或介入治疗代码时必填
		 * 98. YZ1	主要手术操作Ⅰ助	字符	40	条件必填	手术及操作编码属性为手术或介入治疗代码时必填
		 * 99. EZ1	主要手术操作Ⅱ助	字符	40	条件必填	手术及操作编码属性为手术或介入治疗代码时必填
		 * 100. QKDJ1	主要手术操作切口愈合等级	字符	2	条件必填	手术编码属性为手术时必填，《值域范围参考RC014-切开愈合等级》
		 * 101. QKYLB1	主要手术操作切口愈合类别	字符	2	条件必填	手术编码属性为手术时必填，《值域范围参考RC014-切开愈合等级》
		 * 102. MZFS1	主要手术操作麻醉方式	字符	6	条件必填	手术编码属性为手术时必填，《值域范围参考RC013-麻醉方式》
		 * 103. MZYS1	主要手术操作麻醉医师	字符	40	条件必填	手术及操作编码属性为手术时必填
		 */
		/*
		 * 104. SSJCZBM2-SSJCZBM41	其他手术操作编码	字符	20		最多收集40条；采用手术操作分类代码国家临床版2.0编码（ICD-9-CM3）
		 * 105. SSJCZMC2-SSJCZMC41	其他手术操作名称	字符	100		最多收集40条；采用手术操作分类代码国家临床版2.0（ICD-9-CM3）编码对应的名称
		 * 106. SSJCZRQ2-SSJCZRQ41	其他手术操作日期	日期			最多收集40条，格式 yyyy-MM-dd HH:mm:ss
		 * 107. SHJB2-SHJB41	其他手术操作级别	字符	1		最多收集40条，手术及操作编码属性为手术或介入治疗代码时必填。《值域范围参考RC029-手术级别》
		 * 108. SZ2-SZ41	其他手术操作术者	字符	40		最多收集40条，手术及操作编码属性为手术或介入治疗代码时必填
		 * 109. YZ2-YZ41	其他手术操作Ⅰ助	字符	40		最多收集40条，手术及操作编码属性为手术或介入治疗代码时必填
		 * 110. EZ2-EZ41	其他手术操作Ⅱ助	字符	40		最多收集40条，手术及操作编码属性为手术或介入治疗代码时必填
		 * 111. QKDJ2-QKDJ41	其他手术操作切口愈合等级	字符	2		最多收集40条，手术编码属性为手术时必填，《值域范围参考RC014-切开愈合等级》
		 * 112. QKYLB2-QKYLB41	切口愈合类别	字符	2		最多收集40条，手术编码属性为手术时必填，《值域范围参考RC014-切开愈合等级》
		 * 113. MZFS2-MZFS41	其他手术操作麻醉方式	字符	6		最多收集40条，手术编码属性为手术时必填，《值域范围参考RC013-麻醉方式》
		 * 114. MZYS2-MZYS41	其他手术操作麻醉医师	字符	40		最多收集40条，手术及操作编码属性为手术时必填
		 */
		/*
		 * 115. LYFS	离院方式	字符	1	必填	《值域范围参考RC019》；指患者本次住院出院的方式，填写相应的阿拉伯数字
		 * 116. YZZY_JGMC	医嘱转院，拟接收医疗机构名称	字符	100	条件必填	离院方式为医嘱转院患者必填
		 * 117. WSY_JGMC	医嘱转社区卫生服务机构/乡镇卫生院，拟接收医疗机构名称	字符	100	条件必填	离院方式为医嘱转社区患者必填
		 */
		/*
		 * 118. ZZYJH	是否有出院31天内再住院计划	数字	1	必填	《值域范围参考RC016》，指患者本次住院出院后31天内是否有诊疗需要的再住院安排。如果有再住院计划，则需要填写目的，如：进行二次手术
		 * 119. MD	目的	字符	100	条件必填	是否有出院31日内再住院计划填“有”时必填
		 */
		/*
		 * 120. RYQ_T	颅脑损伤患者昏迷入院前时间（天)	数字	5	必填	大于等于0整数。
		 * 121. RYQ_XS	小时	数字	2	必填	大于等于0，小于24整数。
		 * 122. RYQ_FZ	分钟	数字	2	必填	大于等于0，小于60整数。
		 */
		/*
		 * 123. RYH_T	颅脑损伤患者昏迷时间天	数字	5	必填	大于等于0整数。
		 * 124. RYH_XS	小时	数字	2	必填	大于等于0，小于24整数。
		 * 125. RYH_FZ	分钟	数字	2	必填	大于等于0，小于60整数。
		 */
		/*
		 * 126. ZFY	总费用	数字	(10,2)	必填	住院总费用必填且大于0；总费用大于或等于分项费用之和；
		 * 127. ZFJE	自付金额	数字	(10,2)	必填	小于等于总费用
		 * 128. YLFWF	综合医疗服务类(1)一般医疗服务费	数字	(10,2)	必填	住院天数>1时，(1)一般医疗服务费>0。
		 * 129. BZLZF	中医辨证论治费	数字	(10,2)	条件必填	发生辨证论治费时，必填。小于总费用。
		 * 130. ZYBLZHZF	中医辨证论治会诊费	数字	(10,2)	条件必填	发生辨证论治会诊费时，必填。小于总费用。
		 * 131. ZLCZF	(2)一般治疗操作费	数字	(10,2)
		 * 132. HLF	(3)护理费	数字	(10,2)		住院天数>1时，(3)护理费>0。
		 * 133. QTFY	(4)其他费用	数字	(10,2)
		 * 134. BLZDF	诊断类(5)病理诊断费	数字	(10,2)
		 * 135. ZDF	(6)实验室诊断费	数字	(10,2)
		 * 136. YXXZDF	(7)影像学诊断费	数字	(10,2)
		 * 137. LCZDXMF	(8)临床诊断项目费	数字	(10,2)
		 * 138. FSSZLXMF	治疗类(9)非手术治疗项目费	数字	(10,2)
		 * 139. ZLF	临床物理治疗费	数字	(10,2)
		 * 140. SSZLF	(10)手术治疗费	数字	(10,2)		手术治疗费>=麻醉费+手术费。
		 * 141. MZF	麻醉费	数字	(10,2)		麻醉方式有值时，麻醉费>0。
		 * 142. SSF	手术费	数字	(10,2)		发生手术时，手术费用>0。
		 * 143. KFF	康复类(11)康复费	数字	(10,2)
		 * 144. ZYL_ZYZD	中医类(中医和名族医医疗服务)（12）中医诊断	数字	(10,2)	条件必填	发生中医诊断费时，必填；小于总费用；治疗类别为"1或2"且住院天数>1时，（12）中医诊断+(13)中医治疗+(14)中医其他>0；
		 * 145. ZYZL	(13)中医治疗	数字	(10,2)	条件必填	发生中医治疗费时，必填；小于总费用；中医治疗费>=中医外治+中医骨伤+针刺与灸法+中医推拿治疗+中医肛肠治疗+中医特殊治疗；治疗类别为"1或2"且住院天数>1时，(13)中医治疗+(16)中成药费+(17)中草药费>0。
		 * 146. ZYWZ	中医外治	数字	(10,2)	条件必填	发生中医外治费时，必填。小于总费用。
		 * 147. ZYGS	中医骨伤	数字	(10,2)	条件必填	发生中医骨伤费时，必填。小于总费用。
		 * 148. ZCYJF	针刺与灸法	数字	(10,2)	条件必填	发生针刺与灸法费时，必填。小于总费用。
		 * 149. ZYTNZL	中医推拿治疗	数字	(10,2)	条件必填	发生中医推拿治疗费时，必填。小于总费用。
		 * 150. ZYGCZL	中医肛肠治疗	数字	(10,2)	条件必填	发生中医肛肠治疗费时，必填。小于总费用。
		 * 151. ZYTSZL	中医特殊治疗	数字	(10,2)	条件必填	发生中医特殊治疗费时，必填。小于总费用。
		 * 152. ZYQT	(14)中医其他	数字	(10,2)	条件必填	发生中医其他费时，必填。小于总费用。
		 * 153. ZYTSTPJG	中医特殊调配加工	数字	(10,2)	条件必填	发生中医特殊调配加工费时，必填。小于总费用。
		 * 154. BZSS	辨证施膳	数字	(10,2)	条件必填	发生辨证施膳费时，必填。小于总费用。
		 * 155. XYF	西药类(15)西药费	数字	(10,2)
		 * 156. KJYWF	抗菌药物费	数字	(10,2)
		 * 157. ZCYF	中药类(16)中成药费	数字	(10,2)	条件必填	发生中成药费时，必填；中成药费>=医疗机构中药制剂费。
		 * 158. ZYZJF	医疗机构中药制剂费	数字	(10,2)	条件必填	使用医疗机构中药制剂值为"是"时，必填，且值大于0，小于总费用。
		 * 159. ZCYF1	(17)中草药费	数字	(10,2)	条件必填	发生中草药费费时，必填。小于总费用。
		 * 160. XF	血液和血液制品类(18)血费	数字	(10,2)
		 * 161. BDBLZPF	(19)白蛋白类制品费	数字	(10,2)
		 * 162. QDBLZPF	(20)球蛋白类制品费	数字	(10,2)
		 * 163. NXYZLZPF	(21)凝血因子类制品费	数字	(10,2)
		 * 164. XBYZLZPF	(22)细胞因子类制品费	数字	(10,2)
		 * 165. JCYYCLF	耗材类(23)检查用一次性医用材料费	数字	(10,2)		住院天数>1时，(23)检查用一次性医用材料费+(24)治疗用一次性医用材料费+(25)手术用一次性医用材料费>0
		 * 166. YYCLF	(24)治疗用一次性医用材料费	数字	(10,2)
		 * 167. SSYCXCLF	(25)手术用一次性医用材料费	数字	(10,2)
		 * 168. QTF	其他类(26)其他费	数字	(10,2)		住院天数>1时，（26）其他类<总费用
		 */

		homepage.setChecked(checkRecord.size());
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

	public boolean diagnosisVerify(String code, String name, RecordRangeType type) throws Exception {
		boolean result = false;
		IcdTransform[] diagnoses = null;

		if (type.equals(RecordRangeType.DIAGNOSIS_CHINESE)) diagnoses = icd20DiagnosisChinese;
		else if (type.equals(RecordRangeType.DIAGNOSIS_WESTERN)) diagnoses = icd20DiagnosisWestern;
		else if (type.equals(RecordRangeType.DIAGNOSIS_OPERATION)) diagnoses = icd20DiagnosisOperation;
		else if (type.equals(RecordRangeType.DIAGNOSIS_PATHOLOGY)) diagnoses = icd20DiagnosisPathology;

		for (IcdTransform diagno : diagnoses) {
			if (diagno.getCode().equals(code) && diagno.getDiagno().equals(diagno)) {
				result = true;
				break;
			}
		}

		return result;
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

}
