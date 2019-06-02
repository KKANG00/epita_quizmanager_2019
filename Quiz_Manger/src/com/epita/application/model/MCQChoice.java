package com.epita.application.model;

import java.util.List;

public class MCQChoice {
	
	private final List<String> choices;

	public MCQChoice(List<String> excl1) {
		this.choices = excl1;
	}
	
	public List<String> MCQtoList(){
		return choices;
	}

}
