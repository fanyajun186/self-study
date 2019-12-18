package com.example.demo.util.thread.yield;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author: yajun.fan
 * @date: 2019年12月8日
 */
public class YieldTest1 {

	public static void main(String[] args) {
//		Thread thread1 = new Thread(new Worker(), "线程1");
//		Thread thread2 = new Thread(new Worker(), "线程2");
//		Thread thread3 = new Thread(new Worker(), "线程3");
//		
//		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//		newCachedThreadPool.execute(thread1);
//		newCachedThreadPool.execute(thread2);
//		newCachedThreadPool.execute(thread3);
//		newCachedThreadPool.shutdown();
		
		//ReentrantLock lock =new ReentrantLock();
		
		
		int m=1 << 16;
		System.out.println(m);
		
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new LockerTest(), "线程1");
			newFixedThreadPool.execute(thread);
		}
		newFixedThreadPool.shutdown();
	}
	

	
	
}

/**
 * yield 使当前线程让出 CPU 时间片，线程从运行状态（Running）变为可执行状态（Runnable），处于可执行状态的线程有可能会再次获取到时间片继续执行，
 * 也有可能处于等待状态，直到再次获取到时间片。也就是说，后续会有两种情况：
 * 1、当前线程让出 CPU 时间片后，又立即获取到 CPU 时间片，进而继续执行当前方法。
 * 2、当前线程让出 CPU 时间片后，等待一段时间后获取到 CPU 时间片，进而继续执行当前方法。
 */
class Worker implements Runnable{
	@Override
	public void run() {
		for (int i = 0; i < 11; i++) {
			 System.out.println("threadName=" + Thread.currentThread().getName() + ", index=" + i);
			if(5==i) {
				Thread.yield();
				 System.out.println("threadName=" + Thread.currentThread().getName() + "执行 yield 方法，让出 CPU 时间片");
			}
		}
	}		
}

class LockerTest implements Runnable{

	@Override
	public void run() {		
		czy czy=new czy();
		czy.read(Thread.currentThread());
	}
	
}

class czy {   
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    public void read(Thread thread) {
       rwl.readLock().lock();
       try {
           long start = System.currentTimeMillis();

           while(System.currentTimeMillis() - start <= 1) {
               System.out.println(thread.getName()+"正在进行读操作");
           }
           System.out.println(thread.getName()+"读操作完毕");
       } finally {
           rwl.readLock().unlock();
       }
   }
}
