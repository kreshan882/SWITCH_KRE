package com.epic.mfn.txn;

import java.sql.Connection;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.ISO87BPackager;

import com.epic.mfn.connect.HostConnection;
import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.init.ProcessingRequest;
import com.epic.mfn.logs.LogFileCreator;


import com.epic.mfn.util.DBconnections;
import com.epic.mfn.util.DBprocessing;
import com.epic.mfn.util.PrintLogs;
import com.epic.mfn.util.ResponseCode;
import com.epic.mfn.util.TxnCodes;
import com.epic.mfn.util.TxnHelpMethods;
import com.epic.mfn.util.TxnKeyWords;

public class RecvRequest {

	private TxnKeyWords keyWD 				= null;
	private ProcessingRequest procrequest   = null;
	private ISOMsg reponseMsg 				= null;
	private Connection con					= null;
	private DBprocessing dbpr				= null;


	private void startProcess()throws Exception{

		try{
			
			ISOMsg m = new ISOMsg();
			m.setPackager(new ISO87BPackager());
			m.unpack(procrequest.getREQUESTPACKET());
						
			keyWD = procrequest.getKeyWD();
			con = DBconnections.getConnection();
			keyWD.setDbConnectionOb(con);
			dbpr = new DBprocessing();
			keyWD.setDbProcessingOb(dbpr);
			keyWD.setMTI( m.getMTI().toString());
			keyWD.setPROCESSINGCODE(m.getValue(3).toString().trim());
			keyWD.setNII(m.getValue(24).toString());
			keyWD.setNII(m.getValue(24).toString());
			if(null != m.getValue(48)){
				keyWD.setHOSTNAME(m.getValue(48).toString());
			}
			if(null != m.getValue(58)){
				keyWD.setCHQ_NO(m.getValue(58).toString());
			}
			selectBackendHost(keyWD);
				
				boolean invalidMessage = true;
				if( TxnCodes.MTI_MF_AUTH_MESSAGE.equals(keyWD.getMTI())){
					
					if(TxnCodes.PC_MF_CASH_DIPOSIT_ACCREQ.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Cash Diposit Acc Request Transaction ....");
						TxnMFCashDepositAccountRequest txnMFCashDepositAccountRequest = new TxnMFCashDepositAccountRequest();
						reponseMsg = txnMFCashDepositAccountRequest.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_CASH_WITHDRAWAL_ACCREQ.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Cash Withdrawal Acc Request Transaction ....");
						TxnMFCashWithdrawalAccountRequest txnMFCashWithdrawalAccountRequest = new TxnMFCashWithdrawalAccountRequest();
						reponseMsg = txnMFCashWithdrawalAccountRequest.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_MINI_STATEMENT_ACCREQ.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Mini statment Acc Request Transaction ....");
						TxnMFMiniStatementAccountRequest txnMFMiniStatementAccountRequest = new TxnMFMiniStatementAccountRequest();
						reponseMsg = txnMFMiniStatementAccountRequest.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_MINI_STATEMENT_TRANSAACTION.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Mini statment Transaction ....");
						TxnMFMiniStatementTransacton txnMFMiniStatementTransacton = new TxnMFMiniStatementTransacton();
						reponseMsg = txnMFMiniStatementTransacton.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_BALANCE_INQUERY_ACCREQ.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("balance inquery Acc Request Transaction ....");
						TxnMFBalanceInqueryAccountRequest txnMFBalanceInqueryAccountRequest = new TxnMFBalanceInqueryAccountRequest();
						reponseMsg = txnMFBalanceInqueryAccountRequest.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_BALANCE_INQUERY_TRANSAACTION.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Balance Inquery  Transaction ....");
						TxnMFBalanceInqueryTransaction txnMFBalanceInqueryTransaction = new TxnMFBalanceInqueryTransaction();
						reponseMsg = txnMFBalanceInqueryTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_FUND_TRANCEFER_ACCREQ.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Fund Transefer Acc Request Transaction ....");
						TxnMFFundTransferAccountRequest txnMFFundTransferAccountRequest = new TxnMFFundTransferAccountRequest();
						reponseMsg = txnMFFundTransferAccountRequest.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_FUND_TRANCEFER_TO_ACCREQ.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Fund Transefer To Acc Request Transaction ....");
						TxnMFFundTransferToAccountRequest txnMFFundTransferToAccountRequest = new TxnMFFundTransferToAccountRequest();
						reponseMsg = txnMFFundTransferToAccountRequest.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_RENTAL_COLLECTION_ACCREQ.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Rental Collection Acc Request Transaction ....");
						TxnMFRentalCollectionAccountRequest txnMFRentalCollectionAccountRequest = new TxnMFRentalCollectionAccountRequest();
						reponseMsg = txnMFRentalCollectionAccountRequest.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_INSURANCE_COLLECTION_ACCREQ.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Insurance Collection Acc Request Transaction ....");
						TxnMFInsuranceCollectionAccountRequest txnMFInsuranceCollectionAccountRequest = new TxnMFInsuranceCollectionAccountRequest();
						reponseMsg = txnMFInsuranceCollectionAccountRequest.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_PASSBOOK_FIRST_PAGE_PRINTING.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine(" Passbook first page print transaction ....");
						TxnMFPassBookFirstpagePrinting txnMFPassBookFirstpagePrinting = new TxnMFPassBookFirstpagePrinting();
						reponseMsg = txnMFPassBookFirstpagePrinting.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_PASSBOOK_TRANSACTION_PRINTING.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Passbook printing Transaction ....");
						TxnMFPassBookTransactionPrinting txnMFPassBookTransactionPrinting = new TxnMFPassBookTransactionPrinting();
						reponseMsg = txnMFPassBookTransactionPrinting.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_UTILITY_PROVIDED_LIST.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Utility Provided list download....");
						TxnMFUtilityProvidedList txnMFUtilityProvidedList = new TxnMFUtilityProvidedList();
						reponseMsg = txnMFUtilityProvidedList.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_UTILITY_SERVICE_CHARGE.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Utility Service Charge Transection....");
						TxnMFUtilityServiceCharge txnMFUtilityServiceCharge = new TxnMFUtilityServiceCharge();
						reponseMsg = txnMFUtilityServiceCharge.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_UTILITY_BILL_PAYMENT_STATUS.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Transaction Utility Bill Payment Status Check...");
						TxnMFUtilityBillPaymentStatus  txnMFUtilityBillPaymentStatus = new TxnMFUtilityBillPaymentStatus();
						reponseMsg = txnMFUtilityBillPaymentStatus.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}
					
							
				}else if(TxnCodes.MTI_MF_FINANCIAL_MESSAGE.equals(keyWD.getMTI())){
					
					if( TxnCodes.PC_MF_VOID.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Void Transaction ...");
						TxnMFVoidTransaction txnMFVoidTransaction = new TxnMFVoidTransaction();
						reponseMsg = txnMFVoidTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}
					else if(TxnCodes.PC_MF_CASH_DIPOSIT_TRANSAACTION.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Cash Diposit Transaction ...");
						TxnMFCashDepositTransaction txnMFCashDepositTransaction = new TxnMFCashDepositTransaction();
						reponseMsg = txnMFCashDepositTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_CASH_WITHDRAWAL_TRANSAACTION.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Cash Withdrawal Transaction ...");
						TxnMFCashWithdrawalTransaction txnMFCashWithdrawalTransaction = new TxnMFCashWithdrawalTransaction();
						reponseMsg = txnMFCashWithdrawalTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_FUND_TRANCEFER_TRANSAACTION.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Fund Transfer Transaction ...");
						TxnMFFundTransferTransaction txnMFFundTransferTransaction = new TxnMFFundTransferTransaction();
						reponseMsg = txnMFFundTransferTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_ACCOUNT_OPENINIG_TRANSACTION.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Account Opening Transacion ...."+keyWD.getPROCESSINGCODE());
						TxnMFAccountOpeningTransaction txnMFAccountOpeningTransaction= new TxnMFAccountOpeningTransaction();
						reponseMsg = txnMFAccountOpeningTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_RENTAL_COLLECTION_TRANSAACTION.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Rental Collection Transaction ...");
						TxnMFRentalCollectionTransaction txnMFRentalCollectionTransaction = new TxnMFRentalCollectionTransaction();
						reponseMsg = txnMFRentalCollectionTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_INSURANCE_COLLECTION_TRANSAACTION.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Insurance Collection Transaction ...");
						TxnMFInsuranceCollectionTransaction txnMFInsuranceCollectionTransaction = new TxnMFInsuranceCollectionTransaction();
						reponseMsg = txnMFInsuranceCollectionTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
						
					}else if(TxnCodes.PC_MF_BATCH_CLEAR.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Transaction Batch Clear ...");
						TxnMFBatchClear  txnMFBatchClear = new TxnMFBatchClear();
						reponseMsg = txnMFBatchClear.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_UTILITY_BILL_PAYMENT.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Transaction Utility Bill Payment ...");
						TxnMFUtilityBillPayment  txnMFUtilityBillPayment = new TxnMFUtilityBillPayment();
						reponseMsg = txnMFUtilityBillPayment.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}
					
				}else if(TxnCodes.MTI_MF_REVERSAL_MESSAGE.equals(keyWD.getMTI())){
					
					if( TxnCodes.PC_MF_VOID_REEVRSAL.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Void Transaction ...");
						TxnMFVoidReversal txnMFVoidTransaction = new TxnMFVoidReversal();
						reponseMsg = txnMFVoidTransaction.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_CASH_DIPOSIT_REEVRSAL.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Cash Diposit Reversal Transaction ....");
						TxnMFCashDepositReversal txnMFCashDepositReversal = new TxnMFCashDepositReversal();
						reponseMsg = txnMFCashDepositReversal.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_CASH_WITHDRAWAL_REEVRSAL.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Cash Withdrawal Reversal Transaction ....");
						TxnMFCashWithdrawalReversal txnMFCashWithdrawalReversal = new TxnMFCashWithdrawalReversal();
						reponseMsg = txnMFCashWithdrawalReversal.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_FUND_TRANCEFER_REEVRSAL.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Fund Transfer Reversal Transaction ....");
						TxnMFFundTransferReversal txnMFFundTransferReversal = new TxnMFFundTransferReversal();
						reponseMsg = txnMFFundTransferReversal.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_ACCOUNT_OPENINIG_REVERSAL.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Account Opening Reversal Transaction ...."+keyWD.getPROCESSINGCODE());
						TxnMFAccountOpeningReversalTransaction txnMFAccountOpeningReversalTransaction = new TxnMFAccountOpeningReversalTransaction();
						reponseMsg = txnMFAccountOpeningReversalTransaction.doProcessRequest(m , keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_RENTAL_COLLECTION_REEVRSAL.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Rental Collection Reversal Transaction ...."+keyWD.getPROCESSINGCODE());
						TxnMFRentalCollectionReversal txnMFRentalCollectionReversal = new TxnMFRentalCollectionReversal();
						reponseMsg = txnMFRentalCollectionReversal.doProcessRequest(m , keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_INSURANCE_COLLECTION_REEVRSAL.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Insurance Collection Reversal Transaction ...."+keyWD.getPROCESSINGCODE());
						TxnMFInsuranceCollectionReversal  txnMFInsuranceCollectionReversal = new TxnMFInsuranceCollectionReversal();
						reponseMsg = txnMFInsuranceCollectionReversal.doProcessRequest(m , keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_BATCH_CLEAR_REV.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Transaction Batch Clear ...");
						TxnMFBatchClearRev  txnMFBatchClearRev = new TxnMFBatchClearRev();
						reponseMsg = txnMFBatchClearRev.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}	
					
				}else if(TxnCodes.MTI_MF_NETWORK_MESSAGE.equals(keyWD.getMTI())){
					if(TxnCodes.PC_MF_ONLINE_LOGIN.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Transaction Online Login ....");
						TxnMFOnlineLogin  txnMicroFinanceOnlineLogin = new TxnMFOnlineLogin();
						reponseMsg = txnMicroFinanceOnlineLogin.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}else if(TxnCodes.PC_MF_BATCH_CLEAR.equals(keyWD.getPROCESSINGCODE())){
						PrintLogs.printLine("Transaction Batch Clear ...");
						TxnMFBatchClear  txnMFBatchClear = new TxnMFBatchClear();
						reponseMsg = txnMFBatchClear.doProcessRequest(m, keyWD);
						invalidMessage = false;
						writeTosocket();
					}
				}		
				
				if(invalidMessage){
					PrintLogs.printLine("Transaction invalid....");
					TxnHelpMethods.printPacket(m, keyWD,"Request element values from POS..");
					m.setMTI(TxnHelpMethods.getResponseMTI(keyWD.getMTI()));
					keyWD.setRESPONSECODE(ResponseCode.ERROR_IN_REQUEST);
					keyWD.setERROR_DESCRIPTION(ResponseCode.MSG_ERROR_IN_REQUEST);
					m.set(39,keyWD.getRESPONSECODE());
					m.set(60,keyWD.getERROR_DESCRIPTION());
					reponseMsg = m;
					writeTosocket();
				}
			
		}catch (Exception ex){
			PrintLogs.printLine("Error is occurred while transaction is being processed..");
			LogFileCreator.writErrorTologs(ex);
			
			throw ex;
		}
		
		
	}
	

	public void handleRequestFordeman(ProcessingRequest proc)throws Exception{
		try {
			procrequest = proc;
			startProcess();
			System.out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBconnections.relieasConnection(con);
			} catch (Exception e) {
				e.printStackTrace();
				LogFileCreator.writErrorTologs(e);
			}
		}
	}
	
	private void writeTosocket()throws Exception{
		
		TxnHelpMethods.printPacket(reponseMsg, keyWD,"Response element values to POS..");
		
		byte rtpdu[] = TxnHelpMethods.getResponseTPDU(procrequest.getREQUESTTPDU(), keyWD);
		byte responsePacket[] = reponseMsg.pack();
		byte requestlen[] = TxnHelpMethods.getHexLength(rtpdu, reponseMsg.pack());
		byte responseForNac[] = TxnHelpMethods.getResponseForNac(requestlen, rtpdu, responsePacket);
	
		if(InitConfigValue.CONSOLELEVEL >=2){
			System.out.println("Response to POS > "+ISOUtil.hexString(responseForNac));
		}
		if(InitConfigValue.LOGLEVEL >=2){
			LogFileCreator.writInforTologs("Response to POS > "+ISOUtil.hexString(responseForNac),keyWD);
		}
		
		procrequest.getOUTSTREAM().write(responseForNac);
		

		
		
		procrequest.getOUTSTREAM().flush();
		keyWD.setLEAVETIME(System.nanoTime());
		TxnHelpMethods.getProcessingTime(keyWD);
		PrintLogs.putTxnLogs(keyWD);
				
		
	}
	
	private boolean selectBackendHost(TxnKeyWords keyWD)throws Exception{
		if(null != keyWD.getHOSTNAME()){
			HostConnection host = InitConfigValue.hostConnection.get(keyWD.getHOSTNAME());
			if(null != host){
				keyWD.setHostConnection(host);
				return true;
			}
		}
		return false;
	}



}
