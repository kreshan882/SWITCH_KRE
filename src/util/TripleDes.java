/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.mfn.util;

import java.security.Provider;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;



public class TripleDes {

    private static String PAD = "DES/ECB/NoPadding";
    private static Provider PROVIDER = new com.sun.crypto.provider.SunJCE();
    private static String ALGORITHEM = "DES";
    
    private static Cipher DESCIPER_ENC_1 = null;
    private static Cipher DESCIPER_ENC_2 = null;
    private static Cipher DESCIPER_DEC_1 = null;
    private static Cipher DESCIPER_DEC_2 = null;
    public static boolean init;
    
    public synchronized static void init(byte [] key) throws Exception{
    	
    	if(!init){
	    	
	    	Security.addProvider(PROVIDER);
	    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHEM);
	    	
	        byte key1[] = new byte[8];
	        byte key2[] = new byte[8];
	        
	        int c = 0;
	        for (int x = 0; x < 8; x++) {
	            key1[c] = key[x];
	            c++;
	        }
	
	        c = 0;
	        for (int x = 8; x < 16; x++) {
	            key2[c] = key[x];
	            c++;
	        }
	        
	        DESCIPER_ENC_1 = Cipher.getInstance(PAD);
	        DESCIPER_DEC_1 = Cipher.getInstance(PAD);
	        DESCIPER_ENC_2 = Cipher.getInstance(PAD);
	        DESCIPER_DEC_2 = Cipher.getInstance(PAD);
	        
	        DESCIPER_ENC_1.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret( new DESKeySpec(key1)));
	        DESCIPER_DEC_1.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret( new DESKeySpec(key1)));
	        DESCIPER_ENC_2.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret( new DESKeySpec(key2)));
	        DESCIPER_DEC_2.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret( new DESKeySpec(key2)));
	        
	        init = true;
	        System.out.println("Init compleate !!");
    	}
    }
    
    
    public static byte[] getDEPPK( byte [] key , byte [] data) throws Exception {
    	
    	init(key);
    	
    	byte ciper[] = DESCIPER_DEC_1.doFinal(data);
    	ciper =  DESCIPER_ENC_2.doFinal(ciper);
    	return DESCIPER_DEC_1.doFinal(ciper);
    }
    
    public static byte[] getENPPK( byte [] key , byte [] data) throws Exception {
    	
    	if(!init){
    		init(key);
    		init = true;
    	}
    	
    	byte ciper[] = DESCIPER_ENC_1.doFinal(data);
    	ciper =  DESCIPER_DEC_2.doFinal(ciper);
    	return DESCIPER_ENC_1.doFinal(ciper);
    }
       

    
    
}
