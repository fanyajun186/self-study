package com.example.demo.util.thread.syn;

/**
 * 对于代码块的同步实质上需要获取Synchronized关键字后面括号中对象的monitor，由于这段代码中括号的内容都是this，
 * 而method1和method2又是通过同一的对象去调用的，所以进入同步块之前需要去竞争同一个对象上的锁，因此只能顺序执行同步块。
 * @author: yajun.fan
 * @date: 2019年8月2日
 */
public class SynchronizedTest4 {

	public void method1(){
        System.out.println("Method 1 start");
        try {
        	synchronized(this) {
        		System.out.println("Method 1 execute");
                Thread.sleep(3000);
        	}
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }
	
	 public void method2(){
	        System.out.println("Method 2 start");
	        try {
	        	synchronized(this) {
	        		System.out.println("Method 2 execute");
		            Thread.sleep(1000);
	        	}	            
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Method 2 end");
	  }
	 
	   public static void main(String[] args) {
	        final SynchronizedTest4 test = new SynchronizedTest4();

	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                test.method1();
	            }
	        }).start();

	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                test.method2();
	            }
	        }).start();
	    }
}
