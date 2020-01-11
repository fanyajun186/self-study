package com.example.demo.util.str;

/**
 * 自写方法实现字符串转数字
 * @author: yajun.fan
 * @date: 2019年12月23日
 */
public class strToInt {

	public static void main(String[] args) {		
		int a = toInt("12345678");
	}
	
	private static int toInt(String str) {
		char[] arr = str.toCharArray();
		int result=0;
		int j=0;
		for (int i = arr.length-1; i >=0 ; i--) {
			char c = arr[i];
			int intNum = Integer.parseInt(String.valueOf(c));
            double pow = Math.pow(10,j);
            intNum*=pow;
            j++;
            result+=intNum;            
		}
		System.out.println(result);
		return result;
	}

}
