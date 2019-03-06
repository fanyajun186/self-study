package com.example.demo.util.thread.Executor;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) {
    	
    	for (int i = 0; i < 10; i++) {
    		int nextInt = new Random().nextInt(4);
    		System.out.println("nextInt:"+nextInt);			
		}
    	
		
    	ExecutorService executorService = Executors.newFixedThreadPool(4);   
    	
    	executorService.execute(new Runnable() {
			@Override
			public void run() {				
				System.out.println("aaaaa");
			}    		
    	});
    	
    	
    	Future<Object> future = executorService.submit(new Callable<Object>() {
    	        @Override
    	        public Object call() throws Exception {
    	            //throw new RuntimeException("exception in call~");// 该异常会在调用Future.get()时传递给调用者
    	        	return "bbbbb";
    	        }
    	    });
    	    
    	try {
    	  Object result = future.get();
    	  System.out.println(result);
    	  System.out.println("ccccc");
    	} catch (InterruptedException e) {
    	  // interrupt
    	} catch (ExecutionException e) {
    	  // exception in Callable.call()
    	  e.printStackTrace();
    	}   
    	
    }
    
    public void testLatch(ExecutorService executorService, List<Runnable> tasks) throws InterruptedException{
    	      
	    CountDownLatch latch = new CountDownLatch(tasks.size());
	      for(Runnable r : tasks){
	          executorService.submit(new Runnable() {
	              @Override
	              public void run() {
	                  try{
	                      r.run();
	                  }finally {
	                      latch.countDown();// countDown
	                  }
	              }
	          });
	      }
	      latch.await(10, TimeUnit.SECONDS); // 指定超时时间
	 }
    
}
