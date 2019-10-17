package com.example.demo.util.designMode.singleton;

import java.lang.reflect.Constructor;

public class DclSingletonTest {

	public static void main(String[] args) {
		DclSingleton singleton1 = DclSingleton.getInstance();
		DclSingleton singleton2 = null;	   
	    try {
	    	Class<DclSingleton> clazz = DclSingleton.class;
	        Constructor<DclSingleton> constructor = clazz.getDeclaredConstructor();
	        constructor.setAccessible(true);
	        singleton2 = constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    System.out.println(singleton1.hashCode());
	    System.out.println(singleton2.hashCode());
	}

	
}
