package com.example.demo.util.base.finalTest;

public class Person {

	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
