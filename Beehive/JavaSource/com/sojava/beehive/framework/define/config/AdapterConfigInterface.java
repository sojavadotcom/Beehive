package com.sojava.beehive.framework.define.config;

import com.sojava.beehive.framework.config.ConfigProperty;
import com.sojava.beehive.framework.define.Adapter;

public interface AdapterConfigInterface {
	void setAdapter(String kind, ConfigProperty property);
	String getParameterDefine(String kind);
	String getClassName(String kind);
	Adapter getClass(String kind) throws Exception;
}
