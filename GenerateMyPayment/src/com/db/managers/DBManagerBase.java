package com.db.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.connection.JDBCConnection;
import com.db.datatypes.IndicatorPerDate;
import com.db.utils.DBUtils;

public abstract class DBManagerBase {
	
	private String getSelectStatement() {
		String resStatement = "SELECT id, customer, date, " + getResultColumn() + " as result from " + getTableName() + " WHERE customer = ? ORDER BY date"; 
		return  resStatement;
	}
	
	public ArrayList<IndicatorPerDate> getResultsByCustomer (String customer) throws Exception {
		
		Connection connect = JDBCConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		DBUtils dbUtils = DBUtils.getInstance();
		ArrayList<IndicatorPerDate> indicatorList = new ArrayList<IndicatorPerDate>();
		
        try {
	        preparedStatement = connect.prepareStatement(getSelectStatement());

			preparedStatement.setString(1, customer);
	
	        resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	        	indicatorList.add(getIndicator(resultSet));
	        }
	        
	        return indicatorList;
	        
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtils.freeResources(preparedStatement, connect, resultSet);
		}
	}

	private IndicatorPerDate getIndicator(ResultSet resultSet) throws SQLException {
		
		IndicatorPerDate indicator = getNewIndicator();

		indicator.setId(resultSet.getString("id"));
		indicator.setCustomer(resultSet.getString("customer"));
		indicator.setDate(resultSet.getDate("date"));
		indicator.setResult((Double) resultSet.getObject("result"));
		indicator.setIndicatorFromResult();
		
		return indicator;
	}

	protected abstract IndicatorPerDate getNewIndicator();
	protected abstract String getResultColumn();
	protected abstract String getTableName();
	

}
