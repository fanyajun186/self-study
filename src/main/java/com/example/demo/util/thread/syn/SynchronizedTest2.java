package com.example.demo.util.thread.syn;

/**
 * 虽然method1和method2是不同的方法，但是这两个方法都进行了同步，并且是通过同一个对象去调用的，
 * 所以调用之前都需要先去竞争同一个对象上的锁（monitor），也就只能互斥的获取到锁，因此，method1和method2只能顺序的执行。
 * 两个对象分别调用两个方法，各自执行
 * @author: yajun.fan
 * @date: 2019年8月2日
 */
public class SynchronizedTest2 {

	public synchronized void method1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }
	
	 public synchronized void method2(){
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
	        final SynchronizedTest2 test = new SynchronizedTest2();
	        //final SynchronizedTest2 test2 = new SynchronizedTest2();

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
