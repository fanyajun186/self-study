package com.example.demo.util.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;


public class testSet {

	public static void main(String[] args) {
		//testSet();
		objectToString();
	}

	
	private static void objectToString() {
		Object[] objs = {"1","2","3"};
		String[] strs = Arrays.asList(objs).toArray( new String[0] );
		System.out.println(strs.length);
		
	}


	private static void testSet() {
		Map<String,String> map= new HashedMap<String,String>();
		Set<String> keySet = map.keySet();
		String str=map.get("1");
		System.out.println(str);
		for(String type : keySet){
			System.out.println(type);
		}
	}
	
}
