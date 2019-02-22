package com.example.demo.util.designMode.proxy;

public class HelloImp implements Hello {

    @Override
    public String sayHello(String str) {
        
        return "HelloImp "+str;
    }

}
