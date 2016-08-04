package com.epic.mfn.txn;

import java.sql.Connection;

import org.jpos.iso.ISOMsg;

import com.epic.mfn.connect.Communicate;
import com.epic.mfn.util.DBValidation;
import com.epic.mfn.util.DBprocessing;
import com.epic.mfn.util.ResponseCode;
import com.epic.mfn.util.SecurityProcessing;
import com.epic.mfn.util.StatusCode;
import com.epic.mfn.util.TxnHelpMethods;
import com.epic.mfn.util.TxnKeyWords;
import com.epic.mfn.util.TxnTypeCodes;

public class TxnMFUtilityBillPayment {
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
		keyWD.setMID(requestMsg.getValue(42).toString());
		keyWD.setTXNCOMPANY(requestMsg.getValue(48).toString());
		keyWD.setENCDATA(requestMsg.getValue(57).toString());
		keyWD.setENCDATA_U(requestMsg.getValue(58).toString());
		
		
		keyWD.setRESPONSECODE(ResponseCode.SUCCESS);
		keyWD.setTXNTYPE(TxnTypeCodes.MF_UTILITY_BILL_PAY);
		keyWD.setTXNNAME(TxnTypeCodes.MF_UTILITY_BILL_PAY_DES);
		keyWD.setCURRENTDATETIME(TxnHelpMethods.getCurrentDateAndTimeWithYear());
		keyWD.setRRN(TxnHelpMethods.getRRN());
		
		SecurityProcessing.decriptRequestData(keyWD);
		requestMsg.set(58 , SecurityProcessing.decriptRequestData(keyWD.getENCDATA_U()));
		String[] reference = requestMsg.getString(58).split("\\|");
		keyWD.setREFNO1(reference[2]);
		keyWD.setREFNO2(reference[3]);
		
		if(ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE()) && DBValidation.validate(dbpr, con, keyWD, true ,true)){	
			
			dbpr.insertTransaction(con, keyWD, StatusCode.TXN_INI);
			dbpr.insertTransactionHistory(con, keyWD , StatusCode.TXN_INI);

			dbpr.updateTransaction( con, keyWD, StatusCode.TXN_TRFT );
			dbpr.insertTransactionHistory(con, keyWD ,StatusCode.TXN_TRFT);

			
			requestMsg.unset(12);	
			requestMsg.unset(13);	
			requestMsg.unset(24);	
			requestMsg.unset(57);	

			requestMsg.set(7 , TxnHelpMethods.getCurrentDateAndTimeWithoutYear());	// DATE TIME
			requestMsg.set(37 , keyWD.getRRN());									// RRN
			requestMsg.set(41,keyWD.getTID());
			requestMsg.set(43,keyWD.getCOMPANY()+"|"+keyWD.getBRANCH()+"|"+keyWD.getUSERID());
			requestMsg.set(48 , keyWD.getTXNCOMPANY() + "|" + keyWD.getTXNBRANCH());

			
			TxnHelpMethods.printPacket(requestMsg, keyWD,"Request element values to host ("+keyWD.getHostConnection().getHostName()+")..");
			dbpr.insertTransactionHistory(con, keyWD, StatusCode.TXN_TRTH);
			
			Communicate comu = new Communicate();
			ISOMsg resMsg = comu.sendAndRec(requestMsg,keyWD);
			
			TxnHelpMethods.printPacket(resMsg, keyWD,"Response element values from host ("+keyWD.getHostConnection().getHostName()+")..");
			dbpr.insertTransactionHistory(con, keyWD, StatusCode.TXN_TRFH);
			
			if( null == resMsg.getValue(39) || resMsg.getValue(39).equals("")){
				keyWD.setRESPONSECODE(ResponseCode.ERROR_IN_RESPONSE);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_ERROR_IN_RESPONSE);
				resMsg.set(39,keyWD.getRESPONSECODE());
				resMsg.set(60,keyWD.getERROR_DESCRIPTION());
			}else{
				keyWD.setRESPONSECODE(resMsg.getValue(39).toString());
			}
			
			if(ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE()) ){
				if(null != resMsg.getValue(38)) keyWD.setAUTHCODE(resMsg.getValue(38).toString());
				if(null != resMsg.getValue(63)) keyWD.setTRANSACTON_NO(resMsg.getValue(63).toString());
			}else{
				dbpr.insertTransactionHistory(con, keyWD, StatusCode.TXN_FAIL);
			}

			
			resMsg.unset(7);
			resMsg.unset(63);
			
			resMsg.setMTI(TxnHelpMethods.getResponseMTI(keyWD.getMTI()));
			resMsg.set(12,TxnHelpMethods.getCurrentTime());
			resMsg.set(13,TxnHelpMethods.getCurrentDate());
			resMsg.set(24,keyWD.getNII());
			resMsg.set(37,keyWD.getRRN());
			resMsg.set(57,keyWD.getENCDATA());
			resMsg.set(61,keyWD.getTRANSACTON_NO());

			
	        dbpr.updateTransaction(con, keyWD, StatusCode.TXN_TRTT);
	        dbpr.insertTransactionHistory(con, keyWD, StatusCode.TXN_TRTT);
	        responseMsg = resMsg;
	         
		}else{
			requestMsg.setMTI(TxnHelpMethods.getResponseMTI(keyWD.getMTI()));
			requestMsg.unset(57);
			requestMsg.set(39,keyWD.getRESPONSECODE());
			requestMsg.set(60,keyWD.getERROR_DESCRIPTION());
			responseMsg = requestMsg;
		}
		
		return responseMsg;
	}
}
