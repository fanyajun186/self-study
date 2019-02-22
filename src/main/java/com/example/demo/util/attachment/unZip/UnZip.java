package com.example.demo.util.attachment.unZip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.ice.tar.TarEntry;
import com.ice.tar.TarInputStream;

public class UnZip {

	public static void main(String[] args) {
	    extTarFileList("E:/u.tar", "D:/temp/");
	}
	
	 private static boolean extTarFileList(String filename, String directory) {  
	        boolean flag = false;  
	        OutputStream out = null;  
	        try {  
	            TarInputStream in = new TarInputStream(new FileInputStream(new File(filename)));  
	            TarEntry entry = null;  
	            while ((entry = in.getNextEntry()) != null) {  
	                if (entry.isDirectory()) {  
	                    continue;  
	                }  
	                System.out.println(entry.getName());  
	                File outfile = new File(directory + entry.getName());  
	                new File(outfile.getParent()).mkdirs();  
	                out = new BufferedOutputStream(new FileOutputStream(outfile));  
	                int x = 0;  
	                while ((x = in.read()) != -1) {  
	                    out.write(x);  
	                }  
	                out.close();  
	            }  
	            in.close();  
	            flag = true;  
	        } catch (IOException ioe) {
	            ioe.printStackTrace();  
	            flag = false; 
	        }  
	        return flag;  
	    }  
}
