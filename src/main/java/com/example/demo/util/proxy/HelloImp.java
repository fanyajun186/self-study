package com.example.demo.util.proxy;

public class HelloImp implements Hello {

    @Override
    public String sayHello(String str) {
        
        return "HelloImp "+str;
    }

}
