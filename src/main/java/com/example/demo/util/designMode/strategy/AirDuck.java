package com.example.demo.util.designMode.strategy;

import com.example.demo.util.designMode.strategy.impl.FlyWithWing;

/**
 * 太空鸭
 * @author: yajun.fan
 * @date: 2019年10月17日
 */
public class AirDuck extends Duck {

	public AirDuck() {
		super();
		super.setFlyStragety(new FlyWithWing());
	}
	
	@Override
	public void display() {
		System.out.println("脖子是绿的");
	}

}
