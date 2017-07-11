package cn.jxszyyy.anyihis.schedule;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.jxszyyy.anyihis.inhospital.bean.ZyBhjfyz;
import cn.jxszyyy.anyihis.service.TestService;

import com.sojava.beehive.framework.common.bean.Result;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;

@Component
public class applyConfirmByInHospital {

	@Resource private TestService hisTestService;

//	@Scheduled(cron = "0 3 */1 * * ?")
	public void execute() {
		try {
			Result result = hisTestService.getApplysByInHospital(null, new Page(), null);
			hisTestService.conformTestFeeByInHospital(result.getRecords().toArray(new ZyBhjfyz[0]));
		}
		catch(Exception ex) {
			new ErrorException(this.getClass(), ex);
		}
	}

	public TestService getHisTestService() {
		return hisTestService;
	}

	public void setHisTestService(TestService hisTestService) {
		this.hisTestService = hisTestService;
	}

}
