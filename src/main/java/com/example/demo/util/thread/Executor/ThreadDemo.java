package com.example.demo.util.thread.Executor;

import java.util.HashMap;
import java.util.Map;

public class ThreadDemo {

    public static void main(String[] args) {
    	ExecutorService executorService = Executors.newFixedThreadPool(4);   	
    	
    	Future<Object> future = executorService.submit(new Callable<Object>() {
    	        @Override
    	        public Object call() throws Exception {
    	            throw new RuntimeException("exception in call~");// 该异常会在调用Future.get()时传递给调用者
    	        }
    	    });
    	    
    	try {
    	  Object result = future.get();
    	} catch (InterruptedException e) {
    	  // interrupt
    	} catch (ExecutionException e) {
    	  // exception in Callable.call()
    	  e.printStackTrace();
    	}   
    	
    }
    
}
