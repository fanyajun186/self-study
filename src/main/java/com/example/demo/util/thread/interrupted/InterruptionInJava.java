package com.example.demo.util.thread.interrupted;

public class InterruptionInJava implements Runnable{
	
	private static boolean on=false;
	
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new InterruptionInJava(), "测试interrupt");
		t.start();
		Thread.sleep(1000);
		InterruptionInJava.on = true;
		//t.interrupt();
		
		Thread.sleep(1000);
		System.out.println("main end");
	}

	@Override
	public void run() {
		while(!on) {
			/*if(Thread.currentThread().isInterrupted()) {				
				System.out.println("中断标记是true了又不停");
				//return;
			}else {
				System.out.println("我在跑。。。");
			}*/

			try {
				Thread.sleep(10000000);
			} catch (InterruptedException e) {
				System.out.println("caught exception right now: "+e);
			}
		}
		
	}

}
