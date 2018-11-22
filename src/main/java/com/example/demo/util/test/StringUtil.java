package com.example.demo.util.test;

public class StringUtil {
	public final static String SPLIT_STR = " ";
	
	/**
	 * 将linux返回字符串中的多个空格、制表符过滤，数值之间以一个空格分隔
	 * @param str
	 * @return
	 */
	public static String clearLinuxReturnStr(String str){
		StringBuffer newStr = new StringBuffer();
		if(str == null || "".equals(str.trim())){
			return "";
		}
		boolean beforeIsBlank = false;
		boolean hasAdd = false;
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			
			if(!beforeIsBlank){
				if(c == ' ' || c == '\t'){
					newStr.append(SPLIT_STR);
				}else{
					newStr.append(c);
					hasAdd = true;
				}
			}

			if(c == ' ' || c == '\t'){
				beforeIsBlank = true;
			}else{
				beforeIsBlank = false;
				if(!hasAdd){
					newStr.append(c);
				}
			}
			
			hasAdd = false;
		}
		
		int start = 0;
		int end = newStr.length();
		if(newStr.indexOf(SPLIT_STR) == 0){
			start = 1;
		}
		
		if(newStr.lastIndexOf(SPLIT_STR) == end - 1){
			end = end - 1;
		}
		
		return newStr.substring(start, end);
	}
	
	public static String[] getLinuxReturnStr(String str){
		str = clearLinuxReturnStr(str);
		
		return str.split(SPLIT_STR);
	}
	/**
	 * 
	 * @param str
	 * @param index
	 * @return
	 */
	public static String getXStr(String str, int index){
		String[] strs = getLinuxReturnStr(str);
		if(strs.length > index){
			return strs[index];
		}
		
		return null;
	}
}
