package com.epic.mfn.connect;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.ISO87BPackager;

import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.logs.LogFileCreator;
import com.epic.mfn.util.PrintLogs;
import com.epic.mfn.util.ResponseCode;
import com.epic.mfn.util.TxnHelpMethods;
import com.epic.mfn.util.TxnKeyWords;



public class Communicate {
	
	private DataInputStream in 			= null;
	private DataOutputStream out		= null;
	private byte[] response 			= null;
	private byte actualResponse[]		= null;
	

	public ISOMsg sendAndRec (ISOMsg m,TxnKeyWords keyWD)throws Exception{
		
		byte request[] = m.pack();
		response   = new byte[2048];
		
		byte requestLength [] = TxnHelpMethods.getHexLength(request);
		byte[] fullRequest = new byte[requestLength .length + request.length];
		System.arraycopy(requestLength, 0, fullRequest, 0, requestLength.length);
		System.arraycopy(request, 0, fullRequest, requestLength.length, request.length);

		if(InitConfigValue.CONSOLELEVEL >=2){
			System.out.println("Sent Msg > "+   ISOUtil.hexString(fullRequest));
		}
		
		if(InitConfigValue.LOGLEVEL >=2){
			LogFileCreator.writInforTologs("Sent Msg > "+ISOUtil.hexString(fullRequest),keyWD);
		}
		
		if (keyWD.getHostConnection().isPermenetConnectionMode() && keyWD.getHostConnection().isSocektConnected()){
			Socket s = null;
			try{
				keyWD.setHOSTSENDTIME(System.nanoTime());
				s = keyWD.getHostConnection().getPermentSocekt();
				s.setKeepAlive(true);
				s.setSoTimeout(keyWD.getHostConnection().getTimeOut());
				in = new DataInputStream(s.getInputStream());
				out = new DataOutputStream(s.getOutputStream());
				out.flush();
				
				out.write(fullRequest);
				
				int relen = in.read(response, 0,response.length);
				
				
				keyWD.setHOSTRECVTIME(System.nanoTime());
				actualResponse = new byte[relen-2];
				for(int i =0;i<response.length-2;i++){
					actualResponse[i]= response[i+2];
				}
				
				
				if(InitConfigValue.CONSOLELEVEL >=2){
					System.out.println("Recived Msg > "+ISOUtil.hexString(actualResponse));
				}
				if(InitConfigValue.LOGLEVEL >=2){
					LogFileCreator.writInforTologs("Recived Msg > "+ISOUtil.hexString(actualResponse),keyWD);
				}
				
			}catch(Exception e){
				keyWD.setRESPONSECODE(ResponseCode.HOSTDOWN);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_HOSTDOWN);
				PrintLogs.printLine("Error found while connecting with server (P)..");
				keyWD.getHostConnection().setSocektConnected(false);
				if(s !=null)s.close();
				if(out !=null)out.close();
				if(in !=null)in.close();
				
			}
		}
		
		
		if (!keyWD.getHostConnection().isPermenetConnectionMode()){
			Socket s  = null;
			try{
				keyWD.setHOSTSENDTIME(System.nanoTime());
				s = CreateSocketConnection.getUnpermentConnection(keyWD.getHostConnection());
				
				in = new DataInputStream(s.getInputStream());
				out = new DataOutputStream(s.getOutputStream());
				out.flush();
			
				out.write(fullRequest);
				int relen = in.read(response, 0,response.length);
				
				keyWD.setHOSTRECVTIME(System.nanoTime());
				actualResponse= new byte[relen-2];
				
				for(int i =0; i< actualResponse.length ;i++){
					actualResponse[i]= response[i+2];
				}
				
				
				if(InitConfigValue.CONSOLELEVEL >=2){
					System.out.println("Recived Msg > "+ISOUtil.hexString(actualResponse));
				}
				if(InitConfigValue.LOGLEVEL >=2){
					LogFileCreator.writInforTologs("Recived Msg > "+ISOUtil.hexString(actualResponse),keyWD);
				}
				
			}catch(Exception e){
				keyWD.setRESPONSECODE(ResponseCode.HOSTDOWN);
				PrintLogs.printLine("Error found while connecting with server (T)..");
				
			}finally{
				if(in !=null)in.close();
				if(out !=null)out.close();
				if(s !=null)s.close();
			}
		}
		
		ISOMsg hostmsg = new ISOMsg();
		hostmsg.setPackager(new ISO87BPackager());
		
		hostmsg.unpack(actualResponse);
		
		
		return hostmsg;
		
	}

}
