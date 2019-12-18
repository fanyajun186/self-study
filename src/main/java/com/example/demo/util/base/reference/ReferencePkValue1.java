package com.example.demo.util.base.reference;

/**
 * 值传递：方法调用时，实际参数把它的值传递给对应的形式参数，函数接收的是原始值的一个copy，此时内存中存在两个相等的基本类型，即实际参数和形式参数，
 * 后面方法中的操作都是对形参这个值的修改，不影响实际参数的值。
 * 
 * 引用传递：也称为传地址。方法调用时，实际参数的引用(地址，而不是参数的值)被传递给方法中相对应的形式参数，函数接收的是原始值的内存地址；
 * 在方法执行中，形参和实参内容相同，指向同一块内存地址，方法执行中对引用的操作将会影响到实际对象。
 * @author: yajun.fan
 * @date: 2019年12月16日
 */
public class ReferencePkValue1 {

	public static void main(String[] args) {
		ReferencePkValue1 pk=new ReferencePkValue1();
		int a=99;
		pk.test1(a);//a实际参数
		System.out.println(a);
		
		myObj obj=new myObj();
		pk.test2(obj);
		System.out.println(obj.m);
	}



	//a形参，
	private void test1(int a) {
		a=a++;//先赋值再相加，所以是99
		System.out.println(a);
		
	}
	
	private void test2(myObj obj) {
		obj.m=100;
        System.out.println(obj.m);		
	}
	

}

class myObj {
	public int m=99;
} 
