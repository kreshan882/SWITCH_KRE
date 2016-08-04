package com.epic.mfn.util;

import java.sql.Connection;
import java.util.ArrayList;


public class DBValidation {
	
	
	public static boolean validate(DBprocessing dbpr, Connection con, TxnKeyWords keyWD , boolean validateTxnPermition , boolean validateCapLimit) throws Exception{
		
//		get Terminal Parameters 
		double TxnTime = Double.parseDouble( keyWD.getTIME().substring(0, 4) ) / 100  ;
		dbpr.getTerminalParam(con, keyWD);
		if( ! dbpr.getOperateHourParam(con, keyWD) ){
			keyWD.setRESPONSECODE(ResponseCode.OPERATE_HOUR_PARAM_NOT_DEFINED);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_OPERATE_HOUR_PARAM_NOT_DEFINED);
			return false;
		}
		
		if( Double.parseDouble( keyWD.getDB_FROMTIMEQTY() ) > Double.parseDouble(keyWD.getDB_TOTIMEQTY() ) ){
			if( TxnTime < Double.parseDouble( keyWD.getDB_FROMTIMEQTY() ) && TxnTime > Double.parseDouble(keyWD.getDB_TOTIMEQTY() ) )
			{
				keyWD.setRESPONSECODE(ResponseCode.NOT_IN_OPERATION_HOURS);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_NOT_IN_OPERATION_HOURS);
				return false;
			}
		}
		else if( Double.parseDouble( keyWD.getDB_FROMTIMEQTY() ) < Double.parseDouble(keyWD.getDB_TOTIMEQTY() ) )
		{
			if( TxnTime < Double.parseDouble( keyWD.getDB_FROMTIMEQTY() ) || TxnTime > Double.parseDouble(keyWD.getDB_TOTIMEQTY() ) )
			{
				keyWD.setRESPONSECODE(ResponseCode.NOT_IN_OPERATION_HOURS);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_NOT_IN_OPERATION_HOURS);
				return false;
			}
		}

		if(!dbpr.getTerminalUserDeatils(con, keyWD)){
			keyWD.setRESPONSECODE(ResponseCode.INVALID_USER_ID_OR_PASSWD);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_USER_ID_OR_PASSWD);
			return false;
		}
		if(keyWD.getEXPIRYDATE() != null && keyWD.getDB_CURRENTDATE().after(keyWD.getEXPIRYDATE())){
			keyWD.setRESPONSECODE(ResponseCode.PASSWORD_EXPIRED);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_PASSWORD_EXPIRED);
			return false ;
		}
		
		if( TxnTypeCodes.MF_BATCH_CLEAR.equals(keyWD.getTXNTYPE())){
			if(!keyWD.getUSERROLECODE().equals("batchclear") && !keyWD.getMTI().equals(TxnCodes.MTI_MF_REVERSAL_MESSAGE)){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_USER_ROLE);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_USER_ROLE);
				return false ;
			}
		}
		if(keyWD.getUSERROLECODE().equals("batchclear") && !keyWD.getPROCESSINGCODE().equals(TxnCodes.PC_MF_BATCH_CLEAR) && !keyWD.getPROCESSINGCODE().equals(TxnCodes.PC_MF_ONLINE_LOGIN)){
			keyWD.setRESPONSECODE(ResponseCode.INVALID_USER_ROLE);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_USER_ROLE);
			return false ;
		}
	
		//Check User pin count
		if(null != keyWD.getDB_PIN_COUNT() &&  null != keyWD.getMAXPIN_RETRY() && (Integer.parseInt(keyWD.getDB_PIN_COUNT()) >= Integer.parseInt(keyWD.getMAXPIN_RETRY()))){
			keyWD.setRESPONSECODE(ResponseCode.PIN_TRY_LIMIT_EXCEEDED);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_PIN_TRY_LIMIT_EXCEEDED);
			return false;
		}
		
		//Check User Password 
		if(null == keyWD.getDB_PASSWORD() || !keyWD.getDB_PASSWORD().equals(keyWD.getPASSWORD())){
			keyWD.setRESPONSECODE(ResponseCode.INVALID_USER_ID_OR_PASSWD);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_USER_ID_OR_PASSWD);
			dbpr.increasePinTry(con, keyWD);
			return false;
		}
		
		dbpr.resetPinTry(con, keyWD);

		//Check User TID Mapping
		if( ! TxnTypeCodes.MF_BATCH_CLEAR.equals(keyWD.getTXNTYPE()) && !keyWD.getUSERROLECODE().equals("batchclear")){
			if(null == keyWD.getDB_TID() || !keyWD.getDB_TID().equals(keyWD.getTID())){
				keyWD.setRESPONSECODE(ResponseCode.TERMINAL_NOT_MAP_TO_USER);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_TERMINAL_NOT_MAP_TO_USER);
				return false;
			}
		}
		
		//Check User Status
		if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_USER_STATUS())){
			keyWD.setRESPONSECODE(ResponseCode.INACTIVE_USER);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_USER);
			return false;
		}
		
		//Check User Role Status
		if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_USERROLE_STATUS())){
			keyWD.setRESPONSECODE(ResponseCode.INACTIVE_USER_ROLE);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_USER_ROLE);;
			return false;
		}
		
		//Check User Role Type Status	
		if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_USERROLETYPE_STATUS())){
			keyWD.setRESPONSECODE(ResponseCode.INACTIVE_USER_ROLE_TYPE);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_USER_ROLE_TYPE);
			return false;
		}
		
		//Check Terminal ID
		if(!dbpr.getTerminalDeatils(con, keyWD)){
			keyWD.setRESPONSECODE(ResponseCode.TERMINAL_DOSNOT_EXIST);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_TERMINAL_DOSNOT_EXIST);
			return false;
		}
		
		//Check Terminal Status
		if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_TERMINAL_STATUS())){
			keyWD.setRESPONSECODE(ResponseCode.INACTIVE_TERMINAL);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_TERMINAL);
			return false;
		}
		
		//Check Terminal Type Status
		if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_TERMINALTYPE_STATUS())){
			keyWD.setRESPONSECODE(ResponseCode.INACTIVE_TERMINAL_TYPE);
			keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_TERMINAL_TYPE);
			return false;
		}
		
		
		if( ! TxnTypeCodes.MF_BATCH_CLEAR.equals(keyWD.getTXNTYPE()) || ! keyWD.getUSERROLECODE().equals("batchclear")){
			//Check User Branch Status
			if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_BRANCH_STATUS())){
				keyWD.setRESPONSECODE(ResponseCode.INACTIVE_USER_BRANCH);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_USER_BRANCH);
				return false;
			}
			
			//Check User Company Status
			if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_COMPANY_STATUS())){
				keyWD.setRESPONSECODE(ResponseCode.INACTIVE_USER_COMPANY);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_USER_COMPANY);
				return false;
			}
		}

		if(validateTxnPermition){
			
			 if(TxnTypeCodes.MF_BATCH_CLEAR.equals(keyWD.getTXNTYPE()))
	         {
	                keyWD.setTXNCOMPANY(keyWD.getCOMPANY());
	                keyWD.setTXNBRANCH(keyWD.getBRANCH());
	         }

			//Check Transaction Company
			if(null == keyWD.getTXNCOMPANY() || !dbpr.getTxnCompanyDetails(con, keyWD)){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_TXN_COMPANY);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_USER_COMPANY);
				return false;
			}
			
			//Check User TXN Company Status
			if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_TXNCOMPANY_STATUS())){
				keyWD.setRESPONSECODE(ResponseCode.INACTIVE_TXN_COMPANY);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_TXN_COMPANY);
				return false;
			}
			
			//Check Transaction Branch
			if(null == keyWD.getTXNBRANCH() || !dbpr.getTxnBranchDetails(con, keyWD) ){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_TXN_BRANCH);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_TXN_BRANCH);
				return false;
			}
			
			//Check Transaction Branch Status
			if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_TXNBRANCH_STATUS())){
				keyWD.setRESPONSECODE(ResponseCode.INACTIVE_USER_BRANCH);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_USER_BRANCH);
				return false;
			}
			if(!StatusCode.STATE_ACTIVE.equals(keyWD.getDB_COMPANY_STATUS())){
				keyWD.setRESPONSECODE(ResponseCode.INACTIVE_USER_COMPANY);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_USER_COMPANY);
				return false;
			}
			
			//Check Transaction Type
			if(null == keyWD.getTXNTYPE() || !dbpr.getTxnTypeDetails(con, keyWD)){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_TXN_TYPE);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_TXN_TYPE);
				return false;
			}

			//Check Transaction Status
			if( !StatusCode.STATE_ACTIVE.equals(keyWD.getDB_TRANSACTION_STATUS())){
				keyWD.setRESPONSECODE(ResponseCode.INACTIVE_TRANSACTION);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INACTIVE_TRANSACTION);
				return false;
			}
			
			//Check User TXN Permeation
			ArrayList<String> permCompanyList= dbpr.getUserProfile(con, keyWD);
			
			/*if( TxnTypeCodes.MF_BATCH_CLEAR.equals(keyWD.getTXNTYPE()) && permCompanyList.size() <= 0 ){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_TXN_PERMITION);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_TXN_PERMITION);
				return false;
			}else*/
			if(!TxnTypeCodes.MF_BATCH_CLEAR.equals(keyWD.getTXNTYPE()) && !permCompanyList.contains(keyWD.getTXNCOMPANY())){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_TXN_PERMITION);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_TXN_PERMITION);
				return false;
			}
		}
	
		
		if( ( keyWD.getXML_PER_BATCH_LIMIT() != null && ! keyWD.getXML_PER_BATCH_LIMIT().equals("") )&&  keyWD.getUSERROLEVALIDITY().equals("1")  && validateCapLimit ){
			if( ! keyWD.getTXNTYPE().equals(TxnTypeCodes.MF_CASH_WITHDRAWAL) && ! keyWD.getTXNTYPE().equals(TxnTypeCodes.MF_FUND_TRANSFER ) && ( keyWD.getCHQ_NO() == null ||  keyWD.getCHQ_NO() == "" )){
				
				double totalCollection = dbpr.getTotalCollection(con, keyWD);
				//double totalColLimit = dbpr.getCashColectionLimit(con, keyWD);

				if( ( Double.parseDouble(keyWD.getTXNAMOUNT() ) /100) + totalCollection > Double.parseDouble(keyWD.getXML_PER_BATCH_LIMIT()) ){
					keyWD.setRESPONSECODE(ResponseCode.CASH_COLLECTION_LIMIT_EXCEED);
					keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_CASH_COLLECTION_LIMIT_EXCEED);
					return false;
				}
			}
		}

		if( dbpr.getTxnLimit( con, keyWD ) && null != keyWD.getTXNAMOUNT() &&  keyWD.getUSERROLEVALIDITY().equals("1")  && validateCapLimit ){

			if(keyWD.getLIMIT_MINIMUMPERTXNLIMIT() > (Double.parseDouble(keyWD.getTXNAMOUNT())/100)){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_TXN_MIN_LIMIT);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_TXN_MIN_LIMIT);
				return false;
			}
			
			if(keyWD.getLIMIT_PERTXNLIMIT() < (Double.parseDouble(keyWD.getTXNAMOUNT())/100)){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_TXN_MAX_LIMIT);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_TXN_MAX_LIMIT);
				return false;
			}
			
			//double totalCollection = dbpr.getTotalCollection(con, keyWD);
			//double totalColLimit = dbpr.getCashColectionLimit(con, keyWD);
			
			/*if( (Double.parseDouble(keyWD.getTXNAMOUNT())/100) + totalCollection > Double.parseDouble(keyWD.getXML_PER_BATCH_LIMIT()) ){
				keyWD.setRESPONSECODE(ResponseCode.CASH_COLLECTION_LIMIT_EXCEED);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_CASH_COLLECTION_LIMIT_EXCEED);
				return false;
			}*/
			
//			Commented to Remove Validation of transaction Limit
			
			/*dbpr.getTransactionCount(con, keyWD);
			
			if(keyWD.getLIMIT_TXNCOUNT() >= keyWD.getLIMIT_DAILYTXNCOUNT()){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_DALY_TXN_COUNT);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_DALY_TXN_COUNT);
				return false;
			}
			
			if( (keyWD.getLIMIT_TOTALAMOUNT()+ (Double.parseDouble(keyWD.getTXNAMOUNT())/100)) >= keyWD.getLIMIT_DAILYTXNLIMIT()){
				keyWD.setRESPONSECODE(ResponseCode.INVALID_DAILYTXNLIMIT);
				keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_INVALID_DAILYTXNLIMIT);
				return false;
			}*/

		}
		
		return true;
	}
}
