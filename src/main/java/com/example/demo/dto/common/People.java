package com.example.demo.dto.common;

import java.util.Date;

import lombok.Data;


public class People extends Biology {

	private String name;
	
	private int age;
	
	private int sex;
	
	private String aLoanAmount;
	
	private String rLoanAmount;
	
	private String rCarLoanAmount;
	
	private Date appTime;// 订单提交时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getaLoanAmount() {
		return aLoanAmount;
	}

	public void setaLoanAmount(String aLoanAmount) {
		this.aLoanAmount = aLoanAmount;
	}

	public String getrLoanAmount() {
		return rLoanAmount;
	}

	public void setrLoanAmount(String rLoanAmount) {
		this.rLoanAmount = rLoanAmount;
	}

	public String getrCarLoanAmount() {
		return rCarLoanAmount;
	}

	public void setrCarLoanAmount(String rCarLoanAmount) {
		this.rCarLoanAmount = rCarLoanAmount;
	}

	public Date getAppTime() {
		return appTime;
	}

	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}
	
	
}
