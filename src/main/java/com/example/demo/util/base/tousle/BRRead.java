package com.example.demo.util.base.tousle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRRead {

	public static void main(String[] args) throws IOException {
		char c;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("输入Q就停止");
		//读取字符
		do{
			c=(char)br.read();
			System.out.print(c);
		}while(c!='q');
	}

}
