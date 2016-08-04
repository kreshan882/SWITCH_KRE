package com.epic.mfn.init;

import java.util.HashMap;

import com.epic.mfn.connect.HostConnection;

public class InitConfigValue {
	
	public static  String RUNPLATFORM;
	public static  int RUNPORT;
	public static  int RUNMODE;
	public static  int LOGLEVEL;
	public static  int CONSOLELEVEL;
	public static  String LOGFILENAME;
	public static  String LOGFILEPATH;
	public static  String SCONFIGPATH;
	public static  int PROCESSINGTIMEOUT;
	public static int HEADERSIZE;
	public static int PACKETSIZE;
	
	public static HashMap<String, HostConnection> hostConnection;

	public static  int THREADPOOLMAXSIZE;
	public static  int THREADPOOLMINSIZE;
	public static  int THREADPOOLQUEQUSIZE;
	public static  int THREADPOOLBACKLOGSIZE;
	public static  String PRCESSCLASS;
	
	public static String DBUSERNAME;
	public static String DBPASSWORD;
	public static String DBDRIVER;
	public static String DBURL;
	public static int MAXPOOL;
	public static int MAXCON;
	public static int DBCONNECTIONTIMEOUT;
	public static int DBCONEXPIRTIMEOUT;
	public static int SOTIMEOUT;
	
	public static int DASHBORDSTATUS;
	public static String DASHBORDIP;
	public static int DASHBORDPORT;
	public static int DASHBORDTIMEOUT;
}
