package com.example.demo.util.abstractTest;

/**
 * 1.抽象类中可以有abstract方法，也可以是非abstract
 * 2.抽象类不能使用new运算符创建对象
 * 3.如果一个非抽象类是某个抽象类的子类，那么它必须重写父类的abstract方法，
 * 即在子类中将abstract方法重新声明，但必须去掉abstract修饰符，同时要保证声明的方法名字，
 * 返回类型，参数个数和类型与父类的abstract方法完全相同。
 * 4.多态时，Base类只认Base中有的方法，除非是子类覆盖Base中的方法
 *
 */
public abstract class Base {

    abstract void hello();
    
    public void cry(){
        System.out.println("Base cry");
    }
}
