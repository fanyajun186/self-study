package com.example.demo.util.designMode.singleton;

/**
 * 双重锁，保证单例模式线程安全
 * @author: yajun.fan
 * @date: 2019年10月16日
 */
public class DclSingleton {

	//添加volatil确保读在写后面，避免重排序
    private volatile static DclSingleton singleton;  
    private DclSingleton (){
    	 if (singleton!=null) {
        	 throw new RuntimeException("实例已存在");
        }	
    }      
    
    public static DclSingleton getInstance() {  
    if (singleton == null) {  
        synchronized (DclSingleton.class) {  
        if (singleton == null) {  
            singleton = new DclSingleton();  
        }  
        }  
    }  
    return singleton;  
    }  
	
}
