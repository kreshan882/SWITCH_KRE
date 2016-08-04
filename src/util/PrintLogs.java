package com.epic.mfn.util;

import java.util.ArrayList;
import com.epic.mfn.connect.HostConnection;
import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.logs.LogFileCreator;

public class PrintLogs {
	
	public static void logInitInfor()throws Exception{
		
		StringBuffer msg = new StringBuffer();
		
		msg.append("\n-----------------------------------------------------------");
		msg.append("\n      Epic PLC transaction server configurations       " );
		msg.append("\n      Version      : V1.38       " );
		msg.append("\n      Build Number : B20160128     " );
		msg.append("\n-----------------------------------------------------------\n\n");
		msg.append("Running platform                 < "+InitConfigValue.RUNPLATFORM+" > \n");
		msg.append("Server running port              < "+InitConfigValue.RUNPORT+" > \n" );
		msg.append("Server running mode              < "+InitConfigValue.RUNMODE+" > \n" );
		msg.append("Log file name                    < "+InitConfigValue.LOGFILENAME+" > \n" );
		msg.append("Log file level                   < "+InitConfigValue.LOGLEVEL+" > \n" );
		msg.append("Console level                    < "+InitConfigValue.CONSOLELEVEL+" > \n" );
		msg.append("Header size                      < "+InitConfigValue.HEADERSIZE+" > \n" );
		msg.append("Packet size                      < "+InitConfigValue.PACKETSIZE+" > \n" );
		msg.append("Processing timeout               < "+InitConfigValue.PROCESSINGTIMEOUT+" > \n" );
		msg.append("Max thread pool size             < "+InitConfigValue.THREADPOOLMAXSIZE+" > \n" );
		msg.append("Min thread pools size            < "+InitConfigValue.THREADPOOLMINSIZE+" > \n" );
		msg.append("Back log size of thread pool     < "+InitConfigValue.THREADPOOLBACKLOGSIZE+" > \n" );
		msg.append("Processing class name            < "+InitConfigValue.PRCESSCLASS+" > \n" );
		msg.append("Database username                < "+InitConfigValue.DBUSERNAME+" > \n" );
		msg.append("Database password                < "+InitConfigValue.DBPASSWORD+" > \n" );
		msg.append("Database driver                  < "+InitConfigValue.DBDRIVER+" > \n" );
		msg.append("Database url                     < "+InitConfigValue.DBURL+" > \n" );
		msg.append("Database max pool size           < "+InitConfigValue.MAXPOOL+" > \n" );
		msg.append("Database max connections         < "+InitConfigValue.MAXCON+" > \n" );
		msg.append("Database connection timeout      < "+InitConfigValue.DBCONNECTIONTIMEOUT+" > \n" );
		msg.append("Database connection expir time   < "+InitConfigValue.DBCONEXPIRTIMEOUT+" > \n" );
		msg.append("Client connection timeout        < "+InitConfigValue.SOTIMEOUT+" > \n" );
		msg.append("-----------------------------------------------------------");
		
		
		if(InitConfigValue.LOGLEVEL > 0){
			LogFileCreator.writInforTologs(msg.toString(), null);
		}
		if(InitConfigValue.CONSOLELEVEL > 0){
			System.out.println(msg.toString());
		}	
	}
	
public static void logInitHostInfor(ArrayList<HostConnection> hostConnections )throws Exception{
		
		StringBuffer msg = new StringBuffer();
		for (int i = 0; i < hostConnections.size(); i++) {
			
			String conectingStatus = "";
			if(hostConnections.get(i).isActive()){
				conectingStatus = "Enable";
			}else{
				conectingStatus = "Disable";
			}
			
			String conectingMode = "";
			if(hostConnections.get(i).isPermenetConnectionMode() ){
				conectingMode = "Permanent";
			}else{
				conectingMode = "Terminate";
			}
			
			msg.append("HOST CONFIGURATION ("+hostConnections.get(i).getHostName()+")\n" );
			msg.append("Connecting Status                < "+ conectingStatus + "> \n" );
			msg.append("Connecting Ip                    < "+hostConnections.get(i).getHostIp()+" > \n" );
			msg.append("Connecting Port                  < "+hostConnections.get(i).getHostPort()+" > \n" );
			msg.append("Connecting Mode                  < "+conectingMode+" > \n" );
			msg.append("Connecting Timeout               < "+hostConnections.get(i).getTimeOut()+" >\n" );
			
		}

		msg.append("-----------------------------------------------------------\n");
		
		if(InitConfigValue.LOGLEVEL > 0){
			LogFileCreator.writInforTologs(msg.toString(), null);
		}
		if(InitConfigValue.CONSOLELEVEL > 0){
			System.out.println(msg.toString());
		}	
	}
	
	public static void putTxnLogs(TxnKeyWords keyWD)throws Exception{
		
		String msg = "\n--------->\n|MTI : "+keyWD.getMTI()+"| Processing code : "+keyWD.getPROCESSINGCODE()+"| TID : "+keyWD.getTID()+"| MID : "+keyWD.getMID()+
					 "| Response code : "+keyWD.getRESPONSECODE()+"| Account number : "+keyWD.getACCOUNTNUMBER()+"| Amount : "+keyWD.getTXNAMOUNT()+
					 "| NII : "+keyWD.getNII()+"| Trace number : "+keyWD.getTRACENUMBER()+"| Invoice number : "+keyWD.getINVOICENUMBER()+"| Current date time : "+keyWD.getCURRENTDATETIME()+
					 "| Processing time : "+keyWD.getPROCESSINGTIME()+"(ns)|\n--------->\n";
		
		if(InitConfigValue.LOGLEVEL >= 1){
			LogFileCreator.writInforTologs(msg, keyWD);
		}
		if(InitConfigValue.CONSOLELEVEL >= 1){
			System.out.println(msg.toString());
		}	
	}
	
	public static synchronized void printLine(String message){
		if(InitConfigValue.LOGLEVEL >= 4){
			LogFileCreator.writInforTologs(message, null);
		}
		if(InitConfigValue.CONSOLELEVEL >= 4){
			System.out.println(message);
		}	
	}

}
