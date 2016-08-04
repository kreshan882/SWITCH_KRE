package com.epic.mfn.txn;

import java.sql.Connection;

import org.jpos.iso.ISOMsg;

import com.epic.mfn.util.DBValidation;
import com.epic.mfn.util.DBprocessing;
import com.epic.mfn.util.ResponseCode;
import com.epic.mfn.util.SecurityProcessing;
import com.epic.mfn.util.StatusCode;
import com.epic.mfn.util.TxnHelpMethods;
import com.epic.mfn.util.TxnKeyWords;
import com.epic.mfn.util.TxnTypeCodes;

public class TxnMFBatchClearRev {
private ISOMsg responseMsg = null;
	
	public ISOMsg doProcessRequest(ISOMsg requestMsg, TxnKeyWords keyWD)throws Exception {

		TxnHelpMethods.printPacket(requestMsg, keyWD,"Request element values from POS..");
		DBprocessing dbpr = keyWD.getDbProcessingOb();
		Connection con = keyWD.getDbConnectionOb();
		
		keyWD.setTRACENUMBER(requestMsg.getValue(11).toString());
		keyWD.setTIME(requestMsg.getValue(12).toString());
		keyWD.setDATE(requestMsg.getValue(13).toString());
		keyWD.setTID(requestMsg.getValue(41).toString());
		keyWD.setNII(requestMsg.getString(24));
		keyWD.setTXNCOMPANY(requestMsg.getString(48));
		keyWD.setENCDATA(requestMsg.getValue(57).toString());
		requestMsg.unset(57);
		keyWD.setRESPONSECODE(ResponseCode.SUCCESS);
		keyWD.setCURRENTDATETIME(TxnHelpMethods.getCurrentDateAndTimeWithYear());
		keyWD.setRRN(TxnHelpMethods.getRRN());
		
		keyWD.setTXNTYPE(TxnTypeCodes.MF_BATCH_CLEAR);
		keyWD.setTXNNAME(TxnTypeCodes.MF_NAME_BATCH_CLEAR);

		if(!dbpr.getReversalRRN(con, keyWD)){
			requestMsg.setMTI(TxnHelpMethods.getResponseMTI(keyWD.getMTI()));
			requestMsg.set(39,ResponseCode.SUCCESS);
			responseMsg = requestMsg;
			return responseMsg;
		}

		keyWD.setSID(keyWD.getREVERSAL_SID());
		
		SecurityProcessing.decriptRequestData(keyWD);

		if(DBValidation.validate(dbpr, con, keyWD, true ,true)){

			dbpr.updateTransaction( con, keyWD, StatusCode.TXN_RIN);
			dbpr.insertTransactionHistory(con, keyWD , StatusCode.TXN_RIN);
		}
		if(ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE())){
			keyWD.setAUTHCODE(TxnHelpMethods.getAuthCode());
			//dbpr.markAsCleared(con, keyWD);
			dbpr.updateTransaction(con, keyWD, StatusCode.TXN_RS);
	        dbpr.insertTransactionHistory(con, keyWD, StatusCode.TXN_RS);
		}else{
			dbpr.updateTransaction(con, keyWD, StatusCode.TXN_RS_FAIL);
			dbpr.insertTransactionHistory(con, keyWD , StatusCode.TXN_RS_FAIL);
		}
		
		requestMsg.setMTI(TxnHelpMethods.getResponseMTI(keyWD.getMTI()));
		requestMsg.set(12,TxnHelpMethods.getCurrentTime());
		requestMsg.set(13,TxnHelpMethods.getCurrentDate());
		requestMsg.set(24,keyWD.getNII());
		requestMsg.set(37,keyWD.getRRN());
		requestMsg.set(38,keyWD.getAUTHCODE());
		requestMsg.set(39,keyWD.getRESPONSECODE());
		requestMsg.set(60, keyWD.getERROR_DESCRIPTION());
		//requestMsg.set(61,keyWD.getDB_ACCOUNT_DATA());
		//requestMsg.set(63,TxnHelpMethods.getCurrentYear());
		
		responseMsg = requestMsg;
		return responseMsg;
	}

}
