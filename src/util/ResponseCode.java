package com.epic.mfn.util;

public class ResponseCode {

//	
	public final static String SUCCESS			 				= "00";
	
	public final static String ERROR			 				= "F0";
	public final static String MSG_ERROR			 			= "Error E-Switch";
	
	public final static String HOSTDOWN 		 				= "F1";
	public final static String MSG_HOSTDOWN 	 				= "Error In Connection";
	
	public final static String ERROR_IN_RESPONSE 		 		= "F2";
	public final static String MSG_ERROR_IN_RESPONSE 	 		= "Error In Response";
	
	public final static String ERROR_IN_REQUEST 		 		= "F3";
	public final static String MSG_ERROR_IN_REQUEST	 			= "Error In Request";
	
	public final static String INVALID_HOST 		 			= "F5";
	public final static String MSG_INVALID_HOST	 				= "Invalid Host";
	
	public final static String INVALID_USER_ID_OR_PASSWD		= "A1";									//Invalid user id or password
	public final static String MSG_INVALID_USER_ID_OR_PASSWD	= "Incorrect Login";
	
	public final static String PIN_TRY_LIMIT_EXCEEDED	 		= "A2";									//User pin try limit exceed
	public final static String MSG_PIN_TRY_LIMIT_EXCEEDED		= "PIN Tries Exceed";
	
	public final static String TERMINAL_NOT_MAP_TO_USER	 		= "A3";									//Terminal don�t mapped to user
	public final static String MSG_TERMINAL_NOT_MAP_TO_USER		= "Terminal Usr Not Map";
	
	public final static String INACTIVE_USER			 		= "A4";									//Inactive user
	public final static String MSG_INACTIVE_USER				= "Inactive user";
	
	public final static String INACTIVE_USER_ROLE			 	= "A5";									//Inactive user role
	public final static String MSG_INACTIVE_USER_ROLE			= "Inactive user role";
	
	public final static String INACTIVE_USER_ROLE_TYPE			= "A6";									//Inactive user role type
	public final static String MSG_INACTIVE_USER_ROLE_TYPE		= "Inact Usr Role Type";
	
	public final static String INVALID_TXN_COMPANY				= "B1";									//Invalid transaction company
	public final static String MSG_INVALID_TXN_COMPANY			= "Invalid Txn Company";
	
	public final static String INVALID_TXN_BRANCH				= "B2";									//Invalid transaction branch
	public final static String MSG_INVALID_TXN_BRANCH			= "Invalid Txn Branch";
	
	public final static String INVALID_TXN_TYPE					= "B3";									//Invalid transaction branch
	public final static String MSG_INVALID_TXN_TYPE				= "Invalid Txn Branch";
	
	public final static String INACTIVE_TRANSACTION				= "B4";									//Inactive transaction
	public final static String MSG_INACTIVE_TRANSACTION			= "Inactive Transaction";
	
	public final static String INACTIVE_TXN_COMPANY				= "B5";									//Invalid transaction for the company
	public final static String MSG_INACTIVE_TXN_COMPANY			= "Inact Txn To Company";
	

	
	public final static String TERMINAL_DOSNOT_EXIST			= "C0";									//Terminal doesn�t exist
	public final static String MSG_TERMINAL_DOSNOT_EXIST		= "Invalid Terminal";
	
	public final static String INACTIVE_TERMINAL				= "C1";									//Inactive terminal
	public final static String MSG_INACTIVE_TERMINAL			= "Inactive Terminal";
	
	public final static String INACTIVE_TERMINAL_TYPE			= "C2";									//Inactive terminal type
	public final static String MSG_INACTIVE_TERMINAL_TYPE		= "Inact Terminal Type";				
	
	public final static String INACTIVE_USER_BRANCH				= "C3";									//Terminal assign to inactive branch
	public final static String MSG_INACTIVE_USER_BRANCH			= "Inact Term Branch";
	
	public final static String INACTIVE_USER_COMPANY			= "C4";									//Terminal assign to inactive company
	public final static String MSG_INACTIVE_USER_COMPANY		= "Inact Term Company";
	
	public final static String INVALID_TXN_PERMITION			= "D1";									//User dosen't have for this transaction.
	public final static String MSG_INVALID_TXN_PERMITION		= "No Permition to Usr";
	
	public final static String INVALID_TXN_MIN_LIMIT			= "E1";									//Invalid Transaction minimum limit.
	public final static String MSG_INVALID_TXN_MIN_LIMIT		= "Min Txn Limit";
	
	public final static String INVALID_TXN_MAX_LIMIT			= "E2";									//Invalid Transaction max limit.
	public final static String MSG_INVALID_TXN_MAX_LIMIT		= "Max Txn Limit";
	
	public final static String INVALID_DALY_TXN_COUNT			= "E3";									//Transaction Count Limit Reached.
	public final static String MSG_INVALID_DALY_TXN_COUNT		= "Max Daly Count";
	
	public final static String INVALID_DAILYTXNLIMIT			= "E4";									//Transaction Amount Limit Reached.
	public final static String MSG_INVALID_DAILYTXNLIMIT		= "Max Daily Amount";
	
	public final static String CASH_COLLECTION_LIMIT_EXCEED		= "E5";									//Transaction Amount Limit Reached.
	public final static String MSG_CASH_COLLECTION_LIMIT_EXCEED	= "Cash Col Lim Exceed";
	
	public final static String OPERATE_HOUR_PARAM_NOT_DEFINED	= "F1" ;
	public final static String MSG_OPERATE_HOUR_PARAM_NOT_DEFINED	= "Opr hrs not defined" ;
	
	public final static String NOT_IN_OPERATION_HOURS			= "F2" ;
	public final static String MSG_NOT_IN_OPERATION_HOURS		= "Not in Opr Hours" ;
	
	public final static String INVALID_USER_ROLE				= "R1" ;
	public final static String MSG_INVALID_USER_ROLE			= "Invalid Usr Role" ;
	
	public final static String PASSWORD_EXPIRED					= "R2" ;
	public final static String MSG_PASSWORD_EXPIRED				= "Password Expired" ;

	public final static String UTIL_TXN_NOTFOUND				= "R3" ;
	public final static String MSG_UTIL_TXN_NOTFOUND			= "Txn Not Found" ;
	
//	public final static String BATCH_LIMIT_NOTDEFINE				= "P1" ;
//	public final static String MSG_BATCH_LIMIT_NOTDEFINE			= "per batch lim not defined " ;
	

	

}
