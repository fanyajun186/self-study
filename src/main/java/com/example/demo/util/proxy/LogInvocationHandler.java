package com.example.demo.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//动态代理
public class LogInvocationHandler implements InvocationHandler{
    
    /**
     * 1.代理对象是在程序运行时产生的，而不是编译期
     * 2.对代理对象的所有接口方法调用都会转发到InvocationHandler.invoke()方法，在invoke()方法里我们可以加入任何逻辑，
     * 比如修改方法参数，加入日志功能、安全检查功能等；之后我们通过某种方式执行真正的方法体，示例中通过反射调用了Hello对象的相应方法，
     * 还可以通过RPC调用远程方法。    
     */
           
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private Hello hello;
    
    public LogInvocationHandler(Hello hello){
        this.hello=hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        if("sayHello".equals(method.getName())){
            //logger.info("You said: " + Arrays.toString(args));
            System.out.println("You said: " + Arrays.toString(args));
        }
        return method.invoke(hello, args);
    }

}
