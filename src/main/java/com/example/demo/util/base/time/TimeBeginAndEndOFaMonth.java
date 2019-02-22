package com.example.demo.util.base.time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.time.LocalDate;  
import java.time.LocalDateTime;  
import java.time.YearMonth;  
import java.time.ZoneId;  
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List; 

public class TimeBeginAndEndOFaMonth {

	 public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
	  
	    public static void main(String[] args) throws Exception { 
	    	int time=20170111;
	    	Date date = sdf.parse(time+"");
	    	Calendar dd = Calendar.getInstance();//定义日期实例
			dd.setTime(date);//
			int year = dd.get(Calendar.YEAR);
			int month = dd.get(Calendar.MONTH)+1;
			int day = dd.get(Calendar.DAY_OF_MONTH);			
			
			System.out.println(year);
			System.out.println(month);
			System.out.println(day);
			
			System.out.println("今年是"+getNowYear());
			System.out.println("本月是"+getNowMonth());
	        
	        Date beginTime = getBeginTime(year, month);  
	        System.out.println(sdf.format(beginTime));  
	  
	        Date endTime = getEndTime(year, month);
	        System.out.println(sdf.format(endTime));  
	        
	        List list = getTimeList(2016,6,2017,8,1);
	        for (Object object : list) {
				System.out.println(object);
			}
	    }  
	  
	    //获得指定年月开始时间,8月传8
	    public static Date getBeginTime(int year, int month) {
	        YearMonth yearMonth = YearMonth.of(year, month);  
	        LocalDate localDate = yearMonth.atDay(1);  
	        LocalDateTime startOfDay = localDate.atStartOfDay();  
	        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));	  
	        return Date.from(zonedDateTime.toInstant());  
	    }  
	  
	    //获得指定年月开始时间
	    public static Date getEndTime(int year, int month) {  
	        YearMonth yearMonth = YearMonth.of(year, month);  
	        LocalDate endOfMonth = yearMonth.atEndOfMonth();  
	        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);  
	        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));  
	        return Date.from(zonedDateTime.toInstant());  
	    }  
	    
	    //获取某个日期的开始时间
	    public static Timestamp getDayStartTime(Date d) {
	        Calendar calendar = Calendar.getInstance();
	        if(null != d) calendar.setTime(d);
	        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	        return new Timestamp(calendar.getTimeInMillis());
	    }
	    //获取某个日期的结束时间
	    public static Timestamp getDayEndTime(Date d) {
	        Calendar calendar = Calendar.getInstance();
	        if(null != d) calendar.setTime(d);
	        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
	        calendar.set(Calendar.MILLISECOND, 999);	        
	        return new Timestamp(calendar.getTimeInMillis());
	    }
	    
	    //获取今年是哪一年
        public static Integer getNowYear() {
            Date date = new Date();
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            return Integer.valueOf(gc.get(Calendar.YEAR));
        }
	     //获取本月是哪一月
	     public static int getNowMonth() {
            Date date = new Date();
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            return gc.get(Calendar.MONTH) + 1;
	     }
	    
	    //获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
	     public static List getTimeList(int beginYear, int beginMonth, int endYear,int endMonth, int k) {
	         if(beginMonth>=1){
	             beginMonth-=1;
	         }else{
	             return null;
	         }
	         if(endMonth>=1){
	             endMonth-=1;
             }else{
                 return null;
             }
	          List list = new ArrayList();
              if (beginYear == endYear){
                for(int j = beginMonth; j <= endMonth; j++){
                    list.add(getTimeList(beginYear, j, k));	    
                }
              }else{                    
                    for(int j = beginMonth; j < 12; j++){
                         list.add(getTimeList(beginYear, j, k));
                    }  
                    
                   for (int i = beginYear + 1; i < endYear; i++) {
                        for (int j = 0; j < 12; j++) {
                           list.add(getTimeList(i, j, k));
                      }
                    }
                   for (int j = 0; j <= endMonth; j++) {
                         list.add(getTimeList(endYear, j, k));
                   }
                     
               }
               return list;
	     }
	  
	   //获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
      private static List getTimeList(int beginYear, int beginMonth,int k) {
          return getTimeList(beginYear,beginMonth,null,k);
      }   
	    
      public static List getTimeList(int beginYear, int beginMonth,Integer max, int k) {
		List list = new ArrayList();
		Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
		if(max==null){
			max = begincal.getActualMaximum(Calendar.DATE);			
		}
	    for (int i = 1; i < max; i = i + k) {
	         list.add(sdf.format(begincal.getTime()));
	         begincal.add(Calendar.DATE, k);
	     }
	    begincal = new GregorianCalendar(beginYear, beginMonth, max);
	    list.add(sdf.format(begincal.getTime()));
	    return list;
	   }
	
}
