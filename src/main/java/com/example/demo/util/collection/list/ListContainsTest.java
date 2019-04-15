package com.example.demo.util.collection.list;

import java.util.ArrayList;
import java.util.List;

public class ListContainsTest {

	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(300);
		list.add(2100);
		list.add(2);
		System.out.println(list.contains(1));
		System.out.println(list.contains(30));
		System.out.println(list.contains(100));
	}

}
