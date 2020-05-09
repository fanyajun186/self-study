package com.example.demo.util.thread;

import java.util.concurrent.Exchanger;

/**
 * exchange只能是两个线程之间，交换这个东西只能两两进行。
 * Description:
 * @author fanyj
 * @date 2020年5月9日
 */
public class T12_TestExchanger {

	 static Exchanger<String> exchanger = new Exchanger<>();

	    public static void main(String[] args) {
	        new Thread(()->{
	            String s = "T1";
	            try {
	                s = exchanger.exchange(s);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("第一线程:"+Thread.currentThread().getName() + " " + s);

	        }, "t111").start();


	        new Thread(()->{
	            String s = "T2";
	            try {
	                s = exchanger.exchange(s);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("第二线程:"+Thread.currentThread().getName() + " " + s);

	        }, "t222").start();


	    }
	
}
