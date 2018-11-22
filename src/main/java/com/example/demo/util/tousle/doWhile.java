package com.example.demo.util.tousle;

public class doWhile {

	public static void main(String[] args) {
		int x=10;
		do {
			System.out.print("value x : "+x);
			x++;
			//当19进来执行完打印后，自增，变为20不在符合while的条件
		} while (x<20);
	}
}
