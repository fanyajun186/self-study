package com.example.demo.util.designMode.strategy;

public class DuckTest {
	
	public static void main(String[] args) {
		System.out.println("ready go");
		Duck duck=null;
		
		//duck=new MallardDuck();
		//duck=new RedheadDuck();
		//duck=new AirDuck();
		//duck=new RubberDuck();
		
		duck.quack();
		duck.display();
		duck.fly();
	}

}
