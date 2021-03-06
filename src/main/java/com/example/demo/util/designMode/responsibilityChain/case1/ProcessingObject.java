package com.example.demo.util.designMode.responsibilityChain.case1;

public abstract class ProcessingObject<T> {

	protected ProcessingObject<T> successor;

	public void setSuccessor(ProcessingObject<T> successor) {
		this.successor = successor;
	}
	
	public T handler(T input) {
		sayHello();		
		T r =handleWork(input);
		if(successor!=null) {
			return successor.handler(r);
		}
		return r;
	}
	

	public void sayHello() {
		System.out.println("hello,"+Thread.currentThread().getName());
	}

	protected abstract T handleWork(T input);
	
}
