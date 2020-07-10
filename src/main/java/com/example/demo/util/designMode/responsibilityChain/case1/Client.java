package com.example.demo.util.designMode.responsibilityChain.case1;

public class Client {

	public static void main(String[] args) {
		ProcessingObject<String> p1=new HeaderTextProcessing();
		ProcessingObject<String> p2=new SpellCheckProcessing();
		ProcessingObject<String> p3=new HelloWorldProcessing();
		
		p1.setSuccessor(p2);
		p2.setSuccessor(p3);
		
		String result = p1.handler("Aren`t labdas really sexy??");
		System.out.println(result);
	}
	
}
