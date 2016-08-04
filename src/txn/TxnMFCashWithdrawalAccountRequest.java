package com.epic.mfn.txn;

import java.sql.Connection;

import org.jpos.iso.ISOMsg;

import com.epic.mfn.connect.Communicate;
import com.epic.mfn.util.DBValidation;
import com.epic.mfn.util.DBprocessing;
import com.epic.mfn.util.ResponseCode;
import com.epic.mfn.util.SecurityProcessing;
import com.epic.mfn.util.TxnHelpMethods;
import com.epic.mfn.util.TxnKeyWords;
import com.epic.mfn.util.TxnTypeCodes;

public class TxnMFCashWithdrawalAccountRequest {
	private ISOMsg responseMsg = null;
	public ISOMsg doProcessRequest(ISOMsg requestMsg,TxnKeyWords keyWD)throws Exception{

		TxnHelpMethods.printPacket(requestMsg, keyWD,"Request element values from POS..");
		DBprocessing dbpr = keyWD.getDbProcessingOb();
		Connection con = keyWD.getDbConnectionOb();
		
		//Extract data 
		
		keyWD.setTRACENUMBER(requestMsg.getValue(11).toString());
		keyWD.setTIME(requestMsg.getValue(12).toString());
		keyWD.setDATE(requestMsg.getValue(13).toString());
		keyWD.setNII(requestMsg.getValue(24).toString());
		keyWD.setTID(requestMsg.getValue(41).toString());
		keyWD.setTXNCOMPANY(requestMsg.getValue(48).toString());
		keyWD.setENCDATA(requestMsg.getValue(57).toString());
		requestMsg.unset(57);
		keyWD.setRESPONSECODE(ResponseCode.SUCCESS);
		keyWD.setCURRENTDATETIME(TxnHelpMethods.getCurrentDateAndTimeWithYear());
		keyWD.setRRN(TxnHelpMethods.getRRN());
		
		keyWD.setTXNTYPE(TxnTypeCodes.MF_CASH_WITHDRAWAL);
		keyWD.setTXNNAME(TxnTypeCodes.MF_NAME_CASH_WITHDRAWAL_ACC);
		
		SecurityProcessing.decriptRequestData(keyWD);
		
		DBValidation.validate(dbpr, con, keyWD, true ,true);

		if(ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE())){
			
			requestMsg.unset(12);	// DATE
			requestMsg.unset(13);	// TIME
			requestMsg.unset(24);	// NII
			requestMsg.unset(42);	// MID
			requestMsg.unset(57);	// ENC DATA

			requestMsg.set(7 , TxnHelpMethods.getCurrentDateAndTimeWithoutYear());	// DATE TIME
			requestMsg.set(37 , keyWD.getRRN());									// RRN
			requestMsg.set(41,keyWD.getTID());
			requestMsg.set(43,keyWD.getCOMPANY()+"|"+keyWD.getBRANCH()+"|"+keyWD.getUSERID());
			requestMsg.set(48 , keyWD.getTXNCOMPANY() + "|" + keyWD.getTXNBRANCH());
			requestMsg.set(57 , keyWD.getTXNDATA());

			TxnHelpMethods.printPacket(requestMsg, keyWD,"Request element values to host ("+keyWD.getHostConnection().getHostName()+")..");
			
			Communicate comu = new Communicate();
			ISOMsg resMsg = comu.sendAndRec(requestMsg,keyWD);
			
			TxnHelpMethods.printPacket(resMsg, keyWD,"Response element values from host ("+keyWD.getHostConnection().getHostName()+")..");

			if( null == resMsg.getValue(39) || resMsg.getValue(39).equals("")){
				keyWD.setRESPONSECODE(ResponseCode.ERROR_IN_RESPONSE);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_ERROR_IN_RESPONSE);
				resMsg.set(39,keyWD.getRESPONSECODE());
				resMsg.set(60,keyWD.getERROR_DESCRIPTION());
			}else{
				keyWD.setRESPONSECODE(resMsg.getValue(39).toString());
			}
					
			if(ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE()) && null != resMsg.getValue(38)){
				keyWD.setAUTHCODE(resMsg.getValue(38).toString());
			}

			if(ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE())&& null != resMsg.getValue(61)){
				SecurityProcessing.encriptResponseData(resMsg.getValue(61).toString(), keyWD);
			}
			
			resMsg.unset(7);	// DATE TIME
			resMsg.unset(61);	// ACCOUNT LIST
			
			if(ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE())&& null != resMsg.getValue(62)){
				resMsg.set(61,resMsg.getValue(62).toString());
			}
			resMsg.unset(62);
			
			resMsg.setMTI(TxnHelpMethods.getResponseMTI(keyWD.getMTI()));
			resMsg.set(12,TxnHelpMethods.getCurrentTime());
			resMsg.set(13,TxnHelpMethods.getCurrentDate());
			resMsg.set(24,keyWD.getNII());
			resMsg.set(37,keyWD.getRRN());
			requestMsg.set(43,keyWD.getCOMPANY()+"|"+keyWD.getBRANCH()+"|"+keyWD.getUSERID());
			requestMsg.set(48 , keyWD.getTXNCOMPANY() + "|" + keyWD.getTXNBRANCH());
			resMsg.set(57,keyWD.getENCDATA());
			
			
			responseMsg = resMsg;

		}else{
			requestMsg.setMTI(TxnHelpMethods.getResponseMTI(keyWD.getMTI()));
			requestMsg.set(39,keyWD.getRESPONSECODE());
			requestMsg.set(60,keyWD.getERROR_DESCRIPTION());
			responseMsg = requestMsg;
		}
		
		return responseMsg;
	}


}