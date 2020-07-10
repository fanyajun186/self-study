package com.example.demo.util.designMode.strategy.case1;

public abstract class Duck {

	/**
	 * 通常行为，由超类实现，子类使用即可，有差异的可继承重写
	 */
	public void quack() {
		System.out.println("嘎嘎嘎");
	}
	
	/**
	 * 鸭子外观各不相同，声明成abstract，由子类实现
	 */
	public abstract void display();

	
	private FlyStragety flyStragety;

	public void setFlyStragety(FlyStragety flyStragety) {
		this.flyStragety = flyStragety;
	}
	
	public void fly() {
		flyStragety.performFly();
	}
}
