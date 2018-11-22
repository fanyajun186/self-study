package com.example.demo.util.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Preloader {

    //method1
    private final static FutureTask<Object> future = new FutureTask<Object>(new Callable<Object>(){
        @Override
        public Object call() throws Exception{
            return "yes";
        }
    });

    //method2
    static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final Future<Object> futureExecutor = executor.submit(new Callable<Object>(){
        @Override
        public Object call() throws Exception{
            return new ExecutionException(null);
            //return "no";
        }
    });        

 

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        executor.shutdown();
        future.run();
        System.out.println(future.get());
        System.out.println(futureExecutor.get());
        Thread t=new Thread();
    }
    
}
