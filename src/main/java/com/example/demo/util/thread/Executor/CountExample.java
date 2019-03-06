package com.example.demo.util.thread.Executor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CountExample {
	
	private final static Logger logger = LoggerFactory.getLogger(CountExample.class);

	 // 请求总数
    public static int clientTotal = 1;

    // 同时并发执行的线程数
    public static int threadTotal = 1;

    public static int count = 0;
    
     static AtomicInteger test = new AtomicInteger();


    public static void main(String[] args) throws Exception {
    	
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量，此处用于控制并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁，可实现计数器递减
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                	//执行此方法用于获取执行许可，当总计未释放的许可数不超过200时，
                	//允许通行，否则线程阻塞等待，直到获取到许可。
                    semaphore.acquire();
                    send();                    
                    //释放许可
                    semaphore.release();
                } catch (Exception e) {
                    //log.error("exception", e);
                    e.printStackTrace();
                }
                //闭锁减一
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();//线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        executorService.shutdown();
        logger.info("count:{}", count);
        logger.info("test:{}", test.intValue());
    }

    private static void add() {
         count++;
         test.getAndIncrement();
        //count.incrementAndGet();
    }
	
    private static void send() {
    	try {
    		String url1="http://172.28.12.193:9000/app/alarm/sendInvokeException";
    		String url2="http://172.28.12.193:9000/app/alarm/sendDataBaseException";
    		String url3="http://172.28.12.193:9000/app/alarm/sendException";
    		String url4="http://172.28.12.193:9000/app/alarm/sendHttpException";
    		String[] urls= new String[]{url1,url2,url3,url4};
    		int nextInt = new Random().nextInt(4);
    		System.out.println("nextInt:"+nextInt);
    		
        	HttpClient httpclient = HttpClients.createDefault();        	 
        	HttpGet  httpGet = new HttpGet(urls[nextInt]);            
            httpGet.setHeader("Content-Type", "application/json; charset=utf-8");     
            HttpResponse response = httpclient.execute(httpGet);
            add();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
