package com.util;

import java.util.*;
import java.sql.*;

public class DBManager {
	private static final String driver = "com.mysql.jdbc.Driver";

	private static final String url = "jdbc:mysql://localhost:3306/scholarship?characterEncoding=utf-8";

	private static final String user = "root";

	private static final String pwd = "root";

	public static Connection getCon() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeCon(Connection con) {
		try {
			if (con != null || !con.isClosed())
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
