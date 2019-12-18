package com.example.demo.util.base;

/**
 * n++先返回再加加（13），++n先加加再返回（14）
 * @author: yajun.fan
 * @date: 2019年12月8日
 */
public class increase {
	
	public static void main(String[] args) {
		System.out.println(getDivision(10));
	}

	private static int getDivision(int n) {
		try {
			n+=1;
			if(n/0>0) {
				n+=10;
			}else{
				n-=10;
			}
			return n;
		} catch (Exception e) {
			n++;
		}
		n++;
		return ++n;
		
	}
	
	

}
