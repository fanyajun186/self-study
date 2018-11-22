package com.example.demo.util.test;

import com.alibaba.druid.util.StringUtils;

public class TestMemory {

	public static void main(String[] args) {
		
		//System.out.println(getCpuUsage());
		System.out.println(System.getProperty("conf.dir"));
	}	
	
	public static long getMountPointTotal() {
		long total = 0;
		
		
		String[] allTotal = {"Filesystem","1K-blocks","Used","Available", "Use%","Mounted on",
		            		 "/dev/sda3 ", "37050048", "28619312",  "6548676",  "82%", "//",
		            		 "tmpfs","4030892","72","4030820",  "1%", "/dev/shm",
		            		 "/dev/sda1","198337","34065","154032","19%","/boot"};
		
		if(allTotal == null || allTotal.length == 0){
			return total;
		}
		
		/**
			文件系统                 1G-块      已用      可用 已用% 挂载点
			/dev/sdc1                   4G        2G        3G  41% /
			tmpfs                       4G        0G        4G   0% /dev/shm
			/dev/mapper/vg-data      1775G      364G     1322G  22% /data
			/dev/mapper/vg-reserve     50G        1G       47G   1% /reserve
		 * 
		 */
		String lineStr = null;
		for(int i=0; i< allTotal.length; i++){
			lineStr = allTotal[i];
			if(!StringUtils.isEmpty(lineStr) && lineStr.lastIndexOf("boot") > 0){
				lineStr = StringUtil.clearLinuxReturnStr(lineStr);
				if(lineStr.lastIndexOf("/boot") + "/boot".length() == lineStr.length()){
					String[] strs = StringUtil.getLinuxReturnStr(allTotal[i]);
					return Long.parseLong(strs[1].replace("K", ""));
				}
			}
		}
		
		return 0;
		
	}

	
	public static float getCpuUsage() {
		try {
			//String[] rst = LocalShellExcutor.executeReturnArray("vmstat 1 2");
			//logger.info("获取cpu字符串"+rst.hashCode());
			
			String[] rst ={ "procs","-----------memory----------", "---swap--", "-----io----","--system--","-----cpu-----",
							"r","b","swpd","free","buff","cache","si","so","bi","bo","in","cs", "us", "sy", "id", "wa", "st",
							"0", "0", "4372","1508704","204088","3425420","0","1","220","1109","452","1365","5","2","92","1","0",
							"0","0","4372","1508672","204088","3425420","0","0","0","0","363","1101","0","0","100","0","0"};
			
			
			
			String x = StringUtil.getXStr(rst[3], 14);
			return (100 - Integer.parseInt(x))*1.00f; 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}
	
}
