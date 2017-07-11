package com.sojava.beehive.framework.component.log.service;

public interface LogService {

	void record(String kind, String level, String message);
}
