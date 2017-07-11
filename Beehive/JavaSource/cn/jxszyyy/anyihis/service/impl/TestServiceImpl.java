package cn.jxszyyy.anyihis.service.impl;

import com.sojava.beehive.framework.common.bean.Result;
import com.sojava.beehive.framework.define.Filter;
import com.sojava.beehive.framework.define.Order;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.FormatUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jxszyyy.anyihis.dao.TestDao;
import cn.jxszyyy.anyihis.inhospital.bean.TestApplyOfInHospital;
import cn.jxszyyy.anyihis.inhospital.bean.ZyBhjfyz;
import cn.jxszyyy.anyihis.service.TestService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

	@Resource private TestDao hisTestDao;

	@Override
	public Result getApplysByInHospitalForGrid(Filter[] filters, Page page, Order[] orders) {
		Result result = new Result();
		Result data = hisTestDao.listFeesByNeedConform(filters, page, orders);
		result.setPage(page);
		List<Object[]> list = data.getRecordArray();
		List<Object> recs = new ArrayList<Object>();
		for (Object[] rec: list) {
			TestApplyOfInHospital ta = new TestApplyOfInHospital();
			ta.setBhid(rec[2] != null ? Integer.parseInt(String.valueOf(rec[2])) : null);
			ta.setZyh(rec[25] != null ? Integer.parseInt(String.valueOf(rec[25])) : null);
			ta.setJydh(rec[30] != null ? Integer.parseInt(String.valueOf(rec[30])) : null);
			ta.setXm((String) rec[26]);
			ta.setXb((String) rec[27]);
			ta.setKs((String) rec[28]);
			ta.setYs((String) rec[29]);
			ta.setJyxm((String) rec[6]);
			Integer _i_sqrq = rec[10] != null ? Integer.parseInt(String.valueOf(rec[10])) : null;
			String _s_sqrq = null;
			if (_i_sqrq != null) {
				try {
					Calendar c = Calendar.getInstance();
					c.setTime(FormatUtil.DATE_FORMAT.parse("1899-12-30"));
					c.add(Calendar.DAY_OF_MONTH, _i_sqrq);
					_s_sqrq = FormatUtil.DATE_FORMAT.format(c.getTime());
				}
				catch(Exception e) {}
			}
			ta.setSqrq(_s_sqrq);
			ta.setJldw((String) rec[7]);
			ta.setDj((BigDecimal) rec[20]);
			ta.setSl(rec[21] != null ? Integer.parseInt(String.valueOf(rec[21])) : null);
			ta.setJe((BigDecimal) rec[22]);

			recs.add(ta);
		}
		result.setRecords(recs);

		return result;
	}

	@Override
	public Result getApplysByInHospital(Filter[] filters, Page page, Order[] orders) {
		Result result = new Result();
		Result data = hisTestDao.listFeesByNeedConform(filters, page, orders);
		result.setPage(page);
		List<Object[]> list = data.getRecordArray();
		List<Object> recs = new ArrayList<Object>();
		for (Object[] rec: list) {
			ZyBhjfyz bhjf = new ZyBhjfyz();
			bhjf.setNian(rec[0] != null ? Integer.parseInt(String.valueOf(rec[0])) : null);
			bhjf.setYue(rec[1] != null ? Integer.parseInt(String.valueOf(rec[1])) : null);
			bhjf.setBhid(rec[2] != null ? Integer.parseInt(String.valueOf(rec[2])) : null);
			bhjf.setYzid(rec[3] != null ? Integer.parseInt(String.valueOf(rec[3])) : null);
			bhjf.setYzgid(rec[4] != null ? Integer.parseInt(String.valueOf(rec[4])) : null);
			bhjf.setYzbm((String) rec[5]);
			bhjf.setYzmc((String) rec[6]);
			bhjf.setDw((String) rec[7]);
			bhjf.setYzlx(rec[8] != null ? Integer.parseInt(String.valueOf(rec[8])) : null);
			bhjf.setZhxmbm((String) rec[9]);
			bhjf.setYzrq(rec[10] != null ? Integer.parseInt(String.valueOf(rec[10])) : null);
			bhjf.setRq(rec[11] != null ? Integer.parseInt(String.valueOf(rec[11])) : null);
			bhjf.setSj(rec[12] != null ? Integer.parseInt(String.valueOf(rec[12])) : null);
			bhjf.setKsbm((String) rec[13]);
			bhjf.setYsbm((String) rec[14]);
			bhjf.setLyks((String) rec[15]);
			bhjf.setLyys((String) rec[16]);
			bhjf.setZxks((String) rec[17]);
			bhjf.setHsxz((String) rec[18]);
			bhjf.setLy(rec[19] != null ? Integer.parseInt(String.valueOf(rec[19])) : null);
			bhjf.setDj((BigDecimal) rec[20]);
			bhjf.setZl(rec[21] != null ? Integer.parseInt(String.valueOf(rec[21])) : null);
			bhjf.setJe((BigDecimal) rec[22]);
			bhjf.setFylb(rec[23] != null ? Integer.parseInt(String.valueOf(rec[23])) : null);
			bhjf.setFlag(rec[24] != null ? Integer.parseInt(String.valueOf(rec[24])) : null);

			bhjf.setZyh(rec[25] != null ? Integer.parseInt(String.valueOf(rec[25])) : null);
			bhjf.setJydh(rec[30] != null ? Integer.parseInt(String.valueOf(rec[30])) : null);

			recs.add(bhjf);
		}
		result.setRecords(recs);

		return result;
	}

	@Override
	public void conformTestFeeByInHospital(ZyBhjfyz[] recs) {
		for (ZyBhjfyz rec: recs) {
			try {
				hisTestDao.confirmApplyByInHospital(rec.getJydh());
			}
			catch(Exception ex) {
				new ErrorException(this.getClass(), ex);
			}
		}
	}

	public TestDao getHisTestDao() {
		return hisTestDao;
	}

	public void setHisTestDao(TestDao hisTestDao) {
		this.hisTestDao = hisTestDao;
	}

}
