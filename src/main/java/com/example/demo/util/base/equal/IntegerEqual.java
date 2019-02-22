package com.example.demo.util.base.equal;

import java.math.BigDecimal;

public class IntegerEqual {
	
	private static int a=0;
	private static Integer b;
	private static Integer c;
	//全局变量分配了空间
	//数字类型的封装类比的就是数据大小
	public static void main(String[] args) {
		//方法栈需要手动分配
		/*Integer b=null;
		Integer c=null;*/
		
		//b=c;
    	System.out.println(b==c);
    	b=a;    	
    	System.out.println(b==a);
    	
    	if(new BigDecimal("99").compareTo(new BigDecimal("100"))>0){
    		System.out.println("车辆贷款金额不能大于批复车辆贷款金额");
        }else {
        	System.out.println("贷款金额");
        }
	}

}
