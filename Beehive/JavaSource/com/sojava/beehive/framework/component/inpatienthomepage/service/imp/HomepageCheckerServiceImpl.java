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

				if (type.equals(Double.class)) {
					Double val = null;
					try {
						val = value.equals("") ? null : Double.parseDouble(value);
						checkColumn(name, val);
					}
					catch(NumberFormatException e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, "不是合法的数值", "Column"));
					}
					catch(Exception e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, e.getMessage(), "Column"));
					}
					homepage.getClass().getMethod(method, Double.class).invoke(homepage, val);
				} else if (type.equals(Integer.class)) {
					Integer val = null;
					try {
						val = value.equals("") ? null : Integer.parseInt(value);
						checkColumn(name, val);
					}
					catch(NumberFormatException e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, "不是合法的数值", "Column"));
					}
					catch(Exception e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, e.getMessage(), "Column"));
					}
					homepage.getClass().getMethod(method, Integer.class).invoke(homepage, val);
				} else if (type.equals(Short.class)) {
					Short val = null;
					try {
						val = value.equals("") ? null : Short.parseShort(value);
						checkColumn(name, val);
					}
					catch(NumberFormatException e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, "不是合法的数值", "Column"));
					}
					catch(Exception e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, e.getMessage(), "Column"));
					}
					homepage.getClass().getMethod(method, Short.class).invoke(homepage, val);
				} else if (type.equals(Date.class)) {
					Date val = null;
					try {
						val = value.equals("") ? null : FormatUtil.parseDate(value);
						checkColumn(name, val);
					}
					catch(ParseException e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, "不是合法的日期", "Column"));
					}
					catch(Exception e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, e.getMessage(), "Column"));
					}
					homepage.getClass().getMethod(method, Date.class).invoke(homepage, val);
				} else if (type.equals(Timestamp.class)) {
					Timestamp val = null;
					try {
						val = value.equals("") ? null : new Timestamp(FormatUtil.parseDateTime(value).getTime());
						checkColumn(name, val);
					}
					catch(ParseException e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, "不是合法的时间", "Column"));
					}
					catch(Exception e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, e.getMessage(), "Column"));
					}
					homepage.getClass().getMethod(method, Timestamp.class).invoke(homepage, val);
				} else {
					try {
						checkColumn(name, value);
					}
					catch(Exception e) {
						checked ++;
						this.checkRecord.add(new InpatientHomepageAnalyCheck(this.checkIndex ++, homepage.getId(), name, value, e.getMessage(), "Column"));
					}
					homepage.getClass().getMethod(method, String.class).invoke(homepage, value);
				}
			}
		}
		catch(Exception ex) {
			this.checkRecord.add(new InpatientHomepageAnalyCheck(
					this.checkIndex ++,
					homepage.getId(),
					name,
					value,
					"在处理第 " + record.getRecordNumber() + " 行" + (name != null ? "[" + name + "=" + (value == null ? "" : value) + "]" : "") + "时发生严重错误 [" + ex.getMessage() + "]",
					"Record"
				)
			);
		}
		homepage.setChecked(checked);
		homepageList.add(homepage);
	}

	@Override
	public void checkColumn(String name, Object value) throws Exception {
		/*
		 * 1. ZZJGDM	组织机构代码	字符	22	必填	指医疗机构执业许可证上面的机构代码。
		 */
		if (name.equals("zzjgdm") && (value.toString().equals("") || value.toString().length() != 22)) {
			throw new Exception("[ZZJGDM:组织机构代码=" + value.toString() + "]错误");
		}
		/*
		 * 2. JGMC	医疗机构名称	字符	80	必填
		 */
		if (name.equals("jgmc") && (value.toString().equals("") || value.toString().length() > 80)) {
			throw new Exception("[JGMC:组织机构名称=" + value.toString() + "]错误");
		}
		/*
		 * 3. USERNAME	对应的系统登录用户名	字符	10	必填	医院名称或者编码
		 */
		if (name.equals("username") && (value.toString().equals("") || value.toString().length() > 80)) {
			throw new Exception("[USERNAME:系统登录用户名=" + value.toString() + "]错误");
		}
		/*
		 * 4. YLFKFS	医疗付款方式	字符	3	必填	《值域范围参考RC032-医疗付费方式代码》
		 */
		if (!compareDic(rc032, RecordRangeType.code, value.toString())) {
			throw new Exception("[YLFKFS:医疗付款方式=" + value.toString() + "]错误");
		}
		/*
		 * 5. JKKH	健康卡号	字符	50		在已统一发放“中华人民共和国居民健康卡”的地区填写健康卡号码，尚未发放“健康卡”的地区填写“-”
		 */
		if (name.equals("jkkh") && (value.toString().length() < 2 && !value.toString().equals("-"))) {
			throw new Exception("[JKKH:健康卡号=" + value.toString() + "]错误，（在已统一发放“中华人民共和国居民健康卡”的地区填写健康卡号码，尚未发放“健康卡”的地区填写“-”）");
		}
		/*
		 * 6. ZYCS	住院次数	数字	4	必填	大于0的整数
		 */
		if (name.equals("zycs") && (value.toString().equals("") || value.toString().equals("-") || value.toString().equals("0"))) {
			throw new Exception("[ZYCS:住院次数=" + value.toString() + "]错误，（必须大于0的整数)");
		}
		/*
		 * 7. BAH	病案号	字符	50	必填
		 */
		if (name.equals("bah") && (value.toString().equals("") || value.toString().equals("-"))) {
			throw new Exception("[BAH:病案号=" + value.toString() + "]错误");
		}
		/*
		 * 8. XM	姓名	字符	40	必填
		 */
		if (name.equals("xm") && (value.toString().equals("") || value.toString().equals("-"))) {
			throw new Exception("[XM:姓名=" + value.toString() + "]错误");
		}
		/*
		 * 9. XB	性别	数字	1	必填	《值域范围参考RC001-性别代码》
		 */
		if (name.equals("xb") && !compareDic(rc001, RecordRangeType.code, value.toString())) {
			throw new Exception("[XB:性别=" + value.toString() + "]错误");
		}
		/*
		 * 10. CSRQ	出生日期	日期	10	必填	yyyy-mm-dd
		 */
		try {
			if (name.equals("csrq")) compareDate(value.toString(), true);
		}
		catch (Exception e) {
			throw new Exception("[CSRQ:出生日期=" + value.toString() + "]" + e.getMessage());
		}
	}

	public boolean compareDic(Dictionary[] dic, RecordRangeType type, String keyword) {
		boolean result = false;
		for (Dictionary d : dic) {
			if (type == RecordRangeType.code) {
				result = d.getCode().equals(keyword);
			} else if (type == RecordRangeType.name) {
				result = d.getLabel().equals(keyword);
			}
			if (result) return result;
		}
		return result;
	}

	public boolean compareDate(String date, boolean isAge) throws Exception {
		final int maxAge = 150;
		try {
			Date _csrq = FormatUtil.parseDate(date);
			Calendar now = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			cal.setTime(_csrq);

			if (isAge) 
				if (cal.get(Calendar.YEAR) - now.get(Calendar.YEAR) > maxAge) throw new ErrorException(null, ">" + maxAge + "岁，年龄存在意义");

			return true;
		}
		catch (ParseException ex) {
			throw new Exception("格式错误");
		}
		catch (ErrorException ex) {
			throw new Exception(ex.getMessage());
		}
		catch (Exception ex) {
			throw new Exception("错误");
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

	public List<InpatientHomepageAnalyCheck> getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(List<InpatientHomepageAnalyCheck> checkRecord) {
		this.checkRecord = checkRecord;
	}

}
