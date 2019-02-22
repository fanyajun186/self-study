package com.example.demo.util.base.tousle;

public class testTime {

	public static void main(String[] args) {
		long startTime=1477584000000L;
		int level=1;
		System.out.println(countCloseTime(startTime,level));
	}

	private static long countCloseTime(long startTime, int alarmLevel) {
		long closeTime=startTime;
		if(alarmLevel==0){
			closeTime=startTime+30*24*60*60*1000L;
		}else if(alarmLevel==1){
			closeTime=startTime+15*24*60*60*1000L;
		}else if(alarmLevel==2){
			closeTime=startTime+7*24*60*60*1000L;
		}
		return closeTime;
	}
	
	
}
