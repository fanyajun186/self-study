package com.example.demo.util.collection.set;

import java.util.HashSet;

public class TestHashSet {

    public static void main(String[] args) {
        HashSet hs=new HashSet();
        hs.add("aaaa");       
        for (Object obj : hs) {
            System.out.println(obj);
        }
       
        
    }
}
