package edu.se.ustc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class dbUtil {

	public static Connection getConnect() {
//database URL
		String url = "jdbc:mysql://localhost:3306/bus_info?"
				+ "user=root&password=123&useUnicode=true&characterEncoding=UTF-8";
		Connection conn = null;

		/* load the driver of mySql */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* get connection with mySql */
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public static boolean executeSql(String sql, Connection conn)
			throws SQLException {

		Statement stmt = conn.createStatement();

		return stmt.execute(sql);
	}

	public static boolean execute(String sql) throws SQLException {

		Connection conn = dbUtil.getConnect();

		return dbUtil.executeSql(sql,conn);
	}
	

}
