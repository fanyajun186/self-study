package com.example.demo.util.designMode.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticProxiedHello implements Hello{

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private Hello hello=new HelloImp();
    
    @Override
    public String sayHello(String str) {
        logger.info("You said "+ str);
        return hello.sayHello(str);
    }

}
