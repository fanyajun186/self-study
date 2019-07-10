package com.example.demo.util.designMode.responsibilityChain;

public class SpellCheckProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String input) {
		// TODO Auto-generated method stub
		return input.replace("labda", "lambda");
	}

}
