package com.epic.mfn.util;

import org.jpos.iso.ISOUtil;


public class SecurityProcessing {
	
	public static byte [] POS_ENC_KEY ;

	public static void decriptRequestData(TxnKeyWords keyWD)throws Exception{

		try{
			byte [] clear = TripleDes.getDEPPK( POS_ENC_KEY , ISOUtil.hex2byte(keyWD.getENCDATA()));
			String hexData = ISOUtil.hexString(clear);
			
			String clearData = TxnHelpMethods.convertHexToString(hexData);
			System.out.println("cccc:"+clearData);
			int datalength = clearData.trim().length();
			int beginIndex  = 0;
			int elimentLenghth  = 2;
			if( datalength >= elimentLenghth ){
				elimentLenghth = Integer.parseInt(clearData.substring( beginIndex , ( beginIndex =+ elimentLenghth )));
				if( datalength  >=  beginIndex + elimentLenghth ){
					keyWD.setUSERID(clearData.substring( beginIndex , ( beginIndex += elimentLenghth )));
					elimentLenghth  = 2;
					if( datalength >= ( beginIndex + elimentLenghth ) ){
						elimentLenghth = Integer.parseInt(clearData.substring( beginIndex , ( beginIndex += elimentLenghth ) ));
						if(datalength >= ( beginIndex + elimentLenghth )){
							keyWD.setPASSWORD(MD5Security.MD5(clearData.substring( beginIndex ,( beginIndex += elimentLenghth ))));
							elimentLenghth  = 2;
							if( datalength >= ( beginIndex + elimentLenghth ) ){
								elimentLenghth = Integer.parseInt(clearData.substring( beginIndex , ( beginIndex += elimentLenghth ) ));
								if(datalength >= ( beginIndex + elimentLenghth )){
									keyWD.setTXNDATA(clearData.substring( beginIndex ,( beginIndex += elimentLenghth )));
									elimentLenghth  = 12;
									if( datalength  >= ( beginIndex + elimentLenghth ) ){
										keyWD.setTXNAMOUNT( clearData.substring( beginIndex , ( beginIndex += elimentLenghth )) );
									}
								}
							}
						}
					}
				}
			}
			
			if(null != keyWD.getTXNDATA()){
				TxnHelpMethods.extarctAccountNumber(keyWD.getTXNDATA(), keyWD);
			}
			
		}catch (Exception e) {
			throw e;
		}
	}
	
	
	public static boolean encriptResponseData(String data , TxnKeyWords keyWD )throws Exception {
		
		POS_ENC_KEY = TxnHelpMethods.getBytes("4444444444444444555555555555555544444444444444445555555555555555", 32);
		
		String clearData = TxnHelpMethods.convertStringToHex(data);
		String clearDataLength = Integer.toHexString( clearData.length());
		clearData =  ISOUtil.zeropad( clearDataLength , 4 ) + clearData;

		byte [] pre_data = TxnHelpMethods.getBytes(clearData, clearData.length() / 2);
		byte [] padding = new byte [(8 - pre_data.length % 8)];
		byte [] zeroPadData = ISOUtil.concat( pre_data, padding);
		byte [] ecc  = TripleDes.getENPPK(POS_ENC_KEY, zeroPadData);
		keyWD.setENCDATA( ISOUtil.hexString(ecc));
		return true;
	}

	public static String decriptRequestData(String encripydata_utility)throws Exception{
		String clearData = null;
		try{
			byte [] clear = TripleDes.getDEPPK( POS_ENC_KEY , ISOUtil.hex2byte(encripydata_utility));
			String hexData= ISOUtil.hexString(clear);
			clearData = TxnHelpMethods.convertHexToString(hexData);
		}catch (Exception e) {
			throw e;
		}
		return clearData;
	}


}

