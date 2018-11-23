package com.db.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.connection.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBUtils {
	private static DBUtils instance = null;
	
	private DBUtils() {
		
	}
	
	public static DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils();
		}
		return instance;
	}
	
	public void freeResources(PreparedStatement ps, Connection con, ResultSet rs) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			JDBCConnection.getInstance().releaseConnection(con);
		} catch (SQLException e){
			System.err.println("cannot free resources");
			e.printStackTrace();
		}
	}
	
}
