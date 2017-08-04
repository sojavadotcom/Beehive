package com.sojava.beehive.common.datasource;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private String DB_DRIVER_NAME;
	private String DB_URL;
	private String DB_USER_NAME;
	private String DB_PASSWORD;

	public DBConnector(String driverName, String url, String userName, String password) {
		this.DB_DRIVER_NAME = driverName;
		this.DB_URL = url;
		this.DB_USER_NAME = userName;
		this.DB_PASSWORD = password;
	}
	public Connection getConnect() throws Exception {
		Connection connect = null;
		Class.forName(DB_DRIVER_NAME);
		connect = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
		connect.setAutoCommit(false);

		return connect;
	}
}
