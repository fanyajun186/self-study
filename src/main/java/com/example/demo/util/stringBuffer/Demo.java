package com.example.demo.util.stringBuffer;

import java.util.HashSet;
import java.util.Set;

public class Demo {

	public static void main(String[] args) {
		/*StringBuffer a=new StringBuffer("a");
		StringBuffer b=new StringBuffer("b");
		operate(a,b);
		//a.append(b);
		//b=a;
		System.out.println(a+","+b);*/
		
		String str1=new String("123");
		String str2=new String("123");
		Set<String> strSet=new HashSet<>();
		strSet.add(str1);
		System.out.println(str1==str2);
		System.out.println(strSet.contains(str2));
		System.out.println("---------------------------");
		int a=3;
		int b=3;
		System.out.println(a==b);
		String str3=new String("hello");
		String str4=new String("hello");
		System.out.println(str3.equals(str4));
		System.out.println(str3==str4);
		
	}

	private static void operate(StringBuffer a, StringBuffer b) {
		a.append(b);
		b=a;
	}
	
	
}
