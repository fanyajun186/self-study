package com.example.demo.util.base.time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CountEveryMonth {	
	
	public static void main(String[] args) throws Exception {
		/*long startTime=1483004000000L;
		Date d=new Date(startTime);
		Long dst=TimeUtil.getStartTime(d);
		System.out.println(dst);*/
		
		
		//获取告警的开始时间和结束时间
		/*Long startTime=1451934900000l;
		long endTime=1451960100000l;
		//判断开始时间和结束时间是否在同一天
		Date date1=new Date(startTime);
		Date date2=new Date(endTime);
		long dst1=TimeUtil.getStartTime(date1);
		long dst2=TimeUtil.getStartTime(date2);
		if(dst1!=dst2 && startTime<dst2){
			startTime=dst2;
		}
		System.out.println(startTime);*/
		
		
		Calendar cal = Calendar.getInstance();
		Date thisMonthFirstDay = DateUtil.getThisMonthStartDate();
		cal.setTime(thisMonthFirstDay);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date thisMonthSecondDay = cal.getTime();
		Date nowDate = DateUtil.getStartDateOfDay();

		//计算日期
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		Date firstMonthStart = DateUtil.getFirstDayOfMonth(cal);//第一个月第一天

		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		Date secondMonthStart = DateUtil.getFirstDayOfMonth(cal);//第二个月第一天

		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date thirdMonthStart = DateUtil.getFirstDayOfMonth(cal); //第三个月第一天
		Date thirdMonthLast = DateUtil.getEndDayOfMonth(cal); //第三个月最后一天

		cal = Calendar.getInstance();
		Date fourMonthStart = DateUtil.getFirstDayOfMonth(cal); //这个月第一天

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = cal.getTime();//第二个月第一天
	}

	/**
	 * 获得一年前的开始时间
	 * @param startTime
	 * @return
	 */
	public static Long countPreStartTime(long startTime){
		Date d1 = new Date(startTime);//定义起始日期
		Calendar dd = Calendar.getInstance();//定义日期实例
		dd.setTime(d1);//设置日期起始时间
		dd.add(Calendar.MONTH, -11);//对当前月份加1
		Long start=dd.getTimeInMillis();
		return start;
	}
	
	/**
	 * 获得一年各月的开始时间结束时间
	 * @param preStartTime
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static List<TimeEntity> countTimePeriod(long preStartTime,long startTime, long endTime) throws Exception {		
		Date d1 = new Date(preStartTime);//定义起始日期
		Date d2 = new Date(startTime);//定义结束日期

		Calendar dd = Calendar.getInstance();//定义日期实例
		dd.setTime(d1);//设置日期起始时间
		List<TimeEntity> list=new ArrayList<TimeEntity>();
		while(dd.getTime().before(d2)){//判断是否到结束日期			
			TimeEntity te=new TimeEntity();
			Long start=dd.getTimeInMillis();//每月开始时间
			te.setStartTime(start);
			dd.add(Calendar.MONTH, 1);//对当前月份加1
			Long end=dd.getTimeInMillis()-1;
			te.setEndTime(end);			
			list.add(te);
		}
		TimeEntity te=new TimeEntity();
		te.setStartTime(startTime);
		te.setEndTime(endTime);
		list.add(te);
		return list;
	}

	/**
	 * 获得当年的开始时间
	 * @param startTime
	 */
	public static long getYearStartTime(long startTime) {
		Date d = new Date(startTime);		
		int month=d.getMonth();
		Calendar c = Calendar.getInstance();
	    c.setTime(d);
	    c.add(Calendar.MONTH, -(month));//对当前月份加1
		long end=c.getTimeInMillis();
		return end;
	}	
	
}
