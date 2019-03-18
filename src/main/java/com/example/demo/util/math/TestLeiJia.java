package com.example.demo.util.math;

import java.math.BigDecimal;

public class TestLeiJia {

	public static void main(String[] args) {
		BigDecimal  result= new BigDecimal(1);     //定义一个初始变量            
		for(int i=1;i<=1200;i++){
			result=result.multiply(new BigDecimal(i)); //进行累加运算			 
		} 
		System.out.println("输出1-10的累乘为：" + result);
	}

}
