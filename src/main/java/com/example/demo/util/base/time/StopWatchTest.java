package com.example.demo.util.base.time;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

/**
 * 
 * Description:计时器
 * @author fanyj
 * @date 2020年1月15日
 */
public class StopWatchTest {

	public static void main(String[] args) throws Exception {
		StopWatch stopWatch = StopWatch.createStarted();
		
		Thread.sleep(1000);
		stopWatch.split();
		System.out.println(stopWatch.getSplitTime());
		System.out.println(stopWatch.getSplitNanoTime());
		
		Thread.sleep(5000);
		stopWatch.split();
		System.out.println(stopWatch.getSplitTime());
		System.out.println(stopWatch.getSplitNanoTime());
		
		stopWatch.stop();
	    System.out.println(stopWatch.getTime(TimeUnit.SECONDS));

	}

}
