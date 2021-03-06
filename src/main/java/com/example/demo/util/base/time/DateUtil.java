package com.example.demo.util.base.time;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期函数
 *
 * @Description:
 * @Author:Sine Chen
 * @Date:Dec 2, 2014
 * @Copyright: All Rights Reserved. Copyright(c) 2014
 */
public class DateUtil {

	public static final DateFormat DF_FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat DF_HMS = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final DateFormat DF_DATE = new SimpleDateFormat("yyyyMMdd");


    /**
     * 获取日期格式(yyyyMMdd)的字符串
     *
     * @param date
     * @return
     */
    public static String dateToDayString(Date date) {
        if (date == null) {
            return "";
        }
        String dateStr = DF_DATE.format(date);
        return dateStr;
    }


	/**
	 * 日期格转换为字符串
	 * @return
	 */
	public static String dateToString(Date date){
		if(date == null){
			return "";
		}
		String dateStr = DF_HMS.format(date);
		return dateStr;
	}

	/**
	 * 格式化时间
	 *
	 * @param date
	 * @return
	 */
	public static String formatDT(Date date) {
		return DF_FULL.format(date);
	}

	/**
	 * 获取传入日期所在月份的第一天
	 *
	 * @param calendar
	 * @return
	 * @throws Exception
	 */
	public static Date getFirstDayOfMonth(Calendar calendar) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 本月的第一天
		calendar.set(Calendar.DATE, 1);
		String dataStr = sdf.format(calendar.getTime());
		return sdf.parse(dataStr);
	}

	/**
	 * 获取传入日期所在月份的最后一天
	 *
	 * @param calendar
	 * @return
	 * @throws Exception
	 */
	public static Date getEndDayOfMonth(Calendar calendar) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		String dataStr = sdf.format(calendar.getTime());
		return sdf.parse(dataStr);
	}

	/**
	 * @Description: 获取当前月份开始时间
	 * @return String 返回类型
	 * @author gaoxiang
	 * @throws ParseException
	 * @date 2016年4月26日 下午7:02:54
	 */
	public static Date getThisMonthStartDate() throws ParseException {
		String ymDate = new SimpleDateFormat("yyyy-MM").format(new Date());
		return DF_FULL.parse(ymDate + "-01 00:00:00");
	}

	/**
	 * @Description: 获取当前月份下个月开始时间
	 * @throws ParseException
	 * @return Date 返回类型
	 * @author gaoxiang
	 * @date 2016年4月26日 下午7:10:26
	 */
	public static Date getNextMonthStartDate() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		String ymDate = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
		return DF_FULL.parse(ymDate + "-01 00:00:00");
	}

	/**
     * 获取一天的开始时间
     * @return
     */
    public static Date getStartDateOfDay() {
    	Calendar currentDate = Calendar.getInstance();
    	currentDate.set(Calendar.HOUR_OF_DAY, 0);
    	currentDate.set(Calendar.MINUTE, 0);
    	currentDate.set(Calendar.SECOND, 0);
    	currentDate.set(Calendar.MILLISECOND, 0);
    	return (Date)currentDate.getTime().clone();
    }

    /**
     * 获取一天的结束时间
     * @return
     */
    public static Date getEndDateOfDay() {
    	Calendar currentDate = Calendar.getInstance();
    	currentDate.set(Calendar.HOUR_OF_DAY, 23);
    	currentDate.set(Calendar.MINUTE, 59);
    	currentDate.set(Calendar.SECOND, 59);
	    currentDate.set(Calendar.MILLISECOND, 59);
    	return (Date)currentDate.getTime().clone();
    }

	/**
	 * 获取指定一天的开始时间
	 * @return
	 */
	public static Date getStartDateOfDay(Date date) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return (Date)currentDate.getTime().clone();
	}

	/**
	 * 获取指定一天的结束时间
	 * @return
	 */
	public static Date getEndDateOfDay(Date date) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.MILLISECOND, 59);
		return (Date)currentDate.getTime().clone();
	}

	public static Date addByDay(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(5, days);
		date = calendar.getTime();
		return date;
	}
	
	public static String getPreStartTime(Date d,Integer i){
	    Calendar c = Calendar.getInstance();
	    c.setTime(d);
        c.add(Calendar.MONTH, -i);
        SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
        String time = format.format(c.getTime());
        return time;
	}

	 //获取指点时间前N月的月份时间
    public static int getPreMonth(Date d,Integer i){
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MONTH, -i);
        return c.get(Calendar.MONTH) + 1;
    }
	
	public static void main(String[] args) throws Exception {
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
	    Date d=sdf.parse("201701");
	    int smonth=getPreMonth(d,1);
	    System.out.println(smonth);
	    Date date=new Date();
	    System.out.println(date);
    }


	
}
