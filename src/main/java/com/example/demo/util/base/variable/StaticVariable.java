package com.example.demo.util.base.variable;

public class StaticVariable {


	static int num = 4;
	String name;//成员变量
	static String country = "CN";//静态变量，类变量
	
	public static void main(String[] args) {
		StaticVariable s1 = new StaticVariable();
		StaticVariable s = new StaticVariable();
		s.name = "小强";
		s.country = "ss";
		s.show();
		System.out.println(StaticVariable.country);
		print();		
	}
	
	public void show(){
		System.out.println(country+":"+name);
		System.out.println("=========================");
		System.out.println(StaticVariable.country+":"+this.name);//静态成员省略的是类名，成员变量省略的是this
	}
	
	/**
	 * 静态方法只能调用静态成员
	 * 静态方法中不可以定义this或者super关键字
	 */
	public static void print(){
		System.out.println(num);
		//System.out.println(name);//静态方法只能调用静态成员
	}

}
