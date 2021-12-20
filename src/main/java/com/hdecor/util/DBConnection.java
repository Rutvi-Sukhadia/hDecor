package com.hdecor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hdecor.model.ConfigProperties;

public class DBConnection {

	public static Connection getConnection() {
		String url = ConfigProperties.getDbUrl();
		String user = ConfigProperties.getDbUserName();
		String password = ConfigProperties.getDbPassword();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

}
