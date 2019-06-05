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
	
	public String choices_ListtoString() {
		if(!choices.isEmpty()) {
			String choices_=choices.get(0);
			choices_ += ",";
			for(int i=1;i<choices.size();i++) {
				choices_ += choices.get(i);
				if(!(i==choices.size()-1)) choices_ += ",";
			}
			return choices_;
		} else {
			return "";
		}
	}

}
