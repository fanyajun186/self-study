package com.example.demo.util.designMode.responsibilityChain;

public class HelloWorldProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String input) {
		return "Hello world,"+input;
	}

}
