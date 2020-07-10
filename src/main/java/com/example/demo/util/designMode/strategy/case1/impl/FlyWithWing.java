package com.example.demo.util.designMode.strategy.case1.impl;

import com.example.demo.util.designMode.strategy.case1.FlyStragety;

public class FlyWithWing implements FlyStragety {

	@Override
	public void performFly() {
		System.out.println("用翅膀飞");
	}

}
