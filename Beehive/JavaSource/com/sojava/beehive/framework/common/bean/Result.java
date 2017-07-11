package com.sojava.beehive.framework.common.bean;

import com.sojava.beehive.framework.define.Page;

import java.util.List;
import java.util.Properties;

public class Result {

	private Page page;
	private Object record;
	private List<Object> records;
	private List<Object[]> recordArray;
	private Properties properties;

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Object getRecord() {
		return record;
	}

	public void setRecord(Object record) {
		this.record = record;
	}

	public List<Object> getRecords() {
		return records;
	}

	public void setRecords(List<Object> records) {
		this.records = records;
	}

	public List<Object[]> getRecordArray() {
		return recordArray;
	}

	public void setRecordArray(List<Object[]> recordArray) {
		this.recordArray = recordArray;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
