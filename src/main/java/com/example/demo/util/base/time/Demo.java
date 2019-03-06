package com.example.demo.util.base.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Demo {

	public static String dayForWeek(String pTime) throws Throwable {  
		
		try {
			int i=8;
			int j=0;
			System.out.println(i/j);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("除零了");
		}
		
		
		String msg=null;
    	System.out.println(msg.length());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  

        Date tmpDate = format.parse(pTime);  

        Calendar cal = Calendar.getInstance(); 

        String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };

        try {

            cal.setTime(tmpDate);

        } catch (Exception e) {

            e.printStackTrace();

        }

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。

        if (w < 0)

            w = 0;

        return weekDays[w];

    }  

    public static void main(String[] args) throws Throwable {          
        
        try {
        	String a = dayForWeek("2019-02-28");
            System.out.println(a);     
		} catch (Exception exception) {
			StringBuffer sb = new StringBuffer(128);
	        
	        sb.append("\n异常类型：").append("系统业务判断异常");
	        sb.append("\n异常原因：").append(exception.getMessage()!=null?exception.getMessage():exception.getClass().getName());	        
	        
	        StackTraceElement[] stackTraceElements = exception.getStackTrace();
	        if(stackTraceElements!=null && stackTraceElements.length>0) {
	        	StackTraceElement stackTraceElement = stackTraceElements[0];  
	            
	            sb.append(" \n类方法名：").append(stackTraceElement.getClassName());
	            sb.append(".");
	            sb.append(stackTraceElement.getMethodName());        
	            sb.append("\n代码行数：").append(stackTraceElement.getLineNumber());  
	            sb.append("\r\n");
	            
	            System.out.println("异常信息："+sb.toString());
	            
	            System.out.println("stackTraceElement里面有啥:");
	            System.out.println("className:"+stackTraceElement.getClassName());
	            System.out.println("fileName:"+stackTraceElement.getFileName());
	            System.out.println("methodName:"+stackTraceElement.getMethodName());
	            System.out.println("lineNumber:"+stackTraceElement.getLineNumber());	            
	            
	        } 
	        
	        System.out.println("exception里面有啥:");
	        System.out.println("message里面有啥:"+exception.getMessage());
	        System.out.println("LocalizedMessage里面有啥:"+exception.getLocalizedMessage());
	        System.out.println("casue里面有啥:"+exception.getCause());
	        System.out.println("类型里面有啥:"+exception.getClass().getName());
		}
        
    }
	
}
