package com.example.demo.util.base.finalTest;

public class Person {

	private int id;
	private String name;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
