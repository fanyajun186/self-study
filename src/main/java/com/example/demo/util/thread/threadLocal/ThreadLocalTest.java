package com.example.demo.util.thread.threadLocal;

public class ThreadLocalTest {

	public static void main(String[] args) {
		MyRunable runable = new MyRunable();

		new Thread(runable, "线程1").start();
		new Thread(runable, "线程2").start();
	}
	
	//线程类
	public static class MyRunable implements Runnable{
		
		//创建ThreadLocal
		private ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
			protected String initialValue() {
				return "初始化值";
			} 
		};

		@Override
		public void run() {
			
			//运行线程时，分别设置&获取ThreadLocal的值
			String name=Thread.currentThread().getName();
			System.out.println("set前threadLocal的值"+threadLocal.get());
			threadLocal.set(name+"的threadLocal");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+":"+threadLocal.get());
		}
		
	}

}
