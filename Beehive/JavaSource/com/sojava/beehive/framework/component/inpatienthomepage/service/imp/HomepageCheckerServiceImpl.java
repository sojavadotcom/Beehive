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
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class HomepageCheckerServiceImpl implements HomepageCheckerService {

	@Resource private HomepageDao homepageDao;
	private List<InpatientHomepageAnaly> homepageList;
	private int homepageIndex = 1;

	private static boolean isInited = false;

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
	//HIS对照
	private static VIcdTransform[] hisDiagnosisChinese = null;
	private static VIcdTransform[] hisDiagnosisWestern = null;
	private static VIcdTransform[] hisDiagnosisOperation = null;
	private static VIcdTransform[] hisDiagnosisPathology = null;
	//ICD2.0诊断
	private static IcdTransform[] icd20DiagnosisChinese = null;
	private static IcdTransform[] icd20DiagnosisWestern = null;
	private static IcdTransform[] icd20DiagnosisOperation = null;
	private static IcdTransform[] icd20DiagnosisPathology = null;

	private void initialize() {
		if (isInited) return;

		homepageList =  new ArrayList<InpatientHomepageAnaly>();
		String _status = "";
		try {
			_status = "RC001";
			rc001 = homepageDao.getDictionary(RecordRangeType.rc001).toArray(new Dictionary[0]);
			_status = "RC002";
			rc002 = homepageDao.getDictionary(RecordRangeType.rc002).toArray(new Dictionary[0]);
			_status = "RC003";
			rc003 = homepageDao.getDictionary(RecordRangeType.rc003).toArray(new Dictionary[0]);
			_status = "RC011";
			rc011 = homepageDao.getDictionary(RecordRangeType.rc011).toArray(new Dictionary[0]);
			_status = "RC013";
			rc013 = homepageDao.getDictionary(RecordRangeType.rc013).toArray(new Dictionary[0]);
			_status = "RC014_1";
			rc014_1 = homepageDao.getDictionary(RecordRangeType.rc014_1).toArray(new Dictionary[0]);
			_status = "RC014_2";
			rc014_2 = homepageDao.getDictionary(RecordRangeType.rc014_2).toArray(new Dictionary[0]);
			_status = "RC016";
			rc016 = homepageDao.getDictionary(RecordRangeType.rc016).toArray(new Dictionary[0]);
			_status = "RC019";
			rc019 = homepageDao.getDictionary(RecordRangeType.rc019).toArray(new Dictionary[0]);
			_status = "RC023";
			rc023 = homepageDao.getDictionary(RecordRangeType.rc023).toArray(new Dictionary[0]);
			_status = "RC026";
			rc026 = homepageDao.getDictionary(RecordRangeType.rc026).toArray(new Dictionary[0]);
			_status = "RC027";
			rc027 = homepageDao.getDictionary(RecordRangeType.rc027).toArray(new Dictionary[0]);
			_status = "RC028";
			rc028 = homepageDao.getDictionary(RecordRangeType.rc028).toArray(new Dictionary[0]);
			_status = "RC029";
			rc029 = homepageDao.getDictionary(RecordRangeType.rc029).toArray(new Dictionary[0]);
			_status = "RC030";
			rc030 = homepageDao.getDictionary(RecordRangeType.rc030).toArray(new Dictionary[0]);
			_status = "RC031";
			rc031 = homepageDao.getDictionary(RecordRangeType.rc031).toArray(new Dictionary[0]);
			_status = "RC032";
			rc032 = homepageDao.getDictionary(RecordRangeType.rc032).toArray(new Dictionary[0]);
			_status = "RC033";
			rc033 = homepageDao.getDictionary(RecordRangeType.rc033).toArray(new Dictionary[0]);
			_status = "RC035";
			rc035 = homepageDao.getDictionary(RecordRangeType.rc035).toArray(new Dictionary[0]);
			_status = "RC036";
			rc036 = homepageDao.getDictionary(RecordRangeType.rc036).toArray(new Dictionary[0]);
			_status = "RC037";
			rc037 = homepageDao.getDictionary(RecordRangeType.rc037).toArray(new Dictionary[0]);
			_status = "RC038";
			rc038 = homepageDao.getDictionary(RecordRangeType.rc038).toArray(new Dictionary[0]);
			_status = "RC039";
			rc039 = homepageDao.getDictionary(RecordRangeType.rc039).toArray(new Dictionary[0]);
			_status = "RC040";
			rc040 = homepageDao.getDictionary(RecordRangeType.rc040).toArray(new Dictionary[0]);
/*
			_status = "HIS诊断对照 - 中医诊断";
			hisDiagnosisChinese = homepageDao.getHISDiagnosis(RecordRangeType.DIAGNOSIS_CHINESE).toArray(new VIcdTransform[0]);
			_status = "HIS诊断对照 - 西医诊断";
			hisDiagnosisWestern = homepageDao.getHISDiagnosis(RecordRangeType.DIAGNOSIS_WESTERN).toArray(new VIcdTransform[0]);
			_status = "HIS诊断对照 - 手术诊断";
			hisDiagnosisOperation = homepageDao.getHISDiagnosis(RecordRangeType.DIAGNOSIS_OPERATION).toArray(new VIcdTransform[0]);
			_status = "HIS诊断对照 - 病理诊断";
*/
			hisDiagnosisPathology = homepageDao.getHISDiagnosis(RecordRangeType.DIAGNOSIS_PATHOLOGY).toArray(new VIcdTransform[0]);
			_status = "ICD2.0 - 中医诊断";
			icd20DiagnosisChinese = homepageDao.getICD20Diagnosis(RecordRangeType.DIAGNOSIS_CHINESE).toArray(new IcdTransform[0]);
			_status = "ICD2.0 - 西医诊断";
			icd20DiagnosisWestern = homepageDao.getICD20Diagnosis(RecordRangeType.DIAGNOSIS_WESTERN).toArray(new IcdTransform[0]);
			_status = "ICD2.0 - 手术诊断";
			icd20DiagnosisOperation = homepageDao.getICD20Diagnosis(RecordRangeType.DIAGNOSIS_OPERATION).toArray(new IcdTransform[0]);
			_status = "ICD2.0 - 病理诊断";
			icd20DiagnosisPathology = homepageDao.getICD20Diagnosis(RecordRangeType.DIAGNOSIS_PATHOLOGY).toArray(new IcdTransform[0]);

			isInited = true;
		}
		catch (Exception ex) {
			new ErrorException(getClass(), "初始化字典数据[" + _status + "]时发生错误 - " + ex.getClass().getName() + " : " + ex.getMessage());
		}
	}
	@Override
	public void loadCSV(File[] csvFile) throws Exception {
		initialize();
		for(File file : csvFile) {
			String fileName = file.getName();
			String[] fileProperty = fileName.split("\\Q.\\E")[0].split("\\Q_\\E");
			if (fileProperty.length != 3) throw new ErrorException(this.getClass(), "文件名格式错误[TCMMS_201901-08_1]");
			String kind = fileProperty[0];
			String type = fileProperty[1];
			int version = Integer.parseInt(fileProperty[2]);
			CSVParser parser = null;
			try {
				parser = CSVParser.parse(new FileReader(file), CSVFormat.DEFAULT.withFirstRecordAsHeader());
				parser.forEach(rec -> importHomepage(rec, kind, type, version));
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				parser.close();
			}
		}
	}

	@Override
	public void importHomepage(CSVRecord record, String homepageKind, String homepageType, int version) {
		initialize();
		InpatientHomepageAnaly homepage = new InpatientHomepageAnaly(this.homepageIndex ++, homepageKind, homepageType, version);
		homepage.setInpatientHomepageAnalyChecks(new ArrayList<InpatientHomepageAnalyCheck>());
		List<InpatientHomepageAnalyCheck> checkRecord = homepage.getInpatientHomepageAnalyChecks();
		String name = null, value = null;
		int checkIndex = checkRecord.size() + 1;
		try {
			for (Method method : homepage.getClass().getMethods()) {
				String methodName = method.getName();
				if (!methodName.startsWith("set")) continue;
				name = method.getAnnotations().length == 0 ? methodName.substring(3).toLowerCase() : ((Column) method.getAnnotations()[0]).name();
				if (name.equals("id") || name.equals("kind") || name.equals("type") || name.equals("checked") || name.equals("version") || name.startsWith("inpatienthomepageanalycheck")) continue;
				Class<?> type = method.getParameterTypes()[0];
				try {
					value = record.get(name.toUpperCase());
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}

				try {
					Object val = transValueType(name, value, type);
					method.invoke(homepage, val);
				}
				catch(Exception ex) {
					checkRecord.add(new InpatientHomepageAnalyCheck(checkIndex ++, homepage.getId(), name, value, ex.getMessage(), "Item"));
				}
			}
			/***********************************/
			/******* 信息校验（包含逻辑校验）******/
			/**********************************/
			dataVerify(homepage);
		}
		catch(Exception ex) {
			ex.printStackTrace();
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
		initialize();
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
		initialize();
		List<InpatientHomepageAnalyCheck> checkRecord = homepage.getInpatientHomepageAnalyChecks();
		int checkIndex = checkRecord.size() + 1;
		int row = homepage.getId();
		int col = 0;

		boolean hasSs = false;
		boolean hasMz = false;

		/***************************************************************************************/
		/******* 病案首页数据逻辑检查，依据《三级公立中医医院病案首页数据采集接口标准与质量要求》。 ********/ 
		/***************************************************************************************/
		/*
		 * 1. ZZJGDM	组织机构代码	字符	22	必填	指医疗机构执业许可证上面的机构代码。
		 */
		col ++;
		if (homepage.getZzjgdm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZZJGDM",
					homepage.getZzjgdm(),
					"#" + row + "." + col + ".[ZZJGDM:组织机构代码=" + homepage.getZzjgdm() + "]未填写组织机构代码",
					"Item"
				));
		} else if (homepage.getZzjgdm().length() != 18) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZZJGDM",
					homepage.getZzjgdm(),
					"#" + row + "." + col + ".[ZZJGDM:组织机构代码=" + homepage.getZzjgdm() + "]组织机构代码应为18位",
					"Item"
				));
		}
		/*
		 * 2. JGMC	医疗机构名称	字符	80	必填
		 */
		col ++;
		if (homepage.getJgmc().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JGMC",
					homepage.getZzjgdm(),
					"#" + row + "." + col + ".[JGMC:组织机构名称=" + homepage.getJgmc() + "]未填写组织机构名称",
					"Item"
				));
		}
		/*
		 * 3. USERNAME	对应的系统登录用户名	字符	10	必填	医院名称或者编码
		 */
		col ++;
		if (homepage.getUsername().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"USERNAME",
					homepage.getUsername(),
					"#" + row + "." + col + ".[USERNAME:系统登录用户名=" + homepage.getUsername() + "]未填写系统登录用户名",
					"Item"
				));
		}
		/*
		 * 4. YLFKFS	医疗付款方式	字符	3	必填	《值域范围参考RC032-医疗付费方式代码》
		 */
		col ++;
		if (homepage.getYlfkfs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YLFKFS",
					homepage.getYlfkfs(),
					"#" + row + "." + col + ".[YLFKFS:医疗付款方式=" + homepage.getYlfkfs() + "]未填写医疗付款方式",
					"Item"
				));
		} else if (!compareDic(rc032, RecordRangeType.code, homepage.getYlfkfs())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YLFKFS",
					homepage.getYlfkfs(),
					"#" + row + "." + col + ".[YLFKFS:医疗付款方式=" + homepage.getYlfkfs() + "]代码填写不正确，《值域范围参考RC032-医疗付费方式代码》",
					"Item"
				));
		}
		/*
		 * 5. JKKH	健康卡号	字符	50		在已统一发放“中华人民共和国居民健康卡”的地区填写健康卡号码，尚未发放“健康卡”的地区填写“-”
		 */
		col ++;
		if (homepage.getJkkh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JKKH",
					homepage.getJkkh(),
					"#" + row + "." + col + ".[JKKH:医疗付款方式=" + homepage.getJkkh() + "]未填写健康卡号，尚未发放“健康卡”的地区填写“-”",
					"Item"
				));
		}
		/*
		 * 6. ZYCS	住院次数	数字	4	必填	大于0的整数
		 */
		col ++;
		if (homepage.getZycs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYCS",
					homepage.getZycs(),
					"#" + row + "." + col + ".[ZYCS:住院次数=" + homepage.getZycs() + "]未填写住院次数",
					"Item"
				));
		} else if (!isInteger(homepage.getZycs())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYCS",
					homepage.getZycs(),
					"#" + row + "." + col + ".[ZYCS:住院次数=" + homepage.getZycs() + "]填写不正确，必须是大于0的整数",
					"Item"
				));
		} else if (getInteger(homepage.getZycs()) <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYCS",
					homepage.getZycs(),
					"#" + row + "." + col + ".[ZYCS:住院次数=" + homepage.getZycs() + "]住院次数必须大于0",
					"Item"
				));
		}
		/*
		 * 7. BAH	病案号	字符	50	必填
		 */
		col ++;
		if (homepage.getBah().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BAH",
					homepage.getBah(),
					"#" + row + "." + col + ".[BAH:病案号=" + homepage.getBah() + "]未填写病案号",
					"Item"
				));
		}
		/*
		 * 8. XM	姓名	字符	40	必填
		 */
		col ++;
		if (homepage.getXm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XM",
					homepage.getXm(),
					"#" + row + "." + col + ".[XM:姓名=" + homepage.getXm() + "]未填写患者姓名",
					"Item"
				));
		}
		/*
		 * 9. XB	性别	数字	1	必填	《值域范围参考RC001-性别代码》
		 */
		col ++;
		if (homepage.getXb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XB",
					homepage.getXb(),
					"#" + row + "." + col + ".[XB:性别=" + homepage.getXb() + "]未填写性别",
					"Item"
				));
		} else if (!compareDic(rc001, RecordRangeType.code, homepage.getXb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XB",
					homepage.getXb(),
					"#" + row + "." + col + ".[XB:性别=" + homepage.getXb() + "]代码填写不正确，《值域范围参考RC001-性别代码》",
					"Item"
				));
		}
		/*
		 * 10. CSRQ	出生日期	日期	10	必填	yyyy-mm-dd
		 */
		col ++;
		if (homepage.getCsrq().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CSRQ",
					homepage.getCsrq(),
					"#" + row + "." + col + ".[CSRQ:出生日期=" + homepage.getCsrq() + "]未填写出生日期",
					"Item"
				));
		} else if (!isDate(homepage.getCsrq())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CSRQ",
					homepage.getCsrq(),
					"#" + row + "." + col + ".[CSRQ:出生日期=" + homepage.getCsrq() + "]格式不正确，格式 yyyy-MM-dd",
					"Item"
				));
		}
		/*
		 * 11. NL	年龄（岁）	数字	3	必填	患者入院年龄，指患者入院时按照日历计算的历法年龄，应以实足年龄的相应整数填写。大于或等于0的整数
		 */
		col ++;
		Integer _age = getInteger(homepage.getNl());
		if (homepage.getNl().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"NL",
					homepage.getNl(),
					"#" + row + "." + col + ".[NL:年龄（岁）=" + homepage.getNl() + "]未填写",
					"Item"
				));
		} else if (!isInteger(homepage.getNl())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"NL",
					homepage.getNl(),
					"#" + row + "." + col + ".[NL:年龄（岁）=" + homepage.getNl() + "]不是数字(整数)",
					"Item"
				));
		} else if (_age < 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"NL",
					homepage.getNl(),
					"#" + row + "." + col + ".[NL:年龄（岁）=" + homepage.getNl() + "]必须是大于或等于0的整数",
					"Item"
				));
		}
		/*
		 * 12. GJ	国籍	字符	40	必填	《值域范围参考RC038-国籍字典》
		 */
		col ++;
		if (homepage.getGj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GJ",
					homepage.getGj(),
					"#" + row + "." + col + ".[GJ:国籍=" + homepage.getGj() + "]未填写国籍",
					"Item"
				));
		} else if (!compareDic(rc038, RecordRangeType.code, homepage.getGj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GJ",
					homepage.getGj(),
					"#" + row + "." + col + ".[GJ:国籍=" + homepage.getGj() + "]代码填写不正确，《值域范围参考RC038-国籍字典》",
					"Item"
				));
		}
		/*
		 * 13. BZYZS_NL	年龄不足1周岁的年龄（天）	数字	3	条件必填	新生儿病例
		 */
		col ++;
		if (_age != null && _age == 0) {
			if (homepage.getBzyzsNl().isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"BZYZS_NL",
						homepage.getBzyzsNl(),
						"#" + row + "." + col + ".[BZYZS_NL:年龄不足1周岁的年龄（天）=" + homepage.getBzyzsNl() + "]年龄不足1周岁的未填写",
						"Item"
					));
			} else if(!isFloat(homepage.getBzyzsNl())) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"BZYZS_NL",
						homepage.getBzyzsNl(),
						"#" + row + "." + col + ".[BZYZS_NL:年龄不足1周岁的年龄（天）=" + homepage.getBzyzsNl() + "]必须为数字",
						"Item"
					));
			}
		}
		/*
		 * 14. XSETZ	新生儿出生体重(克)	数字	6	条件必填	测量新生儿体重要求精确到10克；应在活产后一小时内称取重量。
		 * 1、产妇和新生儿病案填写，从出生到28天为新生儿期，双胎及以上不同胎儿体重则继续填写下面的新生儿出生体重。
		 * 2、新生儿体重范围：100克-9999克，产妇的主要诊断或其他诊断编码中含有Z37.0,Z37.2, Z37.3, Z37.5, Z37.6编码时，未填写新生儿出生体重
		 */
		col ++;
//		TODO 新生儿体重
//		if () {
//			
//		}
		/*
		 * 15. XSETZ2	新生儿出生体重(克)2	数字	6		新生儿体重范围：100克-9999克
		 */
		col ++;
		if (!homepage.getXsetz2().isEmpty() && !isFloat(homepage.getXsetz2())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XSETZ2",
					homepage.getXsetz2(),
					"#" + row + "." + col + ".[XSETZ2:新生儿出生体重(克)2=" + homepage.getXsetz2() + "]必须为数字",
					"Item"
				));
		}
		/*
		 * 16. XSETZ3	新生儿出生体重(克)3	数字	6		新生儿体重范围：100克-9999克
		 */
		col ++;
		if (!homepage.getXsetz3().isEmpty() && !isFloat(homepage.getXsetz3())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XSETZ3",
					homepage.getXsetz3(),
					"#" + row + "." + col + ".[XSETZ3:新生儿出生体重(克)3=" + homepage.getXsetz3() + "]必须为数字",
					"Item"
				));
		}
		/*
		 * 17. XSETZ4	新生儿出生体重(克)4	数字	6		新生儿体重范围：100克-9999克
		 */
		col ++;
		if (!homepage.getXsetz4().isEmpty() && !isFloat(homepage.getXsetz4())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XSETZ4",
					homepage.getXsetz4(),
					"#" + row + "." + col + ".[XSETZ4:新生儿出生体重(克)4=" + homepage.getXsetz4() + "]必须为数字",
					"Item"
				));
		}
		/*
		 * 18. XSETZ5	新生儿出生体重(克)5	数字	6		新生儿体重范围：100克-9999克
		 */
		col ++;
		if (!homepage.getXsetz5().isEmpty() && !isFloat(homepage.getXsetz5())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XSETZ5",
					homepage.getXsetz5(),
					"#" + row + "." + col + ".[XSETZ5:新生儿出生体重(克)5=" + homepage.getXsetz5() + "]必须为数字",
					"Item"
				));
		}
		/*
		 * 19. XSERYTZ	新生儿入院体重（克）	数字	6	条件必填	100克-9999克，精确到10克；新生儿入院当日的体重；
		 * 小于等于28天的新生儿必填，填写了新生儿入出院体重的，未填写年龄不足1周岁的年龄（天），且必须小于等于28天。
		 */
		col ++;
		// TODO 新生儿入院体重
//		if () {
//			
//		}
		/*
		 * 20. CSD	出生地	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		col ++;
		if (homepage.getCsd().isEmpty() || homepage.getCsd().length() <= 2) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CSD",
					homepage.getCsd(),
					"#" + row + "." + col + ".[CSD:出生地=" + homepage.getCsd() + "]未填写出生地",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getCsd())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CSD",
					homepage.getCsd(),
					"#" + row + "." + col + ".[CSD:出生地=" + homepage.getCsd() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 21. GG	籍贯	字符	50	必填	《值域范围参考RC036-籍贯》
		 */
		col ++;
		if (homepage.getGg().isEmpty() || homepage.getGg().length() <= 2) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GG",
					homepage.getGg(),
					"#" + row + "." + col + ".[GG:籍贯=" + homepage.getGg() + "]未填写籍贯",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getGg())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GG",
					homepage.getGg(),
					"#" + row + "." + col + ".[GG:籍贯=" + homepage.getGg() + "]填写不正确，《值域范围参考RC036-籍贯》",
					"Item"
				));
		}
		/*
		 * 22. MZ	民族	字符	2	必填	《值域范围参考RC035-民族代码》
		 */
		col ++;
		if (homepage.getMz().isEmpty() || homepage.getMz().length() <= 2) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MZ",
					homepage.getMz(),
					"#" + row + "." + col + ".[MZ:民族=" + homepage.getMz() + "]未填写民族",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getMz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MZ",
					homepage.getMz(),
					"#" + row + "." + col + ".[MZ:民族=" + homepage.getMz() + "]填写不正确，《值域范围参考RC035-民族代码》",
					"Item"
				));
		}
		/*
		 * 23. SFZH	身份证号	字符	18	必填	住院患者入院时要如实填写15位或18位身份证号码
		 */
		col ++;
		if (homepage.getSfzh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SFZH",
					homepage.getSfzh(),
					"#" + row + "." + col + ".[SFZH:身份证号=" + homepage.getSfzh() + "]未填写身份证号",
					"Item"
				));
		} else if (isInteger(homepage.getSfzh()) && homepage.getSfzh().length() != 15 && homepage.getSfzh().length() != 18) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SFZH",
					homepage.getSfzh(),
					"#" + row + "." + col + ".[SFZH:身份证号=" + homepage.getSfzh() + "]填写不正确，必须是15位或18位",
					"Item"
				));
		}
		/*
		 * 24. ZY	职业	字符	2	必填	《值域范围参考RC003-职业代码》
		 */
		col ++;
		if (homepage.getZy().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZY",
					homepage.getZy(),
					"#" + row + "." + col + ".[ZY:职业=" + homepage.getZy() + "]未填写职业",
					"Item"
				));
		} else if (!compareDic(rc003, RecordRangeType.code, homepage.getZy())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZY",
					homepage.getZy(),
					"#" + row + "." + col + ".[ZY:职业=" + homepage.getZy() + "]填写不正确，《值域范围参考RC003-职业代码》",
					"Item"
				));
		}
		/*
		 * 25. HY	婚姻	字符	1	必填	《值域范围参考RC002-婚姻代码》
		 */
		col ++;
		if (homepage.getHy().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"HY",
					homepage.getHy(),
					"#" + row + "." + col + ".[HY:婚姻=" + homepage.getHy() + "]未填写婚姻",
					"Item"
				));
		} else if (!compareDic(rc002, RecordRangeType.code, homepage.getHy())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"HY",
					homepage.getHy(),
					"#" + row + "." + col + ".[HY:婚姻=" + homepage.getHy() + "]填写不正确，《值域范围参考RC002-婚姻代码》",
					"Item"
				));
		}
		/*
		 * 26. XZZ	现住址	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		col ++;
		if (homepage.getXzz().isEmpty() || homepage.getXzz().length() <= 2) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XZZ",
					homepage.getXzz(),
					"#" + row + "." + col + ".[XZZ:现住址=" + homepage.getXzz() + "]未填写现住址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getXzz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XZZ",
					homepage.getXzz(),
					"#" + row + "." + col + ".[XZZ:现住址=" + homepage.getXzz() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 27. DH	现住址电话	字符	40	必填
		 */
		col ++;
		if (homepage.getDh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"DH",
					homepage.getDh(),
					"#" + row + "." + col + ".[DH:现住址电话=" + homepage.getDh() + "]未填写现住址电话",
					"Item"
				));
		}
		/*
		 * 28. YB1	现住址邮政编码	字符	6	必填	6位数字
		 */
		col ++;
		if (homepage.getYb1().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YB1",
					homepage.getYb1(),
					"#" + row + "." + col + ".[YB1:现住址邮政编码=" + homepage.getYb1() + "]未填写现住址邮政编码",
					"Item"
				));
		}
		/*
		 * 29. HKDZ	户口地址	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		col ++;
		if (homepage.getHkdz().isEmpty() || homepage.getHkdz().length() <= 2) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"HKDZ",
					homepage.getHkdz(),
					"#" + row + "." + col + ".[HKDZ:户口地址=" + homepage.getHkdz() + "]未填写户口地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getHkdz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"HKDZ",
					homepage.getHkdz(),
					"#" + row + "." + col + ".[HKDZ:户口地址=" + homepage.getHkdz() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 30. YB2	户口地址邮政编码	字符	6	必填	6位数字
		 */
		col ++;
		if (homepage.getYb2().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YB2",
					homepage.getYb2(),
					"#" + row + "." + col + ".[YB2:户口地址邮政编码=" + homepage.getYb2() + "]未填写户口地址邮政编码",
					"Item"
				));
		}
		/*
		 * 31. GZDWJDZ	工作单位及地址	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		col ++;
		if (homepage.getGzdwjdz().isEmpty() || homepage.getGzdwjdz().length() <= 2) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GZDWJDZ",
					homepage.getGzdwjdz(),
					"#" + row + "." + col + ".[GZDWJDZ:工作单位及地址=" + homepage.getGzdwjdz() + "]未填写工作单位及地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getGzdwjdz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GZDWJDZ",
					homepage.getGzdwjdz(),
					"#" + row + "." + col + ".[GZDWJDZ:工作单位=" + homepage.getGzdwjdz() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 32. DWDH	工作单位电话	字符	20	必填
		 */
		col ++;
		if (homepage.getDwdh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"DWDH",
					homepage.getDwdh(),
					"#" + row + "." + col + ".[DWDH:工作单位电话=" + homepage.getDwdh() + "]未填写工作单位电话",
					"Item"
				));
		}
		/*
		 * 33. YB3	工作单位邮政编码	字符	6	必填	6位数字
		 */
		col ++;
		if (homepage.getYb3().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YB3",
					homepage.getYb3(),
					"#" + row + "." + col + ".[YB3:工作单位邮政编码=" + homepage.getYb3() + "]未填写工作单位邮政编码",
					"Item"
				));
		}
		/*
		 * 34. LXRXM	联系人姓名	字符	40	必填
		 */
		col ++;
		if (homepage.getLxrxm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"LXRXM",
					homepage.getLxrxm(),
					"#" + row + "." + col + ".[LXRXM:联系人姓名=" + homepage.getLxrxm() + "]未填写联系人姓名",
					"Item"
				));
		}
		/*
		 * 35. GX	联系人关系	字符	1	必填	《值域范围参考RC033-联系人关系代码》
		 */
		col ++;
		if (homepage.getGx().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GX",
					homepage.getGx(),
					"#" + row + "." + col + ".[GX:联系人关系=" + homepage.getGx() + "]未填写联系人关系",
					"Item"
				));
		} else if (!compareDic(rc033, RecordRangeType.code, homepage.getGx())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GX",
					homepage.getGx(),
					"#" + row + "." + col + ".[GX:联系人关系=" + homepage.getGx() + "]填写不正确，《值域范围参考RC033-联系人关系代码》",
					"Item"
				));
		}
		/*
		 * 36. DZ	联系人地址	字符	200	必填	例如：陕西省商洛市商南县金丝峡镇梁家湾村58号，必填到省或自治区。
		 */
		col ++;
		if (homepage.getDz().isEmpty() || homepage.getDz().length() <= 2) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"DZ",
					homepage.getDz(),
					"#" + row + "." + col + ".[DZ:联系人地址=" + homepage.getDz() + "]未填写联系人地址",
					"Item"
				));
		} else if (!compareDic(rc036, RecordRangeType.name, homepage.getDz())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"DZ",
					homepage.getDz(),
					"#" + row + "." + col + ".[DZ:联系人地址=" + homepage.getDz() + "]必须以省或自治区开始",
					"Item"
				));
		}
		/*
		 * 37. DH1	联系人电话	字符	40	必填
		 */
		col ++;
		if (homepage.getDh1().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"DH1",
					homepage.getDh1(),
					"#" + row + "." + col + ".[DH1:联系人电话=" + homepage.getDh1() + "]未填写工作单位电话",
					"Item"
				));
		}
		/*
		 * 38. RYTJ	入院途径	字符	1	必填	《值域范围参考RC026-入院途径代码》
		 */
		col ++;
		if (homepage.getRytj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYTJ",
					homepage.getRytj(),
					"#" + row + "." + col + ".[RYTJ:入院途径=" + homepage.getRytj() + "]未填写入院途径",
					"Item"
				));
		} else if (!compareDic(rc026, RecordRangeType.code, homepage.getRytj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYTJ",
					homepage.getRytj(),
					"#" + row + "." + col + ".[RYTJ:入院途径=" + homepage.getRytj() + "]填写不正确，《值域范围参考RC026-入院途径代码》",
					"Item"
				));
		}
		/*
		 * 39. ZLLB	治疗类别	字符	3		《值域范围参考RC039-治疗类别字典》
		 */
		col ++;
		if (!homepage.getZllb().isEmpty() && !compareDic(rc039, RecordRangeType.code, homepage.getZllb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZLLB",
					homepage.getZllb(),
					"#" + row + "." + col + ".[ZLLB:治疗类别=" + homepage.getZllb() + "]填写不正确，《值域范围参考RC039-治疗类别字典》",
					"Item"
				));
		}
		/*
		 * 40. RYSJ	入院时间	日期		必填	格式 yyyy-MM-dd HH:mm:ss；入院时间不能晚于出院时间
		 */
		col ++;
		if (homepage.getRysj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYSJ",
					homepage.getRysj(),
					"#" + row + "." + col + ".[RYSJ:入院时间=" + homepage.getRysj() + "]未填写入院时间",
					"Item"
					));
		} else if (!isDatetime(homepage.getRysj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYSJ",
					homepage.getRysj(),
					"#" + row + "." + col + ".[RYSJ:入院时间=" + homepage.getRysj() + "]填写不正确，格式 yyyy-MM-dd HH:mm:ss",
					"Item"
					));
		}
		/*
		 * 41. RYSJ_S	时			必填	24小时制
		 */
		col ++;
		if (homepage.getRysjS().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYSJ_S",
					homepage.getRysjS(),
					"#" + row + "." + col + ".[RYSJ_S:入院时间（时）=" + homepage.getRysjS() + "]未填写入院时间（时）",
					"Item"
					));
		} else if (!isInteger(homepage.getRysjS())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYSJ_S",
					homepage.getRysjS(),
					"#" + row + "." + col + ".[RYSJ_S:入院时间（时）=" + homepage.getRysjS() + "]填写不正确，小时数（24小时制）",
					"Item"
					));
		} else {
			if (isDatetime(homepage.getRysj())) {
				Date _rysj = FormatUtil.parseDate(homepage.getRysj());
				Calendar _rysjCal = Calendar.getInstance();
				_rysjCal.setTime(_rysj);
				int _hour = _rysjCal.get(Calendar.HOUR);
				if (_hour != getInteger(homepage.getRysjS())) {
							checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"RYSJ_S",
							homepage.getRysjS(),
							"#" + row + "." + col + ".[RYSJ:入院时间=" + homepage.getRysj() + "；RYSJ_S:入院时间（时）=" + homepage.getRysjS() + "]逻辑验证未通过，入院时间不匹配",
							"Item"
							));
				}
			}
		}
		/*
		 * 42. RYKB	入院科别	字符	6	必填	《值域范围参考RC023-科室代码》
		 */
		col ++;
		if (homepage.getRykb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYKB",
					homepage.getRykb(),
					"#" + row + "." + col + ".[RYKB:入院科别=" + homepage.getRykb() + "]未填写入院科别",
					"Item"
				));
		} else if (!compareDic(rc023, RecordRangeType.code, homepage.getRykb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYKB",
					homepage.getRykb(),
					"#" + row + "." + col + ".[RYKB:入院科别=" + homepage.getRykb() + "]填写不正确，《值域范围参考RC023-科室代码》",
					"Item"
				));
		}
		/*
		 * 43. RYBF	入院病房	字符	30	必填
		 */
		col ++;
		if (homepage.getRybf().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYBF",
					homepage.getRybf(),
					"#" + row + "." + col + ".[RYBF:入院病房=" + homepage.getRybf() + "]未填写入院病房",
					"Item"
				));
		}
		/*
		 * 44. ZKKB	转科科别	集合	可以多选		《值域范围参考RC023-科室代码》
		 */
		col ++;
		if (homepage.getZkkb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZKKB",
					homepage.getZkkb(),
					"#" + row + "." + col + ".[ZKKB:转科科别=" + homepage.getZkkb() + "]未填写转科科别",
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
							"#" + row + "." + col + ".[ZKKB:转科科别=" + homepage.getZkkb() + "]" + _zkkb + "填写不正确，《值域范围参考RC023-科室代码》",
							"Item"
						));
				}
			}
		}
		/*
		 * 45. CYSJ	出院时间	日期		必填	格式 yyyy-MM-dd HH:mm:ss；入院时间不能晚于出院时间
		 */
		col ++;
		if (homepage.getCysj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ",
					homepage.getCysj(),
					"#" + row + "." + col + ".[CYSJ:出院时间=" + homepage.getCysj() + "]未填写出院时间",
					"Item"
					));
		} else if (!isDatetime(homepage.getCysj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ",
					homepage.getCysj(),
					"#" + row + "." + col + ".[CYSJ:出院时间=" + homepage.getCysj() + "]填写不正确，格式 yyyy-MM-dd HH:mm:ss",
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
					"#" + row + "." + col + ".[CYSJ:出院时间=" + homepage.getCysj() + "；RYSJ:入院时间=" + homepage.getRysj() + "]逻辑验证未通过，出院时间小于入院时间",
					"Item"
					));
		}
		/*
		 * 46. CYSJ_S	时			必填
		 */
		col ++;
		if (homepage.getCysjS().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ_S",
					homepage.getCysjS(),
					"#" + row + "." + col + ".[CYSJ_S:出院时间（时）=" + homepage.getCysjS() + "]未填写出院时间（时）",
					"Item"
					));
		} else if (!isInteger(homepage.getCysjS())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYSJ_S",
					homepage.getCysjS(),
					"#" + row + "." + col + ".[CYSJ_S:出院时间（时）=" + homepage.getCysjS() + "]填写不正确，小时数（24小时制）",
					"Item"
					));
		} else {
			if (isDatetime(homepage.getCysj())) {
				Date _cysj = FormatUtil.parseDate(homepage.getCysj());
				Calendar _cysjCal = Calendar.getInstance();
				_cysjCal.setTime(_cysj);
				int _hour = _cysjCal.get(Calendar.HOUR);
				if (_hour != getInteger(homepage.getCysjS())) {
							checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"CYSJ_S",
							homepage.getCysjS(),
							"#" + row + "." + col + ".[CYSJ:出院时间=" + homepage.getCysj() + "；CYSJ_S:出院时间（时）=" + homepage.getCysjS() + "]逻辑验证未通过，出院时间不匹配",
							"Item"
							));
				}
			}
		}
		/*
		 * 47. CYKB	出院科别	字符	6	必填	《值域范围参考RC023-科室代码》；国家重点专科科室必须有数据。
		 */
		col ++;
		if (homepage.getCykb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYKB",
					homepage.getCykb(),
					"#" + row + "." + col + ".[CYKB:出院科别=" + homepage.getCykb() + "]未填写出院科别",
					"Item"
				));
		} else if (!compareDic(rc023, RecordRangeType.code, homepage.getCykb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYKB",
					homepage.getCykb(),
					"#" + row + "." + col + ".[CYKB:出院科别=" + homepage.getCykb() + "]填写不正确，《值域范围参考RC023-科室代码》",
					"Item"
				));
		}
		/*
		 * 48. CYBF	出院病房	字符	30	必填
		 */
		col ++;
		if (homepage.getCybf().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"CYBF",
					homepage.getCybf(),
					"#" + row + "." + col + ".[CYBF:出院病房=" + homepage.getCybf() + "]未填写出院病房",
					"Item"
				));
		}
		/*
		 * 49. SJZY	实际住院(天)	数字	6	必填	大于0整数。入院时间与出院时间只计算一天，例如：2018年6月12日入院，2018年6月15日出院，计住院天数为3天
		 */
		col ++;
		if (homepage.getSjzy().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SJZY",
					homepage.getSjzy(),
					"#" + row + "." + col + ".[SJZY:实际住院(天)=" + homepage.getSjzy() + "]未填写实际住院(天)",
					"Item"
				));
		} else if (!isInteger(homepage.getSjzy()) || getInteger(homepage.getSjzy()) <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SJZY",
					homepage.getSjzy(),
					"#" + row + "." + col + ".[SJZY:实际住院(天)=" + homepage.getSjzy() + "]填写不正确，必填大于0整数",
					"Item"
				));
		} else if (isDatetime(homepage.getCysj()) && isDatetime(homepage.getRysj())) {
			long _cysj = FormatUtil.parseDateTime(homepage.getCysj()).getTime();
			long _rysj = FormatUtil.parseDateTime(homepage.getRysj()).getTime();
			int _sjzy = (int) (_cysj - _rysj);
			_sjzy = _sjzy == 0 ? 1 : _sjzy;
			if (_sjzy > 0 && _sjzy != getInteger(homepage.getSjzy())) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"SJZY",
						homepage.getSjzy(),
						"#" + row + "." + col + ".[SJZY:实际住院(天)=" + homepage.getSjzy() + "]逻辑验证未通过，与出入院时间不符[" + _sjzy + "]",
						"Item"
					));
			}
		}
		/*
		 * 50. MZD_ZYZD	门(急)诊诊断(中医诊断)	字符	100	必填	采用中医病症分类代码国标版95（TCM95）与编码对应的病诊断名称
		 */
		col ++;
		if (homepage.getMzdZyzd().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MZD_ZYZD",
					homepage.getMzdZyzd(),
					"#" + row + "." + col + ".[MZD_ZYZD:门(急)诊诊断(中医诊断)=" + homepage.getMzdZyzd() + "]未填写门(急)诊诊断(中医诊断)",
					"Item"
				));
		}
		/*
		 * 51. JBDM	门(急)诊诊断疾病代码(中医诊断)	字符	20	必填	采用中医病症分类代码国标版95（TCM95）
		 */
		col ++;
		if (homepage.getJbdm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JBDM",
					homepage.getJbdm(),
					"#" + row + "." + col + ".[JBDM:门(急)诊诊断疾病代码(中医诊断)=" + homepage.getJbdm() + "]未填写门(急)诊诊断疾病代码(中医诊断)",
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
					"#" + row + "." + col + ".[MZD_ZYZD:门(急)诊诊断(中医诊断)=" + homepage.getMzdZyzd() + "；JBDM:门(急)诊诊断疾病代码(中医诊断)=" + homepage.getJbdm() + "]逻辑验证未通过，与《中医病症分类代码国标版95（TCM95）》不匹配",
					"Item"
				));
		}
		/*
		 * 52. MZZD_XYZD	门（急）诊诊断名称(西医诊断)	字符	100	必填	采用疾病分类代码国家临床版2.0(ICD-10)与编码对应的诊断名称
		 */
		col ++;
		if (homepage.getMzzdXyzd().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MZZD_XYZD",
					homepage.getMzzdXyzd(),
					"#" + row + "." + col + ".[MZZD_XYZD:门（急）诊诊断名称(西医诊断)=" + homepage.getMzzdXyzd() + "]未填写门（急）诊诊断名称(西医诊断)",
					"Item"
				));
		}
		/*
		 * 53. JBBM	门（急）诊诊断编码(西医诊断)	字符	20	必填	采用疾病分类代码国家临床版2.0编码（ICD-10）
		 */
		col ++;
		if (homepage.getJbbm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JBBM",
					homepage.getJbbm(),
					"#" + row + "." + col + ".[JBBM:门（急）诊诊断编码(西医诊断)=" + homepage.getJbbm() + "]未填写门（急）诊诊断编码(西医诊断)",
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
					"#" + row + "." + col + ".[MZZD_XYZD:门（急）诊诊断名称(西医诊断)=" + homepage.getMzzdXyzd() + "；JBBM:门（急）诊诊断编码(西医诊断)=" + homepage.getJbbm() + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0编码（ICD-10）》不匹配",
					"Item"
				));
		}
		/*
		 * 54. SSLCLJ	实施临床路径	字符	1	必填	《值域范围参考RC040-实施临床路径字典》
		 */
		col ++;
		if (homepage.getSslclj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SSLCLJ",
					homepage.getSslclj(),
					"#" + row + "." + col + ".[SSLCLJ:实施临床路径=" + homepage.getSslclj() + "]未填写实施临床路径",
					"Item"
				));
		} else if (!compareDic(rc040, RecordRangeType.code, homepage.getSslclj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SSLCLJ",
					homepage.getSslclj(),
					"#" + row + "." + col + ".[SSLCLJ:实施临床路径=" + homepage.getSslclj() + "]填写不正确，《值域范围参考RC040-实施临床路径字典》",
					"Item"
				));
		}
		/*
		 * 55. ZYYJ	使用医疗机构中药制剂	字符	1	必填	《值域范围参考RC016》；参照卫统4-2病案首页值域；当值为"是"时，医疗机构中药制剂费>0
		 */
		col ++;
		if (homepage.getZyyj().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYJ",
					homepage.getZyyj(),
					"#" + row + "." + col + ".[ZYYJ:使用医疗机构中药制剂=" + homepage.getZyyj() + "]未填写使用医疗机构中药制剂",
					"Item"
				));
		} else if (!compareDic(rc016, RecordRangeType.code, homepage.getZyyj())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYJ",
					homepage.getZyyj(),
					"#" + row + "." + col + ".[ZYYJ:使用医疗机构中药制剂=" + homepage.getZyyj() + "]填写不正确，《值域范围参考RC016》",
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
					"#" + row + "." + col + ".[ZYYJ:使用医疗机构中药制剂=" + homepage.getZyyj() + "；ZYZJF:中药制剂费=" + _zyzjf + "]逻辑验证未通过，当值为\"是\"时，医疗机构中药制剂费>0",
					"Item"
				));
			} else if (_zyzjf > 0 && !homepage.getZyyj().equals("1")) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYJ",
					homepage.getZyyj(),
					"#" + row + "." + col + ".[ZYYJ:使用医疗机构中药制剂=" + homepage.getZyyj() + "；ZYZJF:中药制剂费=" + _zyzjf + "]逻辑验证未通过，医疗机构中药制剂费>0，值应为\"是\"",
					"Item"
				));
			}
		}
		/*
		 * 56. ZYZLSB	使用中医诊疗设备	字符	1	必填	《值域范围参考RC016》；参照卫统4-2病案首页值域
		 */
		col ++;
		if (homepage.getZyzlsb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZLSB",
					homepage.getZyzlsb(),
					"#" + row + "." + col + ".[ZYZLSB:使用中医诊疗设备=" + homepage.getZyzlsb() + "]未填写使用中医诊疗设备",
					"Item"
				));
		} else if (!compareDic(rc016, RecordRangeType.code, homepage.getZyzlsb())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZLSB",
					homepage.getZyzlsb(),
					"#" + row + "." + col + ".[ZYZLSB:使用中医诊疗设备=" + homepage.getZyzlsb() + "]填写不正确，《值域范围参考RC016》",
					"Item"
				));
		}
		/*
		 * 57. ZYZLJS	使用中医诊疗技术	字符	1	必填	《值域范围参考RC016》；参照卫统4-2病案首页值域；中医类（中医和民族医医疗服务）费>0
		 */
		col ++;
		if (homepage.getZyzljs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZLJS",
					homepage.getZyzljs(),
					"#" + row + "." + col + ".[ZYZLJS:使用中医诊疗技术=" + homepage.getZyzljs() + "]未填写使用中医诊疗技术",
					"Item"
				));
		} else if (!compareDic(rc016, RecordRangeType.code, homepage.getZyzljs())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZLJS",
					homepage.getZyzljs(),
					"#" + row + "." + col + ".[ZYZLJS:使用中医诊疗技术=" + homepage.getZyzljs() + "]填写不正确，《值域范围参考RC016》",
					"Item"
				));
		}
		/*
		 * 58. BZSH	辩证施护	字符	1	必填	《值域范围参考RC016》；参照卫统4-2病案首页值域
		 */
		col ++;
		if (homepage.getBzsh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BZSH",
					homepage.getBzsh(),
					"#" + row + "." + col + ".[BZSH:辩证施护=" + homepage.getBzsh() + "]未填写辩证施护",
					"Item"
				));
		} else if (!compareDic(rc016, RecordRangeType.code, homepage.getBzsh())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BZSH",
					homepage.getBzsh(),
					"#" + row + "." + col + ".[BZSH:辩证施护=" + homepage.getBzsh() + "]填写不正确，《值域范围参考RC016》",
					"Item"
				));
		}
		/*
		 * 59. ZB	主病出院中医诊断	字符	100	必填	采用中医病症分类代码国标版95（TCM95）与编码对应的病诊断名称
		 */
		col ++;
		if (homepage.getZb().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB",
					homepage.getZb(),
					"#" + row + "." + col + ".[ZB:主病出院中医诊断=" + homepage.getZb() + "]未填写主病出院中医诊断",
					"Item"
				));
		}
		/*
		 * 60. ZB_JBBM	主病疾病编码	字符	20	必填	采用中医病症分类代码国标版95（TCM95）
		 */
		col ++;
		if (homepage.getZbJbbm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB_JBBM",
					homepage.getZbJbbm(),
					"#" + row + "." + col + ".[ZB_JBBM:主病疾病编码=" + homepage.getZbJbbm() + "]未填写主病疾病编码",
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
					"#" + row + "." + col + ".[ZB:主病出院中医诊断=" + homepage.getZb() + "；ZB_JBBM:主病疾病编码=" + homepage.getZbJbbm() + "]逻辑验证未通过，与《中医病症分类代码国标版95（TCM95）》不匹配",
					"Item"
				));
		}
		/*
		 * 61. ZB_RYBQ	主病入院病情	字符	1	必填	《值域范围参考RC027-入院病情》
		 */
		col ++;
		if (homepage.getZbRybq().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB_RYBQ",
					homepage.getZbRybq(),
					"#" + row + "." + col + ".[ZB_RYBQ:主病入院病情=" + homepage.getZbRybq() + "]未填写主病入院病情",
					"Item"
				));
		} else if (!compareDic(rc027, RecordRangeType.code, homepage.getZbRybq())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZB_RYBQ",
					homepage.getZbRybq(),
					"#" + row + "." + col + ".[ZB_RYBQ:主病入院病情=" + homepage.getZbRybq() + "]填写不正确，《值域范围参考RC027-入院病情》",
					"Item"
				));
		}
		/*
		 * 62. ZYZD	出院主要诊断名称(西医)	字符	100	必填	采用疾病分类代码国家临床版2.0(ICD-10)与编码对应的诊断名称
		 */
		col ++;
		if (homepage.getZyzd().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZD",
					homepage.getZyzd(),
					"#" + row + "." + col + ".[ZYZD:出院主要诊断名称(西医)=" + homepage.getZyzd() + "]未填写出院主要诊断名称(西医)",
					"Item"
				));
		}
		/*
		 * 63. ZYZD_JBBM	出院主要诊断编码(西医)	字符	20	必填	采用疾病分类代码国家临床版2.0编码（ICD-10）
		 */
		col ++;
		if (homepage.getZyzdJbbm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZD_JBBM",
					homepage.getZyzdJbbm(),
					"#" + row + "." + col + ".[ZYZD_JBBM:出院主要诊断编码(西医)=" + homepage.getZyzdJbbm() + "]未填写出院主要诊断编码(西医)",
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
					"#" + row + "." + col + ".[ZYZD:出院主要诊断名称(西医)=" + homepage.getZyzd() + "；ZYZD_JBBM:出院主要诊断编码(西医)=" + homepage.getZyzdJbbm() + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0编码（ICD-10）》不匹配",
					"Item"
				));
		}
		/*
		 * 64. XY_RYBQ	出院主要诊断入院病情(西医)	字符	1	必填	《值域范围参考RC027-入院病情》
		 */
		col ++;
		if (homepage.getXyRybq().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XY_RYBQ",
					homepage.getXyRybq(),
					"#" + row + "." + col + ".[XY_RYBQ:出院主要诊断入院病情(西医)=" + homepage.getXyRybq() + "]未填写出院主要诊断入院病情(西医)",
					"Item"
				));
		} else if (!compareDic(rc027, RecordRangeType.code, homepage.getXyRybq())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XY_RYBQ",
					homepage.getXyRybq(),
					"#" + row + "." + col + ".[XY_RYBQ:出院主要诊断入院病情(西医)=" + homepage.getXyRybq() + "]填写不正确，《值域范围参考RC027-入院病情》",
					"Item"
				));
		}
		/*
		 * 65. ZZ1-ZZ7	主证出院中医诊断	字符	100	必填	最多采集7条；采用中医病症分类代码国标版95（TCM95）与编码对应的病诊断名称
		 * 66. ZZ_JBBM1-ZZ_JBBM7	主证疾病编码	字符	20	必填	最多采集7条；采用中医病症分类代码国标版95（TCM95）
		 * 67. ZZ_RYBQ1-ZZ_RYBQ7	主证住入院病情	字符	1	必填	最多采集7条；《值域范围参考RC027-入院病情》
		 */
		col ++;
		col ++;
		col ++;
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
							"#" + row + "." + col + ".[ZZ_JBBM" + i + ":主证疾病编码" + i + "=" + jbbmValue + "；ZZ" + i + ":主证出院中医诊断" + i + "=" + zzValue + "]逻辑验证未通过，与《中医病症分类代码国标版95（TCM95）》不匹配",
							"Item"
						));
				}
				if (rybqValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"ZZ_RYBQ" + i,
							rybqValue,
							"#" + row + "." + col + ".[ZZ_RYBQ" + i + ":主证住入院病情" + i + "=" + rybqValue + "]未填写主证住入院病情" + i,
							"Item"
						));
				} else if (!compareDic(rc027, RecordRangeType.code, rybqValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"ZZ_RYBQ" + i,
							rybqValue,
							"#" + row + "." + col + ".[ZZ_RYBQ" + i + ":主证住入院病情" + i + "=" + rybqValue + "]填写不正确，《值域范围参考RC027-入院病情》",
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
		col ++;
		col ++;
		col ++;
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
							"#" + row + "." + col + ".[ZYZD_JBBM" + i + ":出院其他诊断编码(西医)" + i + "=" + jbbmValue + "；QTZD" + i + ":出院其他诊断名称(西医)" + i + "=" + qtzdValue + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0(ICD-10)》不匹配",
							"Item"
						));
				}
				if (rybqValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"RYBQ" + i,
							rybqValue,
							"#" + row + "." + col + ".[RYBQ" + i + ":出院其他诊断入院病情(西医)" + i + "=" + rybqValue + "]未填写出院其他诊断入院病情(西医)" + i,
							"Item"
						));
				} else if (!compareDic(rc027, RecordRangeType.code, rybqValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"RYBQ" + i,
							rybqValue,
							"#" + row + "." + col + ".[RYBQ" + i + ":出院其他诊断入院病情(西医)" + i + "=" + rybqValue + "]填写不正确，《值域范围参考RC027-入院病情》",
							"Item"
						));
				}
			}
		}
		/*
		 * 71. WBYY	损伤、中毒外部原因名称	字符	100	条件必填	采用疾病分类代码国家临床版2.0(ICD-10)编码对应的外部原因名称；主要诊断ICD编码首字母为S或T时必填
		 * 72. JBBM1	损伤、中毒外部原因编码	字符	20	条件必填	采用疾病分类代码国家临床版2.0的编码(ICD-10)，主要诊断ICD编码首字母为S或T时必填
		 */
		col ++;
		col ++;
		if ((!homepage.getWbyy().isEmpty() || !homepage.getJbbm1().isEmpty()) && !diagnosisVerify(homepage.getJbbm1(), homepage.getWbyy(), RecordRangeType.DIAGNOSIS_WESTERN)) {
			if (homepage.getWbyy().isEmpty()) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"JBBM1",
						homepage.getJbbm1(),
						"#" + row + "." + col + ".[JBBM1:损伤、中毒外部原因编码=" + homepage.getJbbm1() + "；WBYY:损伤、中毒外部原因名称=" + homepage.getWbyy() + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0(ICD-10)》不匹配",
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
					"#" + row + "." + col + ".[JBBM1:损伤、中毒外部原因编码=" + homepage.getJbbm1() + "；WBYY:损伤、中毒外部原因名称=" + homepage.getWbyy() + "]逻辑验证未通过，主要诊断(" + homepage.getZyzdJbbm() + ")ICD编码首字母为S或T时必填",
					"Item"
				));
		}
		/*
		 * 73. BLZD	病理诊断名称	字符	100	条件必填	采用疾病分类代码国家临床版2.0版肿瘤形态学编码(M码)与编码对应的病理名称；主要诊断ICD编码首字母为C或D00-D48时必填
		 * 74. JBBM2	病理诊断编码	字符	20	条件必填	采用疾病分类代码国家临床版2.0版肿瘤形态学编码(M码)；主要诊断ICD编码首字母为C或D00-D48时必填
		 */
		col ++;
		col ++;
		if ((!homepage.getBlzd().isEmpty() || !homepage.getJbbm2().isEmpty()) && !diagnosisVerify(homepage.getJbbm2(), homepage.getBlzd(), RecordRangeType.DIAGNOSIS_PATHOLOGY)) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JBBM2",
					homepage.getJbbm2(),
					"#" + row + "." + col + ".[JBBM2:病理诊断编码=" + homepage.getJbbm2() + "；BLZD:病理诊断名称=" + homepage.getBlzd() + "]逻辑验证未通过，与《疾病分类代码国家临床版2.0(ICD-10)》不匹配",
					"Item"
				));
		}
		if (!homepage.getZyzdJbbm().isEmpty() && homepage.getZyzdJbbm().length() >= 3) {
			int diagnoCode = getInteger(homepage.getZyzdJbbm().substring(1, 3));
			if ((homepage.getZyzdJbbm().charAt(0) == 'C' || (diagnoCode >= 0 && diagnoCode <= 48))
				&& homepage.getJbbm2().isEmpty()) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"JBBM2",
						homepage.getJbbm2(),
						"#" + row + "." + col + ".[JBBM2:病理诊断编码=" + homepage.getJbbm2() + "；BLZD:病理诊断名称=" + homepage.getBlzd() + "]逻辑验证未通过，主要诊断(" + homepage.getZyzdJbbm() + ")ICD编码首字母为C或D00-D48时必填",
						"Item"
					));
			}
		}
		/*
		 * 75. BLH	病理号	字符	50	条件必填	有病理诊断编码时必填
		 */
		col ++;
		if (!homepage.getJbbm2().isEmpty() && homepage.getBlh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BLH",
					homepage.getBlh(),
					"#" + row + "." + col + ".[BLH:病理号=" + homepage.getBlh() + "]逻辑验证未通过，有病理诊断编码(" + homepage.getJbbm2() + ")时必填",
					"Item"
				));
		}
		/*
		 * 76. YWGM	有无药物过敏	字符	1	必填	《值域范围参考RC028-药物过敏》
		 */
		col ++;
		if (homepage.getYwgm().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YWGM",
					homepage.getYwgm(),
					"#" + row + "." + col + ".[YWGM:有无药物过敏=" + homepage.getYwgm() + "]未填写有无药物过敏",
					"Item"
				));
		} else if (!compareDic(rc028, RecordRangeType.code, homepage.getYwgm())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YWGM",
					homepage.getYwgm(),
					"#" + row + "." + col + ".[YWGM:有无药物过敏=" + homepage.getYwgm() + "]填写不正确，《值域范围参考RC028-药物过敏》",
					"Item"
				));
		}
		/*
		 * 77. GMYW	过敏药物名称	字符	200	条件必填	“有无药物过敏”为“有”时必填；多种药物用英文逗号进行分隔
		 */
		col ++;
		if (!homepage.getYwgm().isEmpty() && homepage.getYwgm().equals("2") && homepage.getGmyw().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"GMYW",
					homepage.getGmyw(),
					"#" + row + "." + col + ".[GMYW:过敏药物名称=" + homepage.getGmyw() + "；YWGM:有无药物过敏=" + homepage.getYwgm() + "]逻辑验证未通过，“有无药物过敏”为“有”时必填；多种药物用英文逗号进行分隔",
					"Item"
				));
		}
		/*
		 * 78. SJ	死亡患者尸检	字符	1	条件必填	《值域范围参考RC016-是否》；当离院方式为5死亡时，尸检为“1”或“2”。
		 */
		col ++;
		if (!homepage.getLyfs().isEmpty() && homepage.getLyfs().equals("5") && (homepage.getSj().isEmpty() || !compareDic(rc016, RecordRangeType.code, homepage.getSj()))) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SJ",
					homepage.getSj(),
					"#" + row + "." + col + ".[SJ:死亡患者尸检=" + homepage.getSj() + "]逻辑验证未通过，当离院方式为5死亡时，尸检为“1”或“2”",
					"Item"
				));
		}
		/*
		 * 79. XX	ABO血型	字符	1	必填	《值域范围参考RC030-血型编码》
		 */
		col ++;
		if (homepage.getXx().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XX",
					homepage.getXx(),
					"#" + row + "." + col + ".[XX:ABO血型=" + homepage.getXx() + "]未填写ABO血型",
					"Item"
				));
		} else if (!compareDic(rc030, RecordRangeType.code, homepage.getXx())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XX",
					homepage.getXx(),
					"#" + row + "." + col + ".[XX:ABO血型=" + homepage.getXx() + "]填写不正确，《值域范围参考RC030-血型编码》",
					"Item"
				));
		}
		/*
		 * 80. RH	Rh血型	字符	1	必填	《值域范围参考RC031-Rh血型》；当血费>0时，RH血型值为字典值。
		 */
		col ++;
		if (homepage.getRh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RH",
					homepage.getRh(),
					"#" + row + "." + col + ".[RH:Rh血型=" + homepage.getRh() + "]未填写Rh血型",
					"Item"
				));
		} else if (!compareDic(rc031, RecordRangeType.code, homepage.getRh())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RH",
					homepage.getRh(),
					"#" + row + "." + col + ".[RH:Rh血型=" + homepage.getRh() + "]填写不正确，《值域范围参考RC031-Rh血型》",
					"Item"
				));
		}
		if (homepage.getXf() != null && homepage.getXf() > 0 && !homepage.getRh().equals("1") && !homepage.getRh().equals("2")) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RH",
					homepage.getRh(),
					"#" + row + "." + col + ".[RH:Rh血型=" + homepage.getRh() + "]逻辑验证未通过，当血费>0时，RH血型值为“1”或“2”",
					"Item"
				));
		}
		/*
		 * 81. KZR	科主任	字符	40	必填
		 */
		col ++;
		if (homepage.getKzr().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"KZR",
					homepage.getKzr(),
					"#" + row + "." + col + ".[KZR:科主任=" + homepage.getKzr() + "]未填写科主任",
					"Item"
				));
		}
		/*
		 * 82. ZRYS	主任（副主任）医师	字符	40	必填
		 */
		col ++;
		if (homepage.getZrys().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZRYS",
					homepage.getZrys(),
					"#" + row + "." + col + ".[ZRYS:主任（副主任）医师=" + homepage.getZrys() + "]未填写主任（副主任）医师",
					"Item"
				));
		}
		/*
		 * 83. ZZYS	主治医师	字符	40	必填
		 */
		col ++;
		if (homepage.getZzys().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZZYS",
					homepage.getZzys(),
					"#" + row + "." + col + ".[ZZYS:主治医师=" + homepage.getZzys() + "]未填写主治医师",
					"Item"
				));
		}
		/*
		 * 84. ZYYS	住院医师	字符	40	必填
		 */
		col ++;
		if (homepage.getZyys().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYYS",
					homepage.getZyys(),
					"#" + row + "." + col + ".[ZYYS:住院医师=" + homepage.getZyys() + "]未填写住院医师",
					"Item"
				));
		}
		/*
		 * 85. ZRHS	责任护士	字符	40	必填
		 */
		col ++;
		if (homepage.getZrhs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZRHS",
					homepage.getZrhs(),
					"#" + row + "." + col + ".[ZRHS:责任护士=" + homepage.getZrhs() + "]未填写责任护士",
					"Item"
				));
		}
		/********* 不验证 **********
		 * 86. JXYS	进修医师	字符	40
		 * 87. SXYS	实习医师	字符	40
		 **************************/
		col ++;
		col ++;
		/*
		 * 88. BMY	编码员	字符	40	必填
		 */
		col ++;
		if (homepage.getBmy().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BMY",
					homepage.getBmy(),
					"#" + row + "." + col + ".[BMY:编码员=" + homepage.getBmy() + "]未填写编码员",
					"Item"
				));
		}
		/*
		 * 89. BAZL	病案质量	字符	1		《值域范围参考RC011-病案质量》
		 */
		col ++;
		if (!homepage.getBazl().isEmpty() && !compareDic(rc011, RecordRangeType.code, homepage.getBazl())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BAZL",
					homepage.getBazl(),
					"#" + row + "." + col + ".[BAZL:病案质量=" + homepage.getBazl() + "]填写不正确，《值域范围参考RC011-病案质量》",
					"Item"
				));
		}
		/********* 不验证 **********
		 * 90. ZKYS	质控医师	字符	40
		 * 91. ZKHS	质控护士	字符	40
		 **************************/
		col ++;
		col ++;
		/*
		 * 92. ZKRQ	质控日期	日期			格式 yyyy-MM-dd
		 */
		col ++;
		if (!homepage.getZkrq().isEmpty() && !isDate(homepage.getZkrq())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZKRQ",
					homepage.getZkrq(),
					"#" + row + "." + col + ".[ZKRQ:质控日期=" + homepage.getZkrq() + "]填写不正确，格式yyyy-MM-dd",
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
		 *
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
		for (int i = 1; i <= 41; i ++) {
			col = col + 22;
			String preText = i == 1 ? "主要" : "其他";
			String ssjczbmValue = homepage.getClass().getMethod("getSsjczbm" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String ssjczmcValue = homepage.getClass().getMethod("getSsjczmc" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String ssjczrqValue = homepage.getClass().getMethod("getSsjczrq" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String shjbValue = homepage.getClass().getMethod("getShjb" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String szValue = homepage.getClass().getMethod("getSz" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String yzValue = homepage.getClass().getMethod("getYz" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String qkdjValue = homepage.getClass().getMethod("getQkdj" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String qkylbValue = homepage.getClass().getMethod("getQkylb" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String mzfsValue = homepage.getClass().getMethod("getMzfs" + i, new Class<?>[0]).invoke(new Object[0]).toString();
			String mzysValue = homepage.getClass().getMethod("getMzys" + i, new Class<?>[0]).invoke(new Object[0]).toString();

			if (!ssjczbmValue.isEmpty()) {
				//手术诊断
				if (!diagnosisVerify(ssjczbmValue, ssjczmcValue, RecordRangeType.DIAGNOSIS_OPERATION)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"SSJCZBM" + i,
							ssjczbmValue,
							"#" + row + "." + col + ".[SSJCZBM" + i + ":" + preText + "手术操作编码=" + ssjczbmValue + "；SSJCZMC" + i + ":" + preText + "手术操作名称=" + ssjczmcValue + "]诊断验证未通过，与《手术操作分类代码国家临床版2.0编码（ICD-9-CM3）》不匹配",
							"Item"
						));
				} else {
					hasSs = true;
				}
				//手术时间
				if (ssjczrqValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"SSJCZRQ" + i,
							ssjczrqValue,
							"#" + row + "." + col + ".[SSJCZRQ" + i + ":" + preText + "手术操作日期=" + ssjczrqValue + "]未填写" + preText + "手术操作日期",
							"Item"
						));
				} else if (!isDatetime(ssjczrqValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"SSJCZRQ" + i,
							ssjczrqValue,
							"#" + row + "." + col + ".[SSJCZRQ" + i + ":" + preText + "手术操作日期=" + ssjczrqValue + "]填写不正确，格式yyyy-MM-dd HH:mm:ss",
							"Item"
						));
				}
				//手术级别
				if (shjbValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"SHJB" + i,
							shjbValue,
							"#" + row + "." + col + ".[SHJB" + i + ":" + preText + "手术操作级别=" + shjbValue + "]未填写" + preText + "手术操作级别",
							"Item"
						));
				} else if (!compareDic(rc029, RecordRangeType.code, shjbValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"SHJB" + i,
							shjbValue,
							"#" + row + "." + col + ".[SHJB" + i + ":" + preText + "手术操作级别=" + shjbValue + "]填写不正确，《值域范围参考RC029》",
							"Item"
						));
				}
				//术者
				if (szValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"SZ" + i,
							szValue,
							"#" + row + "." + col + ".[SZ" + i + ":" + preText + "手术操作术者=" + szValue + "]未填写" + preText + "手术操作术者",
							"Item"
						));
				}
				//Ⅰ助
				if (yzValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"YZ" + i,
							yzValue,
							"#" + row + "." + col + ".[YZ" + i + ":" + preText + "手术操作Ⅰ助=" + yzValue + "]未填写" + preText + "手术操作Ⅰ助",
							"Item"
						));
				}
				//切口愈合等级
				if (qkdjValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"QKDJ" + i,
							qkdjValue,
							"#" + row + "." + col + ".[QKDJ" + i + ":" + preText + "手术操作切口愈合等级=" + qkdjValue + "]未填写" + preText + "手术操作切口愈合等级",
							"Item"
						));
				} else if (!compareDic(rc014_1, RecordRangeType.code, qkdjValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"QKDJ" + i,
							qkdjValue,
							"#" + row + "." + col + ".[QKDJ" + i + ":" + preText + "手术操作切口愈合等级=" + qkdjValue + "]填写不正确，《值域范围参考RC014-切开愈合等级》",
							"Item"
						));
				}
				//切口愈合类别
				if (qkylbValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"QKYLB" + i,
							qkylbValue,
							"#" + row + "." + col + ".[QKYLB" + i + ":" + preText + "手术操作切口愈合类别=" + qkylbValue + "]未填写" + preText + "手术操作切口愈合类别",
							"Item"
						));
				} else if (!compareDic(rc014_2, RecordRangeType.code, qkylbValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"QKYLB" + i,
							qkylbValue,
							"#" + row + "." + col + ".[QKYLB" + i + ":" + preText + "手术操作切口愈合类别=" + qkylbValue + "]填写不正确，《值域范围参考RC014-切开愈合等级》",
							"Item"
						));
				}
				//麻醉方式
				if (mzfsValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"MZFS" + i,
							mzfsValue,
							"#" + row + "." + col + ".[MZFS" + i + ":" + preText + "手术操作麻醉方式=" + mzfsValue + "]未填写" + preText + "手术操作麻醉方式",
							"Item"
						));
				} else if (!compareDic(rc013, RecordRangeType.code, mzfsValue)) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"MZFS" + i,
							mzfsValue,
							"#" + row + "." + col + ".[MZFS" + i + ":" + preText + "手术操作麻醉方式=" + mzfsValue + "]填写不正确，《值域范围参考RC013-麻醉方式》",
							"Item"
						));
				} else {
					hasMz = true;
				}
				//麻醉医师
				if (mzysValue.isEmpty()) {
					checkRecord.add(new InpatientHomepageAnalyCheck(
							checkIndex ++,
							homepage.getId(),
							"MZYS" + i,
							mzysValue,
							"#" + row + "." + col + ".[MZYS" + i + ":" + preText + "手术操作麻醉医师=" + mzysValue + "]未填写" + preText + "手术操作麻醉医师",
							"Item"
						));
				}
			}
		}
		/*
		 * 115. LYFS	离院方式	字符	1	必填	《值域范围参考RC019》；指患者本次住院出院的方式，填写相应的阿拉伯数字
		 * 116. YZZY_JGMC	医嘱转院，拟接收医疗机构名称	字符	100	条件必填	离院方式为医嘱转院患者必填
		 * 117. WSY_JGMC	医嘱转社区卫生服务机构/乡镇卫生院，拟接收医疗机构名称	字符	100	条件必填	离院方式为医嘱转社区患者必填
		 */
		col ++;
		col ++;
		col ++;
		if (homepage.getLyfs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"LYFS",
					homepage.getLyfs(),
					"#" + row + "." + col + ".[LYFS:离院方式=" + homepage.getLyfs() + "]未填写离院方式",
					"Item"
				));
		} else if (!compareDic(rc019, RecordRangeType.code, homepage.getLyfs())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"LYFS",
					homepage.getLyfs(),
					"#" + row + "." + col + ".[LYFS:离院方式=" + homepage.getLyfs() + "]填写不正确，《值域范围参考RC019》",
					"Item"
				));
		} else if (homepage.getLyfs().equals("2") && homepage.getYzzyJgmc().isEmpty()) {
			//医嘱转院
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YZZY_JGMC",
					homepage.getYzzyJgmc(),
					"#" + row + "." + col + ".[YZZY_JGMC:医嘱转院=" + homepage.getYzzyJgmc() + "]未填写，离院方式为医嘱转院患者必填",
					"Item"
				));
		} else if (homepage.getLyfs().equals("3") && homepage.getWsyJgmc().isEmpty()) {
			//医嘱转社区卫生服务机构/乡镇卫生院
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"WSY_JGMC",
					homepage.getWsyJgmc(),
					"#" + row + "." + col + ".[WSY_JGMC:医嘱转社区卫生服务机构/乡镇卫生院=" + homepage.getWsyJgmc() + "]未填写，离院方式为医嘱转社区患者必填",
					"Item"
				));
		}
		/*
		 * 118. ZZYJH	是否有出院31天内再住院计划	数字	1	必填	《值域范围参考RC016》，指患者本次住院出院后31天内是否有诊疗需要的再住院安排。如果有再住院计划，则需要填写目的，如：进行二次手术
		 * 119. MD	目的	字符	100	条件必填	是否有出院31日内再住院计划填“有”时必填
		 */
		col ++;
		col ++;
		if (homepage.getZzyjh().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZZYJH",
					homepage.getZzyjh(),
					"#" + row + "." + col + ".[ZZYJH:是否有出院31天内再住院计划=" + homepage.getZzyjh() + "]未填写是否有出院31天内再住院计划",
					"Item"
				));
		} else if (!compareDic(rc037, RecordRangeType.code, homepage.getZzyjh())) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZZYJH",
					homepage.getZzyjh(),
					"#" + row + "." + col + ".[ZZYJH:是否有出院31天内再住院计划=" + homepage.getZzyjh() + "]填写不正确，《值域范围参考RC037》",
					"Item"
				));
		} else if (homepage.getZzyjh().equals("2") && homepage.getMd().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MD",
					homepage.getMd(),
					"#" + row + "." + col + ".[MD:目的=" + homepage.getMd() + "]未填写目的，是否有出院31日内再住院计划填“有”时必填",
					"Item"
				));
		}
		/*
		 * 120. RYQ_T	颅脑损伤患者昏迷入院前时间（天)	数字	5	必填	大于等于0整数。
		 * 121. RYQ_XS	小时	数字	2	必填	大于等于0，小于24整数。
		 * 122. RYQ_FZ	分钟	数字	2	必填	大于等于0，小于60整数。
		 */
		col ++;
		col ++;
		col ++;
		if (homepage.getRyqT().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYQ_T",
					homepage.getRyqT(),
					"#" + row + "." + col + ".[RYQ_T:颅脑损伤患者昏迷入院前时间（天)=" + homepage.getRyqT() + "]未填写颅脑损伤患者昏迷入院前时间（天)",
					"Item"
				));
		}
		if (homepage.getRyqXs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYQ_XS",
					homepage.getRyqXs(),
					"#" + row + "." + col + ".[RYQ_XS:颅脑损伤患者昏迷入院前时间（小时)=" + homepage.getRyqXs() + "]未填写颅脑损伤患者昏迷入院前时间（小时)",
					"Item"
				));
		}
		if (homepage.getRyqFz().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYQ_FZ",
					homepage.getRyqFz(),
					"#" + row + "." + col + ".[RYQ_FZ:颅脑损伤患者昏迷入院前时间（分钟)=" + homepage.getRyqFz() + "]未填写颅脑损伤患者昏迷入院前时间（分钟)",
					"Item"
				));
		}
		//逻辑校验
		if (!homepage.getRyqT().isEmpty() && isInteger(homepage.getRyqT())) {
			if (getInteger(homepage.getRyqT()) < 0) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"RYQ_T",
						homepage.getRyqT(),
						"#" + row + "." + col + ".[RYQ_T:颅脑损伤患者昏迷入院前时间（天)=" + homepage.getRyqT() + "]填写不正确，大于等于0的整数",
						"Item"
					));
			}
			if (!isInteger(homepage.getRyqXs()) || getInteger(homepage.getRyqXs()) < 0) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"RYQ_XS",
						homepage.getRyqXs(),
						"#" + row + "." + col + ".[RYQ_XS:小时=" + homepage.getRyqXs() + "]填写不正确，大于等于0，小于24整数",
						"Item"
					));
			}
			if (!isInteger(homepage.getRyqXs()) || getInteger(homepage.getRyqXs()) < 0) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"RYQ_FZ",
						homepage.getRyqXs(),
						"#" + row + "." + col + ".[RYQ_FZ:分钟=" + homepage.getRyqXs() + "]填写不正确，大于等于0，小于60整数",
						"Item"
					));
			}
		}
		/*
		 * 123. RYH_T	颅脑损伤患者昏迷时间天	数字	5	必填	大于等于0整数。
		 * 124. RYH_XS	小时	数字	2	必填	大于等于0，小于24整数。
		 * 125. RYH_FZ	分钟	数字	2	必填	大于等于0，小于60整数。
		 */
		col ++;
		col ++;
		col ++;
		if (homepage.getRyhT().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYH_T",
					homepage.getRyhT(),
					"#" + row + "." + col + ".[RYH_T:颅脑损伤患者昏迷时间（天)=" + homepage.getRyhT() + "]未填写颅脑损伤患者昏迷时间（天)",
					"Item"
				));
		}
		if (homepage.getRyhXs().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYH_XS",
					homepage.getRyhXs(),
					"#" + row + "." + col + ".[RYH_XS:颅脑损伤患者昏迷时间（小时)=" + homepage.getRyhXs() + "]未填写颅脑损伤患者昏迷时间（小时)",
					"Item"
				));
		}
		if (homepage.getRyhFz().isEmpty()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"RYH_FZ",
					homepage.getRyhFz(),
					"#" + row + "." + col + ".[RYH_FZ:颅脑损伤患者昏迷时间（分钟)=" + homepage.getRyhFz() + "]未填写颅脑损伤患者昏迷时间（分钟)",
					"Item"
				));
		}
		//逻辑校验
		if (!homepage.getRyhT().isEmpty() && isInteger(homepage.getRyhT())) {
			if (getInteger(homepage.getRyhT()) < 0) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"RYH_T",
						homepage.getRyhT(),
						"#" + row + "." + col + ".[RYH_T:颅脑损伤患者昏迷时间（天)=" + homepage.getRyhT() + "]填写不正确，大于等于0的整数",
						"Item"
					));
			}
			if (!isInteger(homepage.getRyhXs()) || getInteger(homepage.getRyhXs()) < 0) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"RYH_XS",
						homepage.getRyhXs(),
						"#" + row + "." + col + ".[RYH_XS:小时=" + homepage.getRyhXs() + "]填写不正确，大于等于0，小于24整数",
						"Item"
					));
			}
			if (!isInteger(homepage.getRyhXs()) || getInteger(homepage.getRyhXs()) < 0) {
				checkRecord.add(new InpatientHomepageAnalyCheck(
						checkIndex ++,
						homepage.getId(),
						"RYH_FZ",
						homepage.getRyhXs(),
						"#" + row + "." + col + ".[RYH_FZ:分钟=" + homepage.getRyhXs() + "]填写不正确，大于等于0，小于60整数",
						"Item"
					));
			}
		}
		/*
		 * 126. ZFY	总费用	数字	(10,2)	必填	住院总费用必填且大于0；总费用大于或等于分项费用之和；
		 */
		col ++;
		double _zfy = homepage.getYlfwf()//综合医疗服务类(1)一般医疗服务费
				+ homepage.getZlczf()//(2)一般治疗操作费
				+ homepage.getHlf()//(3)护理费
				+ homepage.getQtfy()//(4)其他费用
				+ homepage.getBlzdf()//诊断类(5)病理诊断费
				+ homepage.getZdf()//(6)实验室诊断费
				+ homepage.getYxxzdf()//(7)影像学诊断费
				+ homepage.getLczdxmf()//(8)临床诊断项目费
				+ homepage.getFsszlxmf()//治疗类(9)非手术治疗项目费(临床物理治疗费)
				+ homepage.getSszlf()//(10)手术治疗费(麻醉费+手术费)
				+ homepage.getKff()//康复类(11)康复费
				+ homepage.getZylZyzd()//中医类(中医和名族医医疗服务)（12）中医诊断
				+ homepage.getZyzl()//(13)中医治疗(中医外治+中医骨伤+针刺与灸法+中医推拿治疗+中医肛肠治疗+中医特殊治疗)
				+ homepage.getZyqt()//(14)中医其他(中医特殊调配加工+辨证施膳)
				+ homepage.getXyf()//西药类(15)西药费(抗菌药物费)
				+ homepage.getZcyf()//中药类(16)中成药费(医疗机构中药制剂费)
				+ homepage.getZcyf1()//(17)中草药费
				+ homepage.getXf()//血液和血液制品类(18)血费
				+ homepage.getBdblzpf()//(19)白蛋白类制品费
				+ homepage.getQdblzpf()//(20)球蛋白类制品费
				+ homepage.getNxyzlzpf()//(21)凝血因子类制品费
				+ homepage.getXbyzlzpf()//(22)细胞因子类制品费
				+ homepage.getJcyyclf()//耗材类(23)检查用一次性医用材料费
				+ homepage.getYyclf()//(24)治疗用一次性医用材料费
				+ homepage.getSsycxclf()//(25)手术用一次性医用材料费
				+ homepage.getQtf()//其他类(26)其他费
				;
		if (_zfy != homepage.getZfy()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZFY",
					homepage.getZfy().toString(),
					"#" + row + "." + col + ".[ZFY:总费用=" + homepage.getZfy() + "]逻辑验证未通过，总费用≠费用合计(" + _zfy + ")",
					"Item"
				));
		}
		/* 
		 * 127. ZFJE	自付金额	数字	(10,2)	必填	小于等于总费用
		 */
		col ++;
		if (homepage.getZfje() < homepage.getZfy()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZFJE",
					homepage.getZfje().toString(),
					"#" + row + "." + col + ".[ZFJE:自付金额=" + homepage.getZfje() + "]逻辑验证未通过，小于等于总费用(" + homepage.getZfy() + ")",
					"Item"
				));
		}
		/* 
		 * 128. YLFWF	综合医疗服务类(1)一般医疗服务费	数字	(10,2)	必填	住院天数>1时，(1)一般医疗服务费>0。
		 */
		col ++;
		if (isInteger(homepage.getSjzy()) && getInteger(homepage.getSjzy()) > 1 && homepage.getYlfwf() <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YLFWF",
					homepage.getYlfwf().toString(),
					"#" + row + "." + col + ".[YLFWF:(1)一般医疗服务费=" + homepage.getYlfwf() + "]逻辑验证未通过，住院天数>1时(" + homepage.getSjzy() + ")，(1)一般医疗服务费>0",
					"Item"
				));
		}
		//验证(1)一般医疗服务费是否小于中医辨证论治费+中医辨证论治会诊费
		if (homepage.getYlfwf() < homepage.getBzlzf()+homepage.getZyblzhzf()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"YLFWF",
					homepage.getYlfwf().toString(),
					"#" + row + "." + col + ".[YLFWF:(1)一般医疗服务费=" + homepage.getYlfwf() + "]逻辑验证未通过，(1)一般医疗服务费应≥中医辨证论治费( " + homepage.getBzlzf() + " )+中医辨证论治会诊费(" + homepage.getZyblzhzf() + ")",
					"Item"
				));
		}
		/* 
		 * 129. BZLZF	中医辨证论治费	数字	(10,2)	条件必填	发生辨证论治费时，必填。小于总费用。
		 */
		col ++;
		if (homepage.getBzlzf() >= homepage.getZfy()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"BZLZF",
					homepage.getBzlzf().toString(),
					"#" + row + "." + col + ".[BZLZF:中医辨证论治费=" + homepage.getBzlzf() + "]逻辑验证未通过，发生辨证论治费时，必填。小于总费用(" + homepage.getZfy() + ")",
					"Item"
				));
		}
		/* 
		 * 130. ZYBLZHZF	中医辨证论治会诊费	数字	(10,2)	条件必填	发生辨证论治会诊费时，必填。小于总费用。
		 */
		col ++;
		if (homepage.getZyblzhzf() >= homepage.getZfy()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYBLZHZF",
					homepage.getZyblzhzf().toString(),
					"#" + row + "." + col + ".[ZYBLZHZF:中医辨证论治会诊费=" + homepage.getZyblzhzf() + "]逻辑验证未通过，发生辨证论治会诊费时，必填。小于总费用(" + homepage.getZfy() + ")",
					"Item"
				));
		}
		/* 
		 * 131. ZLCZF	(2)一般治疗操作费	数字	(10,2)
		 */
		col ++;
		/* 
		 * 132. HLF	(3)护理费	数字	(10,2)		住院天数>1时，(3)护理费>0。
		 */
		col ++;
		if (isInteger(homepage.getSjzy()) && getInteger(homepage.getSjzy()) > 1 && homepage.getHlf() <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"HLF",
					homepage.getHlf().toString(),
					"#" + row + "." + col + ".[HLF:(3)护理费=" + homepage.getHlf() + "]逻辑验证未通过，住院天数>1时(" + homepage.getSjzy() + ")，(3)护理费>0",
					"Item"
				));
		}
		/* 
		 * 133. QTFY	(4)其他费用	数字	(10,2)
		 * 134. BLZDF	诊断类(5)病理诊断费	数字	(10,2)
		 * 135. ZDF	(6)实验室诊断费	数字	(10,2)
		 * 136. YXXZDF	(7)影像学诊断费	数字	(10,2)
		 * 137. LCZDXMF	(8)临床诊断项目费	数字	(10,2)
		 */
		col = col + 5;
		/* 
		 * 138. FSSZLXMF	治疗类(9)非手术治疗项目费	数字	(10,2)
		 * 139. ZLF	临床物理治疗费	数字	(10,2)
		 */
		col = col + 2;
		if (homepage.getFsszlxmf() < homepage.getZlf()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"FSSZLXMF",
					homepage.getFsszlxmf().toString(),
					"#" + row + "." + col + ".[FSSZLXMF:(9)非手术治疗项目费=" + homepage.getFsszlxmf() + "]逻辑验证未通过，(9)非手术治疗项目费≥临床物理治疗费(" + homepage.getZlf() + ")",
					"Item"
				));
		}
		/* 
		 * 140. SSZLF	(10)手术治疗费	数字	(10,2)		手术治疗费>=麻醉费+手术费。
		 */
		col ++;
		if (homepage.getSszlf() < homepage.getMzf()+homepage.getSsf()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SSZLF",
					homepage.getSszlf().toString(),
					"#" + row + "." + col + ".[SSZLF:(10)手术治疗费=" + homepage.getSszlf() + "]逻辑验证未通过，手术治疗费>=麻醉费(" + homepage.getMzf() + ")+手术费(" + homepage.getSsf() + ")",
					"Item"
				));
		}
		/* 
		 * 141. MZF	麻醉费	数字	(10,2)		麻醉方式有值时，麻醉费>0。
		 */
		col ++;
		if (hasMz && homepage.getMzf() <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"MZF",
					homepage.getMzf().toString(),
					"#" + row + "." + col + ".[MZF:麻醉费=" + homepage.getMzf() + "]逻辑验证未通过，麻醉方式有值时，麻醉费>0",
					"Item"
				));
		}
		/* 
		 * 142. SSF	手术费	数字	(10,2)		发生手术时，手术费用>0。
		 */
		col ++;
		if (hasSs && homepage.getSsf() <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"SSF",
					homepage.getSsf().toString(),
					"#" + row + "." + col + ".[SSF:手术费=" + homepage.getSsf() + "]逻辑验证未通过，发生手术时，手术费用>0",
					"Item"
				));
		}
		/* 
		 * 143. KFF	康复类(11)康复费	数字	(10,2)
		 */
		col ++;
		/* 
		 * 144. ZYL_ZYZD	中医类(中医和名族医医疗服务)（12）中医诊断	数字	(10,2)	条件必填	发生中医诊断费时，必填；小于总费用；治疗类别为"1或2"且住院天数>1时，（12）中医诊断+(13)中医治疗+(14)中医其他>0；
		 */
		col ++;
		if ((homepage.getZllb().equals("1") || homepage.getZllb().equals("2"))
			&& getInteger(homepage.getSjzy()) > 1
			&& homepage.getZylZyzd()+homepage.getZyzl()+homepage.getZyqt() <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYL_ZYZD",
					homepage.getZylZyzd().toString(),
					"#" + row + "." + col + ".[ZYL_ZYZD:（12）中医诊断=" + homepage.getZylZyzd() + "]逻辑验证未通过，治疗类别为“1或2”且住院天数>1时，（12）中医诊断+(13)中医治疗+(14)中医其他>0",
					"Item"
				));
		}
		/* 
		 * 145. ZYZL	(13)中医治疗	数字	(10,2)	条件必填	发生中医治疗费时，必填；小于总费用；中医治疗费>=中医外治+中医骨伤+针刺与灸法+中医推拿治疗+中医肛肠治疗+中医特殊治疗；治疗类别为"1或2"且住院天数>1时，(13)中医治疗+(16)中成药费+(17)中草药费>0。
		 */
		col ++;
		//中医治疗费>=中医外治+中医骨伤+针刺与灸法+中医推拿治疗+中医肛肠治疗+中医特殊治疗；
		//治疗类别为"1或2"且住院天数>1时，(13)中医治疗+(16)中成药费+(17)中草药费>0
		Double zyzl = homepage.getZywz()+homepage.getZygs()+homepage.getZcyjf()+homepage.getZytnzl()+homepage.getZygczl()+homepage.getZytszl();
		if (homepage.getZyzl() < homepage.getZywz()+homepage.getZygs()+homepage.getZcyjf()+homepage.getZytnzl()+homepage.getZygczl()+homepage.getZytszl()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZL",
					homepage.getZyzl().toString(),
					"#" + row + "." + col + ".[ZYZL:(13)中医治疗=" + homepage.getZyzl() + "]逻辑验证未通过，中医治疗费>=(" + zyzl + ")中医外治(" + homepage.getZywz() + ")+中医骨伤(" + homepage.getZygs() + ")+针刺与灸法(" + homepage.getZcyjf() + ")+中医推拿治疗(" + homepage.getZytnzl() + ")+中医肛肠治疗(" + homepage.getZygczl() + ")+中医特殊治疗(" + homepage.getZytszl() + ")",
					"Item"
				));
		}
		if ((homepage.getZllb().equals("1") || homepage.getZllb().equals("2"))
			&& homepage.getZyzl()+homepage.getZcyf()+homepage.getZcyf1() <= 0)
		/* 
		 * 146. ZYWZ	中医外治	数字	(10,2)	条件必填	发生中医外治费时，必填。小于总费用。
		 * 147. ZYGS	中医骨伤	数字	(10,2)	条件必填	发生中医骨伤费时，必填。小于总费用。
		 * 148. ZCYJF	针刺与灸法	数字	(10,2)	条件必填	发生针刺与灸法费时，必填。小于总费用。
		 * 149. ZYTNZL	中医推拿治疗	数字	(10,2)	条件必填	发生中医推拿治疗费时，必填。小于总费用。
		 * 150. ZYGCZL	中医肛肠治疗	数字	(10,2)	条件必填	发生中医肛肠治疗费时，必填。小于总费用。
		 * 151. ZYTSZL	中医特殊治疗	数字	(10,2)	条件必填	发生中医特殊治疗费时，必填。小于总费用。
		 * 152. ZYQT	(14)中医其他	数字	(10,2)	条件必填	发生中医其他费时，必填。小于总费用。
		 * 153. ZYTSTPJG	中医特殊调配加工	数字	(10,2)	条件必填	发生中医特殊调配加工费时，必填。小于总费用。
		 * 154. BZSS	辨证施膳	数字	(10,2)	条件必填	发生辨证施膳费时，必填。小于总费用。
		 */
		col = col + 9;
		/* 155. XYF	西药类(15)西药费	数字	(10,2)
		 */
		col ++;
		if (homepage.getXyf() < homepage.getKjywf()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"XYF",
					homepage.getXyf().toString(),
					"#" + row + "." + col + ".[XYF:(15)西药费=" + homepage.getXyf() + "]逻辑验证未通过，(15)西药费≥抗菌药物费(" + homepage.getKjywf() + ")",
					"Item"
				));
		}
		/* 156. KJYWF	抗菌药物费	数字	(10,2)
		 */
		col ++;
		/* 
		 * 157. ZCYF	中药类(16)中成药费	数字	(10,2)	条件必填	发生中成药费时，必填；中成药费>=医疗机构中药制剂费。
		 */
		col ++;
		if (homepage.getZcyf() < homepage.getZyzjf()) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZCYF",
					homepage.getZcyf().toString(),
					"#" + row + "." + col + ".[ZCYF:(16)中成药费=" + homepage.getZcyf() + "]逻辑验证未通过，中成药费>=医疗机构中药制剂费(" + homepage.getZyzjf() + ")",
					"Item"
				));
		}
		/* 
		 * 158. ZYZJF	医疗机构中药制剂费	数字	(10,2)	条件必填	使用医疗机构中药制剂值为"是"时，必填，且值大于0，小于总费用。
		 */
		col ++;
		if (homepage.getZyyj().equals("1") && homepage.getZyzjf() <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"ZYZJF",
					homepage.getZyzjf().toString(),
					"#" + row + "." + col + ".[ZYZJF:医疗机构中药制剂费=" + homepage.getZyzjf() + "]逻辑验证未通过，使用医疗机构中药制剂值为“是”时，必填，且值大于0，小于总费用",
					"Item"
				));
		}
		/* 
		 * 159. ZCYF1	(17)中草药费	数字	(10,2)	条件必填	发生中草药费费时，必填。小于总费用。
		 * 160. XF	血液和血液制品类(18)血费	数字	(10,2)
		 * 161. BDBLZPF	(19)白蛋白类制品费	数字	(10,2)
		 * 162. QDBLZPF	(20)球蛋白类制品费	数字	(10,2)
		 * 163. NXYZLZPF	(21)凝血因子类制品费	数字	(10,2)
		 * 164. XBYZLZPF	(22)细胞因子类制品费	数字	(10,2)
		 */
		col = col + 6;
		/* 
		 * 165. JCYYCLF	耗材类(23)检查用一次性医用材料费	数字	(10,2)		住院天数>1时，(23)检查用一次性医用材料费+(24)治疗用一次性医用材料费+(25)手术用一次性医用材料费>0
		 */
		col ++;
		if (getInteger(homepage.getSjzy()) > 1 && homepage.getJcyyclf()+homepage.getYyclf()+homepage.getSsycxclf() <= 0) {
			checkRecord.add(new InpatientHomepageAnalyCheck(
					checkIndex ++,
					homepage.getId(),
					"JCYYCLF",
					homepage.getJcyyclf().toString(),
					"#" + row + "." + col + ".[JCYYCLF:(23)检查用一次性医用材料费=" + homepage.getJcyyclf() + "]逻辑验证未通过，住院天数>1时(" + homepage.getSjzy() + ")，(23)检查用一次性医用材料费(" + homepage.getJcyyclf() + ")+(24)治疗用一次性医用材料费(" + homepage.getYyclf() + ")+(25)手术用一次性医用材料费(" + homepage.getSsycxclf() + ")>0",
					"Item"
				));
		}
		/* 
		 * 166. YYCLF	(24)治疗用一次性医用材料费	数字	(10,2)
		 * 167. SSYCXCLF	(25)手术用一次性医用材料费	数字	(10,2)
		 * 168. QTF	其他类(26)其他费	数字	(10,2)		住院天数>1时，（26）其他类<总费用
		 */
		col = col + 3;

		homepage.setChecked(checkRecord.size());
	}

	public boolean compareDic(Dictionary[] dic, RecordRangeType type, String keyword) {
		boolean result = false;
		initialize();
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
		initialize();
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

	public boolean diagnosisVerify(String code, String diagnosis, RecordRangeType type) throws Exception {
		initialize();
		boolean result = false;
		IcdTransform[] diagnoses = null;

		if (type.equals(RecordRangeType.DIAGNOSIS_CHINESE)) diagnoses = icd20DiagnosisChinese;
		else if (type.equals(RecordRangeType.DIAGNOSIS_WESTERN)) diagnoses = icd20DiagnosisWestern;
		else if (type.equals(RecordRangeType.DIAGNOSIS_OPERATION)) diagnoses = icd20DiagnosisOperation;
		else if (type.equals(RecordRangeType.DIAGNOSIS_PATHOLOGY)) diagnoses = icd20DiagnosisPathology;

		for (IcdTransform diagno : diagnoses) {
			result = diagno.getCode().equals(code) && diagno.getDiagno().equals(diagnosis);
			if (result) break;
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

	public int getInteger(String num) {
		try {
			return Integer.parseInt(num);
		}
		catch(Exception ex) {
			return 0;
		}
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
