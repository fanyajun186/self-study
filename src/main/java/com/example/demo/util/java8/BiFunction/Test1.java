package com.example.demo.util.java8.BiFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.Test;

public class Test1 {

	public static void main(String[] args) {
		/*method1();
		method2();
		method3();*/
	}

	private static void method1() {		
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		List<String> result = new ArrayList<>();
		for (int i=0; i < list1.size(); i++) {
		    result.add(list1.get(i) + list2.get(i));
		}
		System.out.println(result);
	}
	
	private static void method2() {		
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		List<String> result = listCombiner(list1,list2,(a,b)-> a+b);
		System.out.println(result);
	}
	
	private static void method3() {		
		List<Double> list1 = Arrays.asList(1.0d, 2.1d, 3.3d);
		List<Float> list2 = Arrays.asList(0.1f, 0.2f, 4f);
		List<Boolean> result = listCombiner(list1,list2,(a,b)-> a>b);		
		System.out.println(result);
	}
	
	@Test
	public  void method4() {
		List<Double> list1 = Arrays.asList(1.0d, 2.1d, 3.3d);
		List<Float> list2 = Arrays.asList(0.1f, 0.2f, 4f);
		List<Boolean> result = listCombiner(list1,list2,this::firstIsGreaterThanSecond);		
		System.out.println(result);
	}
	
	@Test
	public  void method5() {
		List<Double> list1 = Arrays.asList(1.0d, 2.1d, 3.3d);
		List<Double> list2 = Arrays.asList(0.1d, 0.2d, 4d);
		List<Integer> result = listCombiner(list1, list2, Double::compareTo);
		System.out.println(result);
	}
	
	@Test
	public  void method6() {
		List<Double> list1 = Arrays.asList(1.0d, 2.1d, 3.3d);
		List<Double> list2 = Arrays.asList(0.1d, 0.2d, 4d);
		List<Boolean> result = listCombiner(list1, list2, asBiFunction(Double::compareTo).andThen(i -> i > 0));
		System.out.println(result);
	}
	
	/**
	 * 这里可以使用 andThen 在原来的基础上增加额外的处理。这会生成 BiFunction，先对两个输入执行操作，然后接着执行另一个操作。
	 * @param function
	 * @return
	 */
	private static <T, U, R> BiFunction<T, U, R> asBiFunction(BiFunction<T, U, R> function) {
	    return function;
	}
	
	private boolean firstIsGreaterThanSecond(Double a, Float b) {
	    return a > b;
	}
	
	private static <T, U, R> List<R> listCombiner(List<T> list1, List<U> list2, BiFunction<T, U, R> combiner) {
	    List<R> result = new ArrayList<>();
	    for (int i = 0; i < list1.size(); i++) {
	        result.add(combiner.apply(list1.get(i), list2.get(i)));
	    }
	    return result;
	}
	
}
