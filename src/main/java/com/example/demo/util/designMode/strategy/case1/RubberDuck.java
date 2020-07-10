package com.example.demo.util.designMode.strategy.case1;

import com.example.demo.util.designMode.strategy.case1.impl.FlyWithWing;

/**
 * 橡胶鸭
 * @author: yajun.fan
 * @date: 2019年10月17日
 */
public class RubberDuck extends Duck {

	public RubberDuck() {
		super();
		super.setFlyStragety(new FlyWithWing());
	}
	
	@Override
	public void display() {
		System.out.println("脑袋是红的");
	}

}
