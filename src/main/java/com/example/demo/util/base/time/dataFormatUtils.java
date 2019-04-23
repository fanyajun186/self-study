package com.example.demo.util.base.time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class dataFormatUtils {

    public static void main(String[] args) throws Exception {
    	
    	Date date = new Date();
    	Calendar c = Calendar.getInstance();//定义日期实例
    	c.setTime(date);
    	c.add(Calendar.MONTH, 15);
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	System.out.println(sdf.format(c.getTime()));
       
       /*List<String> list=new ArrayList<String>();
      
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");   
       
       Date d1 = sdf.parse("201605");//定义起始日期
       Date d2 = sdf.parse("201605");//定义结束日期
       Calendar dd = Calendar.getInstance();//定义日期实例
       dd.setTime(d1);//设置日期起始时间
       if(dd.getTime().after(d2)){
           throw new Exception("开始时间大于结束时间"); 
       }else{
           while(dd.getTime().before(d2)){//判断是否到结束日期
               String str = sdf.format(dd.getTime());              
               list.add(str);
               dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
           }
           list.add(sdf.format(d2.getTime()));
       }
       for (String timeStr : list) {
           System.out.println(timeStr);//输出日期结果    
       }*/
       
    } 
    
}
