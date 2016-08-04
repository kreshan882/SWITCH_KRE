package com.epic.mfn.util;

public class TxnCodes {
	
	
	public static final String MTI_MF_AUTH_MESSAGE						= "0100";
	public static final String MTI_MF_FINANCIAL_MESSAGE 				= "0200";
	public static final String MTI_MF_REVERSAL_MESSAGE					= "0400";
	public static final String MTI_MF_NETWORK_MESSAGE			 		= "0800";
	
	public static final String MTI_MF_RES_AUTH_MESSAGE						= "0110";
	public static final String MTI_MF_RES_FINANCIAL_MESSAGE 				= "0210";
	public static final String MTI_MF_RES_REVERSAL_MESSAGE					= "0410";
	public static final String MTI_MF_RES_NETWORK_MESSAGE			 		= "0810";
	
	public static final String PC_MF_ONLINE_LOGIN						= "000100";
	public static final String PC_MF_ECHO_MSG							= "000200";
	public static final String PC_MF_BATCH_CLEAR						= "000300";
	public static final String PC_MF_BATCH_CLEAR_REV					= "000300";
	
	public static final String PC_MF_CASH_DIPOSIT_ACCREQ				= "020100";
	public static final String PC_MF_CASH_DIPOSIT_TRANSAACTION			= "020200";
	public static final String PC_MF_CASH_DIPOSIT_REEVRSAL				= "020200";
	
	public static final String PC_MF_VOID								= "020800";
	public static final String PC_MF_VOID_REEVRSAL						= "020800";
	
	public static final String PC_MF_CASH_WITHDRAWAL_ACCREQ				= "030100";
	public static final String PC_MF_CASH_WITHDRAWAL_TRANSAACTION		= "030200";
	public static final String PC_MF_CASH_WITHDRAWAL_REEVRSAL			= "030200";
	
	public static final String PC_MF_BALANCE_INQUERY_ACCREQ				= "050100";
	public static final String PC_MF_BALANCE_INQUERY_TRANSAACTION		= "050200";
	
	public static final String PC_MF_FUND_TRANCEFER_ACCREQ				= "060100";
	public static final String PC_MF_FUND_TRANCEFER_TO_ACCREQ			= "060200";
	public static final String PC_MF_FUND_TRANCEFER_TRANSAACTION		= "060300";
	public static final String PC_MF_FUND_TRANCEFER_REEVRSAL			= "060300";
	
	public static final String PC_MF_MINI_STATEMENT_ACCREQ				= "040100";
	public static final String PC_MF_MINI_STATEMENT_TRANSAACTION		= "040200";
	
	public static final String PC_MF_RENTAL_COLLECTION_ACCREQ			= "070100";
	public static final String PC_MF_RENTAL_COLLECTION_TRANSAACTION		= "070200";
	public static final String PC_MF_RENTAL_COLLECTION_REEVRSAL			= "070200";
	
	public static final String PC_MF_INSURANCE_COLLECTION_ACCREQ		= "090100";
	public static final String PC_MF_INSURANCE_COLLECTION_TRANSAACTION	= "090200";
	public static final String PC_MF_INSURANCE_COLLECTION_REEVRSAL		= "090200";
	
	
	
	public static final String PC_MF_ACCOUNT_OPENINIG_TRANSACTION		= "010100";
	public static final String PC_MF_ACCOUNT_OPENINIG_REVERSAL			= "010100";
	
	public static final String PC_MF_PASSBOOK_FIRST_PAGE_PRINTING		= "100100";
	public static final String PC_MF_PASSBOOK_TRANSACTION_PRINTING		= "100200";
	
	
	public static final String PC_MF_UTILITY_PROVIDED_LIST				= "090500";
	public static final String PC_MF_UTILITY_SERVICE_CHARGE				= "090600";
	public static final String PC_MF_UTILITY_BILL_PAYMENT				= "090700";
	public static final String PC_MF_UTILITY_BILL_PAYMENT_STATUS		= "090700";
	
	public static final String PC_MF_BATCHCLEAR							= "090800";
}
