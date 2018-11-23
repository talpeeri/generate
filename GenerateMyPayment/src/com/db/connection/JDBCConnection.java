package com.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class JDBCConnection 
{
	private static JDBCConnection sInstance;
	
	
	private JDBCConnection()
	{
	}
	
	
	public static JDBCConnection getInstance()
	{
		if (sInstance != null)
		{
			return sInstance;
		}
		else
		{
			synchronized (JDBCConnection.class)
			{
				if (sInstance == null)
				{
					sInstance = new JDBCConnection();
				}
				
				return sInstance;
			}
		}
	}
	
	
	public Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Connection con = null;
	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp?user=root&password=hack&autoReconnect=true&useSSL=false");
		return con;

	}
	
	
	public void releaseConnection(Connection con)
	{
		if (con != null)
		{
			try {
				con.close();
			} 
			catch (SQLException e) 
			{
				//close failed
				System.err.println("close of connection failed. ignoring...");
			}
		}
	}
}