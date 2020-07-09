package com.example.demo.util.designMode.observer.case1;

public class Client {

	public static void main(String[] args) {
		Hero hero = new Hero();
		Monster Monster = new Monster();
		Trap trap = new Trap();
		Treasure treasure = new Treasure();
		hero.attachObserver(Monster);
		hero.attachObserver(trap);
		hero.attachObserver(treasure);
		hero.move();
	}

}
