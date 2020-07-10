package com.example.demo.util.designMode.strategy.case1;

import com.example.demo.util.designMode.strategy.case1.impl.FlyWithWing;

/**
 * 野鸭子
 * @author: yajun.fan
 * @date: 2019年10月17日
 */
public class MallardDuck extends Duck {

	public MallardDuck() {
		super();
		super.setFlyStragety(new FlyWithWing());
	}
	
	@Override
	public void display() {
		System.out.println("脖子是绿的");
	}

}
