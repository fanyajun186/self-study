package com.example.demo.util.collection.blockingQueue;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlokingQueueTest {
	
	private static final AtomicInteger c = new AtomicInteger();

	public static void main(String[] args) {
		 final AtomicInteger count = c;
		 System.out.println(count==c);
		 
		 AtomicLong al=new AtomicLong(100);
		 System.out.println(al.incrementAndGet());
		 System.out.println(al.getAndIncrement());
		 
		 Date a,b;
		 a=new Date();
		 b=a;
		 System.out.println(a==b);
		 
		 
	       System.out.println("count1："+count.get());
	        int c = -1;	       
       
            if (count.get() < 1024) {
                c = count.getAndIncrement();
            }
	        
		LinkedBlockingQueue e =new LinkedBlockingQueue();
		e.offer(12);
		
	}
}
