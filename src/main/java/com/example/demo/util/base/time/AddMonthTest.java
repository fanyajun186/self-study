package com.example.demo.util.base.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMonthTest {

	public static void main(String[] args) {
		Date d=new Date();
		Date d1=addMonth(d,0);
		Date d2=addMonth(d,15);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(d1));
		System.out.println(sdf.format(d2));
	}

	private static Date addMonth(Date loanTime, Integer intervalStartMonth) {
		if(loanTime!=null && intervalStartMonth!=null) {
			Calendar c = Calendar.getInstance();//定义日期实例
	    	c.setTime(loanTime);
	    	c.add(Calendar.MONTH, intervalStartMonth);
			return c.getTime();
		}else {
			return null;
		}    	
	}
}
