package com.epic.mfn.server;

import java.util.Date;
import org.jpos.iso.ISODate;

import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.util.LogFileDelete;


public class ServerLogHandler implements Runnable {
	
	private static boolean PROCESS = true;
	public static void doLogBackup() throws Exception{
		
		
		String time = ISODate.getTime(new Date());
		
		int hh = Integer.parseInt(time.substring(0,2));
		int mm = Integer.parseInt(time.substring(2,4));
		
		
		if (hh==11){
			
			if(mm >=42){
				
				if(PROCESS){
					PROCESS = false;
					
					
					LogFileDelete.keepLast90LogFIles(InitConfigValue.LOGFILEPATH + "infors");
					LogFileDelete.keepLast90LogFIles(InitConfigValue.LOGFILEPATH + "errors");
					
					
				}
			}
		}else{
			PROCESS = true;
		}

	}

	public void run() {
		
		while (true){
			
			try {
				Thread.sleep(60000);
				doLogBackup();
				
				
			} catch (Exception e) {
				System.out.println("Error while removing logs files ...");
				
			}
		}
		
	}
	


}
