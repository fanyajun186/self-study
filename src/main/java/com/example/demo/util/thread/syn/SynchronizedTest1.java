package com.example.demo.util.thread.syn;

/**
 * www.cnblogs.com/paddix/p/5367116.html
 * @author: yajun.fan
 * @date: 2019年8月2日
 */
public class SynchronizedTest1 {

	public void method1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }
	
	 public void method2(){
	        System.out.println("Method 2 start");
	        try {
	            System.out.println("Method 2 execute");
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Method 2 end");
	  }
	 
	   public static void main(String[] args) {
	        final SynchronizedTest1 test = new SynchronizedTest1();

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
