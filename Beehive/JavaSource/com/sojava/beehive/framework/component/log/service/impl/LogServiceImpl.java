package com.sojava.beehive.framework.component.log.service.impl;

import com.sojava.beehive.framework.component.log.bean.Log;
import com.sojava.beehive.framework.component.log.dao.LogDao;
import com.sojava.beehive.framework.component.log.service.LogService;
import com.sojava.beehive.framework.exception.ErrorException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

	@Resource private LogDao logDao;

	@Override
	public void record(String kind, String level, String message) {
		try {
			logDao.save(new Log(kind, level, message));
		}
		catch(Exception ex) {
			new ErrorException(this.getClass(), ex);
		}
	}

	public LogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

}
