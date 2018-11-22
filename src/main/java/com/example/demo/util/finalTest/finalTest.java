package com.example.demo.util.finalTest;

public class finalTest {

	public static final String LOAN = "loan";

    public static void main(String[] args) {
		Person p=new Person();
		p.setName("fan");
		testFinal(p);
		System.out.println(p.getName());
		System.out.println(args.length);
	}
	
	public static void testFinal( Person p1){
		//p1=new Person();
		p1.setName("bai");		
		System.out.println(p1.getName());
	}
}
