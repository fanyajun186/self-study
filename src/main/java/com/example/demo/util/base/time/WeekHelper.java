package com.example.demo.util.base.time;

import java.text.SimpleDateFormat;
import java.util.*;


/** 
 * @Description:指定时段周历
 * @author: yajun.fan
 * @date: 2019年3月22日
 */
public class WeekHelper {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static long SevenDay = 604800;
    
    public static void main(String[] args) {
        String start = "2018-12-31";
        String end = "2019-03-31";
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            getWeekSplit(startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @param args the command line arguments
     * @return
     */
    public static List<Week> getWeekSplit(Date startDate, Date endDate) {
        ArrayList<Week> WeekList = new ArrayList<>();
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        Calendar FistSaturday = Calendar.getInstance();
        FistSaturday.set(startCal.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        
        while (FistSaturday.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            FistSaturday.add(Calendar.DAY_OF_YEAR, 1);
        }

        while (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            startCal.add(Calendar.DAY_OF_YEAR, 1);
        }
        while (endCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            endCal.add(Calendar.DAY_OF_YEAR, -1);
        }
        System.out.println(sdf.format(startCal.getTime()) + "\t" + sdf.format(endCal.getTime()));
        int startYear = startCal.get(Calendar.YEAR);
        while (startCal.compareTo(endCal) < 0) {
            int endYear = startCal.get(Calendar.YEAR);
            if (startYear < endYear) {
                FistSaturday.set(endYear, Calendar.JANUARY, 1, 0, 0, 0);
                while (FistSaturday.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                    FistSaturday.add(Calendar.DAY_OF_YEAR, 1);
                }
                startYear = endYear;
            }
            Week everyWeek = new Week();
            long WeekNum = (startCal.getTimeInMillis() / 1000 - FistSaturday.getTimeInMillis() / 1000) / SevenDay+1;
            String WeekNumStr = String.valueOf(WeekNum);
            if (WeekNum < 10) {
                WeekNumStr = "0" + WeekNum;
            }
            everyWeek.setYear(startCal.get(Calendar.YEAR));
            everyWeek.setWeekBegin(sdf.format(startCal.getTime()));
            startCal.add(Calendar.DATE, 6);
            everyWeek.setWeekEnd(sdf.format(startCal.getTime()));
            everyWeek.setWeekNum(WeekNumStr);
            startCal.add(Calendar.DATE, 1);
            WeekList.add(everyWeek);
        }
        Iterator<Week> iter = WeekList.iterator();
        System.out.println("开始打印");
        while (iter.hasNext()) {
            Week everyweek = iter.next();
            System.out.println(everyweek.getYear() + "年第" + everyweek.getWeekNum() + "周\t" + "开始时间：" + everyweek.getWeekBegin() + "\t结束时间" + everyweek.getWeekEnd());
        }
        return WeekList;
    }
}

class Week {

    String weekNum;
    int year;
    String weekBegin;
    String weekEnd;

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getWeekBegin() {
        return weekBegin;
    }

    public void setWeekBegin(String weekBegin) {
        this.weekBegin = weekBegin;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }
}

