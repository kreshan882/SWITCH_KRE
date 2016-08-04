package com.epic.mfn.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import snaq.db.ConnectionPool;

import com.epic.mfn.init.InitConfigValue;

public class DBconnections {
	private static ConnectionPool pool = null;
	public static void createDbPool()throws Exception{
		
		Driver driver = (Driver)Class.forName(InitConfigValue.DBDRIVER).newInstance();
		DriverManager.registerDriver(driver);
		
		pool = new ConnectionPool("Oracel",
				InitConfigValue.MAXPOOL,
				InitConfigValue.MAXCON,
				InitConfigValue.DBCONEXPIRTIMEOUT,
				InitConfigValue.DBURL,
				InitConfigValue.DBUSERNAME,
				InitConfigValue.DBPASSWORD);
		
		PrintLogs.printLine("Database pool creation successfully done.. ");
		
	}
	
	public static synchronized Connection getConnection()throws Exception{
		Connection con = pool.getConnection(InitConfigValue.DBCONNECTIONTIMEOUT);
		return con;
		
		
	}
	
	public static void relieasConnection(Connection con )throws Exception{
		if(con !=null)con.close();
	}
	
	

}
