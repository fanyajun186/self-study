package com.example.demo.util.thread.Executor;

import java.util.HashMap;
import java.util.Map;

public class ThreadDemo {

    public static void main(String[] args) {
       // Executors.newFixedThreadPool(10);        
      /*  StringBuffer sb = new StringBuffer();
        System.out.println(sb);
        String str=null;
        sb.append(str);
        String s1 = sb.toString();
        
        sb.append("a");
        sb.append("b");

        String s = sb.toString();
        System.out.println(s);*/
        
        
        Map<String,String> map=new HashMap<String,String>();
        for (int i=0;i<1000;i++) {
            map.put(i+"", i+"");
        }
        map.put("name", "fan");
        map.put("name1", "ya");
        map.put("name2", "jun");
        map.put("name3", "bai");
        map.put("name4", "jing");
        map.put("age", "28");
        System.out.println("如果阿什顿发");
        
        
        
    }
}
