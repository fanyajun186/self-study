package com.example.demo.util.designMode.responsibilityChain.case1;

public class SpellCheckProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String input) {
		return input.replace("labda", "lambda");
	}

}
