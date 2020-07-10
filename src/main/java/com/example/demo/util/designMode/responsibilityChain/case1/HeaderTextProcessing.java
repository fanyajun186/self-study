package com.example.demo.util.designMode.responsibilityChain.case1;

public class HeaderTextProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String input) {
		return "From Raoul,Mario and Alan,"+input;
	}

}
