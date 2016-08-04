package com.epic.mfn.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.epic.mfn.logs.LogFileCreator;

public class DBprocessing {
	


	public boolean getTerminalUserDeatils(Connection con, TxnKeyWords keyWD) throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT  GETDATE() AS CURRENTDATE, terminaluser.PASSWORD, terminaluser.TERMINALID, terminaluser.EXPIERYDATE, terminaluser.STATUS AS USER_STATUS, "
						+ "userrole.STATUS AS USERROLE_STATUS , userrole.VALIDITY , userroletype.STATUS AS USERROELTYPE_STATUS , userrole.USERROLECODE AS USERROLE ,terminaluser.PINCOUNT FROM terminaluser "
						+ "INNER JOIN userrole ON terminaluser.USERROLECODE = userrole.USERROLECODE INNER JOIN userroletype ON userrole.USERROLETYPECODE= userroletype.USERROLETYPECODE "
						+ "WHERE terminaluser.USERNAME = ? ;";
		perSt = con.prepareStatement(sql);
		perSt.setString(1, keyWD.getUSERID());
		res = perSt.executeQuery();

		if (res.next()) {

			keyWD.setDB_PASSWORD(res.getString("PASSWORD"));
			keyWD.setDB_TID(res.getString("TERMINALID"));
			keyWD.setDB_USER_STATUS(res.getString("USER_STATUS"));
			keyWD.setDB_USERROLE_STATUS(res.getString("USERROLE_STATUS"));
			keyWD.setDB_USERROLETYPE_STATUS(res.getString("USERROELTYPE_STATUS"));
			keyWD.setDB_PIN_COUNT(res.getString("PINCOUNT"));
			keyWD.setUSERROLECODE(res.getString("USERROLE"));
			keyWD.setUSERROLEVALIDITY(res.getString("VALIDITY"));
			keyWD.setEXPIRYDATE(res.getDate("EXPIERYDATE")) ;
			keyWD.setDB_CURRENTDATE(res.getDate("CURRENTDATE")) ;
			
			
			return true;
		}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}

	public ArrayList<String> getUserProfile(Connection con, TxnKeyWords keyWD)throws Exception {
		PreparedStatement perSt = null;
		ArrayList<String> txnCompany = new ArrayList<String>();
		ResultSet res = null;
		try {
			String sql = "SELECT USERROLE,COMPANYCODE  FROM terminalprofile WHERE terminalprofile.TXNTYPECODE = ? " +
					"AND terminalprofile.COMPANYCODE = ? AND terminalprofile.USERROLE = ?;";

			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getTXNTYPE());
			perSt.setString(2, keyWD.getTXNCOMPANY()) ;
			perSt.setString(3, keyWD.getUSERROLECODE());
			res = perSt.executeQuery();
		
			while (res.next()) {
				txnCompany.add(res.getString("COMPANYCODE"));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return txnCompany;
	}
	
	
	public boolean getTxnTypeDetails(Connection con, TxnKeyWords keyWD)throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT STATUS FROM transactiontype WHERE TRANSACTIONTYPECODE = ? ";
			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getTXNTYPE());
			//perSt.setString(2, keyWD.getTXNCOMPANY());
			res = perSt.executeQuery();

			if (res.next()) {
				keyWD.setDB_TRANSACTION_STATUS(res.getString("STATUS"));
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}
	
	public boolean getTxnBranchDetails(Connection con, TxnKeyWords keyWD)throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT STATUS FROM branch WHERE BRANCHPRIFIX = ? AND COMPANY = ?";
			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getTXNBRANCH());
			perSt.setString(2, keyWD.getTXNCOMPANY());
			res = perSt.executeQuery();

			if (res.next()) {
				keyWD.setDB_TXNBRANCH_STATUS(res.getString("STATUS"));
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}
	
	public boolean getTxnCompanyDetails(Connection con, TxnKeyWords keyWD)throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT STATUS FROM company WHERE COMPANYCODE = ?";
			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getTXNCOMPANY());
			res = perSt.executeQuery();

			if (res.next()) {
				keyWD.setDB_TXNCOMPANY_STATUS(res.getString("STATUS"));
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}
	
	

	
	public boolean getTerminalDeatils(Connection con, TxnKeyWords keyWD) throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT terminal.STATUS AS TERMINAL_STATUS, terminaltype.STATUS AS TERMINALTYPE_STATUS, branch.STATUS AS BRANCH_STATUS, company.STATUS AS COMPANY_STATUS , branch.BRANCHPRIFIX AS BRANCH , company.COMPANYCODE AS COMPANY , terminal.PLCBRANCH , terminal.PLFBRANCH , terminal.PMFBRANCH , terminal.PILBRANCH FROM terminal "
					+ "INNER JOIN terminaltype ON terminal.TERMINALTYPECODE = terminaltype.TERMINALTYPECODE "
					+ "INNER JOIN branch ON terminal.BRANCHPRIFIX = branch.BRANCHPRIFIX "
					+ "INNER JOIN company ON terminal.COMPANY = company.COMPANYCODE "
					+ "WHERE  terminal.TERMINALID = ?";

			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getTID());

			res = perSt.executeQuery();

			if (res.next()) {

				keyWD.setDB_TERMINAL_STATUS(res.getString("TERMINAL_STATUS"));
				keyWD.setDB_TERMINALTYPE_STATUS(res.getString("TERMINALTYPE_STATUS"));
				keyWD.setDB_BRANCH_STATUS(res.getString("BRANCH_STATUS"));
				keyWD.setDB_COMPANY_STATUS(res.getString("COMPANY_STATUS"));
				keyWD.setBRANCH(res.getString("BRANCH"));
				keyWD.setCOMPANY(res.getString("COMPANY"));
				
				if("PLC".equalsIgnoreCase(keyWD.getTXNCOMPANY())){
					keyWD.setTXNBRANCH(res.getString("PLCBRANCH")); 
				}else if("PLF".equalsIgnoreCase(keyWD.getTXNCOMPANY())){
					keyWD.setTXNBRANCH(res.getString("PLFBRANCH"));
				}else if("PMF".equalsIgnoreCase(keyWD.getTXNCOMPANY())){
					keyWD.setTXNBRANCH(res.getString("PMFBRANCH"));
				}else if("PIL".equalsIgnoreCase(keyWD.getTXNCOMPANY())){
					keyWD.setTXNBRANCH(res.getString("PILBRANCH"));
				}
				
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}

	public boolean getTxnLimit(Connection con, TxnKeyWords keyWD) throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT DAILYTXNCOUNT, DAILYTXNLIMIT,PERTXNLIMIT,MINIMUMPERTXNLIMIT FROM txnlimitparam WHERE PARAMCODE = ?;";

			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getTXNTYPE());

			res = perSt.executeQuery();

			if (res.next()) {

				keyWD.setLIMIT_DAILYTXNCOUNT(res.getInt("DAILYTXNCOUNT"));
				keyWD.setLIMIT_DAILYTXNLIMIT(res.getDouble("DAILYTXNLIMIT"));
				keyWD.setLIMIT_PERTXNLIMIT(res.getDouble("PERTXNLIMIT"));
				keyWD.setLIMIT_MINIMUMPERTXNLIMIT(res.getDouble("MINIMUMPERTXNLIMIT"));
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}
	


	public double getTotalCollection( Connection con, TxnKeyWords keyWD)throws Exception {
	PreparedStatement perSt = null;
	ResultSet res = null;
	double collection = 0;
	
	try {
		
		String sql = "SELECT SUM (AMOUNT) AS TOTALAMOUNT FROM TRANSACTIONS WHERE TERMINALID = ? AND STATUS = ? AND FIELD6 = ? AND TRANSACTIONTYPECODE != ? AND TRANSACTIONTYPECODE != ? AND FIELD25 is null";

		perSt = con.prepareStatement(sql);
		perSt.setString(1, keyWD.getTID());
		perSt.setString(2, StatusCode.TXN_TRTT );
		perSt.setString(3, StatusCode.ACCTIVE_BATCH );
		perSt.setString(4, TxnTypeCodes.MF_FUND_TRANSFER );
		perSt.setString(5, TxnTypeCodes.MF_CASH_WITHDRAWAL );
		res = perSt.executeQuery();
	
		if (res.next()) {
			collection += res.getDouble("TOTALAMOUNT");
		}
		

		
	} catch (Exception e) {
		throw e;
	} finally {
		if (perSt != null) {
			perSt.close();
		}
		if (res != null) {
			res.close();
		}
	}
	
	return collection;
}
	

	
	public boolean getTerminalParam(Connection con, TxnKeyWords keyWD)
			throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT PARAMCODE, AMOUNT FROM TERMINALPARAM";
			perSt = con.prepareStatement(sql);
			res = perSt.executeQuery();

			if (res.next()) {
				if ("INATT".equals(res.getString("PARAMCODE"))) {
					keyWD.setMAXPIN_RETRY(res.getString("AMOUNT"));
				}
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}
	
	public boolean getOperateHourParam(Connection con, TxnKeyWords keyWD)
			throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT FROMTIMEQTY, TOTIMEQTY FROM OPERATEHOURPARAM WHERE PARAMCODE = 'BNSHRS' ";
			perSt = con.prepareStatement(sql);
			res = perSt.executeQuery();

			if (res.next()) {
				
				keyWD.setDB_FROMTIMEQTY( res.getString( "FROMTIMEQTY" ) ) ;
				keyWD.setDB_TOTIMEQTY( res.getString( "TOTIMEQTY" ) ) ;
				//keyWD.setDB_FROMTIMEPERIOD( res.getString( "FROMTIMEPERIOD" ) ) ;
				///keyWD.setDB_TOTIMEPERIOD( res.getString( "TOTIMEPERIOD" ) ) ;
				
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}

	public void increasePinTry(Connection con, TxnKeyWords keyWD)
			throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "UPDATE TERMINALUSER SET PINCOUNT = ? WHERE USERNAME = ?";

			perSt = con.prepareStatement(sql);
			perSt.setInt(1, (Integer.parseInt(keyWD.getDB_PIN_COUNT()) + 1));
			perSt.setString(2, keyWD.getUSERID()) ;
			perSt.execute();

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
	}
	
	public void markAsCleared(Connection con, TxnKeyWords keyWD) throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "UPDATE TRANSACTIONS SET FIELD6 = ? WHERE FIELD6 = ? AND TERMINALID = ?";
			perSt = con.prepareStatement(sql);
			perSt.setString(1, StatusCode.SETTLED_BATCH );
			perSt.setString(2, StatusCode.ACCTIVE_BATCH );
			perSt.setString(3, keyWD.getTID());
			perSt.execute();
		
		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
	}

	public void resetPinTry(Connection con, TxnKeyWords keyWD) throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "UPDATE TERMINALUSER SET PINCOUNT = ? WHERE USERNAME = ?";

			perSt = con.prepareStatement(sql);
			perSt.setInt(1, 0);
			perSt.setString(2, keyWD.getUSERID()) ;
			perSt.execute();

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}

	}
	
	
	public boolean getReversalRRN(Connection con, TxnKeyWords keyWD)throws Exception {
	PreparedStatement perSt = null;
	ResultSet res = null;
	try {
		String sql = "SELECT RETRIVELREFFRENCENUMBER, TRANSACTIONID FROM TRANSACTIONS WHERE FIELD3 = ? AND FIELD4 = ? AND TERMINALID =? AND TRACENUMBER = ?";
		perSt = con.prepareStatement(sql);
		perSt.setString(1, keyWD.getDATE());
		perSt.setString(2, keyWD.getTIME());
		perSt.setString(3, keyWD.getTID());
		perSt.setString(4, keyWD.getTRACENUMBER());
		res = perSt.executeQuery();
	
		if (res.next()) {
				System.out.println("rrn found") ;
				keyWD.setREVERSAL_RRN(res.getString("RETRIVELREFFRENCENUMBER"));
				keyWD.setREVERSAL_SID(res.getString("TRANSACTIONID"));
			return true;
		}
	
	} catch (Exception e) {
		throw e;
	} finally {
		if (perSt != null) {
			perSt.close();
		}
		if (res != null) {
			res.close();
		}
	}
	return false;
	}
	
	
	public boolean getUtilityStatusRRN(Connection con, TxnKeyWords keyWD)throws Exception {
	PreparedStatement perSt = null;
	ResultSet res = null;
	try {
		String sql = "SELECT RETRIVELREFFRENCENUMBER, TRANSACTIONID,STATUS FROM TRANSACTIONS WHERE FIELD3 = ? AND FIELD4 = ? AND TERMINALID =? AND TRACENUMBER = ?";
		perSt = con.prepareStatement(sql);
		perSt.setString(1, keyWD.getDATE());
		perSt.setString(2, keyWD.getTIME());
		perSt.setString(3, keyWD.getTID());
		perSt.setString(4, keyWD.getTRACENUMBER());
		res = perSt.executeQuery();
	
		if (res.next()) {
				System.out.println("utility status rrn found") ;
				keyWD.setREVERSAL_RRN(res.getString("RETRIVELREFFRENCENUMBER"));
				keyWD.setREVERSAL_SID(res.getString("TRANSACTIONID"));
				keyWD.setUTILITY_CHECK_STATUS(res.getString("STATUS"));
			return true;
		}
	
	} catch (Exception e) {
		throw e;
	} finally {
		if (perSt != null) {
			perSt.close();
		}
		if (res != null) {
			res.close();
		}
	}
	return false;
	}
	public boolean getVoidSID(Connection con, TxnKeyWords keyWD)throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "SELECT TRANSACTIONID FROM TRANSACTIONS WHERE RETRIVELREFFRENCENUMBER = ?";
			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getREVERSAL_RRN());
			res = perSt.executeQuery();
		
			if (res.next()) {
					System.out.println("rrn found") ;
					keyWD.setREVERSAL_SID(res.getString("TRANSACTIONID"));
				return true;
			}
		
		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}
	public void insertTransaction(Connection con, TxnKeyWords keyWD,
			String status) throws Exception {
		PreparedStatement ps = null;
		try {
			String sqlstr = " INSERT INTO transactions ( "
					+ " TRANSACTIONID, ACCOUNTNUMBER, TRANSACTIONTYPECODE, "
					+ " AMOUNT, TOACCOUNT, TERMINALID, "
					+ " MERCHANTID, BRANCHPRIFIX, UTILITYTYPE, "
					+ " APPROVECODE, RETRIVELREFFRENCENUMBER, TRACENUMBER, "
					+ " BATCHNUMBER, RESPONCECODE, REMARK,  "
					+ " STATUS, CARDNUMBER, FIELD2, "
					+ " FIELD3, FIELD4, FIELD5,  "
					+ " FIELD6, FIELD7, FIELD8,  "
					+ " FIELD9, FIELD10, FIELD11,  "
					+ " FIELD12, FIELD13, FIELD14,  "
					+ " FIELD15, FIELD16, FIELD17,  "
					+ " FIELD18, FIELD19, FIELD20,  "
					+ " FIELD21, FIELD22, FIELD23,  "
					+ " FIELD24, FIELD25, LASTUPDATEDUSER , COMPANY,REFNO1,REFNO2 )  "
					+

					" VALUES (  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
					" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
					" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
					" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
					" ?, ?, ?, ? ,?) ";

			double amount = 0;
			if (keyWD.getTXNAMOUNT() != null) {
				amount = TxnHelpMethods.getAmountForWB(keyWD.getTXNAMOUNT());
			}
			System.out.println("uid->" + keyWD.getUSERID());
			ps = (PreparedStatement) con.prepareStatement(sqlstr);
			ps.setString(1, keyWD.getSID());
			ps.setString(2, keyWD.getACCOUNTNUMBER());
			ps.setString(3, keyWD.getTXNTYPE());
			ps.setDouble(4, amount);
			ps.setString(5, keyWD.getACCOUNTNUMBER());
			ps.setString(6, keyWD.getTID());
			ps.setString(7, keyWD.getMID()); 
			ps.setString(8, keyWD.getBRANCH());
			ps.setString(9, null);
			ps.setString(10, keyWD.getAUTHCODE());
			ps.setString(11, keyWD.getRRN());
			ps.setString(12, keyWD.getTRACENUMBER());
			ps.setString(13, null);
			ps.setString(14, keyWD.getRESPONSECODE());
			ps.setString(15, null);
			ps.setString(16, status);
			ps.setString(17, keyWD.getCORDNUMBER());
			ps.setString(18, keyWD.getINVOICENUMBER()); //2
			ps.setString(19, keyWD.getDATE());//3
			ps.setString(20, keyWD.getTIME());//4
			ps.setString(21, null);//5
			ps.setString(22, StatusCode.ACCTIVE_BATCH);//6
			ps.setString(23, keyWD.getTXNCOMPANY());//7
			ps.setString(24, keyWD.getUSERID());//8
			ps.setString(25, null);//9
			ps.setString(26, null);//10
			ps.setString(27, null);//11
			ps.setString(28, null);//12
			ps.setString(29, null);//13
			ps.setString(30, null);//14
			ps.setString(31, null);//15
			ps.setString(32, null);//16
			ps.setString(33, null);//17
			ps.setString(34, null);//18
			ps.setString(35, null);//19
			ps.setString(36, null);//20
			ps.setString(37, null);//21
			ps.setString(38, null);//22
			ps.setString(39, null);//23
			ps.setString(40, null);//24
			if( keyWD.getCHQ_NO() != null && ! keyWD.getCHQ_NO().equals("")){
				ps.setString(41, keyWD.getCHQ_NO());
			}else{
				ps.setString(41, null);
			}
			ps.setString(42, null);
			ps.setString(43, keyWD.getCOMPANY());
			ps.setString(44, keyWD.getREFNO1()); 
			ps.setString(45, keyWD.getREFNO2()); 
			ps.executeUpdate();

		} catch (Exception ex) {
			System.out.println("TXNID : " + keyWD.getSID() + ", TID : " + keyWD.getTID()) ;
			LogFileCreator.writInforTologs("TXNID : " + keyWD.getSID() + ", TID : " + keyWD.getTID(), keyWD) ;
			throw ex ;
		} finally {
			if (ps != null)
				ps.close();
		}
	}	
	
	public void updateTransaction(Connection con, TxnKeyWords keyWD,
			String status) throws Exception {
		PreparedStatement ps = null;
		try {
			String sqlstr = " UPDATE TRANSACTIONS "
					+ " SET "
					+ " APPROVECODE  = ?, "
					+ " STATUS = ?,RESPONCECODE=?,FIELD5 =?,FIELD8=?,FIELD9=?,REMARK=? "
					+ " WHERE  TRANSACTIONID = ? ";

			ps = (PreparedStatement) con.prepareStatement(sqlstr);
			ps.setString(1, keyWD.getAUTHCODE());
			//ps.setString(2, keyWD.getRRN());

			if (ResponseCode.SUCCESS.equals(keyWD.getRESPONSECODE())) {
				ps.setString(2, status);
			} else {
				ps.setString(2, StatusCode.TXN_FAIL);
			}

			ps.setString(3, keyWD.getRESPONSECODE());
			ps.setString(4, keyWD.getTRANSACTON_NO());
			ps.setString(5, keyWD.getUSERID());
			ps.setString(6, null);
			ps.setString(7, null);
			ps.setString(8, keyWD.getSID());

			ps.executeUpdate();

		} catch (Exception ex) {
			throw ex;
		} finally {
			if (ps != null)
				ps.close();
		}
	}
	
	public void updateTransactionAccountNo(Connection con, TxnKeyWords keyWD) throws Exception {
		PreparedStatement ps = null;
		try {
			String sqlstr = " UPDATE TRANSACTIONS SET ACCOUNTNUMBER = ? WHERE TRANSACTIONID = ?";

			ps = (PreparedStatement) con.prepareStatement(sqlstr);
			ps.setString( 1, keyWD.getACCOUNTNUMBER());
			ps.setString( 2, keyWD.getSID());
			ps.executeUpdate();

		} catch (Exception ex) {
			throw ex;
		} finally {
			if (ps != null)
				ps.close();
		}
	}
	public void updateToVoid(Connection con, TxnKeyWords keyWD, String status) throws Exception {
		PreparedStatement ps = null;
		try {
			String sqlstr = " UPDATE TRANSACTIONS SET STATUS = ? WHERE  RETRIVELREFFRENCENUMBER = ?";

			ps = (PreparedStatement) con.prepareStatement(sqlstr);
			ps.setString( 1, status);
			ps.setString( 2, keyWD.getREVERSAL_RRN());
			ps.executeUpdate();

		} catch (Exception ex) {
			throw ex;
		} finally {
			if (ps != null)
				ps.close();
		}
	}


	
	public void insertTransactionHistory(Connection con, TxnKeyWords keyWD,
			String status) throws Exception {
		PreparedStatement ps = null;
		try {
			String sqlstr = " INSERT INTO transactionhistory ( "
					+ " TRANSACTIONID, TERMINALID,  "
					+ " BRANCHPRIFIX, ACCOUNTNUMBER, TRANSACTIONTYPECODE,  "
					+ " AMOUNT, MERCHANT, TOACCOUNT,  "
					+ " UTILITYTYPECODE, RETRIVELREFERANCENUMBER, STATUS,  "
					+ " REMARKS, FIELD1, FIELD2,  "
					+ " FIELD3, FIELD4, FIELD5,  "
					+ " LASTUPDATEDUSER)  "
					+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			String amount = null;
			if (keyWD.getTXNAMOUNT() != null) {
				amount = TxnHelpMethods.getAmountForWB(keyWD.getTXNAMOUNT())
						+ "";
			}

			ps = (PreparedStatement) con.prepareStatement(sqlstr);

			ps.setString(1, keyWD.getSID());
			ps.setString(2, keyWD.getTID());
			ps.setString(3, null);
			ps.setString(4, keyWD.getACCOUNTNUMBER());
			ps.setString(5, keyWD.getTXNTYPE());
			ps.setString(6, amount);
			ps.setString(7, keyWD.getMID());
			ps.setString(8, keyWD.getACCOUNTNUMBER());
			ps.setString(9, null);
			ps.setString(10, keyWD.getREVERSAL_RRN());
			ps.setString(11, status);
			ps.setString(12, keyWD.getREMARKS());
			ps.setString(13, null);
			ps.setString(14, null);
			ps.setString(15, null);
			ps.setString(16, null);
			ps.setString(17, null);
			ps.setString(18, "e-switch");

			ps.executeUpdate();

		} catch (Exception ex) {
			throw ex;
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	public boolean getAccountData(Connection con, TxnKeyWords keyWD)
			throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {

			String sql = "SELECT COMPANYPRODUCT.COMPANYCODE, COMPANYPRODUCT.PRODUCTCODE , SCHEMECODE , SCHEMEDESCRIPTION AS SCHEME  FROM COMPANYPRODUCT INNER JOIN COMPANY ON COMPANYPRODUCT.COMPANYCODE = COMPANY.COMPANYCODE INNER JOIN PRODUCT ON COMPANYPRODUCT.PRODUCTCODE = PRODUCT.PRODUCTCODE WHERE COMPANY.STATUS = ? AND PRODUCT.STATUS = ? ORDER BY COMPANYCODE, PRODUCTCODE";
			perSt = con.prepareStatement(sql);
			perSt.setString(1, StatusCode.STATE_ACTIVE);
			perSt.setString(2, StatusCode.STATE_ACTIVE);
			res = perSt.executeQuery();
			
			String companyCode = "";
			String productCode = "";
			StringBuffer sbAccountData = new StringBuffer();
			
			while (res.next()) {

				if (!companyCode.equals(res.getString("COMPANYCODE")) || !productCode.equals(res.getString("PRODUCTCODE"))) {
					
					companyCode = res.getString("COMPANYCODE");
					productCode = res.getString("PRODUCTCODE");
					sbAccountData.append("#");
					if (companyCode.equals("PLC")) {
						sbAccountData.append("0");
					}else if (companyCode.equals("PLF")) {
						sbAccountData.append("1");
					}else if (companyCode.equals("PMF")) {
						sbAccountData.append("2");
					}else if (companyCode.equals("PIL")) {
						sbAccountData.append("3");
					}
					
					if (productCode.equals("SAV")) {
						sbAccountData.append("0");
					}else if (productCode.equals("FID")) {
						sbAccountData.append("1");
					}
				} 
				sbAccountData.append("|" + res.getString("SCHEMECODE") + "|"+ res.getString("SCHEME"));
			}

			keyWD.setDB_ACCOUNT_DATA(sbAccountData.substring(0));
			return true;

		} catch (Exception e) {
			throw e;
		} finally {

			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
	}
	
	
	public boolean getPerBatchLimitFromTerminalComponey(Connection con, TxnKeyWords keyWD) throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "select TXNLIMIT from TERCOMTXNLIMIT where TERMINALID=? and COMPANYCODE=?";

			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getTID());
			perSt.setString(2, keyWD.getCOMPANY());

			res = perSt.executeQuery();

			if (res.next()) {

				keyWD.setXML_PER_BATCH_LIMIT(res.getString("TXNLIMIT"));		
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}
	
	
	public boolean getPerBatchLimitFromComponey(Connection con, TxnKeyWords keyWD) throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
		try {
			String sql = "select TXNLIMIT from COMPANYTXNLIMIT where COMPANYCODE=? ";

			perSt = con.prepareStatement(sql);
			perSt.setString(1, keyWD.getCOMPANY());

			res = perSt.executeQuery();

			if (res.next()) {

				keyWD.setXML_PER_BATCH_LIMIT(res.getString("TXNLIMIT"));		
				return true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (perSt != null) {
				perSt.close();
			}
			if (res != null) {
				res.close();
			}
		}
		return false;
	}
	
}
