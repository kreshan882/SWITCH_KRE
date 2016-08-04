package com.epic.mfn.init;

import java.io.DataOutputStream;

import com.epic.mfn.util.TxnKeyWords;

public class ProcessingRequest {
	private byte [] REQUESTPACKET 			= null;
	private int REQUESTLENGTH 					  ;
	private DataOutputStream OUTSTREAM		= null;
	private byte [] REQUESTTPDU				= null;
	private TxnKeyWords keyWD				= null;
	
	public TxnKeyWords getKeyWD() {
		return keyWD;
	}
	public void setKeyWD(TxnKeyWords keyWD) {
		this.keyWD = keyWD;
	}
	public byte[] getREQUESTPACKET() {
		return REQUESTPACKET;
	}
	public void setREQUESTPACKET(byte[] requestpacket) {
		REQUESTPACKET = requestpacket;
	}
	public int getREQUESTLENGTH() {
		return REQUESTLENGTH;
	}
	public void setREQUESTLENGTH(int requestlength) {
		REQUESTLENGTH = requestlength;
	}
	public DataOutputStream getOUTSTREAM() {
		return OUTSTREAM;
	}
	public void setOUTSTREAM(DataOutputStream outstream) {
		OUTSTREAM = outstream;
	}
	public byte[] getREQUESTTPDU() {
		return REQUESTTPDU;
	}
	public void setREQUESTTPDU(byte[] requesttpdu) {
		REQUESTTPDU = requesttpdu;
	}
}
