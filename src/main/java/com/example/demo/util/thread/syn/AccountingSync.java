package com.example.demo.util.thread.syn;

public class AccountingSync implements Runnable{
    static AccountingSync instance=new AccountingSync();
    static int i=0;
    static int j=0;
    
    @Override
    public void run() {
        for(int j=0;j<10;j++){ 
            //this,当前实例对象锁
            synchronized(this){
            	System.out.println("线程名:"+Thread.currentThread().getName());
                i++;
                increase();//synchronized的可重入性,线程拿到当前对象的锁，再调用increase(),方法相当于调用可重入锁
            }
        }
    }
 
    public synchronized void increase(){
        j++;
    }
 
 
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
