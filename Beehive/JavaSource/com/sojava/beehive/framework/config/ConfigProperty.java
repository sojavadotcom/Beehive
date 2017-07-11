package com.sojava.beehive.framework.config;

public class ConfigProperty {
	private String parameterDefine = null;
	private String className = null;

	public ConfigProperty() {}
	public ConfigProperty(String parameterDefine, String className) {
		setParameterDefine(parameterDefine);
		setClassName(className);
	}
	public String getParameterDefine() {
		return parameterDefine;
	}
	public void setParameterDefine(String parameterDefine) {
		this.parameterDefine = parameterDefine;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
