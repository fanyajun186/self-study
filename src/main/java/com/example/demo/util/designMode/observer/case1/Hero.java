package com.example.demo.util.designMode.observer.case1;

public class Hero extends Subject {

	void move(){
        System.out.println("主角向前移动");
        notifyObservers();
    }
}
