package com.example.demo.util.designMode.observer.case1;

//观察者
/**
 * 所谓观察者模式，是一种基于事件和响应的设计模式，常常用于传统的窗体应用程序，以及游戏开发领域
 * spring框架也用了该设计模式，ApplicationListener和ApplicationContext
 *
 */
public interface Observer {
	
	public void update();

}
