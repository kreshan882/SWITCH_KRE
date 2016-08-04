package com.epic.mfn.init;


import java.util.ArrayList;
import java.util.HashMap;

import com.epic.mfn.connect.CreateSocketConnection;
import com.epic.mfn.connect.HostConnection;
import com.epic.mfn.server.ServerLogHandler;
import com.epic.mfn.server.ServerMain;
import com.epic.mfn.util.DBconnections;
import com.epic.mfn.util.PrintLogs;
import com.epic.mfn.util.SecurityProcessing;
import com.epic.mfn.util.TripleDes;
import com.epic.mfn.util.TxnHelpMethods;
import com.epic.mfn.xml.InitXmlReader;

public class InitSer {
	
	private static boolean 		serverConfig 	= true;
	private static String 		initLoading		= "";
	private static String 		initThreadPoolId= "";
	private static String 		initMainQuequeId= "";
	
	private static ServerMain 	mainServer		= null;
	public static boolean isServerConfig() {
		return serverConfig;
	}
	public static void setServerConfig(boolean serverConfig) {
		InitSer.serverConfig = serverConfig;
	}
	public static String getInitLoading() {
		return initLoading;
	}
	public static void setInitLoading(String initLoading) {
		InitSer.initLoading = initLoading;
	}
	public static String getInitThreadPoolId() {
		return initThreadPoolId;
	}
	public static void setInitThreadPoolId(String initThreadPoolId) {
		InitSer.initThreadPoolId = initThreadPoolId;
	}
	public static String getInitMainQuequeId() {
		return initMainQuequeId;
	}
	public static void setInitMainQuequeId(String initMainQuequeId) {
		InitSer.initMainQuequeId = initMainQuequeId;
	}

	public static ServerMain getMainServer() {
		return mainServer;
	}
	public static void setMainServer(ServerMain mainServer) {
		InitSer.mainServer = mainServer;
	}
	
	public static boolean init(boolean isTest)throws Exception{
		
		String osname= System.getProperty("os.name").toUpperCase();
		
		 if("LINUX".equals(osname) && !isTest){
			InitConfigValue.LOGFILEPATH = "/opt/epicplc/logs/";
			InitConfigValue.SCONFIGPATH = "/opt/epicplc/sconfig/";
		}
		else if("LINUX".equals(osname) && isTest){
			InitConfigValue.LOGFILEPATH = "/opt/epicplctest/logs/";
			InitConfigValue.SCONFIGPATH = "/opt/epicplctest/sconfig/";
		}else if(isTest){
			InitConfigValue.LOGFILEPATH = "C:/epicplctest/logs/";
			InitConfigValue.SCONFIGPATH = "C:/epicplctest/sconfig/";
		}
		else{
			InitConfigValue.LOGFILEPATH = "C:/epicplc/logs/";
			InitConfigValue.SCONFIGPATH = "C:/epicplc/sconfig/";
		}
		
		
		InitXmlReader.readSConfigValues();
		ArrayList<HostConnection> hostConnections = InitXmlReader.readHostConfigValues();
		
		InitConfigValue.RUNPLATFORM = osname;
		PrintLogs.logInitInfor();
		PrintLogs.logInitHostInfor(hostConnections);
		
		DBconnections.createDbPool();
		
		SecurityProcessing.POS_ENC_KEY = TxnHelpMethods.getBytes("4444444444444444555555555555555544444444444444445555555555555555", 32);
		TripleDes.init(SecurityProcessing.POS_ENC_KEY);
		
		InitConfigValue.hostConnection = new HashMap<String, HostConnection>();
		new Thread(new ServerLogHandler()).start() ;
		
		for (int i = 0; i < hostConnections.size(); i++) {
			InitConfigValue.hostConnection.put(hostConnections.get(i).getHostName(), hostConnections.get(i));
			if(hostConnections.get(i).isPermenetConnectionMode()){
				new Thread(new CreateSocketConnection(hostConnections.get(i))).start();
			}
		}

		return true;
	}

}
