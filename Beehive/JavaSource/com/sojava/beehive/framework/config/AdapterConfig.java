package com.sojava.beehive.framework.config;

import java.util.Properties;

import com.sojava.beehive.framework.define.Adapter;
import com.sojava.beehive.framework.define.config.AdapterConfigInterface;

public class AdapterConfig implements AdapterConfigInterface {
	private static Properties config = new Properties();

	public void setAdapter(String kind, ConfigProperty property) {
		AdapterConfig.config.setProperty("adapter." + kind + ".parameter_define", property.getParameterDefine());
		AdapterConfig.config.setProperty("adapter." + kind + ".class", property.getClassName());
	}
	public String getParameterDefine(String kind) {
		return AdapterConfig.config.getProperty("adapter." + kind + ".parameter_define");
	}
	public String getClassName(String kind) {
		return AdapterConfig.config.getProperty("adapter." + kind + ".class");
	}
	public Adapter getClass(String kind) throws Exception {
		return (Adapter) Class.forName(this.getClassName(kind)).newInstance();
	}
}
