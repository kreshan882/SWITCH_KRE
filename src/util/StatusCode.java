package com.epic.mfn.util;

public class StatusCode {
	
	/*
	 * Transaction Initiated By Terminal
	 */
	public final static String TXN_INI 			= "TXIN";	//	
	
    public final static String TXN_TRFT		 	= "TRSW";	//	Transaction Request From Terminal
    public final static String TXN_TRTH		 	= "TRSM"; 	//  Transaction Request to Host
    public final static String TXN_TRFH		 	= "TRRS";	//	Transaction Request from Host
    public final static String TXN_TRTT		 	= "TRST";	//  Transaction Request to Terminal

    public final static String TXN_FAIL		 	= "TRFI";	//	Transaction Failed
    
    public final static String TXN_RIN			= "TRIN";	//	Transaction Reverse Initiated
    public final static String TXN_RS_FAIL		= "TXRF";	//	Transaction Reversed Failed
    public final static String TXN_RS		 	= "TXRS";	//	Transaction Reversed
    public final static String TXN_VD			= "VOID";	// Transaction void 
    
    public final static String STATE_ACTIVE		= "ACT";	// 	Status Active
    public final static String TXN_DEA		 	= "DEA";	// 	Status Deceive
    
    public final static String ACCTIVE_BATCH	= "A_BATCH";	// 	Active Batch
    public final static String SETTLED_BATCH	= "S_BATCH";	// 	Settled Batch
    
    

	
  

}
