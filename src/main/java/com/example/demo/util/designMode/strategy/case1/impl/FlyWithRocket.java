package com.example.demo.util.designMode.strategy.case1.impl;

import com.example.demo.util.designMode.strategy.case1.FlyStragety;

public class FlyWithRocket implements FlyStragety{

	@Override
	public void performFly() {
		System.out.println("用火箭飞");
	}
	
}
