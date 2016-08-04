package com.epic.mfn.logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.util.TxnKeyWords;







public class LogFileCreator {

	
	public synchronized static  void writInforTologs(String msg,TxnKeyWords keyWD) {

		
		String line = "\nINFOR:\n";
		String filename = getLogFileName() + "_EpicPLC";
		BufferedWriter bw = null;

		filename = InitConfigValue.LOGFILEPATH+"infors/" + filename
				+ "_infor_" + InitConfigValue.LOGFILENAME.toLowerCase()
				+ ".txt";

		if(keyWD !=null){
			msg = line +getTime() + " SID [ "+keyWD.getSID()+" ]    " + msg;
		}else{
			msg = line +getTime() + "    " + msg;
		}

		try {
			bw = new BufferedWriter(new FileWriter(filename, true));
			bw.write(msg);
			bw.newLine();
			bw.flush();
		} catch (Exception ioe) {
			System.out
					.println("Error when writing to infor log file......\n"+ioe);
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {

				}
		}
		

	} 
	
	public synchronized static  void writErrorTologs(Throwable aThrowable) {

		String line = "\nERROR:\n";
		String filename = getLogFileName() + "_EpicPLC";
		BufferedWriter bw = null;
		String msg = "";


		
		filename = InitConfigValue.LOGFILEPATH+"errors/" + filename
				+ "_error_" + InitConfigValue.LOGFILENAME.toLowerCase()
				+ ".txt";

		
		msg = line + getTime() + ":    " + getStackTrace(aThrowable);

		try {
			bw = new BufferedWriter(new FileWriter(filename, true));
			bw.write(msg);
			bw.newLine();
			bw.flush();
		} catch (Exception ioe) {
			
			System.out
			.println("Error when writing to error log file......\n"+ioe);
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {

				}
		}

	} 

	public static String getLogFileName() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
		
	}

	
	private static String getTime(){
		 return new java.sql.Timestamp(new java.util.Date().getTime()).toString();
		
	}
	
	private  static String getStackTrace(Throwable aThrowable) {
		String re = "";
		Writer result = null;
		PrintWriter printWriter = null;
		try {
			result = new StringWriter();
			printWriter = new PrintWriter(result);

			aThrowable.printStackTrace(printWriter);
			re = result.toString();
			result.close();
			printWriter.close();

		} catch (Exception e) {

			System.out
					.println("Error when getting stackTrace ....\n"+e);
			
		} finally {

			try {
				if (result != null)
					result.close();
				if (printWriter != null)
					printWriter.close();
			} catch (IOException e) {

			}

		}
		return re;

	}
	
	

}

