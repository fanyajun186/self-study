package com.example.demo.util.thread.interrupted;
/**
 * interrupt中断机制中有如下方法：
- Thread.interrupt()，设置当前中断标记为true（类似属性的set方法）
- Thread.isInterrupted()，检测当前的中断标记（类似属性的get方法）
- Thread.interrupted()，检测当前的中断标记，然后重置中断标记为false（类似属性的get方法+set方法） 
 * @author: yajun.fan
 * @date: 2019年12月8日
 */
public class InterruptTest {

	  //这里用来打印消耗的时间
	  private static long time = 0;
	  
	  public static void main(String[] args) {
	    test1();
	  }

	  private static void test1(){

	    Thread1 thread1 = new Thread1();
	    thread1.start();

	    //主线程睡眠，延时3秒后线程1被interrupt中断
	    try {
	      Thread.sleep(3000);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    //线程1标识中断
	    thread1.interrupt();
	    printContent("执行中断");

	  }

	  private static class Thread1 extends Thread{

	    @Override public void run() {

	      resetTime();

	      int num = 0;
	      while (true){
	    	//调用interrupt时，中断标记被设置为true，所以
	        if(isInterrupted()){
	          printContent("当前线程 isInterrupted");
	          break;
	        }
	        num++;
	        
	        /**
	         * 如果Thread1线程中有执行需要捕获InterruptedException异常的操作，比如Thread的sleep，join方法，Object的wait，Condition的await等，它是强制需要捕获InterruptedException异常的，
	         * 那么当thread1.interrupt方法调用之后，它会给thread1线程抛出一个InterruptedException异常，那么在while循环中，就能捕获到这个异常然后这个异常抛出之后，又会马上将线程中断标识重置为false，
	         * 因此在下次的while循环中判断isInterrupted时，它是false，也就不会break，然后while循环会一直执行下去。
	         */
	        try {
	          Thread.sleep(1);
	        } catch (InterruptedException e) {
	        	//被catch住的时候标识就已经变成false。
	        	System.out.println("此时的中断标识:"+isInterrupted());
	        	interrupt();
	        	System.out.println("在看此时的中断标识:"+isInterrupted());
	          e.printStackTrace();
	        }

	        if(num % 100 == 0){
	          printContent("主线程你先睡，我先执行，num : " + num);
	        }
	      }

	    }

	  }
	  
	  private static void resetTime(){
		   time = System.currentTimeMillis();
	  }
	  private static void printContent(String content){
		  System.out.println(content + "     时间：" + (System.currentTimeMillis() - time));
	  }	
}
