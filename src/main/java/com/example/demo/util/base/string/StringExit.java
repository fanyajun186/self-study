package com.example.demo.util.base.string;

public class StringExit {

	public static void main(String[] args) {
		String str="abcdes";
		System.out.println(isExit(str));
	}
	
	public static boolean isExit(String str){
		boolean isOk = false;
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char a = charArray[i];
			for (int j = i+1; j < charArray.length; j++) {
				char b = charArray[j];
				if(a==b){
					isOk=true;
					return isOk;
				}
			}
		}
		return isOk;
	}
}
