package com.sojava.beehive.framework.component.inpatienthomepage.service.imp;

import com.sojava.beehive.framework.component.inpatienthomepage.bean.RecordRangeType;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.Dictionary;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnaly;
import com.sojava.beehive.framework.component.inpatienthomepage.bean.InpatientHomepageAnalyCheck;
import com.sojava.beehive.framework.component.inpatienthomepage.dao.HomepageDao;
import com.sojava.beehive.framework.component.inpatienthomepage.service.HomepageService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class HomepageServiceImpl implements HomepageService {

	@Resource private HomepageDao homepageDao;
	private Dictionary[] rc001; //性别代码
	private Dictionary[] rc002; //婚姻状况
	private Dictionary[] rc003; //职业代码
	private Dictionary[] rc011; //病案质量
	private Dictionary[] rc013; //麻醉方式
	private Dictionary[] rc014_1; //切口愈合等级
	private Dictionary[] rc014_2; //切口愈合类别
	private Dictionary[] rc016; //判断代码
	private Dictionary[] rc019; //离院方式
	private Dictionary[] rc023; //科室代码
	private Dictionary[] rc026; //入院途径代码
	private Dictionary[] rc027; //入院病情
	private Dictionary[] rc028; //药物过敏
	private Dictionary[] rc029; //手术级别
	private Dictionary[] rc030; //血型编码
	private Dictionary[] rc031; //Rh血型
	private Dictionary[] rc032; //医疗付费方式
	private Dictionary[] rc033; //联系人关系
	private Dictionary[] rc035; //民族代码
	private Dictionary[] rc036; //籍贯代码
	private Dictionary[] rc037; //有无字典
	private Dictionary[] rc038; //国籍字典
	private Dictionary[] rc039; //治疗类别
	private Dictionary[] rc040; //实施临床路径

	@Override
	public String[] getTypes(String kind) throws Exception {

		return homepageDao.typeList(kind).toArray(new String[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public InpatientHomepageAnaly[] homepageList(String kind, String[] type, RecordRangeType dateType, Date begin, Date end, Criterion[] filters, Order[] orders, Page page) throws Exception {
		List<InpatientHomepageAnaly> list = null;
		try {
			List<Criterion> _filters = new ArrayList<Criterion>();
			_filters.add(Restrictions.eq("kind", kind));
			if (type != null) {
				_filters.add(Restrictions.in("type", type));
			}
			if (dateType != null) {
				_filters.add(Restrictions.between(dateType.toString(), begin == null ? end : begin, end == null ? begin : end));
			}
			if (filters != null) {
				_filters.add(Restrictions.and(filters));
			}

			list = (List<InpatientHomepageAnaly>) homepageDao.query(InpatientHomepageAnaly.class, _filters.toArray(new Criterion[0]), orders, page, false);

			return list.toArray(new InpatientHomepageAnaly[0]);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public InpatientHomepageAnalyCheck[] homepageCheckList(Integer pid) throws Exception {
		List<InpatientHomepageAnalyCheck> list = null;
		try {
			list = (List<InpatientHomepageAnalyCheck>) homepageDao.query(InpatientHomepageAnalyCheck.class, new Criterion[]{Restrictions.eq("InpatientHomepageAnaly.id", new InpatientHomepageAnaly(pid))}, new Order[]{Order.asc("id")}, null, false);

			return list.toArray(new InpatientHomepageAnalyCheck[0]);
		}
		catch(Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
	}

	@Override
	public void checkHomepage(String kind, String[] type) throws Exception {

		rc001 = homepageDao.getDictionary(RecordRangeType.rc001).toArray(new Dictionary[0]);
		rc002 = homepageDao.getDictionary(RecordRangeType.rc002).toArray(new Dictionary[0]);
		rc003 = homepageDao.getDictionary(RecordRangeType.rc003).toArray(new Dictionary[0]);
		rc011 = homepageDao.getDictionary(RecordRangeType.rc011).toArray(new Dictionary[0]);
		rc013 = homepageDao.getDictionary(RecordRangeType.rc013).toArray(new Dictionary[0]);
		rc014_1 = homepageDao.getDictionary(RecordRangeType.rc014_1).toArray(new Dictionary[0]);
		rc014_2 = homepageDao.getDictionary(RecordRangeType.rc014_2).toArray(new Dictionary[0]);
		rc016 = homepageDao.getDictionary(RecordRangeType.rc016).toArray(new Dictionary[0]);
		rc019 = homepageDao.getDictionary(RecordRangeType.rc019).toArray(new Dictionary[0]);
		rc023 = homepageDao.getDictionary(RecordRangeType.rc023).toArray(new Dictionary[0]);
		rc026 = homepageDao.getDictionary(RecordRangeType.rc026).toArray(new Dictionary[0]);
		rc027 = homepageDao.getDictionary(RecordRangeType.rc027).toArray(new Dictionary[0]);
		rc028 = homepageDao.getDictionary(RecordRangeType.rc028).toArray(new Dictionary[0]);
		rc029 = homepageDao.getDictionary(RecordRangeType.rc029).toArray(new Dictionary[0]);
		rc030 = homepageDao.getDictionary(RecordRangeType.rc030).toArray(new Dictionary[0]);
		rc031 = homepageDao.getDictionary(RecordRangeType.rc031).toArray(new Dictionary[0]);
		rc032 = homepageDao.getDictionary(RecordRangeType.rc032).toArray(new Dictionary[0]);
		rc033 = homepageDao.getDictionary(RecordRangeType.rc033).toArray(new Dictionary[0]);
		rc035 = homepageDao.getDictionary(RecordRangeType.rc035).toArray(new Dictionary[0]);
		rc036 = homepageDao.getDictionary(RecordRangeType.rc036).toArray(new Dictionary[0]);
		rc037 = homepageDao.getDictionary(RecordRangeType.rc037).toArray(new Dictionary[0]);
		rc038 = homepageDao.getDictionary(RecordRangeType.rc038).toArray(new Dictionary[0]);
		rc039 = homepageDao.getDictionary(RecordRangeType.rc039).toArray(new Dictionary[0]);
		rc040 = homepageDao.getDictionary(RecordRangeType.rc040).toArray(new Dictionary[0]);

		InpatientHomepageAnaly[] homepageList = homepageList(kind, type, null, null, null, null, new Order[]{Order.asc("id")}, null);
		for (InpatientHomepageAnaly homepage : homepageList) {
			this.checkColumn(homepage);
		}
	}

	public void checkColumn(InpatientHomepageAnaly homepage) throws Exception {

		homepageDao.emptyCheckinfo(homepage.getId());

		/*
		 * 1. ZZJGDM	组织机构代码	字符	22	必填	指医疗机构执业许可证上面的机构代码。
		 */
		if (homepage.getZzjgdm().trim().equals("") || homepage.getZzjgdm().trim().length() != 22) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[ZZJGDM:组织机构代码=" + homepage.getZzjgdm() + "]错误"));
		}
		/*
		 * 2. JGMC	医疗机构名称	字符	80	必填
		 */
		if (homepage.getJgmc().trim().equals("") || homepage.getJgmc().trim().length() > 80) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[JGMC:组织机构名称=" + homepage.getJgmc() + "]错误"));
		}
		/*
		 * 3. USERNAME	对应的系统登录用户名	字符	10	必填	医院名称或者编码
		 */
		if (homepage.getUsername().trim().equals("") || homepage.getUsername().trim().length() > 80) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[USERNAME:系统登录用户名=" + homepage.getUsername() + "]错误"));
		}
		/*
		 * 4. YLFKFS	医疗付款方式	字符	3	必填	《值域范围参考RC032-医疗付费方式代码》
		 */
		if (!compareDic(rc032, RecordRangeType.code, homepage.getYlfkfs())) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[YLFKFS:医疗付款方式=" + homepage.getYlfkfs() + "]错误"));
		}
		/*
		 * 5. JKKH	健康卡号	字符	50		在已统一发放“中华人民共和国居民健康卡”的地区填写健康卡号码，尚未发放“健康卡”的地区填写“-”
		 */
		if (homepage.getJkkh().trim().length() < 2 && !homepage.getJkkh().equals("-")) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[JKKH:健康卡号=" + homepage.getJkkh() + "]错误，（在已统一发放“中华人民共和国居民健康卡”的地区填写健康卡号码，尚未发放“健康卡”的地区填写“-”）"));
		}
		/*
		 * 6. ZYCS	住院次数	数字	4	必填	大于0的整数
		 */
		if (homepage.getZycs().trim().equals("") || homepage.getZycs().trim().equals("-") || homepage.getZycs().trim().equals("0")) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[ZYCS:住院次数=" + homepage.getZycs() + "]错误，（必须大于0的整数)"));
		}
		/*
		 * 7. BAH	病案号	字符	50	必填
		 */
		if (homepage.getBah().trim().equals("") || homepage.getBah().trim().equals("-")) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[BAH:病案号=" + homepage.getBah() + "]错误"));
		}
		/*
		 * 8. XM	姓名	字符	40	必填
		 */
		if (homepage.getXm().trim().equals("") || homepage.getXm().trim().equals("-")) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[XM:姓名=" + homepage.getXm() + "]错误"));
		}
		/*
		 * 9. XB	性别	数字	1	必填	《值域范围参考RC001-性别代码》
		 */
		if (!compareDic(rc001, RecordRangeType.code, homepage.getXb())) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[XB:性别=" + homepage.getXb() + "]错误"));
		}
		/*
		 * 10. CSRQ	出生日期	日期	10	必填	yyyy-mm-dd
		 */
		try {
			compareDate(homepage.getCsrq(), true);
		}
		catch (Exception e) {
			homepageDao.save(new InpatientHomepageAnalyCheck(homepage.getId(), "[CSRQ:出生日期=" + homepage.getCsrq() + "]" + e.getMessage()));
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
}
