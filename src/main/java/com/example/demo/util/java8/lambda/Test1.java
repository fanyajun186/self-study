package com.example.demo.util.java8.lambda;

import java.util.stream.Stream;

public class Test1 {

	public static void main(String[] args) {
		method1();

	}

	private static void method1() {
		String str1 = Stream.of("hello", "world").reduce("", (a,b)->a+"？？"+b);
		System.out.println(str1);//？？hello？？world
		
		String str2 = Stream.of("hello", "world").reduce("", (a,b)->b+"？？"+a);
		System.out.println(str2);//world？？hello？？
	}

	private String combineWithoutTrailingDash(String a, String b) {
	    if (a.isEmpty()) {
	        return b;
	    }
	    return b + "-" + a;
	}
}
