package com.epic.mfn.process.uprocess;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.jpos.iso.ISOUtil;

import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.init.ProcessingRequest;
import com.epic.mfn.logs.LogFileCreator;
import com.epic.mfn.pool.upool.URequestHandler;
import com.epic.mfn.txn.RecvRequest;
import com.epic.mfn.util.PrintLogs;
import com.epic.mfn.util.TxnHelpMethods;
import com.epic.mfn.util.TxnKeyWords;

public class USessionProcess implements URequestHandler {


	
	private Socket clientSocket 		= null;
	private byte []TPDU 				= null;
	private byte []request 				= null;
	private byte []actualRequest		= null;
	private byte []printRequest 		= null;
	private DataInputStream in 			= null;
	private DataOutputStream out		= null;
	private String IP ;
	
	
	public void handleRequest(Socket socket) {
	
		try {
			
			this.clientSocket = socket;
			clientSocket.setKeepAlive(true);
			clientSocket.setSoTimeout(InitConfigValue.SOTIMEOUT);
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			out.flush();
			
			TPDU = new byte[5]; 
			request = new byte[InitConfigValue.PACKETSIZE];
			
			IP = clientSocket.getInetAddress().getHostAddress() ;
			System.out.println("Accepted and waiting for incoming message from " + IP );
	
			int relen = in.read(request, 0,InitConfigValue.PACKETSIZE);
			
			TxnKeyWords keyWD = new TxnKeyWords();	
			keyWD.setSID(TxnHelpMethods.getSid()) ;
			keyWD.setHITTIME(System.nanoTime());

			if(relen >0){
				if(relen > 2){
					actualRequest = new byte[relen-7];
					printRequest  = new byte[relen];
					
					for(int i=0 ; i<relen;i++){
						printRequest[i]=request[i];
						if(i>1){
							
							if(i<7){
								TPDU[i-2]= request[i];
							}else{
								actualRequest[i-7]=request[i];
							}
						}
					}

				
					
					if(InitConfigValue.CONSOLELEVEL >=2){
						PrintLogs.printLine("Recived from POS > "+ISOUtil.hexString(printRequest));
					}
					if(InitConfigValue.LOGLEVEL >=2){
						LogFileCreator.writInforTologs("Recived from POS > "+ISOUtil.hexString(printRequest),keyWD);
					}
					
					PrintLogs.printLine("Waitting for coming transaction.......");
					PrintLogs.printLine("Coming rquest length is ["+relen+"]");
					ProcessingRequest initOB = new ProcessingRequest();
					initOB.setREQUESTPACKET(actualRequest);
					initOB.setREQUESTTPDU(TPDU);
					initOB.setOUTSTREAM(out);
					initOB.setREQUESTLENGTH(relen);
					initOB.setKeyWD(keyWD);
					
					
					RecvRequest recv = new RecvRequest();
					recv.handleRequestFordeman(initOB);
					
					
				}
			}else{
				PrintLogs.printLine("Connection is dropped ");	
			}
			
		}catch(SocketTimeoutException te){
			te.printStackTrace();
			PrintLogs.printLine("Connection timeout");
		} catch (Exception e) {
			
			PrintLogs.printLine("Connection error is found from" + IP );
			LogFileCreator.writErrorTologs(e);
			e.printStackTrace();
		}finally{
			try{
				if(clientSocket !=null ){clientSocket.close();}
				if(in !=null){in.close();}
				if(out !=null){out.flush();}
				if(out !=null){out.close();}
				
				clientSocket = null;
				in = null;
				out= null;
				PrintLogs.printLine("Connection close succussfully with " + IP);
			}catch(Exception e){
				
			}
		}
		
	}

}
