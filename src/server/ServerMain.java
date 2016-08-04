package com.epic.mfn.server;


import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.init.InitSer;
import com.epic.mfn.logs.LogFileCreator;

import com.epic.mfn.util.PrintLogs;




/**
 * This is the main entry point to LES 
 * @author Kapila shantha rajapaksha
 *
 */
public class ServerMain  {
	

	public ServerMain(boolean handler)throws Exception{
		

	}


	
	public static void main(String[] args) {
		
		boolean testServer = false;
		
		if( args.length > 0 && args[0].equalsIgnoreCase("Test")){
			System .out.print("Server Running on Test Configeration");
			testServer = true;
		}
		
		try {
			if(InitSer.init(testServer)){
				if(InitConfigValue.RUNMODE==1){
					
					System .out.print("Runing mode non-persistant"+InitConfigValue.RUNMODE);
					new RunUprocesser(InitConfigValue.RUNPORT,InitConfigValue.THREADPOOLBACKLOGSIZE,InitConfigValue.PRCESSCLASS,
			        		InitConfigValue.THREADPOOLQUEQUSIZE,
			        		InitConfigValue.THREADPOOLMINSIZE,
			        		InitConfigValue.THREADPOOLMAXSIZE );
					
				}
			}else{
				PrintLogs.printLine("Loding error of server...");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			PrintLogs.printLine("Error while server is initilizing............");
			LogFileCreator.writErrorTologs(e);
		}
	}

	


}
