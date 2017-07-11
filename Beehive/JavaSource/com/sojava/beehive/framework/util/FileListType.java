package com.sojava.beehive.framework.util;

public class FileListType {
	String name;
	String path;
	long size;
	String type;
	String kind;
	boolean isHidden;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
}
