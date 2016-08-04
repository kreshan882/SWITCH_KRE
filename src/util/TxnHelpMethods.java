package com.epic.mfn.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.jpos.iso.ISODate;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.logs.LogFileCreator;

public class TxnHelpMethods {
	
	public static String getCurrentTime()throws Exception{
		return ISODate.getTime(new Date());
	}
	public static String getCurrentDate()throws Exception{
		return ISODate.getDate(new Date());
	}
	public static String getCurrentYear()throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(new Date());
	}
	
	public static String getCurrentDateAndTime()throws Exception{
	
		Date d = new Date();
		String result = ISODate.getDate(d)+ISODate.getTime(d);
		return result;
	}
	public static String getCurrentDateAndTimeWithYear()throws Exception{
		
		Date d = new Date();
		String result = new GregorianCalendar().get(Calendar.YEAR)+ISODate.getDate(d)+ISODate.getTime(d);
		return result;
	}
	
	public static String getCurrentDateAndTimeWithoutYear()throws Exception{
		
		Date d = new Date();
		String result = ISODate.getDateTime(d);
		return result;
	}
	
	public static String getRRN()throws Exception{
		Random ran = new  Random();
		String ranv = ran.nextInt(100)+"";
		return getCurrentDateAndTime() + ISOUtil.zeropad(ranv, 2);
	}
	public static String getAuthCode()throws Exception{
		return getCurrentTime();
	}
	public static String getResponseMTI(String mti)throws Exception{
		int m = Integer.parseInt(mti) + 10;
		return "0" + m;
		
	}
	/*public static String getSid()throws Exception{
		return getCurrentDateAndTime();
	}*/
	
	public static String getProcessingTime(TxnKeyWords keyWD)throws Exception{
		double time = (keyWD.getLEAVETIME()-keyWD.getHITTIME())/1000000;
		keyWD.setPROCESSINGTIME(time+"");
		return keyWD.getPROCESSINGTIME();
	}
	public static byte[] getResponseTPDU(byte retpdu[],TxnKeyWords keyWD)throws Exception{
		byte RAN_VAL_1 = retpdu[3] ;   
		byte RAN_VAL_2 = retpdu[4] ; 
		
		byte tpdu[] = new byte[5];
		
		tpdu[0]=(byte)Integer.parseInt(60+"",16);
		tpdu[1]=(byte)RAN_VAL_1;
		tpdu[2]=(byte)RAN_VAL_2;
		tpdu[3]=(byte)Integer.parseInt(keyWD.getNII().substring(0,1),16);
		tpdu[4]=(byte)Integer.parseInt(keyWD.getNII().substring(1,3),16);
		return tpdu;
		
	}
	
	public static byte[] getHexLength(byte tpdu[],byte[] response)throws Exception{
		
		int requestLen = response.length+5;
		String len = Integer.toHexString(requestLen);
		len = ISOUtil.zeropad(len, 4);
		
		byte y[] = new byte[2];
		y[0]=(byte)Integer.parseInt(len.substring(0,2),16);
		y[1]=(byte)Integer.parseInt(len.substring(2,4),16);
		
		
		return y;
		
	}
	
	public synchronized static String getSid() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Thread.sleep(1);
		String Sid = sdf.format(date) + Long.toString(System.nanoTime() ).substring(8) ;
		if(Sid.length() > 20){
			Sid = Sid.substring(0, 20) ;
		}
		return Sid;

	}
	public static byte[] getHexLength(byte[] response)throws Exception{
		
		int requestLen = response.length;
		String len = Integer.toHexString(requestLen);
		len = ISOUtil.zeropad(len, 4);
		
		byte y[] = new byte[2];
		y[0]=(byte)Integer.parseInt(len.substring(0,2),16);
		y[1]=(byte)Integer.parseInt(len.substring(2,4),16);
		return y;
		
	}
	
	public static byte[] getResponseForNac (byte[] len,byte[] tpdu,byte[] response)throws Exception{
		return ISOUtil.concat(ISOUtil.concat(len, tpdu),response);
	}
	
	
	
	public static void printPacket(ISOMsg m,TxnKeyWords keyWD,String msgt)throws Exception{
		
		StringBuffer msg = new StringBuffer();
		msg.append("\n---------------------------------------------------\n");
		msg.append(msgt+"\n");
		msg.append("\n---------------------------------------------------\n");
		for (int i =0;i<128;i++){
			if(m.hasField(i)){
				
				msg.append("Element ["+i+"]  "+ m.getValue(i).toString()+"\n");
			}
			
		}
		
		if(InitConfigValue.CONSOLELEVEL >=2){
			System.out.println(msg.toString());
		}
		if(InitConfigValue.LOGLEVEL >=2){
			LogFileCreator.writInforTologs(msg.toString(),keyWD);
		}
		
	}
	
	public static String getCardNoFromTrack(String tarck2data)throws Exception {
		
		if(tarck2data.contains("=")){
			return tarck2data.substring(0 , tarck2data.indexOf('='));
		}
		return null;

	}
	
    public static double getAmountForWB(String amount)throws Exception{
        double am = Double.parseDouble(amount);
//    	int am = Integer.parseInt(amount);
        return am/100;
    }
    
    public static byte[] getBytes(String key, int length) throws Exception {

        byte bkey[] = new byte[length];
        int c = 0;
        for (int x = 0; x < key.length(); x++) {
            bkey[c] = (byte) Integer.parseInt(key.substring(x++, x + 1), 16);
            c++;
        }

        return bkey;

    }	
    
    public static String convertStringToHex(String str){
    	 
  	  char[] chars = str.toCharArray();
  	  StringBuffer hex = new StringBuffer();
  	  for(int i = 0; i < chars.length; i++){
  	    hex.append(Integer.toHexString((int)chars[i]));
  	  }
  	  return hex.toString();
    }
    
    public static String convertHexToString(String hex){
    	 
  	  StringBuilder sb = new StringBuilder();
  	  StringBuilder temp = new StringBuilder();
   

  	  for( int i=0; i<hex.length()-1; i+=2 ){
   
  	      //grab the hex in pairs
  	      String output = hex.substring(i, (i + 2));
  	      //convert hex to decimal
  	      int decimal = Integer.parseInt(output, 16);
  	      //convert the decimal to character
  	      sb.append((char)decimal);
   
  	      temp.append(decimal);
  	  }
  	  return sb.toString();
    }
    
    public static void extarctAccountNumber(String data ,TxnKeyWords keyWD){
    	String dataSet []  =  data.split("\\|");
    	for (int i = 0; i < dataSet.length; i++) {
			if(dataSet[i].equals("02")&& dataSet[i].length()> i+1){
				keyWD.setACCOUNTNUMBER(dataSet[i+1]);
			}
		}
    }
    
    public static void substarctAccountNumber(String data ,TxnKeyWords keyWD){
    	String dataSet []  =  data.split("\\|");
    	if(dataSet.length > 1 && null != dataSet[1]){
				keyWD.setACCOUNTNUMBER( dataSet[1]);
		}
    }
    
}
