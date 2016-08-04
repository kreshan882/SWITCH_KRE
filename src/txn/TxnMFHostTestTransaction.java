package com.epic.mfn.txn;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.ISO87BPackager;
import com.epic.mfn.connect.Communicate;
import com.epic.mfn.util.ResponseCode;
import com.epic.mfn.util.TxnCodes;
import com.epic.mfn.util.TxnHelpMethods;
import com.epic.mfn.util.TxnKeyWords;
import com.epic.mfn.util.TxnTypeCodes;

public class TxnMFHostTestTransaction {

	public static boolean doProcessRequest(TxnKeyWords keyWD)throws Exception{

		try{
			keyWD.setRESPONSECODE(ResponseCode.SUCCESS);
			keyWD.setCURRENTDATETIME(TxnHelpMethods.getCurrentDateAndTimeWithYear());
			keyWD.setRRN(TxnHelpMethods.getRRN());
			keyWD.setTXNTYPE(TxnTypeCodes.MF_HOST_TEST);
			keyWD.setTXNNAME(TxnTypeCodes.MF_NAME_HOST_TEST);

			ISOMsg requestMsg = new ISOMsg();
			requestMsg.setPackager(new ISO87BPackager());
			
			requestMsg.setMTI(TxnCodes.MTI_MF_NETWORK_MESSAGE);
			requestMsg.set(3 ,TxnCodes.PC_MF_ECHO_MSG);
			requestMsg.set(7 , TxnHelpMethods.getCurrentDateAndTimeWithoutYear());	// DATE TIME
			requestMsg.set(37, keyWD.getRRN());

//			TxnHelpMethods.printPacket(requestMsg, keyWD,"Request element values to host ("+keyWD.getHostConnection().getHostName()+")..");

			Communicate comu = new Communicate();
			ISOMsg resMsg = comu.sendAndRec(requestMsg,keyWD);
				
//			TxnHelpMethods.printPacket(resMsg, keyWD,"Response element values from host ("+keyWD.getHostConnection().getHostName()+")..");

			if( null == resMsg.getValue(39) || resMsg.getValue(39).equals("")){
				keyWD.setRESPONSECODE(ResponseCode.ERROR_IN_RESPONSE);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_ERROR_IN_RESPONSE);
			}else{
				keyWD.setRESPONSECODE(resMsg.getValue(39).toString());
			}
			
			if(!ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE())){
				return false;
			}
		    return true;
		        
		}catch (Exception e) {
		}
		
		return false;  
	}

}
