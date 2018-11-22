package com.example.demo.util.proxy;

import java.lang.reflect.Proxy;

public class testLog {

    public static void main(String[] args) {
        
        Hello hello = (Hello)Proxy.newProxyInstance(
                HelloImp.class.getClassLoader(), // 1. 类加载器
                new Class<?>[] {Hello.class}, // 2. 代理需要实现的接口，可以有多个
                new LogInvocationHandler(new HelloImp()));// 3. 方法调用的实际处理者    

            System.out.println(hello.sayHello("I love you!"));
    }
    
}
