package com.example.demo.util.java8.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test1 {
	
	public static void main(String[] args) {
		method1();
	}

	
	private static void method1() {
		List<String> list = Stream.of("hello","world").map(word-> word+"!").collect(Collectors.toList());
		System.out.println(list.toString());
	}
	

}
