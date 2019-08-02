package com.example.demo.util.thread.syn;

/**
 * 虽然test和test2属于不同对象，但是test和test2属于同一个类的不同实例，由于method1和method2都属于静态同步方法，
 * 所以调用的时候需要获取同一个类上monitor（每个类只对应一个class对象），所以也只能顺序的执行。
 * 改成一个对象调用也是顺序执行
 * @author: yajun.fan
 * @date: 2019年8月2日
 */
public class SynchronizedTest3 {

	public static synchronized void method1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }
	
	 public static synchronized void method2(){
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
		   final SynchronizedTest3 test = new SynchronizedTest3();
		   final SynchronizedTest3 test2 = new SynchronizedTest3();
	
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                test.method1();
	            }
	        }).start();
	
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                test2.method2();
	            }
	        }).start();
	    }
}
