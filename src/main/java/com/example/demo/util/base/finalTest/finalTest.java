package com.example.demo.util.base.finalTest;

import org.springframework.util.Assert;

public class finalTest {

	public static final String LOAN = "loan";

    public static void main(String[] args) {    	
        final Person p = new Person(20, "炭烧生蚝");
        System.out.println(p.hashCode());
        p.setId(18);   //可以修改p对象的数据
        System.out.println(p.getId()); //输出18

        Person pp = new Person(30, "蚝生烧炭");
        Assert.notNull(pp, "用户不存在.");
        //p = pp; //这行代码会报错, 不能通过编译, 因为p经final修饰永远指向上面定义的p对象, 不能指向pp对象.    
        testFinal();
	}
	
    /**
     * n2会在编译期间放到常量池中,s 变量所对应的"20190522"字符串会放入到字符串常量池中.
     * 这时候拼接字符串 s1，由于 n1 对应的数据没有放入常量池中，所以 s1 暂时无法拼接，需要等程序加载运行时才能确定 s1 对应的值
     * 拼接 s2 的时候，由于 n2 已经存在于常量池，所以可以直接与"0522"拼接，拼接出的结果是"20190522"。
     * 这时系统会查看字符串常量池，发现已经存在字符串20190522，所以直接返回20190522的引用。所以 s2 和 s 指向的是同一个引用
     * 拼接 s1 的时候，会创建一个新的 String 类型对象，也就是说字符串常量池中的 20190522 会对外提供一个新的引用
     */
	public static void testFinal(){
	    int n1 = 2019;          //普通变量
	    final int n2 = 2019;    //final修饰的变量

	    String s = "20190522";  
	    String s1 = n1 + "0522";    //拼接字符串"20190512"
	    String s2 = n2 + "0522";    

	    System.out.println(s == s1);    //false
	    System.out.println(s == s2);    //true
	}
}
