package com.sojava.beehive.framework.define;

public class Order {
	String column;
	String direction;

	public Order() {}

	public Order(String column, String direction) {
		this.column = column;
		this.direction = direction;
	}

	public String getOrder() {
		return this.column + " " + this.direction;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
