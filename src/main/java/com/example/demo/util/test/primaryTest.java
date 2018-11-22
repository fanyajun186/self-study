package com.example.demo.util.test;

public class primaryTest {

	public static void main(String[] args) {		
		//charToInt();
		//printSanjiao();
		jiujiu();
	}

	private static void jiujiu() {
		for(int i=1;i<=9;i++){
			for(int j=1;j<=i;j++){
				System.out.print(j+"*"+i+"="+i*j+"  ");
			}
			System.out.println();
		}
		
	}

	//字符型转整型
	private static void charToInt() {
		System.out.println('A'+1);
		System.out.println('a'+1);
		int x=3,y;
		y=(x>1)?'a':200;
		System.out.println(y);		
	}


	//打印三角形
	private static void printSanjiao() {
		for(int i=0;i<5;i++){
			for(int j=5;j>i;j--){
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
	
	
}
