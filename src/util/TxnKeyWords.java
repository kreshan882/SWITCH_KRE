package com.epic.mfn.util;

import java.sql.Connection;
import java.sql.Date;

import com.epic.mfn.connect.HostConnection;



public class TxnKeyWords {
	
	private String MTI;
	private String PROCESSINGCODE;
	private String TXNAMOUNT;
	private String CORDNUMBER;
	private String TRACENUMBER;
	private String INVOICENUMBER;
	private String MID;
	private String TID;
	private String TXNCOMPANY;
	private String TXNBRANCH;
	private String RRN;
	private String REVERSAL_RRN;
	private String AUTHCODE;
	private String CURRENTDATETIME;
	private String NII;
	private String PIN;
	private String SID;
	private String REVERSAL_SID;
	private String RESPONSECODE;
	private String ERROR_DESCRIPTION;
	private String ACCOUNTNUMBER;
	private String PROCESSINGTIME;
	private String PROVIDERID;
	private String REFERANCENO;
	private String TXNTYPE;
	private String TXNNAME;
	private String COLLECTMODE;
	private String USERID;
	private String USEAGE;
	private String USRNIC;
	private double HITTIME;
	private double LEAVETIME;
	private double HOSTSENDTIME;
	private double HOSTRECVTIME;
	private String DATE;
	private String TIME;
	private String FNAME;
	private String LNAME;
	private String NIC;
	private String TXNMODE;
	private String POSENTRYMODE;
	private String POSTFUNCTIONCODE;
	private String TRACK2DATA;
	private String STATEMENTDETAILS;
	private String USERROLECODE;
	private String USERROLEVALIDITY;
	private String REMARKS;
	private String HOSTNAME;
	private Date EXPIRYDATE ;
	private String UTILITY_CHECK_STATUS;
	private String REFNO1;
	private String REFNO2;
	
	private Date DB_CURRENTDATE ;
	private String DB_TID;
	private String DB_MID;
	private String DB_PASSWORD;
	private String DB_USER_STATUS;
	private String DB_USERROLE_STATUS;
	private String DB_USERROLETYPE_STATUS;
	private String DB_TERMINAL_STATUS;
	private String DB_TERMINALTYPE_STATUS;
	private String DB_BRANCH_STATUS;
	private String DB_MERCHANT_STATUS;
	private String DB_ACCOUNT_DATA;
	private String DB_COMPANY_STATUS;
	private String DB_TXNCOMPANY_STATUS;
	private String DB_TXNBRANCH_STATUS;
	private String DB_TRANSACTION_STATUS;
	private String DB_PIN_COUNT;
	private String DB_FROMTIMEQTY ;
	private String DB_TOTIMEQTY ;
	private String DB_FROMTIMEPERIOD ;
	private String DB_TOTIMEPERIOD ;
	
	private String XML_PER_BATCH_LIMIT ;
	private String CHQ_NO ;
	
	private int LIMIT_TXNCOUNT;
	private double LIMIT_TOTALAMOUNT;
	private int LIMIT_DAILYTXNCOUNT;
	private double LIMIT_DAILYTXNLIMIT;
	private double LIMIT_PERTXNLIMIT;
	private double LIMIT_MINIMUMPERTXNLIMIT;
		
	private String ENCDATA;
	private String TXNDATA;
	private String PASSWORD;
	private String TRANSACTON_NO;
	private String ENCDATA_U;
	
	private String BRANCH;
	private String COMPANY;

	private String MAXPIN_RETRY;
	
	private DBprocessing dbProcessingOb;
	private Connection dbConnectionOb;
	private HostConnection hostConnection;
	
	


	public String getREFNO1() {
		return REFNO1;
	}
	public void setREFNO1(String rEFNO1) {
		REFNO1 = rEFNO1;
	}
	public String getREFNO2() {
		return REFNO2;
	}
	public void setREFNO2(String rEFNO2) {
		REFNO2 = rEFNO2;
	}
	public String getUTILITY_CHECK_STATUS() {
		return UTILITY_CHECK_STATUS;
	}
	public void setUTILITY_CHECK_STATUS(String uTILITY_CHECK_STATUS) {
		UTILITY_CHECK_STATUS = uTILITY_CHECK_STATUS;
	}
	public String getENCDATA_U() {
		return ENCDATA_U;
	}
	public void setENCDATA_U(String eNCDATA_U) {
		ENCDATA_U = eNCDATA_U;
	}
	public String getSTATEMENTDETAILS() {
		return STATEMENTDETAILS;
	}
	public void setSTATEMENTDETAILS(String sTATEMENTDETAILS) {
		STATEMENTDETAILS = sTATEMENTDETAILS;
	}
	public String getTRACK2DATA() {
		return TRACK2DATA;
	}
	public void setTRACK2DATA(String tRACK2DATA) {
		TRACK2DATA = tRACK2DATA;
	}
	public String getPOSENTRYMODE() {
		return POSENTRYMODE;
	}
	public void setPOSENTRYMODE(String pOSENTRYMODE) {
		POSENTRYMODE = pOSENTRYMODE;
	}
	public String getPOSTFUNCTIONCODE() {
		return POSTFUNCTIONCODE;
	}
	public void setPOSTFUNCTIONCODE(String pOSTFUNCTIONCODE) {
		POSTFUNCTIONCODE = pOSTFUNCTIONCODE;
	}
	public String getTXNMODE() {
		return TXNMODE;
	}
	public void setTXNMODE(String txnmode) {
		TXNMODE = txnmode;
	}
	public String getNIC() {
		return NIC;
	}
	public void setNIC(String nic) {
		NIC = nic;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String date) {
		DATE = date;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String time) {
		TIME = time;
	}
	public double getHITTIME() {
		return HITTIME;
	}
	public void setHITTIME(double hittime) {
		HITTIME = hittime;
	}
	public double getLEAVETIME() {
		return LEAVETIME;
	}
	public void setLEAVETIME(double leavetime) {
		LEAVETIME = leavetime;
	}
	public String getPROVIDERID() {
		return PROVIDERID;
	}
	public void setPROVIDERID(String providerid) {
		PROVIDERID = providerid;
	}
	public String getREFERANCENO() {
		return REFERANCENO;
	}
	public void setREFERANCENO(String referanceno) {
		REFERANCENO = referanceno;
	}
	public String getTXNTYPE() {
		return TXNTYPE;
	}
	public void setTXNTYPE(String txntype) {
		TXNTYPE = txntype;
	}
	public String getCOLLECTMODE() {
		return COLLECTMODE;
	}
	public void setCOLLECTMODE(String collectmode) {
		COLLECTMODE = collectmode;
	}
	

	
	public String getUSEAGE() {
		return USEAGE;
	}
	public void setUSEAGE(String useage) {
		USEAGE = useage;
	}
	public String getUSRNIC() {
		return USRNIC;
	}
	public void setUSRNIC(String usrnic) {
		USRNIC = usrnic;
	}
	public String getPROCESSINGTIME() {
		return PROCESSINGTIME;
	}
	public void setPROCESSINGTIME(String processingtime) {
		PROCESSINGTIME = processingtime;
	}
	public String getACCOUNTNUMBER() {
		return ACCOUNTNUMBER;
	}
	public void setACCOUNTNUMBER(String accountnumber) {
		ACCOUNTNUMBER = accountnumber;
	}
	public String getRESPONSECODE() {
		return RESPONSECODE;
	}
	public void setRESPONSECODE(String responsecode) {
		RESPONSECODE = responsecode;
	}
	public String getSID() {
		return SID;
	}
	public void setSID(String sid) {
		SID = sid;
	}
	public String getMTI() {
		return MTI;
	}
	public void setMTI(String mti) {
		MTI = mti;
	}
	public String getPROCESSINGCODE() {
		return PROCESSINGCODE;
	}
	public void setPROCESSINGCODE(String processingcode) {
		PROCESSINGCODE = processingcode;
	}
	public String getTXNAMOUNT() {
		return TXNAMOUNT;
	}
	public void setTXNAMOUNT(String txnamount) {
		TXNAMOUNT = txnamount;
	}
	public String getCORDNUMBER() {
		return CORDNUMBER;
	}
	public void setCORDNUMBER(String cordnumber) {
		CORDNUMBER = cordnumber;
	}
	public String getTRACENUMBER() {
		return TRACENUMBER;
	}
	public void setTRACENUMBER(String tracenumber) {
		TRACENUMBER = tracenumber;
	}
	public String getINVOICENUMBER() {
		return INVOICENUMBER;
	}
	public void setINVOICENUMBER(String invoicenumber) {
		INVOICENUMBER = invoicenumber;
	}
	public String getMID() {
		return MID;
	}
	public void setMID(String mid) {
		MID = mid;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tid) {
		TID = tid;
	}
	public String getRRN() {
		return RRN;
	}
	public void setRRN(String rrn) {
		RRN = rrn;
	}
	public String getAUTHCODE() {
		return AUTHCODE;
	}
	public void setAUTHCODE(String authcode) {
		AUTHCODE = authcode;
	}
	public String getCURRENTDATETIME() {
		return CURRENTDATETIME;
	}
	public void setCURRENTDATETIME(String currentdatetime) {
		CURRENTDATETIME = currentdatetime;
	}
	public String getNII() {
		return NII;
	}
	public void setNII(String nii) {
		NII = nii;
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String pin) {
		PIN = pin;
	}
	public String getFNAME() {
		return FNAME;
	}
	public void setFNAME(String fname) {
		FNAME = fname;
	}
	public String getLNAME() {
		return LNAME;
	}
	public void setLNAME(String lname) {
		LNAME = lname;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String userid) {
		USERID = userid;
	}
	public String getDB_TID() {
		return DB_TID;
	}
	public void setDB_TID(String dB_TID) {
		DB_TID = dB_TID;
	}
	public String getDB_MID() {
		return DB_MID;
	}
	public void setDB_MID(String dB_MID) {
		DB_MID = dB_MID;
	}
	public String getDB_PASSWORD() {
		return DB_PASSWORD;
	}
	public void setDB_PASSWORD(String dB_PASSWORD) {
		DB_PASSWORD = dB_PASSWORD;
	}
	public String getDB_USER_STATUS() {
		return DB_USER_STATUS;
	}
	public void setDB_USER_STATUS(String dB_USER_STATUS) {
		DB_USER_STATUS = dB_USER_STATUS;
	}
	public String getDB_USERROLE_STATUS() {
		return DB_USERROLE_STATUS;
	}
	public void setDB_USERROLE_STATUS(String dB_USERROLE_STATUS) {
		DB_USERROLE_STATUS = dB_USERROLE_STATUS;
	}
	public String getDB_USERROLETYPE_STATUS() {
		return DB_USERROLETYPE_STATUS;
	}
	public void setDB_USERROLETYPE_STATUS(String dB_USERROLETYPE_STATUS) {
		DB_USERROLETYPE_STATUS = dB_USERROLETYPE_STATUS;
	}
	public String getDB_TERMINAL_STATUS() {
		return DB_TERMINAL_STATUS;
	}
	public void setDB_TERMINAL_STATUS(String dB_TERMINAL_STATUS) {
		DB_TERMINAL_STATUS = dB_TERMINAL_STATUS;
	}
	public String getDB_TERMINALTYPE_STATUS() {
		return DB_TERMINALTYPE_STATUS;
	}
	public void setDB_TERMINALTYPE_STATUS(String dB_TERMINALTYPE_STATUS) {
		DB_TERMINALTYPE_STATUS = dB_TERMINALTYPE_STATUS;
	}
	public String getDB_BRANCH_STATUS() {
		return DB_BRANCH_STATUS;
	}
	public void setDB_BRANCH_STATUS(String dB_BRANCH_STATUS) {
		DB_BRANCH_STATUS = dB_BRANCH_STATUS;
	}
	public String getDB_MERCHANT_STATUS() {
		return DB_MERCHANT_STATUS;
	}
	public void setDB_MERCHANT_STATUS(String dB_MERCHANT_STATUS) {
		DB_MERCHANT_STATUS = dB_MERCHANT_STATUS;
	}
	public DBprocessing getDbProcessingOb() {
		return dbProcessingOb;
	}
	public void setDbProcessingOb(DBprocessing dbProcessingOb) {
		this.dbProcessingOb = dbProcessingOb;
	}
	public Connection getDbConnectionOb() {
		return dbConnectionOb;
	}
	public void setDbConnectionOb(Connection dbConnectionOb) {
		this.dbConnectionOb = dbConnectionOb;
	}
	public String getENCDATA() {
		return ENCDATA;
	}
	public void setENCDATA(String eNCDATA) {
		ENCDATA = eNCDATA;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setERROR_DESCRIPTION(String eRROR_DESCRIPTION) {
		ERROR_DESCRIPTION = eRROR_DESCRIPTION;
	}
	public String getERROR_DESCRIPTION() {
		return ERROR_DESCRIPTION;
	}
	public void setTXNCOMPANY(String tXNCOMPANY) {
		TXNCOMPANY = tXNCOMPANY;
	}
	public String getTXNCOMPANY() {
		return TXNCOMPANY;
	}

	public void setTXNDATA(String tXNDATA) {
		TXNDATA = tXNDATA;
	}
	public String getTXNDATA() {
		return TXNDATA;
	}
	public void setDB_ACCOUNT_DATA(String dB_ACCOUNT_DATA) {
		DB_ACCOUNT_DATA = dB_ACCOUNT_DATA;
	}
	public String getDB_ACCOUNT_DATA() {
		return DB_ACCOUNT_DATA;
	}
	public void setBRANCH(String bRANCH) {
		BRANCH = bRANCH;
	}
	public String getBRANCH() {
		return BRANCH;
	}
	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}
	public String getCOMPANY() {
		return COMPANY;
	}
	public void setDB_COMPANY_STATUS(String dB_COMPANY_STATUS) {
		DB_COMPANY_STATUS = dB_COMPANY_STATUS;
	}
	public String getDB_COMPANY_STATUS() {
		return DB_COMPANY_STATUS;
	}
	public void setDB_TXNCOMPANY_STATUS(String dB_TXNCOMPANY_STATUS) {
		DB_TXNCOMPANY_STATUS = dB_TXNCOMPANY_STATUS;
	}
	public String getDB_TXNCOMPANY_STATUS() {
		return DB_TXNCOMPANY_STATUS;
	}
	public void setMAXPIN_RETRY(String mAXPIN_RETRY) {
		MAXPIN_RETRY = mAXPIN_RETRY;
	}
	public String getMAXPIN_RETRY() {
		return MAXPIN_RETRY;
	}
	public void setDB_PIN_COUNT(String dB_PIN_COUNT) {
		DB_PIN_COUNT = dB_PIN_COUNT;
	}
	public String getDB_PIN_COUNT() {
		return DB_PIN_COUNT;
	}
	public void setUSERROLECODE(String uSERROLECODE) {
		USERROLECODE = uSERROLECODE;
	}
	public String getUSERROLECODE() {
		return USERROLECODE;
	}
	public void setDB_TRANSACTION_STATUS(String dB_TRANSACTION_STATUS) {
		DB_TRANSACTION_STATUS = dB_TRANSACTION_STATUS;
	}
	public String getDB_TRANSACTION_STATUS() {
		return DB_TRANSACTION_STATUS;
	}

	public void setREVERSAL_RRN(String rEVERSAL_RRN) {
		REVERSAL_RRN = rEVERSAL_RRN;
	}
	public String getREVERSAL_RRN() {
		return REVERSAL_RRN;
	}
	public void setREVERSAL_SID(String rEVERSAL_SID) {
		REVERSAL_SID = rEVERSAL_SID;
	}
	public String getREVERSAL_SID() {
		return REVERSAL_SID;
	}
	public void setTRANSACTON_NO(String tRANSACTON_NO) {
		TRANSACTON_NO = tRANSACTON_NO;
	}
	public String getTRANSACTON_NO() {
		return TRANSACTON_NO;
	}
	public int getLIMIT_TXNCOUNT() {
		return LIMIT_TXNCOUNT;
	}
	public void setLIMIT_TXNCOUNT(int lIMIT_TXNCOUNT) {
		LIMIT_TXNCOUNT = lIMIT_TXNCOUNT;
	}
	public double getLIMIT_TOTALAMOUNT() {
		return LIMIT_TOTALAMOUNT;
	}
	public void setLIMIT_TOTALAMOUNT(double lIMIT_TOTALAMOUNT) {
		LIMIT_TOTALAMOUNT = lIMIT_TOTALAMOUNT;
	}
	public int getLIMIT_DAILYTXNCOUNT() {
		return LIMIT_DAILYTXNCOUNT;
	}
	public void setLIMIT_DAILYTXNCOUNT(int lIMIT_DAILYTXNCOUNT) {
		LIMIT_DAILYTXNCOUNT = lIMIT_DAILYTXNCOUNT;
	}
	public double getLIMIT_DAILYTXNLIMIT() {
		return LIMIT_DAILYTXNLIMIT;
	}
	public void setLIMIT_DAILYTXNLIMIT(double lIMIT_DAILYTXNLIMIT) {
		LIMIT_DAILYTXNLIMIT = lIMIT_DAILYTXNLIMIT;
	}
	public double getLIMIT_PERTXNLIMIT() {
		return LIMIT_PERTXNLIMIT;
	}
	public void setLIMIT_PERTXNLIMIT(double lIMIT_PERTXNLIMIT) {
		LIMIT_PERTXNLIMIT = lIMIT_PERTXNLIMIT;
	}
	public double getLIMIT_MINIMUMPERTXNLIMIT() {
		return LIMIT_MINIMUMPERTXNLIMIT;
	}
	public void setLIMIT_MINIMUMPERTXNLIMIT(double lIMIT_MINIMUMPERTXNLIMIT) {
		LIMIT_MINIMUMPERTXNLIMIT = lIMIT_MINIMUMPERTXNLIMIT;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setHostConnection(HostConnection hostConnection) {
		this.hostConnection = hostConnection;
	}
	public HostConnection getHostConnection() {
		return hostConnection;
	}
	public void setHOSTNAME(String hOSTNAME) {
		HOSTNAME = hOSTNAME;
	}
	public String getHOSTNAME() {
		return HOSTNAME;
	}
	public void setUSERROLEVALIDITY(String uSERROLEVALIDITY) {
		USERROLEVALIDITY = uSERROLEVALIDITY;
	}
	public String getUSERROLEVALIDITY() {
		return USERROLEVALIDITY;
	}
	public void setTXNBRANCH(String tXNBRANCH) {
		TXNBRANCH = tXNBRANCH;
	}
	public String getTXNBRANCH() {
		return TXNBRANCH;
	}
	public void setTXNNAME(String tXNNAME) {
		TXNNAME = tXNNAME;
	}
	public String getTXNNAME() {
		return TXNNAME;
	}
	public void setHOSTSENDTIME(double hOSTSENDTIME) {
		HOSTSENDTIME = hOSTSENDTIME;
	}
	public double getHOSTSENDTIME() {
		return HOSTSENDTIME;
	}
	public void setHOSTRECVTIME(double hOSTRECVTIME) {
		HOSTRECVTIME = hOSTRECVTIME;
	}
	public double getHOSTRECVTIME() {
		return HOSTRECVTIME;
	}
	public void setDB_TXNBRANCH_STATUS(String dB_TXNBRANCH_STATUS) {
		DB_TXNBRANCH_STATUS = dB_TXNBRANCH_STATUS;
	}
	public String getDB_TXNBRANCH_STATUS() {
		return DB_TXNBRANCH_STATUS;
	}
	public String getDB_FROMTIMEQTY() {
		return DB_FROMTIMEQTY;
	}
	public void setDB_FROMTIMEQTY(String dB_FROMTIMEQTY) {
		DB_FROMTIMEQTY = dB_FROMTIMEQTY;
	}
	public String getDB_TOTIMEQTY() {
		return DB_TOTIMEQTY;
	}
	public void setDB_TOTIMEQTY(String dB_TOTIMEQTY) {
		DB_TOTIMEQTY = dB_TOTIMEQTY;
	}
	public String getDB_FROMTIMEPERIOD() {
		return DB_FROMTIMEPERIOD;
	}
	public void setDB_FROMTIMEPERIOD(String dB_FROMTIMEPERIOD) {
		DB_FROMTIMEPERIOD = dB_FROMTIMEPERIOD;
	}
	public String getDB_TOTIMEPERIOD() {
		return DB_TOTIMEPERIOD;
	}
	public void setDB_TOTIMEPERIOD(String dB_TOTIMEPERIOD) {
		DB_TOTIMEPERIOD = dB_TOTIMEPERIOD;
	}
	public String getXML_PER_BATCH_LIMIT() {
		return XML_PER_BATCH_LIMIT;
	}
	public void setXML_PER_BATCH_LIMIT(String xML_PER_BATCH_LIMIT) {
		XML_PER_BATCH_LIMIT = xML_PER_BATCH_LIMIT;
	}
	public String getCHQ_NO() {
		return CHQ_NO;
	}
	public void setCHQ_NO(String cHQ_NO) {
		CHQ_NO = cHQ_NO;
	}
	public Date getEXPIRYDATE() {
		return EXPIRYDATE;
	}
	public void setEXPIRYDATE(Date eXPIRYDATE) {
		EXPIRYDATE = eXPIRYDATE;
	}
	public Date getDB_CURRENTDATE() {
		return DB_CURRENTDATE;
	}
	public void setDB_CURRENTDATE(Date dB_CURRENTDATE) {
		DB_CURRENTDATE = dB_CURRENTDATE;
	}
	

}
