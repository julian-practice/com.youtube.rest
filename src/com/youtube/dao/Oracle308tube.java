package com.youtube.dao;

import javax.naming.*;
import javax.sql.*;
import java.sql.Connection;

public class Oracle308tube {

	private static DataSource oracle308tube = null;
	private static Context context = null;
	
	public static DataSource Oracle308tubeConn() throws Exception {
		if(oracle308tube != null){
			return oracle308tube;
		}
		try {
			if (context == null){
				context = new InitialContext();
			}
			oracle308tube = (DataSource) context.lookup("localhostDb");
		}
		catch (Exception e){
			
		}
		return oracle308tube;
	}
	
	protected static Connection oraclePcPartsConnection() {
		Connection conn = null;
		try {
			conn = Oracle308tubeConn().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
