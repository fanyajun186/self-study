package com.example.demo.util.base.tousle;

public class continueTest {

	public static void main(String[] args) {
		int[] numbers={10,20,30,40,50};
		for (int i : numbers) {
			if(i==30){
				continue;
			}
			System.out.println(i);
			
		}
	}
}
