package com.example.demo.util.base.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SimpleDateFormat不是线程安全,验证并发问题
 * @author fan
 *
 */
public class SimpleDateFormatTest {

	//private static final SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
	private static ThreadLocal<DateFormat> threadLocal=new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {			
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}		
	};
	
	private static final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws ParseException, InterruptedException {
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 20; i++) {
			int firstStr = i+1;
			System.out.println("第"+firstStr+"个线程");
			service.execute(() ->{
				for (int j = 0; j < 10; j++) {
					int secondStr = j+1;
					try {
						System.out.println("第"+firstStr+"个线程的第"+secondStr+"个子线程");
						/*System.out.println("第"+firstStr+"个线程的第"+secondStr+"个子线程"+"时间："+parse("2019-03-26 18:00:00"));*/
						//解决办法四：基于JDK1.8的DateTimeFormatter，线程安全的
						System.out.println("第"+firstStr+"个线程的第"+secondStr+"个子线程"+"时间："+parse2(formateDate2(LocalDateTime.now())));
					} catch (Exception e) {						
						e.printStackTrace();
					}
				}
			}					
			);
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.DAYS);		
		
	}

	private static LocalDateTime parse2(String str) {		
		return LocalDateTime.parse(str, formatter);
	}

	private static String formateDate2(LocalDateTime date) {
		return formatter.format(date);		
	}

	private static Date parse(String source) throws ParseException {
		//解决办法一：不采用公共的sdf，每个方法自创建，缺点就是频繁的创建销毁，效率低
		//SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//解决办法二：采用公共的sdf，给parse方法套上synchronized，缺点并发量大的时候会对性能有影响，线程阻塞。
		/*synchronized(sdf){
			return sdf.parse(source);
		}*/
		//ThreadLocal可以确保每个线程都可以得到单独的一个SimpleDateFormat的对象，那么自然也就不存在竞争问题了
		return threadLocal.get().parse(source);
		
	}

	private static String formateDate(Date date) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);		
	}	
	
	
}
