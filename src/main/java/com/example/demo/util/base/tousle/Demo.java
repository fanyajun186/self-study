package com.example.demo.util.base.tousle;

public class Demo {

	public static void main(String[] args) {
		Parent child=new Child();
		child.echo();
		child.echo2();
		System.out.println(child.i);
		
		Child child2=new Child();
		child2.echo();
		child2.echo2();
		System.out.println(child2.i);
	}

}
