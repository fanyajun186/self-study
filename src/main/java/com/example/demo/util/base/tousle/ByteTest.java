package com.example.demo.util.base.tousle;

public class ByteTest {

	public static void main(String[] args) {
		//String str="范abcdef";
		//System.out.println(SubString(str,3));
		
		String str1="压";
		System.out.println(str1.getBytes().length);
		
		char c='饭';	
		System.out.println(c);
	}

	public static String SubString(String str,int len){
		if(str.length()<len/2){
			return str;
		}
		int count=0;
		StringBuffer sb=new StringBuffer();
		String[] ss=str.split("");
		for(int i=0;i<ss.length;i++){
			count+=ss[i].getBytes().length>1?2:1;
			sb.append(ss[i]);			
			if(count>=len){
				break;
			}			
		}
		return (sb.toString().length()<str.length())?sb.toString():str;
	}
}
