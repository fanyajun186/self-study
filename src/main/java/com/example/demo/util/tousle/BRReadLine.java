package com.example.demo.util.tousle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRReadLine {

	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str;
		System.out.println("Enter lines of text.");
		System.out.println("Enter 'end' of quit.");
		//读取字符
		do{
			str=br.readLine();
			System.out.print(str);
		}while(!"end".equals(str));
	

	}

}
