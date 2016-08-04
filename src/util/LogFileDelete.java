package com.epic.mfn.util;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Date;


import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;


public class LogFileDelete {
	private static int TOP_LOG_FILE = 20;
	
	public static void keepLast90LogFIles(String path)throws Exception{
		
		File directory = new File(path);
		
		File[] files = directory.listFiles((FileFilter) FileFileFilter.FILE);


		if (TOP_LOG_FILE < files.length){
			Arrays.sort(files, NameFileComparator.NAME_REVERSE);
			System.out.println("\nLast Modified Descending Order (NAME_REVERSE)");
			displayFiles(files);
			
			
		}
	}
	
	private static void displayFiles(File[] files) {
		int c=0;
		for (File file : files) {
			
			
			if(c<TOP_LOG_FILE){
				System.out.printf("File: %-20s Last Modified:" + new Date(file.lastModified()) + "\n", file.getName());
			}else{
				if(file.isFile()){
					
					if(file.exists()){
						System.out.printf("Deleteing file: %-20s Last Modified:" + new Date(file.lastModified()) + "\n", file.getName());
						file.delete();
					}
				}
			}
			c++;
			
		}
	}

}
