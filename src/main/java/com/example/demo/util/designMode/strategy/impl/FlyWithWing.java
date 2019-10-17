package com.example.demo.util.designMode.strategy.impl;

import com.example.demo.util.designMode.strategy.FlyStragety;

public class FlyWithWing implements FlyStragety {

	@Override
	public void performFly() {
		System.out.println("用翅膀飞");
	}

}
