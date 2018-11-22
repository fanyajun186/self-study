package com.example.demo.util.poiUtil;


public class DBSearcher {

	private Class<?> klass;   //调用对象类class
	private String annoName;  //调用对象类注解名
	private String methodName;//调用方法名称
	private String paraValue; //方法参数
	
	
	public DBSearcher() {
		super();
	}
	
	public DBSearcher(Class<?> klass, String annoName, String methodName, String paraValue) {
		super();
		this.klass = klass;
		this.annoName = annoName;
		this.methodName = methodName;
		this.paraValue = paraValue;
	}

	public String getAnnoName() {
		return annoName;
	}

	public void setAnnoName(String annoName) {
		this.annoName = annoName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

	public Class<?> getKlass() {
		return klass;
	}

	public void setKlass(Class<?> klass) {
		this.klass = klass;
	}
	
	
	
	
}
