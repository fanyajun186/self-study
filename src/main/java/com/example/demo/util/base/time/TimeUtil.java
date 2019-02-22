package com.example.demo.util.base.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public  class TimeUtil {

	public static String AccountTime(long kssj,long jssj){
		if(StringUtils.isNotEmpty(String.valueOf(kssj)) && StringUtils.isNotEmpty(String.valueOf(jssj))){
			long interval=jssj-kssj;
			if(interval>0){
				long day=interval/(24*60*60*1000);
				String  str="";
				//攻击时间大于天
				if(day>0){
					interval=interval-day*24*60*60*1000;
					Date date=new Date(interval);			
					SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
					str=sdf.format(date);
					//String[] strs=str.split(":");				
					str=day+"天 ";
				}
				String time=AccountHour(interval);
				str=str+" "+time;
				
				return str;	
			}else{
				return "开始时间大于结束时间";
			}			
		}else{
			return "开始时间或结束时间不能为空";
		}
	}
	
	public static String AccountHour(long interval){
		String str="";
		long hour=interval/(60*60*1000);
		//存在小时
		if(hour>0){
			str=hour+"时";
			interval=interval-hour*60*60*1000;
		}
		
		long min=(interval)/(60*1000);
		//存在分钟
		if(min>0){
			str=str+min+"分";
			interval=interval-min*60*1000;
		}
		
		long m=interval/1000;
		//存在秒
		if(m>0){
			str=str+m+"秒";
		}
		return str;
	}
	
	//毫秒转时间字符串
	public static String msToTime(long ms){
		Date date=new Date(ms);			
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=sdf.format(date);
		return str;
	}
	
	//时间字符串转毫秒
	public static long timeToMs(String str) throws ParseException{	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf.parse(str);		
		return date.getTime();
	}
	
	//获取当前时间
	public static long getCurrentTime(){
		Date date=new Date();
		return date.getTime();
	}
	
	//获取当天开始时间
	public static long getStartTime(){
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0); 
	    return c.getTime().getTime();
		
	}
	//获取当天结束时间
	public static long getEndTime(){	   
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.set(Calendar.HOUR_OF_DAY, 23);
	    c.set(Calendar.MINUTE, 59);
	    c.set(Calendar.SECOND, 59);
	    c.set(Calendar.MILLISECOND, 999); 
	    return c.getTime().getTime();
		
	}
	
	//获取当前时间
	public static long getCurrentTime(Date date){
		//Date date=new Date();
		return date.getTime();
	}
	
	//获取当天开始时间
	public static long getStartTime(Date date){
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0); 
	    return c.getTime().getTime();
		
	}
	//获取当天结束时间
	public static long getEndTime(Date date){	   
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.set(Calendar.HOUR_OF_DAY, 23);
	    c.set(Calendar.MINUTE, 59);
	    c.set(Calendar.SECOND, 59);
	    c.set(Calendar.MILLISECOND, 999); 
	    return c.getTime().getTime();
		
	}
	
	/**
	 * 提供毫秒 获得yyyyMMdd
	 * @param time
	 * @return
	 * @data 2013-2-28 下午5:38:01
	 */
	public static String longToStringGetYMD(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(time);
	}
}
